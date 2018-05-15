package com.nantian.dao;

import java.util.Date;

public class JedaOrg {
    private String orgId;

    private String orgName;

    private String parentOrgId;

    private String orgDescription;

    private String orgTel;

    private String orgAddress;

    private String orgContact;

    private String orgPath;

    private String orgLevel;

    private Long orgEnabled;

    private String orgType;

    private String orgProperty;

    private Long orgOrder;

    private Long orgVersion;

    private String orgCreator;

    private Date orgCreated;

    private String orgModifier;

    private Date orgModified;

    private String province;

    private String orglname;

    private String city;

    private String post;

    private Date foundday;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getParentOrgId() {
        return parentOrgId;
    }

    public void setParentOrgId(String parentOrgId) {
        this.parentOrgId = parentOrgId == null ? null : parentOrgId.trim();
    }

    public String getOrgDescription() {
        return orgDescription;
    }

    public void setOrgDescription(String orgDescription) {
        this.orgDescription = orgDescription == null ? null : orgDescription.trim();
    }

    public String getOrgTel() {
        return orgTel;
    }

    public void setOrgTel(String orgTel) {
        this.orgTel = orgTel == null ? null : orgTel.trim();
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress == null ? null : orgAddress.trim();
    }

    public String getOrgContact() {
        return orgContact;
    }

    public void setOrgContact(String orgContact) {
        this.orgContact = orgContact == null ? null : orgContact.trim();
    }

    public String getOrgPath() {
        return orgPath;
    }

    public void setOrgPath(String orgPath) {
        this.orgPath = orgPath == null ? null : orgPath.trim();
    }

    public String getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(String orgLevel) {
        this.orgLevel = orgLevel == null ? null : orgLevel.trim();
    }

    public Long getOrgEnabled() {
        return orgEnabled;
    }

    public void setOrgEnabled(Long orgEnabled) {
        this.orgEnabled = orgEnabled;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType == null ? null : orgType.trim();
    }

    public String getOrgProperty() {
        return orgProperty;
    }

    public void setOrgProperty(String orgProperty) {
        this.orgProperty = orgProperty == null ? null : orgProperty.trim();
    }

    public Long getOrgOrder() {
        return orgOrder;
    }

    public void setOrgOrder(Long orgOrder) {
        this.orgOrder = orgOrder;
    }

    public Long getOrgVersion() {
        return orgVersion;
    }

    public void setOrgVersion(Long orgVersion) {
        this.orgVersion = orgVersion;
    }

    public String getOrgCreator() {
        return orgCreator;
    }

    public void setOrgCreator(String orgCreator) {
        this.orgCreator = orgCreator == null ? null : orgCreator.trim();
    }

    public Date getOrgCreated() {
        return orgCreated;
    }

    public void setOrgCreated(Date orgCreated) {
        this.orgCreated = orgCreated;
    }

    public String getOrgModifier() {
        return orgModifier;
    }

    public void setOrgModifier(String orgModifier) {
        this.orgModifier = orgModifier == null ? null : orgModifier.trim();
    }

    public Date getOrgModified() {
        return orgModified;
    }

    public void setOrgModified(Date orgModified) {
        this.orgModified = orgModified;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getOrglname() {
        return orglname;
    }

    public void setOrglname(String orglname) {
        this.orglname = orglname == null ? null : orglname.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post == null ? null : post.trim();
    }

    public Date getFoundday() {
        return foundday;
    }

    public void setFoundday(Date foundday) {
        this.foundday = foundday;
    }
}