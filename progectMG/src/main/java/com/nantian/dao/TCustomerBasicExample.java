package com.nantian.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TCustomerBasicExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public TCustomerBasicExample() {
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

        public Criteria andCustomerIdIsNull() {
            addCriterion("CUSTOMER_ID is null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNotNull() {
            addCriterion("CUSTOMER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdEqualTo(String value) {
            addCriterion("CUSTOMER_ID =", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotEqualTo(String value) {
            addCriterion("CUSTOMER_ID <>", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThan(String value) {
            addCriterion("CUSTOMER_ID >", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_ID >=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThan(String value) {
            addCriterion("CUSTOMER_ID <", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_ID <=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLike(String value) {
            addCriterion("CUSTOMER_ID like", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotLike(String value) {
            addCriterion("CUSTOMER_ID not like", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIn(List<String> values) {
            addCriterion("CUSTOMER_ID in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotIn(List<String> values) {
            addCriterion("CUSTOMER_ID not in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdBetween(String value1, String value2) {
            addCriterion("CUSTOMER_ID between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotBetween(String value1, String value2) {
            addCriterion("CUSTOMER_ID not between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNull() {
            addCriterion("CUSTOMER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNotNull() {
            addCriterion("CUSTOMER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameEqualTo(String value) {
            addCriterion("CUSTOMER_NAME =", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotEqualTo(String value) {
            addCriterion("CUSTOMER_NAME <>", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThan(String value) {
            addCriterion("CUSTOMER_NAME >", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_NAME >=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThan(String value) {
            addCriterion("CUSTOMER_NAME <", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_NAME <=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLike(String value) {
            addCriterion("CUSTOMER_NAME like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotLike(String value) {
            addCriterion("CUSTOMER_NAME not like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIn(List<String> values) {
            addCriterion("CUSTOMER_NAME in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotIn(List<String> values) {
            addCriterion("CUSTOMER_NAME not in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameBetween(String value1, String value2) {
            addCriterion("CUSTOMER_NAME between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotBetween(String value1, String value2) {
            addCriterion("CUSTOMER_NAME not between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andCertificateFlagIsNull() {
            addCriterion("CERTIFICATE_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andCertificateFlagIsNotNull() {
            addCriterion("CERTIFICATE_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateFlagEqualTo(String value) {
            addCriterion("CERTIFICATE_FLAG =", value, "certificateFlag");
            return (Criteria) this;
        }

        public Criteria andCertificateFlagNotEqualTo(String value) {
            addCriterion("CERTIFICATE_FLAG <>", value, "certificateFlag");
            return (Criteria) this;
        }

        public Criteria andCertificateFlagGreaterThan(String value) {
            addCriterion("CERTIFICATE_FLAG >", value, "certificateFlag");
            return (Criteria) this;
        }

        public Criteria andCertificateFlagGreaterThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_FLAG >=", value, "certificateFlag");
            return (Criteria) this;
        }

        public Criteria andCertificateFlagLessThan(String value) {
            addCriterion("CERTIFICATE_FLAG <", value, "certificateFlag");
            return (Criteria) this;
        }

        public Criteria andCertificateFlagLessThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_FLAG <=", value, "certificateFlag");
            return (Criteria) this;
        }

        public Criteria andCertificateFlagLike(String value) {
            addCriterion("CERTIFICATE_FLAG like", value, "certificateFlag");
            return (Criteria) this;
        }

        public Criteria andCertificateFlagNotLike(String value) {
            addCriterion("CERTIFICATE_FLAG not like", value, "certificateFlag");
            return (Criteria) this;
        }

        public Criteria andCertificateFlagIn(List<String> values) {
            addCriterion("CERTIFICATE_FLAG in", values, "certificateFlag");
            return (Criteria) this;
        }

        public Criteria andCertificateFlagNotIn(List<String> values) {
            addCriterion("CERTIFICATE_FLAG not in", values, "certificateFlag");
            return (Criteria) this;
        }

        public Criteria andCertificateFlagBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_FLAG between", value1, value2, "certificateFlag");
            return (Criteria) this;
        }

        public Criteria andCertificateFlagNotBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_FLAG not between", value1, value2, "certificateFlag");
            return (Criteria) this;
        }

        public Criteria andBusinessNoIsNull() {
            addCriterion("BUSINESS_NO is null");
            return (Criteria) this;
        }

        public Criteria andBusinessNoIsNotNull() {
            addCriterion("BUSINESS_NO is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessNoEqualTo(String value) {
            addCriterion("BUSINESS_NO =", value, "businessNo");
            return (Criteria) this;
        }

        public Criteria andBusinessNoNotEqualTo(String value) {
            addCriterion("BUSINESS_NO <>", value, "businessNo");
            return (Criteria) this;
        }

        public Criteria andBusinessNoGreaterThan(String value) {
            addCriterion("BUSINESS_NO >", value, "businessNo");
            return (Criteria) this;
        }

        public Criteria andBusinessNoGreaterThanOrEqualTo(String value) {
            addCriterion("BUSINESS_NO >=", value, "businessNo");
            return (Criteria) this;
        }

        public Criteria andBusinessNoLessThan(String value) {
            addCriterion("BUSINESS_NO <", value, "businessNo");
            return (Criteria) this;
        }

        public Criteria andBusinessNoLessThanOrEqualTo(String value) {
            addCriterion("BUSINESS_NO <=", value, "businessNo");
            return (Criteria) this;
        }

        public Criteria andBusinessNoLike(String value) {
            addCriterion("BUSINESS_NO like", value, "businessNo");
            return (Criteria) this;
        }

        public Criteria andBusinessNoNotLike(String value) {
            addCriterion("BUSINESS_NO not like", value, "businessNo");
            return (Criteria) this;
        }

        public Criteria andBusinessNoIn(List<String> values) {
            addCriterion("BUSINESS_NO in", values, "businessNo");
            return (Criteria) this;
        }

        public Criteria andBusinessNoNotIn(List<String> values) {
            addCriterion("BUSINESS_NO not in", values, "businessNo");
            return (Criteria) this;
        }

        public Criteria andBusinessNoBetween(String value1, String value2) {
            addCriterion("BUSINESS_NO between", value1, value2, "businessNo");
            return (Criteria) this;
        }

        public Criteria andBusinessNoNotBetween(String value1, String value2) {
            addCriterion("BUSINESS_NO not between", value1, value2, "businessNo");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateIsNull() {
            addCriterion("REGISTRATION_DATE is null");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateIsNotNull() {
            addCriterion("REGISTRATION_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateEqualTo(Date value) {
            addCriterionForJDBCDate("REGISTRATION_DATE =", value, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("REGISTRATION_DATE <>", value, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateGreaterThan(Date value) {
            addCriterionForJDBCDate("REGISTRATION_DATE >", value, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("REGISTRATION_DATE >=", value, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateLessThan(Date value) {
            addCriterionForJDBCDate("REGISTRATION_DATE <", value, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("REGISTRATION_DATE <=", value, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateIn(List<Date> values) {
            addCriterionForJDBCDate("REGISTRATION_DATE in", values, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("REGISTRATION_DATE not in", values, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("REGISTRATION_DATE between", value1, value2, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("REGISTRATION_DATE not between", value1, value2, "registrationDate");
            return (Criteria) this;
        }

        public Criteria andIfloanCardIsNull() {
            addCriterion("IFLOAN_CARD is null");
            return (Criteria) this;
        }

        public Criteria andIfloanCardIsNotNull() {
            addCriterion("IFLOAN_CARD is not null");
            return (Criteria) this;
        }

        public Criteria andIfloanCardEqualTo(String value) {
            addCriterion("IFLOAN_CARD =", value, "ifloanCard");
            return (Criteria) this;
        }

        public Criteria andIfloanCardNotEqualTo(String value) {
            addCriterion("IFLOAN_CARD <>", value, "ifloanCard");
            return (Criteria) this;
        }

        public Criteria andIfloanCardGreaterThan(String value) {
            addCriterion("IFLOAN_CARD >", value, "ifloanCard");
            return (Criteria) this;
        }

        public Criteria andIfloanCardGreaterThanOrEqualTo(String value) {
            addCriterion("IFLOAN_CARD >=", value, "ifloanCard");
            return (Criteria) this;
        }

        public Criteria andIfloanCardLessThan(String value) {
            addCriterion("IFLOAN_CARD <", value, "ifloanCard");
            return (Criteria) this;
        }

        public Criteria andIfloanCardLessThanOrEqualTo(String value) {
            addCriterion("IFLOAN_CARD <=", value, "ifloanCard");
            return (Criteria) this;
        }

        public Criteria andIfloanCardLike(String value) {
            addCriterion("IFLOAN_CARD like", value, "ifloanCard");
            return (Criteria) this;
        }

        public Criteria andIfloanCardNotLike(String value) {
            addCriterion("IFLOAN_CARD not like", value, "ifloanCard");
            return (Criteria) this;
        }

        public Criteria andIfloanCardIn(List<String> values) {
            addCriterion("IFLOAN_CARD in", values, "ifloanCard");
            return (Criteria) this;
        }

        public Criteria andIfloanCardNotIn(List<String> values) {
            addCriterion("IFLOAN_CARD not in", values, "ifloanCard");
            return (Criteria) this;
        }

        public Criteria andIfloanCardBetween(String value1, String value2) {
            addCriterion("IFLOAN_CARD between", value1, value2, "ifloanCard");
            return (Criteria) this;
        }

        public Criteria andIfloanCardNotBetween(String value1, String value2) {
            addCriterion("IFLOAN_CARD not between", value1, value2, "ifloanCard");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTypeIsNull() {
            addCriterion("ENTERPRISE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTypeIsNotNull() {
            addCriterion("ENTERPRISE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTypeEqualTo(String value) {
            addCriterion("ENTERPRISE_TYPE =", value, "enterpriseType");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTypeNotEqualTo(String value) {
            addCriterion("ENTERPRISE_TYPE <>", value, "enterpriseType");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTypeGreaterThan(String value) {
            addCriterion("ENTERPRISE_TYPE >", value, "enterpriseType");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ENTERPRISE_TYPE >=", value, "enterpriseType");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTypeLessThan(String value) {
            addCriterion("ENTERPRISE_TYPE <", value, "enterpriseType");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTypeLessThanOrEqualTo(String value) {
            addCriterion("ENTERPRISE_TYPE <=", value, "enterpriseType");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTypeLike(String value) {
            addCriterion("ENTERPRISE_TYPE like", value, "enterpriseType");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTypeNotLike(String value) {
            addCriterion("ENTERPRISE_TYPE not like", value, "enterpriseType");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTypeIn(List<String> values) {
            addCriterion("ENTERPRISE_TYPE in", values, "enterpriseType");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTypeNotIn(List<String> values) {
            addCriterion("ENTERPRISE_TYPE not in", values, "enterpriseType");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTypeBetween(String value1, String value2) {
            addCriterion("ENTERPRISE_TYPE between", value1, value2, "enterpriseType");
            return (Criteria) this;
        }

        public Criteria andEnterpriseTypeNotBetween(String value1, String value2) {
            addCriterion("ENTERPRISE_TYPE not between", value1, value2, "enterpriseType");
            return (Criteria) this;
        }

        public Criteria andActualControllerTypeIsNull() {
            addCriterion("ACTUAL_CONTROLLER_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andActualControllerTypeIsNotNull() {
            addCriterion("ACTUAL_CONTROLLER_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andActualControllerTypeEqualTo(String value) {
            addCriterion("ACTUAL_CONTROLLER_TYPE =", value, "actualControllerType");
            return (Criteria) this;
        }

        public Criteria andActualControllerTypeNotEqualTo(String value) {
            addCriterion("ACTUAL_CONTROLLER_TYPE <>", value, "actualControllerType");
            return (Criteria) this;
        }

        public Criteria andActualControllerTypeGreaterThan(String value) {
            addCriterion("ACTUAL_CONTROLLER_TYPE >", value, "actualControllerType");
            return (Criteria) this;
        }

        public Criteria andActualControllerTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ACTUAL_CONTROLLER_TYPE >=", value, "actualControllerType");
            return (Criteria) this;
        }

        public Criteria andActualControllerTypeLessThan(String value) {
            addCriterion("ACTUAL_CONTROLLER_TYPE <", value, "actualControllerType");
            return (Criteria) this;
        }

        public Criteria andActualControllerTypeLessThanOrEqualTo(String value) {
            addCriterion("ACTUAL_CONTROLLER_TYPE <=", value, "actualControllerType");
            return (Criteria) this;
        }

        public Criteria andActualControllerTypeLike(String value) {
            addCriterion("ACTUAL_CONTROLLER_TYPE like", value, "actualControllerType");
            return (Criteria) this;
        }

        public Criteria andActualControllerTypeNotLike(String value) {
            addCriterion("ACTUAL_CONTROLLER_TYPE not like", value, "actualControllerType");
            return (Criteria) this;
        }

        public Criteria andActualControllerTypeIn(List<String> values) {
            addCriterion("ACTUAL_CONTROLLER_TYPE in", values, "actualControllerType");
            return (Criteria) this;
        }

        public Criteria andActualControllerTypeNotIn(List<String> values) {
            addCriterion("ACTUAL_CONTROLLER_TYPE not in", values, "actualControllerType");
            return (Criteria) this;
        }

        public Criteria andActualControllerTypeBetween(String value1, String value2) {
            addCriterion("ACTUAL_CONTROLLER_TYPE between", value1, value2, "actualControllerType");
            return (Criteria) this;
        }

        public Criteria andActualControllerTypeNotBetween(String value1, String value2) {
            addCriterion("ACTUAL_CONTROLLER_TYPE not between", value1, value2, "actualControllerType");
            return (Criteria) this;
        }

        public Criteria andActualControllerIsNull() {
            addCriterion("ACTUAL_CONTROLLER is null");
            return (Criteria) this;
        }

        public Criteria andActualControllerIsNotNull() {
            addCriterion("ACTUAL_CONTROLLER is not null");
            return (Criteria) this;
        }

        public Criteria andActualControllerEqualTo(String value) {
            addCriterion("ACTUAL_CONTROLLER =", value, "actualController");
            return (Criteria) this;
        }

        public Criteria andActualControllerNotEqualTo(String value) {
            addCriterion("ACTUAL_CONTROLLER <>", value, "actualController");
            return (Criteria) this;
        }

        public Criteria andActualControllerGreaterThan(String value) {
            addCriterion("ACTUAL_CONTROLLER >", value, "actualController");
            return (Criteria) this;
        }

        public Criteria andActualControllerGreaterThanOrEqualTo(String value) {
            addCriterion("ACTUAL_CONTROLLER >=", value, "actualController");
            return (Criteria) this;
        }

        public Criteria andActualControllerLessThan(String value) {
            addCriterion("ACTUAL_CONTROLLER <", value, "actualController");
            return (Criteria) this;
        }

        public Criteria andActualControllerLessThanOrEqualTo(String value) {
            addCriterion("ACTUAL_CONTROLLER <=", value, "actualController");
            return (Criteria) this;
        }

        public Criteria andActualControllerLike(String value) {
            addCriterion("ACTUAL_CONTROLLER like", value, "actualController");
            return (Criteria) this;
        }

        public Criteria andActualControllerNotLike(String value) {
            addCriterion("ACTUAL_CONTROLLER not like", value, "actualController");
            return (Criteria) this;
        }

        public Criteria andActualControllerIn(List<String> values) {
            addCriterion("ACTUAL_CONTROLLER in", values, "actualController");
            return (Criteria) this;
        }

        public Criteria andActualControllerNotIn(List<String> values) {
            addCriterion("ACTUAL_CONTROLLER not in", values, "actualController");
            return (Criteria) this;
        }

        public Criteria andActualControllerBetween(String value1, String value2) {
            addCriterion("ACTUAL_CONTROLLER between", value1, value2, "actualController");
            return (Criteria) this;
        }

        public Criteria andActualControllerNotBetween(String value1, String value2) {
            addCriterion("ACTUAL_CONTROLLER not between", value1, value2, "actualController");
            return (Criteria) this;
        }

        public Criteria andCompanyeNameIsNull() {
            addCriterion("COMPANYE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCompanyeNameIsNotNull() {
            addCriterion("COMPANYE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyeNameEqualTo(String value) {
            addCriterion("COMPANYE_NAME =", value, "companyeName");
            return (Criteria) this;
        }

        public Criteria andCompanyeNameNotEqualTo(String value) {
            addCriterion("COMPANYE_NAME <>", value, "companyeName");
            return (Criteria) this;
        }

        public Criteria andCompanyeNameGreaterThan(String value) {
            addCriterion("COMPANYE_NAME >", value, "companyeName");
            return (Criteria) this;
        }

        public Criteria andCompanyeNameGreaterThanOrEqualTo(String value) {
            addCriterion("COMPANYE_NAME >=", value, "companyeName");
            return (Criteria) this;
        }

        public Criteria andCompanyeNameLessThan(String value) {
            addCriterion("COMPANYE_NAME <", value, "companyeName");
            return (Criteria) this;
        }

        public Criteria andCompanyeNameLessThanOrEqualTo(String value) {
            addCriterion("COMPANYE_NAME <=", value, "companyeName");
            return (Criteria) this;
        }

        public Criteria andCompanyeNameLike(String value) {
            addCriterion("COMPANYE_NAME like", value, "companyeName");
            return (Criteria) this;
        }

        public Criteria andCompanyeNameNotLike(String value) {
            addCriterion("COMPANYE_NAME not like", value, "companyeName");
            return (Criteria) this;
        }

        public Criteria andCompanyeNameIn(List<String> values) {
            addCriterion("COMPANYE_NAME in", values, "companyeName");
            return (Criteria) this;
        }

        public Criteria andCompanyeNameNotIn(List<String> values) {
            addCriterion("COMPANYE_NAME not in", values, "companyeName");
            return (Criteria) this;
        }

        public Criteria andCompanyeNameBetween(String value1, String value2) {
            addCriterion("COMPANYE_NAME between", value1, value2, "companyeName");
            return (Criteria) this;
        }

        public Criteria andCompanyeNameNotBetween(String value1, String value2) {
            addCriterion("COMPANYE_NAME not between", value1, value2, "companyeName");
            return (Criteria) this;
        }

        public Criteria andCredentialNoIsNull() {
            addCriterion("CREDENTIAL_NO is null");
            return (Criteria) this;
        }

        public Criteria andCredentialNoIsNotNull() {
            addCriterion("CREDENTIAL_NO is not null");
            return (Criteria) this;
        }

        public Criteria andCredentialNoEqualTo(String value) {
            addCriterion("CREDENTIAL_NO =", value, "credentialNo");
            return (Criteria) this;
        }

        public Criteria andCredentialNoNotEqualTo(String value) {
            addCriterion("CREDENTIAL_NO <>", value, "credentialNo");
            return (Criteria) this;
        }

        public Criteria andCredentialNoGreaterThan(String value) {
            addCriterion("CREDENTIAL_NO >", value, "credentialNo");
            return (Criteria) this;
        }

        public Criteria andCredentialNoGreaterThanOrEqualTo(String value) {
            addCriterion("CREDENTIAL_NO >=", value, "credentialNo");
            return (Criteria) this;
        }

        public Criteria andCredentialNoLessThan(String value) {
            addCriterion("CREDENTIAL_NO <", value, "credentialNo");
            return (Criteria) this;
        }

        public Criteria andCredentialNoLessThanOrEqualTo(String value) {
            addCriterion("CREDENTIAL_NO <=", value, "credentialNo");
            return (Criteria) this;
        }

        public Criteria andCredentialNoLike(String value) {
            addCriterion("CREDENTIAL_NO like", value, "credentialNo");
            return (Criteria) this;
        }

        public Criteria andCredentialNoNotLike(String value) {
            addCriterion("CREDENTIAL_NO not like", value, "credentialNo");
            return (Criteria) this;
        }

        public Criteria andCredentialNoIn(List<String> values) {
            addCriterion("CREDENTIAL_NO in", values, "credentialNo");
            return (Criteria) this;
        }

        public Criteria andCredentialNoNotIn(List<String> values) {
            addCriterion("CREDENTIAL_NO not in", values, "credentialNo");
            return (Criteria) this;
        }

        public Criteria andCredentialNoBetween(String value1, String value2) {
            addCriterion("CREDENTIAL_NO between", value1, value2, "credentialNo");
            return (Criteria) this;
        }

        public Criteria andCredentialNoNotBetween(String value1, String value2) {
            addCriterion("CREDENTIAL_NO not between", value1, value2, "credentialNo");
            return (Criteria) this;
        }

        public Criteria andTaxNoIsNull() {
            addCriterion("TAX_NO is null");
            return (Criteria) this;
        }

        public Criteria andTaxNoIsNotNull() {
            addCriterion("TAX_NO is not null");
            return (Criteria) this;
        }

        public Criteria andTaxNoEqualTo(String value) {
            addCriterion("TAX_NO =", value, "taxNo");
            return (Criteria) this;
        }

        public Criteria andTaxNoNotEqualTo(String value) {
            addCriterion("TAX_NO <>", value, "taxNo");
            return (Criteria) this;
        }

        public Criteria andTaxNoGreaterThan(String value) {
            addCriterion("TAX_NO >", value, "taxNo");
            return (Criteria) this;
        }

        public Criteria andTaxNoGreaterThanOrEqualTo(String value) {
            addCriterion("TAX_NO >=", value, "taxNo");
            return (Criteria) this;
        }

        public Criteria andTaxNoLessThan(String value) {
            addCriterion("TAX_NO <", value, "taxNo");
            return (Criteria) this;
        }

        public Criteria andTaxNoLessThanOrEqualTo(String value) {
            addCriterion("TAX_NO <=", value, "taxNo");
            return (Criteria) this;
        }

        public Criteria andTaxNoLike(String value) {
            addCriterion("TAX_NO like", value, "taxNo");
            return (Criteria) this;
        }

        public Criteria andTaxNoNotLike(String value) {
            addCriterion("TAX_NO not like", value, "taxNo");
            return (Criteria) this;
        }

        public Criteria andTaxNoIn(List<String> values) {
            addCriterion("TAX_NO in", values, "taxNo");
            return (Criteria) this;
        }

        public Criteria andTaxNoNotIn(List<String> values) {
            addCriterion("TAX_NO not in", values, "taxNo");
            return (Criteria) this;
        }

        public Criteria andTaxNoBetween(String value1, String value2) {
            addCriterion("TAX_NO between", value1, value2, "taxNo");
            return (Criteria) this;
        }

        public Criteria andTaxNoNotBetween(String value1, String value2) {
            addCriterion("TAX_NO not between", value1, value2, "taxNo");
            return (Criteria) this;
        }

        public Criteria andLoancardNoIsNull() {
            addCriterion("LOANCARD_NO is null");
            return (Criteria) this;
        }

        public Criteria andLoancardNoIsNotNull() {
            addCriterion("LOANCARD_NO is not null");
            return (Criteria) this;
        }

        public Criteria andLoancardNoEqualTo(String value) {
            addCriterion("LOANCARD_NO =", value, "loancardNo");
            return (Criteria) this;
        }

        public Criteria andLoancardNoNotEqualTo(String value) {
            addCriterion("LOANCARD_NO <>", value, "loancardNo");
            return (Criteria) this;
        }

        public Criteria andLoancardNoGreaterThan(String value) {
            addCriterion("LOANCARD_NO >", value, "loancardNo");
            return (Criteria) this;
        }

        public Criteria andLoancardNoGreaterThanOrEqualTo(String value) {
            addCriterion("LOANCARD_NO >=", value, "loancardNo");
            return (Criteria) this;
        }

        public Criteria andLoancardNoLessThan(String value) {
            addCriterion("LOANCARD_NO <", value, "loancardNo");
            return (Criteria) this;
        }

        public Criteria andLoancardNoLessThanOrEqualTo(String value) {
            addCriterion("LOANCARD_NO <=", value, "loancardNo");
            return (Criteria) this;
        }

        public Criteria andLoancardNoLike(String value) {
            addCriterion("LOANCARD_NO like", value, "loancardNo");
            return (Criteria) this;
        }

        public Criteria andLoancardNoNotLike(String value) {
            addCriterion("LOANCARD_NO not like", value, "loancardNo");
            return (Criteria) this;
        }

        public Criteria andLoancardNoIn(List<String> values) {
            addCriterion("LOANCARD_NO in", values, "loancardNo");
            return (Criteria) this;
        }

        public Criteria andLoancardNoNotIn(List<String> values) {
            addCriterion("LOANCARD_NO not in", values, "loancardNo");
            return (Criteria) this;
        }

        public Criteria andLoancardNoBetween(String value1, String value2) {
            addCriterion("LOANCARD_NO between", value1, value2, "loancardNo");
            return (Criteria) this;
        }

        public Criteria andLoancardNoNotBetween(String value1, String value2) {
            addCriterion("LOANCARD_NO not between", value1, value2, "loancardNo");
            return (Criteria) this;
        }

        public Criteria andCustomerOrgIsNull() {
            addCriterion("CUSTOMER_ORG is null");
            return (Criteria) this;
        }

        public Criteria andCustomerOrgIsNotNull() {
            addCriterion("CUSTOMER_ORG is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerOrgEqualTo(String value) {
            addCriterion("CUSTOMER_ORG =", value, "customerOrg");
            return (Criteria) this;
        }

        public Criteria andCustomerOrgNotEqualTo(String value) {
            addCriterion("CUSTOMER_ORG <>", value, "customerOrg");
            return (Criteria) this;
        }

        public Criteria andCustomerOrgGreaterThan(String value) {
            addCriterion("CUSTOMER_ORG >", value, "customerOrg");
            return (Criteria) this;
        }

        public Criteria andCustomerOrgGreaterThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_ORG >=", value, "customerOrg");
            return (Criteria) this;
        }

        public Criteria andCustomerOrgLessThan(String value) {
            addCriterion("CUSTOMER_ORG <", value, "customerOrg");
            return (Criteria) this;
        }

        public Criteria andCustomerOrgLessThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_ORG <=", value, "customerOrg");
            return (Criteria) this;
        }

        public Criteria andCustomerOrgLike(String value) {
            addCriterion("CUSTOMER_ORG like", value, "customerOrg");
            return (Criteria) this;
        }

        public Criteria andCustomerOrgNotLike(String value) {
            addCriterion("CUSTOMER_ORG not like", value, "customerOrg");
            return (Criteria) this;
        }

        public Criteria andCustomerOrgIn(List<String> values) {
            addCriterion("CUSTOMER_ORG in", values, "customerOrg");
            return (Criteria) this;
        }

        public Criteria andCustomerOrgNotIn(List<String> values) {
            addCriterion("CUSTOMER_ORG not in", values, "customerOrg");
            return (Criteria) this;
        }

        public Criteria andCustomerOrgBetween(String value1, String value2) {
            addCriterion("CUSTOMER_ORG between", value1, value2, "customerOrg");
            return (Criteria) this;
        }

        public Criteria andCustomerOrgNotBetween(String value1, String value2) {
            addCriterion("CUSTOMER_ORG not between", value1, value2, "customerOrg");
            return (Criteria) this;
        }

        public Criteria andCustomerDeptIsNull() {
            addCriterion("CUSTOMER_DEPT is null");
            return (Criteria) this;
        }

        public Criteria andCustomerDeptIsNotNull() {
            addCriterion("CUSTOMER_DEPT is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerDeptEqualTo(String value) {
            addCriterion("CUSTOMER_DEPT =", value, "customerDept");
            return (Criteria) this;
        }

        public Criteria andCustomerDeptNotEqualTo(String value) {
            addCriterion("CUSTOMER_DEPT <>", value, "customerDept");
            return (Criteria) this;
        }

        public Criteria andCustomerDeptGreaterThan(String value) {
            addCriterion("CUSTOMER_DEPT >", value, "customerDept");
            return (Criteria) this;
        }

        public Criteria andCustomerDeptGreaterThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_DEPT >=", value, "customerDept");
            return (Criteria) this;
        }

        public Criteria andCustomerDeptLessThan(String value) {
            addCriterion("CUSTOMER_DEPT <", value, "customerDept");
            return (Criteria) this;
        }

        public Criteria andCustomerDeptLessThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_DEPT <=", value, "customerDept");
            return (Criteria) this;
        }

        public Criteria andCustomerDeptLike(String value) {
            addCriterion("CUSTOMER_DEPT like", value, "customerDept");
            return (Criteria) this;
        }

        public Criteria andCustomerDeptNotLike(String value) {
            addCriterion("CUSTOMER_DEPT not like", value, "customerDept");
            return (Criteria) this;
        }

        public Criteria andCustomerDeptIn(List<String> values) {
            addCriterion("CUSTOMER_DEPT in", values, "customerDept");
            return (Criteria) this;
        }

        public Criteria andCustomerDeptNotIn(List<String> values) {
            addCriterion("CUSTOMER_DEPT not in", values, "customerDept");
            return (Criteria) this;
        }

        public Criteria andCustomerDeptBetween(String value1, String value2) {
            addCriterion("CUSTOMER_DEPT between", value1, value2, "customerDept");
            return (Criteria) this;
        }

        public Criteria andCustomerDeptNotBetween(String value1, String value2) {
            addCriterion("CUSTOMER_DEPT not between", value1, value2, "customerDept");
            return (Criteria) this;
        }

        public Criteria andCreaterIsNull() {
            addCriterion("CREATER is null");
            return (Criteria) this;
        }

        public Criteria andCreaterIsNotNull() {
            addCriterion("CREATER is not null");
            return (Criteria) this;
        }

        public Criteria andCreaterEqualTo(String value) {
            addCriterion("CREATER =", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotEqualTo(String value) {
            addCriterion("CREATER <>", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterGreaterThan(String value) {
            addCriterion("CREATER >", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterGreaterThanOrEqualTo(String value) {
            addCriterion("CREATER >=", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLessThan(String value) {
            addCriterion("CREATER <", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLessThanOrEqualTo(String value) {
            addCriterion("CREATER <=", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLike(String value) {
            addCriterion("CREATER like", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotLike(String value) {
            addCriterion("CREATER not like", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterIn(List<String> values) {
            addCriterion("CREATER in", values, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotIn(List<String> values) {
            addCriterion("CREATER not in", values, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterBetween(String value1, String value2) {
            addCriterion("CREATER between", value1, value2, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotBetween(String value1, String value2) {
            addCriterion("CREATER not between", value1, value2, "creater");
            return (Criteria) this;
        }

        public Criteria andCteateTimeIsNull() {
            addCriterion("CTEATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCteateTimeIsNotNull() {
            addCriterion("CTEATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCteateTimeEqualTo(Date value) {
            addCriterion("CTEATE_TIME =", value, "cteateTime");
            return (Criteria) this;
        }

        public Criteria andCteateTimeNotEqualTo(Date value) {
            addCriterion("CTEATE_TIME <>", value, "cteateTime");
            return (Criteria) this;
        }

        public Criteria andCteateTimeGreaterThan(Date value) {
            addCriterion("CTEATE_TIME >", value, "cteateTime");
            return (Criteria) this;
        }

        public Criteria andCteateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CTEATE_TIME >=", value, "cteateTime");
            return (Criteria) this;
        }

        public Criteria andCteateTimeLessThan(Date value) {
            addCriterion("CTEATE_TIME <", value, "cteateTime");
            return (Criteria) this;
        }

        public Criteria andCteateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CTEATE_TIME <=", value, "cteateTime");
            return (Criteria) this;
        }

        public Criteria andCteateTimeIn(List<Date> values) {
            addCriterion("CTEATE_TIME in", values, "cteateTime");
            return (Criteria) this;
        }

        public Criteria andCteateTimeNotIn(List<Date> values) {
            addCriterion("CTEATE_TIME not in", values, "cteateTime");
            return (Criteria) this;
        }

        public Criteria andCteateTimeBetween(Date value1, Date value2) {
            addCriterion("CTEATE_TIME between", value1, value2, "cteateTime");
            return (Criteria) this;
        }

        public Criteria andCteateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CTEATE_TIME not between", value1, value2, "cteateTime");
            return (Criteria) this;
        }

        public Criteria andUpdaterIsNull() {
            addCriterion("UPDATER is null");
            return (Criteria) this;
        }

        public Criteria andUpdaterIsNotNull() {
            addCriterion("UPDATER is not null");
            return (Criteria) this;
        }

        public Criteria andUpdaterEqualTo(String value) {
            addCriterion("UPDATER =", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotEqualTo(String value) {
            addCriterion("UPDATER <>", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterGreaterThan(String value) {
            addCriterion("UPDATER >", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATER >=", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterLessThan(String value) {
            addCriterion("UPDATER <", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterLessThanOrEqualTo(String value) {
            addCriterion("UPDATER <=", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterLike(String value) {
            addCriterion("UPDATER like", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotLike(String value) {
            addCriterion("UPDATER not like", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterIn(List<String> values) {
            addCriterion("UPDATER in", values, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotIn(List<String> values) {
            addCriterion("UPDATER not in", values, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterBetween(String value1, String value2) {
            addCriterion("UPDATER between", value1, value2, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotBetween(String value1, String value2) {
            addCriterion("UPDATER not between", value1, value2, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCustomerPeIsNull() {
            addCriterion("CUSTOMER_PE is null");
            return (Criteria) this;
        }

        public Criteria andCustomerPeIsNotNull() {
            addCriterion("CUSTOMER_PE is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerPeEqualTo(String value) {
            addCriterion("CUSTOMER_PE =", value, "customerPe");
            return (Criteria) this;
        }

        public Criteria andCustomerPeNotEqualTo(String value) {
            addCriterion("CUSTOMER_PE <>", value, "customerPe");
            return (Criteria) this;
        }

        public Criteria andCustomerPeGreaterThan(String value) {
            addCriterion("CUSTOMER_PE >", value, "customerPe");
            return (Criteria) this;
        }

        public Criteria andCustomerPeGreaterThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_PE >=", value, "customerPe");
            return (Criteria) this;
        }

        public Criteria andCustomerPeLessThan(String value) {
            addCriterion("CUSTOMER_PE <", value, "customerPe");
            return (Criteria) this;
        }

        public Criteria andCustomerPeLessThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_PE <=", value, "customerPe");
            return (Criteria) this;
        }

        public Criteria andCustomerPeLike(String value) {
            addCriterion("CUSTOMER_PE like", value, "customerPe");
            return (Criteria) this;
        }

        public Criteria andCustomerPeNotLike(String value) {
            addCriterion("CUSTOMER_PE not like", value, "customerPe");
            return (Criteria) this;
        }

        public Criteria andCustomerPeIn(List<String> values) {
            addCriterion("CUSTOMER_PE in", values, "customerPe");
            return (Criteria) this;
        }

        public Criteria andCustomerPeNotIn(List<String> values) {
            addCriterion("CUSTOMER_PE not in", values, "customerPe");
            return (Criteria) this;
        }

        public Criteria andCustomerPeBetween(String value1, String value2) {
            addCriterion("CUSTOMER_PE between", value1, value2, "customerPe");
            return (Criteria) this;
        }

        public Criteria andCustomerPeNotBetween(String value1, String value2) {
            addCriterion("CUSTOMER_PE not between", value1, value2, "customerPe");
            return (Criteria) this;
        }

        public Criteria andIfInvestIsNull() {
            addCriterion("IF_INVEST is null");
            return (Criteria) this;
        }

        public Criteria andIfInvestIsNotNull() {
            addCriterion("IF_INVEST is not null");
            return (Criteria) this;
        }

        public Criteria andIfInvestEqualTo(String value) {
            addCriterion("IF_INVEST =", value, "ifInvest");
            return (Criteria) this;
        }

        public Criteria andIfInvestNotEqualTo(String value) {
            addCriterion("IF_INVEST <>", value, "ifInvest");
            return (Criteria) this;
        }

        public Criteria andIfInvestGreaterThan(String value) {
            addCriterion("IF_INVEST >", value, "ifInvest");
            return (Criteria) this;
        }

        public Criteria andIfInvestGreaterThanOrEqualTo(String value) {
            addCriterion("IF_INVEST >=", value, "ifInvest");
            return (Criteria) this;
        }

        public Criteria andIfInvestLessThan(String value) {
            addCriterion("IF_INVEST <", value, "ifInvest");
            return (Criteria) this;
        }

        public Criteria andIfInvestLessThanOrEqualTo(String value) {
            addCriterion("IF_INVEST <=", value, "ifInvest");
            return (Criteria) this;
        }

        public Criteria andIfInvestLike(String value) {
            addCriterion("IF_INVEST like", value, "ifInvest");
            return (Criteria) this;
        }

        public Criteria andIfInvestNotLike(String value) {
            addCriterion("IF_INVEST not like", value, "ifInvest");
            return (Criteria) this;
        }

        public Criteria andIfInvestIn(List<String> values) {
            addCriterion("IF_INVEST in", values, "ifInvest");
            return (Criteria) this;
        }

        public Criteria andIfInvestNotIn(List<String> values) {
            addCriterion("IF_INVEST not in", values, "ifInvest");
            return (Criteria) this;
        }

        public Criteria andIfInvestBetween(String value1, String value2) {
            addCriterion("IF_INVEST between", value1, value2, "ifInvest");
            return (Criteria) this;
        }

        public Criteria andIfInvestNotBetween(String value1, String value2) {
            addCriterion("IF_INVEST not between", value1, value2, "ifInvest");
            return (Criteria) this;
        }

        public Criteria andIfHarmfulIsNull() {
            addCriterion("IF_HARMFUL is null");
            return (Criteria) this;
        }

        public Criteria andIfHarmfulIsNotNull() {
            addCriterion("IF_HARMFUL is not null");
            return (Criteria) this;
        }

        public Criteria andIfHarmfulEqualTo(String value) {
            addCriterion("IF_HARMFUL =", value, "ifHarmful");
            return (Criteria) this;
        }

        public Criteria andIfHarmfulNotEqualTo(String value) {
            addCriterion("IF_HARMFUL <>", value, "ifHarmful");
            return (Criteria) this;
        }

        public Criteria andIfHarmfulGreaterThan(String value) {
            addCriterion("IF_HARMFUL >", value, "ifHarmful");
            return (Criteria) this;
        }

        public Criteria andIfHarmfulGreaterThanOrEqualTo(String value) {
            addCriterion("IF_HARMFUL >=", value, "ifHarmful");
            return (Criteria) this;
        }

        public Criteria andIfHarmfulLessThan(String value) {
            addCriterion("IF_HARMFUL <", value, "ifHarmful");
            return (Criteria) this;
        }

        public Criteria andIfHarmfulLessThanOrEqualTo(String value) {
            addCriterion("IF_HARMFUL <=", value, "ifHarmful");
            return (Criteria) this;
        }

        public Criteria andIfHarmfulLike(String value) {
            addCriterion("IF_HARMFUL like", value, "ifHarmful");
            return (Criteria) this;
        }

        public Criteria andIfHarmfulNotLike(String value) {
            addCriterion("IF_HARMFUL not like", value, "ifHarmful");
            return (Criteria) this;
        }

        public Criteria andIfHarmfulIn(List<String> values) {
            addCriterion("IF_HARMFUL in", values, "ifHarmful");
            return (Criteria) this;
        }

        public Criteria andIfHarmfulNotIn(List<String> values) {
            addCriterion("IF_HARMFUL not in", values, "ifHarmful");
            return (Criteria) this;
        }

        public Criteria andIfHarmfulBetween(String value1, String value2) {
            addCriterion("IF_HARMFUL between", value1, value2, "ifHarmful");
            return (Criteria) this;
        }

        public Criteria andIfHarmfulNotBetween(String value1, String value2) {
            addCriterion("IF_HARMFUL not between", value1, value2, "ifHarmful");
            return (Criteria) this;
        }

        public Criteria andCustomerCompanyIsNull() {
            addCriterion("CUSTOMER_COMPANY is null");
            return (Criteria) this;
        }

        public Criteria andCustomerCompanyIsNotNull() {
            addCriterion("CUSTOMER_COMPANY is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerCompanyEqualTo(String value) {
            addCriterion("CUSTOMER_COMPANY =", value, "customerCompany");
            return (Criteria) this;
        }

        public Criteria andCustomerCompanyNotEqualTo(String value) {
            addCriterion("CUSTOMER_COMPANY <>", value, "customerCompany");
            return (Criteria) this;
        }

        public Criteria andCustomerCompanyGreaterThan(String value) {
            addCriterion("CUSTOMER_COMPANY >", value, "customerCompany");
            return (Criteria) this;
        }

        public Criteria andCustomerCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_COMPANY >=", value, "customerCompany");
            return (Criteria) this;
        }

        public Criteria andCustomerCompanyLessThan(String value) {
            addCriterion("CUSTOMER_COMPANY <", value, "customerCompany");
            return (Criteria) this;
        }

        public Criteria andCustomerCompanyLessThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_COMPANY <=", value, "customerCompany");
            return (Criteria) this;
        }

        public Criteria andCustomerCompanyLike(String value) {
            addCriterion("CUSTOMER_COMPANY like", value, "customerCompany");
            return (Criteria) this;
        }

        public Criteria andCustomerCompanyNotLike(String value) {
            addCriterion("CUSTOMER_COMPANY not like", value, "customerCompany");
            return (Criteria) this;
        }

        public Criteria andCustomerCompanyIn(List<String> values) {
            addCriterion("CUSTOMER_COMPANY in", values, "customerCompany");
            return (Criteria) this;
        }

        public Criteria andCustomerCompanyNotIn(List<String> values) {
            addCriterion("CUSTOMER_COMPANY not in", values, "customerCompany");
            return (Criteria) this;
        }

        public Criteria andCustomerCompanyBetween(String value1, String value2) {
            addCriterion("CUSTOMER_COMPANY between", value1, value2, "customerCompany");
            return (Criteria) this;
        }

        public Criteria andCustomerCompanyNotBetween(String value1, String value2) {
            addCriterion("CUSTOMER_COMPANY not between", value1, value2, "customerCompany");
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