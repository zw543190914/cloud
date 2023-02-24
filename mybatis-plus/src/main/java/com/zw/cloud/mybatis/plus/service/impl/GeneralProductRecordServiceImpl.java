package com.zw.cloud.mybatis.plus.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.zw.cloud.common.exception.BizException;
import com.zw.cloud.mybatis.plus.entity.GeneralProductRecord;
import com.zw.cloud.mybatis.plus.enums.DeleteEnum;
import com.zw.cloud.mybatis.plus.mapper.GeneralProductRecordMapper;
import com.zw.cloud.mybatis.plus.service.api.IGeneralProductRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * <p>
 * 通用生产记录表 服务实现类
 * </p>
 *
 * @author zw
 * @since 2022-12-15
 */
@Service
@Slf4j
public class GeneralProductRecordServiceImpl extends ServiceImpl<GeneralProductRecordMapper, GeneralProductRecord> implements IGeneralProductRecordService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean start(Long id) {
        LambdaQueryWrapper<GeneralProductRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GeneralProductRecord::getId,id)
                .eq(GeneralProductRecord::getIsDeleted, DeleteEnum.NO.getStatus());
        GeneralProductRecord productRecord = baseMapper.selectOne(queryWrapper);
        log.info("[GeneralProductRecordServiceImpl][start]productRecord is {}", JSON.toJSONString(productRecord));
        if (Objects.isNull(productRecord)) {
            throw new BizException("数据不存在");
        }
        if (Objects.equals(1,productRecord.getIsDeleted())) {
            return false;
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LambdaUpdateWrapper<GeneralProductRecord> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(GeneralProductRecord::getId,productRecord.getId())
                .eq(GeneralProductRecord::getPreStatus,0)
                .eq(GeneralProductRecord::getIsDeleted,DeleteEnum.NO.getStatus())
                // 乐观锁限制
        .set(GeneralProductRecord::getPreStatus,1);
        int update = baseMapper.update(null, updateWrapper);
        return update > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean finish(Long id) {
        LambdaQueryWrapper<GeneralProductRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GeneralProductRecord::getId,id)
                .eq(GeneralProductRecord::getIsDeleted, DeleteEnum.NO.getStatus());
        GeneralProductRecord productRecord = baseMapper.selectOne(queryWrapper);
        log.info("[GeneralProductRecordServiceImpl][finish]productRecord is {}", JSON.toJSONString(productRecord));
        if (Objects.isNull(productRecord)) {
            throw new BizException("数据不存在");
        }
        if (Objects.equals(1,productRecord.getIsDeleted())) {
            return false;
        }
        productRecord.setPreStatus(2);
        int count = baseMapper.updateById(productRecord);
        log.info("[GeneralProductRecordServiceImpl][finish]updateById count is {}", count);
        delete(productRecord.getProductCardCode(),productRecord.getCraftType());
        return true;
    }

    private void delete(String productCardCode,String craftType) {
        LambdaUpdateWrapper<GeneralProductRecord> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(GeneralProductRecord::getProductCardCode,productCardCode)
                .eq(GeneralProductRecord::getCraftType,craftType)
                .eq(GeneralProductRecord::getPreStatus,0)
                .eq(GeneralProductRecord::getIsDeleted,DeleteEnum.NO.getStatus())
        .set(GeneralProductRecord::getIsDeleted,DeleteEnum.YES.getStatus());
        int count = baseMapper.update(null, updateWrapper);
        log.info("[GeneralProductRecordServiceImpl][finish] update count is {}", count);
    }
}
