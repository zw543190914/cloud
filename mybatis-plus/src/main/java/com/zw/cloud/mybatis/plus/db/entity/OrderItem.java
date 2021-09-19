package com.zw.cloud.mybatis.plus.db.entity;

import java.io.Serializable;

public class OrderItem implements Serializable {
    public static final String ASC = "ASC";
    public static final String DESC = "DESC";
    private String field;
    private String order;

    public OrderItem withField(String field) {
        this.setField(field);
        return this;
    }

    public OrderItem withOrder(String order) {
        this.setOrder(order);
        return this;
    }

    public OrderItem() {
    }

    public String getField() {
        return this.field;
    }

    public String getOrder() {
        return this.order;
    }

    public void setField(final String field) {
        this.field = field;
    }

    public void setOrder(final String order) {
        this.order = order;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof OrderItem)) {
            return false;
        } else {
            OrderItem other = (OrderItem)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$field = this.getField();
                Object other$field = other.getField();
                if (this$field == null) {
                    if (other$field != null) {
                        return false;
                    }
                } else if (!this$field.equals(other$field)) {
                    return false;
                }

                Object this$order = this.getOrder();
                Object other$order = other.getOrder();
                if (this$order == null) {
                    if (other$order != null) {
                        return false;
                    }
                } else if (!this$order.equals(other$order)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof OrderItem;
    }

    @Override
    public int hashCode() {
        int result = 1;
        Object $field = this.getField();
        result = result * 59 + ($field == null ? 43 : $field.hashCode());
        Object $order = this.getOrder();
        result = result * 59 + ($order == null ? 43 : $order.hashCode());
        return result;
    }

    @Override
    public String toString() {
        String var10000 = this.getField();
        return "OrderItem(field=" + var10000 + ", order=" + this.getOrder() + ")";
    }
}

