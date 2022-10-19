package com.zw.cloud.mybatis.plus.service.api;

import com.zw.cloud.mybatis.plus.entity.BaseProductRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 生产记录表 服务类
 * </p>
 *
 * @author zw
 * @since 2022-09-29
 */
public interface IBaseProductRecordService extends IService<BaseProductRecord> {

    void insertBaseProductRecord(BaseProductRecord productRecord);
}
