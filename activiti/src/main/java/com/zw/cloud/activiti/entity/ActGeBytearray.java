package com.zw.cloud.activiti.entity;

import java.io.Serializable;

public class ActGeBytearray implements Serializable {
    private String id;

    private Integer rev;

    private String name;

    private String deploymentId;

    private Byte generated;

    private byte[] bytes;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getRev() {
        return rev;
    }

    public void setRev(Integer rev) {
        this.rev = rev;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId == null ? null : deploymentId.trim();
    }

    public Byte getGenerated() {
        return generated;
    }

    public void setGenerated(Byte generated) {
        this.generated = generated;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}