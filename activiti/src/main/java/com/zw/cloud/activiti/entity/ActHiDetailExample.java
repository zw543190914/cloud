package com.zw.cloud.activiti.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActHiDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ActHiDetailExample() {
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

        public Criteria andTypeIsNull() {
            addCriterion("TYPE_ is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("TYPE_ is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("TYPE_ =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("TYPE_ <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("TYPE_ >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE_ >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("TYPE_ <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("TYPE_ <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("TYPE_ like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("TYPE_ not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("TYPE_ in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("TYPE_ not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("TYPE_ between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("TYPE_ not between", value1, value2, "type");
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

        public Criteria andExecutionIdIsNull() {
            addCriterion("EXECUTION_ID_ is null");
            return (Criteria) this;
        }

        public Criteria andExecutionIdIsNotNull() {
            addCriterion("EXECUTION_ID_ is not null");
            return (Criteria) this;
        }

        public Criteria andExecutionIdEqualTo(String value) {
            addCriterion("EXECUTION_ID_ =", value, "executionId");
            return (Criteria) this;
        }

        public Criteria andExecutionIdNotEqualTo(String value) {
            addCriterion("EXECUTION_ID_ <>", value, "executionId");
            return (Criteria) this;
        }

        public Criteria andExecutionIdGreaterThan(String value) {
            addCriterion("EXECUTION_ID_ >", value, "executionId");
            return (Criteria) this;
        }

        public Criteria andExecutionIdGreaterThanOrEqualTo(String value) {
            addCriterion("EXECUTION_ID_ >=", value, "executionId");
            return (Criteria) this;
        }

        public Criteria andExecutionIdLessThan(String value) {
            addCriterion("EXECUTION_ID_ <", value, "executionId");
            return (Criteria) this;
        }

        public Criteria andExecutionIdLessThanOrEqualTo(String value) {
            addCriterion("EXECUTION_ID_ <=", value, "executionId");
            return (Criteria) this;
        }

        public Criteria andExecutionIdLike(String value) {
            addCriterion("EXECUTION_ID_ like", value, "executionId");
            return (Criteria) this;
        }

        public Criteria andExecutionIdNotLike(String value) {
            addCriterion("EXECUTION_ID_ not like", value, "executionId");
            return (Criteria) this;
        }

        public Criteria andExecutionIdIn(List<String> values) {
            addCriterion("EXECUTION_ID_ in", values, "executionId");
            return (Criteria) this;
        }

        public Criteria andExecutionIdNotIn(List<String> values) {
            addCriterion("EXECUTION_ID_ not in", values, "executionId");
            return (Criteria) this;
        }

        public Criteria andExecutionIdBetween(String value1, String value2) {
            addCriterion("EXECUTION_ID_ between", value1, value2, "executionId");
            return (Criteria) this;
        }

        public Criteria andExecutionIdNotBetween(String value1, String value2) {
            addCriterion("EXECUTION_ID_ not between", value1, value2, "executionId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNull() {
            addCriterion("TASK_ID_ is null");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNotNull() {
            addCriterion("TASK_ID_ is not null");
            return (Criteria) this;
        }

        public Criteria andTaskIdEqualTo(String value) {
            addCriterion("TASK_ID_ =", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotEqualTo(String value) {
            addCriterion("TASK_ID_ <>", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThan(String value) {
            addCriterion("TASK_ID_ >", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_ID_ >=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThan(String value) {
            addCriterion("TASK_ID_ <", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThanOrEqualTo(String value) {
            addCriterion("TASK_ID_ <=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLike(String value) {
            addCriterion("TASK_ID_ like", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotLike(String value) {
            addCriterion("TASK_ID_ not like", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIn(List<String> values) {
            addCriterion("TASK_ID_ in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotIn(List<String> values) {
            addCriterion("TASK_ID_ not in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdBetween(String value1, String value2) {
            addCriterion("TASK_ID_ between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotBetween(String value1, String value2) {
            addCriterion("TASK_ID_ not between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andActInstIdIsNull() {
            addCriterion("ACT_INST_ID_ is null");
            return (Criteria) this;
        }

        public Criteria andActInstIdIsNotNull() {
            addCriterion("ACT_INST_ID_ is not null");
            return (Criteria) this;
        }

        public Criteria andActInstIdEqualTo(String value) {
            addCriterion("ACT_INST_ID_ =", value, "actInstId");
            return (Criteria) this;
        }

        public Criteria andActInstIdNotEqualTo(String value) {
            addCriterion("ACT_INST_ID_ <>", value, "actInstId");
            return (Criteria) this;
        }

        public Criteria andActInstIdGreaterThan(String value) {
            addCriterion("ACT_INST_ID_ >", value, "actInstId");
            return (Criteria) this;
        }

        public Criteria andActInstIdGreaterThanOrEqualTo(String value) {
            addCriterion("ACT_INST_ID_ >=", value, "actInstId");
            return (Criteria) this;
        }

        public Criteria andActInstIdLessThan(String value) {
            addCriterion("ACT_INST_ID_ <", value, "actInstId");
            return (Criteria) this;
        }

        public Criteria andActInstIdLessThanOrEqualTo(String value) {
            addCriterion("ACT_INST_ID_ <=", value, "actInstId");
            return (Criteria) this;
        }

        public Criteria andActInstIdLike(String value) {
            addCriterion("ACT_INST_ID_ like", value, "actInstId");
            return (Criteria) this;
        }

        public Criteria andActInstIdNotLike(String value) {
            addCriterion("ACT_INST_ID_ not like", value, "actInstId");
            return (Criteria) this;
        }

        public Criteria andActInstIdIn(List<String> values) {
            addCriterion("ACT_INST_ID_ in", values, "actInstId");
            return (Criteria) this;
        }

        public Criteria andActInstIdNotIn(List<String> values) {
            addCriterion("ACT_INST_ID_ not in", values, "actInstId");
            return (Criteria) this;
        }

        public Criteria andActInstIdBetween(String value1, String value2) {
            addCriterion("ACT_INST_ID_ between", value1, value2, "actInstId");
            return (Criteria) this;
        }

        public Criteria andActInstIdNotBetween(String value1, String value2) {
            addCriterion("ACT_INST_ID_ not between", value1, value2, "actInstId");
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

        public Criteria andVarTypeIsNull() {
            addCriterion("VAR_TYPE_ is null");
            return (Criteria) this;
        }

        public Criteria andVarTypeIsNotNull() {
            addCriterion("VAR_TYPE_ is not null");
            return (Criteria) this;
        }

        public Criteria andVarTypeEqualTo(String value) {
            addCriterion("VAR_TYPE_ =", value, "varType");
            return (Criteria) this;
        }

        public Criteria andVarTypeNotEqualTo(String value) {
            addCriterion("VAR_TYPE_ <>", value, "varType");
            return (Criteria) this;
        }

        public Criteria andVarTypeGreaterThan(String value) {
            addCriterion("VAR_TYPE_ >", value, "varType");
            return (Criteria) this;
        }

        public Criteria andVarTypeGreaterThanOrEqualTo(String value) {
            addCriterion("VAR_TYPE_ >=", value, "varType");
            return (Criteria) this;
        }

        public Criteria andVarTypeLessThan(String value) {
            addCriterion("VAR_TYPE_ <", value, "varType");
            return (Criteria) this;
        }

        public Criteria andVarTypeLessThanOrEqualTo(String value) {
            addCriterion("VAR_TYPE_ <=", value, "varType");
            return (Criteria) this;
        }

        public Criteria andVarTypeLike(String value) {
            addCriterion("VAR_TYPE_ like", value, "varType");
            return (Criteria) this;
        }

        public Criteria andVarTypeNotLike(String value) {
            addCriterion("VAR_TYPE_ not like", value, "varType");
            return (Criteria) this;
        }

        public Criteria andVarTypeIn(List<String> values) {
            addCriterion("VAR_TYPE_ in", values, "varType");
            return (Criteria) this;
        }

        public Criteria andVarTypeNotIn(List<String> values) {
            addCriterion("VAR_TYPE_ not in", values, "varType");
            return (Criteria) this;
        }

        public Criteria andVarTypeBetween(String value1, String value2) {
            addCriterion("VAR_TYPE_ between", value1, value2, "varType");
            return (Criteria) this;
        }

        public Criteria andVarTypeNotBetween(String value1, String value2) {
            addCriterion("VAR_TYPE_ not between", value1, value2, "varType");
            return (Criteria) this;
        }

        public Criteria andRevIsNull() {
            addCriterion("REV_ is null");
            return (Criteria) this;
        }

        public Criteria andRevIsNotNull() {
            addCriterion("REV_ is not null");
            return (Criteria) this;
        }

        public Criteria andRevEqualTo(Integer value) {
            addCriterion("REV_ =", value, "rev");
            return (Criteria) this;
        }

        public Criteria andRevNotEqualTo(Integer value) {
            addCriterion("REV_ <>", value, "rev");
            return (Criteria) this;
        }

        public Criteria andRevGreaterThan(Integer value) {
            addCriterion("REV_ >", value, "rev");
            return (Criteria) this;
        }

        public Criteria andRevGreaterThanOrEqualTo(Integer value) {
            addCriterion("REV_ >=", value, "rev");
            return (Criteria) this;
        }

        public Criteria andRevLessThan(Integer value) {
            addCriterion("REV_ <", value, "rev");
            return (Criteria) this;
        }

        public Criteria andRevLessThanOrEqualTo(Integer value) {
            addCriterion("REV_ <=", value, "rev");
            return (Criteria) this;
        }

        public Criteria andRevIn(List<Integer> values) {
            addCriterion("REV_ in", values, "rev");
            return (Criteria) this;
        }

        public Criteria andRevNotIn(List<Integer> values) {
            addCriterion("REV_ not in", values, "rev");
            return (Criteria) this;
        }

        public Criteria andRevBetween(Integer value1, Integer value2) {
            addCriterion("REV_ between", value1, value2, "rev");
            return (Criteria) this;
        }

        public Criteria andRevNotBetween(Integer value1, Integer value2) {
            addCriterion("REV_ not between", value1, value2, "rev");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("TIME_ is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("TIME_ is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(Date value) {
            addCriterion("TIME_ =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(Date value) {
            addCriterion("TIME_ <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(Date value) {
            addCriterion("TIME_ >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("TIME_ >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(Date value) {
            addCriterion("TIME_ <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(Date value) {
            addCriterion("TIME_ <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<Date> values) {
            addCriterion("TIME_ in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<Date> values) {
            addCriterion("TIME_ not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(Date value1, Date value2) {
            addCriterion("TIME_ between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(Date value1, Date value2) {
            addCriterion("TIME_ not between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andBytearrayIdIsNull() {
            addCriterion("BYTEARRAY_ID_ is null");
            return (Criteria) this;
        }

        public Criteria andBytearrayIdIsNotNull() {
            addCriterion("BYTEARRAY_ID_ is not null");
            return (Criteria) this;
        }

        public Criteria andBytearrayIdEqualTo(String value) {
            addCriterion("BYTEARRAY_ID_ =", value, "bytearrayId");
            return (Criteria) this;
        }

        public Criteria andBytearrayIdNotEqualTo(String value) {
            addCriterion("BYTEARRAY_ID_ <>", value, "bytearrayId");
            return (Criteria) this;
        }

        public Criteria andBytearrayIdGreaterThan(String value) {
            addCriterion("BYTEARRAY_ID_ >", value, "bytearrayId");
            return (Criteria) this;
        }

        public Criteria andBytearrayIdGreaterThanOrEqualTo(String value) {
            addCriterion("BYTEARRAY_ID_ >=", value, "bytearrayId");
            return (Criteria) this;
        }

        public Criteria andBytearrayIdLessThan(String value) {
            addCriterion("BYTEARRAY_ID_ <", value, "bytearrayId");
            return (Criteria) this;
        }

        public Criteria andBytearrayIdLessThanOrEqualTo(String value) {
            addCriterion("BYTEARRAY_ID_ <=", value, "bytearrayId");
            return (Criteria) this;
        }

        public Criteria andBytearrayIdLike(String value) {
            addCriterion("BYTEARRAY_ID_ like", value, "bytearrayId");
            return (Criteria) this;
        }

        public Criteria andBytearrayIdNotLike(String value) {
            addCriterion("BYTEARRAY_ID_ not like", value, "bytearrayId");
            return (Criteria) this;
        }

        public Criteria andBytearrayIdIn(List<String> values) {
            addCriterion("BYTEARRAY_ID_ in", values, "bytearrayId");
            return (Criteria) this;
        }

        public Criteria andBytearrayIdNotIn(List<String> values) {
            addCriterion("BYTEARRAY_ID_ not in", values, "bytearrayId");
            return (Criteria) this;
        }

        public Criteria andBytearrayIdBetween(String value1, String value2) {
            addCriterion("BYTEARRAY_ID_ between", value1, value2, "bytearrayId");
            return (Criteria) this;
        }

        public Criteria andBytearrayIdNotBetween(String value1, String value2) {
            addCriterion("BYTEARRAY_ID_ not between", value1, value2, "bytearrayId");
            return (Criteria) this;
        }

        public Criteria andDoubleIsNull() {
            addCriterion("DOUBLE_ is null");
            return (Criteria) this;
        }

        public Criteria andDoubleIsNotNull() {
            addCriterion("DOUBLE_ is not null");
            return (Criteria) this;
        }

        public Criteria andDoubleEqualTo(Double value) {
            addCriterion("DOUBLE_ =", value, "double");
            return (Criteria) this;
        }

        public Criteria andDoubleNotEqualTo(Double value) {
            addCriterion("DOUBLE_ <>", value, "double");
            return (Criteria) this;
        }

        public Criteria andDoubleGreaterThan(Double value) {
            addCriterion("DOUBLE_ >", value, "double");
            return (Criteria) this;
        }

        public Criteria andDoubleGreaterThanOrEqualTo(Double value) {
            addCriterion("DOUBLE_ >=", value, "double");
            return (Criteria) this;
        }

        public Criteria andDoubleLessThan(Double value) {
            addCriterion("DOUBLE_ <", value, "double");
            return (Criteria) this;
        }

        public Criteria andDoubleLessThanOrEqualTo(Double value) {
            addCriterion("DOUBLE_ <=", value, "double");
            return (Criteria) this;
        }

        public Criteria andDoubleIn(List<Double> values) {
            addCriterion("DOUBLE_ in", values, "double");
            return (Criteria) this;
        }

        public Criteria andDoubleNotIn(List<Double> values) {
            addCriterion("DOUBLE_ not in", values, "double");
            return (Criteria) this;
        }

        public Criteria andDoubleBetween(Double value1, Double value2) {
            addCriterion("DOUBLE_ between", value1, value2, "double");
            return (Criteria) this;
        }

        public Criteria andDoubleNotBetween(Double value1, Double value2) {
            addCriterion("DOUBLE_ not between", value1, value2, "double");
            return (Criteria) this;
        }

        public Criteria andLongIsNull() {
            addCriterion("LONG_ is null");
            return (Criteria) this;
        }

        public Criteria andLongIsNotNull() {
            addCriterion("LONG_ is not null");
            return (Criteria) this;
        }

        public Criteria andLongEqualTo(Long value) {
            addCriterion("LONG_ =", value, "long");
            return (Criteria) this;
        }

        public Criteria andLongNotEqualTo(Long value) {
            addCriterion("LONG_ <>", value, "long");
            return (Criteria) this;
        }

        public Criteria andLongGreaterThan(Long value) {
            addCriterion("LONG_ >", value, "long");
            return (Criteria) this;
        }

        public Criteria andLongGreaterThanOrEqualTo(Long value) {
            addCriterion("LONG_ >=", value, "long");
            return (Criteria) this;
        }

        public Criteria andLongLessThan(Long value) {
            addCriterion("LONG_ <", value, "long");
            return (Criteria) this;
        }

        public Criteria andLongLessThanOrEqualTo(Long value) {
            addCriterion("LONG_ <=", value, "long");
            return (Criteria) this;
        }

        public Criteria andLongIn(List<Long> values) {
            addCriterion("LONG_ in", values, "long");
            return (Criteria) this;
        }

        public Criteria andLongNotIn(List<Long> values) {
            addCriterion("LONG_ not in", values, "long");
            return (Criteria) this;
        }

        public Criteria andLongBetween(Long value1, Long value2) {
            addCriterion("LONG_ between", value1, value2, "long");
            return (Criteria) this;
        }

        public Criteria andLongNotBetween(Long value1, Long value2) {
            addCriterion("LONG_ not between", value1, value2, "long");
            return (Criteria) this;
        }

        public Criteria andTextIsNull() {
            addCriterion("TEXT_ is null");
            return (Criteria) this;
        }

        public Criteria andTextIsNotNull() {
            addCriterion("TEXT_ is not null");
            return (Criteria) this;
        }

        public Criteria andTextEqualTo(String value) {
            addCriterion("TEXT_ =", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextNotEqualTo(String value) {
            addCriterion("TEXT_ <>", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextGreaterThan(String value) {
            addCriterion("TEXT_ >", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextGreaterThanOrEqualTo(String value) {
            addCriterion("TEXT_ >=", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextLessThan(String value) {
            addCriterion("TEXT_ <", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextLessThanOrEqualTo(String value) {
            addCriterion("TEXT_ <=", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextLike(String value) {
            addCriterion("TEXT_ like", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextNotLike(String value) {
            addCriterion("TEXT_ not like", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextIn(List<String> values) {
            addCriterion("TEXT_ in", values, "text");
            return (Criteria) this;
        }

        public Criteria andTextNotIn(List<String> values) {
            addCriterion("TEXT_ not in", values, "text");
            return (Criteria) this;
        }

        public Criteria andTextBetween(String value1, String value2) {
            addCriterion("TEXT_ between", value1, value2, "text");
            return (Criteria) this;
        }

        public Criteria andTextNotBetween(String value1, String value2) {
            addCriterion("TEXT_ not between", value1, value2, "text");
            return (Criteria) this;
        }

        public Criteria andText2IsNull() {
            addCriterion("TEXT2_ is null");
            return (Criteria) this;
        }

        public Criteria andText2IsNotNull() {
            addCriterion("TEXT2_ is not null");
            return (Criteria) this;
        }

        public Criteria andText2EqualTo(String value) {
            addCriterion("TEXT2_ =", value, "text2");
            return (Criteria) this;
        }

        public Criteria andText2NotEqualTo(String value) {
            addCriterion("TEXT2_ <>", value, "text2");
            return (Criteria) this;
        }

        public Criteria andText2GreaterThan(String value) {
            addCriterion("TEXT2_ >", value, "text2");
            return (Criteria) this;
        }

        public Criteria andText2GreaterThanOrEqualTo(String value) {
            addCriterion("TEXT2_ >=", value, "text2");
            return (Criteria) this;
        }

        public Criteria andText2LessThan(String value) {
            addCriterion("TEXT2_ <", value, "text2");
            return (Criteria) this;
        }

        public Criteria andText2LessThanOrEqualTo(String value) {
            addCriterion("TEXT2_ <=", value, "text2");
            return (Criteria) this;
        }

        public Criteria andText2Like(String value) {
            addCriterion("TEXT2_ like", value, "text2");
            return (Criteria) this;
        }

        public Criteria andText2NotLike(String value) {
            addCriterion("TEXT2_ not like", value, "text2");
            return (Criteria) this;
        }

        public Criteria andText2In(List<String> values) {
            addCriterion("TEXT2_ in", values, "text2");
            return (Criteria) this;
        }

        public Criteria andText2NotIn(List<String> values) {
            addCriterion("TEXT2_ not in", values, "text2");
            return (Criteria) this;
        }

        public Criteria andText2Between(String value1, String value2) {
            addCriterion("TEXT2_ between", value1, value2, "text2");
            return (Criteria) this;
        }

        public Criteria andText2NotBetween(String value1, String value2) {
            addCriterion("TEXT2_ not between", value1, value2, "text2");
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