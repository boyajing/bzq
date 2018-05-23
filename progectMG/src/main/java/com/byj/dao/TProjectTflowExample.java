package com.byj.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TProjectTflowExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TProjectTflowExample() {
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
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(BigDecimal value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(BigDecimal value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(BigDecimal value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(BigDecimal value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<BigDecimal> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<BigDecimal> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andProjectNoIsNull() {
            addCriterion("PROJECT_NO is null");
            return (Criteria) this;
        }

        public Criteria andProjectNoIsNotNull() {
            addCriterion("PROJECT_NO is not null");
            return (Criteria) this;
        }

        public Criteria andProjectNoEqualTo(String value) {
            addCriterion("PROJECT_NO =", value, "projectNo");
            return (Criteria) this;
        }

        public Criteria andProjectNoNotEqualTo(String value) {
            addCriterion("PROJECT_NO <>", value, "projectNo");
            return (Criteria) this;
        }

        public Criteria andProjectNoGreaterThan(String value) {
            addCriterion("PROJECT_NO >", value, "projectNo");
            return (Criteria) this;
        }

        public Criteria andProjectNoGreaterThanOrEqualTo(String value) {
            addCriterion("PROJECT_NO >=", value, "projectNo");
            return (Criteria) this;
        }

        public Criteria andProjectNoLessThan(String value) {
            addCriterion("PROJECT_NO <", value, "projectNo");
            return (Criteria) this;
        }

        public Criteria andProjectNoLessThanOrEqualTo(String value) {
            addCriterion("PROJECT_NO <=", value, "projectNo");
            return (Criteria) this;
        }

        public Criteria andProjectNoLike(String value) {
            addCriterion("PROJECT_NO like", value, "projectNo");
            return (Criteria) this;
        }

        public Criteria andProjectNoNotLike(String value) {
            addCriterion("PROJECT_NO not like", value, "projectNo");
            return (Criteria) this;
        }

        public Criteria andProjectNoIn(List<String> values) {
            addCriterion("PROJECT_NO in", values, "projectNo");
            return (Criteria) this;
        }

        public Criteria andProjectNoNotIn(List<String> values) {
            addCriterion("PROJECT_NO not in", values, "projectNo");
            return (Criteria) this;
        }

        public Criteria andProjectNoBetween(String value1, String value2) {
            addCriterion("PROJECT_NO between", value1, value2, "projectNo");
            return (Criteria) this;
        }

        public Criteria andProjectNoNotBetween(String value1, String value2) {
            addCriterion("PROJECT_NO not between", value1, value2, "projectNo");
            return (Criteria) this;
        }

        public Criteria andBeginDateIsNull() {
            addCriterion("BEGIN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andBeginDateIsNotNull() {
            addCriterion("BEGIN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andBeginDateEqualTo(Date value) {
            addCriterionForJDBCDate("BEGIN_DATE =", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("BEGIN_DATE <>", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateGreaterThan(Date value) {
            addCriterionForJDBCDate("BEGIN_DATE >", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("BEGIN_DATE >=", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateLessThan(Date value) {
            addCriterionForJDBCDate("BEGIN_DATE <", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("BEGIN_DATE <=", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateIn(List<Date> values) {
            addCriterionForJDBCDate("BEGIN_DATE in", values, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("BEGIN_DATE not in", values, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("BEGIN_DATE between", value1, value2, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("BEGIN_DATE not between", value1, value2, "beginDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNull() {
            addCriterion("END_DATE is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("END_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(Date value) {
            addCriterionForJDBCDate("END_DATE =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("END_DATE <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(Date value) {
            addCriterionForJDBCDate("END_DATE >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("END_DATE >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(Date value) {
            addCriterionForJDBCDate("END_DATE <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("END_DATE <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<Date> values) {
            addCriterionForJDBCDate("END_DATE in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("END_DATE not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("END_DATE between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("END_DATE not between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andActQuantityIsNull() {
            addCriterion("ACT_QUANTITY is null");
            return (Criteria) this;
        }

        public Criteria andActQuantityIsNotNull() {
            addCriterion("ACT_QUANTITY is not null");
            return (Criteria) this;
        }

        public Criteria andActQuantityEqualTo(BigDecimal value) {
            addCriterion("ACT_QUANTITY =", value, "actQuantity");
            return (Criteria) this;
        }

        public Criteria andActQuantityNotEqualTo(BigDecimal value) {
            addCriterion("ACT_QUANTITY <>", value, "actQuantity");
            return (Criteria) this;
        }

        public Criteria andActQuantityGreaterThan(BigDecimal value) {
            addCriterion("ACT_QUANTITY >", value, "actQuantity");
            return (Criteria) this;
        }

        public Criteria andActQuantityGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ACT_QUANTITY >=", value, "actQuantity");
            return (Criteria) this;
        }

        public Criteria andActQuantityLessThan(BigDecimal value) {
            addCriterion("ACT_QUANTITY <", value, "actQuantity");
            return (Criteria) this;
        }

        public Criteria andActQuantityLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ACT_QUANTITY <=", value, "actQuantity");
            return (Criteria) this;
        }

        public Criteria andActQuantityIn(List<BigDecimal> values) {
            addCriterion("ACT_QUANTITY in", values, "actQuantity");
            return (Criteria) this;
        }

        public Criteria andActQuantityNotIn(List<BigDecimal> values) {
            addCriterion("ACT_QUANTITY not in", values, "actQuantity");
            return (Criteria) this;
        }

        public Criteria andActQuantityBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACT_QUANTITY between", value1, value2, "actQuantity");
            return (Criteria) this;
        }

        public Criteria andActQuantityNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACT_QUANTITY not between", value1, value2, "actQuantity");
            return (Criteria) this;
        }

        public Criteria andRemainQuantityIsNull() {
            addCriterion("REMAIN_QUANTITY is null");
            return (Criteria) this;
        }

        public Criteria andRemainQuantityIsNotNull() {
            addCriterion("REMAIN_QUANTITY is not null");
            return (Criteria) this;
        }

        public Criteria andRemainQuantityEqualTo(BigDecimal value) {
            addCriterion("REMAIN_QUANTITY =", value, "remainQuantity");
            return (Criteria) this;
        }

        public Criteria andRemainQuantityNotEqualTo(BigDecimal value) {
            addCriterion("REMAIN_QUANTITY <>", value, "remainQuantity");
            return (Criteria) this;
        }

        public Criteria andRemainQuantityGreaterThan(BigDecimal value) {
            addCriterion("REMAIN_QUANTITY >", value, "remainQuantity");
            return (Criteria) this;
        }

        public Criteria andRemainQuantityGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("REMAIN_QUANTITY >=", value, "remainQuantity");
            return (Criteria) this;
        }

        public Criteria andRemainQuantityLessThan(BigDecimal value) {
            addCriterion("REMAIN_QUANTITY <", value, "remainQuantity");
            return (Criteria) this;
        }

        public Criteria andRemainQuantityLessThanOrEqualTo(BigDecimal value) {
            addCriterion("REMAIN_QUANTITY <=", value, "remainQuantity");
            return (Criteria) this;
        }

        public Criteria andRemainQuantityIn(List<BigDecimal> values) {
            addCriterion("REMAIN_QUANTITY in", values, "remainQuantity");
            return (Criteria) this;
        }

        public Criteria andRemainQuantityNotIn(List<BigDecimal> values) {
            addCriterion("REMAIN_QUANTITY not in", values, "remainQuantity");
            return (Criteria) this;
        }

        public Criteria andRemainQuantityBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REMAIN_QUANTITY between", value1, value2, "remainQuantity");
            return (Criteria) this;
        }

        public Criteria andRemainQuantityNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REMAIN_QUANTITY not between", value1, value2, "remainQuantity");
            return (Criteria) this;
        }

        public Criteria andActPickupIsNull() {
            addCriterion("ACT_PICKUP is null");
            return (Criteria) this;
        }

        public Criteria andActPickupIsNotNull() {
            addCriterion("ACT_PICKUP is not null");
            return (Criteria) this;
        }

        public Criteria andActPickupEqualTo(BigDecimal value) {
            addCriterion("ACT_PICKUP =", value, "actPickup");
            return (Criteria) this;
        }

        public Criteria andActPickupNotEqualTo(BigDecimal value) {
            addCriterion("ACT_PICKUP <>", value, "actPickup");
            return (Criteria) this;
        }

        public Criteria andActPickupGreaterThan(BigDecimal value) {
            addCriterion("ACT_PICKUP >", value, "actPickup");
            return (Criteria) this;
        }

        public Criteria andActPickupGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ACT_PICKUP >=", value, "actPickup");
            return (Criteria) this;
        }

        public Criteria andActPickupLessThan(BigDecimal value) {
            addCriterion("ACT_PICKUP <", value, "actPickup");
            return (Criteria) this;
        }

        public Criteria andActPickupLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ACT_PICKUP <=", value, "actPickup");
            return (Criteria) this;
        }

        public Criteria andActPickupIn(List<BigDecimal> values) {
            addCriterion("ACT_PICKUP in", values, "actPickup");
            return (Criteria) this;
        }

        public Criteria andActPickupNotIn(List<BigDecimal> values) {
            addCriterion("ACT_PICKUP not in", values, "actPickup");
            return (Criteria) this;
        }

        public Criteria andActPickupBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACT_PICKUP between", value1, value2, "actPickup");
            return (Criteria) this;
        }

        public Criteria andActPickupNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACT_PICKUP not between", value1, value2, "actPickup");
            return (Criteria) this;
        }

        public Criteria andRemainPickupIsNull() {
            addCriterion("REMAIN_PICKUP is null");
            return (Criteria) this;
        }

        public Criteria andRemainPickupIsNotNull() {
            addCriterion("REMAIN_PICKUP is not null");
            return (Criteria) this;
        }

        public Criteria andRemainPickupEqualTo(BigDecimal value) {
            addCriterion("REMAIN_PICKUP =", value, "remainPickup");
            return (Criteria) this;
        }

        public Criteria andRemainPickupNotEqualTo(BigDecimal value) {
            addCriterion("REMAIN_PICKUP <>", value, "remainPickup");
            return (Criteria) this;
        }

        public Criteria andRemainPickupGreaterThan(BigDecimal value) {
            addCriterion("REMAIN_PICKUP >", value, "remainPickup");
            return (Criteria) this;
        }

        public Criteria andRemainPickupGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("REMAIN_PICKUP >=", value, "remainPickup");
            return (Criteria) this;
        }

        public Criteria andRemainPickupLessThan(BigDecimal value) {
            addCriterion("REMAIN_PICKUP <", value, "remainPickup");
            return (Criteria) this;
        }

        public Criteria andRemainPickupLessThanOrEqualTo(BigDecimal value) {
            addCriterion("REMAIN_PICKUP <=", value, "remainPickup");
            return (Criteria) this;
        }

        public Criteria andRemainPickupIn(List<BigDecimal> values) {
            addCriterion("REMAIN_PICKUP in", values, "remainPickup");
            return (Criteria) this;
        }

        public Criteria andRemainPickupNotIn(List<BigDecimal> values) {
            addCriterion("REMAIN_PICKUP not in", values, "remainPickup");
            return (Criteria) this;
        }

        public Criteria andRemainPickupBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REMAIN_PICKUP between", value1, value2, "remainPickup");
            return (Criteria) this;
        }

        public Criteria andRemainPickupNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("REMAIN_PICKUP not between", value1, value2, "remainPickup");
            return (Criteria) this;
        }

        public Criteria andActDateIsNull() {
            addCriterion("ACT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andActDateIsNotNull() {
            addCriterion("ACT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andActDateEqualTo(Date value) {
            addCriterionForJDBCDate("ACT_DATE =", value, "actDate");
            return (Criteria) this;
        }

        public Criteria andActDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("ACT_DATE <>", value, "actDate");
            return (Criteria) this;
        }

        public Criteria andActDateGreaterThan(Date value) {
            addCriterionForJDBCDate("ACT_DATE >", value, "actDate");
            return (Criteria) this;
        }

        public Criteria andActDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ACT_DATE >=", value, "actDate");
            return (Criteria) this;
        }

        public Criteria andActDateLessThan(Date value) {
            addCriterionForJDBCDate("ACT_DATE <", value, "actDate");
            return (Criteria) this;
        }

        public Criteria andActDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ACT_DATE <=", value, "actDate");
            return (Criteria) this;
        }

        public Criteria andActDateIn(List<Date> values) {
            addCriterionForJDBCDate("ACT_DATE in", values, "actDate");
            return (Criteria) this;
        }

        public Criteria andActDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("ACT_DATE not in", values, "actDate");
            return (Criteria) this;
        }

        public Criteria andActDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ACT_DATE between", value1, value2, "actDate");
            return (Criteria) this;
        }

        public Criteria andActDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ACT_DATE not between", value1, value2, "actDate");
            return (Criteria) this;
        }

        public Criteria andIsValidIsNull() {
            addCriterion("IS_VALID is null");
            return (Criteria) this;
        }

        public Criteria andIsValidIsNotNull() {
            addCriterion("IS_VALID is not null");
            return (Criteria) this;
        }

        public Criteria andIsValidEqualTo(String value) {
            addCriterion("IS_VALID =", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidNotEqualTo(String value) {
            addCriterion("IS_VALID <>", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidGreaterThan(String value) {
            addCriterion("IS_VALID >", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidGreaterThanOrEqualTo(String value) {
            addCriterion("IS_VALID >=", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidLessThan(String value) {
            addCriterion("IS_VALID <", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidLessThanOrEqualTo(String value) {
            addCriterion("IS_VALID <=", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidLike(String value) {
            addCriterion("IS_VALID like", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidNotLike(String value) {
            addCriterion("IS_VALID not like", value, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidIn(List<String> values) {
            addCriterion("IS_VALID in", values, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidNotIn(List<String> values) {
            addCriterion("IS_VALID not in", values, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidBetween(String value1, String value2) {
            addCriterion("IS_VALID between", value1, value2, "isValid");
            return (Criteria) this;
        }

        public Criteria andIsValidNotBetween(String value1, String value2) {
            addCriterion("IS_VALID not between", value1, value2, "isValid");
            return (Criteria) this;
        }

        public Criteria andApplyOprIsNull() {
            addCriterion("APPLY_OPR is null");
            return (Criteria) this;
        }

        public Criteria andApplyOprIsNotNull() {
            addCriterion("APPLY_OPR is not null");
            return (Criteria) this;
        }

        public Criteria andApplyOprEqualTo(String value) {
            addCriterion("APPLY_OPR =", value, "applyOpr");
            return (Criteria) this;
        }

        public Criteria andApplyOprNotEqualTo(String value) {
            addCriterion("APPLY_OPR <>", value, "applyOpr");
            return (Criteria) this;
        }

        public Criteria andApplyOprGreaterThan(String value) {
            addCriterion("APPLY_OPR >", value, "applyOpr");
            return (Criteria) this;
        }

        public Criteria andApplyOprGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_OPR >=", value, "applyOpr");
            return (Criteria) this;
        }

        public Criteria andApplyOprLessThan(String value) {
            addCriterion("APPLY_OPR <", value, "applyOpr");
            return (Criteria) this;
        }

        public Criteria andApplyOprLessThanOrEqualTo(String value) {
            addCriterion("APPLY_OPR <=", value, "applyOpr");
            return (Criteria) this;
        }

        public Criteria andApplyOprLike(String value) {
            addCriterion("APPLY_OPR like", value, "applyOpr");
            return (Criteria) this;
        }

        public Criteria andApplyOprNotLike(String value) {
            addCriterion("APPLY_OPR not like", value, "applyOpr");
            return (Criteria) this;
        }

        public Criteria andApplyOprIn(List<String> values) {
            addCriterion("APPLY_OPR in", values, "applyOpr");
            return (Criteria) this;
        }

        public Criteria andApplyOprNotIn(List<String> values) {
            addCriterion("APPLY_OPR not in", values, "applyOpr");
            return (Criteria) this;
        }

        public Criteria andApplyOprBetween(String value1, String value2) {
            addCriterion("APPLY_OPR between", value1, value2, "applyOpr");
            return (Criteria) this;
        }

        public Criteria andApplyOprNotBetween(String value1, String value2) {
            addCriterion("APPLY_OPR not between", value1, value2, "applyOpr");
            return (Criteria) this;
        }

        public Criteria andApplyDateIsNull() {
            addCriterion("APPLY_DATE is null");
            return (Criteria) this;
        }

        public Criteria andApplyDateIsNotNull() {
            addCriterion("APPLY_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andApplyDateEqualTo(Date value) {
            addCriterionForJDBCDate("APPLY_DATE =", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("APPLY_DATE <>", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateGreaterThan(Date value) {
            addCriterionForJDBCDate("APPLY_DATE >", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("APPLY_DATE >=", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateLessThan(Date value) {
            addCriterionForJDBCDate("APPLY_DATE <", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("APPLY_DATE <=", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateIn(List<Date> values) {
            addCriterionForJDBCDate("APPLY_DATE in", values, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("APPLY_DATE not in", values, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("APPLY_DATE between", value1, value2, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("APPLY_DATE not between", value1, value2, "applyDate");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("TYPE =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("TYPE <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("TYPE >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("TYPE <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("TYPE <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("TYPE like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("TYPE not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("TYPE in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("TYPE not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("TYPE between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("TYPE not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andDetailIdIsNull() {
            addCriterion("DETAIL_ID is null");
            return (Criteria) this;
        }

        public Criteria andDetailIdIsNotNull() {
            addCriterion("DETAIL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDetailIdEqualTo(BigDecimal value) {
            addCriterion("DETAIL_ID =", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdNotEqualTo(BigDecimal value) {
            addCriterion("DETAIL_ID <>", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdGreaterThan(BigDecimal value) {
            addCriterion("DETAIL_ID >", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("DETAIL_ID >=", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdLessThan(BigDecimal value) {
            addCriterion("DETAIL_ID <", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("DETAIL_ID <=", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdIn(List<BigDecimal> values) {
            addCriterion("DETAIL_ID in", values, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdNotIn(List<BigDecimal> values) {
            addCriterion("DETAIL_ID not in", values, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DETAIL_ID between", value1, value2, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DETAIL_ID not between", value1, value2, "detailId");
            return (Criteria) this;
        }

        public Criteria andWorkpieceIsNull() {
            addCriterion("WORKPIECE is null");
            return (Criteria) this;
        }

        public Criteria andWorkpieceIsNotNull() {
            addCriterion("WORKPIECE is not null");
            return (Criteria) this;
        }

        public Criteria andWorkpieceEqualTo(String value) {
            addCriterion("WORKPIECE =", value, "workpiece");
            return (Criteria) this;
        }

        public Criteria andWorkpieceNotEqualTo(String value) {
            addCriterion("WORKPIECE <>", value, "workpiece");
            return (Criteria) this;
        }

        public Criteria andWorkpieceGreaterThan(String value) {
            addCriterion("WORKPIECE >", value, "workpiece");
            return (Criteria) this;
        }

        public Criteria andWorkpieceGreaterThanOrEqualTo(String value) {
            addCriterion("WORKPIECE >=", value, "workpiece");
            return (Criteria) this;
        }

        public Criteria andWorkpieceLessThan(String value) {
            addCriterion("WORKPIECE <", value, "workpiece");
            return (Criteria) this;
        }

        public Criteria andWorkpieceLessThanOrEqualTo(String value) {
            addCriterion("WORKPIECE <=", value, "workpiece");
            return (Criteria) this;
        }

        public Criteria andWorkpieceLike(String value) {
            addCriterion("WORKPIECE like", value, "workpiece");
            return (Criteria) this;
        }

        public Criteria andWorkpieceNotLike(String value) {
            addCriterion("WORKPIECE not like", value, "workpiece");
            return (Criteria) this;
        }

        public Criteria andWorkpieceIn(List<String> values) {
            addCriterion("WORKPIECE in", values, "workpiece");
            return (Criteria) this;
        }

        public Criteria andWorkpieceNotIn(List<String> values) {
            addCriterion("WORKPIECE not in", values, "workpiece");
            return (Criteria) this;
        }

        public Criteria andWorkpieceBetween(String value1, String value2) {
            addCriterion("WORKPIECE between", value1, value2, "workpiece");
            return (Criteria) this;
        }

        public Criteria andWorkpieceNotBetween(String value1, String value2) {
            addCriterion("WORKPIECE not between", value1, value2, "workpiece");
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