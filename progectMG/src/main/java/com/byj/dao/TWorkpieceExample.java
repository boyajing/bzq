package com.byj.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TWorkpieceExample {
    protected Integer limitStart;

    protected Integer limitEnd;

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitStart(Integer limitStart) {
        this.limitStart = limitStart;
    }

    public Integer getLimitEnd() {
        return limitEnd;
    }

    public void setLimitEnd(Integer limitEnd) {
        this.limitEnd = limitEnd;
    }
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_WORKPIECE
     *
     * @mbg.generated Tue May 15 21:38:24 CST 2018
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_WORKPIECE
     *
     * @mbg.generated Tue May 15 21:38:24 CST 2018
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table T_WORKPIECE
     *
     * @mbg.generated Tue May 15 21:38:24 CST 2018
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_WORKPIECE
     *
     * @mbg.generated Tue May 15 21:38:24 CST 2018
     */
    public TWorkpieceExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_WORKPIECE
     *
     * @mbg.generated Tue May 15 21:38:24 CST 2018
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_WORKPIECE
     *
     * @mbg.generated Tue May 15 21:38:24 CST 2018
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_WORKPIECE
     *
     * @mbg.generated Tue May 15 21:38:24 CST 2018
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_WORKPIECE
     *
     * @mbg.generated Tue May 15 21:38:24 CST 2018
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_WORKPIECE
     *
     * @mbg.generated Tue May 15 21:38:24 CST 2018
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_WORKPIECE
     *
     * @mbg.generated Tue May 15 21:38:24 CST 2018
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_WORKPIECE
     *
     * @mbg.generated Tue May 15 21:38:24 CST 2018
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_WORKPIECE
     *
     * @mbg.generated Tue May 15 21:38:24 CST 2018
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
     * This method corresponds to the database table T_WORKPIECE
     *
     * @mbg.generated Tue May 15 21:38:24 CST 2018
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_WORKPIECE
     *
     * @mbg.generated Tue May 15 21:38:24 CST 2018
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table T_WORKPIECE
     *
     * @mbg.generated Tue May 15 21:38:24 CST 2018
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

        public Criteria andWorkpieceNoIsNull() {
            addCriterion("WORKPIECE_NO is null");
            return (Criteria) this;
        }

        public Criteria andWorkpieceNoIsNotNull() {
            addCriterion("WORKPIECE_NO is not null");
            return (Criteria) this;
        }

        public Criteria andWorkpieceNoEqualTo(String value) {
            addCriterion("WORKPIECE_NO =", value, "workpieceNo");
            return (Criteria) this;
        }

        public Criteria andWorkpieceNoNotEqualTo(String value) {
            addCriterion("WORKPIECE_NO <>", value, "workpieceNo");
            return (Criteria) this;
        }

        public Criteria andWorkpieceNoGreaterThan(String value) {
            addCriterion("WORKPIECE_NO >", value, "workpieceNo");
            return (Criteria) this;
        }

        public Criteria andWorkpieceNoGreaterThanOrEqualTo(String value) {
            addCriterion("WORKPIECE_NO >=", value, "workpieceNo");
            return (Criteria) this;
        }

        public Criteria andWorkpieceNoLessThan(String value) {
            addCriterion("WORKPIECE_NO <", value, "workpieceNo");
            return (Criteria) this;
        }

        public Criteria andWorkpieceNoLessThanOrEqualTo(String value) {
            addCriterion("WORKPIECE_NO <=", value, "workpieceNo");
            return (Criteria) this;
        }

        public Criteria andWorkpieceNoLike(String value) {
            addCriterion("WORKPIECE_NO like", value, "workpieceNo");
            return (Criteria) this;
        }

        public Criteria andWorkpieceNoNotLike(String value) {
            addCriterion("WORKPIECE_NO not like", value, "workpieceNo");
            return (Criteria) this;
        }

        public Criteria andWorkpieceNoIn(List<String> values) {
            addCriterion("WORKPIECE_NO in", values, "workpieceNo");
            return (Criteria) this;
        }

        public Criteria andWorkpieceNoNotIn(List<String> values) {
            addCriterion("WORKPIECE_NO not in", values, "workpieceNo");
            return (Criteria) this;
        }

        public Criteria andWorkpieceNoBetween(String value1, String value2) {
            addCriterion("WORKPIECE_NO between", value1, value2, "workpieceNo");
            return (Criteria) this;
        }

        public Criteria andWorkpieceNoNotBetween(String value1, String value2) {
            addCriterion("WORKPIECE_NO not between", value1, value2, "workpieceNo");
            return (Criteria) this;
        }

        public Criteria andWorkpieceNameIsNull() {
            addCriterion("WORKPIECE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andWorkpieceNameIsNotNull() {
            addCriterion("WORKPIECE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andWorkpieceNameEqualTo(String value) {
            addCriterion("WORKPIECE_NAME =", value, "workpieceName");
            return (Criteria) this;
        }

        public Criteria andWorkpieceNameNotEqualTo(String value) {
            addCriterion("WORKPIECE_NAME <>", value, "workpieceName");
            return (Criteria) this;
        }

        public Criteria andWorkpieceNameGreaterThan(String value) {
            addCriterion("WORKPIECE_NAME >", value, "workpieceName");
            return (Criteria) this;
        }

        public Criteria andWorkpieceNameGreaterThanOrEqualTo(String value) {
            addCriterion("WORKPIECE_NAME >=", value, "workpieceName");
            return (Criteria) this;
        }

        public Criteria andWorkpieceNameLessThan(String value) {
            addCriterion("WORKPIECE_NAME <", value, "workpieceName");
            return (Criteria) this;
        }

        public Criteria andWorkpieceNameLessThanOrEqualTo(String value) {
            addCriterion("WORKPIECE_NAME <=", value, "workpieceName");
            return (Criteria) this;
        }

        public Criteria andWorkpieceNameLike(String value) {
            addCriterion("WORKPIECE_NAME like", value, "workpieceName");
            return (Criteria) this;
        }

        public Criteria andWorkpieceNameNotLike(String value) {
            addCriterion("WORKPIECE_NAME not like", value, "workpieceName");
            return (Criteria) this;
        }

        public Criteria andWorkpieceNameIn(List<String> values) {
            addCriterion("WORKPIECE_NAME in", values, "workpieceName");
            return (Criteria) this;
        }

        public Criteria andWorkpieceNameNotIn(List<String> values) {
            addCriterion("WORKPIECE_NAME not in", values, "workpieceName");
            return (Criteria) this;
        }

        public Criteria andWorkpieceNameBetween(String value1, String value2) {
            addCriterion("WORKPIECE_NAME between", value1, value2, "workpieceName");
            return (Criteria) this;
        }

        public Criteria andWorkpieceNameNotBetween(String value1, String value2) {
            addCriterion("WORKPIECE_NAME not between", value1, value2, "workpieceName");
            return (Criteria) this;
        }

        public Criteria andUnitIsNull() {
            addCriterion("UNIT is null");
            return (Criteria) this;
        }

        public Criteria andUnitIsNotNull() {
            addCriterion("UNIT is not null");
            return (Criteria) this;
        }

        public Criteria andUnitEqualTo(String value) {
            addCriterion("UNIT =", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotEqualTo(String value) {
            addCriterion("UNIT <>", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThan(String value) {
            addCriterion("UNIT >", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThanOrEqualTo(String value) {
            addCriterion("UNIT >=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThan(String value) {
            addCriterion("UNIT <", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThanOrEqualTo(String value) {
            addCriterion("UNIT <=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLike(String value) {
            addCriterion("UNIT like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotLike(String value) {
            addCriterion("UNIT not like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitIn(List<String> values) {
            addCriterion("UNIT in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotIn(List<String> values) {
            addCriterion("UNIT not in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitBetween(String value1, String value2) {
            addCriterion("UNIT between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotBetween(String value1, String value2) {
            addCriterion("UNIT not between", value1, value2, "unit");
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

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
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
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table T_WORKPIECE
     *
     * @mbg.generated do_not_delete_during_merge Tue May 15 21:38:24 CST 2018
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table T_WORKPIECE
     *
     * @mbg.generated Tue May 15 21:38:24 CST 2018
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