package com.zw.cloud.activiti.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActHiProcinstExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ActHiProcinstExample() {
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
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID_ is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID_ is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID_ =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID_ <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID_ >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID_ >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID_ <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID_ <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID_ like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID_ not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID_ in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID_ not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID_ between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID_ not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andProcInstIdIsNull() {
            addCriterion("PROC_INST_ID_ is null");
            return (Criteria) this;
        }

        public Criteria andProcInstIdIsNotNull() {
            addCriterion("PROC_INST_ID_ is not null");
            return (Criteria) this;
        }

        public Criteria andProcInstIdEqualTo(String value) {
            addCriterion("PROC_INST_ID_ =", value, "procInstId");
            return (Criteria) this;
        }

        public Criteria andProcInstIdNotEqualTo(String value) {
            addCriterion("PROC_INST_ID_ <>", value, "procInstId");
            return (Criteria) this;
        }

        public Criteria andProcInstIdGreaterThan(String value) {
            addCriterion("PROC_INST_ID_ >", value, "procInstId");
            return (Criteria) this;
        }

        public Criteria andProcInstIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROC_INST_ID_ >=", value, "procInstId");
            return (Criteria) this;
        }

        public Criteria andProcInstIdLessThan(String value) {
            addCriterion("PROC_INST_ID_ <", value, "procInstId");
            return (Criteria) this;
        }

        public Criteria andProcInstIdLessThanOrEqualTo(String value) {
            addCriterion("PROC_INST_ID_ <=", value, "procInstId");
            return (Criteria) this;
        }

        public Criteria andProcInstIdLike(String value) {
            addCriterion("PROC_INST_ID_ like", value, "procInstId");
            return (Criteria) this;
        }

        public Criteria andProcInstIdNotLike(String value) {
            addCriterion("PROC_INST_ID_ not like", value, "procInstId");
            return (Criteria) this;
        }

        public Criteria andProcInstIdIn(List<String> values) {
            addCriterion("PROC_INST_ID_ in", values, "procInstId");
            return (Criteria) this;
        }

        public Criteria andProcInstIdNotIn(List<String> values) {
            addCriterion("PROC_INST_ID_ not in", values, "procInstId");
            return (Criteria) this;
        }

        public Criteria andProcInstIdBetween(String value1, String value2) {
            addCriterion("PROC_INST_ID_ between", value1, value2, "procInstId");
            return (Criteria) this;
        }

        public Criteria andProcInstIdNotBetween(String value1, String value2) {
            addCriterion("PROC_INST_ID_ not between", value1, value2, "procInstId");
            return (Criteria) this;
        }

        public Criteria andBusinessKeyIsNull() {
            addCriterion("BUSINESS_KEY_ is null");
            return (Criteria) this;
        }

        public Criteria andBusinessKeyIsNotNull() {
            addCriterion("BUSINESS_KEY_ is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessKeyEqualTo(String value) {
            addCriterion("BUSINESS_KEY_ =", value, "businessKey");
            return (Criteria) this;
        }

        public Criteria andBusinessKeyNotEqualTo(String value) {
            addCriterion("BUSINESS_KEY_ <>", value, "businessKey");
            return (Criteria) this;
        }

        public Criteria andBusinessKeyGreaterThan(String value) {
            addCriterion("BUSINESS_KEY_ >", value, "businessKey");
            return (Criteria) this;
        }

        public Criteria andBusinessKeyGreaterThanOrEqualTo(String value) {
            addCriterion("BUSINESS_KEY_ >=", value, "businessKey");
            return (Criteria) this;
        }

        public Criteria andBusinessKeyLessThan(String value) {
            addCriterion("BUSINESS_KEY_ <", value, "businessKey");
            return (Criteria) this;
        }

        public Criteria andBusinessKeyLessThanOrEqualTo(String value) {
            addCriterion("BUSINESS_KEY_ <=", value, "businessKey");
            return (Criteria) this;
        }

        public Criteria andBusinessKeyLike(String value) {
            addCriterion("BUSINESS_KEY_ like", value, "businessKey");
            return (Criteria) this;
        }

        public Criteria andBusinessKeyNotLike(String value) {
            addCriterion("BUSINESS_KEY_ not like", value, "businessKey");
            return (Criteria) this;
        }

        public Criteria andBusinessKeyIn(List<String> values) {
            addCriterion("BUSINESS_KEY_ in", values, "businessKey");
            return (Criteria) this;
        }

        public Criteria andBusinessKeyNotIn(List<String> values) {
            addCriterion("BUSINESS_KEY_ not in", values, "businessKey");
            return (Criteria) this;
        }

        public Criteria andBusinessKeyBetween(String value1, String value2) {
            addCriterion("BUSINESS_KEY_ between", value1, value2, "businessKey");
            return (Criteria) this;
        }

        public Criteria andBusinessKeyNotBetween(String value1, String value2) {
            addCriterion("BUSINESS_KEY_ not between", value1, value2, "businessKey");
            return (Criteria) this;
        }

        public Criteria andProcDefIdIsNull() {
            addCriterion("PROC_DEF_ID_ is null");
            return (Criteria) this;
        }

        public Criteria andProcDefIdIsNotNull() {
            addCriterion("PROC_DEF_ID_ is not null");
            return (Criteria) this;
        }

        public Criteria andProcDefIdEqualTo(String value) {
            addCriterion("PROC_DEF_ID_ =", value, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdNotEqualTo(String value) {
            addCriterion("PROC_DEF_ID_ <>", value, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdGreaterThan(String value) {
            addCriterion("PROC_DEF_ID_ >", value, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROC_DEF_ID_ >=", value, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdLessThan(String value) {
            addCriterion("PROC_DEF_ID_ <", value, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdLessThanOrEqualTo(String value) {
            addCriterion("PROC_DEF_ID_ <=", value, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdLike(String value) {
            addCriterion("PROC_DEF_ID_ like", value, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdNotLike(String value) {
            addCriterion("PROC_DEF_ID_ not like", value, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdIn(List<String> values) {
            addCriterion("PROC_DEF_ID_ in", values, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdNotIn(List<String> values) {
            addCriterion("PROC_DEF_ID_ not in", values, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdBetween(String value1, String value2) {
            addCriterion("PROC_DEF_ID_ between", value1, value2, "procDefId");
            return (Criteria) this;
        }

        public Criteria andProcDefIdNotBetween(String value1, String value2) {
            addCriterion("PROC_DEF_ID_ not between", value1, value2, "procDefId");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("START_TIME_ is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("START_TIME_ is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("START_TIME_ =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("START_TIME_ <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("START_TIME_ >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("START_TIME_ >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("START_TIME_ <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("START_TIME_ <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("START_TIME_ in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("START_TIME_ not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("START_TIME_ between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("START_TIME_ not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("END_TIME_ is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("END_TIME_ is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("END_TIME_ =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("END_TIME_ <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("END_TIME_ >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("END_TIME_ >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("END_TIME_ <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("END_TIME_ <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("END_TIME_ in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("END_TIME_ not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("END_TIME_ between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("END_TIME_ not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andDurationIsNull() {
            addCriterion("DURATION_ is null");
            return (Criteria) this;
        }

        public Criteria andDurationIsNotNull() {
            addCriterion("DURATION_ is not null");
            return (Criteria) this;
        }

        public Criteria andDurationEqualTo(Long value) {
            addCriterion("DURATION_ =", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotEqualTo(Long value) {
            addCriterion("DURATION_ <>", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThan(Long value) {
            addCriterion("DURATION_ >", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThanOrEqualTo(Long value) {
            addCriterion("DURATION_ >=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThan(Long value) {
            addCriterion("DURATION_ <", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThanOrEqualTo(Long value) {
            addCriterion("DURATION_ <=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationIn(List<Long> values) {
            addCriterion("DURATION_ in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotIn(List<Long> values) {
            addCriterion("DURATION_ not in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationBetween(Long value1, Long value2) {
            addCriterion("DURATION_ between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotBetween(Long value1, Long value2) {
            addCriterion("DURATION_ not between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andStartUserIdIsNull() {
            addCriterion("START_USER_ID_ is null");
            return (Criteria) this;
        }

        public Criteria andStartUserIdIsNotNull() {
            addCriterion("START_USER_ID_ is not null");
            return (Criteria) this;
        }

        public Criteria andStartUserIdEqualTo(String value) {
            addCriterion("START_USER_ID_ =", value, "startUserId");
            return (Criteria) this;
        }

        public Criteria andStartUserIdNotEqualTo(String value) {
            addCriterion("START_USER_ID_ <>", value, "startUserId");
            return (Criteria) this;
        }

        public Criteria andStartUserIdGreaterThan(String value) {
            addCriterion("START_USER_ID_ >", value, "startUserId");
            return (Criteria) this;
        }

        public Criteria andStartUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("START_USER_ID_ >=", value, "startUserId");
            return (Criteria) this;
        }

        public Criteria andStartUserIdLessThan(String value) {
            addCriterion("START_USER_ID_ <", value, "startUserId");
            return (Criteria) this;
        }

        public Criteria andStartUserIdLessThanOrEqualTo(String value) {
            addCriterion("START_USER_ID_ <=", value, "startUserId");
            return (Criteria) this;
        }

        public Criteria andStartUserIdLike(String value) {
            addCriterion("START_USER_ID_ like", value, "startUserId");
            return (Criteria) this;
        }

        public Criteria andStartUserIdNotLike(String value) {
            addCriterion("START_USER_ID_ not like", value, "startUserId");
            return (Criteria) this;
        }

        public Criteria andStartUserIdIn(List<String> values) {
            addCriterion("START_USER_ID_ in", values, "startUserId");
            return (Criteria) this;
        }

        public Criteria andStartUserIdNotIn(List<String> values) {
            addCriterion("START_USER_ID_ not in", values, "startUserId");
            return (Criteria) this;
        }

        public Criteria andStartUserIdBetween(String value1, String value2) {
            addCriterion("START_USER_ID_ between", value1, value2, "startUserId");
            return (Criteria) this;
        }

        public Criteria andStartUserIdNotBetween(String value1, String value2) {
            addCriterion("START_USER_ID_ not between", value1, value2, "startUserId");
            return (Criteria) this;
        }

        public Criteria andStartActIdIsNull() {
            addCriterion("START_ACT_ID_ is null");
            return (Criteria) this;
        }

        public Criteria andStartActIdIsNotNull() {
            addCriterion("START_ACT_ID_ is not null");
            return (Criteria) this;
        }

        public Criteria andStartActIdEqualTo(String value) {
            addCriterion("START_ACT_ID_ =", value, "startActId");
            return (Criteria) this;
        }

        public Criteria andStartActIdNotEqualTo(String value) {
            addCriterion("START_ACT_ID_ <>", value, "startActId");
            return (Criteria) this;
        }

        public Criteria andStartActIdGreaterThan(String value) {
            addCriterion("START_ACT_ID_ >", value, "startActId");
            return (Criteria) this;
        }

        public Criteria andStartActIdGreaterThanOrEqualTo(String value) {
            addCriterion("START_ACT_ID_ >=", value, "startActId");
            return (Criteria) this;
        }

        public Criteria andStartActIdLessThan(String value) {
            addCriterion("START_ACT_ID_ <", value, "startActId");
            return (Criteria) this;
        }

        public Criteria andStartActIdLessThanOrEqualTo(String value) {
            addCriterion("START_ACT_ID_ <=", value, "startActId");
            return (Criteria) this;
        }

        public Criteria andStartActIdLike(String value) {
            addCriterion("START_ACT_ID_ like", value, "startActId");
            return (Criteria) this;
        }

        public Criteria andStartActIdNotLike(String value) {
            addCriterion("START_ACT_ID_ not like", value, "startActId");
            return (Criteria) this;
        }

        public Criteria andStartActIdIn(List<String> values) {
            addCriterion("START_ACT_ID_ in", values, "startActId");
            return (Criteria) this;
        }

        public Criteria andStartActIdNotIn(List<String> values) {
            addCriterion("START_ACT_ID_ not in", values, "startActId");
            return (Criteria) this;
        }

        public Criteria andStartActIdBetween(String value1, String value2) {
            addCriterion("START_ACT_ID_ between", value1, value2, "startActId");
            return (Criteria) this;
        }

        public Criteria andStartActIdNotBetween(String value1, String value2) {
            addCriterion("START_ACT_ID_ not between", value1, value2, "startActId");
            return (Criteria) this;
        }

        public Criteria andEndActIdIsNull() {
            addCriterion("END_ACT_ID_ is null");
            return (Criteria) this;
        }

        public Criteria andEndActIdIsNotNull() {
            addCriterion("END_ACT_ID_ is not null");
            return (Criteria) this;
        }

        public Criteria andEndActIdEqualTo(String value) {
            addCriterion("END_ACT_ID_ =", value, "endActId");
            return (Criteria) this;
        }

        public Criteria andEndActIdNotEqualTo(String value) {
            addCriterion("END_ACT_ID_ <>", value, "endActId");
            return (Criteria) this;
        }

        public Criteria andEndActIdGreaterThan(String value) {
            addCriterion("END_ACT_ID_ >", value, "endActId");
            return (Criteria) this;
        }

        public Criteria andEndActIdGreaterThanOrEqualTo(String value) {
            addCriterion("END_ACT_ID_ >=", value, "endActId");
            return (Criteria) this;
        }

        public Criteria andEndActIdLessThan(String value) {
            addCriterion("END_ACT_ID_ <", value, "endActId");
            return (Criteria) this;
        }

        public Criteria andEndActIdLessThanOrEqualTo(String value) {
            addCriterion("END_ACT_ID_ <=", value, "endActId");
            return (Criteria) this;
        }

        public Criteria andEndActIdLike(String value) {
            addCriterion("END_ACT_ID_ like", value, "endActId");
            return (Criteria) this;
        }

        public Criteria andEndActIdNotLike(String value) {
            addCriterion("END_ACT_ID_ not like", value, "endActId");
            return (Criteria) this;
        }

        public Criteria andEndActIdIn(List<String> values) {
            addCriterion("END_ACT_ID_ in", values, "endActId");
            return (Criteria) this;
        }

        public Criteria andEndActIdNotIn(List<String> values) {
            addCriterion("END_ACT_ID_ not in", values, "endActId");
            return (Criteria) this;
        }

        public Criteria andEndActIdBetween(String value1, String value2) {
            addCriterion("END_ACT_ID_ between", value1, value2, "endActId");
            return (Criteria) this;
        }

        public Criteria andEndActIdNotBetween(String value1, String value2) {
            addCriterion("END_ACT_ID_ not between", value1, value2, "endActId");
            return (Criteria) this;
        }

        public Criteria andSuperProcessInstanceIdIsNull() {
            addCriterion("SUPER_PROCESS_INSTANCE_ID_ is null");
            return (Criteria) this;
        }

        public Criteria andSuperProcessInstanceIdIsNotNull() {
            addCriterion("SUPER_PROCESS_INSTANCE_ID_ is not null");
            return (Criteria) this;
        }

        public Criteria andSuperProcessInstanceIdEqualTo(String value) {
            addCriterion("SUPER_PROCESS_INSTANCE_ID_ =", value, "superProcessInstanceId");
            return (Criteria) this;
        }

        public Criteria andSuperProcessInstanceIdNotEqualTo(String value) {
            addCriterion("SUPER_PROCESS_INSTANCE_ID_ <>", value, "superProcessInstanceId");
            return (Criteria) this;
        }

        public Criteria andSuperProcessInstanceIdGreaterThan(String value) {
            addCriterion("SUPER_PROCESS_INSTANCE_ID_ >", value, "superProcessInstanceId");
            return (Criteria) this;
        }

        public Criteria andSuperProcessInstanceIdGreaterThanOrEqualTo(String value) {
            addCriterion("SUPER_PROCESS_INSTANCE_ID_ >=", value, "superProcessInstanceId");
            return (Criteria) this;
        }

        public Criteria andSuperProcessInstanceIdLessThan(String value) {
            addCriterion("SUPER_PROCESS_INSTANCE_ID_ <", value, "superProcessInstanceId");
            return (Criteria) this;
        }

        public Criteria andSuperProcessInstanceIdLessThanOrEqualTo(String value) {
            addCriterion("SUPER_PROCESS_INSTANCE_ID_ <=", value, "superProcessInstanceId");
            return (Criteria) this;
        }

        public Criteria andSuperProcessInstanceIdLike(String value) {
            addCriterion("SUPER_PROCESS_INSTANCE_ID_ like", value, "superProcessInstanceId");
            return (Criteria) this;
        }

        public Criteria andSuperProcessInstanceIdNotLike(String value) {
            addCriterion("SUPER_PROCESS_INSTANCE_ID_ not like", value, "superProcessInstanceId");
            return (Criteria) this;
        }

        public Criteria andSuperProcessInstanceIdIn(List<String> values) {
            addCriterion("SUPER_PROCESS_INSTANCE_ID_ in", values, "superProcessInstanceId");
            return (Criteria) this;
        }

        public Criteria andSuperProcessInstanceIdNotIn(List<String> values) {
            addCriterion("SUPER_PROCESS_INSTANCE_ID_ not in", values, "superProcessInstanceId");
            return (Criteria) this;
        }

        public Criteria andSuperProcessInstanceIdBetween(String value1, String value2) {
            addCriterion("SUPER_PROCESS_INSTANCE_ID_ between", value1, value2, "superProcessInstanceId");
            return (Criteria) this;
        }

        public Criteria andSuperProcessInstanceIdNotBetween(String value1, String value2) {
            addCriterion("SUPER_PROCESS_INSTANCE_ID_ not between", value1, value2, "superProcessInstanceId");
            return (Criteria) this;
        }

        public Criteria andDeleteReasonIsNull() {
            addCriterion("DELETE_REASON_ is null");
            return (Criteria) this;
        }

        public Criteria andDeleteReasonIsNotNull() {
            addCriterion("DELETE_REASON_ is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteReasonEqualTo(String value) {
            addCriterion("DELETE_REASON_ =", value, "deleteReason");
            return (Criteria) this;
        }

        public Criteria andDeleteReasonNotEqualTo(String value) {
            addCriterion("DELETE_REASON_ <>", value, "deleteReason");
            return (Criteria) this;
        }

        public Criteria andDeleteReasonGreaterThan(String value) {
            addCriterion("DELETE_REASON_ >", value, "deleteReason");
            return (Criteria) this;
        }

        public Criteria andDeleteReasonGreaterThanOrEqualTo(String value) {
            addCriterion("DELETE_REASON_ >=", value, "deleteReason");
            return (Criteria) this;
        }

        public Criteria andDeleteReasonLessThan(String value) {
            addCriterion("DELETE_REASON_ <", value, "deleteReason");
            return (Criteria) this;
        }

        public Criteria andDeleteReasonLessThanOrEqualTo(String value) {
            addCriterion("DELETE_REASON_ <=", value, "deleteReason");
            return (Criteria) this;
        }

        public Criteria andDeleteReasonLike(String value) {
            addCriterion("DELETE_REASON_ like", value, "deleteReason");
            return (Criteria) this;
        }

        public Criteria andDeleteReasonNotLike(String value) {
            addCriterion("DELETE_REASON_ not like", value, "deleteReason");
            return (Criteria) this;
        }

        public Criteria andDeleteReasonIn(List<String> values) {
            addCriterion("DELETE_REASON_ in", values, "deleteReason");
            return (Criteria) this;
        }

        public Criteria andDeleteReasonNotIn(List<String> values) {
            addCriterion("DELETE_REASON_ not in", values, "deleteReason");
            return (Criteria) this;
        }

        public Criteria andDeleteReasonBetween(String value1, String value2) {
            addCriterion("DELETE_REASON_ between", value1, value2, "deleteReason");
            return (Criteria) this;
        }

        public Criteria andDeleteReasonNotBetween(String value1, String value2) {
            addCriterion("DELETE_REASON_ not between", value1, value2, "deleteReason");
            return (Criteria) this;
        }

        public Criteria andTenantIdIsNull() {
            addCriterion("TENANT_ID_ is null");
            return (Criteria) this;
        }

        public Criteria andTenantIdIsNotNull() {
            addCriterion("TENANT_ID_ is not null");
            return (Criteria) this;
        }

        public Criteria andTenantIdEqualTo(String value) {
            addCriterion("TENANT_ID_ =", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotEqualTo(String value) {
            addCriterion("TENANT_ID_ <>", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThan(String value) {
            addCriterion("TENANT_ID_ >", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThanOrEqualTo(String value) {
            addCriterion("TENANT_ID_ >=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThan(String value) {
            addCriterion("TENANT_ID_ <", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThanOrEqualTo(String value) {
            addCriterion("TENANT_ID_ <=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLike(String value) {
            addCriterion("TENANT_ID_ like", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotLike(String value) {
            addCriterion("TENANT_ID_ not like", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdIn(List<String> values) {
            addCriterion("TENANT_ID_ in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotIn(List<String> values) {
            addCriterion("TENANT_ID_ not in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdBetween(String value1, String value2) {
            addCriterion("TENANT_ID_ between", value1, value2, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotBetween(String value1, String value2) {
            addCriterion("TENANT_ID_ not between", value1, value2, "tenantId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("NAME_ is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("NAME_ is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("NAME_ =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("NAME_ <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("NAME_ >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAME_ >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("NAME_ <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("NAME_ <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("NAME_ like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("NAME_ not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("NAME_ in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("NAME_ not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("NAME_ between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("NAME_ not between", value1, value2, "name");
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