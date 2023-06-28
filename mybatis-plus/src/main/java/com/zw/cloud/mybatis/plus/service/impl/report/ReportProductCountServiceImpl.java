package com.zw.cloud.mybatis.plus.service.impl.report;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.zw.cloud.common.entity.dto.LocalDateDTO;
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
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
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
@Slf4j
public class ReportProductCountServiceImpl extends ServiceImpl<ReportProductCountMapper, ReportProductCount> implements IReportProductCountService {

    /**
     * 定型产量统计
     */
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
        Integer caleType = productReportCountQueryDTO.getCaleType();
        // 查询数据库中相关数据
        LambdaQueryWrapper<ReportProductCount> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ReportProductCount::getOrgCode,productReportCountQueryDTO.getOrgCode())
                .eq(ReportProductCount::getWorkshopId,productReportCountQueryDTO.getWorkshopId())
                .ge(ReportProductCount::getCalcDay,productReportCountQueryDTO.getStartTime())
                .le(ReportProductCount::getCalcDay,productReportCountQueryDTO.getEndTime());
        List<ReportProductCount> reportProductCountList = baseMapper.selectList(queryWrapper);
        log.info("[ReportProductCountServiceImpl][queryProductReportCount]reportProductCountList is {}",JSON.toJSONString(reportProductCountList));
        ReportProductCountVO productCountVO = new ReportProductCountVO();
        if (CollectionUtils.isEmpty(reportProductCountList)) {
            return productCountVO;
        }
        // 工序类型集合
        Set<String> craftIdSet = new HashSet<>();
        reportProductCountList.forEach(reportProductCount -> {
            Object craftProductInfo = caleType == 1 ? reportProductCount.getCraftProductInfo() : reportProductCount.getProductLevelInfo();
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
            queryByDay(productReportCountQueryDTO,reportProductCountList,craftIdList,reportProductCaleDateCountVOList,caleType);
        } else if (Objects.equals(2,productReportCountQueryDTO.getTimeType())) {
            queryByWeek(productReportCountQueryDTO,reportProductCountList,craftIdList,reportProductCaleDateCountVOList,caleType);
        } else {
            queryByMonth(productReportCountQueryDTO,reportProductCountList,craftIdList,reportProductCaleDateCountVOList,caleType);
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

    /**
     * 定型产量统计导出
     */
    @Override
    public SXSSFWorkbook exportProductReportCount(ProductReportCountQueryDTO productReportCountQueryDTO) {
        ReportProductCountVO productCountVO = queryProductReportCount(productReportCountQueryDTO);
        List<String> craftNameList = productCountVO.getCraftNameList();

        //创建HSSFWorkbook对象(excel的文档对象)
        SXSSFWorkbook workbook = new SXSSFWorkbook(200);
        //建立新的sheet对象（excel的表单）
        SXSSFSheet sheet = workbook.createSheet("定型产量统计");

        CellStyle cellStyle = getStyleWithWrapText(workbook);
        int rowNum = 0;
        int titleCell = 0;
        SXSSFRow titleRow = sheet.createRow(rowNum ++);
        SXSSFRow secondTitleRow = sheet.createRow(rowNum ++);

        titleRow.createCell(titleCell++).setCellValue("统计日期");
        titleRow.createCell(titleCell++).setCellValue("机台");
        titleRow.createCell(titleCell++).setCellValue("生产记录数");
        titleRow.createCell(titleCell++).setCellValue("产量");

        SXSSFCell wightCell = titleRow.createCell(titleCell);
        wightCell.setCellStyle(cellStyle);
        wightCell.setCellValue("白班");
        SXSSFCell blackCell = titleRow.createCell(titleCell + 3);
        blackCell.setCellStyle(cellStyle);
        blackCell.setCellValue("晚班");
        // 起始行，结束行，起始列，结束列
        CellRangeAddress caleRegion = new CellRangeAddress(0, 1, 0, 0);
        CellRangeAddress deviceRegion = new CellRangeAddress(0, 1, 1, 1);
        CellRangeAddress productNumRegion = new CellRangeAddress(0, 1, 2, 2);
        CellRangeAddress productQuantityRegion = new CellRangeAddress(0, 1, 3, 3);

        CellRangeAddress whiteRegion = new CellRangeAddress(0, 0, 4, 6);
        CellRangeAddress blackRegion = new CellRangeAddress(0, 0, 7, 9);
        sheet.addMergedRegion(caleRegion);
        sheet.addMergedRegion(deviceRegion);
        sheet.addMergedRegion(productNumRegion);
        sheet.addMergedRegion(productQuantityRegion);
        sheet.addMergedRegion(whiteRegion);
        sheet.addMergedRegion(blackRegion);

        int titleSecondCell = 4;
        secondTitleRow.createCell(titleSecondCell++).setCellValue("生产记录数");
        secondTitleRow.createCell(titleSecondCell++).setCellValue("产量");
        secondTitleRow.createCell(titleSecondCell++).setCellValue("产量占比");
        secondTitleRow.createCell(titleSecondCell++).setCellValue("生产记录数");
        secondTitleRow.createCell(titleSecondCell++).setCellValue("产量");
        secondTitleRow.createCell(titleSecondCell++).setCellValue("产量占比");
        if (CollectionUtils.isNotEmpty(craftNameList)) {
            int index = 10;

            for (String craftName : craftNameList) {
                CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, index, index + 8);
                sheet.addMergedRegion(cellRangeAddress);
                SXSSFCell cell = titleRow.createCell(index);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(craftName);

                index += 9;
                // 表头第二列
                secondTitleRow.createCell(titleSecondCell++).setCellValue("生产记录数");
                secondTitleRow.createCell(titleSecondCell++).setCellValue("产量");
                secondTitleRow.createCell(titleSecondCell++).setCellValue("产量占比");
                secondTitleRow.createCell(titleSecondCell++).setCellValue("白班生产记录数");
                secondTitleRow.createCell(titleSecondCell++).setCellValue("白班产量");
                secondTitleRow.createCell(titleSecondCell++).setCellValue("白班产量占比");
                secondTitleRow.createCell(titleSecondCell++).setCellValue("晚班生产记录数");
                secondTitleRow.createCell(titleSecondCell++).setCellValue("晚班产量");
                secondTitleRow.createCell(titleSecondCell++).setCellValue("晚班产量占比");

            }
        }
        List<ReportProductCaleDateCountVO> caleDateCountVOList = productCountVO.getReportProductCaleDateCountVOList();
        if (CollectionUtils.isNotEmpty(caleDateCountVOList)) {
            for (ReportProductCaleDateCountVO caleDateCountVO : caleDateCountVOList) {
                SXSSFRow row = sheet.createRow(rowNum ++);
                int cell = 0;
                // 换行 \n
                SXSSFCell cell1 = row.createCell(cell++);
                cell1.setCellValue(caleDateCountVO.getCaleDateStr().replaceAll(":","\n"));
                CellStyle style = workbook.createCellStyle();
                style.setWrapText(true);
                cell1.setCellStyle(style);
                if (StringUtils.equals("总计",caleDateCountVO.getCaleDateStr())) {
                    CellRangeAddress cellRangeAddress = new CellRangeAddress(rowNum - 1, rowNum - 1, 0, 1);
                    sheet.addMergedRegion(cellRangeAddress);
                    cell++;
                }
                List<ProductReportProductCountDTO> reportProductCountDTOList = caleDateCountVO.getReportProductCountDTOList();
                boolean first = true;
                for (ProductReportProductCountDTO productCountDTO : reportProductCountDTOList) {
                    if (first) {
                        first = false;
                        if (!StringUtils.equals("总计",caleDateCountVO.getCaleDateStr())) {
                            CellRangeAddress cellRangeAddress = new CellRangeAddress(rowNum-1 , rowNum + reportProductCountDTOList.size() -2, 0, 0);
                            sheet.addMergedRegion(cellRangeAddress);
                        }
                    } else {
                        row = sheet.createRow(rowNum ++);
                        cell = 1;
                    }
                    String deviceName = productCountDTO.getDeviceName();
                    if (!StringUtils.equals("总计",caleDateCountVO.getCaleDateStr())) {
                        row.createCell(cell++).setCellValue(deviceName);
                    }
                    row.createCell(cell++).setCellValue(productCountDTO.getProductNum());
                    fillBigDecimalCell(productCountDTO.getProductQuantity(),row,cell++,true);

                    row.createCell(cell++).setCellValue(productCountDTO.getWhiteProductNum());
                    fillBigDecimalCell(productCountDTO.getWhiteProductQuantity(),row,cell++,true);
                    fillBigDecimalCell(productCountDTO.getWhiteProductQuantityRate(),row,cell++,false);

                    row.createCell(cell++).setCellValue(productCountDTO.getBlackProductNum());
                    fillBigDecimalCell(productCountDTO.getBlackProductQuantity(),row,cell++,true);
                    fillBigDecimalCell(productCountDTO.getBlackProductQuantityRate(),row,cell++,false);

                    List<ProductReportCraftCountDTO> craftCountDTOList = productCountDTO.getCraftCountDTOList();
                    if (CollectionUtils.isEmpty(craftCountDTOList)) {
                        continue;
                    }
                    for (ProductReportCraftCountDTO productReportCraftCountDTO : craftCountDTOList) {
                        row.createCell(cell++).setCellValue(productReportCraftCountDTO.getProductNum());
                        fillBigDecimalCell(productReportCraftCountDTO.getProductQuantity(),row,cell++,true);
                        fillBigDecimalCell(productReportCraftCountDTO.getProductQuantityRate(),row,cell++,false);

                        row.createCell(cell++).setCellValue(productReportCraftCountDTO.getWhiteProductNum());
                        fillBigDecimalCell(productReportCraftCountDTO.getWhiteProductQuantity(),row,cell++,true);
                        fillBigDecimalCell(productReportCraftCountDTO.getWhiteProductQuantityRate(),row,cell++,false);

                        row.createCell(cell++).setCellValue(productReportCraftCountDTO.getBlackProductNum());
                        fillBigDecimalCell(productReportCraftCountDTO.getBlackProductQuantity(),row,cell++,true);
                        fillBigDecimalCell(productReportCraftCountDTO.getBlackProductQuantityRate(),row,cell++,false);
                    }
                }
            }
        }

        return workbook;
    }

    private void queryByDay(ProductReportCountQueryDTO productReportCountQueryDTO,List<ReportProductCount> reportProductCountList,List<Long> craftIdList,List<ReportProductCaleDateCountVO> reportProductCaleDateCountVOList,Integer caleType) {

        LocalDate endTime = productReportCountQueryDTO.getEndTime();
        LocalDate startCalcTime = productReportCountQueryDTO.getStartTime();

        while (startCalcTime.compareTo(endTime) <= 0) {
            LocalDate finalStartCalcTime = startCalcTime;
            List<ReportProductCount> caleDataList = reportProductCountList.stream().filter(reportProductCount -> reportProductCount.getCalcDay().isEqual(finalStartCalcTime)).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(caleDataList)) {
                ReportProductCaleDateCountVO caleDateCountVO = new ReportProductCaleDateCountVO();
                caleDateCountVO.setCaleDateStr(DateTimeUtils.parse2Str(finalStartCalcTime, null));
                parseReportProduct(caleDataList, caleDateCountVO, craftIdList,caleType);
                reportProductCaleDateCountVOList.add(caleDateCountVO);
            }

            startCalcTime = startCalcTime.plusDays(1);
        }

    }

    private void queryByWeek(ProductReportCountQueryDTO productReportCountQueryDTO,List<ReportProductCount> reportProductCountList,List<Long> craftIdList,List<ReportProductCaleDateCountVO> reportProductCaleDateCountVOList,Integer caleType) {
        LocalDate startCalcTime = productReportCountQueryDTO.getStartTime();
        List<LocalDateDTO> weekPeriodsByMonth = DateTimeUtils.getWeekPeriodsByMonth(startCalcTime);
        for (LocalDateDTO localDateDTO : weekPeriodsByMonth) {
            List<ReportProductCount> caleDataList = reportProductCountList.stream().filter(reportProductCount -> reportProductCount.getCalcDay().compareTo(localDateDTO.getStartTime()) >= 0
                    && reportProductCount.getCalcDay().compareTo(localDateDTO.getEndTime()) <= 0).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(caleDataList)) {
                ReportProductCaleDateCountVO caleDateCountVO = new ReportProductCaleDateCountVO();
                caleDateCountVO.setCaleDateStr(localDateDTO.getDateStr());
                parseReportProduct(caleDataList, caleDateCountVO, craftIdList,caleType);
                reportProductCaleDateCountVOList.add(caleDateCountVO);
            }
        }
    }

    private void queryByMonth(ProductReportCountQueryDTO productReportCountQueryDTO,List<ReportProductCount> reportProductCountList,List<Long> craftIdList,List<ReportProductCaleDateCountVO> reportProductCaleDateCountVOList,Integer caleType) {

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
                parseReportProduct(caleDataList, caleDateCountVO, craftIdList,caleType);
                reportProductCaleDateCountVOList.add(caleDateCountVO);
            }

            startCalcTime = startCalcTime.plusMonths(1);
        }

    }

    private void parseReportProduct(List<ReportProductCount> reportProductCountList,ReportProductCaleDateCountVO caleDateCountVO,List<Long> craftIdList,Integer caleType) {
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
                Object craftProductInfo = caleType == 1 ? reportProductCount.getCraftProductInfo() : reportProductCount.getProductLevelInfo();
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
                BigDecimal whiteProductQuantityRate = whiteProductQuantity.divide(productQuantity,4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)).setScale(2,RoundingMode.HALF_UP);
                productCountDTO.setWhiteProductQuantityRate(whiteProductQuantityRate);

                // 晚班产量占比
                //BigDecimal blackProductQuantityRate = blackProductQuantity.divide(productQuantity,4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100);
                productCountDTO.setBlackProductQuantityRate(BigDecimal.valueOf(100).subtract(whiteProductQuantityRate).setScale(2,RoundingMode.HALF_UP));

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
            BigDecimal productQuantityRate = productQuantity.divide(deviceProductQuantity,4,RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)).setScale(2,RoundingMode.HALF_UP);
            productReportCraftCountDTO.setProductQuantityRate(productQuantityRate);
        }


        if (productQuantity.compareTo(BigDecimal.ZERO) > 0) {
            // 白班产量占比
            BigDecimal whiteProductQuantityRate = whiteProductQuantity.divide(productQuantity,4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)).setScale(2,RoundingMode.HALF_UP);
            productReportCraftCountDTO.setWhiteProductQuantityRate(whiteProductQuantityRate);

            // 晚班产量占比
            BigDecimal blackProductQuantityRate = blackProductQuantity.divide(productQuantity,4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)).setScale(2,RoundingMode.HALF_UP);
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
            BigDecimal whiteProductQuantityRate = whiteProductQuantity.divide(productQuantity,4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)).setScale(2,RoundingMode.HALF_UP);
            sumProductCountDTO.setWhiteProductQuantityRate(whiteProductQuantityRate);

            // 晚班产量占比
            BigDecimal blackProductQuantityRate = blackProductQuantity.divide(productQuantity,4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)).setScale(2,RoundingMode.HALF_UP);
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

    private void fillBigDecimalCell(BigDecimal value,SXSSFRow row,int cellNum,boolean needStripTrailingZeros) {
        if (Objects.nonNull(value)) {
            if (needStripTrailingZeros) {
                row.createCell(cellNum).setCellValue(value.stripTrailingZeros().toPlainString());
                return;
            }
            row.createCell(cellNum).setCellValue(value.toPlainString() + "%");
            return;
        }
        row.createCell(cellNum).setCellValue("");
    }

    private CellStyle getStyleWithWrapText(SXSSFWorkbook wb) {
        //创建样式对象
        CellStyle style = wb.createCellStyle();
        //设置样式对齐方式：水平\垂直居中
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(true);
        return style;
    }
}
