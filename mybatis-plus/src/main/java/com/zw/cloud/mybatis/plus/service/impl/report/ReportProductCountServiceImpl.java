package com.zw.cloud.mybatis.plus.service.impl.report;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.zw.cloud.common.entity.dto.LocalDateDTO;
import com.zw.cloud.common.entity.vo.LocationVO;
import com.zw.cloud.common.utils.DateTimeUtils;
import com.zw.cloud.mybatis.plus.entity.report.ReportProductCount;
import com.zw.cloud.mybatis.plus.entity.report.dto.ProductReportCountQueryDTO;
import com.zw.cloud.mybatis.plus.entity.report.dto.ProductReportCraftCountDTO;
import com.zw.cloud.mybatis.plus.entity.report.dto.ProductReportProductCountDTO;
import com.zw.cloud.mybatis.plus.entity.report.vo.ReportProductCaleDateCountVO;
import com.zw.cloud.mybatis.plus.entity.report.vo.ReportProductCountVO;
import com.zw.cloud.mybatis.plus.mapper.ReportProductCountMapper;
import com.zw.cloud.mybatis.plus.service.api.report.IReportProductCountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 定型产量统计报表 服务实现类
 * </p>
 *
 * @author zw
 * @since 2023-06-25
 */
@Service
public class ReportProductCountServiceImpl extends ServiceImpl<ReportProductCountMapper, ReportProductCount> implements IReportProductCountService {

