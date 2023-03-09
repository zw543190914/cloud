package com.zw.cloud.mybatis.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ProductRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProductRecordExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> createTimeCriteria;

        protected List<Criterion> updateTimeCriteria;

        protected List<Criterion> allCriteria;

        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
            createTimeCriteria = new ArrayList<Criterion>();
            updateTimeCriteria = new ArrayList<Criterion>();
        }

        public List<Criterion> getCreateTimeCriteria() {
            return createTimeCriteria;
        }

        protected void addCreateTimeCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            createTimeCriteria.add(new Criterion(condition, value, "org.apache.ibatis.type.LocalDateTimeTypeHandler"));
            allCriteria = null;
        }

        protected void addCreateTimeCriterion(String condition, LocalDateTime value1, LocalDateTime value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            createTimeCriteria.add(new Criterion(condition, value1, value2, "org.apache.ibatis.type.LocalDateTimeTypeHandler"));
            allCriteria = null;
        }

        public List<Criterion> getUpdateTimeCriteria() {
            return updateTimeCriteria;
        }

        protected void addUpdateTimeCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            updateTimeCriteria.add(new Criterion(condition, value, "org.apache.ibatis.type.LocalDateTimeTypeHandler"));
            allCriteria = null;
        }

        protected void addUpdateTimeCriterion(String condition, LocalDateTime value1, LocalDateTime value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            updateTimeCriteria.add(new Criterion(condition, value1, value2, "org.apache.ibatis.type.LocalDateTimeTypeHandler"));
            allCriteria = null;
        }

        public boolean isValid() {
            return criteria.size() > 0
                || createTimeCriteria.size() > 0
                || updateTimeCriteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            if (allCriteria == null) {
                allCriteria = new ArrayList<Criterion>();
                allCriteria.addAll(criteria);
                allCriteria.addAll(createTimeCriteria);
                allCriteria.addAll(updateTimeCriteria);
            }
            return allCriteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
            allCriteria = null;
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
            allCriteria = null;
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
            allCriteria = null;
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdIsNull() {
            addCriterion("product_info_id is null");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdIsNotNull() {
            addCriterion("product_info_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdEqualTo(Long value) {
            addCriterion("product_info_id =", value, "productInfoId");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdNotEqualTo(Long value) {
            addCriterion("product_info_id <>", value, "productInfoId");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdGreaterThan(Long value) {
            addCriterion("product_info_id >", value, "productInfoId");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdGreaterThanOrEqualTo(Long value) {
            addCriterion("product_info_id >=", value, "productInfoId");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdLessThan(Long value) {
            addCriterion("product_info_id <", value, "productInfoId");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdLessThanOrEqualTo(Long value) {
            addCriterion("product_info_id <=", value, "productInfoId");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdIn(List<Long> values) {
            addCriterion("product_info_id in", values, "productInfoId");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdNotIn(List<Long> values) {
            addCriterion("product_info_id not in", values, "productInfoId");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdBetween(Long value1, Long value2) {
            addCriterion("product_info_id between", value1, value2, "productInfoId");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdNotBetween(Long value1, Long value2) {
            addCriterion("product_info_id not between", value1, value2, "productInfoId");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIsNull() {
            addCriterion("org_code is null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIsNotNull() {
            addCriterion("org_code is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeEqualTo(String value) {
            addCriterion("org_code =", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotEqualTo(String value) {
            addCriterion("org_code <>", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThan(String value) {
            addCriterion("org_code >", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThanOrEqualTo(String value) {
            addCriterion("org_code >=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThan(String value) {
            addCriterion("org_code <", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThanOrEqualTo(String value) {
            addCriterion("org_code <=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLike(String value) {
            addCriterion("org_code like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotLike(String value) {
            addCriterion("org_code not like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIn(List<String> values) {
            addCriterion("org_code in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotIn(List<String> values) {
            addCriterion("org_code not in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeBetween(String value1, String value2) {
            addCriterion("org_code between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotBetween(String value1, String value2) {
            addCriterion("org_code not between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andDeviceIdIsNull() {
            addCriterion("device_id is null");
            return (Criteria) this;
        }

        public Criteria andDeviceIdIsNotNull() {
            addCriterion("device_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceIdEqualTo(Long value) {
            addCriterion("device_id =", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdNotEqualTo(Long value) {
            addCriterion("device_id <>", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdGreaterThan(Long value) {
            addCriterion("device_id >", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("device_id >=", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdLessThan(Long value) {
            addCriterion("device_id <", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdLessThanOrEqualTo(Long value) {
            addCriterion("device_id <=", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdIn(List<Long> values) {
            addCriterion("device_id in", values, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdNotIn(List<Long> values) {
            addCriterion("device_id not in", values, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdBetween(Long value1, Long value2) {
            addCriterion("device_id between", value1, value2, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdNotBetween(Long value1, Long value2) {
            addCriterion("device_id not between", value1, value2, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceNameIsNull() {
            addCriterion("device_name is null");
            return (Criteria) this;
        }

        public Criteria andDeviceNameIsNotNull() {
            addCriterion("device_name is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceNameEqualTo(String value) {
            addCriterion("device_name =", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotEqualTo(String value) {
            addCriterion("device_name <>", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameGreaterThan(String value) {
            addCriterion("device_name >", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameGreaterThanOrEqualTo(String value) {
            addCriterion("device_name >=", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameLessThan(String value) {
            addCriterion("device_name <", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameLessThanOrEqualTo(String value) {
            addCriterion("device_name <=", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameLike(String value) {
            addCriterion("device_name like", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotLike(String value) {
            addCriterion("device_name not like", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameIn(List<String> values) {
            addCriterion("device_name in", values, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotIn(List<String> values) {
            addCriterion("device_name not in", values, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameBetween(String value1, String value2) {
            addCriterion("device_name between", value1, value2, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotBetween(String value1, String value2) {
            addCriterion("device_name not between", value1, value2, "deviceName");
            return (Criteria) this;
        }

        public Criteria andProductCardCodeIsNull() {
            addCriterion("product_card_code is null");
            return (Criteria) this;
        }

        public Criteria andProductCardCodeIsNotNull() {
            addCriterion("product_card_code is not null");
            return (Criteria) this;
        }

        public Criteria andProductCardCodeEqualTo(String value) {
            addCriterion("product_card_code =", value, "productCardCode");
            return (Criteria) this;
        }

        public Criteria andProductCardCodeNotEqualTo(String value) {
            addCriterion("product_card_code <>", value, "productCardCode");
            return (Criteria) this;
        }

        public Criteria andProductCardCodeGreaterThan(String value) {
            addCriterion("product_card_code >", value, "productCardCode");
            return (Criteria) this;
        }

        public Criteria andProductCardCodeGreaterThanOrEqualTo(String value) {
            addCriterion("product_card_code >=", value, "productCardCode");
            return (Criteria) this;
        }

        public Criteria andProductCardCodeLessThan(String value) {
            addCriterion("product_card_code <", value, "productCardCode");
            return (Criteria) this;
        }

        public Criteria andProductCardCodeLessThanOrEqualTo(String value) {
            addCriterion("product_card_code <=", value, "productCardCode");
            return (Criteria) this;
        }

        public Criteria andProductCardCodeLike(String value) {
            addCriterion("product_card_code like", value, "productCardCode");
            return (Criteria) this;
        }

        public Criteria andProductCardCodeNotLike(String value) {
            addCriterion("product_card_code not like", value, "productCardCode");
            return (Criteria) this;
        }

        public Criteria andProductCardCodeIn(List<String> values) {
            addCriterion("product_card_code in", values, "productCardCode");
            return (Criteria) this;
        }

        public Criteria andProductCardCodeNotIn(List<String> values) {
            addCriterion("product_card_code not in", values, "productCardCode");
            return (Criteria) this;
        }

        public Criteria andProductCardCodeBetween(String value1, String value2) {
            addCriterion("product_card_code between", value1, value2, "productCardCode");
            return (Criteria) this;
        }

        public Criteria andProductCardCodeNotBetween(String value1, String value2) {
            addCriterion("product_card_code not between", value1, value2, "productCardCode");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(String value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(String value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(String value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(String value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(String value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLike(String value) {
            addCriterion("order_id like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotLike(String value) {
            addCriterion("order_id not like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<String> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<String> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(String value1, String value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(String value1, String value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andCustomerNoIsNull() {
            addCriterion("customer_no is null");
            return (Criteria) this;
        }

        public Criteria andCustomerNoIsNotNull() {
            addCriterion("customer_no is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerNoEqualTo(String value) {
            addCriterion("customer_no =", value, "customerNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNoNotEqualTo(String value) {
            addCriterion("customer_no <>", value, "customerNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNoGreaterThan(String value) {
            addCriterion("customer_no >", value, "customerNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNoGreaterThanOrEqualTo(String value) {
            addCriterion("customer_no >=", value, "customerNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNoLessThan(String value) {
            addCriterion("customer_no <", value, "customerNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNoLessThanOrEqualTo(String value) {
            addCriterion("customer_no <=", value, "customerNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNoLike(String value) {
            addCriterion("customer_no like", value, "customerNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNoNotLike(String value) {
            addCriterion("customer_no not like", value, "customerNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNoIn(List<String> values) {
            addCriterion("customer_no in", values, "customerNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNoNotIn(List<String> values) {
            addCriterion("customer_no not in", values, "customerNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNoBetween(String value1, String value2) {
            addCriterion("customer_no between", value1, value2, "customerNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNoNotBetween(String value1, String value2) {
            addCriterion("customer_no not between", value1, value2, "customerNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNull() {
            addCriterion("customer_name is null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNotNull() {
            addCriterion("customer_name is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameEqualTo(String value) {
            addCriterion("customer_name =", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotEqualTo(String value) {
            addCriterion("customer_name <>", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThan(String value) {
            addCriterion("customer_name >", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThanOrEqualTo(String value) {
            addCriterion("customer_name >=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThan(String value) {
            addCriterion("customer_name <", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThanOrEqualTo(String value) {
            addCriterion("customer_name <=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLike(String value) {
            addCriterion("customer_name like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotLike(String value) {
            addCriterion("customer_name not like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIn(List<String> values) {
            addCriterion("customer_name in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotIn(List<String> values) {
            addCriterion("customer_name not in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameBetween(String value1, String value2) {
            addCriterion("customer_name between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotBetween(String value1, String value2) {
            addCriterion("customer_name not between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andColorNoIsNull() {
            addCriterion("color_no is null");
            return (Criteria) this;
        }

        public Criteria andColorNoIsNotNull() {
            addCriterion("color_no is not null");
            return (Criteria) this;
        }

        public Criteria andColorNoEqualTo(String value) {
            addCriterion("color_no =", value, "colorNo");
            return (Criteria) this;
        }

        public Criteria andColorNoNotEqualTo(String value) {
            addCriterion("color_no <>", value, "colorNo");
            return (Criteria) this;
        }

        public Criteria andColorNoGreaterThan(String value) {
            addCriterion("color_no >", value, "colorNo");
            return (Criteria) this;
        }

        public Criteria andColorNoGreaterThanOrEqualTo(String value) {
            addCriterion("color_no >=", value, "colorNo");
            return (Criteria) this;
        }

        public Criteria andColorNoLessThan(String value) {
            addCriterion("color_no <", value, "colorNo");
            return (Criteria) this;
        }

        public Criteria andColorNoLessThanOrEqualTo(String value) {
            addCriterion("color_no <=", value, "colorNo");
            return (Criteria) this;
        }

        public Criteria andColorNoLike(String value) {
            addCriterion("color_no like", value, "colorNo");
            return (Criteria) this;
        }

        public Criteria andColorNoNotLike(String value) {
            addCriterion("color_no not like", value, "colorNo");
            return (Criteria) this;
        }

        public Criteria andColorNoIn(List<String> values) {
            addCriterion("color_no in", values, "colorNo");
            return (Criteria) this;
        }

        public Criteria andColorNoNotIn(List<String> values) {
            addCriterion("color_no not in", values, "colorNo");
            return (Criteria) this;
        }

        public Criteria andColorNoBetween(String value1, String value2) {
            addCriterion("color_no between", value1, value2, "colorNo");
            return (Criteria) this;
        }

        public Criteria andColorNoNotBetween(String value1, String value2) {
            addCriterion("color_no not between", value1, value2, "colorNo");
            return (Criteria) this;
        }

        public Criteria andColorIsNull() {
            addCriterion("color is null");
            return (Criteria) this;
        }

        public Criteria andColorIsNotNull() {
            addCriterion("color is not null");
            return (Criteria) this;
        }

        public Criteria andColorEqualTo(String value) {
            addCriterion("color =", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotEqualTo(String value) {
            addCriterion("color <>", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorGreaterThan(String value) {
            addCriterion("color >", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorGreaterThanOrEqualTo(String value) {
            addCriterion("color >=", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLessThan(String value) {
            addCriterion("color <", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLessThanOrEqualTo(String value) {
            addCriterion("color <=", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLike(String value) {
            addCriterion("color like", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotLike(String value) {
            addCriterion("color not like", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorIn(List<String> values) {
            addCriterion("color in", values, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotIn(List<String> values) {
            addCriterion("color not in", values, "color");
            return (Criteria) this;
        }

        public Criteria andColorBetween(String value1, String value2) {
            addCriterion("color between", value1, value2, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotBetween(String value1, String value2) {
            addCriterion("color not between", value1, value2, "color");
            return (Criteria) this;
        }

        public Criteria andFabricNoIsNull() {
            addCriterion("fabric_no is null");
            return (Criteria) this;
        }

        public Criteria andFabricNoIsNotNull() {
            addCriterion("fabric_no is not null");
            return (Criteria) this;
        }

        public Criteria andFabricNoEqualTo(String value) {
            addCriterion("fabric_no =", value, "fabricNo");
            return (Criteria) this;
        }

        public Criteria andFabricNoNotEqualTo(String value) {
            addCriterion("fabric_no <>", value, "fabricNo");
            return (Criteria) this;
        }

        public Criteria andFabricNoGreaterThan(String value) {
            addCriterion("fabric_no >", value, "fabricNo");
            return (Criteria) this;
        }

        public Criteria andFabricNoGreaterThanOrEqualTo(String value) {
            addCriterion("fabric_no >=", value, "fabricNo");
            return (Criteria) this;
        }

        public Criteria andFabricNoLessThan(String value) {
            addCriterion("fabric_no <", value, "fabricNo");
            return (Criteria) this;
        }

        public Criteria andFabricNoLessThanOrEqualTo(String value) {
            addCriterion("fabric_no <=", value, "fabricNo");
            return (Criteria) this;
        }

        public Criteria andFabricNoLike(String value) {
            addCriterion("fabric_no like", value, "fabricNo");
            return (Criteria) this;
        }

        public Criteria andFabricNoNotLike(String value) {
            addCriterion("fabric_no not like", value, "fabricNo");
            return (Criteria) this;
        }

        public Criteria andFabricNoIn(List<String> values) {
            addCriterion("fabric_no in", values, "fabricNo");
            return (Criteria) this;
        }

        public Criteria andFabricNoNotIn(List<String> values) {
            addCriterion("fabric_no not in", values, "fabricNo");
            return (Criteria) this;
        }

        public Criteria andFabricNoBetween(String value1, String value2) {
            addCriterion("fabric_no between", value1, value2, "fabricNo");
            return (Criteria) this;
        }

        public Criteria andFabricNoNotBetween(String value1, String value2) {
            addCriterion("fabric_no not between", value1, value2, "fabricNo");
            return (Criteria) this;
        }

        public Criteria andFabricNameIsNull() {
            addCriterion("fabric_name is null");
            return (Criteria) this;
        }

        public Criteria andFabricNameIsNotNull() {
            addCriterion("fabric_name is not null");
            return (Criteria) this;
        }

        public Criteria andFabricNameEqualTo(String value) {
            addCriterion("fabric_name =", value, "fabricName");
            return (Criteria) this;
        }

        public Criteria andFabricNameNotEqualTo(String value) {
            addCriterion("fabric_name <>", value, "fabricName");
            return (Criteria) this;
        }

        public Criteria andFabricNameGreaterThan(String value) {
            addCriterion("fabric_name >", value, "fabricName");
            return (Criteria) this;
        }

        public Criteria andFabricNameGreaterThanOrEqualTo(String value) {
            addCriterion("fabric_name >=", value, "fabricName");
            return (Criteria) this;
        }

        public Criteria andFabricNameLessThan(String value) {
            addCriterion("fabric_name <", value, "fabricName");
            return (Criteria) this;
        }

        public Criteria andFabricNameLessThanOrEqualTo(String value) {
            addCriterion("fabric_name <=", value, "fabricName");
            return (Criteria) this;
        }

        public Criteria andFabricNameLike(String value) {
            addCriterion("fabric_name like", value, "fabricName");
            return (Criteria) this;
        }

        public Criteria andFabricNameNotLike(String value) {
            addCriterion("fabric_name not like", value, "fabricName");
            return (Criteria) this;
        }

        public Criteria andFabricNameIn(List<String> values) {
            addCriterion("fabric_name in", values, "fabricName");
            return (Criteria) this;
        }

        public Criteria andFabricNameNotIn(List<String> values) {
            addCriterion("fabric_name not in", values, "fabricName");
            return (Criteria) this;
        }

        public Criteria andFabricNameBetween(String value1, String value2) {
            addCriterion("fabric_name between", value1, value2, "fabricName");
            return (Criteria) this;
        }

        public Criteria andFabricNameNotBetween(String value1, String value2) {
            addCriterion("fabric_name not between", value1, value2, "fabricName");
            return (Criteria) this;
        }

        public Criteria andBatchNoIsNull() {
            addCriterion("batch_no is null");
            return (Criteria) this;
        }

        public Criteria andBatchNoIsNotNull() {
            addCriterion("batch_no is not null");
            return (Criteria) this;
        }

        public Criteria andBatchNoEqualTo(String value) {
            addCriterion("batch_no =", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotEqualTo(String value) {
            addCriterion("batch_no <>", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoGreaterThan(String value) {
            addCriterion("batch_no >", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoGreaterThanOrEqualTo(String value) {
            addCriterion("batch_no >=", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLessThan(String value) {
            addCriterion("batch_no <", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLessThanOrEqualTo(String value) {
            addCriterion("batch_no <=", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLike(String value) {
            addCriterion("batch_no like", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotLike(String value) {
            addCriterion("batch_no not like", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoIn(List<String> values) {
            addCriterion("batch_no in", values, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotIn(List<String> values) {
            addCriterion("batch_no not in", values, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoBetween(String value1, String value2) {
            addCriterion("batch_no between", value1, value2, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotBetween(String value1, String value2) {
            addCriterion("batch_no not between", value1, value2, "batchNo");
            return (Criteria) this;
        }

        public Criteria andFabricWidthIsNull() {
            addCriterion("fabric_width is null");
            return (Criteria) this;
        }

        public Criteria andFabricWidthIsNotNull() {
            addCriterion("fabric_width is not null");
            return (Criteria) this;
        }

        public Criteria andFabricWidthEqualTo(String value) {
            addCriterion("fabric_width =", value, "fabricWidth");
            return (Criteria) this;
        }

        public Criteria andFabricWidthNotEqualTo(String value) {
            addCriterion("fabric_width <>", value, "fabricWidth");
            return (Criteria) this;
        }

        public Criteria andFabricWidthGreaterThan(String value) {
            addCriterion("fabric_width >", value, "fabricWidth");
            return (Criteria) this;
        }

        public Criteria andFabricWidthGreaterThanOrEqualTo(String value) {
            addCriterion("fabric_width >=", value, "fabricWidth");
            return (Criteria) this;
        }

        public Criteria andFabricWidthLessThan(String value) {
            addCriterion("fabric_width <", value, "fabricWidth");
            return (Criteria) this;
        }

        public Criteria andFabricWidthLessThanOrEqualTo(String value) {
            addCriterion("fabric_width <=", value, "fabricWidth");
            return (Criteria) this;
        }

        public Criteria andFabricWidthLike(String value) {
            addCriterion("fabric_width like", value, "fabricWidth");
            return (Criteria) this;
        }

        public Criteria andFabricWidthNotLike(String value) {
            addCriterion("fabric_width not like", value, "fabricWidth");
            return (Criteria) this;
        }

        public Criteria andFabricWidthIn(List<String> values) {
            addCriterion("fabric_width in", values, "fabricWidth");
            return (Criteria) this;
        }

        public Criteria andFabricWidthNotIn(List<String> values) {
            addCriterion("fabric_width not in", values, "fabricWidth");
            return (Criteria) this;
        }

        public Criteria andFabricWidthBetween(String value1, String value2) {
            addCriterion("fabric_width between", value1, value2, "fabricWidth");
            return (Criteria) this;
        }

        public Criteria andFabricWidthNotBetween(String value1, String value2) {
            addCriterion("fabric_width not between", value1, value2, "fabricWidth");
            return (Criteria) this;
        }

        public Criteria andGramWeightIsNull() {
            addCriterion("gram_weight is null");
            return (Criteria) this;
        }

        public Criteria andGramWeightIsNotNull() {
            addCriterion("gram_weight is not null");
            return (Criteria) this;
        }

        public Criteria andGramWeightEqualTo(Long value) {
            addCriterion("gram_weight =", value, "gramWeight");
            return (Criteria) this;
        }

        public Criteria andGramWeightNotEqualTo(Long value) {
            addCriterion("gram_weight <>", value, "gramWeight");
            return (Criteria) this;
        }

        public Criteria andGramWeightGreaterThan(Long value) {
            addCriterion("gram_weight >", value, "gramWeight");
            return (Criteria) this;
        }

        public Criteria andGramWeightGreaterThanOrEqualTo(Long value) {
            addCriterion("gram_weight >=", value, "gramWeight");
            return (Criteria) this;
        }

        public Criteria andGramWeightLessThan(Long value) {
            addCriterion("gram_weight <", value, "gramWeight");
            return (Criteria) this;
        }

        public Criteria andGramWeightLessThanOrEqualTo(Long value) {
            addCriterion("gram_weight <=", value, "gramWeight");
            return (Criteria) this;
        }

        public Criteria andGramWeightIn(List<Long> values) {
            addCriterion("gram_weight in", values, "gramWeight");
            return (Criteria) this;
        }

        public Criteria andGramWeightNotIn(List<Long> values) {
            addCriterion("gram_weight not in", values, "gramWeight");
            return (Criteria) this;
        }

        public Criteria andGramWeightBetween(Long value1, Long value2) {
            addCriterion("gram_weight between", value1, value2, "gramWeight");
            return (Criteria) this;
        }

        public Criteria andGramWeightNotBetween(Long value1, Long value2) {
            addCriterion("gram_weight not between", value1, value2, "gramWeight");
            return (Criteria) this;
        }

        public Criteria andGramHeftIsNull() {
            addCriterion("gram_heft is null");
            return (Criteria) this;
        }

        public Criteria andGramHeftIsNotNull() {
            addCriterion("gram_heft is not null");
            return (Criteria) this;
        }

        public Criteria andGramHeftEqualTo(Integer value) {
            addCriterion("gram_heft =", value, "gramHeft");
            return (Criteria) this;
        }

        public Criteria andGramHeftNotEqualTo(Integer value) {
            addCriterion("gram_heft <>", value, "gramHeft");
            return (Criteria) this;
        }

        public Criteria andGramHeftGreaterThan(Integer value) {
            addCriterion("gram_heft >", value, "gramHeft");
            return (Criteria) this;
        }

        public Criteria andGramHeftGreaterThanOrEqualTo(Integer value) {
            addCriterion("gram_heft >=", value, "gramHeft");
            return (Criteria) this;
        }

        public Criteria andGramHeftLessThan(Integer value) {
            addCriterion("gram_heft <", value, "gramHeft");
            return (Criteria) this;
        }

        public Criteria andGramHeftLessThanOrEqualTo(Integer value) {
            addCriterion("gram_heft <=", value, "gramHeft");
            return (Criteria) this;
        }

        public Criteria andGramHeftIn(List<Integer> values) {
            addCriterion("gram_heft in", values, "gramHeft");
            return (Criteria) this;
        }

        public Criteria andGramHeftNotIn(List<Integer> values) {
            addCriterion("gram_heft not in", values, "gramHeft");
            return (Criteria) this;
        }

        public Criteria andGramHeftBetween(Integer value1, Integer value2) {
            addCriterion("gram_heft between", value1, value2, "gramHeft");
            return (Criteria) this;
        }

        public Criteria andGramHeftNotBetween(Integer value1, Integer value2) {
            addCriterion("gram_heft not between", value1, value2, "gramHeft");
            return (Criteria) this;
        }

        public Criteria andWeftDensityIsNull() {
            addCriterion("weft_density is null");
            return (Criteria) this;
        }

        public Criteria andWeftDensityIsNotNull() {
            addCriterion("weft_density is not null");
            return (Criteria) this;
        }

        public Criteria andWeftDensityEqualTo(String value) {
            addCriterion("weft_density =", value, "weftDensity");
            return (Criteria) this;
        }

        public Criteria andWeftDensityNotEqualTo(String value) {
            addCriterion("weft_density <>", value, "weftDensity");
            return (Criteria) this;
        }

        public Criteria andWeftDensityGreaterThan(String value) {
            addCriterion("weft_density >", value, "weftDensity");
            return (Criteria) this;
        }

        public Criteria andWeftDensityGreaterThanOrEqualTo(String value) {
            addCriterion("weft_density >=", value, "weftDensity");
            return (Criteria) this;
        }

        public Criteria andWeftDensityLessThan(String value) {
            addCriterion("weft_density <", value, "weftDensity");
            return (Criteria) this;
        }

        public Criteria andWeftDensityLessThanOrEqualTo(String value) {
            addCriterion("weft_density <=", value, "weftDensity");
            return (Criteria) this;
        }

        public Criteria andWeftDensityLike(String value) {
            addCriterion("weft_density like", value, "weftDensity");
            return (Criteria) this;
        }

        public Criteria andWeftDensityNotLike(String value) {
            addCriterion("weft_density not like", value, "weftDensity");
            return (Criteria) this;
        }

        public Criteria andWeftDensityIn(List<String> values) {
            addCriterion("weft_density in", values, "weftDensity");
            return (Criteria) this;
        }

        public Criteria andWeftDensityNotIn(List<String> values) {
            addCriterion("weft_density not in", values, "weftDensity");
            return (Criteria) this;
        }

        public Criteria andWeftDensityBetween(String value1, String value2) {
            addCriterion("weft_density between", value1, value2, "weftDensity");
            return (Criteria) this;
        }

        public Criteria andWeftDensityNotBetween(String value1, String value2) {
            addCriterion("weft_density not between", value1, value2, "weftDensity");
            return (Criteria) this;
        }

        public Criteria andPlanMetersIsNull() {
            addCriterion("plan_meters is null");
            return (Criteria) this;
        }

        public Criteria andPlanMetersIsNotNull() {
            addCriterion("plan_meters is not null");
            return (Criteria) this;
        }

        public Criteria andPlanMetersEqualTo(BigDecimal value) {
            addCriterion("plan_meters =", value, "planMeters");
            return (Criteria) this;
        }

        public Criteria andPlanMetersNotEqualTo(BigDecimal value) {
            addCriterion("plan_meters <>", value, "planMeters");
            return (Criteria) this;
        }

        public Criteria andPlanMetersGreaterThan(BigDecimal value) {
            addCriterion("plan_meters >", value, "planMeters");
            return (Criteria) this;
        }

        public Criteria andPlanMetersGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("plan_meters >=", value, "planMeters");
            return (Criteria) this;
        }

        public Criteria andPlanMetersLessThan(BigDecimal value) {
            addCriterion("plan_meters <", value, "planMeters");
            return (Criteria) this;
        }

        public Criteria andPlanMetersLessThanOrEqualTo(BigDecimal value) {
            addCriterion("plan_meters <=", value, "planMeters");
            return (Criteria) this;
        }

        public Criteria andPlanMetersIn(List<BigDecimal> values) {
            addCriterion("plan_meters in", values, "planMeters");
            return (Criteria) this;
        }

        public Criteria andPlanMetersNotIn(List<BigDecimal> values) {
            addCriterion("plan_meters not in", values, "planMeters");
            return (Criteria) this;
        }

        public Criteria andPlanMetersBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("plan_meters between", value1, value2, "planMeters");
            return (Criteria) this;
        }

        public Criteria andPlanMetersNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("plan_meters not between", value1, value2, "planMeters");
            return (Criteria) this;
        }

        public Criteria andDefectsNumberIsNull() {
            addCriterion("defects_number is null");
            return (Criteria) this;
        }

        public Criteria andDefectsNumberIsNotNull() {
            addCriterion("defects_number is not null");
            return (Criteria) this;
        }

        public Criteria andDefectsNumberEqualTo(Integer value) {
            addCriterion("defects_number =", value, "defectsNumber");
            return (Criteria) this;
        }

        public Criteria andDefectsNumberNotEqualTo(Integer value) {
            addCriterion("defects_number <>", value, "defectsNumber");
            return (Criteria) this;
        }

        public Criteria andDefectsNumberGreaterThan(Integer value) {
            addCriterion("defects_number >", value, "defectsNumber");
            return (Criteria) this;
        }

        public Criteria andDefectsNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("defects_number >=", value, "defectsNumber");
            return (Criteria) this;
        }

        public Criteria andDefectsNumberLessThan(Integer value) {
            addCriterion("defects_number <", value, "defectsNumber");
            return (Criteria) this;
        }

        public Criteria andDefectsNumberLessThanOrEqualTo(Integer value) {
            addCriterion("defects_number <=", value, "defectsNumber");
            return (Criteria) this;
        }

        public Criteria andDefectsNumberIn(List<Integer> values) {
            addCriterion("defects_number in", values, "defectsNumber");
            return (Criteria) this;
        }

        public Criteria andDefectsNumberNotIn(List<Integer> values) {
            addCriterion("defects_number not in", values, "defectsNumber");
            return (Criteria) this;
        }

        public Criteria andDefectsNumberBetween(Integer value1, Integer value2) {
            addCriterion("defects_number between", value1, value2, "defectsNumber");
            return (Criteria) this;
        }

        public Criteria andDefectsNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("defects_number not between", value1, value2, "defectsNumber");
            return (Criteria) this;
        }

        public Criteria andSeverityIsNull() {
            addCriterion("severity is null");
            return (Criteria) this;
        }

        public Criteria andSeverityIsNotNull() {
            addCriterion("severity is not null");
            return (Criteria) this;
        }

        public Criteria andSeverityEqualTo(String value) {
            addCriterion("severity =", value, "severity");
            return (Criteria) this;
        }

        public Criteria andSeverityNotEqualTo(String value) {
            addCriterion("severity <>", value, "severity");
            return (Criteria) this;
        }

        public Criteria andSeverityGreaterThan(String value) {
            addCriterion("severity >", value, "severity");
            return (Criteria) this;
        }

        public Criteria andSeverityGreaterThanOrEqualTo(String value) {
            addCriterion("severity >=", value, "severity");
            return (Criteria) this;
        }

        public Criteria andSeverityLessThan(String value) {
            addCriterion("severity <", value, "severity");
            return (Criteria) this;
        }

        public Criteria andSeverityLessThanOrEqualTo(String value) {
            addCriterion("severity <=", value, "severity");
            return (Criteria) this;
        }

        public Criteria andSeverityLike(String value) {
            addCriterion("severity like", value, "severity");
            return (Criteria) this;
        }

        public Criteria andSeverityNotLike(String value) {
            addCriterion("severity not like", value, "severity");
            return (Criteria) this;
        }

        public Criteria andSeverityIn(List<String> values) {
            addCriterion("severity in", values, "severity");
            return (Criteria) this;
        }

        public Criteria andSeverityNotIn(List<String> values) {
            addCriterion("severity not in", values, "severity");
            return (Criteria) this;
        }

        public Criteria andSeverityBetween(String value1, String value2) {
            addCriterion("severity between", value1, value2, "severity");
            return (Criteria) this;
        }

        public Criteria andSeverityNotBetween(String value1, String value2) {
            addCriterion("severity not between", value1, value2, "severity");
            return (Criteria) this;
        }

        public Criteria andResultIsNull() {
            addCriterion("result is null");
            return (Criteria) this;
        }

        public Criteria andResultIsNotNull() {
            addCriterion("result is not null");
            return (Criteria) this;
        }

        public Criteria andResultEqualTo(String value) {
            addCriterion("result =", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotEqualTo(String value) {
            addCriterion("result <>", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThan(String value) {
            addCriterion("result >", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThanOrEqualTo(String value) {
            addCriterion("result >=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThan(String value) {
            addCriterion("result <", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThanOrEqualTo(String value) {
            addCriterion("result <=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLike(String value) {
            addCriterion("result like", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotLike(String value) {
            addCriterion("result not like", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultIn(List<String> values) {
            addCriterion("result in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotIn(List<String> values) {
            addCriterion("result not in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultBetween(String value1, String value2) {
            addCriterion("result between", value1, value2, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotBetween(String value1, String value2) {
            addCriterion("result not between", value1, value2, "result");
            return (Criteria) this;
        }

        public Criteria andCarNumberIsNull() {
            addCriterion("car_number is null");
            return (Criteria) this;
        }

        public Criteria andCarNumberIsNotNull() {
            addCriterion("car_number is not null");
            return (Criteria) this;
        }

        public Criteria andCarNumberEqualTo(String value) {
            addCriterion("car_number =", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberNotEqualTo(String value) {
            addCriterion("car_number <>", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberGreaterThan(String value) {
            addCriterion("car_number >", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberGreaterThanOrEqualTo(String value) {
            addCriterion("car_number >=", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberLessThan(String value) {
            addCriterion("car_number <", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberLessThanOrEqualTo(String value) {
            addCriterion("car_number <=", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberLike(String value) {
            addCriterion("car_number like", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberNotLike(String value) {
            addCriterion("car_number not like", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberIn(List<String> values) {
            addCriterion("car_number in", values, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberNotIn(List<String> values) {
            addCriterion("car_number not in", values, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberBetween(String value1, String value2) {
            addCriterion("car_number between", value1, value2, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberNotBetween(String value1, String value2) {
            addCriterion("car_number not between", value1, value2, "carNumber");
            return (Criteria) this;
        }

        public Criteria andUpperDoorWidthIsNull() {
            addCriterion("upper_door_width is null");
            return (Criteria) this;
        }

        public Criteria andUpperDoorWidthIsNotNull() {
            addCriterion("upper_door_width is not null");
            return (Criteria) this;
        }

        public Criteria andUpperDoorWidthEqualTo(Double value) {
            addCriterion("upper_door_width =", value, "upperDoorWidth");
            return (Criteria) this;
        }

        public Criteria andUpperDoorWidthNotEqualTo(Double value) {
            addCriterion("upper_door_width <>", value, "upperDoorWidth");
            return (Criteria) this;
        }

        public Criteria andUpperDoorWidthGreaterThan(Double value) {
            addCriterion("upper_door_width >", value, "upperDoorWidth");
            return (Criteria) this;
        }

        public Criteria andUpperDoorWidthGreaterThanOrEqualTo(Double value) {
            addCriterion("upper_door_width >=", value, "upperDoorWidth");
            return (Criteria) this;
        }

        public Criteria andUpperDoorWidthLessThan(Double value) {
            addCriterion("upper_door_width <", value, "upperDoorWidth");
            return (Criteria) this;
        }

        public Criteria andUpperDoorWidthLessThanOrEqualTo(Double value) {
            addCriterion("upper_door_width <=", value, "upperDoorWidth");
            return (Criteria) this;
        }

        public Criteria andUpperDoorWidthIn(List<Double> values) {
            addCriterion("upper_door_width in", values, "upperDoorWidth");
            return (Criteria) this;
        }

        public Criteria andUpperDoorWidthNotIn(List<Double> values) {
            addCriterion("upper_door_width not in", values, "upperDoorWidth");
            return (Criteria) this;
        }

        public Criteria andUpperDoorWidthBetween(Double value1, Double value2) {
            addCriterion("upper_door_width between", value1, value2, "upperDoorWidth");
            return (Criteria) this;
        }

        public Criteria andUpperDoorWidthNotBetween(Double value1, Double value2) {
            addCriterion("upper_door_width not between", value1, value2, "upperDoorWidth");
            return (Criteria) this;
        }

        public Criteria andLowerDoorWidthIsNull() {
            addCriterion("lower_door_width is null");
            return (Criteria) this;
        }

        public Criteria andLowerDoorWidthIsNotNull() {
            addCriterion("lower_door_width is not null");
            return (Criteria) this;
        }

        public Criteria andLowerDoorWidthEqualTo(Double value) {
            addCriterion("lower_door_width =", value, "lowerDoorWidth");
            return (Criteria) this;
        }

        public Criteria andLowerDoorWidthNotEqualTo(Double value) {
            addCriterion("lower_door_width <>", value, "lowerDoorWidth");
            return (Criteria) this;
        }

        public Criteria andLowerDoorWidthGreaterThan(Double value) {
            addCriterion("lower_door_width >", value, "lowerDoorWidth");
            return (Criteria) this;
        }

        public Criteria andLowerDoorWidthGreaterThanOrEqualTo(Double value) {
            addCriterion("lower_door_width >=", value, "lowerDoorWidth");
            return (Criteria) this;
        }

        public Criteria andLowerDoorWidthLessThan(Double value) {
            addCriterion("lower_door_width <", value, "lowerDoorWidth");
            return (Criteria) this;
        }

        public Criteria andLowerDoorWidthLessThanOrEqualTo(Double value) {
            addCriterion("lower_door_width <=", value, "lowerDoorWidth");
            return (Criteria) this;
        }

        public Criteria andLowerDoorWidthIn(List<Double> values) {
            addCriterion("lower_door_width in", values, "lowerDoorWidth");
            return (Criteria) this;
        }

        public Criteria andLowerDoorWidthNotIn(List<Double> values) {
            addCriterion("lower_door_width not in", values, "lowerDoorWidth");
            return (Criteria) this;
        }

        public Criteria andLowerDoorWidthBetween(Double value1, Double value2) {
            addCriterion("lower_door_width between", value1, value2, "lowerDoorWidth");
            return (Criteria) this;
        }

        public Criteria andLowerDoorWidthNotBetween(Double value1, Double value2) {
            addCriterion("lower_door_width not between", value1, value2, "lowerDoorWidth");
            return (Criteria) this;
        }

        public Criteria andScanTimeIsNull() {
            addCriterion("scan_time is null");
            return (Criteria) this;
        }

        public Criteria andScanTimeIsNotNull() {
            addCriterion("scan_time is not null");
            return (Criteria) this;
        }

        public Criteria andScanTimeEqualTo(Date value) {
            addCriterion("scan_time =", value, "scanTime");
            return (Criteria) this;
        }

        public Criteria andScanTimeNotEqualTo(Date value) {
            addCriterion("scan_time <>", value, "scanTime");
            return (Criteria) this;
        }

        public Criteria andScanTimeGreaterThan(Date value) {
            addCriterion("scan_time >", value, "scanTime");
            return (Criteria) this;
        }

        public Criteria andScanTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("scan_time >=", value, "scanTime");
            return (Criteria) this;
        }

        public Criteria andScanTimeLessThan(Date value) {
            addCriterion("scan_time <", value, "scanTime");
            return (Criteria) this;
        }

        public Criteria andScanTimeLessThanOrEqualTo(Date value) {
            addCriterion("scan_time <=", value, "scanTime");
            return (Criteria) this;
        }

        public Criteria andScanTimeIn(List<Date> values) {
            addCriterion("scan_time in", values, "scanTime");
            return (Criteria) this;
        }

        public Criteria andScanTimeNotIn(List<Date> values) {
            addCriterion("scan_time not in", values, "scanTime");
            return (Criteria) this;
        }

        public Criteria andScanTimeBetween(Date value1, Date value2) {
            addCriterion("scan_time between", value1, value2, "scanTime");
            return (Criteria) this;
        }

        public Criteria andScanTimeNotBetween(Date value1, Date value2) {
            addCriterion("scan_time not between", value1, value2, "scanTime");
            return (Criteria) this;
        }

        public Criteria andPreStatusIsNull() {
            addCriterion("pre_status is null");
            return (Criteria) this;
        }

        public Criteria andPreStatusIsNotNull() {
            addCriterion("pre_status is not null");
            return (Criteria) this;
        }

        public Criteria andPreStatusEqualTo(String value) {
            addCriterion("pre_status =", value, "preStatus");
            return (Criteria) this;
        }

        public Criteria andPreStatusNotEqualTo(String value) {
            addCriterion("pre_status <>", value, "preStatus");
            return (Criteria) this;
        }

        public Criteria andPreStatusGreaterThan(String value) {
            addCriterion("pre_status >", value, "preStatus");
            return (Criteria) this;
        }

        public Criteria andPreStatusGreaterThanOrEqualTo(String value) {
            addCriterion("pre_status >=", value, "preStatus");
            return (Criteria) this;
        }

        public Criteria andPreStatusLessThan(String value) {
            addCriterion("pre_status <", value, "preStatus");
            return (Criteria) this;
        }

        public Criteria andPreStatusLessThanOrEqualTo(String value) {
            addCriterion("pre_status <=", value, "preStatus");
            return (Criteria) this;
        }

        public Criteria andPreStatusLike(String value) {
            addCriterion("pre_status like", value, "preStatus");
            return (Criteria) this;
        }

        public Criteria andPreStatusNotLike(String value) {
            addCriterion("pre_status not like", value, "preStatus");
            return (Criteria) this;
        }

        public Criteria andPreStatusIn(List<String> values) {
            addCriterion("pre_status in", values, "preStatus");
            return (Criteria) this;
        }

        public Criteria andPreStatusNotIn(List<String> values) {
            addCriterion("pre_status not in", values, "preStatus");
            return (Criteria) this;
        }

        public Criteria andPreStatusBetween(String value1, String value2) {
            addCriterion("pre_status between", value1, value2, "preStatus");
            return (Criteria) this;
        }

        public Criteria andPreStatusNotBetween(String value1, String value2) {
            addCriterion("pre_status not between", value1, value2, "preStatus");
            return (Criteria) this;
        }

        public Criteria andAfterStatusIsNull() {
            addCriterion("after_status is null");
            return (Criteria) this;
        }

        public Criteria andAfterStatusIsNotNull() {
            addCriterion("after_status is not null");
            return (Criteria) this;
        }

        public Criteria andAfterStatusEqualTo(String value) {
            addCriterion("after_status =", value, "afterStatus");
            return (Criteria) this;
        }

        public Criteria andAfterStatusNotEqualTo(String value) {
            addCriterion("after_status <>", value, "afterStatus");
            return (Criteria) this;
        }

        public Criteria andAfterStatusGreaterThan(String value) {
            addCriterion("after_status >", value, "afterStatus");
            return (Criteria) this;
        }

        public Criteria andAfterStatusGreaterThanOrEqualTo(String value) {
            addCriterion("after_status >=", value, "afterStatus");
            return (Criteria) this;
        }

        public Criteria andAfterStatusLessThan(String value) {
            addCriterion("after_status <", value, "afterStatus");
            return (Criteria) this;
        }

        public Criteria andAfterStatusLessThanOrEqualTo(String value) {
            addCriterion("after_status <=", value, "afterStatus");
            return (Criteria) this;
        }

        public Criteria andAfterStatusLike(String value) {
            addCriterion("after_status like", value, "afterStatus");
            return (Criteria) this;
        }

        public Criteria andAfterStatusNotLike(String value) {
            addCriterion("after_status not like", value, "afterStatus");
            return (Criteria) this;
        }

        public Criteria andAfterStatusIn(List<String> values) {
            addCriterion("after_status in", values, "afterStatus");
            return (Criteria) this;
        }

        public Criteria andAfterStatusNotIn(List<String> values) {
            addCriterion("after_status not in", values, "afterStatus");
            return (Criteria) this;
        }

        public Criteria andAfterStatusBetween(String value1, String value2) {
            addCriterion("after_status between", value1, value2, "afterStatus");
            return (Criteria) this;
        }

        public Criteria andAfterStatusNotBetween(String value1, String value2) {
            addCriterion("after_status not between", value1, value2, "afterStatus");
            return (Criteria) this;
        }

        public Criteria andHandleTimeIsNull() {
            addCriterion("handle_time is null");
            return (Criteria) this;
        }

        public Criteria andHandleTimeIsNotNull() {
            addCriterion("handle_time is not null");
            return (Criteria) this;
        }

        public Criteria andHandleTimeEqualTo(Date value) {
            addCriterion("handle_time =", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeNotEqualTo(Date value) {
            addCriterion("handle_time <>", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeGreaterThan(Date value) {
            addCriterion("handle_time >", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("handle_time >=", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeLessThan(Date value) {
            addCriterion("handle_time <", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeLessThanOrEqualTo(Date value) {
            addCriterion("handle_time <=", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeIn(List<Date> values) {
            addCriterion("handle_time in", values, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeNotIn(List<Date> values) {
            addCriterion("handle_time not in", values, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeBetween(Date value1, Date value2) {
            addCriterion("handle_time between", value1, value2, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeNotBetween(Date value1, Date value2) {
            addCriterion("handle_time not between", value1, value2, "handleTime");
            return (Criteria) this;
        }

        public Criteria andSaveTimeIsNull() {
            addCriterion("save_time is null");
            return (Criteria) this;
        }

        public Criteria andSaveTimeIsNotNull() {
            addCriterion("save_time is not null");
            return (Criteria) this;
        }

        public Criteria andSaveTimeEqualTo(Date value) {
            addCriterion("save_time =", value, "saveTime");
            return (Criteria) this;
        }

        public Criteria andSaveTimeNotEqualTo(Date value) {
            addCriterion("save_time <>", value, "saveTime");
            return (Criteria) this;
        }

        public Criteria andSaveTimeGreaterThan(Date value) {
            addCriterion("save_time >", value, "saveTime");
            return (Criteria) this;
        }

        public Criteria andSaveTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("save_time >=", value, "saveTime");
            return (Criteria) this;
        }

        public Criteria andSaveTimeLessThan(Date value) {
            addCriterion("save_time <", value, "saveTime");
            return (Criteria) this;
        }

        public Criteria andSaveTimeLessThanOrEqualTo(Date value) {
            addCriterion("save_time <=", value, "saveTime");
            return (Criteria) this;
        }

        public Criteria andSaveTimeIn(List<Date> values) {
            addCriterion("save_time in", values, "saveTime");
            return (Criteria) this;
        }

        public Criteria andSaveTimeNotIn(List<Date> values) {
            addCriterion("save_time not in", values, "saveTime");
            return (Criteria) this;
        }

        public Criteria andSaveTimeBetween(Date value1, Date value2) {
            addCriterion("save_time between", value1, value2, "saveTime");
            return (Criteria) this;
        }

        public Criteria andSaveTimeNotBetween(Date value1, Date value2) {
            addCriterion("save_time not between", value1, value2, "saveTime");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNull() {
            addCriterion("operator is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNotNull() {
            addCriterion("operator is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorEqualTo(String value) {
            addCriterion("operator =", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotEqualTo(String value) {
            addCriterion("operator <>", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThan(String value) {
            addCriterion("operator >", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("operator >=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThan(String value) {
            addCriterion("operator <", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThanOrEqualTo(String value) {
            addCriterion("operator <=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLike(String value) {
            addCriterion("operator like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotLike(String value) {
            addCriterion("operator not like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorIn(List<String> values) {
            addCriterion("operator in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotIn(List<String> values) {
            addCriterion("operator not in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorBetween(String value1, String value2) {
            addCriterion("operator between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotBetween(String value1, String value2) {
            addCriterion("operator not between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(LocalDateTime value) {
            addCreateTimeCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(LocalDateTime value) {
            addCreateTimeCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(LocalDateTime value) {
            addCreateTimeCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCreateTimeCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(LocalDateTime value) {
            addCreateTimeCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(LocalDateTime value) {
            addCreateTimeCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<LocalDateTime> values) {
            addCreateTimeCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<LocalDateTime> values) {
            addCreateTimeCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCreateTimeCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCreateTimeCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNull() {
            addCriterion("create_user is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("create_user is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(String value) {
            addCriterion("create_user =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(String value) {
            addCriterion("create_user <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(String value) {
            addCriterion("create_user >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(String value) {
            addCriterion("create_user >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(String value) {
            addCriterion("create_user <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(String value) {
            addCriterion("create_user <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLike(String value) {
            addCriterion("create_user like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotLike(String value) {
            addCriterion("create_user not like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<String> values) {
            addCriterion("create_user in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<String> values) {
            addCriterion("create_user not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(String value1, String value2) {
            addCriterion("create_user between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(String value1, String value2) {
            addCriterion("create_user not between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateSystemIsNull() {
            addCriterion("create_system is null");
            return (Criteria) this;
        }

        public Criteria andCreateSystemIsNotNull() {
            addCriterion("create_system is not null");
            return (Criteria) this;
        }

        public Criteria andCreateSystemEqualTo(String value) {
            addCriterion("create_system =", value, "createSystem");
            return (Criteria) this;
        }

        public Criteria andCreateSystemNotEqualTo(String value) {
            addCriterion("create_system <>", value, "createSystem");
            return (Criteria) this;
        }

        public Criteria andCreateSystemGreaterThan(String value) {
            addCriterion("create_system >", value, "createSystem");
            return (Criteria) this;
        }

        public Criteria andCreateSystemGreaterThanOrEqualTo(String value) {
            addCriterion("create_system >=", value, "createSystem");
            return (Criteria) this;
        }

        public Criteria andCreateSystemLessThan(String value) {
            addCriterion("create_system <", value, "createSystem");
            return (Criteria) this;
        }

        public Criteria andCreateSystemLessThanOrEqualTo(String value) {
            addCriterion("create_system <=", value, "createSystem");
            return (Criteria) this;
        }

        public Criteria andCreateSystemLike(String value) {
            addCriterion("create_system like", value, "createSystem");
            return (Criteria) this;
        }

        public Criteria andCreateSystemNotLike(String value) {
            addCriterion("create_system not like", value, "createSystem");
            return (Criteria) this;
        }

        public Criteria andCreateSystemIn(List<String> values) {
            addCriterion("create_system in", values, "createSystem");
            return (Criteria) this;
        }

        public Criteria andCreateSystemNotIn(List<String> values) {
            addCriterion("create_system not in", values, "createSystem");
            return (Criteria) this;
        }

        public Criteria andCreateSystemBetween(String value1, String value2) {
            addCriterion("create_system between", value1, value2, "createSystem");
            return (Criteria) this;
        }

        public Criteria andCreateSystemNotBetween(String value1, String value2) {
            addCriterion("create_system not between", value1, value2, "createSystem");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(LocalDateTime value) {
            addUpdateTimeCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(LocalDateTime value) {
            addUpdateTimeCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(LocalDateTime value) {
            addUpdateTimeCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addUpdateTimeCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(LocalDateTime value) {
            addUpdateTimeCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(LocalDateTime value) {
            addUpdateTimeCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<LocalDateTime> values) {
            addUpdateTimeCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<LocalDateTime> values) {
            addUpdateTimeCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addUpdateTimeCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addUpdateTimeCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNull() {
            addCriterion("update_user is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNotNull() {
            addCriterion("update_user is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserEqualTo(String value) {
            addCriterion("update_user =", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotEqualTo(String value) {
            addCriterion("update_user <>", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThan(String value) {
            addCriterion("update_user >", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThanOrEqualTo(String value) {
            addCriterion("update_user >=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThan(String value) {
            addCriterion("update_user <", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThanOrEqualTo(String value) {
            addCriterion("update_user <=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLike(String value) {
            addCriterion("update_user like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotLike(String value) {
            addCriterion("update_user not like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIn(List<String> values) {
            addCriterion("update_user in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotIn(List<String> values) {
            addCriterion("update_user not in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserBetween(String value1, String value2) {
            addCriterion("update_user between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotBetween(String value1, String value2) {
            addCriterion("update_user not between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateSystemIsNull() {
            addCriterion("update_system is null");
            return (Criteria) this;
        }

        public Criteria andUpdateSystemIsNotNull() {
            addCriterion("update_system is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateSystemEqualTo(String value) {
            addCriterion("update_system =", value, "updateSystem");
            return (Criteria) this;
        }

        public Criteria andUpdateSystemNotEqualTo(String value) {
            addCriterion("update_system <>", value, "updateSystem");
            return (Criteria) this;
        }

        public Criteria andUpdateSystemGreaterThan(String value) {
            addCriterion("update_system >", value, "updateSystem");
            return (Criteria) this;
        }

        public Criteria andUpdateSystemGreaterThanOrEqualTo(String value) {
            addCriterion("update_system >=", value, "updateSystem");
            return (Criteria) this;
        }

        public Criteria andUpdateSystemLessThan(String value) {
            addCriterion("update_system <", value, "updateSystem");
            return (Criteria) this;
        }

        public Criteria andUpdateSystemLessThanOrEqualTo(String value) {
            addCriterion("update_system <=", value, "updateSystem");
            return (Criteria) this;
        }

        public Criteria andUpdateSystemLike(String value) {
            addCriterion("update_system like", value, "updateSystem");
            return (Criteria) this;
        }

        public Criteria andUpdateSystemNotLike(String value) {
            addCriterion("update_system not like", value, "updateSystem");
            return (Criteria) this;
        }

        public Criteria andUpdateSystemIn(List<String> values) {
            addCriterion("update_system in", values, "updateSystem");
            return (Criteria) this;
        }

        public Criteria andUpdateSystemNotIn(List<String> values) {
            addCriterion("update_system not in", values, "updateSystem");
            return (Criteria) this;
        }

        public Criteria andUpdateSystemBetween(String value1, String value2) {
            addCriterion("update_system between", value1, value2, "updateSystem");
            return (Criteria) this;
        }

        public Criteria andUpdateSystemNotBetween(String value1, String value2) {
            addCriterion("update_system not between", value1, value2, "updateSystem");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNull() {
            addCriterion("is_deleted is null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNotNull() {
            addCriterion("is_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedEqualTo(Byte value) {
            addCriterion("is_deleted =", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotEqualTo(Byte value) {
            addCriterion("is_deleted <>", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThan(Byte value) {
            addCriterion("is_deleted >", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_deleted >=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThan(Byte value) {
            addCriterion("is_deleted <", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThanOrEqualTo(Byte value) {
            addCriterion("is_deleted <=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIn(List<Byte> values) {
            addCriterion("is_deleted in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotIn(List<Byte> values) {
            addCriterion("is_deleted not in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedBetween(Byte value1, Byte value2) {
            addCriterion("is_deleted between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotBetween(Byte value1, Byte value2) {
            addCriterion("is_deleted not between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andSourceIsNull() {
            addCriterion("source is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("source is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(Byte value) {
            addCriterion("source =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(Byte value) {
            addCriterion("source <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(Byte value) {
            addCriterion("source >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(Byte value) {
            addCriterion("source >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(Byte value) {
            addCriterion("source <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(Byte value) {
            addCriterion("source <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<Byte> values) {
            addCriterion("source in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<Byte> values) {
            addCriterion("source not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(Byte value1, Byte value2) {
            addCriterion("source between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(Byte value1, Byte value2) {
            addCriterion("source not between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andPtQuantityIsNull() {
            addCriterion("pt_quantity is null");
            return (Criteria) this;
        }

        public Criteria andPtQuantityIsNotNull() {
            addCriterion("pt_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andPtQuantityEqualTo(BigDecimal value) {
            addCriterion("pt_quantity =", value, "ptQuantity");
            return (Criteria) this;
        }

        public Criteria andPtQuantityNotEqualTo(BigDecimal value) {
            addCriterion("pt_quantity <>", value, "ptQuantity");
            return (Criteria) this;
        }

        public Criteria andPtQuantityGreaterThan(BigDecimal value) {
            addCriterion("pt_quantity >", value, "ptQuantity");
            return (Criteria) this;
        }

        public Criteria andPtQuantityGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pt_quantity >=", value, "ptQuantity");
            return (Criteria) this;
        }

        public Criteria andPtQuantityLessThan(BigDecimal value) {
            addCriterion("pt_quantity <", value, "ptQuantity");
            return (Criteria) this;
        }

        public Criteria andPtQuantityLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pt_quantity <=", value, "ptQuantity");
            return (Criteria) this;
        }

        public Criteria andPtQuantityIn(List<BigDecimal> values) {
            addCriterion("pt_quantity in", values, "ptQuantity");
            return (Criteria) this;
        }

        public Criteria andPtQuantityNotIn(List<BigDecimal> values) {
            addCriterion("pt_quantity not in", values, "ptQuantity");
            return (Criteria) this;
        }

        public Criteria andPtQuantityBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pt_quantity between", value1, value2, "ptQuantity");
            return (Criteria) this;
        }

        public Criteria andPtQuantityNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pt_quantity not between", value1, value2, "ptQuantity");
            return (Criteria) this;
        }

        public Criteria andProductNoIsNull() {
            addCriterion("product_no is null");
            return (Criteria) this;
        }

        public Criteria andProductNoIsNotNull() {
            addCriterion("product_no is not null");
            return (Criteria) this;
        }

        public Criteria andProductNoEqualTo(Long value) {
            addCriterion("product_no =", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoNotEqualTo(Long value) {
            addCriterion("product_no <>", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoGreaterThan(Long value) {
            addCriterion("product_no >", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoGreaterThanOrEqualTo(Long value) {
            addCriterion("product_no >=", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoLessThan(Long value) {
            addCriterion("product_no <", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoLessThanOrEqualTo(Long value) {
            addCriterion("product_no <=", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoIn(List<Long> values) {
            addCriterion("product_no in", values, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoNotIn(List<Long> values) {
            addCriterion("product_no not in", values, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoBetween(Long value1, Long value2) {
            addCriterion("product_no between", value1, value2, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoNotBetween(Long value1, Long value2) {
            addCriterion("product_no not between", value1, value2, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductSortIsNull() {
            addCriterion("product_sort is null");
            return (Criteria) this;
        }

        public Criteria andProductSortIsNotNull() {
            addCriterion("product_sort is not null");
            return (Criteria) this;
        }

        public Criteria andProductSortEqualTo(Long value) {
            addCriterion("product_sort =", value, "productSort");
            return (Criteria) this;
        }

        public Criteria andProductSortNotEqualTo(Long value) {
            addCriterion("product_sort <>", value, "productSort");
            return (Criteria) this;
        }

        public Criteria andProductSortGreaterThan(Long value) {
            addCriterion("product_sort >", value, "productSort");
            return (Criteria) this;
        }

        public Criteria andProductSortGreaterThanOrEqualTo(Long value) {
            addCriterion("product_sort >=", value, "productSort");
            return (Criteria) this;
        }

        public Criteria andProductSortLessThan(Long value) {
            addCriterion("product_sort <", value, "productSort");
            return (Criteria) this;
        }

        public Criteria andProductSortLessThanOrEqualTo(Long value) {
            addCriterion("product_sort <=", value, "productSort");
            return (Criteria) this;
        }

        public Criteria andProductSortIn(List<Long> values) {
            addCriterion("product_sort in", values, "productSort");
            return (Criteria) this;
        }

        public Criteria andProductSortNotIn(List<Long> values) {
            addCriterion("product_sort not in", values, "productSort");
            return (Criteria) this;
        }

        public Criteria andProductSortBetween(Long value1, Long value2) {
            addCriterion("product_sort between", value1, value2, "productSort");
            return (Criteria) this;
        }

        public Criteria andProductSortNotBetween(Long value1, Long value2) {
            addCriterion("product_sort not between", value1, value2, "productSort");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameIsNull() {
            addCriterion("employee_name is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameIsNotNull() {
            addCriterion("employee_name is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameEqualTo(String value) {
            addCriterion("employee_name =", value, "employeeName");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameNotEqualTo(String value) {
            addCriterion("employee_name <>", value, "employeeName");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameGreaterThan(String value) {
            addCriterion("employee_name >", value, "employeeName");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameGreaterThanOrEqualTo(String value) {
            addCriterion("employee_name >=", value, "employeeName");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameLessThan(String value) {
            addCriterion("employee_name <", value, "employeeName");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameLessThanOrEqualTo(String value) {
            addCriterion("employee_name <=", value, "employeeName");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameLike(String value) {
            addCriterion("employee_name like", value, "employeeName");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameNotLike(String value) {
            addCriterion("employee_name not like", value, "employeeName");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameIn(List<String> values) {
            addCriterion("employee_name in", values, "employeeName");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameNotIn(List<String> values) {
            addCriterion("employee_name not in", values, "employeeName");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameBetween(String value1, String value2) {
            addCriterion("employee_name between", value1, value2, "employeeName");
            return (Criteria) this;
        }

        public Criteria andEmployeeNameNotBetween(String value1, String value2) {
            addCriterion("employee_name not between", value1, value2, "employeeName");
            return (Criteria) this;
        }

        public Criteria andMarkupCraftIsNull() {
            addCriterion("markup_craft is null");
            return (Criteria) this;
        }

        public Criteria andMarkupCraftIsNotNull() {
            addCriterion("markup_craft is not null");
            return (Criteria) this;
        }

        public Criteria andMarkupCraftEqualTo(String value) {
            addCriterion("markup_craft =", value, "markupCraft");
            return (Criteria) this;
        }

        public Criteria andMarkupCraftNotEqualTo(String value) {
            addCriterion("markup_craft <>", value, "markupCraft");
            return (Criteria) this;
        }

        public Criteria andMarkupCraftGreaterThan(String value) {
            addCriterion("markup_craft >", value, "markupCraft");
            return (Criteria) this;
        }

        public Criteria andMarkupCraftGreaterThanOrEqualTo(String value) {
            addCriterion("markup_craft >=", value, "markupCraft");
            return (Criteria) this;
        }

        public Criteria andMarkupCraftLessThan(String value) {
            addCriterion("markup_craft <", value, "markupCraft");
            return (Criteria) this;
        }

        public Criteria andMarkupCraftLessThanOrEqualTo(String value) {
            addCriterion("markup_craft <=", value, "markupCraft");
            return (Criteria) this;
        }

        public Criteria andMarkupCraftLike(String value) {
            addCriterion("markup_craft like", value, "markupCraft");
            return (Criteria) this;
        }

        public Criteria andMarkupCraftNotLike(String value) {
            addCriterion("markup_craft not like", value, "markupCraft");
            return (Criteria) this;
        }

        public Criteria andMarkupCraftIn(List<String> values) {
            addCriterion("markup_craft in", values, "markupCraft");
            return (Criteria) this;
        }

        public Criteria andMarkupCraftNotIn(List<String> values) {
            addCriterion("markup_craft not in", values, "markupCraft");
            return (Criteria) this;
        }

        public Criteria andMarkupCraftBetween(String value1, String value2) {
            addCriterion("markup_craft between", value1, value2, "markupCraft");
            return (Criteria) this;
        }

        public Criteria andMarkupCraftNotBetween(String value1, String value2) {
            addCriterion("markup_craft not between", value1, value2, "markupCraft");
            return (Criteria) this;
        }

        public Criteria andTeamGroupIsNull() {
            addCriterion("team_group is null");
            return (Criteria) this;
        }

        public Criteria andTeamGroupIsNotNull() {
            addCriterion("team_group is not null");
            return (Criteria) this;
        }

        public Criteria andTeamGroupEqualTo(String value) {
            addCriterion("team_group =", value, "teamGroup");
            return (Criteria) this;
        }

        public Criteria andTeamGroupNotEqualTo(String value) {
            addCriterion("team_group <>", value, "teamGroup");
            return (Criteria) this;
        }

        public Criteria andTeamGroupGreaterThan(String value) {
            addCriterion("team_group >", value, "teamGroup");
            return (Criteria) this;
        }

        public Criteria andTeamGroupGreaterThanOrEqualTo(String value) {
            addCriterion("team_group >=", value, "teamGroup");
            return (Criteria) this;
        }

        public Criteria andTeamGroupLessThan(String value) {
            addCriterion("team_group <", value, "teamGroup");
            return (Criteria) this;
        }

        public Criteria andTeamGroupLessThanOrEqualTo(String value) {
            addCriterion("team_group <=", value, "teamGroup");
            return (Criteria) this;
        }

        public Criteria andTeamGroupLike(String value) {
            addCriterion("team_group like", value, "teamGroup");
            return (Criteria) this;
        }

        public Criteria andTeamGroupNotLike(String value) {
            addCriterion("team_group not like", value, "teamGroup");
            return (Criteria) this;
        }

        public Criteria andTeamGroupIn(List<String> values) {
            addCriterion("team_group in", values, "teamGroup");
            return (Criteria) this;
        }

        public Criteria andTeamGroupNotIn(List<String> values) {
            addCriterion("team_group not in", values, "teamGroup");
            return (Criteria) this;
        }

        public Criteria andTeamGroupBetween(String value1, String value2) {
            addCriterion("team_group between", value1, value2, "teamGroup");
            return (Criteria) this;
        }

        public Criteria andTeamGroupNotBetween(String value1, String value2) {
            addCriterion("team_group not between", value1, value2, "teamGroup");
            return (Criteria) this;
        }

        public Criteria andAvgCarSpeedIsNull() {
            addCriterion("avg_car_speed is null");
            return (Criteria) this;
        }

        public Criteria andAvgCarSpeedIsNotNull() {
            addCriterion("avg_car_speed is not null");
            return (Criteria) this;
        }

        public Criteria andAvgCarSpeedEqualTo(BigDecimal value) {
            addCriterion("avg_car_speed =", value, "avgCarSpeed");
            return (Criteria) this;
        }

        public Criteria andAvgCarSpeedNotEqualTo(BigDecimal value) {
            addCriterion("avg_car_speed <>", value, "avgCarSpeed");
            return (Criteria) this;
        }

        public Criteria andAvgCarSpeedGreaterThan(BigDecimal value) {
            addCriterion("avg_car_speed >", value, "avgCarSpeed");
            return (Criteria) this;
        }

        public Criteria andAvgCarSpeedGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("avg_car_speed >=", value, "avgCarSpeed");
            return (Criteria) this;
        }

        public Criteria andAvgCarSpeedLessThan(BigDecimal value) {
            addCriterion("avg_car_speed <", value, "avgCarSpeed");
            return (Criteria) this;
        }

        public Criteria andAvgCarSpeedLessThanOrEqualTo(BigDecimal value) {
            addCriterion("avg_car_speed <=", value, "avgCarSpeed");
            return (Criteria) this;
        }

        public Criteria andAvgCarSpeedIn(List<BigDecimal> values) {
            addCriterion("avg_car_speed in", values, "avgCarSpeed");
            return (Criteria) this;
        }

        public Criteria andAvgCarSpeedNotIn(List<BigDecimal> values) {
            addCriterion("avg_car_speed not in", values, "avgCarSpeed");
            return (Criteria) this;
        }

        public Criteria andAvgCarSpeedBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("avg_car_speed between", value1, value2, "avgCarSpeed");
            return (Criteria) this;
        }

        public Criteria andAvgCarSpeedNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("avg_car_speed not between", value1, value2, "avgCarSpeed");
            return (Criteria) this;
        }

        public Criteria andAvgTemperatureIsNull() {
            addCriterion("avg_temperature is null");
            return (Criteria) this;
        }

        public Criteria andAvgTemperatureIsNotNull() {
            addCriterion("avg_temperature is not null");
            return (Criteria) this;
        }

        public Criteria andAvgTemperatureEqualTo(BigDecimal value) {
            addCriterion("avg_temperature =", value, "avgTemperature");
            return (Criteria) this;
        }

        public Criteria andAvgTemperatureNotEqualTo(BigDecimal value) {
            addCriterion("avg_temperature <>", value, "avgTemperature");
            return (Criteria) this;
        }

        public Criteria andAvgTemperatureGreaterThan(BigDecimal value) {
            addCriterion("avg_temperature >", value, "avgTemperature");
            return (Criteria) this;
        }

        public Criteria andAvgTemperatureGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("avg_temperature >=", value, "avgTemperature");
            return (Criteria) this;
        }

        public Criteria andAvgTemperatureLessThan(BigDecimal value) {
            addCriterion("avg_temperature <", value, "avgTemperature");
            return (Criteria) this;
        }

        public Criteria andAvgTemperatureLessThanOrEqualTo(BigDecimal value) {
            addCriterion("avg_temperature <=", value, "avgTemperature");
            return (Criteria) this;
        }

        public Criteria andAvgTemperatureIn(List<BigDecimal> values) {
            addCriterion("avg_temperature in", values, "avgTemperature");
            return (Criteria) this;
        }

        public Criteria andAvgTemperatureNotIn(List<BigDecimal> values) {
            addCriterion("avg_temperature not in", values, "avgTemperature");
            return (Criteria) this;
        }

        public Criteria andAvgTemperatureBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("avg_temperature between", value1, value2, "avgTemperature");
            return (Criteria) this;
        }

        public Criteria andAvgTemperatureNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("avg_temperature not between", value1, value2, "avgTemperature");
            return (Criteria) this;
        }

        public Criteria andAvgWindSpeedIsNull() {
            addCriterion("avg_wind_speed is null");
            return (Criteria) this;
        }

        public Criteria andAvgWindSpeedIsNotNull() {
            addCriterion("avg_wind_speed is not null");
            return (Criteria) this;
        }

        public Criteria andAvgWindSpeedEqualTo(BigDecimal value) {
            addCriterion("avg_wind_speed =", value, "avgWindSpeed");
            return (Criteria) this;
        }

        public Criteria andAvgWindSpeedNotEqualTo(BigDecimal value) {
            addCriterion("avg_wind_speed <>", value, "avgWindSpeed");
            return (Criteria) this;
        }

        public Criteria andAvgWindSpeedGreaterThan(BigDecimal value) {
            addCriterion("avg_wind_speed >", value, "avgWindSpeed");
            return (Criteria) this;
        }

        public Criteria andAvgWindSpeedGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("avg_wind_speed >=", value, "avgWindSpeed");
            return (Criteria) this;
        }

        public Criteria andAvgWindSpeedLessThan(BigDecimal value) {
            addCriterion("avg_wind_speed <", value, "avgWindSpeed");
            return (Criteria) this;
        }

        public Criteria andAvgWindSpeedLessThanOrEqualTo(BigDecimal value) {
            addCriterion("avg_wind_speed <=", value, "avgWindSpeed");
            return (Criteria) this;
        }

        public Criteria andAvgWindSpeedIn(List<BigDecimal> values) {
            addCriterion("avg_wind_speed in", values, "avgWindSpeed");
            return (Criteria) this;
        }

        public Criteria andAvgWindSpeedNotIn(List<BigDecimal> values) {
            addCriterion("avg_wind_speed not in", values, "avgWindSpeed");
            return (Criteria) this;
        }

        public Criteria andAvgWindSpeedBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("avg_wind_speed between", value1, value2, "avgWindSpeed");
            return (Criteria) this;
        }

        public Criteria andAvgWindSpeedNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("avg_wind_speed not between", value1, value2, "avgWindSpeed");
            return (Criteria) this;
        }

        public Criteria andAvgTopFeedIsNull() {
            addCriterion("avg_top_feed is null");
            return (Criteria) this;
        }

        public Criteria andAvgTopFeedIsNotNull() {
            addCriterion("avg_top_feed is not null");
            return (Criteria) this;
        }

        public Criteria andAvgTopFeedEqualTo(BigDecimal value) {
            addCriterion("avg_top_feed =", value, "avgTopFeed");
            return (Criteria) this;
        }

        public Criteria andAvgTopFeedNotEqualTo(BigDecimal value) {
            addCriterion("avg_top_feed <>", value, "avgTopFeed");
            return (Criteria) this;
        }

        public Criteria andAvgTopFeedGreaterThan(BigDecimal value) {
            addCriterion("avg_top_feed >", value, "avgTopFeed");
            return (Criteria) this;
        }

        public Criteria andAvgTopFeedGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("avg_top_feed >=", value, "avgTopFeed");
            return (Criteria) this;
        }

        public Criteria andAvgTopFeedLessThan(BigDecimal value) {
            addCriterion("avg_top_feed <", value, "avgTopFeed");
            return (Criteria) this;
        }

        public Criteria andAvgTopFeedLessThanOrEqualTo(BigDecimal value) {
            addCriterion("avg_top_feed <=", value, "avgTopFeed");
            return (Criteria) this;
        }

        public Criteria andAvgTopFeedIn(List<BigDecimal> values) {
            addCriterion("avg_top_feed in", values, "avgTopFeed");
            return (Criteria) this;
        }

        public Criteria andAvgTopFeedNotIn(List<BigDecimal> values) {
            addCriterion("avg_top_feed not in", values, "avgTopFeed");
            return (Criteria) this;
        }

        public Criteria andAvgTopFeedBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("avg_top_feed between", value1, value2, "avgTopFeed");
            return (Criteria) this;
        }

        public Criteria andAvgTopFeedNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("avg_top_feed not between", value1, value2, "avgTopFeed");
            return (Criteria) this;
        }

        public Criteria andDeptNameIsNull() {
            addCriterion("dept_name is null");
            return (Criteria) this;
        }

        public Criteria andDeptNameIsNotNull() {
            addCriterion("dept_name is not null");
            return (Criteria) this;
        }

        public Criteria andDeptNameEqualTo(String value) {
            addCriterion("dept_name =", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotEqualTo(String value) {
            addCriterion("dept_name <>", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThan(String value) {
            addCriterion("dept_name >", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("dept_name >=", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThan(String value) {
            addCriterion("dept_name <", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThanOrEqualTo(String value) {
            addCriterion("dept_name <=", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLike(String value) {
            addCriterion("dept_name like", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotLike(String value) {
            addCriterion("dept_name not like", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameIn(List<String> values) {
            addCriterion("dept_name in", values, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotIn(List<String> values) {
            addCriterion("dept_name not in", values, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameBetween(String value1, String value2) {
            addCriterion("dept_name between", value1, value2, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotBetween(String value1, String value2) {
            addCriterion("dept_name not between", value1, value2, "deptName");
            return (Criteria) this;
        }

        public Criteria andScheduleDateIsNull() {
            addCriterion("schedule_date is null");
            return (Criteria) this;
        }

        public Criteria andScheduleDateIsNotNull() {
            addCriterion("schedule_date is not null");
            return (Criteria) this;
        }

        public Criteria andScheduleDateEqualTo(Date value) {
            addCriterionForJDBCDate("schedule_date =", value, "scheduleDate");
            return (Criteria) this;
        }

        public Criteria andScheduleDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("schedule_date <>", value, "scheduleDate");
            return (Criteria) this;
        }

        public Criteria andScheduleDateGreaterThan(Date value) {
            addCriterionForJDBCDate("schedule_date >", value, "scheduleDate");
            return (Criteria) this;
        }

        public Criteria andScheduleDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("schedule_date >=", value, "scheduleDate");
            return (Criteria) this;
        }

        public Criteria andScheduleDateLessThan(Date value) {
            addCriterionForJDBCDate("schedule_date <", value, "scheduleDate");
            return (Criteria) this;
        }

        public Criteria andScheduleDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("schedule_date <=", value, "scheduleDate");
            return (Criteria) this;
        }

        public Criteria andScheduleDateIn(List<Date> values) {
            addCriterionForJDBCDate("schedule_date in", values, "scheduleDate");
            return (Criteria) this;
        }

        public Criteria andScheduleDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("schedule_date not in", values, "scheduleDate");
            return (Criteria) this;
        }

        public Criteria andScheduleDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("schedule_date between", value1, value2, "scheduleDate");
            return (Criteria) this;
        }

        public Criteria andScheduleDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("schedule_date not between", value1, value2, "scheduleDate");
            return (Criteria) this;
        }

        public Criteria andTeamGroupIdIsNull() {
            addCriterion("team_group_id is null");
            return (Criteria) this;
        }

        public Criteria andTeamGroupIdIsNotNull() {
            addCriterion("team_group_id is not null");
            return (Criteria) this;
        }

        public Criteria andTeamGroupIdEqualTo(Long value) {
            addCriterion("team_group_id =", value, "teamGroupId");
            return (Criteria) this;
        }

        public Criteria andTeamGroupIdNotEqualTo(Long value) {
            addCriterion("team_group_id <>", value, "teamGroupId");
            return (Criteria) this;
        }

        public Criteria andTeamGroupIdGreaterThan(Long value) {
            addCriterion("team_group_id >", value, "teamGroupId");
            return (Criteria) this;
        }

        public Criteria andTeamGroupIdGreaterThanOrEqualTo(Long value) {
            addCriterion("team_group_id >=", value, "teamGroupId");
            return (Criteria) this;
        }

        public Criteria andTeamGroupIdLessThan(Long value) {
            addCriterion("team_group_id <", value, "teamGroupId");
            return (Criteria) this;
        }

        public Criteria andTeamGroupIdLessThanOrEqualTo(Long value) {
            addCriterion("team_group_id <=", value, "teamGroupId");
            return (Criteria) this;
        }

        public Criteria andTeamGroupIdIn(List<Long> values) {
            addCriterion("team_group_id in", values, "teamGroupId");
            return (Criteria) this;
        }

        public Criteria andTeamGroupIdNotIn(List<Long> values) {
            addCriterion("team_group_id not in", values, "teamGroupId");
            return (Criteria) this;
        }

        public Criteria andTeamGroupIdBetween(Long value1, Long value2) {
            addCriterion("team_group_id between", value1, value2, "teamGroupId");
            return (Criteria) this;
        }

        public Criteria andTeamGroupIdNotBetween(Long value1, Long value2) {
            addCriterion("team_group_id not between", value1, value2, "teamGroupId");
            return (Criteria) this;
        }

        public Criteria andTeamGroupNameIsNull() {
            addCriterion("team_group_name is null");
            return (Criteria) this;
        }

        public Criteria andTeamGroupNameIsNotNull() {
            addCriterion("team_group_name is not null");
            return (Criteria) this;
        }

        public Criteria andTeamGroupNameEqualTo(String value) {
            addCriterion("team_group_name =", value, "teamGroupName");
            return (Criteria) this;
        }

        public Criteria andTeamGroupNameNotEqualTo(String value) {
            addCriterion("team_group_name <>", value, "teamGroupName");
            return (Criteria) this;
        }

        public Criteria andTeamGroupNameGreaterThan(String value) {
            addCriterion("team_group_name >", value, "teamGroupName");
            return (Criteria) this;
        }

        public Criteria andTeamGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("team_group_name >=", value, "teamGroupName");
            return (Criteria) this;
        }

        public Criteria andTeamGroupNameLessThan(String value) {
            addCriterion("team_group_name <", value, "teamGroupName");
            return (Criteria) this;
        }

        public Criteria andTeamGroupNameLessThanOrEqualTo(String value) {
            addCriterion("team_group_name <=", value, "teamGroupName");
            return (Criteria) this;
        }

        public Criteria andTeamGroupNameLike(String value) {
            addCriterion("team_group_name like", value, "teamGroupName");
            return (Criteria) this;
        }

        public Criteria andTeamGroupNameNotLike(String value) {
            addCriterion("team_group_name not like", value, "teamGroupName");
            return (Criteria) this;
        }

        public Criteria andTeamGroupNameIn(List<String> values) {
            addCriterion("team_group_name in", values, "teamGroupName");
            return (Criteria) this;
        }

        public Criteria andTeamGroupNameNotIn(List<String> values) {
            addCriterion("team_group_name not in", values, "teamGroupName");
            return (Criteria) this;
        }

        public Criteria andTeamGroupNameBetween(String value1, String value2) {
            addCriterion("team_group_name between", value1, value2, "teamGroupName");
            return (Criteria) this;
        }

        public Criteria andTeamGroupNameNotBetween(String value1, String value2) {
            addCriterion("team_group_name not between", value1, value2, "teamGroupName");
            return (Criteria) this;
        }

        public Criteria andTeamShiftIdIsNull() {
            addCriterion("team_shift_id is null");
            return (Criteria) this;
        }

        public Criteria andTeamShiftIdIsNotNull() {
            addCriterion("team_shift_id is not null");
            return (Criteria) this;
        }

        public Criteria andTeamShiftIdEqualTo(Long value) {
            addCriterion("team_shift_id =", value, "teamShiftId");
            return (Criteria) this;
        }

        public Criteria andTeamShiftIdNotEqualTo(Long value) {
            addCriterion("team_shift_id <>", value, "teamShiftId");
            return (Criteria) this;
        }

        public Criteria andTeamShiftIdGreaterThan(Long value) {
            addCriterion("team_shift_id >", value, "teamShiftId");
            return (Criteria) this;
        }

        public Criteria andTeamShiftIdGreaterThanOrEqualTo(Long value) {
            addCriterion("team_shift_id >=", value, "teamShiftId");
            return (Criteria) this;
        }

        public Criteria andTeamShiftIdLessThan(Long value) {
            addCriterion("team_shift_id <", value, "teamShiftId");
            return (Criteria) this;
        }

        public Criteria andTeamShiftIdLessThanOrEqualTo(Long value) {
            addCriterion("team_shift_id <=", value, "teamShiftId");
            return (Criteria) this;
        }

        public Criteria andTeamShiftIdIn(List<Long> values) {
            addCriterion("team_shift_id in", values, "teamShiftId");
            return (Criteria) this;
        }

        public Criteria andTeamShiftIdNotIn(List<Long> values) {
            addCriterion("team_shift_id not in", values, "teamShiftId");
            return (Criteria) this;
        }

        public Criteria andTeamShiftIdBetween(Long value1, Long value2) {
            addCriterion("team_shift_id between", value1, value2, "teamShiftId");
            return (Criteria) this;
        }

        public Criteria andTeamShiftIdNotBetween(Long value1, Long value2) {
            addCriterion("team_shift_id not between", value1, value2, "teamShiftId");
            return (Criteria) this;
        }

        public Criteria andTeamShiftNameIsNull() {
            addCriterion("team_shift_name is null");
            return (Criteria) this;
        }

        public Criteria andTeamShiftNameIsNotNull() {
            addCriterion("team_shift_name is not null");
            return (Criteria) this;
        }

        public Criteria andTeamShiftNameEqualTo(String value) {
            addCriterion("team_shift_name =", value, "teamShiftName");
            return (Criteria) this;
        }

        public Criteria andTeamShiftNameNotEqualTo(String value) {
            addCriterion("team_shift_name <>", value, "teamShiftName");
            return (Criteria) this;
        }

        public Criteria andTeamShiftNameGreaterThan(String value) {
            addCriterion("team_shift_name >", value, "teamShiftName");
            return (Criteria) this;
        }

        public Criteria andTeamShiftNameGreaterThanOrEqualTo(String value) {
            addCriterion("team_shift_name >=", value, "teamShiftName");
            return (Criteria) this;
        }

        public Criteria andTeamShiftNameLessThan(String value) {
            addCriterion("team_shift_name <", value, "teamShiftName");
            return (Criteria) this;
        }

        public Criteria andTeamShiftNameLessThanOrEqualTo(String value) {
            addCriterion("team_shift_name <=", value, "teamShiftName");
            return (Criteria) this;
        }

        public Criteria andTeamShiftNameLike(String value) {
            addCriterion("team_shift_name like", value, "teamShiftName");
            return (Criteria) this;
        }

        public Criteria andTeamShiftNameNotLike(String value) {
            addCriterion("team_shift_name not like", value, "teamShiftName");
            return (Criteria) this;
        }

        public Criteria andTeamShiftNameIn(List<String> values) {
            addCriterion("team_shift_name in", values, "teamShiftName");
            return (Criteria) this;
        }

        public Criteria andTeamShiftNameNotIn(List<String> values) {
            addCriterion("team_shift_name not in", values, "teamShiftName");
            return (Criteria) this;
        }

        public Criteria andTeamShiftNameBetween(String value1, String value2) {
            addCriterion("team_shift_name between", value1, value2, "teamShiftName");
            return (Criteria) this;
        }

        public Criteria andTeamShiftNameNotBetween(String value1, String value2) {
            addCriterion("team_shift_name not between", value1, value2, "teamShiftName");
            return (Criteria) this;
        }

        public Criteria andWorkshopIdIsNull() {
            addCriterion("workshop_id is null");
            return (Criteria) this;
        }

        public Criteria andWorkshopIdIsNotNull() {
            addCriterion("workshop_id is not null");
            return (Criteria) this;
        }

        public Criteria andWorkshopIdEqualTo(Long value) {
            addCriterion("workshop_id =", value, "workshopId");
            return (Criteria) this;
        }

        public Criteria andWorkshopIdNotEqualTo(Long value) {
            addCriterion("workshop_id <>", value, "workshopId");
            return (Criteria) this;
        }

        public Criteria andWorkshopIdGreaterThan(Long value) {
            addCriterion("workshop_id >", value, "workshopId");
            return (Criteria) this;
        }

        public Criteria andWorkshopIdGreaterThanOrEqualTo(Long value) {
            addCriterion("workshop_id >=", value, "workshopId");
            return (Criteria) this;
        }

        public Criteria andWorkshopIdLessThan(Long value) {
            addCriterion("workshop_id <", value, "workshopId");
            return (Criteria) this;
        }

        public Criteria andWorkshopIdLessThanOrEqualTo(Long value) {
            addCriterion("workshop_id <=", value, "workshopId");
            return (Criteria) this;
        }

        public Criteria andWorkshopIdIn(List<Long> values) {
            addCriterion("workshop_id in", values, "workshopId");
            return (Criteria) this;
        }

        public Criteria andWorkshopIdNotIn(List<Long> values) {
            addCriterion("workshop_id not in", values, "workshopId");
            return (Criteria) this;
        }

        public Criteria andWorkshopIdBetween(Long value1, Long value2) {
            addCriterion("workshop_id between", value1, value2, "workshopId");
            return (Criteria) this;
        }

        public Criteria andWorkshopIdNotBetween(Long value1, Long value2) {
            addCriterion("workshop_id not between", value1, value2, "workshopId");
            return (Criteria) this;
        }

        public Criteria andWorkshopNameIsNull() {
            addCriterion("workshop_name is null");
            return (Criteria) this;
        }

        public Criteria andWorkshopNameIsNotNull() {
            addCriterion("workshop_name is not null");
            return (Criteria) this;
        }

        public Criteria andWorkshopNameEqualTo(String value) {
            addCriterion("workshop_name =", value, "workshopName");
            return (Criteria) this;
        }

        public Criteria andWorkshopNameNotEqualTo(String value) {
            addCriterion("workshop_name <>", value, "workshopName");
            return (Criteria) this;
        }

        public Criteria andWorkshopNameGreaterThan(String value) {
            addCriterion("workshop_name >", value, "workshopName");
            return (Criteria) this;
        }

        public Criteria andWorkshopNameGreaterThanOrEqualTo(String value) {
            addCriterion("workshop_name >=", value, "workshopName");
            return (Criteria) this;
        }

        public Criteria andWorkshopNameLessThan(String value) {
            addCriterion("workshop_name <", value, "workshopName");
            return (Criteria) this;
        }

        public Criteria andWorkshopNameLessThanOrEqualTo(String value) {
            addCriterion("workshop_name <=", value, "workshopName");
            return (Criteria) this;
        }

        public Criteria andWorkshopNameLike(String value) {
            addCriterion("workshop_name like", value, "workshopName");
            return (Criteria) this;
        }

        public Criteria andWorkshopNameNotLike(String value) {
            addCriterion("workshop_name not like", value, "workshopName");
            return (Criteria) this;
        }

        public Criteria andWorkshopNameIn(List<String> values) {
            addCriterion("workshop_name in", values, "workshopName");
            return (Criteria) this;
        }

        public Criteria andWorkshopNameNotIn(List<String> values) {
            addCriterion("workshop_name not in", values, "workshopName");
            return (Criteria) this;
        }

        public Criteria andWorkshopNameBetween(String value1, String value2) {
            addCriterion("workshop_name between", value1, value2, "workshopName");
            return (Criteria) this;
        }

        public Criteria andWorkshopNameNotBetween(String value1, String value2) {
            addCriterion("workshop_name not between", value1, value2, "workshopName");
            return (Criteria) this;
        }

        public Criteria andScheduleStartTimeIsNull() {
            addCriterion("schedule_start_time is null");
            return (Criteria) this;
        }

        public Criteria andScheduleStartTimeIsNotNull() {
            addCriterion("schedule_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andScheduleStartTimeEqualTo(Date value) {
            addCriterion("schedule_start_time =", value, "scheduleStartTime");
            return (Criteria) this;
        }

        public Criteria andScheduleStartTimeNotEqualTo(Date value) {
            addCriterion("schedule_start_time <>", value, "scheduleStartTime");
            return (Criteria) this;
        }

        public Criteria andScheduleStartTimeGreaterThan(Date value) {
            addCriterion("schedule_start_time >", value, "scheduleStartTime");
            return (Criteria) this;
        }

        public Criteria andScheduleStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("schedule_start_time >=", value, "scheduleStartTime");
            return (Criteria) this;
        }

        public Criteria andScheduleStartTimeLessThan(Date value) {
            addCriterion("schedule_start_time <", value, "scheduleStartTime");
            return (Criteria) this;
        }

        public Criteria andScheduleStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("schedule_start_time <=", value, "scheduleStartTime");
            return (Criteria) this;
        }

        public Criteria andScheduleStartTimeIn(List<Date> values) {
            addCriterion("schedule_start_time in", values, "scheduleStartTime");
            return (Criteria) this;
        }

        public Criteria andScheduleStartTimeNotIn(List<Date> values) {
            addCriterion("schedule_start_time not in", values, "scheduleStartTime");
            return (Criteria) this;
        }

        public Criteria andScheduleStartTimeBetween(Date value1, Date value2) {
            addCriterion("schedule_start_time between", value1, value2, "scheduleStartTime");
            return (Criteria) this;
        }

        public Criteria andScheduleStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("schedule_start_time not between", value1, value2, "scheduleStartTime");
            return (Criteria) this;
        }

        public Criteria andScheduleEndTimeIsNull() {
            addCriterion("schedule_end_time is null");
            return (Criteria) this;
        }

        public Criteria andScheduleEndTimeIsNotNull() {
            addCriterion("schedule_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andScheduleEndTimeEqualTo(Date value) {
            addCriterion("schedule_end_time =", value, "scheduleEndTime");
            return (Criteria) this;
        }

        public Criteria andScheduleEndTimeNotEqualTo(Date value) {
            addCriterion("schedule_end_time <>", value, "scheduleEndTime");
            return (Criteria) this;
        }

        public Criteria andScheduleEndTimeGreaterThan(Date value) {
            addCriterion("schedule_end_time >", value, "scheduleEndTime");
            return (Criteria) this;
        }

        public Criteria andScheduleEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("schedule_end_time >=", value, "scheduleEndTime");
            return (Criteria) this;
        }

        public Criteria andScheduleEndTimeLessThan(Date value) {
            addCriterion("schedule_end_time <", value, "scheduleEndTime");
            return (Criteria) this;
        }

        public Criteria andScheduleEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("schedule_end_time <=", value, "scheduleEndTime");
            return (Criteria) this;
        }

        public Criteria andScheduleEndTimeIn(List<Date> values) {
            addCriterion("schedule_end_time in", values, "scheduleEndTime");
            return (Criteria) this;
        }

        public Criteria andScheduleEndTimeNotIn(List<Date> values) {
            addCriterion("schedule_end_time not in", values, "scheduleEndTime");
            return (Criteria) this;
        }

        public Criteria andScheduleEndTimeBetween(Date value1, Date value2) {
            addCriterion("schedule_end_time between", value1, value2, "scheduleEndTime");
            return (Criteria) this;
        }

        public Criteria andScheduleEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("schedule_end_time not between", value1, value2, "scheduleEndTime");
            return (Criteria) this;
        }

        public Criteria andCalcDayIsNull() {
            addCriterion("calc_day is null");
            return (Criteria) this;
        }

        public Criteria andCalcDayIsNotNull() {
            addCriterion("calc_day is not null");
            return (Criteria) this;
        }

        public Criteria andCalcDayEqualTo(Date value) {
            addCriterionForJDBCDate("calc_day =", value, "calcDay");
            return (Criteria) this;
        }

        public Criteria andCalcDayNotEqualTo(Date value) {
            addCriterionForJDBCDate("calc_day <>", value, "calcDay");
            return (Criteria) this;
        }

        public Criteria andCalcDayGreaterThan(Date value) {
            addCriterionForJDBCDate("calc_day >", value, "calcDay");
            return (Criteria) this;
        }

        public Criteria andCalcDayGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("calc_day >=", value, "calcDay");
            return (Criteria) this;
        }

        public Criteria andCalcDayLessThan(Date value) {
            addCriterionForJDBCDate("calc_day <", value, "calcDay");
            return (Criteria) this;
        }

        public Criteria andCalcDayLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("calc_day <=", value, "calcDay");
            return (Criteria) this;
        }

        public Criteria andCalcDayIn(List<Date> values) {
            addCriterionForJDBCDate("calc_day in", values, "calcDay");
            return (Criteria) this;
        }

        public Criteria andCalcDayNotIn(List<Date> values) {
            addCriterionForJDBCDate("calc_day not in", values, "calcDay");
            return (Criteria) this;
        }

        public Criteria andCalcDayBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("calc_day between", value1, value2, "calcDay");
            return (Criteria) this;
        }

        public Criteria andCalcDayNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("calc_day not between", value1, value2, "calcDay");
            return (Criteria) this;
        }

        public Criteria andPreCarNumberIsNull() {
            addCriterion("pre_car_number is null");
            return (Criteria) this;
        }

        public Criteria andPreCarNumberIsNotNull() {
            addCriterion("pre_car_number is not null");
            return (Criteria) this;
        }

        public Criteria andPreCarNumberEqualTo(String value) {
            addCriterion("pre_car_number =", value, "preCarNumber");
            return (Criteria) this;
        }

        public Criteria andPreCarNumberNotEqualTo(String value) {
            addCriterion("pre_car_number <>", value, "preCarNumber");
            return (Criteria) this;
        }

        public Criteria andPreCarNumberGreaterThan(String value) {
            addCriterion("pre_car_number >", value, "preCarNumber");
            return (Criteria) this;
        }

        public Criteria andPreCarNumberGreaterThanOrEqualTo(String value) {
            addCriterion("pre_car_number >=", value, "preCarNumber");
            return (Criteria) this;
        }

        public Criteria andPreCarNumberLessThan(String value) {
            addCriterion("pre_car_number <", value, "preCarNumber");
            return (Criteria) this;
        }

        public Criteria andPreCarNumberLessThanOrEqualTo(String value) {
            addCriterion("pre_car_number <=", value, "preCarNumber");
            return (Criteria) this;
        }

        public Criteria andPreCarNumberLike(String value) {
            addCriterion("pre_car_number like", value, "preCarNumber");
            return (Criteria) this;
        }

        public Criteria andPreCarNumberNotLike(String value) {
            addCriterion("pre_car_number not like", value, "preCarNumber");
            return (Criteria) this;
        }

        public Criteria andPreCarNumberIn(List<String> values) {
            addCriterion("pre_car_number in", values, "preCarNumber");
            return (Criteria) this;
        }

        public Criteria andPreCarNumberNotIn(List<String> values) {
            addCriterion("pre_car_number not in", values, "preCarNumber");
            return (Criteria) this;
        }

        public Criteria andPreCarNumberBetween(String value1, String value2) {
            addCriterion("pre_car_number between", value1, value2, "preCarNumber");
            return (Criteria) this;
        }

        public Criteria andPreCarNumberNotBetween(String value1, String value2) {
            addCriterion("pre_car_number not between", value1, value2, "preCarNumber");
            return (Criteria) this;
        }

        public Criteria andCraftTypeIsNull() {
            addCriterion("craft_type is null");
            return (Criteria) this;
        }

        public Criteria andCraftTypeIsNotNull() {
            addCriterion("craft_type is not null");
            return (Criteria) this;
        }

        public Criteria andCraftTypeEqualTo(Long value) {
            addCriterion("craft_type =", value, "craftType");
            return (Criteria) this;
        }

        public Criteria andCraftTypeNotEqualTo(Long value) {
            addCriterion("craft_type <>", value, "craftType");
            return (Criteria) this;
        }

        public Criteria andCraftTypeGreaterThan(Long value) {
            addCriterion("craft_type >", value, "craftType");
            return (Criteria) this;
        }

        public Criteria andCraftTypeGreaterThanOrEqualTo(Long value) {
            addCriterion("craft_type >=", value, "craftType");
            return (Criteria) this;
        }

        public Criteria andCraftTypeLessThan(Long value) {
            addCriterion("craft_type <", value, "craftType");
            return (Criteria) this;
        }

        public Criteria andCraftTypeLessThanOrEqualTo(Long value) {
            addCriterion("craft_type <=", value, "craftType");
            return (Criteria) this;
        }

        public Criteria andCraftTypeIn(List<Long> values) {
            addCriterion("craft_type in", values, "craftType");
            return (Criteria) this;
        }

        public Criteria andCraftTypeNotIn(List<Long> values) {
            addCriterion("craft_type not in", values, "craftType");
            return (Criteria) this;
        }

        public Criteria andCraftTypeBetween(Long value1, Long value2) {
            addCriterion("craft_type between", value1, value2, "craftType");
            return (Criteria) this;
        }

        public Criteria andCraftTypeNotBetween(Long value1, Long value2) {
            addCriterion("craft_type not between", value1, value2, "craftType");
            return (Criteria) this;
        }

        public Criteria andPreSortIsNull() {
            addCriterion("pre_sort is null");
            return (Criteria) this;
        }

        public Criteria andPreSortIsNotNull() {
            addCriterion("pre_sort is not null");
            return (Criteria) this;
        }

        public Criteria andPreSortEqualTo(Integer value) {
            addCriterion("pre_sort =", value, "preSort");
            return (Criteria) this;
        }

        public Criteria andPreSortNotEqualTo(Integer value) {
            addCriterion("pre_sort <>", value, "preSort");
            return (Criteria) this;
        }

        public Criteria andPreSortGreaterThan(Integer value) {
            addCriterion("pre_sort >", value, "preSort");
            return (Criteria) this;
        }

        public Criteria andPreSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("pre_sort >=", value, "preSort");
            return (Criteria) this;
        }

        public Criteria andPreSortLessThan(Integer value) {
            addCriterion("pre_sort <", value, "preSort");
            return (Criteria) this;
        }

        public Criteria andPreSortLessThanOrEqualTo(Integer value) {
            addCriterion("pre_sort <=", value, "preSort");
            return (Criteria) this;
        }

        public Criteria andPreSortIn(List<Integer> values) {
            addCriterion("pre_sort in", values, "preSort");
            return (Criteria) this;
        }

        public Criteria andPreSortNotIn(List<Integer> values) {
            addCriterion("pre_sort not in", values, "preSort");
            return (Criteria) this;
        }

        public Criteria andPreSortBetween(Integer value1, Integer value2) {
            addCriterion("pre_sort between", value1, value2, "preSort");
            return (Criteria) this;
        }

        public Criteria andPreSortNotBetween(Integer value1, Integer value2) {
            addCriterion("pre_sort not between", value1, value2, "preSort");
            return (Criteria) this;
        }

        public Criteria andSpecificationIsNull() {
            addCriterion("specification is null");
            return (Criteria) this;
        }

        public Criteria andSpecificationIsNotNull() {
            addCriterion("specification is not null");
            return (Criteria) this;
        }

        public Criteria andSpecificationEqualTo(String value) {
            addCriterion("specification =", value, "specification");
            return (Criteria) this;
        }

        public Criteria andSpecificationNotEqualTo(String value) {
            addCriterion("specification <>", value, "specification");
            return (Criteria) this;
        }

        public Criteria andSpecificationGreaterThan(String value) {
            addCriterion("specification >", value, "specification");
            return (Criteria) this;
        }

        public Criteria andSpecificationGreaterThanOrEqualTo(String value) {
            addCriterion("specification >=", value, "specification");
            return (Criteria) this;
        }

        public Criteria andSpecificationLessThan(String value) {
            addCriterion("specification <", value, "specification");
            return (Criteria) this;
        }

        public Criteria andSpecificationLessThanOrEqualTo(String value) {
            addCriterion("specification <=", value, "specification");
            return (Criteria) this;
        }

        public Criteria andSpecificationLike(String value) {
            addCriterion("specification like", value, "specification");
            return (Criteria) this;
        }

        public Criteria andSpecificationNotLike(String value) {
            addCriterion("specification not like", value, "specification");
            return (Criteria) this;
        }

        public Criteria andSpecificationIn(List<String> values) {
            addCriterion("specification in", values, "specification");
            return (Criteria) this;
        }

        public Criteria andSpecificationNotIn(List<String> values) {
            addCriterion("specification not in", values, "specification");
            return (Criteria) this;
        }

        public Criteria andSpecificationBetween(String value1, String value2) {
            addCriterion("specification between", value1, value2, "specification");
            return (Criteria) this;
        }

        public Criteria andSpecificationNotBetween(String value1, String value2) {
            addCriterion("specification not between", value1, value2, "specification");
            return (Criteria) this;
        }

        public Criteria andStyleIsNull() {
            addCriterion("style is null");
            return (Criteria) this;
        }

        public Criteria andStyleIsNotNull() {
            addCriterion("style is not null");
            return (Criteria) this;
        }

        public Criteria andStyleEqualTo(String value) {
            addCriterion("style =", value, "style");
            return (Criteria) this;
        }

        public Criteria andStyleNotEqualTo(String value) {
            addCriterion("style <>", value, "style");
            return (Criteria) this;
        }

        public Criteria andStyleGreaterThan(String value) {
            addCriterion("style >", value, "style");
            return (Criteria) this;
        }

        public Criteria andStyleGreaterThanOrEqualTo(String value) {
            addCriterion("style >=", value, "style");
            return (Criteria) this;
        }

        public Criteria andStyleLessThan(String value) {
            addCriterion("style <", value, "style");
            return (Criteria) this;
        }

        public Criteria andStyleLessThanOrEqualTo(String value) {
            addCriterion("style <=", value, "style");
            return (Criteria) this;
        }

        public Criteria andStyleLike(String value) {
            addCriterion("style like", value, "style");
            return (Criteria) this;
        }

        public Criteria andStyleNotLike(String value) {
            addCriterion("style not like", value, "style");
            return (Criteria) this;
        }

        public Criteria andStyleIn(List<String> values) {
            addCriterion("style in", values, "style");
            return (Criteria) this;
        }

        public Criteria andStyleNotIn(List<String> values) {
            addCriterion("style not in", values, "style");
            return (Criteria) this;
        }

        public Criteria andStyleBetween(String value1, String value2) {
            addCriterion("style between", value1, value2, "style");
            return (Criteria) this;
        }

        public Criteria andStyleNotBetween(String value1, String value2) {
            addCriterion("style not between", value1, value2, "style");
            return (Criteria) this;
        }

        public Criteria andPreOperatorIsNull() {
            addCriterion("pre_operator is null");
            return (Criteria) this;
        }

        public Criteria andPreOperatorIsNotNull() {
            addCriterion("pre_operator is not null");
            return (Criteria) this;
        }

        public Criteria andPreOperatorEqualTo(String value) {
            addCriterion("pre_operator =", value, "preOperator");
            return (Criteria) this;
        }

        public Criteria andPreOperatorNotEqualTo(String value) {
            addCriterion("pre_operator <>", value, "preOperator");
            return (Criteria) this;
        }

        public Criteria andPreOperatorGreaterThan(String value) {
            addCriterion("pre_operator >", value, "preOperator");
            return (Criteria) this;
        }

        public Criteria andPreOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("pre_operator >=", value, "preOperator");
            return (Criteria) this;
        }

        public Criteria andPreOperatorLessThan(String value) {
            addCriterion("pre_operator <", value, "preOperator");
            return (Criteria) this;
        }

        public Criteria andPreOperatorLessThanOrEqualTo(String value) {
            addCriterion("pre_operator <=", value, "preOperator");
            return (Criteria) this;
        }

        public Criteria andPreOperatorLike(String value) {
            addCriterion("pre_operator like", value, "preOperator");
            return (Criteria) this;
        }

        public Criteria andPreOperatorNotLike(String value) {
            addCriterion("pre_operator not like", value, "preOperator");
            return (Criteria) this;
        }

        public Criteria andPreOperatorIn(List<String> values) {
            addCriterion("pre_operator in", values, "preOperator");
            return (Criteria) this;
        }

        public Criteria andPreOperatorNotIn(List<String> values) {
            addCriterion("pre_operator not in", values, "preOperator");
            return (Criteria) this;
        }

        public Criteria andPreOperatorBetween(String value1, String value2) {
            addCriterion("pre_operator between", value1, value2, "preOperator");
            return (Criteria) this;
        }

        public Criteria andPreOperatorNotBetween(String value1, String value2) {
            addCriterion("pre_operator not between", value1, value2, "preOperator");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andFrontOperatorIsNull() {
            addCriterion("front_operator is null");
            return (Criteria) this;
        }

        public Criteria andFrontOperatorIsNotNull() {
            addCriterion("front_operator is not null");
            return (Criteria) this;
        }

        public Criteria andFrontOperatorEqualTo(String value) {
            addCriterion("front_operator =", value, "frontOperator");
            return (Criteria) this;
        }

        public Criteria andFrontOperatorNotEqualTo(String value) {
            addCriterion("front_operator <>", value, "frontOperator");
            return (Criteria) this;
        }

        public Criteria andFrontOperatorGreaterThan(String value) {
            addCriterion("front_operator >", value, "frontOperator");
            return (Criteria) this;
        }

        public Criteria andFrontOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("front_operator >=", value, "frontOperator");
            return (Criteria) this;
        }

        public Criteria andFrontOperatorLessThan(String value) {
            addCriterion("front_operator <", value, "frontOperator");
            return (Criteria) this;
        }

        public Criteria andFrontOperatorLessThanOrEqualTo(String value) {
            addCriterion("front_operator <=", value, "frontOperator");
            return (Criteria) this;
        }

        public Criteria andFrontOperatorLike(String value) {
            addCriterion("front_operator like", value, "frontOperator");
            return (Criteria) this;
        }

        public Criteria andFrontOperatorNotLike(String value) {
            addCriterion("front_operator not like", value, "frontOperator");
            return (Criteria) this;
        }

        public Criteria andFrontOperatorIn(List<String> values) {
            addCriterion("front_operator in", values, "frontOperator");
            return (Criteria) this;
        }

        public Criteria andFrontOperatorNotIn(List<String> values) {
            addCriterion("front_operator not in", values, "frontOperator");
            return (Criteria) this;
        }

        public Criteria andFrontOperatorBetween(String value1, String value2) {
            addCriterion("front_operator between", value1, value2, "frontOperator");
            return (Criteria) this;
        }

        public Criteria andFrontOperatorNotBetween(String value1, String value2) {
            addCriterion("front_operator not between", value1, value2, "frontOperator");
            return (Criteria) this;
        }

        public Criteria andActualMetersIsNull() {
            addCriterion("actual_meters is null");
            return (Criteria) this;
        }

        public Criteria andActualMetersIsNotNull() {
            addCriterion("actual_meters is not null");
            return (Criteria) this;
        }

        public Criteria andActualMetersEqualTo(BigDecimal value) {
            addCriterion("actual_meters =", value, "actualMeters");
            return (Criteria) this;
        }

        public Criteria andActualMetersNotEqualTo(BigDecimal value) {
            addCriterion("actual_meters <>", value, "actualMeters");
            return (Criteria) this;
        }

        public Criteria andActualMetersGreaterThan(BigDecimal value) {
            addCriterion("actual_meters >", value, "actualMeters");
            return (Criteria) this;
        }

        public Criteria andActualMetersGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("actual_meters >=", value, "actualMeters");
            return (Criteria) this;
        }

        public Criteria andActualMetersLessThan(BigDecimal value) {
            addCriterion("actual_meters <", value, "actualMeters");
            return (Criteria) this;
        }

        public Criteria andActualMetersLessThanOrEqualTo(BigDecimal value) {
            addCriterion("actual_meters <=", value, "actualMeters");
            return (Criteria) this;
        }

        public Criteria andActualMetersIn(List<BigDecimal> values) {
            addCriterion("actual_meters in", values, "actualMeters");
            return (Criteria) this;
        }

        public Criteria andActualMetersNotIn(List<BigDecimal> values) {
            addCriterion("actual_meters not in", values, "actualMeters");
            return (Criteria) this;
        }

        public Criteria andActualMetersBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actual_meters between", value1, value2, "actualMeters");
            return (Criteria) this;
        }

        public Criteria andActualMetersNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("actual_meters not between", value1, value2, "actualMeters");
            return (Criteria) this;
        }

        public Criteria andMatchesIsNull() {
            addCriterion("matches is null");
            return (Criteria) this;
        }

        public Criteria andMatchesIsNotNull() {
            addCriterion("matches is not null");
            return (Criteria) this;
        }

        public Criteria andMatchesEqualTo(String value) {
            addCriterion("matches =", value, "matches");
            return (Criteria) this;
        }

        public Criteria andMatchesNotEqualTo(String value) {
            addCriterion("matches <>", value, "matches");
            return (Criteria) this;
        }

        public Criteria andMatchesGreaterThan(String value) {
            addCriterion("matches >", value, "matches");
            return (Criteria) this;
        }

        public Criteria andMatchesGreaterThanOrEqualTo(String value) {
            addCriterion("matches >=", value, "matches");
            return (Criteria) this;
        }

        public Criteria andMatchesLessThan(String value) {
            addCriterion("matches <", value, "matches");
            return (Criteria) this;
        }

        public Criteria andMatchesLessThanOrEqualTo(String value) {
            addCriterion("matches <=", value, "matches");
            return (Criteria) this;
        }

        public Criteria andMatchesLike(String value) {
            addCriterion("matches like", value, "matches");
            return (Criteria) this;
        }

        public Criteria andMatchesNotLike(String value) {
            addCriterion("matches not like", value, "matches");
            return (Criteria) this;
        }

        public Criteria andMatchesIn(List<String> values) {
            addCriterion("matches in", values, "matches");
            return (Criteria) this;
        }

        public Criteria andMatchesNotIn(List<String> values) {
            addCriterion("matches not in", values, "matches");
            return (Criteria) this;
        }

        public Criteria andMatchesBetween(String value1, String value2) {
            addCriterion("matches between", value1, value2, "matches");
            return (Criteria) this;
        }

        public Criteria andMatchesNotBetween(String value1, String value2) {
            addCriterion("matches not between", value1, value2, "matches");
            return (Criteria) this;
        }

        public Criteria andExpectedTimeIsNull() {
            addCriterion("expected_time is null");
            return (Criteria) this;
        }

        public Criteria andExpectedTimeIsNotNull() {
            addCriterion("expected_time is not null");
            return (Criteria) this;
        }

        public Criteria andExpectedTimeEqualTo(String value) {
            addCriterion("expected_time =", value, "expectedTime");
            return (Criteria) this;
        }

        public Criteria andExpectedTimeNotEqualTo(String value) {
            addCriterion("expected_time <>", value, "expectedTime");
            return (Criteria) this;
        }

        public Criteria andExpectedTimeGreaterThan(String value) {
            addCriterion("expected_time >", value, "expectedTime");
            return (Criteria) this;
        }

        public Criteria andExpectedTimeGreaterThanOrEqualTo(String value) {
            addCriterion("expected_time >=", value, "expectedTime");
            return (Criteria) this;
        }

        public Criteria andExpectedTimeLessThan(String value) {
            addCriterion("expected_time <", value, "expectedTime");
            return (Criteria) this;
        }

        public Criteria andExpectedTimeLessThanOrEqualTo(String value) {
            addCriterion("expected_time <=", value, "expectedTime");
            return (Criteria) this;
        }

        public Criteria andExpectedTimeLike(String value) {
            addCriterion("expected_time like", value, "expectedTime");
            return (Criteria) this;
        }

        public Criteria andExpectedTimeNotLike(String value) {
            addCriterion("expected_time not like", value, "expectedTime");
            return (Criteria) this;
        }

        public Criteria andExpectedTimeIn(List<String> values) {
            addCriterion("expected_time in", values, "expectedTime");
            return (Criteria) this;
        }

        public Criteria andExpectedTimeNotIn(List<String> values) {
            addCriterion("expected_time not in", values, "expectedTime");
            return (Criteria) this;
        }

        public Criteria andExpectedTimeBetween(String value1, String value2) {
            addCriterion("expected_time between", value1, value2, "expectedTime");
            return (Criteria) this;
        }

        public Criteria andExpectedTimeNotBetween(String value1, String value2) {
            addCriterion("expected_time not between", value1, value2, "expectedTime");
            return (Criteria) this;
        }

        public Criteria andActualTimeIsNull() {
            addCriterion("actual_time is null");
            return (Criteria) this;
        }

        public Criteria andActualTimeIsNotNull() {
            addCriterion("actual_time is not null");
            return (Criteria) this;
        }

        public Criteria andActualTimeEqualTo(String value) {
            addCriterion("actual_time =", value, "actualTime");
            return (Criteria) this;
        }

        public Criteria andActualTimeNotEqualTo(String value) {
            addCriterion("actual_time <>", value, "actualTime");
            return (Criteria) this;
        }

        public Criteria andActualTimeGreaterThan(String value) {
            addCriterion("actual_time >", value, "actualTime");
            return (Criteria) this;
        }

        public Criteria andActualTimeGreaterThanOrEqualTo(String value) {
            addCriterion("actual_time >=", value, "actualTime");
            return (Criteria) this;
        }

        public Criteria andActualTimeLessThan(String value) {
            addCriterion("actual_time <", value, "actualTime");
            return (Criteria) this;
        }

        public Criteria andActualTimeLessThanOrEqualTo(String value) {
            addCriterion("actual_time <=", value, "actualTime");
            return (Criteria) this;
        }

        public Criteria andActualTimeLike(String value) {
            addCriterion("actual_time like", value, "actualTime");
            return (Criteria) this;
        }

        public Criteria andActualTimeNotLike(String value) {
            addCriterion("actual_time not like", value, "actualTime");
            return (Criteria) this;
        }

        public Criteria andActualTimeIn(List<String> values) {
            addCriterion("actual_time in", values, "actualTime");
            return (Criteria) this;
        }

        public Criteria andActualTimeNotIn(List<String> values) {
            addCriterion("actual_time not in", values, "actualTime");
            return (Criteria) this;
        }

        public Criteria andActualTimeBetween(String value1, String value2) {
            addCriterion("actual_time between", value1, value2, "actualTime");
            return (Criteria) this;
        }

        public Criteria andActualTimeNotBetween(String value1, String value2) {
            addCriterion("actual_time not between", value1, value2, "actualTime");
            return (Criteria) this;
        }

        public Criteria andExceptionTypeIsNull() {
            addCriterion("exception_type is null");
            return (Criteria) this;
        }

        public Criteria andExceptionTypeIsNotNull() {
            addCriterion("exception_type is not null");
            return (Criteria) this;
        }

        public Criteria andExceptionTypeEqualTo(Integer value) {
            addCriterion("exception_type =", value, "exceptionType");
            return (Criteria) this;
        }

        public Criteria andExceptionTypeNotEqualTo(Integer value) {
            addCriterion("exception_type <>", value, "exceptionType");
            return (Criteria) this;
        }

        public Criteria andExceptionTypeGreaterThan(Integer value) {
            addCriterion("exception_type >", value, "exceptionType");
            return (Criteria) this;
        }

        public Criteria andExceptionTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("exception_type >=", value, "exceptionType");
            return (Criteria) this;
        }

        public Criteria andExceptionTypeLessThan(Integer value) {
            addCriterion("exception_type <", value, "exceptionType");
            return (Criteria) this;
        }

        public Criteria andExceptionTypeLessThanOrEqualTo(Integer value) {
            addCriterion("exception_type <=", value, "exceptionType");
            return (Criteria) this;
        }

        public Criteria andExceptionTypeIn(List<Integer> values) {
            addCriterion("exception_type in", values, "exceptionType");
            return (Criteria) this;
        }

        public Criteria andExceptionTypeNotIn(List<Integer> values) {
            addCriterion("exception_type not in", values, "exceptionType");
            return (Criteria) this;
        }

        public Criteria andExceptionTypeBetween(Integer value1, Integer value2) {
            addCriterion("exception_type between", value1, value2, "exceptionType");
            return (Criteria) this;
        }

        public Criteria andExceptionTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("exception_type not between", value1, value2, "exceptionType");
            return (Criteria) this;
        }

        public Criteria andProductWeightMinIsNull() {
            addCriterion("product_weight_min is null");
            return (Criteria) this;
        }

        public Criteria andProductWeightMinIsNotNull() {
            addCriterion("product_weight_min is not null");
            return (Criteria) this;
        }

        public Criteria andProductWeightMinEqualTo(String value) {
            addCriterion("product_weight_min =", value, "productWeightMin");
            return (Criteria) this;
        }

        public Criteria andProductWeightMinNotEqualTo(String value) {
            addCriterion("product_weight_min <>", value, "productWeightMin");
            return (Criteria) this;
        }

        public Criteria andProductWeightMinGreaterThan(String value) {
            addCriterion("product_weight_min >", value, "productWeightMin");
            return (Criteria) this;
        }

        public Criteria andProductWeightMinGreaterThanOrEqualTo(String value) {
            addCriterion("product_weight_min >=", value, "productWeightMin");
            return (Criteria) this;
        }

        public Criteria andProductWeightMinLessThan(String value) {
            addCriterion("product_weight_min <", value, "productWeightMin");
            return (Criteria) this;
        }

        public Criteria andProductWeightMinLessThanOrEqualTo(String value) {
            addCriterion("product_weight_min <=", value, "productWeightMin");
            return (Criteria) this;
        }

        public Criteria andProductWeightMinLike(String value) {
            addCriterion("product_weight_min like", value, "productWeightMin");
            return (Criteria) this;
        }

        public Criteria andProductWeightMinNotLike(String value) {
            addCriterion("product_weight_min not like", value, "productWeightMin");
            return (Criteria) this;
        }

        public Criteria andProductWeightMinIn(List<String> values) {
            addCriterion("product_weight_min in", values, "productWeightMin");
            return (Criteria) this;
        }

        public Criteria andProductWeightMinNotIn(List<String> values) {
            addCriterion("product_weight_min not in", values, "productWeightMin");
            return (Criteria) this;
        }

        public Criteria andProductWeightMinBetween(String value1, String value2) {
            addCriterion("product_weight_min between", value1, value2, "productWeightMin");
            return (Criteria) this;
        }

        public Criteria andProductWeightMinNotBetween(String value1, String value2) {
            addCriterion("product_weight_min not between", value1, value2, "productWeightMin");
            return (Criteria) this;
        }

        public Criteria andProductWeightMaxIsNull() {
            addCriterion("product_weight_max is null");
            return (Criteria) this;
        }

        public Criteria andProductWeightMaxIsNotNull() {
            addCriterion("product_weight_max is not null");
            return (Criteria) this;
        }

        public Criteria andProductWeightMaxEqualTo(String value) {
            addCriterion("product_weight_max =", value, "productWeightMax");
            return (Criteria) this;
        }

        public Criteria andProductWeightMaxNotEqualTo(String value) {
            addCriterion("product_weight_max <>", value, "productWeightMax");
            return (Criteria) this;
        }

        public Criteria andProductWeightMaxGreaterThan(String value) {
            addCriterion("product_weight_max >", value, "productWeightMax");
            return (Criteria) this;
        }

        public Criteria andProductWeightMaxGreaterThanOrEqualTo(String value) {
            addCriterion("product_weight_max >=", value, "productWeightMax");
            return (Criteria) this;
        }

        public Criteria andProductWeightMaxLessThan(String value) {
            addCriterion("product_weight_max <", value, "productWeightMax");
            return (Criteria) this;
        }

        public Criteria andProductWeightMaxLessThanOrEqualTo(String value) {
            addCriterion("product_weight_max <=", value, "productWeightMax");
            return (Criteria) this;
        }

        public Criteria andProductWeightMaxLike(String value) {
            addCriterion("product_weight_max like", value, "productWeightMax");
            return (Criteria) this;
        }

        public Criteria andProductWeightMaxNotLike(String value) {
            addCriterion("product_weight_max not like", value, "productWeightMax");
            return (Criteria) this;
        }

        public Criteria andProductWeightMaxIn(List<String> values) {
            addCriterion("product_weight_max in", values, "productWeightMax");
            return (Criteria) this;
        }

        public Criteria andProductWeightMaxNotIn(List<String> values) {
            addCriterion("product_weight_max not in", values, "productWeightMax");
            return (Criteria) this;
        }

        public Criteria andProductWeightMaxBetween(String value1, String value2) {
            addCriterion("product_weight_max between", value1, value2, "productWeightMax");
            return (Criteria) this;
        }

        public Criteria andProductWeightMaxNotBetween(String value1, String value2) {
            addCriterion("product_weight_max not between", value1, value2, "productWeightMax");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andIsBatchIsNull() {
            addCriterion("is_batch is null");
            return (Criteria) this;
        }

        public Criteria andIsBatchIsNotNull() {
            addCriterion("is_batch is not null");
            return (Criteria) this;
        }

        public Criteria andIsBatchEqualTo(Byte value) {
            addCriterion("is_batch =", value, "isBatch");
            return (Criteria) this;
        }

        public Criteria andIsBatchNotEqualTo(Byte value) {
            addCriterion("is_batch <>", value, "isBatch");
            return (Criteria) this;
        }

        public Criteria andIsBatchGreaterThan(Byte value) {
            addCriterion("is_batch >", value, "isBatch");
            return (Criteria) this;
        }

        public Criteria andIsBatchGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_batch >=", value, "isBatch");
            return (Criteria) this;
        }

        public Criteria andIsBatchLessThan(Byte value) {
            addCriterion("is_batch <", value, "isBatch");
            return (Criteria) this;
        }

        public Criteria andIsBatchLessThanOrEqualTo(Byte value) {
            addCriterion("is_batch <=", value, "isBatch");
            return (Criteria) this;
        }

        public Criteria andIsBatchIn(List<Byte> values) {
            addCriterion("is_batch in", values, "isBatch");
            return (Criteria) this;
        }

        public Criteria andIsBatchNotIn(List<Byte> values) {
            addCriterion("is_batch not in", values, "isBatch");
            return (Criteria) this;
        }

        public Criteria andIsBatchBetween(Byte value1, Byte value2) {
            addCriterion("is_batch between", value1, value2, "isBatch");
            return (Criteria) this;
        }

        public Criteria andIsBatchNotBetween(Byte value1, Byte value2) {
            addCriterion("is_batch not between", value1, value2, "isBatch");
            return (Criteria) this;
        }

        public Criteria andProcessNameIsNull() {
            addCriterion("process_name is null");
            return (Criteria) this;
        }

        public Criteria andProcessNameIsNotNull() {
            addCriterion("process_name is not null");
            return (Criteria) this;
        }

        public Criteria andProcessNameEqualTo(String value) {
            addCriterion("process_name =", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameNotEqualTo(String value) {
            addCriterion("process_name <>", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameGreaterThan(String value) {
            addCriterion("process_name >", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameGreaterThanOrEqualTo(String value) {
            addCriterion("process_name >=", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameLessThan(String value) {
            addCriterion("process_name <", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameLessThanOrEqualTo(String value) {
            addCriterion("process_name <=", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameLike(String value) {
            addCriterion("process_name like", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameNotLike(String value) {
            addCriterion("process_name not like", value, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameIn(List<String> values) {
            addCriterion("process_name in", values, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameNotIn(List<String> values) {
            addCriterion("process_name not in", values, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameBetween(String value1, String value2) {
            addCriterion("process_name between", value1, value2, "processName");
            return (Criteria) this;
        }

        public Criteria andProcessNameNotBetween(String value1, String value2) {
            addCriterion("process_name not between", value1, value2, "processName");
            return (Criteria) this;
        }

        public Criteria andScheduleImportIsNull() {
            addCriterion("schedule_import is null");
            return (Criteria) this;
        }

        public Criteria andScheduleImportIsNotNull() {
            addCriterion("schedule_import is not null");
            return (Criteria) this;
        }

        public Criteria andScheduleImportEqualTo(Byte value) {
            addCriterion("schedule_import =", value, "scheduleImport");
            return (Criteria) this;
        }

        public Criteria andScheduleImportNotEqualTo(Byte value) {
            addCriterion("schedule_import <>", value, "scheduleImport");
            return (Criteria) this;
        }

        public Criteria andScheduleImportGreaterThan(Byte value) {
            addCriterion("schedule_import >", value, "scheduleImport");
            return (Criteria) this;
        }

        public Criteria andScheduleImportGreaterThanOrEqualTo(Byte value) {
            addCriterion("schedule_import >=", value, "scheduleImport");
            return (Criteria) this;
        }

        public Criteria andScheduleImportLessThan(Byte value) {
            addCriterion("schedule_import <", value, "scheduleImport");
            return (Criteria) this;
        }

        public Criteria andScheduleImportLessThanOrEqualTo(Byte value) {
            addCriterion("schedule_import <=", value, "scheduleImport");
            return (Criteria) this;
        }

        public Criteria andScheduleImportIn(List<Byte> values) {
            addCriterion("schedule_import in", values, "scheduleImport");
            return (Criteria) this;
        }

        public Criteria andScheduleImportNotIn(List<Byte> values) {
            addCriterion("schedule_import not in", values, "scheduleImport");
            return (Criteria) this;
        }

        public Criteria andScheduleImportBetween(Byte value1, Byte value2) {
            addCriterion("schedule_import between", value1, value2, "scheduleImport");
            return (Criteria) this;
        }

        public Criteria andScheduleImportNotBetween(Byte value1, Byte value2) {
            addCriterion("schedule_import not between", value1, value2, "scheduleImport");
            return (Criteria) this;
        }

        public Criteria andQualityStatusIsNull() {
            addCriterion("quality_status is null");
            return (Criteria) this;
        }

        public Criteria andQualityStatusIsNotNull() {
            addCriterion("quality_status is not null");
            return (Criteria) this;
        }

        public Criteria andQualityStatusEqualTo(String value) {
            addCriterion("quality_status =", value, "qualityStatus");
            return (Criteria) this;
        }

        public Criteria andQualityStatusNotEqualTo(String value) {
            addCriterion("quality_status <>", value, "qualityStatus");
            return (Criteria) this;
        }

        public Criteria andQualityStatusGreaterThan(String value) {
            addCriterion("quality_status >", value, "qualityStatus");
            return (Criteria) this;
        }

        public Criteria andQualityStatusGreaterThanOrEqualTo(String value) {
            addCriterion("quality_status >=", value, "qualityStatus");
            return (Criteria) this;
        }

        public Criteria andQualityStatusLessThan(String value) {
            addCriterion("quality_status <", value, "qualityStatus");
            return (Criteria) this;
        }

        public Criteria andQualityStatusLessThanOrEqualTo(String value) {
            addCriterion("quality_status <=", value, "qualityStatus");
            return (Criteria) this;
        }

        public Criteria andQualityStatusLike(String value) {
            addCriterion("quality_status like", value, "qualityStatus");
            return (Criteria) this;
        }

        public Criteria andQualityStatusNotLike(String value) {
            addCriterion("quality_status not like", value, "qualityStatus");
            return (Criteria) this;
        }

        public Criteria andQualityStatusIn(List<String> values) {
            addCriterion("quality_status in", values, "qualityStatus");
            return (Criteria) this;
        }

        public Criteria andQualityStatusNotIn(List<String> values) {
            addCriterion("quality_status not in", values, "qualityStatus");
            return (Criteria) this;
        }

        public Criteria andQualityStatusBetween(String value1, String value2) {
            addCriterion("quality_status between", value1, value2, "qualityStatus");
            return (Criteria) this;
        }

        public Criteria andQualityStatusNotBetween(String value1, String value2) {
            addCriterion("quality_status not between", value1, value2, "qualityStatus");
            return (Criteria) this;
        }

        public Criteria andProcessCodeIsNull() {
            addCriterion("process_code is null");
            return (Criteria) this;
        }

        public Criteria andProcessCodeIsNotNull() {
            addCriterion("process_code is not null");
            return (Criteria) this;
        }

        public Criteria andProcessCodeEqualTo(String value) {
            addCriterion("process_code =", value, "processCode");
            return (Criteria) this;
        }

        public Criteria andProcessCodeNotEqualTo(String value) {
            addCriterion("process_code <>", value, "processCode");
            return (Criteria) this;
        }

        public Criteria andProcessCodeGreaterThan(String value) {
            addCriterion("process_code >", value, "processCode");
            return (Criteria) this;
        }

        public Criteria andProcessCodeGreaterThanOrEqualTo(String value) {
            addCriterion("process_code >=", value, "processCode");
            return (Criteria) this;
        }

        public Criteria andProcessCodeLessThan(String value) {
            addCriterion("process_code <", value, "processCode");
            return (Criteria) this;
        }

        public Criteria andProcessCodeLessThanOrEqualTo(String value) {
            addCriterion("process_code <=", value, "processCode");
            return (Criteria) this;
        }

        public Criteria andProcessCodeLike(String value) {
            addCriterion("process_code like", value, "processCode");
            return (Criteria) this;
        }

        public Criteria andProcessCodeNotLike(String value) {
            addCriterion("process_code not like", value, "processCode");
            return (Criteria) this;
        }

        public Criteria andProcessCodeIn(List<String> values) {
            addCriterion("process_code in", values, "processCode");
            return (Criteria) this;
        }

        public Criteria andProcessCodeNotIn(List<String> values) {
            addCriterion("process_code not in", values, "processCode");
            return (Criteria) this;
        }

        public Criteria andProcessCodeBetween(String value1, String value2) {
            addCriterion("process_code between", value1, value2, "processCode");
            return (Criteria) this;
        }

        public Criteria andProcessCodeNotBetween(String value1, String value2) {
            addCriterion("process_code not between", value1, value2, "processCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeIsNull() {
            addCriterion("product_code is null");
            return (Criteria) this;
        }

        public Criteria andProductCodeIsNotNull() {
            addCriterion("product_code is not null");
            return (Criteria) this;
        }

        public Criteria andProductCodeEqualTo(String value) {
            addCriterion("product_code =", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotEqualTo(String value) {
            addCriterion("product_code <>", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeGreaterThan(String value) {
            addCriterion("product_code >", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeGreaterThanOrEqualTo(String value) {
            addCriterion("product_code >=", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeLessThan(String value) {
            addCriterion("product_code <", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeLessThanOrEqualTo(String value) {
            addCriterion("product_code <=", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeLike(String value) {
            addCriterion("product_code like", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotLike(String value) {
            addCriterion("product_code not like", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeIn(List<String> values) {
            addCriterion("product_code in", values, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotIn(List<String> values) {
            addCriterion("product_code not in", values, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeBetween(String value1, String value2) {
            addCriterion("product_code between", value1, value2, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotBetween(String value1, String value2) {
            addCriterion("product_code not between", value1, value2, "productCode");
            return (Criteria) this;
        }

        public Criteria andPlatformIsNull() {
            addCriterion("platform is null");
            return (Criteria) this;
        }

        public Criteria andPlatformIsNotNull() {
            addCriterion("platform is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformEqualTo(Integer value) {
            addCriterion("platform =", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotEqualTo(Integer value) {
            addCriterion("platform <>", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformGreaterThan(Integer value) {
            addCriterion("platform >", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformGreaterThanOrEqualTo(Integer value) {
            addCriterion("platform >=", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformLessThan(Integer value) {
            addCriterion("platform <", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformLessThanOrEqualTo(Integer value) {
            addCriterion("platform <=", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformIn(List<Integer> values) {
            addCriterion("platform in", values, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotIn(List<Integer> values) {
            addCriterion("platform not in", values, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformBetween(Integer value1, Integer value2) {
            addCriterion("platform between", value1, value2, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotBetween(Integer value1, Integer value2) {
            addCriterion("platform not between", value1, value2, "platform");
            return (Criteria) this;
        }

        public Criteria andTaskNoIsNull() {
            addCriterion("task_no is null");
            return (Criteria) this;
        }

        public Criteria andTaskNoIsNotNull() {
            addCriterion("task_no is not null");
            return (Criteria) this;
        }

        public Criteria andTaskNoEqualTo(String value) {
            addCriterion("task_no =", value, "taskNo");
            return (Criteria) this;
        }

        public Criteria andTaskNoNotEqualTo(String value) {
            addCriterion("task_no <>", value, "taskNo");
            return (Criteria) this;
        }

        public Criteria andTaskNoGreaterThan(String value) {
            addCriterion("task_no >", value, "taskNo");
            return (Criteria) this;
        }

        public Criteria andTaskNoGreaterThanOrEqualTo(String value) {
            addCriterion("task_no >=", value, "taskNo");
            return (Criteria) this;
        }

        public Criteria andTaskNoLessThan(String value) {
            addCriterion("task_no <", value, "taskNo");
            return (Criteria) this;
        }

        public Criteria andTaskNoLessThanOrEqualTo(String value) {
            addCriterion("task_no <=", value, "taskNo");
            return (Criteria) this;
        }

        public Criteria andTaskNoLike(String value) {
            addCriterion("task_no like", value, "taskNo");
            return (Criteria) this;
        }

        public Criteria andTaskNoNotLike(String value) {
            addCriterion("task_no not like", value, "taskNo");
            return (Criteria) this;
        }

        public Criteria andTaskNoIn(List<String> values) {
            addCriterion("task_no in", values, "taskNo");
            return (Criteria) this;
        }

        public Criteria andTaskNoNotIn(List<String> values) {
            addCriterion("task_no not in", values, "taskNo");
            return (Criteria) this;
        }

        public Criteria andTaskNoBetween(String value1, String value2) {
            addCriterion("task_no between", value1, value2, "taskNo");
            return (Criteria) this;
        }

        public Criteria andTaskNoNotBetween(String value1, String value2) {
            addCriterion("task_no not between", value1, value2, "taskNo");
            return (Criteria) this;
        }

        public Criteria andAssistantExceptionTypeIsNull() {
            addCriterion("assistant_exception_type is null");
            return (Criteria) this;
        }

        public Criteria andAssistantExceptionTypeIsNotNull() {
            addCriterion("assistant_exception_type is not null");
            return (Criteria) this;
        }

        public Criteria andAssistantExceptionTypeEqualTo(Integer value) {
            addCriterion("assistant_exception_type =", value, "assistantExceptionType");
            return (Criteria) this;
        }

        public Criteria andAssistantExceptionTypeNotEqualTo(Integer value) {
            addCriterion("assistant_exception_type <>", value, "assistantExceptionType");
            return (Criteria) this;
        }

        public Criteria andAssistantExceptionTypeGreaterThan(Integer value) {
            addCriterion("assistant_exception_type >", value, "assistantExceptionType");
            return (Criteria) this;
        }

        public Criteria andAssistantExceptionTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("assistant_exception_type >=", value, "assistantExceptionType");
            return (Criteria) this;
        }

        public Criteria andAssistantExceptionTypeLessThan(Integer value) {
            addCriterion("assistant_exception_type <", value, "assistantExceptionType");
            return (Criteria) this;
        }

        public Criteria andAssistantExceptionTypeLessThanOrEqualTo(Integer value) {
            addCriterion("assistant_exception_type <=", value, "assistantExceptionType");
            return (Criteria) this;
        }

        public Criteria andAssistantExceptionTypeIn(List<Integer> values) {
            addCriterion("assistant_exception_type in", values, "assistantExceptionType");
            return (Criteria) this;
        }

        public Criteria andAssistantExceptionTypeNotIn(List<Integer> values) {
            addCriterion("assistant_exception_type not in", values, "assistantExceptionType");
            return (Criteria) this;
        }

        public Criteria andAssistantExceptionTypeBetween(Integer value1, Integer value2) {
            addCriterion("assistant_exception_type between", value1, value2, "assistantExceptionType");
            return (Criteria) this;
        }

        public Criteria andAssistantExceptionTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("assistant_exception_type not between", value1, value2, "assistantExceptionType");
            return (Criteria) this;
        }

        public Criteria andCustomerRequestIsNull() {
            addCriterion("customer_request is null");
            return (Criteria) this;
        }

        public Criteria andCustomerRequestIsNotNull() {
            addCriterion("customer_request is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerRequestEqualTo(String value) {
            addCriterion("customer_request =", value, "customerRequest");
            return (Criteria) this;
        }

        public Criteria andCustomerRequestNotEqualTo(String value) {
            addCriterion("customer_request <>", value, "customerRequest");
            return (Criteria) this;
        }

        public Criteria andCustomerRequestGreaterThan(String value) {
            addCriterion("customer_request >", value, "customerRequest");
            return (Criteria) this;
        }

        public Criteria andCustomerRequestGreaterThanOrEqualTo(String value) {
            addCriterion("customer_request >=", value, "customerRequest");
            return (Criteria) this;
        }

        public Criteria andCustomerRequestLessThan(String value) {
            addCriterion("customer_request <", value, "customerRequest");
            return (Criteria) this;
        }

        public Criteria andCustomerRequestLessThanOrEqualTo(String value) {
            addCriterion("customer_request <=", value, "customerRequest");
            return (Criteria) this;
        }

        public Criteria andCustomerRequestLike(String value) {
            addCriterion("customer_request like", value, "customerRequest");
            return (Criteria) this;
        }

        public Criteria andCustomerRequestNotLike(String value) {
            addCriterion("customer_request not like", value, "customerRequest");
            return (Criteria) this;
        }

        public Criteria andCustomerRequestIn(List<String> values) {
            addCriterion("customer_request in", values, "customerRequest");
            return (Criteria) this;
        }

        public Criteria andCustomerRequestNotIn(List<String> values) {
            addCriterion("customer_request not in", values, "customerRequest");
            return (Criteria) this;
        }

        public Criteria andCustomerRequestBetween(String value1, String value2) {
            addCriterion("customer_request between", value1, value2, "customerRequest");
            return (Criteria) this;
        }

        public Criteria andCustomerRequestNotBetween(String value1, String value2) {
            addCriterion("customer_request not between", value1, value2, "customerRequest");
            return (Criteria) this;
        }

        public Criteria andStereotypeRequirementIsNull() {
            addCriterion("stereotype_requirement is null");
            return (Criteria) this;
        }

        public Criteria andStereotypeRequirementIsNotNull() {
            addCriterion("stereotype_requirement is not null");
            return (Criteria) this;
        }

        public Criteria andStereotypeRequirementEqualTo(String value) {
            addCriterion("stereotype_requirement =", value, "stereotypeRequirement");
            return (Criteria) this;
        }

        public Criteria andStereotypeRequirementNotEqualTo(String value) {
            addCriterion("stereotype_requirement <>", value, "stereotypeRequirement");
            return (Criteria) this;
        }

        public Criteria andStereotypeRequirementGreaterThan(String value) {
            addCriterion("stereotype_requirement >", value, "stereotypeRequirement");
            return (Criteria) this;
        }

        public Criteria andStereotypeRequirementGreaterThanOrEqualTo(String value) {
            addCriterion("stereotype_requirement >=", value, "stereotypeRequirement");
            return (Criteria) this;
        }

        public Criteria andStereotypeRequirementLessThan(String value) {
            addCriterion("stereotype_requirement <", value, "stereotypeRequirement");
            return (Criteria) this;
        }

        public Criteria andStereotypeRequirementLessThanOrEqualTo(String value) {
            addCriterion("stereotype_requirement <=", value, "stereotypeRequirement");
            return (Criteria) this;
        }

        public Criteria andStereotypeRequirementLike(String value) {
            addCriterion("stereotype_requirement like", value, "stereotypeRequirement");
            return (Criteria) this;
        }

        public Criteria andStereotypeRequirementNotLike(String value) {
            addCriterion("stereotype_requirement not like", value, "stereotypeRequirement");
            return (Criteria) this;
        }

        public Criteria andStereotypeRequirementIn(List<String> values) {
            addCriterion("stereotype_requirement in", values, "stereotypeRequirement");
            return (Criteria) this;
        }

        public Criteria andStereotypeRequirementNotIn(List<String> values) {
            addCriterion("stereotype_requirement not in", values, "stereotypeRequirement");
            return (Criteria) this;
        }

        public Criteria andStereotypeRequirementBetween(String value1, String value2) {
            addCriterion("stereotype_requirement between", value1, value2, "stereotypeRequirement");
            return (Criteria) this;
        }

        public Criteria andStereotypeRequirementNotBetween(String value1, String value2) {
            addCriterion("stereotype_requirement not between", value1, value2, "stereotypeRequirement");
            return (Criteria) this;
        }

        public Criteria andCraftSuitabilityIsNull() {
            addCriterion("craft_suitability is null");
            return (Criteria) this;
        }

        public Criteria andCraftSuitabilityIsNotNull() {
            addCriterion("craft_suitability is not null");
            return (Criteria) this;
        }

        public Criteria andCraftSuitabilityEqualTo(BigDecimal value) {
            addCriterion("craft_suitability =", value, "craftSuitability");
            return (Criteria) this;
        }

        public Criteria andCraftSuitabilityNotEqualTo(BigDecimal value) {
            addCriterion("craft_suitability <>", value, "craftSuitability");
            return (Criteria) this;
        }

        public Criteria andCraftSuitabilityGreaterThan(BigDecimal value) {
            addCriterion("craft_suitability >", value, "craftSuitability");
            return (Criteria) this;
        }

        public Criteria andCraftSuitabilityGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("craft_suitability >=", value, "craftSuitability");
            return (Criteria) this;
        }

        public Criteria andCraftSuitabilityLessThan(BigDecimal value) {
            addCriterion("craft_suitability <", value, "craftSuitability");
            return (Criteria) this;
        }

        public Criteria andCraftSuitabilityLessThanOrEqualTo(BigDecimal value) {
            addCriterion("craft_suitability <=", value, "craftSuitability");
            return (Criteria) this;
        }

        public Criteria andCraftSuitabilityIn(List<BigDecimal> values) {
            addCriterion("craft_suitability in", values, "craftSuitability");
            return (Criteria) this;
        }

        public Criteria andCraftSuitabilityNotIn(List<BigDecimal> values) {
            addCriterion("craft_suitability not in", values, "craftSuitability");
            return (Criteria) this;
        }

        public Criteria andCraftSuitabilityBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("craft_suitability between", value1, value2, "craftSuitability");
            return (Criteria) this;
        }

        public Criteria andCraftSuitabilityNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("craft_suitability not between", value1, value2, "craftSuitability");
            return (Criteria) this;
        }

        public Criteria andShrinkageIsNull() {
            addCriterion("shrinkage is null");
            return (Criteria) this;
        }

        public Criteria andShrinkageIsNotNull() {
            addCriterion("shrinkage is not null");
            return (Criteria) this;
        }

        public Criteria andShrinkageEqualTo(BigDecimal value) {
            addCriterion("shrinkage =", value, "shrinkage");
            return (Criteria) this;
        }

        public Criteria andShrinkageNotEqualTo(BigDecimal value) {
            addCriterion("shrinkage <>", value, "shrinkage");
            return (Criteria) this;
        }

        public Criteria andShrinkageGreaterThan(BigDecimal value) {
            addCriterion("shrinkage >", value, "shrinkage");
            return (Criteria) this;
        }

        public Criteria andShrinkageGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("shrinkage >=", value, "shrinkage");
            return (Criteria) this;
        }

        public Criteria andShrinkageLessThan(BigDecimal value) {
            addCriterion("shrinkage <", value, "shrinkage");
            return (Criteria) this;
        }

        public Criteria andShrinkageLessThanOrEqualTo(BigDecimal value) {
            addCriterion("shrinkage <=", value, "shrinkage");
            return (Criteria) this;
        }

        public Criteria andShrinkageIn(List<BigDecimal> values) {
            addCriterion("shrinkage in", values, "shrinkage");
            return (Criteria) this;
        }

        public Criteria andShrinkageNotIn(List<BigDecimal> values) {
            addCriterion("shrinkage not in", values, "shrinkage");
            return (Criteria) this;
        }

        public Criteria andShrinkageBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("shrinkage between", value1, value2, "shrinkage");
            return (Criteria) this;
        }

        public Criteria andShrinkageNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("shrinkage not between", value1, value2, "shrinkage");
            return (Criteria) this;
        }

        public Criteria andCraftExceptionTypeIsNull() {
            addCriterion("craft_exception_type is null");
            return (Criteria) this;
        }

        public Criteria andCraftExceptionTypeIsNotNull() {
            addCriterion("craft_exception_type is not null");
            return (Criteria) this;
        }

        public Criteria andCraftExceptionTypeEqualTo(Integer value) {
            addCriterion("craft_exception_type =", value, "craftExceptionType");
            return (Criteria) this;
        }

        public Criteria andCraftExceptionTypeNotEqualTo(Integer value) {
            addCriterion("craft_exception_type <>", value, "craftExceptionType");
            return (Criteria) this;
        }

        public Criteria andCraftExceptionTypeGreaterThan(Integer value) {
            addCriterion("craft_exception_type >", value, "craftExceptionType");
            return (Criteria) this;
        }

        public Criteria andCraftExceptionTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("craft_exception_type >=", value, "craftExceptionType");
            return (Criteria) this;
        }

        public Criteria andCraftExceptionTypeLessThan(Integer value) {
            addCriterion("craft_exception_type <", value, "craftExceptionType");
            return (Criteria) this;
        }

        public Criteria andCraftExceptionTypeLessThanOrEqualTo(Integer value) {
            addCriterion("craft_exception_type <=", value, "craftExceptionType");
            return (Criteria) this;
        }

        public Criteria andCraftExceptionTypeIn(List<Integer> values) {
            addCriterion("craft_exception_type in", values, "craftExceptionType");
            return (Criteria) this;
        }

        public Criteria andCraftExceptionTypeNotIn(List<Integer> values) {
            addCriterion("craft_exception_type not in", values, "craftExceptionType");
            return (Criteria) this;
        }

        public Criteria andCraftExceptionTypeBetween(Integer value1, Integer value2) {
            addCriterion("craft_exception_type between", value1, value2, "craftExceptionType");
            return (Criteria) this;
        }

        public Criteria andCraftExceptionTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("craft_exception_type not between", value1, value2, "craftExceptionType");
            return (Criteria) this;
        }

        public Criteria andIsHaveCraftIsNull() {
            addCriterion("is_have_craft is null");
            return (Criteria) this;
        }

        public Criteria andIsHaveCraftIsNotNull() {
            addCriterion("is_have_craft is not null");
            return (Criteria) this;
        }

        public Criteria andIsHaveCraftEqualTo(Byte value) {
            addCriterion("is_have_craft =", value, "isHaveCraft");
            return (Criteria) this;
        }

        public Criteria andIsHaveCraftNotEqualTo(Byte value) {
            addCriterion("is_have_craft <>", value, "isHaveCraft");
            return (Criteria) this;
        }

        public Criteria andIsHaveCraftGreaterThan(Byte value) {
            addCriterion("is_have_craft >", value, "isHaveCraft");
            return (Criteria) this;
        }

        public Criteria andIsHaveCraftGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_have_craft >=", value, "isHaveCraft");
            return (Criteria) this;
        }

        public Criteria andIsHaveCraftLessThan(Byte value) {
            addCriterion("is_have_craft <", value, "isHaveCraft");
            return (Criteria) this;
        }

        public Criteria andIsHaveCraftLessThanOrEqualTo(Byte value) {
            addCriterion("is_have_craft <=", value, "isHaveCraft");
            return (Criteria) this;
        }

        public Criteria andIsHaveCraftIn(List<Byte> values) {
            addCriterion("is_have_craft in", values, "isHaveCraft");
            return (Criteria) this;
        }

        public Criteria andIsHaveCraftNotIn(List<Byte> values) {
            addCriterion("is_have_craft not in", values, "isHaveCraft");
            return (Criteria) this;
        }

        public Criteria andIsHaveCraftBetween(Byte value1, Byte value2) {
            addCriterion("is_have_craft between", value1, value2, "isHaveCraft");
            return (Criteria) this;
        }

        public Criteria andIsHaveCraftNotBetween(Byte value1, Byte value2) {
            addCriterion("is_have_craft not between", value1, value2, "isHaveCraft");
            return (Criteria) this;
        }

        public Criteria andProductWeightIsNull() {
            addCriterion("product_weight is null");
            return (Criteria) this;
        }

        public Criteria andProductWeightIsNotNull() {
            addCriterion("product_weight is not null");
            return (Criteria) this;
        }

        public Criteria andProductWeightEqualTo(String value) {
            addCriterion("product_weight =", value, "productWeight");
            return (Criteria) this;
        }

        public Criteria andProductWeightNotEqualTo(String value) {
            addCriterion("product_weight <>", value, "productWeight");
            return (Criteria) this;
        }

        public Criteria andProductWeightGreaterThan(String value) {
            addCriterion("product_weight >", value, "productWeight");
            return (Criteria) this;
        }

        public Criteria andProductWeightGreaterThanOrEqualTo(String value) {
            addCriterion("product_weight >=", value, "productWeight");
            return (Criteria) this;
        }

        public Criteria andProductWeightLessThan(String value) {
            addCriterion("product_weight <", value, "productWeight");
            return (Criteria) this;
        }

        public Criteria andProductWeightLessThanOrEqualTo(String value) {
            addCriterion("product_weight <=", value, "productWeight");
            return (Criteria) this;
        }

        public Criteria andProductWeightLike(String value) {
            addCriterion("product_weight like", value, "productWeight");
            return (Criteria) this;
        }

        public Criteria andProductWeightNotLike(String value) {
            addCriterion("product_weight not like", value, "productWeight");
            return (Criteria) this;
        }

        public Criteria andProductWeightIn(List<String> values) {
            addCriterion("product_weight in", values, "productWeight");
            return (Criteria) this;
        }

        public Criteria andProductWeightNotIn(List<String> values) {
            addCriterion("product_weight not in", values, "productWeight");
            return (Criteria) this;
        }

        public Criteria andProductWeightBetween(String value1, String value2) {
            addCriterion("product_weight between", value1, value2, "productWeight");
            return (Criteria) this;
        }

        public Criteria andProductWeightNotBetween(String value1, String value2) {
            addCriterion("product_weight not between", value1, value2, "productWeight");
            return (Criteria) this;
        }

        public Criteria andThicknessIsNull() {
            addCriterion("thickness is null");
            return (Criteria) this;
        }

        public Criteria andThicknessIsNotNull() {
            addCriterion("thickness is not null");
            return (Criteria) this;
        }

        public Criteria andThicknessEqualTo(String value) {
            addCriterion("thickness =", value, "thickness");
            return (Criteria) this;
        }

        public Criteria andThicknessNotEqualTo(String value) {
            addCriterion("thickness <>", value, "thickness");
            return (Criteria) this;
        }

        public Criteria andThicknessGreaterThan(String value) {
            addCriterion("thickness >", value, "thickness");
            return (Criteria) this;
        }

        public Criteria andThicknessGreaterThanOrEqualTo(String value) {
            addCriterion("thickness >=", value, "thickness");
            return (Criteria) this;
        }

        public Criteria andThicknessLessThan(String value) {
            addCriterion("thickness <", value, "thickness");
            return (Criteria) this;
        }

        public Criteria andThicknessLessThanOrEqualTo(String value) {
            addCriterion("thickness <=", value, "thickness");
            return (Criteria) this;
        }

        public Criteria andThicknessLike(String value) {
            addCriterion("thickness like", value, "thickness");
            return (Criteria) this;
        }

        public Criteria andThicknessNotLike(String value) {
            addCriterion("thickness not like", value, "thickness");
            return (Criteria) this;
        }

        public Criteria andThicknessIn(List<String> values) {
            addCriterion("thickness in", values, "thickness");
            return (Criteria) this;
        }

        public Criteria andThicknessNotIn(List<String> values) {
            addCriterion("thickness not in", values, "thickness");
            return (Criteria) this;
        }

        public Criteria andThicknessBetween(String value1, String value2) {
            addCriterion("thickness between", value1, value2, "thickness");
            return (Criteria) this;
        }

        public Criteria andThicknessNotBetween(String value1, String value2) {
            addCriterion("thickness not between", value1, value2, "thickness");
            return (Criteria) this;
        }

        public Criteria andProcessReportTypeIsNull() {
            addCriterion("process_report_type is null");
            return (Criteria) this;
        }

        public Criteria andProcessReportTypeIsNotNull() {
            addCriterion("process_report_type is not null");
            return (Criteria) this;
        }

        public Criteria andProcessReportTypeEqualTo(Byte value) {
            addCriterion("process_report_type =", value, "processReportType");
            return (Criteria) this;
        }

        public Criteria andProcessReportTypeNotEqualTo(Byte value) {
            addCriterion("process_report_type <>", value, "processReportType");
            return (Criteria) this;
        }

        public Criteria andProcessReportTypeGreaterThan(Byte value) {
            addCriterion("process_report_type >", value, "processReportType");
            return (Criteria) this;
        }

        public Criteria andProcessReportTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("process_report_type >=", value, "processReportType");
            return (Criteria) this;
        }

        public Criteria andProcessReportTypeLessThan(Byte value) {
            addCriterion("process_report_type <", value, "processReportType");
            return (Criteria) this;
        }

        public Criteria andProcessReportTypeLessThanOrEqualTo(Byte value) {
            addCriterion("process_report_type <=", value, "processReportType");
            return (Criteria) this;
        }

        public Criteria andProcessReportTypeIn(List<Byte> values) {
            addCriterion("process_report_type in", values, "processReportType");
            return (Criteria) this;
        }

        public Criteria andProcessReportTypeNotIn(List<Byte> values) {
            addCriterion("process_report_type not in", values, "processReportType");
            return (Criteria) this;
        }

        public Criteria andProcessReportTypeBetween(Byte value1, Byte value2) {
            addCriterion("process_report_type between", value1, value2, "processReportType");
            return (Criteria) this;
        }

        public Criteria andProcessReportTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("process_report_type not between", value1, value2, "processReportType");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}