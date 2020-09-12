package com.zw.cloud.db.entity;

import java.util.ArrayList;
import java.util.List;

public class TcExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TcExample() {
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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOneIsNull() {
            addCriterion("one is null");
            return (Criteria) this;
        }

        public Criteria andOneIsNotNull() {
            addCriterion("one is not null");
            return (Criteria) this;
        }

        public Criteria andOneEqualTo(Integer value) {
            addCriterion("one =", value, "one");
            return (Criteria) this;
        }

        public Criteria andOneNotEqualTo(Integer value) {
            addCriterion("one <>", value, "one");
            return (Criteria) this;
        }

        public Criteria andOneGreaterThan(Integer value) {
            addCriterion("one >", value, "one");
            return (Criteria) this;
        }

        public Criteria andOneGreaterThanOrEqualTo(Integer value) {
            addCriterion("one >=", value, "one");
            return (Criteria) this;
        }

        public Criteria andOneLessThan(Integer value) {
            addCriterion("one <", value, "one");
            return (Criteria) this;
        }

        public Criteria andOneLessThanOrEqualTo(Integer value) {
            addCriterion("one <=", value, "one");
            return (Criteria) this;
        }

        public Criteria andOneIn(List<Integer> values) {
            addCriterion("one in", values, "one");
            return (Criteria) this;
        }

        public Criteria andOneNotIn(List<Integer> values) {
            addCriterion("one not in", values, "one");
            return (Criteria) this;
        }

        public Criteria andOneBetween(Integer value1, Integer value2) {
            addCriterion("one between", value1, value2, "one");
            return (Criteria) this;
        }

        public Criteria andOneNotBetween(Integer value1, Integer value2) {
            addCriterion("one not between", value1, value2, "one");
            return (Criteria) this;
        }

        public Criteria andTwoIsNull() {
            addCriterion("two is null");
            return (Criteria) this;
        }

        public Criteria andTwoIsNotNull() {
            addCriterion("two is not null");
            return (Criteria) this;
        }

        public Criteria andTwoEqualTo(Integer value) {
            addCriterion("two =", value, "two");
            return (Criteria) this;
        }

        public Criteria andTwoNotEqualTo(Integer value) {
            addCriterion("two <>", value, "two");
            return (Criteria) this;
        }

        public Criteria andTwoGreaterThan(Integer value) {
            addCriterion("two >", value, "two");
            return (Criteria) this;
        }

        public Criteria andTwoGreaterThanOrEqualTo(Integer value) {
            addCriterion("two >=", value, "two");
            return (Criteria) this;
        }

        public Criteria andTwoLessThan(Integer value) {
            addCriterion("two <", value, "two");
            return (Criteria) this;
        }

        public Criteria andTwoLessThanOrEqualTo(Integer value) {
            addCriterion("two <=", value, "two");
            return (Criteria) this;
        }

        public Criteria andTwoIn(List<Integer> values) {
            addCriterion("two in", values, "two");
            return (Criteria) this;
        }

        public Criteria andTwoNotIn(List<Integer> values) {
            addCriterion("two not in", values, "two");
            return (Criteria) this;
        }

        public Criteria andTwoBetween(Integer value1, Integer value2) {
            addCriterion("two between", value1, value2, "two");
            return (Criteria) this;
        }

        public Criteria andTwoNotBetween(Integer value1, Integer value2) {
            addCriterion("two not between", value1, value2, "two");
            return (Criteria) this;
        }

        public Criteria andThreeIsNull() {
            addCriterion("three is null");
            return (Criteria) this;
        }

        public Criteria andThreeIsNotNull() {
            addCriterion("three is not null");
            return (Criteria) this;
        }

        public Criteria andThreeEqualTo(Integer value) {
            addCriterion("three =", value, "three");
            return (Criteria) this;
        }

        public Criteria andThreeNotEqualTo(Integer value) {
            addCriterion("three <>", value, "three");
            return (Criteria) this;
        }

