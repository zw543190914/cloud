package com.zw.cloud.mybatis.plus.db.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 分页DTO
 * <br>CreateDate 2021/3/15
 *
 * @author relam
 */
public class PageInfo<T> implements Serializable {

    public static final PageInfo EMPTY_PAGE_INFO = new PageInfo(25, null);

    private Integer total = 0;
    private Integer pageNo = 1;
    private Integer pageSize;
    private List<T> list;

    public PageInfo() {
    }

    public PageInfo(Integer pageSize, List<T> list) {
        this.pageSize = pageSize;
        this.list = list;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
