package com.byj.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TProjectDetail {
    private List<TProjectTflow> tflows;

    public List<TProjectTflow> getTflows() {
        return tflows;
    }

    public void setTflows(List<TProjectTflow> tflows) {
        this.tflows = tflows;
    }

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_PROJECT_DETAIL.ID
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    private BigDecimal id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_PROJECT_DETAIL.PROJECT_NO
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    private String projectNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_PROJECT_DETAIL.WORKPIECE_NO
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    private String workpieceNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_PROJECT_DETAIL.CUSTOMER_NO
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    private String customerNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_PROJECT_DETAIL.UNIT
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    private String unit;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_PROJECT_DETAIL.BEGIN_DATE
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    private Date beginDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_PROJECT_DETAIL.END_DATE
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    private Date endDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_PROJECT_DETAIL.UNIT_PRICE
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    private BigDecimal unitPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_PROJECT_DETAIL.QUANTITY
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    private BigDecimal quantity;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_PROJECT_DETAIL.TOTAL_PRICE
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    private BigDecimal totalPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_PROJECT_DETAIL.END_QUANTITY
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    private BigDecimal endQuantity;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_PROJECT_DETAIL.PICKUP_QUANTITY
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    private BigDecimal pickupQuantity;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_PROJECT_DETAIL.PROCEEDS_STATUS
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    private String proceedsStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_PROJECT_DETAIL.ACT_AMT
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    private BigDecimal actAmt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_PROJECT_DETAIL.IS_VALID
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    private String isValid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_PROJECT_DETAIL.STATUS
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_PROJECT_DETAIL.APPLY_OPR
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    private String applyOpr;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_PROJECT_DETAIL.APPLY_DATE
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    private Date applyDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_PROJECT_DETAIL.REMARK
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_PROJECT_DETAIL.PROCESS_TYPE
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    private String processType;

    private int totalCount;

    private int finishCount;

    private int pickupCount;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getFinishCount() {
        return finishCount;
    }

    public void setFinishCount(int finishCount) {
        this.finishCount = finishCount;
    }

    public int getPickupCount() {
        return pickupCount;
    }

    public void setPickupCount(int pickupCount) {
        this.pickupCount = pickupCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_PROJECT_DETAIL.ID
     *
     * @return the value of T_PROJECT_DETAIL.ID
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public BigDecimal getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_PROJECT_DETAIL.ID
     *
     * @param id the value for T_PROJECT_DETAIL.ID
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public void setId(BigDecimal id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_PROJECT_DETAIL.PROJECT_NO
     *
     * @return the value of T_PROJECT_DETAIL.PROJECT_NO
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public String getProjectNo() {
        return projectNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_PROJECT_DETAIL.PROJECT_NO
     *
     * @param projectNo the value for T_PROJECT_DETAIL.PROJECT_NO
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo == null ? null : projectNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_PROJECT_DETAIL.WORKPIECE_NO
     *
     * @return the value of T_PROJECT_DETAIL.WORKPIECE_NO
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public String getWorkpieceNo() {
        return workpieceNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_PROJECT_DETAIL.WORKPIECE_NO
     *
     * @param workpieceNo the value for T_PROJECT_DETAIL.WORKPIECE_NO
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public void setWorkpieceNo(String workpieceNo) {
        this.workpieceNo = workpieceNo == null ? null : workpieceNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_PROJECT_DETAIL.CUSTOMER_NO
     *
     * @return the value of T_PROJECT_DETAIL.CUSTOMER_NO
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public String getCustomerNo() {
        return customerNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_PROJECT_DETAIL.CUSTOMER_NO
     *
     * @param customerNo the value for T_PROJECT_DETAIL.CUSTOMER_NO
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo == null ? null : customerNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_PROJECT_DETAIL.UNIT
     *
     * @return the value of T_PROJECT_DETAIL.UNIT
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public String getUnit() {
        return unit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_PROJECT_DETAIL.UNIT
     *
     * @param unit the value for T_PROJECT_DETAIL.UNIT
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_PROJECT_DETAIL.BEGIN_DATE
     *
     * @return the value of T_PROJECT_DETAIL.BEGIN_DATE
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_PROJECT_DETAIL.BEGIN_DATE
     *
     * @param beginDate the value for T_PROJECT_DETAIL.BEGIN_DATE
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_PROJECT_DETAIL.END_DATE
     *
     * @return the value of T_PROJECT_DETAIL.END_DATE
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_PROJECT_DETAIL.END_DATE
     *
     * @param endDate the value for T_PROJECT_DETAIL.END_DATE
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_PROJECT_DETAIL.UNIT_PRICE
     *
     * @return the value of T_PROJECT_DETAIL.UNIT_PRICE
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_PROJECT_DETAIL.UNIT_PRICE
     *
     * @param unitPrice the value for T_PROJECT_DETAIL.UNIT_PRICE
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_PROJECT_DETAIL.QUANTITY
     *
     * @return the value of T_PROJECT_DETAIL.QUANTITY
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public BigDecimal getQuantity() {
        return quantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_PROJECT_DETAIL.QUANTITY
     *
     * @param quantity the value for T_PROJECT_DETAIL.QUANTITY
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_PROJECT_DETAIL.TOTAL_PRICE
     *
     * @return the value of T_PROJECT_DETAIL.TOTAL_PRICE
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_PROJECT_DETAIL.TOTAL_PRICE
     *
     * @param totalPrice the value for T_PROJECT_DETAIL.TOTAL_PRICE
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_PROJECT_DETAIL.END_QUANTITY
     *
     * @return the value of T_PROJECT_DETAIL.END_QUANTITY
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public BigDecimal getEndQuantity() {
        return endQuantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_PROJECT_DETAIL.END_QUANTITY
     *
     * @param endQuantity the value for T_PROJECT_DETAIL.END_QUANTITY
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public void setEndQuantity(BigDecimal endQuantity) {
        this.endQuantity = endQuantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_PROJECT_DETAIL.PICKUP_QUANTITY
     *
     * @return the value of T_PROJECT_DETAIL.PICKUP_QUANTITY
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public BigDecimal getPickupQuantity() {
        return pickupQuantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_PROJECT_DETAIL.PICKUP_QUANTITY
     *
     * @param pickupQuantity the value for T_PROJECT_DETAIL.PICKUP_QUANTITY
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public void setPickupQuantity(BigDecimal pickupQuantity) {
        this.pickupQuantity = pickupQuantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_PROJECT_DETAIL.PROCEEDS_STATUS
     *
     * @return the value of T_PROJECT_DETAIL.PROCEEDS_STATUS
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public String getProceedsStatus() {
        return proceedsStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_PROJECT_DETAIL.PROCEEDS_STATUS
     *
     * @param proceedsStatus the value for T_PROJECT_DETAIL.PROCEEDS_STATUS
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public void setProceedsStatus(String proceedsStatus) {
        this.proceedsStatus = proceedsStatus == null ? null : proceedsStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_PROJECT_DETAIL.ACT_AMT
     *
     * @return the value of T_PROJECT_DETAIL.ACT_AMT
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public BigDecimal getActAmt() {
        return actAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_PROJECT_DETAIL.ACT_AMT
     *
     * @param actAmt the value for T_PROJECT_DETAIL.ACT_AMT
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public void setActAmt(BigDecimal actAmt) {
        this.actAmt = actAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_PROJECT_DETAIL.IS_VALID
     *
     * @return the value of T_PROJECT_DETAIL.IS_VALID
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public String getIsValid() {
        return isValid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_PROJECT_DETAIL.IS_VALID
     *
     * @param isValid the value for T_PROJECT_DETAIL.IS_VALID
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_PROJECT_DETAIL.STATUS
     *
     * @return the value of T_PROJECT_DETAIL.STATUS
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_PROJECT_DETAIL.STATUS
     *
     * @param status the value for T_PROJECT_DETAIL.STATUS
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_PROJECT_DETAIL.APPLY_OPR
     *
     * @return the value of T_PROJECT_DETAIL.APPLY_OPR
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public String getApplyOpr() {
        return applyOpr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_PROJECT_DETAIL.APPLY_OPR
     *
     * @param applyOpr the value for T_PROJECT_DETAIL.APPLY_OPR
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public void setApplyOpr(String applyOpr) {
        this.applyOpr = applyOpr == null ? null : applyOpr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_PROJECT_DETAIL.APPLY_DATE
     *
     * @return the value of T_PROJECT_DETAIL.APPLY_DATE
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public Date getApplyDate() {
        return applyDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_PROJECT_DETAIL.APPLY_DATE
     *
     * @param applyDate the value for T_PROJECT_DETAIL.APPLY_DATE
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_PROJECT_DETAIL.REMARK
     *
     * @return the value of T_PROJECT_DETAIL.REMARK
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_PROJECT_DETAIL.REMARK
     *
     * @param remark the value for T_PROJECT_DETAIL.REMARK
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_PROJECT_DETAIL.PROCESS_TYPE
     *
     * @return the value of T_PROJECT_DETAIL.PROCESS_TYPE
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public String getProcessType() {
        return processType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_PROJECT_DETAIL.PROCESS_TYPE
     *
     * @param processType the value for T_PROJECT_DETAIL.PROCESS_TYPE
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    public void setProcessType(String processType) {
        this.processType = processType == null ? null : processType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_PROJECT_DETAIL
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", projectNo=").append(projectNo);
        sb.append(", workpieceNo=").append(workpieceNo);
        sb.append(", customerNo=").append(customerNo);
        sb.append(", unit=").append(unit);
        sb.append(", beginDate=").append(beginDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", unitPrice=").append(unitPrice);
        sb.append(", quantity=").append(quantity);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", endQuantity=").append(endQuantity);
        sb.append(", pickupQuantity=").append(pickupQuantity);
        sb.append(", proceedsStatus=").append(proceedsStatus);
        sb.append(", actAmt=").append(actAmt);
        sb.append(", isValid=").append(isValid);
        sb.append(", status=").append(status);
        sb.append(", applyOpr=").append(applyOpr);
        sb.append(", applyDate=").append(applyDate);
        sb.append(", remark=").append(remark);
        sb.append(", processType=").append(processType);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_PROJECT_DETAIL
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
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
        TProjectDetail other = (TProjectDetail) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProjectNo() == null ? other.getProjectNo() == null : this.getProjectNo().equals(other.getProjectNo()))
            && (this.getWorkpieceNo() == null ? other.getWorkpieceNo() == null : this.getWorkpieceNo().equals(other.getWorkpieceNo()))
            && (this.getCustomerNo() == null ? other.getCustomerNo() == null : this.getCustomerNo().equals(other.getCustomerNo()))
            && (this.getUnit() == null ? other.getUnit() == null : this.getUnit().equals(other.getUnit()))
            && (this.getBeginDate() == null ? other.getBeginDate() == null : this.getBeginDate().equals(other.getBeginDate()))
            && (this.getEndDate() == null ? other.getEndDate() == null : this.getEndDate().equals(other.getEndDate()))
            && (this.getUnitPrice() == null ? other.getUnitPrice() == null : this.getUnitPrice().equals(other.getUnitPrice()))
            && (this.getQuantity() == null ? other.getQuantity() == null : this.getQuantity().equals(other.getQuantity()))
            && (this.getTotalPrice() == null ? other.getTotalPrice() == null : this.getTotalPrice().equals(other.getTotalPrice()))
            && (this.getEndQuantity() == null ? other.getEndQuantity() == null : this.getEndQuantity().equals(other.getEndQuantity()))
            && (this.getPickupQuantity() == null ? other.getPickupQuantity() == null : this.getPickupQuantity().equals(other.getPickupQuantity()))
            && (this.getProceedsStatus() == null ? other.getProceedsStatus() == null : this.getProceedsStatus().equals(other.getProceedsStatus()))
            && (this.getActAmt() == null ? other.getActAmt() == null : this.getActAmt().equals(other.getActAmt()))
            && (this.getIsValid() == null ? other.getIsValid() == null : this.getIsValid().equals(other.getIsValid()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getApplyOpr() == null ? other.getApplyOpr() == null : this.getApplyOpr().equals(other.getApplyOpr()))
            && (this.getApplyDate() == null ? other.getApplyDate() == null : this.getApplyDate().equals(other.getApplyDate()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getProcessType() == null ? other.getProcessType() == null : this.getProcessType().equals(other.getProcessType()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_PROJECT_DETAIL
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProjectNo() == null) ? 0 : getProjectNo().hashCode());
        result = prime * result + ((getWorkpieceNo() == null) ? 0 : getWorkpieceNo().hashCode());
        result = prime * result + ((getCustomerNo() == null) ? 0 : getCustomerNo().hashCode());
        result = prime * result + ((getUnit() == null) ? 0 : getUnit().hashCode());
        result = prime * result + ((getBeginDate() == null) ? 0 : getBeginDate().hashCode());
        result = prime * result + ((getEndDate() == null) ? 0 : getEndDate().hashCode());
        result = prime * result + ((getUnitPrice() == null) ? 0 : getUnitPrice().hashCode());
        result = prime * result + ((getQuantity() == null) ? 0 : getQuantity().hashCode());
        result = prime * result + ((getTotalPrice() == null) ? 0 : getTotalPrice().hashCode());
        result = prime * result + ((getEndQuantity() == null) ? 0 : getEndQuantity().hashCode());
        result = prime * result + ((getPickupQuantity() == null) ? 0 : getPickupQuantity().hashCode());
        result = prime * result + ((getProceedsStatus() == null) ? 0 : getProceedsStatus().hashCode());
        result = prime * result + ((getActAmt() == null) ? 0 : getActAmt().hashCode());
        result = prime * result + ((getIsValid() == null) ? 0 : getIsValid().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getApplyOpr() == null) ? 0 : getApplyOpr().hashCode());
        result = prime * result + ((getApplyDate() == null) ? 0 : getApplyDate().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getProcessType() == null) ? 0 : getProcessType().hashCode());
        return result;
    }
}