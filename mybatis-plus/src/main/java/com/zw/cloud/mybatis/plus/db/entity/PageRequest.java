package com.zw.cloud.mybatis.plus.db.entity;


import java.util.ArrayList;
import java.util.List;

public class PageRequest {
    private int pageNo = 1;
    private int pageSize = 20;
    private List<OrderItem> orderBy;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public PageRequest withPageNo(int pageNo) {
        this.setPageNo(pageNo);
        return this;
    }

    public PageRequest withPageSize(int pageSize) {
        this.setPageSize(pageSize);
        return this;
    }

    public PageRequest withOrder(OrderItem order) {
        if (this.orderBy == null) {
            this.orderBy = new ArrayList();
        }

        if (!this.orderBy.contains(order)) {
            this.orderBy.add(order);
        }

        return this;
    }

    public PageRequest withOrderBy(List<OrderItem> orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    public int getOffset() {
        return (this.getPageNo() - 1) * this.getPageSize();
    }

    public PageRequest() {
    }

    public List<OrderItem> getOrderBy() {
        return this.orderBy;
    }

    public void setOrderBy(final List<OrderItem> orderBy) {
        this.orderBy = orderBy;
    }


}

