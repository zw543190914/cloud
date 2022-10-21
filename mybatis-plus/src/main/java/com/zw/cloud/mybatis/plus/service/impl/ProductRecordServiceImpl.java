package com.zw.cloud.mybatis.plus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zw.cloud.common.exception.BizException;
import com.zw.cloud.mybatis.plus.entity.ProductRecord;

import com.zw.cloud.mybatis.plus.entity.dto.ProductRecordDTO;
import com.zw.cloud.mybatis.plus.entity.dto.ProductRecordReportQueryDTO;
import com.zw.cloud.mybatis.plus.enums.ExceptionTypeEnum;
import com.zw.cloud.mybatis.plus.mapper.ProductRecordMapper;
import com.zw.cloud.mybatis.plus.service.api.IProductRecordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.util.*;

/**
 * <p>
 * 生产记录表 服务实现类
 * </p>
 *
 * @author zw
 * @since 2022-09-29
 */
@Service
@Slf4j
public class ProductRecordServiceImpl extends ServiceImpl<ProductRecordMapper, ProductRecord> implements IProductRecordService {

    @Autowired
    private ProductRecordServiceImpl productRecordService;

    /**
     * 查询已完成记录
     */
    @Override
    public List<ProductRecordDTO> queryAllFinishedProductForReport(ProductRecordReportQueryDTO productRecordReportQueryDTO) {
        if (Objects.isNull(productRecordReportQueryDTO.getStartTime()) && Objects.isNull(productRecordReportQueryDTO.getEndTime())) {
            if (StringUtils.isBlank(productRecordReportQueryDTO.getProductCardCode())) {
                throw new BizException(400, "请选择时间范围或者流转卡号");
            }
        }
        fillSourceAndIsShowExceptionsParam(productRecordReportQueryDTO);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("t1");
        List<ProductRecord> productRecordList = baseMapper.queryAllFinishedProductForReport(productRecordReportQueryDTO);
        if (CollectionUtils.isEmpty(productRecordList)) {
            return Collections.emptyList();
        }
        stopWatch.stop();
        stopWatch.start("t2");
        List<ProductRecordDTO> productRecordDTOList = new ArrayList<>(productRecordList.size());
       /* Class<ProductRecordDTO> productRecordDTOClass = ProductRecordDTO.class;
        Class<ProductRecord> productRecordClass = ProductRecord.class;
        Field[] recordFields = productRecordClass.getDeclaredFields();*/
        for (ProductRecord productRecord : productRecordList) {
            // TODO 注意使用的是手动 get/set 方法
            //  000000200  000%  t2
            /**
             * ---------------------------------------------
             * ns         %     Task name
             * ---------------------------------------------
             * 6016509900  100%  t1
             * 014425800  000%  t2
             *
             * ---------------------------------------------
             * ns         %     Task name
             * ---------------------------------------------
             * 2489787800  100%  t1
             * 011906800  000%  t2
             */
            ProductRecordDTO productRecordDTO = copyProductRecord(productRecord);

            /**
             * ---------------------------------------------
             * ns         %     Task name
             * ---------------------------------------------
             * 14245077200  059%  t1
             * 9825607100  041%  t2
             *
             * ---------------------------------------------
             * ns         %     Task name
             * ---------------------------------------------
             * 2965117300  026%  t1
             * 8424624200  074%  t2
             */
           /* ProductRecordDTO productRecordDTO = new ProductRecordDTO();
            BeanUtils.copyProperties(productRecord,productRecordDTO);*/
            /*ProductRecordDTO productRecordDTO = new ProductRecordDTO();
            for (Field recordField : recordFields) {
                recordField.setAccessible(true);
                try {
                    Field dtoField = productRecordDTOClass.getDeclaredField(recordField.getName());
                    dtoField.setAccessible(true);
                    dtoField.set(productRecordDTO,recordField.get(productRecord));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    log.warn("[ProductRecordLineServiceImpl][queryAllFinishedProductForReport] copy ProductRecord error is ", e);
                }
            }*/

            productRecordDTOList.add(productRecordDTO);
        }
        stopWatch.stop();
        log.info("[ProductRecordServiceImpl][queryAllFinishedProductForReport] stopWatch is {}",stopWatch.prettyPrint());
        return productRecordDTOList;
    }
    /**
     * 分页查询已完成记录
     */
    @Override
    public Map<String, Object> pageQueryAllFinishedProduct(ProductRecordReportQueryDTO productRecordReportQueryDTO) {
        Map<String, Object> resultMap = new HashMap<>();
        if (Objects.isNull(productRecordReportQueryDTO.getStartTime()) && Objects.isNull(productRecordReportQueryDTO.getEndTime())) {
            if (StringUtils.isBlank(productRecordReportQueryDTO.getProductCardCode())) {
                throw new BizException(400, "请选择时间范围或者流转卡号");
            }
        }
        productRecordReportQueryDTO.setStart((productRecordReportQueryDTO.getPageNumber() - 1) * productRecordReportQueryDTO.getPageSize());
        fillSourceAndIsShowExceptionsParam(productRecordReportQueryDTO);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("t1");
        List<ProductRecord> productRecordList = baseMapper.pageQueryAllFinishedProductForReport(productRecordReportQueryDTO);
        stopWatch.stop();
        stopWatch.start("t2");
        int total = baseMapper.countAllFinishedProductForReport(productRecordReportQueryDTO);
        stopWatch.stop();
        stopWatch.start("t3");
        // 只查询了 exception_type,assistant_exception_type,craft_exception_type 3列数据，查询全部字段，耗时增加巨大
        List<ProductRecord> exceptionList = baseMapper.queryProductRecordExceptionList(productRecordReportQueryDTO);
        stopWatch.stop();
        stopWatch.start("t4");
        long operateExceptions = 0L;
        long qualityExceptions = 0L;
        long totalExceptions = 0L;
        long assistantExceptions = 0L;
        long craftExceptions = 0L;

        if (CollectionUtils.isNotEmpty(exceptionList)) {
            operateExceptions = exceptionList.stream().filter(productRecord -> Objects.equals(2, productRecord.getExceptionType())
                    || Objects.equals(3, productRecord.getExceptionType())).count();
            qualityExceptions = exceptionList.stream().filter(productRecord -> Objects.equals(1, productRecord.getExceptionType())
                    || Objects.equals(3, productRecord.getExceptionType())).count();
            totalExceptions = exceptionList.size();
            assistantExceptions = exceptionList.stream().filter(productRecord -> Objects.equals(1, productRecord.getAssistantExceptionType())).count();
            craftExceptions = exceptionList.stream().filter(productRecord -> Objects.equals(1, productRecord.getCraftExceptionType())).count();

        }
        stopWatch.stop();
        resultMap.put("total", total);
        resultMap.put("dataList", productRecordList);
        // 原有接口返回值为 int
        resultMap.put("operateExceptions", (int)operateExceptions);
        resultMap.put("qualityExceptions", (int)qualityExceptions);
        resultMap.put("totalExceptions", (int)totalExceptions);
        resultMap.put("assistantExceptions", (int)assistantExceptions);
        resultMap.put("craftExceptions", (int)craftExceptions);
        log.info("[ProductRecordServiceImpl][pageQueryAllFinishedProduct] stopWatch is {}",stopWatch.prettyPrint());

        return resultMap;
    }
    @Override
    public void insertProductRecord(ProductRecord productRecord) {
        // 注入自身，调用本类方法使事务生效
        productRecordService.insert(productRecord);
    }

