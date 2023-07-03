package com.zw.cloud.mybatis.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class ProductRecordDetail implements Serializable {
    private Long id;

    private Long productRecordId;

    private String orgCode;

    private Date exceptionUpdateTime;

    private String createUser;

    private String createSystem;

    private LocalDateTime createTime;

    private String updateUser;

    private String updateSystem;

    private LocalDateTime updateTime;

    private String reductionWeight;

    private String reductionAmplitude;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductRecordId() {
        return productRecordId;
    }

    public void setProductRecordId(Long productRecordId) {
        this.productRecordId = productRecordId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public Date getExceptionUpdateTime() {
        return exceptionUpdateTime;
    }

    public void setExceptionUpdateTime(Date exceptionUpdateTime) {
        this.exceptionUpdateTime = exceptionUpdateTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getCreateSystem() {
        return createSystem;
    }

    public void setCreateSystem(String createSystem) {
        this.createSystem = createSystem == null ? null : createSystem.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public String getUpdateSystem() {
        return updateSystem;
    }

    public void setUpdateSystem(String updateSystem) {
        this.updateSystem = updateSystem == null ? null : updateSystem.trim();
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getReductionWeight() {
        return reductionWeight;
    }

    public void setReductionWeight(String reductionWeight) {
        this.reductionWeight = reductionWeight == null ? null : reductionWeight.trim();
    }

    public String getReductionAmplitude() {
        return reductionAmplitude;
    }

    public void setReductionAmplitude(String reductionAmplitude) {
        this.reductionAmplitude = reductionAmplitude == null ? null : reductionAmplitude.trim();
    }
}