package com.zw.cloud.activiti.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActHiCommentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ActHiCommentExample() {
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

        public Criteria andUserIdIsNull() {
            addCriterion("USER_ID_ is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("USER_ID_ is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("USER_ID_ =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("USER_ID_ <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("USER_ID_ >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("USER_ID_ >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("USER_ID_ <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("USER_ID_ <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("USER_ID_ like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("USER_ID_ not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("USER_ID_ in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("USER_ID_ not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("USER_ID_ between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("USER_ID_ not between", value1, value2, "userId");
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

        public Criteria andActionIsNull() {
            addCriterion("ACTION_ is null");
            return (Criteria) this;
        }

        public Criteria andActionIsNotNull() {
            addCriterion("ACTION_ is not null");
            return (Criteria) this;
        }

        public Criteria andActionEqualTo(String value) {
            addCriterion("ACTION_ =", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotEqualTo(String value) {
            addCriterion("ACTION_ <>", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionGreaterThan(String value) {
            addCriterion("ACTION_ >", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionGreaterThanOrEqualTo(String value) {
            addCriterion("ACTION_ >=", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionLessThan(String value) {
            addCriterion("ACTION_ <", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionLessThanOrEqualTo(String value) {
            addCriterion("ACTION_ <=", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionLike(String value) {
            addCriterion("ACTION_ like", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotLike(String value) {
            addCriterion("ACTION_ not like", value, "action");
            return (Criteria) this;
        }

        public Criteria andActionIn(List<String> values) {
            addCriterion("ACTION_ in", values, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotIn(List<String> values) {
            addCriterion("ACTION_ not in", values, "action");
            return (Criteria) this;
        }

        public Criteria andActionBetween(String value1, String value2) {
            addCriterion("ACTION_ between", value1, value2, "action");
            return (Criteria) this;
        }

        public Criteria andActionNotBetween(String value1, String value2) {
            addCriterion("ACTION_ not between", value1, value2, "action");
            return (Criteria) this;
        }

        public Criteria andMessageIsNull() {
            addCriterion("MESSAGE_ is null");
            return (Criteria) this;
        }

        public Criteria andMessageIsNotNull() {
            addCriterion("MESSAGE_ is not null");
            return (Criteria) this;
        }

        public Criteria andMessageEqualTo(String value) {
            addCriterion("MESSAGE_ =", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotEqualTo(String value) {
            addCriterion("MESSAGE_ <>", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageGreaterThan(String value) {
            addCriterion("MESSAGE_ >", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageGreaterThanOrEqualTo(String value) {
            addCriterion("MESSAGE_ >=", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLessThan(String value) {
            addCriterion("MESSAGE_ <", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLessThanOrEqualTo(String value) {
            addCriterion("MESSAGE_ <=", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageLike(String value) {
            addCriterion("MESSAGE_ like", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotLike(String value) {
            addCriterion("MESSAGE_ not like", value, "message");
            return (Criteria) this;
        }

        public Criteria andMessageIn(List<String> values) {
            addCriterion("MESSAGE_ in", values, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotIn(List<String> values) {
            addCriterion("MESSAGE_ not in", values, "message");
            return (Criteria) this;
        }

        public Criteria andMessageBetween(String value1, String value2) {
            addCriterion("MESSAGE_ between", value1, value2, "message");
            return (Criteria) this;
        }

        public Criteria andMessageNotBetween(String value1, String value2) {
            addCriterion("MESSAGE_ not between", value1, value2, "message");
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