    @Override
    public ReportProductCountVO queryProductReportCount(ProductReportCountQueryDTO productReportCountQueryDTO) {
        if (Objects.equals(2,productReportCountQueryDTO.getTimeType())) {
            // 按周
            LocalDate time = productReportCountQueryDTO.getStartTime();
            productReportCountQueryDTO.setStartTime(time.with(TemporalAdjusters.firstDayOfMonth()));
            productReportCountQueryDTO.setEndTime(time.with(TemporalAdjusters.lastDayOfMonth()));
        }
        if (Objects.equals(3,productReportCountQueryDTO.getTimeType())) {
            // 按月
            productReportCountQueryDTO.setEndTime(productReportCountQueryDTO.getEndTime().with(TemporalAdjusters.lastDayOfMonth()));
        }
        // 查询数据库中相关数据
        LambdaQueryWrapper<ReportProductCount> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ReportProductCount::getOrgCode,productReportCountQueryDTO.getOrgCode())
                .eq(ReportProductCount::getWorkshopId,productReportCountQueryDTO.getWorkshopId())
                .ge(ReportProductCount::getCalcDay,productReportCountQueryDTO.getStartTime())
                .le(ReportProductCount::getCalcDay,productReportCountQueryDTO.getEndTime());
        List<ReportProductCount> reportProductCountList = baseMapper.selectList(queryWrapper);
        ReportProductCountVO productCountVO = new ReportProductCountVO();
        if (CollectionUtils.isEmpty(reportProductCountList)) {
            return productCountVO;
        }
        // 工序类型集合
        Set<String> craftIdSet = new HashSet<>();
        reportProductCountList.forEach(reportProductCount -> {
            Object craftProductInfo = reportProductCount.getCraftProductInfo();
            if (Objects.isNull(craftProductInfo)) {
                return;
            }
            Map<String,Object> craftMap = JSON.parseObject(JSON.toJSONString(craftProductInfo), Map.class);
            craftIdSet.addAll(craftMap.keySet());
        });
        List<String> craftIdStrList = Lists.newArrayList(craftIdSet);
        List<Long> craftIdList = craftIdStrList.stream().map(Long::parseLong).sorted(Comparator.comparingLong(s -> s)).collect(Collectors.toList());
        List<ReportProductCaleDateCountVO> reportProductCaleDateCountVOList = new ArrayList<>();
        // 按照 日期+机台 维度进行汇总
        if (Objects.equals(1,productReportCountQueryDTO.getTimeType())) {
            queryByDay(productReportCountQueryDTO,reportProductCountList,craftIdList,reportProductCaleDateCountVOList);
        } else if (Objects.equals(2,productReportCountQueryDTO.getTimeType())) {
            queryByWeek(productReportCountQueryDTO,reportProductCountList,craftIdList,reportProductCaleDateCountVOList);
        } else {
            queryByMonth(productReportCountQueryDTO,reportProductCountList,craftIdList,reportProductCaleDateCountVOList);
        }
        // 总计
        List<ProductReportProductCountDTO> totalReportProductCountDTOList = new ArrayList<>();
        reportProductCaleDateCountVOList.forEach(reportProductCaleDateCountVO -> {
            List<ProductReportProductCountDTO> reportProductCountDTOList = reportProductCaleDateCountVO.getReportProductCountDTOList();
            List<ProductReportProductCountDTO> sumList = reportProductCountDTOList.stream().filter(productReportProductCountDTO -> Objects.equals(0L, productReportProductCountDTO.getDeviceId())).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(sumList)) {
                return;
            }
            totalReportProductCountDTOList.addAll(sumList);
        });
        ReportProductCaleDateCountVO sumCaleDateCountVO = new ReportProductCaleDateCountVO();
        sumCaleDateCountVO.setCaleDateStr("总计");

        ProductReportProductCountDTO totalProductCountDTO = new ProductReportProductCountDTO();
        totalProductCountDTO.setDeviceName("总计");
        sumProductReportCraftCount(totalReportProductCountDTOList,totalProductCountDTO,craftIdList);

        sumCaleDateCountVO.setReportProductCountDTOList(Lists.newArrayList(totalProductCountDTO));

        productCountVO.setCraftNameList(craftIdStrList);
        reportProductCaleDateCountVOList.add(sumCaleDateCountVO);
        productCountVO.setReportProductCaleDateCountVOList(reportProductCaleDateCountVOList);
        return productCountVO;
    }

    private void queryByDay(ProductReportCountQueryDTO productReportCountQueryDTO,List<ReportProductCount> reportProductCountList,List<Long> craftIdList,List<ReportProductCaleDateCountVO> reportProductCaleDateCountVOList) {

        LocalDate endTime = productReportCountQueryDTO.getEndTime();
        LocalDate startCalcTime = productReportCountQueryDTO.getStartTime();

        while (startCalcTime.compareTo(endTime) <= 0) {
            LocalDate finalStartCalcTime = startCalcTime;
            List<ReportProductCount> caleDataList = reportProductCountList.stream().filter(reportProductCount -> reportProductCount.getCalcDay().isEqual(finalStartCalcTime)).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(caleDataList)) {
                ReportProductCaleDateCountVO caleDateCountVO = new ReportProductCaleDateCountVO();
                caleDateCountVO.setCaleDateStr(DateTimeUtils.parse2Str(finalStartCalcTime, null));
                parseReportProduct(caleDataList, caleDateCountVO, craftIdList);
                reportProductCaleDateCountVOList.add(caleDateCountVO);
            }

            startCalcTime = startCalcTime.plusDays(1);
        }

    }

    private void queryByWeek(ProductReportCountQueryDTO productReportCountQueryDTO,List<ReportProductCount> reportProductCountList,List<Long> craftIdList,List<ReportProductCaleDateCountVO> reportProductCaleDateCountVOList) {
        LocalDate startCalcTime = productReportCountQueryDTO.getStartTime();
        List<LocalDateDTO> weekPeriodsByMonth = DateTimeUtils.getWeekPeriodsByMonth(startCalcTime);
        for (LocalDateDTO localDateDTO : weekPeriodsByMonth) {
            List<ReportProductCount> caleDataList = reportProductCountList.stream().filter(reportProductCount -> reportProductCount.getCalcDay().compareTo(localDateDTO.getStartTime()) >= 0
                    && reportProductCount.getCalcDay().compareTo(localDateDTO.getEndTime()) <= 0).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(caleDataList)) {
                ReportProductCaleDateCountVO caleDateCountVO = new ReportProductCaleDateCountVO();
                caleDateCountVO.setCaleDateStr(localDateDTO.getDateStr());
                parseReportProduct(caleDataList, caleDateCountVO, craftIdList);
                reportProductCaleDateCountVOList.add(caleDateCountVO);
            }
        }
    }

    private void queryByMonth(ProductReportCountQueryDTO productReportCountQueryDTO,List<ReportProductCount> reportProductCountList,List<Long> craftIdList,List<ReportProductCaleDateCountVO> reportProductCaleDateCountVOList) {

        LocalDate endTime = productReportCountQueryDTO.getEndTime();
        LocalDate startCalcTime = productReportCountQueryDTO.getStartTime();

        while (startCalcTime.compareTo(endTime) <= 0) {
            LocalDate finalStartCalcTime = startCalcTime;
            LocalDate lastDayOfMonth = finalStartCalcTime.with(TemporalAdjusters.lastDayOfMonth());
            List<ReportProductCount> caleDataList = reportProductCountList.stream().filter(reportProductCount -> reportProductCount.getCalcDay().compareTo(finalStartCalcTime) >= 0
                    && reportProductCount.getCalcDay().compareTo(lastDayOfMonth) <= 0).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(caleDataList)) {
                ReportProductCaleDateCountVO caleDateCountVO = new ReportProductCaleDateCountVO();
                caleDateCountVO.setCaleDateStr(DateTimeUtils.parseMonth2Str(finalStartCalcTime, null) + "月");
                parseReportProduct(caleDataList, caleDateCountVO, craftIdList);
                reportProductCaleDateCountVOList.add(caleDateCountVO);
            }

            startCalcTime = startCalcTime.plusMonths(1);
        }

    }

    private void parseReportProduct(List<ReportProductCount> reportProductCountList,ReportProductCaleDateCountVO caleDateCountVO,List<Long> craftIdList) {
        Map<Long, List<ReportProductCount>> deviceMap = reportProductCountList.stream().collect(Collectors.groupingBy(ReportProductCount::getDeviceId));
        List<ProductReportProductCountDTO> reportProductCountDTOList = new ArrayList<>();
        deviceMap.forEach((k,v) -> {
            ProductReportProductCountDTO productCountDTO = new ProductReportProductCountDTO();
            List<ProductReportCraftCountDTO> deviceCraftCountDTOList = new ArrayList<>();
            ReportProductCount reportProductCount = v.get(0);
            productCountDTO.setDeviceId(reportProductCount.getDeviceId());
            productCountDTO.setDeviceName(reportProductCount.getDeviceName());
            // 生产记录数
            int productNum = 0;
            // 产量
            BigDecimal productQuantity = BigDecimal.ZERO;

            // 白班 生产记录数
            int whiteProductNum = 0;
            // 白班产量
            BigDecimal whiteProductQuantity = BigDecimal.ZERO;

            // 晚班生产记录数
            int blackProductNum = 0;
            //  晚班产量
            BigDecimal blackProductQuantity = BigDecimal.ZERO;

            Map<Long, List<ProductReportCraftCountDTO>> deviceCraftMap = new HashMap<>();
            for (ReportProductCount productCount : v) {
                productNum = productNum + Optional.ofNullable(productCount.getProductNum()).orElse(0);
                productQuantity = productQuantity.add(Optional.ofNullable(productCount.getProductQuantity()).orElse(BigDecimal.ZERO));

                whiteProductNum = whiteProductNum + Optional.ofNullable(productCount.getWhiteProductNum()).orElse(0);
                whiteProductQuantity = whiteProductQuantity.add(Optional.ofNullable(productCount.getWhiteProductQuantity()).orElse(BigDecimal.ZERO));

                blackProductNum = blackProductNum + Optional.ofNullable(productCount.getBlackProductNum()).orElse(0);
                blackProductQuantity = blackProductQuantity.add(Optional.ofNullable(productCount.getBlackProductQuantity()).orElse(BigDecimal.ZERO));

                // 工序类型计算
                Object craftProductInfo = productCount.getCraftProductInfo();
                if (Objects.nonNull(craftProductInfo)) {
                    Map<String, ProductReportCraftCountDTO> craftMap = JSON.parseObject(JSON.toJSONString(craftProductInfo),new TypeReference<Map<String, ProductReportCraftCountDTO>>() {});

                    craftMap.forEach((raftId,craftCountDTO) -> {
                        List<ProductReportCraftCountDTO> craftCountDTOList = deviceCraftMap.get(Long.parseLong(raftId));
                        if (Objects.isNull(craftCountDTOList)) {
                            craftCountDTOList = new ArrayList<>();
                        }
                        craftCountDTOList.add(craftCountDTO);
                        deviceCraftMap.put(Long.parseLong(raftId),craftCountDTOList);
                    });
                }

            }

            if (productQuantity.compareTo(BigDecimal.ZERO) > 0) {
                // 白班产量占比
                BigDecimal whiteProductQuantityRate = whiteProductQuantity.divide(productQuantity,2, RoundingMode.HALF_UP);
                productCountDTO.setWhiteProductQuantityRate(whiteProductQuantityRate);

                // 晚班产量占比
                BigDecimal blackProductQuantityRate = blackProductQuantity.divide(productQuantity,2, RoundingMode.HALF_UP);
                productCountDTO.setBlackProductQuantityRate(blackProductQuantityRate);

            }
            productCountDTO.setProductNum(productNum);
            productCountDTO.setProductQuantity(productQuantity);
            productCountDTO.setWhiteProductQuantity(whiteProductQuantity);
            productCountDTO.setWhiteProductNum(whiteProductNum);
            productCountDTO.setBlackProductQuantity(blackProductQuantity);
            productCountDTO.setBlackProductNum(blackProductNum);

            for (Long craftId : craftIdList) {
                List<ProductReportCraftCountDTO> craftCountDTOList = deviceCraftMap.get(craftId);
                ProductReportCraftCountDTO productReportCraftCountDTO = new ProductReportCraftCountDTO();
                productReportCraftCountDTO.setCraftId(craftId);
                deviceCraftCountDTOList.add(productReportCraftCountDTO);
                if (CollectionUtils.isEmpty(craftCountDTOList)) {
                    continue;
                }
                caleCraftCount(craftCountDTOList,productReportCraftCountDTO,productQuantity);
            }
            productCountDTO.setCraftCountDTOList(deviceCraftCountDTOList);

            reportProductCountDTOList.add(productCountDTO);
        });
        // 小计
        ProductReportProductCountDTO sumProductCountDTO = new ProductReportProductCountDTO();
        sumProductCountDTO.setDeviceId(0L);
        sumProductCountDTO.setDeviceName("小计");
        sumProductReportCraftCount(reportProductCountDTOList,sumProductCountDTO,craftIdList);

        reportProductCountDTOList.add(sumProductCountDTO);

        caleDateCountVO.setReportProductCountDTOList(reportProductCountDTOList);
    }

    private void caleCraftCount(List<ProductReportCraftCountDTO> craftCountDTOList,ProductReportCraftCountDTO productReportCraftCountDTO, BigDecimal deviceProductQuantity) {
        // 生产记录数
        int productNum = 0;
        // 产量
        BigDecimal productQuantity = BigDecimal.ZERO;

        // 白班 生产记录数
        int whiteProductNum = 0;
        // 白班产量
        BigDecimal whiteProductQuantity = BigDecimal.ZERO;

        // 晚班生产记录数
        int blackProductNum = 0;
        //  晚班产量
        BigDecimal blackProductQuantity = BigDecimal.ZERO;

        for (ProductReportCraftCountDTO productCount : craftCountDTOList) {
            productNum = productNum + Optional.ofNullable(productCount.getProductNum()).orElse(0);
            productQuantity = productQuantity.add(Optional.ofNullable(productCount.getProductQuantity()).orElse(BigDecimal.ZERO));

            whiteProductNum = whiteProductNum + Optional.ofNullable(productCount.getWhiteProductNum()).orElse(0);
            whiteProductQuantity = whiteProductQuantity.add(Optional.ofNullable(productCount.getWhiteProductQuantity()).orElse(BigDecimal.ZERO));

            blackProductNum = blackProductNum + Optional.ofNullable(productCount.getBlackProductNum()).orElse(0);
            blackProductQuantity = blackProductQuantity.add(Optional.ofNullable(productCount.getBlackProductQuantity()).orElse(BigDecimal.ZERO));


        }
        productReportCraftCountDTO.setProductNum(productNum);
        productReportCraftCountDTO.setProductQuantity(productQuantity);
        productReportCraftCountDTO.setWhiteProductQuantity(whiteProductQuantity);
        productReportCraftCountDTO.setWhiteProductNum(whiteProductNum);
        productReportCraftCountDTO.setBlackProductQuantity(blackProductQuantity);
        productReportCraftCountDTO.setBlackProductNum(blackProductNum);
        if (deviceProductQuantity.compareTo(BigDecimal.ZERO) > 0) {
            // 产量占比
            BigDecimal productQuantityRate = productQuantity.divide(deviceProductQuantity,2,RoundingMode.HALF_UP);
            productReportCraftCountDTO.setProductQuantityRate(productQuantityRate);
        }


        if (productQuantity.compareTo(BigDecimal.ZERO) > 0) {
            // 白班产量占比
            BigDecimal whiteProductQuantityRate = whiteProductQuantity.divide(productQuantity,2, RoundingMode.HALF_UP);
            productReportCraftCountDTO.setWhiteProductQuantityRate(whiteProductQuantityRate);

            // 晚班产量占比
            BigDecimal blackProductQuantityRate = blackProductQuantity.divide(productQuantity,2, RoundingMode.HALF_UP);
            productReportCraftCountDTO.setBlackProductQuantityRate(blackProductQuantityRate);

        }
    }

    private void sumProductReportCraftCount(List<ProductReportProductCountDTO> reportProductCountDTOList,ProductReportProductCountDTO sumProductCountDTO,List<Long> craftIdList) {
        // 生产记录数
        int productNum = 0;
        // 产量
        BigDecimal productQuantity = BigDecimal.ZERO;

        // 白班 生产记录数
        int whiteProductNum = 0;
        // 白班产量
        BigDecimal whiteProductQuantity = BigDecimal.ZERO;

        // 晚班生产记录数
        int blackProductNum = 0;
        //  晚班产量
        BigDecimal blackProductQuantity = BigDecimal.ZERO;

        List<ProductReportCraftCountDTO> allCraftCountDTOList = new ArrayList<>();

        for (ProductReportProductCountDTO productCount : reportProductCountDTOList) {
            productNum = productNum + Optional.ofNullable(productCount.getProductNum()).orElse(0);
            productQuantity = productQuantity.add(Optional.ofNullable(productCount.getProductQuantity()).orElse(BigDecimal.ZERO));

            whiteProductNum = whiteProductNum + Optional.ofNullable(productCount.getWhiteProductNum()).orElse(0);
            whiteProductQuantity = whiteProductQuantity.add(Optional.ofNullable(productCount.getWhiteProductQuantity()).orElse(BigDecimal.ZERO));

            blackProductNum = blackProductNum + Optional.ofNullable(productCount.getBlackProductNum()).orElse(0);
            blackProductQuantity = blackProductQuantity.add(Optional.ofNullable(productCount.getBlackProductQuantity()).orElse(BigDecimal.ZERO));

            List<ProductReportCraftCountDTO> craftCountDTOList = productCount.getCraftCountDTOList();
            if (CollectionUtils.isNotEmpty(craftCountDTOList)) {
                allCraftCountDTOList.addAll(craftCountDTOList);
            }
        }
        if (productQuantity.compareTo(BigDecimal.ZERO) > 0) {
            // 白班产量占比
            BigDecimal whiteProductQuantityRate = whiteProductQuantity.divide(productQuantity,2, RoundingMode.HALF_UP);
            sumProductCountDTO.setWhiteProductQuantityRate(whiteProductQuantityRate);

            // 晚班产量占比
            BigDecimal blackProductQuantityRate = blackProductQuantity.divide(productQuantity,2, RoundingMode.HALF_UP);
            sumProductCountDTO.setBlackProductQuantityRate(blackProductQuantityRate);

        }
        sumProductCountDTO.setProductNum(productNum);
        sumProductCountDTO.setProductQuantity(productQuantity);
        sumProductCountDTO.setWhiteProductQuantity(whiteProductQuantity);
        sumProductCountDTO.setWhiteProductNum(whiteProductNum);
        sumProductCountDTO.setBlackProductQuantity(blackProductQuantity);
        sumProductCountDTO.setBlackProductNum(blackProductNum);

        List<ProductReportCraftCountDTO> deviceCraftCountDTOList = new ArrayList<>();
        for (Long craftId : craftIdList) {
            List<ProductReportCraftCountDTO> craftProductReportCraftCountDTOList = allCraftCountDTOList.stream().filter(productReportCraftCountDTO -> Objects.equals(craftId, productReportCraftCountDTO.getCraftId())).collect(Collectors.toList());
            ProductReportCraftCountDTO productReportCraftCountDTO = new ProductReportCraftCountDTO();
            productReportCraftCountDTO.setCraftId(craftId);
            deviceCraftCountDTOList.add(productReportCraftCountDTO);
            if (CollectionUtils.isEmpty(craftProductReportCraftCountDTOList)) {
                continue;
            }

            caleCraftCount(craftProductReportCraftCountDTOList,productReportCraftCountDTO,productQuantity);
        }
        sumProductCountDTO.setCraftCountDTOList(deviceCraftCountDTOList);
    }
}
