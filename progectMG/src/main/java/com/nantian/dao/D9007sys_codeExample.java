package com.nantian.dao;

import java.util.ArrayList;
import java.util.List;

public class D9007sys_codeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public D9007sys_codeExample() {
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

    public void setLimitStart(Integer limitStart) {
        this.limitStart=limitStart;
    }

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(Integer limitEnd) {
        this.limitEnd=limitEnd;
    }

    public Integer getLimitEnd() {
        return limitEnd;
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

        public Criteria andF9007idIsNull() {
            addCriterion("f9007id is null");
            return (Criteria) this;
        }

        public Criteria andF9007idIsNotNull() {
            addCriterion("f9007id is not null");
            return (Criteria) this;
        }

        public Criteria andF9007idEqualTo(String value) {
            addCriterion("f9007id =", value, "f9007id");
            return (Criteria) this;
        }

        public Criteria andF9007idNotEqualTo(String value) {
            addCriterion("f9007id <>", value, "f9007id");
            return (Criteria) this;
        }

        public Criteria andF9007idGreaterThan(String value) {
            addCriterion("f9007id >", value, "f9007id");
            return (Criteria) this;
        }

        public Criteria andF9007idGreaterThanOrEqualTo(String value) {
            addCriterion("f9007id >=", value, "f9007id");
            return (Criteria) this;
        }

        public Criteria andF9007idLessThan(String value) {
            addCriterion("f9007id <", value, "f9007id");
            return (Criteria) this;
        }

        public Criteria andF9007idLessThanOrEqualTo(String value) {
            addCriterion("f9007id <=", value, "f9007id");
            return (Criteria) this;
        }

        public Criteria andF9007idLike(String value) {
            addCriterion("f9007id like", value, "f9007id");
            return (Criteria) this;
        }

        public Criteria andF9007idNotLike(String value) {
            addCriterion("f9007id not like", value, "f9007id");
            return (Criteria) this;
        }

        public Criteria andF9007idIn(List<String> values) {
            addCriterion("f9007id in", values, "f9007id");
            return (Criteria) this;
        }

        public Criteria andF9007idNotIn(List<String> values) {
            addCriterion("f9007id not in", values, "f9007id");
            return (Criteria) this;
        }

        public Criteria andF9007idBetween(String value1, String value2) {
            addCriterion("f9007id between", value1, value2, "f9007id");
            return (Criteria) this;
        }

        public Criteria andF9007idNotBetween(String value1, String value2) {
            addCriterion("f9007id not between", value1, value2, "f9007id");
            return (Criteria) this;
        }

        public Criteria andF9007dmIsNull() {
            addCriterion("f9007dm is null");
            return (Criteria) this;
        }

        public Criteria andF9007dmIsNotNull() {
            addCriterion("f9007dm is not null");
            return (Criteria) this;
        }

        public Criteria andF9007dmEqualTo(String value) {
            addCriterion("f9007dm =", value, "f9007dm");
            return (Criteria) this;
        }

        public Criteria andF9007dmNotEqualTo(String value) {
            addCriterion("f9007dm <>", value, "f9007dm");
            return (Criteria) this;
        }

        public Criteria andF9007dmGreaterThan(String value) {
            addCriterion("f9007dm >", value, "f9007dm");
            return (Criteria) this;
        }

        public Criteria andF9007dmGreaterThanOrEqualTo(String value) {
            addCriterion("f9007dm >=", value, "f9007dm");
            return (Criteria) this;
        }

        public Criteria andF9007dmLessThan(String value) {
            addCriterion("f9007dm <", value, "f9007dm");
            return (Criteria) this;
        }

        public Criteria andF9007dmLessThanOrEqualTo(String value) {
            addCriterion("f9007dm <=", value, "f9007dm");
            return (Criteria) this;
        }

        public Criteria andF9007dmLike(String value) {
            addCriterion("f9007dm like", value, "f9007dm");
            return (Criteria) this;
        }

        public Criteria andF9007dmNotLike(String value) {
            addCriterion("f9007dm not like", value, "f9007dm");
            return (Criteria) this;
        }

        public Criteria andF9007dmIn(List<String> values) {
            addCriterion("f9007dm in", values, "f9007dm");
            return (Criteria) this;
        }

        public Criteria andF9007dmNotIn(List<String> values) {
            addCriterion("f9007dm not in", values, "f9007dm");
            return (Criteria) this;
        }

        public Criteria andF9007dmBetween(String value1, String value2) {
            addCriterion("f9007dm between", value1, value2, "f9007dm");
            return (Criteria) this;
        }

        public Criteria andF9007dmNotBetween(String value1, String value2) {
            addCriterion("f9007dm not between", value1, value2, "f9007dm");
            return (Criteria) this;
        }

        public Criteria andF9007dmmcIsNull() {
            addCriterion("f9007dmmc is null");
            return (Criteria) this;
        }

        public Criteria andF9007dmmcIsNotNull() {
            addCriterion("f9007dmmc is not null");
            return (Criteria) this;
        }

        public Criteria andF9007dmmcEqualTo(String value) {
            addCriterion("f9007dmmc =", value, "f9007dmmc");
            return (Criteria) this;
        }

        public Criteria andF9007dmmcNotEqualTo(String value) {
            addCriterion("f9007dmmc <>", value, "f9007dmmc");
            return (Criteria) this;
        }

        public Criteria andF9007dmmcGreaterThan(String value) {
            addCriterion("f9007dmmc >", value, "f9007dmmc");
            return (Criteria) this;
        }

        public Criteria andF9007dmmcGreaterThanOrEqualTo(String value) {
            addCriterion("f9007dmmc >=", value, "f9007dmmc");
            return (Criteria) this;
        }

        public Criteria andF9007dmmcLessThan(String value) {
            addCriterion("f9007dmmc <", value, "f9007dmmc");
            return (Criteria) this;
        }

        public Criteria andF9007dmmcLessThanOrEqualTo(String value) {
            addCriterion("f9007dmmc <=", value, "f9007dmmc");
            return (Criteria) this;
        }

        public Criteria andF9007dmmcLike(String value) {
            addCriterion("f9007dmmc like", value, "f9007dmmc");
            return (Criteria) this;
        }

        public Criteria andF9007dmmcNotLike(String value) {
            addCriterion("f9007dmmc not like", value, "f9007dmmc");
            return (Criteria) this;
        }

        public Criteria andF9007dmmcIn(List<String> values) {
            addCriterion("f9007dmmc in", values, "f9007dmmc");
            return (Criteria) this;
        }

        public Criteria andF9007dmmcNotIn(List<String> values) {
            addCriterion("f9007dmmc not in", values, "f9007dmmc");
            return (Criteria) this;
        }

        public Criteria andF9007dmmcBetween(String value1, String value2) {
            addCriterion("f9007dmmc between", value1, value2, "f9007dmmc");
            return (Criteria) this;
        }

        public Criteria andF9007dmmcNotBetween(String value1, String value2) {
            addCriterion("f9007dmmc not between", value1, value2, "f9007dmmc");
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