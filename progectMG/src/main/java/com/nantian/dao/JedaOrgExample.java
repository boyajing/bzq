package com.nantian.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class JedaOrgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public JedaOrgExample() {
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

        public Criteria andOrgIdIsNull() {
            addCriterion("ORG_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNotNull() {
            addCriterion("ORG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgIdEqualTo(String value) {
            addCriterion("ORG_ID =", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotEqualTo(String value) {
            addCriterion("ORG_ID <>", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThan(String value) {
            addCriterion("ORG_ID >", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_ID >=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThan(String value) {
            addCriterion("ORG_ID <", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThanOrEqualTo(String value) {
            addCriterion("ORG_ID <=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLike(String value) {
            addCriterion("ORG_ID like", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotLike(String value) {
            addCriterion("ORG_ID not like", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdIn(List<String> values) {
            addCriterion("ORG_ID in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotIn(List<String> values) {
            addCriterion("ORG_ID not in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdBetween(String value1, String value2) {
            addCriterion("ORG_ID between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotBetween(String value1, String value2) {
            addCriterion("ORG_ID not between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNull() {
            addCriterion("ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNotNull() {
            addCriterion("ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrgNameEqualTo(String value) {
            addCriterion("ORG_NAME =", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotEqualTo(String value) {
            addCriterion("ORG_NAME <>", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThan(String value) {
            addCriterion("ORG_NAME >", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_NAME >=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThan(String value) {
            addCriterion("ORG_NAME <", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThanOrEqualTo(String value) {
            addCriterion("ORG_NAME <=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLike(String value) {
            addCriterion("ORG_NAME like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotLike(String value) {
            addCriterion("ORG_NAME not like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameIn(List<String> values) {
            addCriterion("ORG_NAME in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotIn(List<String> values) {
            addCriterion("ORG_NAME not in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameBetween(String value1, String value2) {
            addCriterion("ORG_NAME between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotBetween(String value1, String value2) {
            addCriterion("ORG_NAME not between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andParentOrgIdIsNull() {
            addCriterion("PARENT_ORG_ID is null");
            return (Criteria) this;
        }

        public Criteria andParentOrgIdIsNotNull() {
            addCriterion("PARENT_ORG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andParentOrgIdEqualTo(String value) {
            addCriterion("PARENT_ORG_ID =", value, "parentOrgId");
            return (Criteria) this;
        }

        public Criteria andParentOrgIdNotEqualTo(String value) {
            addCriterion("PARENT_ORG_ID <>", value, "parentOrgId");
            return (Criteria) this;
        }

        public Criteria andParentOrgIdGreaterThan(String value) {
            addCriterion("PARENT_ORG_ID >", value, "parentOrgId");
            return (Criteria) this;
        }

        public Criteria andParentOrgIdGreaterThanOrEqualTo(String value) {
            addCriterion("PARENT_ORG_ID >=", value, "parentOrgId");
            return (Criteria) this;
        }

        public Criteria andParentOrgIdLessThan(String value) {
            addCriterion("PARENT_ORG_ID <", value, "parentOrgId");
            return (Criteria) this;
        }

        public Criteria andParentOrgIdLessThanOrEqualTo(String value) {
            addCriterion("PARENT_ORG_ID <=", value, "parentOrgId");
            return (Criteria) this;
        }

        public Criteria andParentOrgIdLike(String value) {
            addCriterion("PARENT_ORG_ID like", value, "parentOrgId");
            return (Criteria) this;
        }

        public Criteria andParentOrgIdNotLike(String value) {
            addCriterion("PARENT_ORG_ID not like", value, "parentOrgId");
            return (Criteria) this;
        }

        public Criteria andParentOrgIdIn(List<String> values) {
            addCriterion("PARENT_ORG_ID in", values, "parentOrgId");
            return (Criteria) this;
        }

        public Criteria andParentOrgIdNotIn(List<String> values) {
            addCriterion("PARENT_ORG_ID not in", values, "parentOrgId");
            return (Criteria) this;
        }

        public Criteria andParentOrgIdBetween(String value1, String value2) {
            addCriterion("PARENT_ORG_ID between", value1, value2, "parentOrgId");
            return (Criteria) this;
        }

        public Criteria andParentOrgIdNotBetween(String value1, String value2) {
            addCriterion("PARENT_ORG_ID not between", value1, value2, "parentOrgId");
            return (Criteria) this;
        }

        public Criteria andOrgDescriptionIsNull() {
            addCriterion("ORG_DESCRIPTION is null");
            return (Criteria) this;
        }

        public Criteria andOrgDescriptionIsNotNull() {
            addCriterion("ORG_DESCRIPTION is not null");
            return (Criteria) this;
        }

        public Criteria andOrgDescriptionEqualTo(String value) {
            addCriterion("ORG_DESCRIPTION =", value, "orgDescription");
            return (Criteria) this;
        }

        public Criteria andOrgDescriptionNotEqualTo(String value) {
            addCriterion("ORG_DESCRIPTION <>", value, "orgDescription");
            return (Criteria) this;
        }

        public Criteria andOrgDescriptionGreaterThan(String value) {
            addCriterion("ORG_DESCRIPTION >", value, "orgDescription");
            return (Criteria) this;
        }

        public Criteria andOrgDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_DESCRIPTION >=", value, "orgDescription");
            return (Criteria) this;
        }

        public Criteria andOrgDescriptionLessThan(String value) {
            addCriterion("ORG_DESCRIPTION <", value, "orgDescription");
            return (Criteria) this;
        }

        public Criteria andOrgDescriptionLessThanOrEqualTo(String value) {
            addCriterion("ORG_DESCRIPTION <=", value, "orgDescription");
            return (Criteria) this;
        }

        public Criteria andOrgDescriptionLike(String value) {
            addCriterion("ORG_DESCRIPTION like", value, "orgDescription");
            return (Criteria) this;
        }

        public Criteria andOrgDescriptionNotLike(String value) {
            addCriterion("ORG_DESCRIPTION not like", value, "orgDescription");
            return (Criteria) this;
        }

        public Criteria andOrgDescriptionIn(List<String> values) {
            addCriterion("ORG_DESCRIPTION in", values, "orgDescription");
            return (Criteria) this;
        }

        public Criteria andOrgDescriptionNotIn(List<String> values) {
            addCriterion("ORG_DESCRIPTION not in", values, "orgDescription");
            return (Criteria) this;
        }

        public Criteria andOrgDescriptionBetween(String value1, String value2) {
            addCriterion("ORG_DESCRIPTION between", value1, value2, "orgDescription");
            return (Criteria) this;
        }

        public Criteria andOrgDescriptionNotBetween(String value1, String value2) {
            addCriterion("ORG_DESCRIPTION not between", value1, value2, "orgDescription");
            return (Criteria) this;
        }

        public Criteria andOrgTelIsNull() {
            addCriterion("ORG_TEL is null");
            return (Criteria) this;
        }

        public Criteria andOrgTelIsNotNull() {
            addCriterion("ORG_TEL is not null");
            return (Criteria) this;
        }

        public Criteria andOrgTelEqualTo(String value) {
            addCriterion("ORG_TEL =", value, "orgTel");
            return (Criteria) this;
        }

        public Criteria andOrgTelNotEqualTo(String value) {
            addCriterion("ORG_TEL <>", value, "orgTel");
            return (Criteria) this;
        }

        public Criteria andOrgTelGreaterThan(String value) {
            addCriterion("ORG_TEL >", value, "orgTel");
            return (Criteria) this;
        }

        public Criteria andOrgTelGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_TEL >=", value, "orgTel");
            return (Criteria) this;
        }

        public Criteria andOrgTelLessThan(String value) {
            addCriterion("ORG_TEL <", value, "orgTel");
            return (Criteria) this;
        }

        public Criteria andOrgTelLessThanOrEqualTo(String value) {
            addCriterion("ORG_TEL <=", value, "orgTel");
            return (Criteria) this;
        }

        public Criteria andOrgTelLike(String value) {
            addCriterion("ORG_TEL like", value, "orgTel");
            return (Criteria) this;
        }

        public Criteria andOrgTelNotLike(String value) {
            addCriterion("ORG_TEL not like", value, "orgTel");
            return (Criteria) this;
        }

        public Criteria andOrgTelIn(List<String> values) {
            addCriterion("ORG_TEL in", values, "orgTel");
            return (Criteria) this;
        }

        public Criteria andOrgTelNotIn(List<String> values) {
            addCriterion("ORG_TEL not in", values, "orgTel");
            return (Criteria) this;
        }

        public Criteria andOrgTelBetween(String value1, String value2) {
            addCriterion("ORG_TEL between", value1, value2, "orgTel");
            return (Criteria) this;
        }

        public Criteria andOrgTelNotBetween(String value1, String value2) {
            addCriterion("ORG_TEL not between", value1, value2, "orgTel");
            return (Criteria) this;
        }

        public Criteria andOrgAddressIsNull() {
            addCriterion("ORG_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andOrgAddressIsNotNull() {
            addCriterion("ORG_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andOrgAddressEqualTo(String value) {
            addCriterion("ORG_ADDRESS =", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressNotEqualTo(String value) {
            addCriterion("ORG_ADDRESS <>", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressGreaterThan(String value) {
            addCriterion("ORG_ADDRESS >", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_ADDRESS >=", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressLessThan(String value) {
            addCriterion("ORG_ADDRESS <", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressLessThanOrEqualTo(String value) {
            addCriterion("ORG_ADDRESS <=", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressLike(String value) {
            addCriterion("ORG_ADDRESS like", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressNotLike(String value) {
            addCriterion("ORG_ADDRESS not like", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressIn(List<String> values) {
            addCriterion("ORG_ADDRESS in", values, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressNotIn(List<String> values) {
            addCriterion("ORG_ADDRESS not in", values, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressBetween(String value1, String value2) {
            addCriterion("ORG_ADDRESS between", value1, value2, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressNotBetween(String value1, String value2) {
            addCriterion("ORG_ADDRESS not between", value1, value2, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgContactIsNull() {
            addCriterion("ORG_CONTACT is null");
            return (Criteria) this;
        }

        public Criteria andOrgContactIsNotNull() {
            addCriterion("ORG_CONTACT is not null");
            return (Criteria) this;
        }

        public Criteria andOrgContactEqualTo(String value) {
            addCriterion("ORG_CONTACT =", value, "orgContact");
            return (Criteria) this;
        }

        public Criteria andOrgContactNotEqualTo(String value) {
            addCriterion("ORG_CONTACT <>", value, "orgContact");
            return (Criteria) this;
        }

        public Criteria andOrgContactGreaterThan(String value) {
            addCriterion("ORG_CONTACT >", value, "orgContact");
            return (Criteria) this;
        }

        public Criteria andOrgContactGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_CONTACT >=", value, "orgContact");
            return (Criteria) this;
        }

        public Criteria andOrgContactLessThan(String value) {
            addCriterion("ORG_CONTACT <", value, "orgContact");
            return (Criteria) this;
        }

        public Criteria andOrgContactLessThanOrEqualTo(String value) {
            addCriterion("ORG_CONTACT <=", value, "orgContact");
            return (Criteria) this;
        }

        public Criteria andOrgContactLike(String value) {
            addCriterion("ORG_CONTACT like", value, "orgContact");
            return (Criteria) this;
        }

        public Criteria andOrgContactNotLike(String value) {
            addCriterion("ORG_CONTACT not like", value, "orgContact");
            return (Criteria) this;
        }

        public Criteria andOrgContactIn(List<String> values) {
            addCriterion("ORG_CONTACT in", values, "orgContact");
            return (Criteria) this;
        }

        public Criteria andOrgContactNotIn(List<String> values) {
            addCriterion("ORG_CONTACT not in", values, "orgContact");
            return (Criteria) this;
        }

        public Criteria andOrgContactBetween(String value1, String value2) {
            addCriterion("ORG_CONTACT between", value1, value2, "orgContact");
            return (Criteria) this;
        }

        public Criteria andOrgContactNotBetween(String value1, String value2) {
            addCriterion("ORG_CONTACT not between", value1, value2, "orgContact");
            return (Criteria) this;
        }

        public Criteria andOrgPathIsNull() {
            addCriterion("ORG_PATH is null");
            return (Criteria) this;
        }

        public Criteria andOrgPathIsNotNull() {
            addCriterion("ORG_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andOrgPathEqualTo(String value) {
            addCriterion("ORG_PATH =", value, "orgPath");
            return (Criteria) this;
        }

        public Criteria andOrgPathNotEqualTo(String value) {
            addCriterion("ORG_PATH <>", value, "orgPath");
            return (Criteria) this;
        }

        public Criteria andOrgPathGreaterThan(String value) {
            addCriterion("ORG_PATH >", value, "orgPath");
            return (Criteria) this;
        }

        public Criteria andOrgPathGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_PATH >=", value, "orgPath");
            return (Criteria) this;
        }

        public Criteria andOrgPathLessThan(String value) {
            addCriterion("ORG_PATH <", value, "orgPath");
            return (Criteria) this;
        }

        public Criteria andOrgPathLessThanOrEqualTo(String value) {
            addCriterion("ORG_PATH <=", value, "orgPath");
            return (Criteria) this;
        }

        public Criteria andOrgPathLike(String value) {
            addCriterion("ORG_PATH like", value, "orgPath");
            return (Criteria) this;
        }

        public Criteria andOrgPathNotLike(String value) {
            addCriterion("ORG_PATH not like", value, "orgPath");
            return (Criteria) this;
        }

        public Criteria andOrgPathIn(List<String> values) {
            addCriterion("ORG_PATH in", values, "orgPath");
            return (Criteria) this;
        }

        public Criteria andOrgPathNotIn(List<String> values) {
            addCriterion("ORG_PATH not in", values, "orgPath");
            return (Criteria) this;
        }

        public Criteria andOrgPathBetween(String value1, String value2) {
            addCriterion("ORG_PATH between", value1, value2, "orgPath");
            return (Criteria) this;
        }

        public Criteria andOrgPathNotBetween(String value1, String value2) {
            addCriterion("ORG_PATH not between", value1, value2, "orgPath");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIsNull() {
            addCriterion("ORG_LEVEL is null");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIsNotNull() {
            addCriterion("ORG_LEVEL is not null");
            return (Criteria) this;
        }

        public Criteria andOrgLevelEqualTo(String value) {
            addCriterion("ORG_LEVEL =", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelNotEqualTo(String value) {
            addCriterion("ORG_LEVEL <>", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelGreaterThan(String value) {
            addCriterion("ORG_LEVEL >", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_LEVEL >=", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelLessThan(String value) {
            addCriterion("ORG_LEVEL <", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelLessThanOrEqualTo(String value) {
            addCriterion("ORG_LEVEL <=", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelLike(String value) {
            addCriterion("ORG_LEVEL like", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelNotLike(String value) {
            addCriterion("ORG_LEVEL not like", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIn(List<String> values) {
            addCriterion("ORG_LEVEL in", values, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelNotIn(List<String> values) {
            addCriterion("ORG_LEVEL not in", values, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelBetween(String value1, String value2) {
            addCriterion("ORG_LEVEL between", value1, value2, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelNotBetween(String value1, String value2) {
            addCriterion("ORG_LEVEL not between", value1, value2, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgEnabledIsNull() {
            addCriterion("ORG_ENABLED is null");
            return (Criteria) this;
        }

        public Criteria andOrgEnabledIsNotNull() {
            addCriterion("ORG_ENABLED is not null");
            return (Criteria) this;
        }

        public Criteria andOrgEnabledEqualTo(Long value) {
            addCriterion("ORG_ENABLED =", value, "orgEnabled");
            return (Criteria) this;
        }

        public Criteria andOrgEnabledNotEqualTo(Long value) {
            addCriterion("ORG_ENABLED <>", value, "orgEnabled");
            return (Criteria) this;
        }

        public Criteria andOrgEnabledGreaterThan(Long value) {
            addCriterion("ORG_ENABLED >", value, "orgEnabled");
            return (Criteria) this;
        }

        public Criteria andOrgEnabledGreaterThanOrEqualTo(Long value) {
            addCriterion("ORG_ENABLED >=", value, "orgEnabled");
            return (Criteria) this;
        }

        public Criteria andOrgEnabledLessThan(Long value) {
            addCriterion("ORG_ENABLED <", value, "orgEnabled");
            return (Criteria) this;
        }

        public Criteria andOrgEnabledLessThanOrEqualTo(Long value) {
            addCriterion("ORG_ENABLED <=", value, "orgEnabled");
            return (Criteria) this;
        }

        public Criteria andOrgEnabledIn(List<Long> values) {
            addCriterion("ORG_ENABLED in", values, "orgEnabled");
            return (Criteria) this;
        }

        public Criteria andOrgEnabledNotIn(List<Long> values) {
            addCriterion("ORG_ENABLED not in", values, "orgEnabled");
            return (Criteria) this;
        }

        public Criteria andOrgEnabledBetween(Long value1, Long value2) {
            addCriterion("ORG_ENABLED between", value1, value2, "orgEnabled");
            return (Criteria) this;
        }

        public Criteria andOrgEnabledNotBetween(Long value1, Long value2) {
            addCriterion("ORG_ENABLED not between", value1, value2, "orgEnabled");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIsNull() {
            addCriterion("ORG_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIsNotNull() {
            addCriterion("ORG_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andOrgTypeEqualTo(String value) {
            addCriterion("ORG_TYPE =", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNotEqualTo(String value) {
            addCriterion("ORG_TYPE <>", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeGreaterThan(String value) {
            addCriterion("ORG_TYPE >", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_TYPE >=", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeLessThan(String value) {
            addCriterion("ORG_TYPE <", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeLessThanOrEqualTo(String value) {
            addCriterion("ORG_TYPE <=", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeLike(String value) {
            addCriterion("ORG_TYPE like", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNotLike(String value) {
            addCriterion("ORG_TYPE not like", value, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeIn(List<String> values) {
            addCriterion("ORG_TYPE in", values, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNotIn(List<String> values) {
            addCriterion("ORG_TYPE not in", values, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeBetween(String value1, String value2) {
            addCriterion("ORG_TYPE between", value1, value2, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgTypeNotBetween(String value1, String value2) {
            addCriterion("ORG_TYPE not between", value1, value2, "orgType");
            return (Criteria) this;
        }

        public Criteria andOrgPropertyIsNull() {
            addCriterion("ORG_PROPERTY is null");
            return (Criteria) this;
        }

        public Criteria andOrgPropertyIsNotNull() {
            addCriterion("ORG_PROPERTY is not null");
            return (Criteria) this;
        }

        public Criteria andOrgPropertyEqualTo(String value) {
            addCriterion("ORG_PROPERTY =", value, "orgProperty");
            return (Criteria) this;
        }

        public Criteria andOrgPropertyNotEqualTo(String value) {
            addCriterion("ORG_PROPERTY <>", value, "orgProperty");
            return (Criteria) this;
        }

        public Criteria andOrgPropertyGreaterThan(String value) {
            addCriterion("ORG_PROPERTY >", value, "orgProperty");
            return (Criteria) this;
        }

        public Criteria andOrgPropertyGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_PROPERTY >=", value, "orgProperty");
            return (Criteria) this;
        }

        public Criteria andOrgPropertyLessThan(String value) {
            addCriterion("ORG_PROPERTY <", value, "orgProperty");
            return (Criteria) this;
        }

        public Criteria andOrgPropertyLessThanOrEqualTo(String value) {
            addCriterion("ORG_PROPERTY <=", value, "orgProperty");
            return (Criteria) this;
        }

        public Criteria andOrgPropertyLike(String value) {
            addCriterion("ORG_PROPERTY like", value, "orgProperty");
            return (Criteria) this;
        }

        public Criteria andOrgPropertyNotLike(String value) {
            addCriterion("ORG_PROPERTY not like", value, "orgProperty");
            return (Criteria) this;
        }

        public Criteria andOrgPropertyIn(List<String> values) {
            addCriterion("ORG_PROPERTY in", values, "orgProperty");
            return (Criteria) this;
        }

        public Criteria andOrgPropertyNotIn(List<String> values) {
            addCriterion("ORG_PROPERTY not in", values, "orgProperty");
            return (Criteria) this;
        }

        public Criteria andOrgPropertyBetween(String value1, String value2) {
            addCriterion("ORG_PROPERTY between", value1, value2, "orgProperty");
            return (Criteria) this;
        }

        public Criteria andOrgPropertyNotBetween(String value1, String value2) {
            addCriterion("ORG_PROPERTY not between", value1, value2, "orgProperty");
            return (Criteria) this;
        }

        public Criteria andOrgOrderIsNull() {
            addCriterion("ORG_ORDER is null");
            return (Criteria) this;
        }

        public Criteria andOrgOrderIsNotNull() {
            addCriterion("ORG_ORDER is not null");
            return (Criteria) this;
        }

        public Criteria andOrgOrderEqualTo(Long value) {
            addCriterion("ORG_ORDER =", value, "orgOrder");
            return (Criteria) this;
        }

        public Criteria andOrgOrderNotEqualTo(Long value) {
            addCriterion("ORG_ORDER <>", value, "orgOrder");
            return (Criteria) this;
        }

        public Criteria andOrgOrderGreaterThan(Long value) {
            addCriterion("ORG_ORDER >", value, "orgOrder");
            return (Criteria) this;
        }

        public Criteria andOrgOrderGreaterThanOrEqualTo(Long value) {
            addCriterion("ORG_ORDER >=", value, "orgOrder");
            return (Criteria) this;
        }

        public Criteria andOrgOrderLessThan(Long value) {
            addCriterion("ORG_ORDER <", value, "orgOrder");
            return (Criteria) this;
        }

        public Criteria andOrgOrderLessThanOrEqualTo(Long value) {
            addCriterion("ORG_ORDER <=", value, "orgOrder");
            return (Criteria) this;
        }

        public Criteria andOrgOrderIn(List<Long> values) {
            addCriterion("ORG_ORDER in", values, "orgOrder");
            return (Criteria) this;
        }

        public Criteria andOrgOrderNotIn(List<Long> values) {
            addCriterion("ORG_ORDER not in", values, "orgOrder");
            return (Criteria) this;
        }

        public Criteria andOrgOrderBetween(Long value1, Long value2) {
            addCriterion("ORG_ORDER between", value1, value2, "orgOrder");
            return (Criteria) this;
        }

        public Criteria andOrgOrderNotBetween(Long value1, Long value2) {
            addCriterion("ORG_ORDER not between", value1, value2, "orgOrder");
            return (Criteria) this;
        }

        public Criteria andOrgVersionIsNull() {
            addCriterion("ORG_VERSION is null");
            return (Criteria) this;
        }

        public Criteria andOrgVersionIsNotNull() {
            addCriterion("ORG_VERSION is not null");
            return (Criteria) this;
        }

        public Criteria andOrgVersionEqualTo(Long value) {
            addCriterion("ORG_VERSION =", value, "orgVersion");
            return (Criteria) this;
        }

        public Criteria andOrgVersionNotEqualTo(Long value) {
            addCriterion("ORG_VERSION <>", value, "orgVersion");
            return (Criteria) this;
        }

        public Criteria andOrgVersionGreaterThan(Long value) {
            addCriterion("ORG_VERSION >", value, "orgVersion");
            return (Criteria) this;
        }

        public Criteria andOrgVersionGreaterThanOrEqualTo(Long value) {
            addCriterion("ORG_VERSION >=", value, "orgVersion");
            return (Criteria) this;
        }

        public Criteria andOrgVersionLessThan(Long value) {
            addCriterion("ORG_VERSION <", value, "orgVersion");
            return (Criteria) this;
        }

        public Criteria andOrgVersionLessThanOrEqualTo(Long value) {
            addCriterion("ORG_VERSION <=", value, "orgVersion");
            return (Criteria) this;
        }

        public Criteria andOrgVersionIn(List<Long> values) {
            addCriterion("ORG_VERSION in", values, "orgVersion");
            return (Criteria) this;
        }

        public Criteria andOrgVersionNotIn(List<Long> values) {
            addCriterion("ORG_VERSION not in", values, "orgVersion");
            return (Criteria) this;
        }

        public Criteria andOrgVersionBetween(Long value1, Long value2) {
            addCriterion("ORG_VERSION between", value1, value2, "orgVersion");
            return (Criteria) this;
        }

        public Criteria andOrgVersionNotBetween(Long value1, Long value2) {
            addCriterion("ORG_VERSION not between", value1, value2, "orgVersion");
            return (Criteria) this;
        }

        public Criteria andOrgCreatorIsNull() {
            addCriterion("ORG_CREATOR is null");
            return (Criteria) this;
        }

        public Criteria andOrgCreatorIsNotNull() {
            addCriterion("ORG_CREATOR is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCreatorEqualTo(String value) {
            addCriterion("ORG_CREATOR =", value, "orgCreator");
            return (Criteria) this;
        }

        public Criteria andOrgCreatorNotEqualTo(String value) {
            addCriterion("ORG_CREATOR <>", value, "orgCreator");
            return (Criteria) this;
        }

        public Criteria andOrgCreatorGreaterThan(String value) {
            addCriterion("ORG_CREATOR >", value, "orgCreator");
            return (Criteria) this;
        }

        public Criteria andOrgCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_CREATOR >=", value, "orgCreator");
            return (Criteria) this;
        }

        public Criteria andOrgCreatorLessThan(String value) {
            addCriterion("ORG_CREATOR <", value, "orgCreator");
            return (Criteria) this;
        }

        public Criteria andOrgCreatorLessThanOrEqualTo(String value) {
            addCriterion("ORG_CREATOR <=", value, "orgCreator");
            return (Criteria) this;
        }

        public Criteria andOrgCreatorLike(String value) {
            addCriterion("ORG_CREATOR like", value, "orgCreator");
            return (Criteria) this;
        }

        public Criteria andOrgCreatorNotLike(String value) {
            addCriterion("ORG_CREATOR not like", value, "orgCreator");
            return (Criteria) this;
        }

        public Criteria andOrgCreatorIn(List<String> values) {
            addCriterion("ORG_CREATOR in", values, "orgCreator");
            return (Criteria) this;
        }

        public Criteria andOrgCreatorNotIn(List<String> values) {
            addCriterion("ORG_CREATOR not in", values, "orgCreator");
            return (Criteria) this;
        }

        public Criteria andOrgCreatorBetween(String value1, String value2) {
            addCriterion("ORG_CREATOR between", value1, value2, "orgCreator");
            return (Criteria) this;
        }

        public Criteria andOrgCreatorNotBetween(String value1, String value2) {
            addCriterion("ORG_CREATOR not between", value1, value2, "orgCreator");
            return (Criteria) this;
        }

        public Criteria andOrgCreatedIsNull() {
            addCriterion("ORG_CREATED is null");
            return (Criteria) this;
        }

        public Criteria andOrgCreatedIsNotNull() {
            addCriterion("ORG_CREATED is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCreatedEqualTo(Date value) {
            addCriterionForJDBCDate("ORG_CREATED =", value, "orgCreated");
            return (Criteria) this;
        }

        public Criteria andOrgCreatedNotEqualTo(Date value) {
            addCriterionForJDBCDate("ORG_CREATED <>", value, "orgCreated");
            return (Criteria) this;
        }

        public Criteria andOrgCreatedGreaterThan(Date value) {
            addCriterionForJDBCDate("ORG_CREATED >", value, "orgCreated");
            return (Criteria) this;
        }

        public Criteria andOrgCreatedGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ORG_CREATED >=", value, "orgCreated");
            return (Criteria) this;
        }

        public Criteria andOrgCreatedLessThan(Date value) {
            addCriterionForJDBCDate("ORG_CREATED <", value, "orgCreated");
            return (Criteria) this;
        }

        public Criteria andOrgCreatedLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ORG_CREATED <=", value, "orgCreated");
            return (Criteria) this;
        }

        public Criteria andOrgCreatedIn(List<Date> values) {
            addCriterionForJDBCDate("ORG_CREATED in", values, "orgCreated");
            return (Criteria) this;
        }

        public Criteria andOrgCreatedNotIn(List<Date> values) {
            addCriterionForJDBCDate("ORG_CREATED not in", values, "orgCreated");
            return (Criteria) this;
        }

        public Criteria andOrgCreatedBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ORG_CREATED between", value1, value2, "orgCreated");
            return (Criteria) this;
        }

        public Criteria andOrgCreatedNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ORG_CREATED not between", value1, value2, "orgCreated");
            return (Criteria) this;
        }

        public Criteria andOrgModifierIsNull() {
            addCriterion("ORG_MODIFIER is null");
            return (Criteria) this;
        }

        public Criteria andOrgModifierIsNotNull() {
            addCriterion("ORG_MODIFIER is not null");
            return (Criteria) this;
        }

        public Criteria andOrgModifierEqualTo(String value) {
            addCriterion("ORG_MODIFIER =", value, "orgModifier");
            return (Criteria) this;
        }

        public Criteria andOrgModifierNotEqualTo(String value) {
            addCriterion("ORG_MODIFIER <>", value, "orgModifier");
            return (Criteria) this;
        }

        public Criteria andOrgModifierGreaterThan(String value) {
            addCriterion("ORG_MODIFIER >", value, "orgModifier");
            return (Criteria) this;
        }

        public Criteria andOrgModifierGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_MODIFIER >=", value, "orgModifier");
            return (Criteria) this;
        }

        public Criteria andOrgModifierLessThan(String value) {
            addCriterion("ORG_MODIFIER <", value, "orgModifier");
            return (Criteria) this;
        }

        public Criteria andOrgModifierLessThanOrEqualTo(String value) {
            addCriterion("ORG_MODIFIER <=", value, "orgModifier");
            return (Criteria) this;
        }

        public Criteria andOrgModifierLike(String value) {
            addCriterion("ORG_MODIFIER like", value, "orgModifier");
            return (Criteria) this;
        }

        public Criteria andOrgModifierNotLike(String value) {
            addCriterion("ORG_MODIFIER not like", value, "orgModifier");
            return (Criteria) this;
        }

        public Criteria andOrgModifierIn(List<String> values) {
            addCriterion("ORG_MODIFIER in", values, "orgModifier");
            return (Criteria) this;
        }

        public Criteria andOrgModifierNotIn(List<String> values) {
            addCriterion("ORG_MODIFIER not in", values, "orgModifier");
            return (Criteria) this;
        }

        public Criteria andOrgModifierBetween(String value1, String value2) {
            addCriterion("ORG_MODIFIER between", value1, value2, "orgModifier");
            return (Criteria) this;
        }

        public Criteria andOrgModifierNotBetween(String value1, String value2) {
            addCriterion("ORG_MODIFIER not between", value1, value2, "orgModifier");
            return (Criteria) this;
        }

        public Criteria andOrgModifiedIsNull() {
            addCriterion("ORG_MODIFIED is null");
            return (Criteria) this;
        }

        public Criteria andOrgModifiedIsNotNull() {
            addCriterion("ORG_MODIFIED is not null");
            return (Criteria) this;
        }

        public Criteria andOrgModifiedEqualTo(Date value) {
            addCriterionForJDBCDate("ORG_MODIFIED =", value, "orgModified");
            return (Criteria) this;
        }

        public Criteria andOrgModifiedNotEqualTo(Date value) {
            addCriterionForJDBCDate("ORG_MODIFIED <>", value, "orgModified");
            return (Criteria) this;
        }

        public Criteria andOrgModifiedGreaterThan(Date value) {
            addCriterionForJDBCDate("ORG_MODIFIED >", value, "orgModified");
            return (Criteria) this;
        }

        public Criteria andOrgModifiedGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ORG_MODIFIED >=", value, "orgModified");
            return (Criteria) this;
        }

        public Criteria andOrgModifiedLessThan(Date value) {
            addCriterionForJDBCDate("ORG_MODIFIED <", value, "orgModified");
            return (Criteria) this;
        }

        public Criteria andOrgModifiedLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("ORG_MODIFIED <=", value, "orgModified");
            return (Criteria) this;
        }

        public Criteria andOrgModifiedIn(List<Date> values) {
            addCriterionForJDBCDate("ORG_MODIFIED in", values, "orgModified");
            return (Criteria) this;
        }

        public Criteria andOrgModifiedNotIn(List<Date> values) {
            addCriterionForJDBCDate("ORG_MODIFIED not in", values, "orgModified");
            return (Criteria) this;
        }

        public Criteria andOrgModifiedBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ORG_MODIFIED between", value1, value2, "orgModified");
            return (Criteria) this;
        }

        public Criteria andOrgModifiedNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("ORG_MODIFIED not between", value1, value2, "orgModified");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("PROVINCE is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("PROVINCE is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("PROVINCE =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("PROVINCE <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("PROVINCE >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("PROVINCE >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("PROVINCE <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("PROVINCE <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("PROVINCE like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("PROVINCE not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("PROVINCE in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("PROVINCE not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("PROVINCE between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("PROVINCE not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andOrglnameIsNull() {
            addCriterion("ORGLNAME is null");
            return (Criteria) this;
        }

        public Criteria andOrglnameIsNotNull() {
            addCriterion("ORGLNAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrglnameEqualTo(String value) {
            addCriterion("ORGLNAME =", value, "orglname");
            return (Criteria) this;
        }

        public Criteria andOrglnameNotEqualTo(String value) {
            addCriterion("ORGLNAME <>", value, "orglname");
            return (Criteria) this;
        }

        public Criteria andOrglnameGreaterThan(String value) {
            addCriterion("ORGLNAME >", value, "orglname");
            return (Criteria) this;
        }

        public Criteria andOrglnameGreaterThanOrEqualTo(String value) {
            addCriterion("ORGLNAME >=", value, "orglname");
            return (Criteria) this;
        }

        public Criteria andOrglnameLessThan(String value) {
            addCriterion("ORGLNAME <", value, "orglname");
            return (Criteria) this;
        }

        public Criteria andOrglnameLessThanOrEqualTo(String value) {
            addCriterion("ORGLNAME <=", value, "orglname");
            return (Criteria) this;
        }

        public Criteria andOrglnameLike(String value) {
            addCriterion("ORGLNAME like", value, "orglname");
            return (Criteria) this;
        }

        public Criteria andOrglnameNotLike(String value) {
            addCriterion("ORGLNAME not like", value, "orglname");
            return (Criteria) this;
        }

        public Criteria andOrglnameIn(List<String> values) {
            addCriterion("ORGLNAME in", values, "orglname");
            return (Criteria) this;
        }

        public Criteria andOrglnameNotIn(List<String> values) {
            addCriterion("ORGLNAME not in", values, "orglname");
            return (Criteria) this;
        }

        public Criteria andOrglnameBetween(String value1, String value2) {
            addCriterion("ORGLNAME between", value1, value2, "orglname");
            return (Criteria) this;
        }

        public Criteria andOrglnameNotBetween(String value1, String value2) {
            addCriterion("ORGLNAME not between", value1, value2, "orglname");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("CITY is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("CITY is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("CITY =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("CITY <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("CITY >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("CITY >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("CITY <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("CITY <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("CITY like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("CITY not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("CITY in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("CITY not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("CITY between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("CITY not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andPostIsNull() {
            addCriterion("POST is null");
            return (Criteria) this;
        }

        public Criteria andPostIsNotNull() {
            addCriterion("POST is not null");
            return (Criteria) this;
        }

        public Criteria andPostEqualTo(String value) {
            addCriterion("POST =", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostNotEqualTo(String value) {
            addCriterion("POST <>", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostGreaterThan(String value) {
            addCriterion("POST >", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostGreaterThanOrEqualTo(String value) {
            addCriterion("POST >=", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostLessThan(String value) {
            addCriterion("POST <", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostLessThanOrEqualTo(String value) {
            addCriterion("POST <=", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostLike(String value) {
            addCriterion("POST like", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostNotLike(String value) {
            addCriterion("POST not like", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostIn(List<String> values) {
            addCriterion("POST in", values, "post");
            return (Criteria) this;
        }

        public Criteria andPostNotIn(List<String> values) {
            addCriterion("POST not in", values, "post");
            return (Criteria) this;
        }

        public Criteria andPostBetween(String value1, String value2) {
            addCriterion("POST between", value1, value2, "post");
            return (Criteria) this;
        }

        public Criteria andPostNotBetween(String value1, String value2) {
            addCriterion("POST not between", value1, value2, "post");
            return (Criteria) this;
        }

        public Criteria andFounddayIsNull() {
            addCriterion("FOUNDDAY is null");
            return (Criteria) this;
        }

        public Criteria andFounddayIsNotNull() {
            addCriterion("FOUNDDAY is not null");
            return (Criteria) this;
        }

        public Criteria andFounddayEqualTo(Date value) {
            addCriterionForJDBCDate("FOUNDDAY =", value, "foundday");
            return (Criteria) this;
        }

        public Criteria andFounddayNotEqualTo(Date value) {
            addCriterionForJDBCDate("FOUNDDAY <>", value, "foundday");
            return (Criteria) this;
        }

        public Criteria andFounddayGreaterThan(Date value) {
            addCriterionForJDBCDate("FOUNDDAY >", value, "foundday");
            return (Criteria) this;
        }

        public Criteria andFounddayGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("FOUNDDAY >=", value, "foundday");
            return (Criteria) this;
        }

        public Criteria andFounddayLessThan(Date value) {
            addCriterionForJDBCDate("FOUNDDAY <", value, "foundday");
            return (Criteria) this;
        }

        public Criteria andFounddayLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("FOUNDDAY <=", value, "foundday");
            return (Criteria) this;
        }

        public Criteria andFounddayIn(List<Date> values) {
            addCriterionForJDBCDate("FOUNDDAY in", values, "foundday");
            return (Criteria) this;
        }

        public Criteria andFounddayNotIn(List<Date> values) {
            addCriterionForJDBCDate("FOUNDDAY not in", values, "foundday");
            return (Criteria) this;
        }

        public Criteria andFounddayBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("FOUNDDAY between", value1, value2, "foundday");
            return (Criteria) this;
        }

        public Criteria andFounddayNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("FOUNDDAY not between", value1, value2, "foundday");
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