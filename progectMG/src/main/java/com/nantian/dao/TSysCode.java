package com.nantian.dao;

public class TSysCode {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_SYS_CODE.ID
     *
     * @mbg.generated Fri Mar 31 17:51:43 CST 2017
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_SYS_CODE.CODE_TYPE
     *
     * @mbg.generated Fri Mar 31 17:51:43 CST 2017
     */
    private String codeType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_SYS_CODE.CODE_NAME
     *
     * @mbg.generated Fri Mar 31 17:51:43 CST 2017
     */
    private String codeName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column T_SYS_CODE.CODE_VALUE
     *
     * @mbg.generated Fri Mar 31 17:51:43 CST 2017
     */
    private String codeValue;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_SYS_CODE.ID
     *
     * @return the value of T_SYS_CODE.ID
     *
     * @mbg.generated Fri Mar 31 17:51:43 CST 2017
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_SYS_CODE.ID
     *
     * @param id the value for T_SYS_CODE.ID
     *
     * @mbg.generated Fri Mar 31 17:51:43 CST 2017
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_SYS_CODE.CODE_TYPE
     *
     * @return the value of T_SYS_CODE.CODE_TYPE
     *
     * @mbg.generated Fri Mar 31 17:51:43 CST 2017
     */
    public String getCodeType() {
        return codeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_SYS_CODE.CODE_TYPE
     *
     * @param codeType the value for T_SYS_CODE.CODE_TYPE
     *
     * @mbg.generated Fri Mar 31 17:51:43 CST 2017
     */
    public void setCodeType(String codeType) {
        this.codeType = codeType == null ? null : codeType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_SYS_CODE.CODE_NAME
     *
     * @return the value of T_SYS_CODE.CODE_NAME
     *
     * @mbg.generated Fri Mar 31 17:51:43 CST 2017
     */
    public String getCodeName() {
        return codeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_SYS_CODE.CODE_NAME
     *
     * @param codeName the value for T_SYS_CODE.CODE_NAME
     *
     * @mbg.generated Fri Mar 31 17:51:43 CST 2017
     */
    public void setCodeName(String codeName) {
        this.codeName = codeName == null ? null : codeName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_SYS_CODE.CODE_VALUE
     *
     * @return the value of T_SYS_CODE.CODE_VALUE
     *
     * @mbg.generated Fri Mar 31 17:51:43 CST 2017
     */
    public String getCodeValue() {
        return codeValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_SYS_CODE.CODE_VALUE
     *
     * @param codeValue the value for T_SYS_CODE.CODE_VALUE
     *
     * @mbg.generated Fri Mar 31 17:51:43 CST 2017
     */
    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue == null ? null : codeValue.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_CODE
     *
     * @mbg.generated Fri Mar 31 17:51:43 CST 2017
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", codeType=").append(codeType);
        sb.append(", codeName=").append(codeName);
        sb.append(", codeValue=").append(codeValue);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_CODE
     *
     * @mbg.generated Fri Mar 31 17:51:43 CST 2017
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
        TSysCode other = (TSysCode) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCodeType() == null ? other.getCodeType() == null : this.getCodeType().equals(other.getCodeType()))
            && (this.getCodeName() == null ? other.getCodeName() == null : this.getCodeName().equals(other.getCodeName()))
            && (this.getCodeValue() == null ? other.getCodeValue() == null : this.getCodeValue().equals(other.getCodeValue()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_CODE
     *
     * @mbg.generated Fri Mar 31 17:51:43 CST 2017
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCodeType() == null) ? 0 : getCodeType().hashCode());
        result = prime * result + ((getCodeName() == null) ? 0 : getCodeName().hashCode());
        result = prime * result + ((getCodeValue() == null) ? 0 : getCodeValue().hashCode());
        return result;
    }
}