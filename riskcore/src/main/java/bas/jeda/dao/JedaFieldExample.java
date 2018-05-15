package bas.jeda.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JedaFieldExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table JEDA_FIELD
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table JEDA_FIELD
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table JEDA_FIELD
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JEDA_FIELD
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public JedaFieldExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JEDA_FIELD
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JEDA_FIELD
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JEDA_FIELD
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JEDA_FIELD
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JEDA_FIELD
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JEDA_FIELD
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JEDA_FIELD
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JEDA_FIELD
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JEDA_FIELD
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JEDA_FIELD
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table JEDA_FIELD
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
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

        public Criteria andFieldIdIsNull() {
            addCriterion("FIELD_ID is null");
            return (Criteria) this;
        }

        public Criteria andFieldIdIsNotNull() {
            addCriterion("FIELD_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFieldIdEqualTo(Integer value) {
            addCriterion("FIELD_ID =", value, "fieldId");
            return (Criteria) this;
        }

        public Criteria andFieldIdNotEqualTo(Integer value) {
            addCriterion("FIELD_ID <>", value, "fieldId");
            return (Criteria) this;
        }

        public Criteria andFieldIdGreaterThan(Integer value) {
            addCriterion("FIELD_ID >", value, "fieldId");
            return (Criteria) this;
        }

        public Criteria andFieldIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("FIELD_ID >=", value, "fieldId");
            return (Criteria) this;
        }

        public Criteria andFieldIdLessThan(Integer value) {
            addCriterion("FIELD_ID <", value, "fieldId");
            return (Criteria) this;
        }

        public Criteria andFieldIdLessThanOrEqualTo(Integer value) {
            addCriterion("FIELD_ID <=", value, "fieldId");
            return (Criteria) this;
        }

        public Criteria andFieldIdIn(List<Integer> values) {
            addCriterion("FIELD_ID in", values, "fieldId");
            return (Criteria) this;
        }

        public Criteria andFieldIdNotIn(List<Integer> values) {
            addCriterion("FIELD_ID not in", values, "fieldId");
            return (Criteria) this;
        }

        public Criteria andFieldIdBetween(Integer value1, Integer value2) {
            addCriterion("FIELD_ID between", value1, value2, "fieldId");
            return (Criteria) this;
        }

        public Criteria andFieldIdNotBetween(Integer value1, Integer value2) {
            addCriterion("FIELD_ID not between", value1, value2, "fieldId");
            return (Criteria) this;
        }

        public Criteria andFunctionIdIsNull() {
            addCriterion("FUNCTION_ID is null");
            return (Criteria) this;
        }

        public Criteria andFunctionIdIsNotNull() {
            addCriterion("FUNCTION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFunctionIdEqualTo(String value) {
            addCriterion("FUNCTION_ID =", value, "functionId");
            return (Criteria) this;
        }

        public Criteria andFunctionIdNotEqualTo(String value) {
            addCriterion("FUNCTION_ID <>", value, "functionId");
            return (Criteria) this;
        }

        public Criteria andFunctionIdGreaterThan(String value) {
            addCriterion("FUNCTION_ID >", value, "functionId");
            return (Criteria) this;
        }

        public Criteria andFunctionIdGreaterThanOrEqualTo(String value) {
            addCriterion("FUNCTION_ID >=", value, "functionId");
            return (Criteria) this;
        }

        public Criteria andFunctionIdLessThan(String value) {
            addCriterion("FUNCTION_ID <", value, "functionId");
            return (Criteria) this;
        }

        public Criteria andFunctionIdLessThanOrEqualTo(String value) {
            addCriterion("FUNCTION_ID <=", value, "functionId");
            return (Criteria) this;
        }

        public Criteria andFunctionIdLike(String value) {
            addCriterion("FUNCTION_ID like", value, "functionId");
            return (Criteria) this;
        }

        public Criteria andFunctionIdNotLike(String value) {
            addCriterion("FUNCTION_ID not like", value, "functionId");
            return (Criteria) this;
        }

        public Criteria andFunctionIdIn(List<String> values) {
            addCriterion("FUNCTION_ID in", values, "functionId");
            return (Criteria) this;
        }

        public Criteria andFunctionIdNotIn(List<String> values) {
            addCriterion("FUNCTION_ID not in", values, "functionId");
            return (Criteria) this;
        }

        public Criteria andFunctionIdBetween(String value1, String value2) {
            addCriterion("FUNCTION_ID between", value1, value2, "functionId");
            return (Criteria) this;
        }

        public Criteria andFunctionIdNotBetween(String value1, String value2) {
            addCriterion("FUNCTION_ID not between", value1, value2, "functionId");
            return (Criteria) this;
        }

        public Criteria andFieldCodeIsNull() {
            addCriterion("FIELD_CODE is null");
            return (Criteria) this;
        }

        public Criteria andFieldCodeIsNotNull() {
            addCriterion("FIELD_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andFieldCodeEqualTo(String value) {
            addCriterion("FIELD_CODE =", value, "fieldCode");
            return (Criteria) this;
        }

        public Criteria andFieldCodeNotEqualTo(String value) {
            addCriterion("FIELD_CODE <>", value, "fieldCode");
            return (Criteria) this;
        }

        public Criteria andFieldCodeGreaterThan(String value) {
            addCriterion("FIELD_CODE >", value, "fieldCode");
            return (Criteria) this;
        }

        public Criteria andFieldCodeGreaterThanOrEqualTo(String value) {
            addCriterion("FIELD_CODE >=", value, "fieldCode");
            return (Criteria) this;
        }

        public Criteria andFieldCodeLessThan(String value) {
            addCriterion("FIELD_CODE <", value, "fieldCode");
            return (Criteria) this;
        }

        public Criteria andFieldCodeLessThanOrEqualTo(String value) {
            addCriterion("FIELD_CODE <=", value, "fieldCode");
            return (Criteria) this;
        }

        public Criteria andFieldCodeLike(String value) {
            addCriterion("FIELD_CODE like", value, "fieldCode");
            return (Criteria) this;
        }

        public Criteria andFieldCodeNotLike(String value) {
            addCriterion("FIELD_CODE not like", value, "fieldCode");
            return (Criteria) this;
        }

        public Criteria andFieldCodeIn(List<String> values) {
            addCriterion("FIELD_CODE in", values, "fieldCode");
            return (Criteria) this;
        }

        public Criteria andFieldCodeNotIn(List<String> values) {
            addCriterion("FIELD_CODE not in", values, "fieldCode");
            return (Criteria) this;
        }

        public Criteria andFieldCodeBetween(String value1, String value2) {
            addCriterion("FIELD_CODE between", value1, value2, "fieldCode");
            return (Criteria) this;
        }

        public Criteria andFieldCodeNotBetween(String value1, String value2) {
            addCriterion("FIELD_CODE not between", value1, value2, "fieldCode");
            return (Criteria) this;
        }

        public Criteria andFieldNameIsNull() {
            addCriterion("FIELD_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFieldNameIsNotNull() {
            addCriterion("FIELD_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFieldNameEqualTo(String value) {
            addCriterion("FIELD_NAME =", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameNotEqualTo(String value) {
            addCriterion("FIELD_NAME <>", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameGreaterThan(String value) {
            addCriterion("FIELD_NAME >", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameGreaterThanOrEqualTo(String value) {
            addCriterion("FIELD_NAME >=", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameLessThan(String value) {
            addCriterion("FIELD_NAME <", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameLessThanOrEqualTo(String value) {
            addCriterion("FIELD_NAME <=", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameLike(String value) {
            addCriterion("FIELD_NAME like", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameNotLike(String value) {
            addCriterion("FIELD_NAME not like", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameIn(List<String> values) {
            addCriterion("FIELD_NAME in", values, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameNotIn(List<String> values) {
            addCriterion("FIELD_NAME not in", values, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameBetween(String value1, String value2) {
            addCriterion("FIELD_NAME between", value1, value2, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameNotBetween(String value1, String value2) {
            addCriterion("FIELD_NAME not between", value1, value2, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldTypeIsNull() {
            addCriterion("FIELD_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andFieldTypeIsNotNull() {
            addCriterion("FIELD_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andFieldTypeEqualTo(String value) {
            addCriterion("FIELD_TYPE =", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNotEqualTo(String value) {
            addCriterion("FIELD_TYPE <>", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeGreaterThan(String value) {
            addCriterion("FIELD_TYPE >", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeGreaterThanOrEqualTo(String value) {
            addCriterion("FIELD_TYPE >=", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeLessThan(String value) {
            addCriterion("FIELD_TYPE <", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeLessThanOrEqualTo(String value) {
            addCriterion("FIELD_TYPE <=", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeLike(String value) {
            addCriterion("FIELD_TYPE like", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNotLike(String value) {
            addCriterion("FIELD_TYPE not like", value, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeIn(List<String> values) {
            addCriterion("FIELD_TYPE in", values, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNotIn(List<String> values) {
            addCriterion("FIELD_TYPE not in", values, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeBetween(String value1, String value2) {
            addCriterion("FIELD_TYPE between", value1, value2, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldTypeNotBetween(String value1, String value2) {
            addCriterion("FIELD_TYPE not between", value1, value2, "fieldType");
            return (Criteria) this;
        }

        public Criteria andFieldReadOnlyIsNull() {
            addCriterion("FIELD_READ_ONLY is null");
            return (Criteria) this;
        }

        public Criteria andFieldReadOnlyIsNotNull() {
            addCriterion("FIELD_READ_ONLY is not null");
            return (Criteria) this;
        }

        public Criteria andFieldReadOnlyEqualTo(BigDecimal value) {
            addCriterion("FIELD_READ_ONLY =", value, "fieldReadOnly");
            return (Criteria) this;
        }

        public Criteria andFieldReadOnlyNotEqualTo(BigDecimal value) {
            addCriterion("FIELD_READ_ONLY <>", value, "fieldReadOnly");
            return (Criteria) this;
        }

        public Criteria andFieldReadOnlyGreaterThan(BigDecimal value) {
            addCriterion("FIELD_READ_ONLY >", value, "fieldReadOnly");
            return (Criteria) this;
        }

        public Criteria andFieldReadOnlyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("FIELD_READ_ONLY >=", value, "fieldReadOnly");
            return (Criteria) this;
        }

        public Criteria andFieldReadOnlyLessThan(BigDecimal value) {
            addCriterion("FIELD_READ_ONLY <", value, "fieldReadOnly");
            return (Criteria) this;
        }

        public Criteria andFieldReadOnlyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("FIELD_READ_ONLY <=", value, "fieldReadOnly");
            return (Criteria) this;
        }

        public Criteria andFieldReadOnlyIn(List<BigDecimal> values) {
            addCriterion("FIELD_READ_ONLY in", values, "fieldReadOnly");
            return (Criteria) this;
        }

        public Criteria andFieldReadOnlyNotIn(List<BigDecimal> values) {
            addCriterion("FIELD_READ_ONLY not in", values, "fieldReadOnly");
            return (Criteria) this;
        }

        public Criteria andFieldReadOnlyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FIELD_READ_ONLY between", value1, value2, "fieldReadOnly");
            return (Criteria) this;
        }

        public Criteria andFieldReadOnlyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FIELD_READ_ONLY not between", value1, value2, "fieldReadOnly");
            return (Criteria) this;
        }

        public Criteria andFieldVersionIsNull() {
            addCriterion("FIELD_VERSION is null");
            return (Criteria) this;
        }

        public Criteria andFieldVersionIsNotNull() {
            addCriterion("FIELD_VERSION is not null");
            return (Criteria) this;
        }

        public Criteria andFieldVersionEqualTo(BigDecimal value) {
            addCriterion("FIELD_VERSION =", value, "fieldVersion");
            return (Criteria) this;
        }

        public Criteria andFieldVersionNotEqualTo(BigDecimal value) {
            addCriterion("FIELD_VERSION <>", value, "fieldVersion");
            return (Criteria) this;
        }

        public Criteria andFieldVersionGreaterThan(BigDecimal value) {
            addCriterion("FIELD_VERSION >", value, "fieldVersion");
            return (Criteria) this;
        }

        public Criteria andFieldVersionGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("FIELD_VERSION >=", value, "fieldVersion");
            return (Criteria) this;
        }

        public Criteria andFieldVersionLessThan(BigDecimal value) {
            addCriterion("FIELD_VERSION <", value, "fieldVersion");
            return (Criteria) this;
        }

        public Criteria andFieldVersionLessThanOrEqualTo(BigDecimal value) {
            addCriterion("FIELD_VERSION <=", value, "fieldVersion");
            return (Criteria) this;
        }

        public Criteria andFieldVersionIn(List<BigDecimal> values) {
            addCriterion("FIELD_VERSION in", values, "fieldVersion");
            return (Criteria) this;
        }

        public Criteria andFieldVersionNotIn(List<BigDecimal> values) {
            addCriterion("FIELD_VERSION not in", values, "fieldVersion");
            return (Criteria) this;
        }

        public Criteria andFieldVersionBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FIELD_VERSION between", value1, value2, "fieldVersion");
            return (Criteria) this;
        }

        public Criteria andFieldVersionNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FIELD_VERSION not between", value1, value2, "fieldVersion");
            return (Criteria) this;
        }

        public Criteria andFieldOrderIsNull() {
            addCriterion("FIELD_ORDER is null");
            return (Criteria) this;
        }

        public Criteria andFieldOrderIsNotNull() {
            addCriterion("FIELD_ORDER is not null");
            return (Criteria) this;
        }

        public Criteria andFieldOrderEqualTo(BigDecimal value) {
            addCriterion("FIELD_ORDER =", value, "fieldOrder");
            return (Criteria) this;
        }

        public Criteria andFieldOrderNotEqualTo(BigDecimal value) {
            addCriterion("FIELD_ORDER <>", value, "fieldOrder");
            return (Criteria) this;
        }

        public Criteria andFieldOrderGreaterThan(BigDecimal value) {
            addCriterion("FIELD_ORDER >", value, "fieldOrder");
            return (Criteria) this;
        }

        public Criteria andFieldOrderGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("FIELD_ORDER >=", value, "fieldOrder");
            return (Criteria) this;
        }

        public Criteria andFieldOrderLessThan(BigDecimal value) {
            addCriterion("FIELD_ORDER <", value, "fieldOrder");
            return (Criteria) this;
        }

        public Criteria andFieldOrderLessThanOrEqualTo(BigDecimal value) {
            addCriterion("FIELD_ORDER <=", value, "fieldOrder");
            return (Criteria) this;
        }

        public Criteria andFieldOrderIn(List<BigDecimal> values) {
            addCriterion("FIELD_ORDER in", values, "fieldOrder");
            return (Criteria) this;
        }

        public Criteria andFieldOrderNotIn(List<BigDecimal> values) {
            addCriterion("FIELD_ORDER not in", values, "fieldOrder");
            return (Criteria) this;
        }

        public Criteria andFieldOrderBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FIELD_ORDER between", value1, value2, "fieldOrder");
            return (Criteria) this;
        }

        public Criteria andFieldOrderNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FIELD_ORDER not between", value1, value2, "fieldOrder");
            return (Criteria) this;
        }

        public Criteria andFieldCreatorIsNull() {
            addCriterion("FIELD_CREATOR is null");
            return (Criteria) this;
        }

        public Criteria andFieldCreatorIsNotNull() {
            addCriterion("FIELD_CREATOR is not null");
            return (Criteria) this;
        }

        public Criteria andFieldCreatorEqualTo(String value) {
            addCriterion("FIELD_CREATOR =", value, "fieldCreator");
            return (Criteria) this;
        }

        public Criteria andFieldCreatorNotEqualTo(String value) {
            addCriterion("FIELD_CREATOR <>", value, "fieldCreator");
            return (Criteria) this;
        }

        public Criteria andFieldCreatorGreaterThan(String value) {
            addCriterion("FIELD_CREATOR >", value, "fieldCreator");
            return (Criteria) this;
        }

        public Criteria andFieldCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("FIELD_CREATOR >=", value, "fieldCreator");
            return (Criteria) this;
        }

        public Criteria andFieldCreatorLessThan(String value) {
            addCriterion("FIELD_CREATOR <", value, "fieldCreator");
            return (Criteria) this;
        }

        public Criteria andFieldCreatorLessThanOrEqualTo(String value) {
            addCriterion("FIELD_CREATOR <=", value, "fieldCreator");
            return (Criteria) this;
        }

        public Criteria andFieldCreatorLike(String value) {
            addCriterion("FIELD_CREATOR like", value, "fieldCreator");
            return (Criteria) this;
        }

        public Criteria andFieldCreatorNotLike(String value) {
            addCriterion("FIELD_CREATOR not like", value, "fieldCreator");
            return (Criteria) this;
        }

        public Criteria andFieldCreatorIn(List<String> values) {
            addCriterion("FIELD_CREATOR in", values, "fieldCreator");
            return (Criteria) this;
        }

        public Criteria andFieldCreatorNotIn(List<String> values) {
            addCriterion("FIELD_CREATOR not in", values, "fieldCreator");
            return (Criteria) this;
        }

        public Criteria andFieldCreatorBetween(String value1, String value2) {
            addCriterion("FIELD_CREATOR between", value1, value2, "fieldCreator");
            return (Criteria) this;
        }

        public Criteria andFieldCreatorNotBetween(String value1, String value2) {
            addCriterion("FIELD_CREATOR not between", value1, value2, "fieldCreator");
            return (Criteria) this;
        }

        public Criteria andFieldCreatedIsNull() {
            addCriterion("FIELD_CREATED is null");
            return (Criteria) this;
        }

        public Criteria andFieldCreatedIsNotNull() {
            addCriterion("FIELD_CREATED is not null");
            return (Criteria) this;
        }

        public Criteria andFieldCreatedEqualTo(Date value) {
            addCriterion("FIELD_CREATED =", value, "fieldCreated");
            return (Criteria) this;
        }

        public Criteria andFieldCreatedNotEqualTo(Date value) {
            addCriterion("FIELD_CREATED <>", value, "fieldCreated");
            return (Criteria) this;
        }

        public Criteria andFieldCreatedGreaterThan(Date value) {
            addCriterion("FIELD_CREATED >", value, "fieldCreated");
            return (Criteria) this;
        }

        public Criteria andFieldCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("FIELD_CREATED >=", value, "fieldCreated");
            return (Criteria) this;
        }

        public Criteria andFieldCreatedLessThan(Date value) {
            addCriterion("FIELD_CREATED <", value, "fieldCreated");
            return (Criteria) this;
        }

        public Criteria andFieldCreatedLessThanOrEqualTo(Date value) {
            addCriterion("FIELD_CREATED <=", value, "fieldCreated");
            return (Criteria) this;
        }

        public Criteria andFieldCreatedIn(List<Date> values) {
            addCriterion("FIELD_CREATED in", values, "fieldCreated");
            return (Criteria) this;
        }

        public Criteria andFieldCreatedNotIn(List<Date> values) {
            addCriterion("FIELD_CREATED not in", values, "fieldCreated");
            return (Criteria) this;
        }

        public Criteria andFieldCreatedBetween(Date value1, Date value2) {
            addCriterion("FIELD_CREATED between", value1, value2, "fieldCreated");
            return (Criteria) this;
        }

        public Criteria andFieldCreatedNotBetween(Date value1, Date value2) {
            addCriterion("FIELD_CREATED not between", value1, value2, "fieldCreated");
            return (Criteria) this;
        }

        public Criteria andFieldModifierIsNull() {
            addCriterion("FIELD_MODIFIER is null");
            return (Criteria) this;
        }

        public Criteria andFieldModifierIsNotNull() {
            addCriterion("FIELD_MODIFIER is not null");
            return (Criteria) this;
        }

        public Criteria andFieldModifierEqualTo(String value) {
            addCriterion("FIELD_MODIFIER =", value, "fieldModifier");
            return (Criteria) this;
        }

        public Criteria andFieldModifierNotEqualTo(String value) {
            addCriterion("FIELD_MODIFIER <>", value, "fieldModifier");
            return (Criteria) this;
        }

        public Criteria andFieldModifierGreaterThan(String value) {
            addCriterion("FIELD_MODIFIER >", value, "fieldModifier");
            return (Criteria) this;
        }

        public Criteria andFieldModifierGreaterThanOrEqualTo(String value) {
            addCriterion("FIELD_MODIFIER >=", value, "fieldModifier");
            return (Criteria) this;
        }

        public Criteria andFieldModifierLessThan(String value) {
            addCriterion("FIELD_MODIFIER <", value, "fieldModifier");
            return (Criteria) this;
        }

        public Criteria andFieldModifierLessThanOrEqualTo(String value) {
            addCriterion("FIELD_MODIFIER <=", value, "fieldModifier");
            return (Criteria) this;
        }

        public Criteria andFieldModifierLike(String value) {
            addCriterion("FIELD_MODIFIER like", value, "fieldModifier");
            return (Criteria) this;
        }

        public Criteria andFieldModifierNotLike(String value) {
            addCriterion("FIELD_MODIFIER not like", value, "fieldModifier");
            return (Criteria) this;
        }

        public Criteria andFieldModifierIn(List<String> values) {
            addCriterion("FIELD_MODIFIER in", values, "fieldModifier");
            return (Criteria) this;
        }

        public Criteria andFieldModifierNotIn(List<String> values) {
            addCriterion("FIELD_MODIFIER not in", values, "fieldModifier");
            return (Criteria) this;
        }

        public Criteria andFieldModifierBetween(String value1, String value2) {
            addCriterion("FIELD_MODIFIER between", value1, value2, "fieldModifier");
            return (Criteria) this;
        }

        public Criteria andFieldModifierNotBetween(String value1, String value2) {
            addCriterion("FIELD_MODIFIER not between", value1, value2, "fieldModifier");
            return (Criteria) this;
        }

        public Criteria andFieldModifiedIsNull() {
            addCriterion("FIELD_MODIFIED is null");
            return (Criteria) this;
        }

        public Criteria andFieldModifiedIsNotNull() {
            addCriterion("FIELD_MODIFIED is not null");
            return (Criteria) this;
        }

        public Criteria andFieldModifiedEqualTo(Date value) {
            addCriterion("FIELD_MODIFIED =", value, "fieldModified");
            return (Criteria) this;
        }

        public Criteria andFieldModifiedNotEqualTo(Date value) {
            addCriterion("FIELD_MODIFIED <>", value, "fieldModified");
            return (Criteria) this;
        }

        public Criteria andFieldModifiedGreaterThan(Date value) {
            addCriterion("FIELD_MODIFIED >", value, "fieldModified");
            return (Criteria) this;
        }

        public Criteria andFieldModifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("FIELD_MODIFIED >=", value, "fieldModified");
            return (Criteria) this;
        }

        public Criteria andFieldModifiedLessThan(Date value) {
            addCriterion("FIELD_MODIFIED <", value, "fieldModified");
            return (Criteria) this;
        }

        public Criteria andFieldModifiedLessThanOrEqualTo(Date value) {
            addCriterion("FIELD_MODIFIED <=", value, "fieldModified");
            return (Criteria) this;
        }

        public Criteria andFieldModifiedIn(List<Date> values) {
            addCriterion("FIELD_MODIFIED in", values, "fieldModified");
            return (Criteria) this;
        }

        public Criteria andFieldModifiedNotIn(List<Date> values) {
            addCriterion("FIELD_MODIFIED not in", values, "fieldModified");
            return (Criteria) this;
        }

        public Criteria andFieldModifiedBetween(Date value1, Date value2) {
            addCriterion("FIELD_MODIFIED between", value1, value2, "fieldModified");
            return (Criteria) this;
        }

        public Criteria andFieldModifiedNotBetween(Date value1, Date value2) {
            addCriterion("FIELD_MODIFIED not between", value1, value2, "fieldModified");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table JEDA_FIELD
     *
     * @mbggenerated do_not_delete_during_merge Mon Mar 02 21:49:14 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table JEDA_FIELD
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
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