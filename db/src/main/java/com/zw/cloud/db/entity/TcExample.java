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

        public Criteria andFirstIsNull() {
            addCriterion("first is null");
            return (Criteria) this;
        }

        public Criteria andFirstIsNotNull() {
            addCriterion("first is not null");
            return (Criteria) this;
        }

        public Criteria andFirstEqualTo(Integer value) {
            addCriterion("first =", value, "first");
            return (Criteria) this;
        }

        public Criteria andFirstNotEqualTo(Integer value) {
            addCriterion("first <>", value, "first");
            return (Criteria) this;
        }

        public Criteria andFirstGreaterThan(Integer value) {
            addCriterion("first >", value, "first");
            return (Criteria) this;
        }

        public Criteria andFirstGreaterThanOrEqualTo(Integer value) {
            addCriterion("first >=", value, "first");
            return (Criteria) this;
        }

        public Criteria andFirstLessThan(Integer value) {
            addCriterion("first <", value, "first");
            return (Criteria) this;
        }

        public Criteria andFirstLessThanOrEqualTo(Integer value) {
            addCriterion("first <=", value, "first");
            return (Criteria) this;
        }

        public Criteria andFirstIn(List<Integer> values) {
            addCriterion("first in", values, "first");
            return (Criteria) this;
        }

        public Criteria andFirstNotIn(List<Integer> values) {
            addCriterion("first not in", values, "first");
            return (Criteria) this;
        }

        public Criteria andFirstBetween(Integer value1, Integer value2) {
            addCriterion("first between", value1, value2, "first");
            return (Criteria) this;
        }

        public Criteria andFirstNotBetween(Integer value1, Integer value2) {
            addCriterion("first not between", value1, value2, "first");
            return (Criteria) this;
        }

        public Criteria andSecondIsNull() {
            addCriterion("second is null");
            return (Criteria) this;
        }

        public Criteria andSecondIsNotNull() {
            addCriterion("second is not null");
            return (Criteria) this;
        }

        public Criteria andSecondEqualTo(Integer value) {
            addCriterion("second =", value, "second");
            return (Criteria) this;
        }

        public Criteria andSecondNotEqualTo(Integer value) {
            addCriterion("second <>", value, "second");
            return (Criteria) this;
        }

        public Criteria andSecondGreaterThan(Integer value) {
            addCriterion("second >", value, "second");
            return (Criteria) this;
        }

        public Criteria andSecondGreaterThanOrEqualTo(Integer value) {
            addCriterion("second >=", value, "second");
            return (Criteria) this;
        }

        public Criteria andSecondLessThan(Integer value) {
            addCriterion("second <", value, "second");
            return (Criteria) this;
        }

        public Criteria andSecondLessThanOrEqualTo(Integer value) {
            addCriterion("second <=", value, "second");
            return (Criteria) this;
        }

        public Criteria andSecondIn(List<Integer> values) {
            addCriterion("second in", values, "second");
            return (Criteria) this;
        }

        public Criteria andSecondNotIn(List<Integer> values) {
            addCriterion("second not in", values, "second");
            return (Criteria) this;
        }

        public Criteria andSecondBetween(Integer value1, Integer value2) {
            addCriterion("second between", value1, value2, "second");
            return (Criteria) this;
        }

        public Criteria andSecondNotBetween(Integer value1, Integer value2) {
            addCriterion("second not between", value1, value2, "second");
            return (Criteria) this;
        }

        public Criteria andThirdIsNull() {
            addCriterion("third is null");
            return (Criteria) this;
        }

        public Criteria andThirdIsNotNull() {
            addCriterion("third is not null");
            return (Criteria) this;
        }

        public Criteria andThirdEqualTo(Integer value) {
            addCriterion("third =", value, "third");
            return (Criteria) this;
        }

        public Criteria andThirdNotEqualTo(Integer value) {
            addCriterion("third <>", value, "third");
            return (Criteria) this;
        }

        public Criteria andThirdGreaterThan(Integer value) {
            addCriterion("third >", value, "third");
            return (Criteria) this;
        }

        public Criteria andThirdGreaterThanOrEqualTo(Integer value) {
            addCriterion("third >=", value, "third");
            return (Criteria) this;
        }

        public Criteria andThirdLessThan(Integer value) {
            addCriterion("third <", value, "third");
            return (Criteria) this;
        }

        public Criteria andThirdLessThanOrEqualTo(Integer value) {
            addCriterion("third <=", value, "third");
            return (Criteria) this;
        }

        public Criteria andThirdIn(List<Integer> values) {
            addCriterion("third in", values, "third");
            return (Criteria) this;
        }

        public Criteria andThirdNotIn(List<Integer> values) {
            addCriterion("third not in", values, "third");
            return (Criteria) this;
        }

        public Criteria andThirdBetween(Integer value1, Integer value2) {
            addCriterion("third between", value1, value2, "third");
            return (Criteria) this;
        }

        public Criteria andThirdNotBetween(Integer value1, Integer value2) {
            addCriterion("third not between", value1, value2, "third");
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

        public Criteria andBlueFirstIsNull() {
            addCriterion("blue_first is null");
            return (Criteria) this;
        }

        public Criteria andBlueFirstIsNotNull() {
            addCriterion("blue_first is not null");
            return (Criteria) this;
        }

        public Criteria andBlueFirstEqualTo(Integer value) {
            addCriterion("blue_first =", value, "blueFirst");
            return (Criteria) this;
        }

        public Criteria andBlueFirstNotEqualTo(Integer value) {
            addCriterion("blue_first <>", value, "blueFirst");
            return (Criteria) this;
        }

        public Criteria andBlueFirstGreaterThan(Integer value) {
            addCriterion("blue_first >", value, "blueFirst");
            return (Criteria) this;
        }

        public Criteria andBlueFirstGreaterThanOrEqualTo(Integer value) {
            addCriterion("blue_first >=", value, "blueFirst");
            return (Criteria) this;
        }

        public Criteria andBlueFirstLessThan(Integer value) {
            addCriterion("blue_first <", value, "blueFirst");
            return (Criteria) this;
        }

        public Criteria andBlueFirstLessThanOrEqualTo(Integer value) {
            addCriterion("blue_first <=", value, "blueFirst");
            return (Criteria) this;
        }

        public Criteria andBlueFirstIn(List<Integer> values) {
            addCriterion("blue_first in", values, "blueFirst");
            return (Criteria) this;
        }

        public Criteria andBlueFirstNotIn(List<Integer> values) {
            addCriterion("blue_first not in", values, "blueFirst");
            return (Criteria) this;
        }

        public Criteria andBlueFirstBetween(Integer value1, Integer value2) {
            addCriterion("blue_first between", value1, value2, "blueFirst");
            return (Criteria) this;
        }

        public Criteria andBlueFirstNotBetween(Integer value1, Integer value2) {
            addCriterion("blue_first not between", value1, value2, "blueFirst");
            return (Criteria) this;
        }

        public Criteria andBlueSecondIsNull() {
            addCriterion("blue_second is null");
            return (Criteria) this;
        }

        public Criteria andBlueSecondIsNotNull() {
            addCriterion("blue_second is not null");
            return (Criteria) this;
        }

        public Criteria andBlueSecondEqualTo(Integer value) {
            addCriterion("blue_second =", value, "blueSecond");
            return (Criteria) this;
        }

        public Criteria andBlueSecondNotEqualTo(Integer value) {
            addCriterion("blue_second <>", value, "blueSecond");
            return (Criteria) this;
        }

        public Criteria andBlueSecondGreaterThan(Integer value) {
            addCriterion("blue_second >", value, "blueSecond");
            return (Criteria) this;
        }

        public Criteria andBlueSecondGreaterThanOrEqualTo(Integer value) {
            addCriterion("blue_second >=", value, "blueSecond");
            return (Criteria) this;
        }

        public Criteria andBlueSecondLessThan(Integer value) {
            addCriterion("blue_second <", value, "blueSecond");
            return (Criteria) this;
        }

        public Criteria andBlueSecondLessThanOrEqualTo(Integer value) {
            addCriterion("blue_second <=", value, "blueSecond");
            return (Criteria) this;
        }

        public Criteria andBlueSecondIn(List<Integer> values) {
            addCriterion("blue_second in", values, "blueSecond");
            return (Criteria) this;
        }

        public Criteria andBlueSecondNotIn(List<Integer> values) {
            addCriterion("blue_second not in", values, "blueSecond");
            return (Criteria) this;
        }

        public Criteria andBlueSecondBetween(Integer value1, Integer value2) {
            addCriterion("blue_second between", value1, value2, "blueSecond");
            return (Criteria) this;
        }

        public Criteria andBlueSecondNotBetween(Integer value1, Integer value2) {
            addCriterion("blue_second not between", value1, value2, "blueSecond");
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