package bas.jeda.dao;

import java.math.BigDecimal;
import java.util.Date;

public class JedaField {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JEDA_FIELD.FIELD_ID
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    private Integer fieldId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JEDA_FIELD.FUNCTION_ID
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    private String functionId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JEDA_FIELD.FIELD_CODE
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    private String fieldCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JEDA_FIELD.FIELD_NAME
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    private String fieldName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JEDA_FIELD.FIELD_TYPE
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    private String fieldType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JEDA_FIELD.FIELD_READ_ONLY
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    private BigDecimal fieldReadOnly;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JEDA_FIELD.FIELD_VERSION
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    private BigDecimal fieldVersion;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JEDA_FIELD.FIELD_ORDER
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    private BigDecimal fieldOrder;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JEDA_FIELD.FIELD_CREATOR
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    private String fieldCreator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JEDA_FIELD.FIELD_CREATED
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    private Date fieldCreated;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JEDA_FIELD.FIELD_MODIFIER
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    private String fieldModifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JEDA_FIELD.FIELD_MODIFIED
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    private Date fieldModified;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JEDA_FIELD.FIELD_ID
     *
     * @return the value of JEDA_FIELD.FIELD_ID
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public Integer getFieldId() {
        return fieldId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JEDA_FIELD.FIELD_ID
     *
     * @param fieldId the value for JEDA_FIELD.FIELD_ID
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JEDA_FIELD.FUNCTION_ID
     *
     * @return the value of JEDA_FIELD.FUNCTION_ID
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public String getFunctionId() {
        return functionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JEDA_FIELD.FUNCTION_ID
     *
     * @param functionId the value for JEDA_FIELD.FUNCTION_ID
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public void setFunctionId(String functionId) {
        this.functionId = functionId == null ? null : functionId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JEDA_FIELD.FIELD_CODE
     *
     * @return the value of JEDA_FIELD.FIELD_CODE
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public String getFieldCode() {
        return fieldCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JEDA_FIELD.FIELD_CODE
     *
     * @param fieldCode the value for JEDA_FIELD.FIELD_CODE
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode == null ? null : fieldCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JEDA_FIELD.FIELD_NAME
     *
     * @return the value of JEDA_FIELD.FIELD_NAME
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JEDA_FIELD.FIELD_NAME
     *
     * @param fieldName the value for JEDA_FIELD.FIELD_NAME
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JEDA_FIELD.FIELD_TYPE
     *
     * @return the value of JEDA_FIELD.FIELD_TYPE
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public String getFieldType() {
        return fieldType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JEDA_FIELD.FIELD_TYPE
     *
     * @param fieldType the value for JEDA_FIELD.FIELD_TYPE
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public void setFieldType(String fieldType) {
        this.fieldType = fieldType == null ? null : fieldType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JEDA_FIELD.FIELD_READ_ONLY
     *
     * @return the value of JEDA_FIELD.FIELD_READ_ONLY
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public BigDecimal getFieldReadOnly() {
        return fieldReadOnly;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JEDA_FIELD.FIELD_READ_ONLY
     *
     * @param fieldReadOnly the value for JEDA_FIELD.FIELD_READ_ONLY
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public void setFieldReadOnly(BigDecimal fieldReadOnly) {
        this.fieldReadOnly = fieldReadOnly;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JEDA_FIELD.FIELD_VERSION
     *
     * @return the value of JEDA_FIELD.FIELD_VERSION
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public BigDecimal getFieldVersion() {
        return fieldVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JEDA_FIELD.FIELD_VERSION
     *
     * @param fieldVersion the value for JEDA_FIELD.FIELD_VERSION
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public void setFieldVersion(BigDecimal fieldVersion) {
        this.fieldVersion = fieldVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JEDA_FIELD.FIELD_ORDER
     *
     * @return the value of JEDA_FIELD.FIELD_ORDER
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public BigDecimal getFieldOrder() {
        return fieldOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JEDA_FIELD.FIELD_ORDER
     *
     * @param fieldOrder the value for JEDA_FIELD.FIELD_ORDER
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public void setFieldOrder(BigDecimal fieldOrder) {
        this.fieldOrder = fieldOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JEDA_FIELD.FIELD_CREATOR
     *
     * @return the value of JEDA_FIELD.FIELD_CREATOR
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public String getFieldCreator() {
        return fieldCreator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JEDA_FIELD.FIELD_CREATOR
     *
     * @param fieldCreator the value for JEDA_FIELD.FIELD_CREATOR
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public void setFieldCreator(String fieldCreator) {
        this.fieldCreator = fieldCreator == null ? null : fieldCreator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JEDA_FIELD.FIELD_CREATED
     *
     * @return the value of JEDA_FIELD.FIELD_CREATED
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public Date getFieldCreated() {
        return fieldCreated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JEDA_FIELD.FIELD_CREATED
     *
     * @param fieldCreated the value for JEDA_FIELD.FIELD_CREATED
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public void setFieldCreated(Date fieldCreated) {
        this.fieldCreated = fieldCreated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JEDA_FIELD.FIELD_MODIFIER
     *
     * @return the value of JEDA_FIELD.FIELD_MODIFIER
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public String getFieldModifier() {
        return fieldModifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JEDA_FIELD.FIELD_MODIFIER
     *
     * @param fieldModifier the value for JEDA_FIELD.FIELD_MODIFIER
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public void setFieldModifier(String fieldModifier) {
        this.fieldModifier = fieldModifier == null ? null : fieldModifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JEDA_FIELD.FIELD_MODIFIED
     *
     * @return the value of JEDA_FIELD.FIELD_MODIFIED
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public Date getFieldModified() {
        return fieldModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JEDA_FIELD.FIELD_MODIFIED
     *
     * @param fieldModified the value for JEDA_FIELD.FIELD_MODIFIED
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public void setFieldModified(Date fieldModified) {
        this.fieldModified = fieldModified;
    }
}