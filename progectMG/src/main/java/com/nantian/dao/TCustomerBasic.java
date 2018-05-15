package com.nantian.dao;

import java.util.Date;

public class TCustomerBasic {
    private String customerId;

    private String customerName;

    private String certificateFlag;

    private String businessNo;

    private Date registrationDate;

    private String ifloanCard;

    private String enterpriseType;

    private String actualControllerType;

    private String actualController;

    private String companyeName;

    private String credentialNo;

    private String taxNo;

    private String loancardNo;

    private String customerOrg;

    private String customerDept;

    private String creater;

    private Date cteateTime;

    private String updater;

    private Date updateTime;

    private String customerPe;

    private String ifInvest;

    private String ifHarmful;

    private String customerCompany;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getCertificateFlag() {
        return certificateFlag;
    }

    public void setCertificateFlag(String certificateFlag) {
        this.certificateFlag = certificateFlag == null ? null : certificateFlag.trim();
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo == null ? null : businessNo.trim();
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getIfloanCard() {
        return ifloanCard;
    }

    public void setIfloanCard(String ifloanCard) {
        this.ifloanCard = ifloanCard == null ? null : ifloanCard.trim();
    }

    public String getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(String enterpriseType) {
        this.enterpriseType = enterpriseType == null ? null : enterpriseType.trim();
    }

    public String getActualControllerType() {
        return actualControllerType;
    }

    public void setActualControllerType(String actualControllerType) {
        this.actualControllerType = actualControllerType == null ? null : actualControllerType.trim();
    }

    public String getActualController() {
        return actualController;
    }

    public void setActualController(String actualController) {
        this.actualController = actualController == null ? null : actualController.trim();
    }

    public String getCompanyeName() {
        return companyeName;
    }

    public void setCompanyeName(String companyeName) {
        this.companyeName = companyeName == null ? null : companyeName.trim();
    }

    public String getCredentialNo() {
        return credentialNo;
    }

    public void setCredentialNo(String credentialNo) {
        this.credentialNo = credentialNo == null ? null : credentialNo.trim();
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo == null ? null : taxNo.trim();
    }

    public String getLoancardNo() {
        return loancardNo;
    }

    public void setLoancardNo(String loancardNo) {
        this.loancardNo = loancardNo == null ? null : loancardNo.trim();
    }

    public String getCustomerOrg() {
        return customerOrg;
    }

    public void setCustomerOrg(String customerOrg) {
        this.customerOrg = customerOrg == null ? null : customerOrg.trim();
    }

    public String getCustomerDept() {
        return customerDept;
    }

    public void setCustomerDept(String customerDept) {
        this.customerDept = customerDept == null ? null : customerDept.trim();
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public Date getCteateTime() {
        return cteateTime;
    }

    public void setCteateTime(Date cteateTime) {
        this.cteateTime = cteateTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCustomerPe() {
        return customerPe;
    }

    public void setCustomerPe(String customerPe) {
        this.customerPe = customerPe == null ? null : customerPe.trim();
    }

    public String getIfInvest() {
        return ifInvest;
    }

    public void setIfInvest(String ifInvest) {
        this.ifInvest = ifInvest == null ? null : ifInvest.trim();
    }

    public String getIfHarmful() {
        return ifHarmful;
    }

    public void setIfHarmful(String ifHarmful) {
        this.ifHarmful = ifHarmful == null ? null : ifHarmful.trim();
    }

    public String getCustomerCompany() {
        return customerCompany;
    }

    public void setCustomerCompany(String customerCompany) {
        this.customerCompany = customerCompany == null ? null : customerCompany.trim();
    }
}