        public Criteria andThreeGreaterThan(Integer value) {
            addCriterion("three >", value, "three");
            return (Criteria) this;
        }

        public Criteria andThreeGreaterThanOrEqualTo(Integer value) {
            addCriterion("three >=", value, "three");
            return (Criteria) this;
        }

        public Criteria andThreeLessThan(Integer value) {
            addCriterion("three <", value, "three");
            return (Criteria) this;
        }

        public Criteria andThreeLessThanOrEqualTo(Integer value) {
            addCriterion("three <=", value, "three");
            return (Criteria) this;
        }

        public Criteria andThreeIn(List<Integer> values) {
            addCriterion("three in", values, "three");
            return (Criteria) this;
        }

        public Criteria andThreeNotIn(List<Integer> values) {
            addCriterion("three not in", values, "three");
            return (Criteria) this;
        }

        public Criteria andThreeBetween(Integer value1, Integer value2) {
            addCriterion("three between", value1, value2, "three");
            return (Criteria) this;
        }

        public Criteria andThreeNotBetween(Integer value1, Integer value2) {
            addCriterion("three not between", value1, value2, "three");
            return (Criteria) this;
        }

        public Criteria andFourIsNull() {
            addCriterion("four is null");
            return (Criteria) this;
        }

        public Criteria andFourIsNotNull() {
            addCriterion("four is not null");
            return (Criteria) this;
        }

        public Criteria andFourEqualTo(Integer value) {
            addCriterion("four =", value, "four");
            return (Criteria) this;
        }

        public Criteria andFourNotEqualTo(Integer value) {
            addCriterion("four <>", value, "four");
            return (Criteria) this;
        }

        public Criteria andFourGreaterThan(Integer value) {
            addCriterion("four >", value, "four");
            return (Criteria) this;
        }

        public Criteria andFourGreaterThanOrEqualTo(Integer value) {
            addCriterion("four >=", value, "four");
            return (Criteria) this;
        }

        public Criteria andFourLessThan(Integer value) {
            addCriterion("four <", value, "four");
            return (Criteria) this;
        }

        public Criteria andFourLessThanOrEqualTo(Integer value) {
            addCriterion("four <=", value, "four");
            return (Criteria) this;
        }

        public Criteria andFourIn(List<Integer> values) {
            addCriterion("four in", values, "four");
            return (Criteria) this;
        }

        public Criteria andFourNotIn(List<Integer> values) {
            addCriterion("four not in", values, "four");
            return (Criteria) this;
        }

        public Criteria andFourBetween(Integer value1, Integer value2) {
            addCriterion("four between", value1, value2, "four");
            return (Criteria) this;
        }

        public Criteria andFourNotBetween(Integer value1, Integer value2) {
            addCriterion("four not between", value1, value2, "four");
            return (Criteria) this;
        }

        public Criteria andFiveIsNull() {
            addCriterion("five is null");
            return (Criteria) this;
        }

        public Criteria andFiveIsNotNull() {
            addCriterion("five is not null");
            return (Criteria) this;
        }

        public Criteria andFiveEqualTo(Integer value) {
            addCriterion("five =", value, "five");
            return (Criteria) this;
        }

        public Criteria andFiveNotEqualTo(Integer value) {
            addCriterion("five <>", value, "five");
            return (Criteria) this;
        }

        public Criteria andFiveGreaterThan(Integer value) {
            addCriterion("five >", value, "five");
            return (Criteria) this;
        }

        public Criteria andFiveGreaterThanOrEqualTo(Integer value) {
            addCriterion("five >=", value, "five");
            return (Criteria) this;
        }

        public Criteria andFiveLessThan(Integer value) {
            addCriterion("five <", value, "five");
            return (Criteria) this;
        }

        public Criteria andFiveLessThanOrEqualTo(Integer value) {
            addCriterion("five <=", value, "five");
            return (Criteria) this;
        }

