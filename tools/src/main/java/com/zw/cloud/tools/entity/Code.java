package com.zw.cloud.tools.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Code)实体类
 *
 * @author makejava
 * @since 2021-08-29 20:37:03
 */
public class Code implements Serializable {
    private static final long serialVersionUID = -89883930145205890L;
    
    private Integer id;
    
    private Byte status;
    
    private Date gmtCreate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

}