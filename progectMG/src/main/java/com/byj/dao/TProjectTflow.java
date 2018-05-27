package com.byj.dao;

import java.math.BigDecimal;
import java.util.Date;

public class TProjectTflow {
    private BigDecimal id;

    private String projectNo;

    private Date beginDate;

    private Date endDate;

    private BigDecimal actQuantity;

    private BigDecimal remainQuantity;

    private BigDecimal actPickup;

    private BigDecimal remainPickup;

    private Date actDate;

    private String isValid;

    private String applyOpr;

    private Date applyDate;

    private String remark;

    private String type;

    private BigDecimal detailId;

    private String workpiece;

    private BigDecimal actAmt;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo == null ? null : projectNo.trim();
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getActQuantity() {
        return actQuantity;
    }

    public void setActQuantity(BigDecimal actQuantity) {
        this.actQuantity = actQuantity;
    }

    public BigDecimal getRemainQuantity() {
        return remainQuantity;
    }

    public void setRemainQuantity(BigDecimal remainQuantity) {
        this.remainQuantity = remainQuantity;
    }

    public BigDecimal getActPickup() {
        return actPickup;
    }

    public void setActPickup(BigDecimal actPickup) {
        this.actPickup = actPickup;
    }

    public BigDecimal getRemainPickup() {
        return remainPickup;
    }

    public void setRemainPickup(BigDecimal remainPickup) {
        this.remainPickup = remainPickup;
    }

    public Date getActDate() {
        return actDate;
    }

    public void setActDate(Date actDate) {
        this.actDate = actDate;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public BigDecimal getDetailId() {
        return detailId;
    }

    public void setDetailId(BigDecimal detailId) {
        this.detailId = detailId;
    }

    public String getWorkpiece() {
        return workpiece;
    }

    public void setWorkpiece(String workpiece) {
        this.workpiece = workpiece == null ? null : workpiece.trim();
    }

    public BigDecimal getActAmt() {
        return actAmt;
    }

    public void setActAmt(BigDecimal actAmt) {
        this.actAmt = actAmt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", projectNo=").append(projectNo);
        sb.append(", beginDate=").append(beginDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", actQuantity=").append(actQuantity);
        sb.append(", remainQuantity=").append(remainQuantity);
        sb.append(", actPickup=").append(actPickup);
        sb.append(", remainPickup=").append(remainPickup);
        sb.append(", actDate=").append(actDate);
        sb.append(", isValid=").append(isValid);
        sb.append(", applyOpr=").append(applyOpr);
        sb.append(", applyDate=").append(applyDate);
        sb.append(", remark=").append(remark);
        sb.append(", type=").append(type);
        sb.append(", detailId=").append(detailId);
        sb.append(", workpiece=").append(workpiece);
        sb.append(", actAmt=").append(actAmt);
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
        TProjectTflow other = (TProjectTflow) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProjectNo() == null ? other.getProjectNo() == null : this.getProjectNo().equals(other.getProjectNo()))
            && (this.getBeginDate() == null ? other.getBeginDate() == null : this.getBeginDate().equals(other.getBeginDate()))
            && (this.getEndDate() == null ? other.getEndDate() == null : this.getEndDate().equals(other.getEndDate()))
            && (this.getActQuantity() == null ? other.getActQuantity() == null : this.getActQuantity().equals(other.getActQuantity()))
            && (this.getRemainQuantity() == null ? other.getRemainQuantity() == null : this.getRemainQuantity().equals(other.getRemainQuantity()))
            && (this.getActPickup() == null ? other.getActPickup() == null : this.getActPickup().equals(other.getActPickup()))
            && (this.getRemainPickup() == null ? other.getRemainPickup() == null : this.getRemainPickup().equals(other.getRemainPickup()))
            && (this.getActDate() == null ? other.getActDate() == null : this.getActDate().equals(other.getActDate()))
            && (this.getIsValid() == null ? other.getIsValid() == null : this.getIsValid().equals(other.getIsValid()))
            && (this.getApplyOpr() == null ? other.getApplyOpr() == null : this.getApplyOpr().equals(other.getApplyOpr()))
            && (this.getApplyDate() == null ? other.getApplyDate() == null : this.getApplyDate().equals(other.getApplyDate()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getDetailId() == null ? other.getDetailId() == null : this.getDetailId().equals(other.getDetailId()))
            && (this.getWorkpiece() == null ? other.getWorkpiece() == null : this.getWorkpiece().equals(other.getWorkpiece()))
            && (this.getActAmt() == null ? other.getActAmt() == null : this.getActAmt().equals(other.getActAmt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProjectNo() == null) ? 0 : getProjectNo().hashCode());
        result = prime * result + ((getBeginDate() == null) ? 0 : getBeginDate().hashCode());
        result = prime * result + ((getEndDate() == null) ? 0 : getEndDate().hashCode());
        result = prime * result + ((getActQuantity() == null) ? 0 : getActQuantity().hashCode());
        result = prime * result + ((getRemainQuantity() == null) ? 0 : getRemainQuantity().hashCode());
        result = prime * result + ((getActPickup() == null) ? 0 : getActPickup().hashCode());
        result = prime * result + ((getRemainPickup() == null) ? 0 : getRemainPickup().hashCode());
        result = prime * result + ((getActDate() == null) ? 0 : getActDate().hashCode());
        result = prime * result + ((getIsValid() == null) ? 0 : getIsValid().hashCode());
        result = prime * result + ((getApplyOpr() == null) ? 0 : getApplyOpr().hashCode());
        result = prime * result + ((getApplyDate() == null) ? 0 : getApplyDate().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getDetailId() == null) ? 0 : getDetailId().hashCode());
        result = prime * result + ((getWorkpiece() == null) ? 0 : getWorkpiece().hashCode());
        result = prime * result + ((getActAmt() == null) ? 0 : getActAmt().hashCode());
        return result;
    }
}