        public Criteria andFiveIn(List<Integer> values) {
            addCriterion("five in", values, "five");
            return (Criteria) this;
        }

        public Criteria andFiveNotIn(List<Integer> values) {
            addCriterion("five not in", values, "five");
            return (Criteria) this;
        }

        public Criteria andFiveBetween(Integer value1, Integer value2) {
            addCriterion("five between", value1, value2, "five");
            return (Criteria) this;
        }

        public Criteria andFiveNotBetween(Integer value1, Integer value2) {
            addCriterion("five not between", value1, value2, "five");
            return (Criteria) this;
        }

        public Criteria andSixIsNull() {
            addCriterion("six is null");
            return (Criteria) this;
        }

        public Criteria andSixIsNotNull() {
            addCriterion("six is not null");
            return (Criteria) this;
        }

        public Criteria andSixEqualTo(Integer value) {
            addCriterion("six =", value, "six");
            return (Criteria) this;
        }

        public Criteria andSixNotEqualTo(Integer value) {
            addCriterion("six <>", value, "six");
            return (Criteria) this;
        }

        public Criteria andSixGreaterThan(Integer value) {
            addCriterion("six >", value, "six");
            return (Criteria) this;
        }

        public Criteria andSixGreaterThanOrEqualTo(Integer value) {
            addCriterion("six >=", value, "six");
            return (Criteria) this;
        }

        public Criteria andSixLessThan(Integer value) {
            addCriterion("six <", value, "six");
            return (Criteria) this;
        }

        public Criteria andSixLessThanOrEqualTo(Integer value) {
            addCriterion("six <=", value, "six");
            return (Criteria) this;
        }

        public Criteria andSixIn(List<Integer> values) {
            addCriterion("six in", values, "six");
            return (Criteria) this;
        }

        public Criteria andSixNotIn(List<Integer> values) {
            addCriterion("six not in", values, "six");
            return (Criteria) this;
        }

        public Criteria andSixBetween(Integer value1, Integer value2) {
            addCriterion("six between", value1, value2, "six");
            return (Criteria) this;
        }

        public Criteria andSixNotBetween(Integer value1, Integer value2) {
            addCriterion("six not between", value1, value2, "six");
            return (Criteria) this;
        }

        public Criteria andSevenIsNull() {
            addCriterion("seven is null");
            return (Criteria) this;
        }

        public Criteria andSevenIsNotNull() {
            addCriterion("seven is not null");
            return (Criteria) this;
        }

        public Criteria andSevenEqualTo(Integer value) {
            addCriterion("seven =", value, "seven");
            return (Criteria) this;
        }

        public Criteria andSevenNotEqualTo(Integer value) {
            addCriterion("seven <>", value, "seven");
            return (Criteria) this;
        }

        public Criteria andSevenGreaterThan(Integer value) {
            addCriterion("seven >", value, "seven");
            return (Criteria) this;
        }

        public Criteria andSevenGreaterThanOrEqualTo(Integer value) {
            addCriterion("seven >=", value, "seven");
            return (Criteria) this;
        }

        public Criteria andSevenLessThan(Integer value) {
            addCriterion("seven <", value, "seven");
            return (Criteria) this;
        }

        public Criteria andSevenLessThanOrEqualTo(Integer value) {
            addCriterion("seven <=", value, "seven");
            return (Criteria) this;
        }

        public Criteria andSevenIn(List<Integer> values) {
            addCriterion("seven in", values, "seven");
            return (Criteria) this;
        }

        public Criteria andSevenNotIn(List<Integer> values) {
            addCriterion("seven not in", values, "seven");
            return (Criteria) this;
        }

        public Criteria andSevenBetween(Integer value1, Integer value2) {
            addCriterion("seven between", value1, value2, "seven");
            return (Criteria) this;
        }

        public Criteria andSevenNotBetween(Integer value1, Integer value2) {
            addCriterion("seven not between", value1, value2, "seven");
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