package com.zw.cloud.mybatis.plus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zw.cloud.mybatis.plus.entity.BaseProductRecord;
import com.zw.cloud.mybatis.plus.mapper.BaseProductRecordMapper;
import com.zw.cloud.mybatis.plus.service.api.IBaseProductRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 生产记录表 服务实现类
 * </p>
 *
 * @author zw
 * @since 2022-09-29
 */
@Service
public class BaseProductRecordServiceImpl extends ServiceImpl<BaseProductRecordMapper, BaseProductRecord> implements IBaseProductRecordService {

    @Autowired
    private BaseProductRecordServiceImpl productRecordService;

    @Override
    public void insertBaseProductRecord(BaseProductRecord productRecord) {
        // 注入自身，调用本类方法使事务生效
        productRecordService.insert(productRecord);
    }

    @Override
    public List<BaseProductRecord> queryList(BaseProductRecord productRecord) {
        LambdaQueryWrapper<BaseProductRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(productRecord.getOrgCode()),BaseProductRecord::getOrgCode,productRecord.getOrgCode())
                .eq(Objects.nonNull(productRecord.getDeviceId()),BaseProductRecord::getDeviceId,productRecord.getDeviceId())
                .eq(StringUtils.isNotBlank(productRecord.getProductCardCode()),BaseProductRecord::getProductCardCode,productRecord.getProductCardCode())
                .eq(BaseProductRecord::getIsDeleted,0)
                .last("limit 5");
        return baseMapper.selectList(queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    public void insert(BaseProductRecord productRecord) {
        baseMapper.insert(productRecord);
        if (Objects.equals(1L,productRecord.getId())) {
            throw new RuntimeException("测试异常");
        }
    }
}
