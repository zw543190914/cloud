package com.zw.cloud.activiti.entity;

import java.io.Serializable;
import java.util.Date;

public class ActReDeployment implements Serializable {
    private String id;

    private String name;

    private String category;

    private String key;

    private String tenantId;

    private Date deployTime;

    private String engineVersion;

    private Integer version;

    private String projectReleaseVersion;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public Date getDeployTime() {
        return deployTime;
    }

    public void setDeployTime(Date deployTime) {
        this.deployTime = deployTime;
    }

    public String getEngineVersion() {
        return engineVersion;
    }

    public void setEngineVersion(String engineVersion) {
        this.engineVersion = engineVersion == null ? null : engineVersion.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getProjectReleaseVersion() {
        return projectReleaseVersion;
    }

    public void setProjectReleaseVersion(String projectReleaseVersion) {
        this.projectReleaseVersion = projectReleaseVersion == null ? null : projectReleaseVersion.trim();
    }
}