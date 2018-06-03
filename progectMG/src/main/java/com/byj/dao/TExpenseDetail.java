package com.byj.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TExpenseDetail implements Serializable{
    private BigDecimal YCquantity;

    private BigDecimal HBquantity;

    private BigDecimal YCtotalPrice;

    private BigDecimal HBtotalPrice;

    public BigDecimal getYCquantity() {
        return YCquantity;
    }

    public void setYCquantity(BigDecimal YCquantity) {
        this.YCquantity = YCquantity;
    }

    public BigDecimal getHBquantity() {
        return HBquantity;
    }

    public void setHBquantity(BigDecimal HBquantity) {
        this.HBquantity = HBquantity;
    }

    public BigDecimal getYCtotalPrice() {
        return YCtotalPrice;
    }

    public void setYCtotalPrice(BigDecimal YCtotalPrice) {
        this.YCtotalPrice = YCtotalPrice;
    }

    public BigDecimal getHBtotalPrice() {
        return HBtotalPrice;
    }

    public void setHBtotalPrice(BigDecimal HBtotalPrice) {
        this.HBtotalPrice = HBtotalPrice;
    }

    private BigDecimal id;

    private String expenseNo;

    private String expenseType;

    private String electricType;

    private String toolNo;

    private BigDecimal quantity;

    private BigDecimal unitPrice;

    private BigDecimal totalPrice;

    private Date expenseDate;

    private String isValid;

    private String status;

    private String applyOpr;

    private Date applyDate;

    private String remark;

    private BigDecimal ammeterTotal;

    private BigDecimal ammeterHb;

    private String unit;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getExpenseNo() {
        return expenseNo;
    }

    public void setExpenseNo(String expenseNo) {
        this.expenseNo = expenseNo == null ? null : expenseNo.trim();
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType == null ? null : expenseType.trim();
    }

    public String getElectricType() {
        return electricType;
    }

    public void setElectricType(String electricType) {
        this.electricType = electricType == null ? null : electricType.trim();
    }

    public String getToolNo() {
        return toolNo;
    }

    public void setToolNo(String toolNo) {
        this.toolNo = toolNo == null ? null : toolNo.trim();
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getApplyOpr() {
        return applyOpr;
    }

    public void setApplyOpr(String applyOpr) {
        this.applyOpr = applyOpr == null ? null : applyOpr.trim();
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public BigDecimal getAmmeterTotal() {
        return ammeterTotal;
    }

    public void setAmmeterTotal(BigDecimal ammeterTotal) {
        this.ammeterTotal = ammeterTotal;
    }

    public BigDecimal getAmmeterHb() {
        return ammeterHb;
    }

    public void setAmmeterHb(BigDecimal ammeterHb) {
        this.ammeterHb = ammeterHb;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", expenseNo=").append(expenseNo);
        sb.append(", expenseType=").append(expenseType);
        sb.append(", electricType=").append(electricType);
        sb.append(", toolNo=").append(toolNo);
        sb.append(", quantity=").append(quantity);
        sb.append(", unitPrice=").append(unitPrice);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", expenseDate=").append(expenseDate);
        sb.append(", isValid=").append(isValid);
        sb.append(", status=").append(status);
        sb.append(", applyOpr=").append(applyOpr);
        sb.append(", applyDate=").append(applyDate);
        sb.append(", remark=").append(remark);
        sb.append(", ammeterTotal=").append(ammeterTotal);
        sb.append(", ammeterHb=").append(ammeterHb);
        sb.append(", unit=").append(unit);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TExpenseDetail other = (TExpenseDetail) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getExpenseNo() == null ? other.getExpenseNo() == null : this.getExpenseNo().equals(other.getExpenseNo()))
            && (this.getExpenseType() == null ? other.getExpenseType() == null : this.getExpenseType().equals(other.getExpenseType()))
            && (this.getElectricType() == null ? other.getElectricType() == null : this.getElectricType().equals(other.getElectricType()))
            && (this.getToolNo() == null ? other.getToolNo() == null : this.getToolNo().equals(other.getToolNo()))
            && (this.getQuantity() == null ? other.getQuantity() == null : this.getQuantity().equals(other.getQuantity()))
            && (this.getUnitPrice() == null ? other.getUnitPrice() == null : this.getUnitPrice().equals(other.getUnitPrice()))
            && (this.getTotalPrice() == null ? other.getTotalPrice() == null : this.getTotalPrice().equals(other.getTotalPrice()))
            && (this.getExpenseDate() == null ? other.getExpenseDate() == null : this.getExpenseDate().equals(other.getExpenseDate()))
            && (this.getIsValid() == null ? other.getIsValid() == null : this.getIsValid().equals(other.getIsValid()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getApplyOpr() == null ? other.getApplyOpr() == null : this.getApplyOpr().equals(other.getApplyOpr()))
            && (this.getApplyDate() == null ? other.getApplyDate() == null : this.getApplyDate().equals(other.getApplyDate()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getAmmeterTotal() == null ? other.getAmmeterTotal() == null : this.getAmmeterTotal().equals(other.getAmmeterTotal()))
            && (this.getAmmeterHb() == null ? other.getAmmeterHb() == null : this.getAmmeterHb().equals(other.getAmmeterHb()))
            && (this.getUnit() == null ? other.getUnit() == null : this.getUnit().equals(other.getUnit()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getExpenseNo() == null) ? 0 : getExpenseNo().hashCode());
        result = prime * result + ((getExpenseType() == null) ? 0 : getExpenseType().hashCode());
        result = prime * result + ((getElectricType() == null) ? 0 : getElectricType().hashCode());
        result = prime * result + ((getToolNo() == null) ? 0 : getToolNo().hashCode());
        result = prime * result + ((getQuantity() == null) ? 0 : getQuantity().hashCode());
        result = prime * result + ((getUnitPrice() == null) ? 0 : getUnitPrice().hashCode());
        result = prime * result + ((getTotalPrice() == null) ? 0 : getTotalPrice().hashCode());
        result = prime * result + ((getExpenseDate() == null) ? 0 : getExpenseDate().hashCode());
        result = prime * result + ((getIsValid() == null) ? 0 : getIsValid().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getApplyOpr() == null) ? 0 : getApplyOpr().hashCode());
        result = prime * result + ((getApplyDate() == null) ? 0 : getApplyDate().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getAmmeterTotal() == null) ? 0 : getAmmeterTotal().hashCode());
        result = prime * result + ((getAmmeterHb() == null) ? 0 : getAmmeterHb().hashCode());
        result = prime * result + ((getUnit() == null) ? 0 : getUnit().hashCode());
        return result;
    }
}