    @Override
    public List<ProductRecord> queryList(ProductRecord productRecord) {
        LambdaQueryWrapper<ProductRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(productRecord.getOrgCode()),ProductRecord::getOrgCode,productRecord.getOrgCode())
                .eq(Objects.nonNull(productRecord.getDeviceId()),ProductRecord::getDeviceId,productRecord.getDeviceId())
                .eq(StringUtils.isNotBlank(productRecord.getProductCardCode()),ProductRecord::getProductCardCode,productRecord.getProductCardCode())
                .eq(ProductRecord::getIsDeleted,0)
                .last("limit 5");
        return baseMapper.selectList(queryWrapper);
    }

    private void fillSourceAndIsShowExceptionsParam(ProductRecordReportQueryDTO productRecordReportQueryDTO){

        //处理异常类型
        /**
         * 1 exception_type 有异常
         * 2 assistant_exception_type 有助剂异常
         * 3 craft_exception_type 有工艺异常
         */
        int sqlExceptionType = 0;

        if (CollectionUtils.isNotEmpty(productRecordReportQueryDTO.getExceptionTypeList())) {
            List<Integer> convertList = new ArrayList<>();
            List<Integer> convertAssistantList = new ArrayList<>();
            for (String exceptionType : productRecordReportQueryDTO.getExceptionTypeList()) {
                if ("1".equals(exceptionType.trim())) {
                    convertList.add(ExceptionTypeEnum.QUALITY_EXCEPTION.getCode());
                    convertList.add(ExceptionTypeEnum.OPERATE_QUALITY_EXCEPTION.getCode());
                    sqlExceptionType = 1;
                } else if ("2".equals(exceptionType.trim())) {
                    convertList.add(ExceptionTypeEnum.OPERATE_EXCEPTION.getCode());
                    convertList.add(ExceptionTypeEnum.OPERATE_QUALITY_EXCEPTION.getCode());
                    sqlExceptionType = 1;
                } else if ("4".equals(exceptionType.trim())) {
                    //助剂异常
                    convertAssistantList.add(1);
                    sqlExceptionType = 2;
                } else if ("5".equals(exceptionType.trim())) {
                    sqlExceptionType = 3;
                    productRecordReportQueryDTO.setCraftExceptionType(1);
                }
            }
            productRecordReportQueryDTO.setExceptionConvertList(convertList);
            productRecordReportQueryDTO.setConvertAssistantList(convertAssistantList);
            productRecordReportQueryDTO.setSqlExceptionType(sqlExceptionType);
        }

    }

    private ProductRecordDTO copyProductRecord(ProductRecord productRecord) {
        ProductRecordDTO productRecordDTO = new ProductRecordDTO();
        productRecordDTO.setId(productRecord.getId());
        productRecordDTO.setProductInfoId(productRecord.getProductInfoId());
        productRecordDTO.setOrgCode(productRecord.getOrgCode());
        productRecordDTO.setDeviceId(productRecord.getDeviceId());
        productRecordDTO.setDeviceName(productRecord.getDeviceName());
        productRecordDTO.setProductCardCode(productRecord.getProductCardCode());
        productRecordDTO.setOrderId(productRecord.getOrderId());
        productRecordDTO.setCustomerNo(productRecord.getCustomerNo());
        productRecordDTO.setCustomerName(productRecord.getCustomerName());
        productRecordDTO.setColorNo(productRecord.getColorNo());
        productRecordDTO.setColor(productRecord.getColor());
        productRecordDTO.setFabricNo(productRecord.getFabricNo());
        productRecordDTO.setFabricName(productRecord.getFabricName());
        productRecordDTO.setBatchNo(productRecord.getBatchNo());
        productRecordDTO.setFabricWidth(productRecord.getFabricWidth());
        productRecordDTO.setGramWeight(productRecord.getGramWeight());
        productRecordDTO.setGramHeft(productRecord.getGramHeft());
        productRecordDTO.setWeftDensity(productRecord.getWeftDensity());
        productRecordDTO.setPlanMeters(productRecord.getPlanMeters());
        productRecordDTO.setDefect(productRecord.getDefect());
        productRecordDTO.setDefectsNumber(productRecord.getDefectsNumber());
        productRecordDTO.setSeverity(productRecord.getSeverity());
        productRecordDTO.setResult(productRecord.getResult());
        productRecordDTO.setCarNumber(productRecord.getCarNumber());
        productRecordDTO.setUpperDoorWidth(productRecord.getUpperDoorWidth());
        productRecordDTO.setLowerDoorWidth(productRecord.getLowerDoorWidth());
        productRecordDTO.setScanTime(productRecord.getScanTime());
        productRecordDTO.setPreStatus(productRecord.getPreStatus());
        productRecordDTO.setAfterStatus(productRecord.getAfterStatus());
        productRecordDTO.setHandleTime(productRecord.getHandleTime());
        productRecordDTO.setSaveTime(productRecord.getSaveTime());
        productRecordDTO.setOperator(productRecord.getOperator());
        productRecordDTO.setSort(productRecord.getSort());
        productRecordDTO.setSource(productRecord.getSource());
        productRecordDTO.setCreateTime(productRecord.getCreateTime());
        productRecordDTO.setCreateUser(productRecord.getCreateUser());
        productRecordDTO.setUpdateTime(productRecord.getUpdateTime());
        productRecordDTO.setUpdateUser(productRecord.getUpdateUser());
        productRecordDTO.setUpdateSystem(productRecord.getUpdateSystem());
        productRecordDTO.setIsDeleted(productRecord.getIsDeleted());
        productRecordDTO.setPtQuantity(productRecord.getPtQuantity());
        productRecordDTO.setProductNo(productRecord.getProductNo());
        productRecordDTO.setProductSort(productRecord.getProductSort());
        productRecordDTO.setEmployeeName(productRecord.getEmployeeName());
        productRecordDTO.setMarkupCraft(productRecord.getMarkupCraft());
        productRecordDTO.setTeamGroup(productRecord.getTeamGroup());
        productRecordDTO.setAvgCarSpeed(productRecord.getAvgCarSpeed());
        productRecordDTO.setAvgTemperature(productRecord.getAvgTemperature());
        productRecordDTO.setAvgWindSpeed(productRecord.getAvgWindSpeed());
        productRecordDTO.setAvgTopFeed(productRecord.getAvgTopFeed());
        productRecordDTO.setDeptName(productRecord.getDeptName());
        productRecordDTO.setAssistant(productRecord.getAssistant());
        productRecordDTO.setStartTime(productRecord.getStartTime());
        productRecordDTO.setEndTime(productRecord.getEndTime());
        productRecordDTO.setScheduleDate(productRecord.getScheduleDate());
        productRecordDTO.setTeamGroupId(productRecord.getTeamGroupId());
        productRecordDTO.setTeamGroupName(productRecord.getTeamGroupName());
        productRecordDTO.setTeamShiftId(productRecord.getTeamShiftId());
        productRecordDTO.setTeamShiftName(productRecord.getTeamShiftName());
        productRecordDTO.setWorkshopId(productRecord.getWorkshopId());
        productRecordDTO.setWorkshopName(productRecord.getWorkshopName());
        productRecordDTO.setScheduleStartTime(productRecord.getScheduleStartTime());
        productRecordDTO.setScheduleEndTime(productRecord.getScheduleEndTime());
        productRecordDTO.setCalcDay(productRecord.getCalcDay());
        productRecordDTO.setCraftType(productRecord.getCraftType());
        productRecordDTO.setCraftTypeName(productRecord.getCustomerName());
        productRecordDTO.setPreSort(productRecord.getPreSort());
        productRecordDTO.setPreCarNumber(productRecord.getPreCarNumber());
        productRecordDTO.setSpecification(productRecord.getSpecification());
        productRecordDTO.setStyle(productRecord.getStyle());
        productRecordDTO.setOperatorInfo(productRecord.getOperatorInfo());
        productRecordDTO.setPreOperator(productRecord.getPreOperator());
        productRecordDTO.setFrontOperator(productRecord.getFrontOperator());
        productRecordDTO.setMatches(productRecord.getMatches());
        productRecordDTO.setActualMeters(productRecord.getActualMeters());
        productRecordDTO.setExpectedTime(productRecord.getExpectedTime());
        productRecordDTO.setActualTime(productRecord.getActualTime());
        productRecordDTO.setExceptionType(productRecord.getExceptionType());
        productRecordDTO.setRemark(productRecord.getRemark());
        productRecordDTO.setProductWeightMin(productRecord.getProductWeightMin());
        productRecordDTO.setProductWeightMax(productRecord.getProductWeightMax());
        productRecordDTO.setIsBatch(productRecord.getIsBatch());
        productRecordDTO.setProcessName(productRecord.getProcessName());
        productRecordDTO.setQualityStatus(productRecord.getQualityStatus());
        productRecordDTO.setScheduleImport(productRecord.getScheduleImport());
        productRecordDTO.setProcessCode(productRecord.getProcessCode());
        productRecordDTO.setPlatform(productRecord.getPlatform());
        productRecordDTO.setProductCode(productRecord.getProductCode());
        productRecordDTO.setTaskNo(productRecord.getTaskNo());
        productRecordDTO.setCustomerRequest(productRecord.getCustomerRequest());
        productRecordDTO.setStereotypeRequirement(productRecord.getStereotypeRequirement());
        productRecordDTO.setShrinkage(productRecord.getShrinkage());
        productRecordDTO.setCraftExceptionType(productRecord.getCraftExceptionType());
        productRecordDTO.setProductWeight(productRecord.getProductWeight());
        productRecordDTO.setThickness(productRecord.getThickness());
        productRecordDTO.setIsHaveCraft(productRecord.getIsHaveCraft());
        productRecordDTO.setIsHaveCraftStr(productRecord.getIsHaveCraftStr());
        productRecordDTO.setProcessReportType(productRecord.getProcessReportType());
        return productRecordDTO;
    }

    @Transactional(rollbackFor = Exception.class)
    public void insert(ProductRecord productRecord) {
        baseMapper.insert(productRecord);
        if (Objects.equals(1L,productRecord.getId())) {
            throw new RuntimeException("测试异常");
        }
    }
}
