package com.zw.cloud.mybatis.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductRecordDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProductRecordDetailExample() {
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

        public Criteria andProductRecordIdIsNull() {
            addCriterion("product_record_id is null");
            return (Criteria) this;
        }

        public Criteria andProductRecordIdIsNotNull() {
            addCriterion("product_record_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductRecordIdEqualTo(Long value) {
            addCriterion("product_record_id =", value, "productRecordId");
            return (Criteria) this;
        }

        public Criteria andProductRecordIdNotEqualTo(Long value) {
            addCriterion("product_record_id <>", value, "productRecordId");
            return (Criteria) this;
        }

        public Criteria andProductRecordIdGreaterThan(Long value) {
            addCriterion("product_record_id >", value, "productRecordId");
            return (Criteria) this;
        }

        public Criteria andProductRecordIdGreaterThanOrEqualTo(Long value) {
            addCriterion("product_record_id >=", value, "productRecordId");
            return (Criteria) this;
        }

        public Criteria andProductRecordIdLessThan(Long value) {
            addCriterion("product_record_id <", value, "productRecordId");
            return (Criteria) this;
        }

        public Criteria andProductRecordIdLessThanOrEqualTo(Long value) {
            addCriterion("product_record_id <=", value, "productRecordId");
            return (Criteria) this;
        }

        public Criteria andProductRecordIdIn(List<Long> values) {
            addCriterion("product_record_id in", values, "productRecordId");
            return (Criteria) this;
        }

        public Criteria andProductRecordIdNotIn(List<Long> values) {
            addCriterion("product_record_id not in", values, "productRecordId");
            return (Criteria) this;
        }

        public Criteria andProductRecordIdBetween(Long value1, Long value2) {
            addCriterion("product_record_id between", value1, value2, "productRecordId");
            return (Criteria) this;
        }

        public Criteria andProductRecordIdNotBetween(Long value1, Long value2) {
            addCriterion("product_record_id not between", value1, value2, "productRecordId");
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

        public Criteria andExceptionUpdateTimeIsNull() {
            addCriterion("exception_update_time is null");
            return (Criteria) this;
        }

        public Criteria andExceptionUpdateTimeIsNotNull() {
            addCriterion("exception_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andExceptionUpdateTimeEqualTo(Date value) {
            addCriterion("exception_update_time =", value, "exceptionUpdateTime");
            return (Criteria) this;
        }

        public Criteria andExceptionUpdateTimeNotEqualTo(Date value) {
            addCriterion("exception_update_time <>", value, "exceptionUpdateTime");
            return (Criteria) this;
        }

        public Criteria andExceptionUpdateTimeGreaterThan(Date value) {
            addCriterion("exception_update_time >", value, "exceptionUpdateTime");
            return (Criteria) this;
        }

        public Criteria andExceptionUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("exception_update_time >=", value, "exceptionUpdateTime");
            return (Criteria) this;
        }

        public Criteria andExceptionUpdateTimeLessThan(Date value) {
            addCriterion("exception_update_time <", value, "exceptionUpdateTime");
            return (Criteria) this;
        }

        public Criteria andExceptionUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("exception_update_time <=", value, "exceptionUpdateTime");
            return (Criteria) this;
        }

        public Criteria andExceptionUpdateTimeIn(List<Date> values) {
            addCriterion("exception_update_time in", values, "exceptionUpdateTime");
            return (Criteria) this;
        }

        public Criteria andExceptionUpdateTimeNotIn(List<Date> values) {
            addCriterion("exception_update_time not in", values, "exceptionUpdateTime");
            return (Criteria) this;
        }

        public Criteria andExceptionUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("exception_update_time between", value1, value2, "exceptionUpdateTime");
            return (Criteria) this;
        }

        public Criteria andExceptionUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("exception_update_time not between", value1, value2, "exceptionUpdateTime");
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

        public Criteria andReductionWeightIsNull() {
            addCriterion("reduction_weight is null");
            return (Criteria) this;
        }

        public Criteria andReductionWeightIsNotNull() {
            addCriterion("reduction_weight is not null");
            return (Criteria) this;
        }

        public Criteria andReductionWeightEqualTo(String value) {
            addCriterion("reduction_weight =", value, "reductionWeight");
            return (Criteria) this;
        }

        public Criteria andReductionWeightNotEqualTo(String value) {
            addCriterion("reduction_weight <>", value, "reductionWeight");
            return (Criteria) this;
        }

        public Criteria andReductionWeightGreaterThan(String value) {
            addCriterion("reduction_weight >", value, "reductionWeight");
            return (Criteria) this;
        }

        public Criteria andReductionWeightGreaterThanOrEqualTo(String value) {
            addCriterion("reduction_weight >=", value, "reductionWeight");
            return (Criteria) this;
        }

        public Criteria andReductionWeightLessThan(String value) {
            addCriterion("reduction_weight <", value, "reductionWeight");
            return (Criteria) this;
        }

        public Criteria andReductionWeightLessThanOrEqualTo(String value) {
            addCriterion("reduction_weight <=", value, "reductionWeight");
            return (Criteria) this;
        }

        public Criteria andReductionWeightLike(String value) {
            addCriterion("reduction_weight like", value, "reductionWeight");
            return (Criteria) this;
        }

        public Criteria andReductionWeightNotLike(String value) {
            addCriterion("reduction_weight not like", value, "reductionWeight");
            return (Criteria) this;
        }

        public Criteria andReductionWeightIn(List<String> values) {
            addCriterion("reduction_weight in", values, "reductionWeight");
            return (Criteria) this;
        }

        public Criteria andReductionWeightNotIn(List<String> values) {
            addCriterion("reduction_weight not in", values, "reductionWeight");
            return (Criteria) this;
        }

        public Criteria andReductionWeightBetween(String value1, String value2) {
            addCriterion("reduction_weight between", value1, value2, "reductionWeight");
            return (Criteria) this;
        }

        public Criteria andReductionWeightNotBetween(String value1, String value2) {
            addCriterion("reduction_weight not between", value1, value2, "reductionWeight");
            return (Criteria) this;
        }

        public Criteria andReductionAmplitudeIsNull() {
            addCriterion("reduction_amplitude is null");
            return (Criteria) this;
        }

        public Criteria andReductionAmplitudeIsNotNull() {
            addCriterion("reduction_amplitude is not null");
            return (Criteria) this;
        }

        public Criteria andReductionAmplitudeEqualTo(String value) {
            addCriterion("reduction_amplitude =", value, "reductionAmplitude");
            return (Criteria) this;
        }

        public Criteria andReductionAmplitudeNotEqualTo(String value) {
            addCriterion("reduction_amplitude <>", value, "reductionAmplitude");
            return (Criteria) this;
        }

        public Criteria andReductionAmplitudeGreaterThan(String value) {
            addCriterion("reduction_amplitude >", value, "reductionAmplitude");
            return (Criteria) this;
        }

        public Criteria andReductionAmplitudeGreaterThanOrEqualTo(String value) {
            addCriterion("reduction_amplitude >=", value, "reductionAmplitude");
            return (Criteria) this;
        }

        public Criteria andReductionAmplitudeLessThan(String value) {
            addCriterion("reduction_amplitude <", value, "reductionAmplitude");
            return (Criteria) this;
        }

        public Criteria andReductionAmplitudeLessThanOrEqualTo(String value) {
            addCriterion("reduction_amplitude <=", value, "reductionAmplitude");
            return (Criteria) this;
        }

        public Criteria andReductionAmplitudeLike(String value) {
            addCriterion("reduction_amplitude like", value, "reductionAmplitude");
            return (Criteria) this;
        }

        public Criteria andReductionAmplitudeNotLike(String value) {
            addCriterion("reduction_amplitude not like", value, "reductionAmplitude");
            return (Criteria) this;
        }

        public Criteria andReductionAmplitudeIn(List<String> values) {
            addCriterion("reduction_amplitude in", values, "reductionAmplitude");
            return (Criteria) this;
        }

        public Criteria andReductionAmplitudeNotIn(List<String> values) {
            addCriterion("reduction_amplitude not in", values, "reductionAmplitude");
            return (Criteria) this;
        }

        public Criteria andReductionAmplitudeBetween(String value1, String value2) {
            addCriterion("reduction_amplitude between", value1, value2, "reductionAmplitude");
            return (Criteria) this;
        }

        public Criteria andReductionAmplitudeNotBetween(String value1, String value2) {
            addCriterion("reduction_amplitude not between", value1, value2, "reductionAmplitude");
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