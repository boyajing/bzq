package bas.jeda.dao;

import java.math.BigDecimal;
import java.util.Date;

public class JedaItem {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JEDA_ITEM.ITEM_ID
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    private String itemId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JEDA_ITEM.ITEM_NAME
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    private String itemName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JEDA_ITEM.ITEM_DESCRIPTION
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    private String itemDescription;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JEDA_ITEM.ITEM_IS_PERMISSION
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    private BigDecimal itemIsPermission;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JEDA_ITEM.ITEM_ORDER
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    private BigDecimal itemOrder;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JEDA_ITEM.ITEM_READ_ONLY
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    private BigDecimal itemReadOnly;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JEDA_ITEM.ITEM_CREATOR
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    private String itemCreator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JEDA_ITEM.ITEM_CREATED
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    private Date itemCreated;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JEDA_ITEM.ITEM_MODIFIER
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    private String itemModifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JEDA_ITEM.ITEM_MODIFIED
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    private Date itemModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JEDA_ITEM.ITEM_VERSION
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    private BigDecimal itemVersion;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JEDA_ITEM.ITEM_ID
     *
     * @return the value of JEDA_ITEM.ITEM_ID
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JEDA_ITEM.ITEM_ID
     *
     * @param itemId the value for JEDA_ITEM.ITEM_ID
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JEDA_ITEM.ITEM_NAME
     *
     * @return the value of JEDA_ITEM.ITEM_NAME
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JEDA_ITEM.ITEM_NAME
     *
     * @param itemName the value for JEDA_ITEM.ITEM_NAME
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JEDA_ITEM.ITEM_DESCRIPTION
     *
     * @return the value of JEDA_ITEM.ITEM_DESCRIPTION
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JEDA_ITEM.ITEM_DESCRIPTION
     *
     * @param itemDescription the value for JEDA_ITEM.ITEM_DESCRIPTION
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription == null ? null : itemDescription.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JEDA_ITEM.ITEM_IS_PERMISSION
     *
     * @return the value of JEDA_ITEM.ITEM_IS_PERMISSION
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public BigDecimal getItemIsPermission() {
        return itemIsPermission;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JEDA_ITEM.ITEM_IS_PERMISSION
     *
     * @param itemIsPermission the value for JEDA_ITEM.ITEM_IS_PERMISSION
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public void setItemIsPermission(BigDecimal itemIsPermission) {
        this.itemIsPermission = itemIsPermission;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JEDA_ITEM.ITEM_ORDER
     *
     * @return the value of JEDA_ITEM.ITEM_ORDER
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public BigDecimal getItemOrder() {
        return itemOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JEDA_ITEM.ITEM_ORDER
     *
     * @param itemOrder the value for JEDA_ITEM.ITEM_ORDER
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public void setItemOrder(BigDecimal itemOrder) {
        this.itemOrder = itemOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JEDA_ITEM.ITEM_READ_ONLY
     *
     * @return the value of JEDA_ITEM.ITEM_READ_ONLY
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public BigDecimal getItemReadOnly() {
        return itemReadOnly;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JEDA_ITEM.ITEM_READ_ONLY
     *
     * @param itemReadOnly the value for JEDA_ITEM.ITEM_READ_ONLY
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public void setItemReadOnly(BigDecimal itemReadOnly) {
        this.itemReadOnly = itemReadOnly;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JEDA_ITEM.ITEM_CREATOR
     *
     * @return the value of JEDA_ITEM.ITEM_CREATOR
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public String getItemCreator() {
        return itemCreator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JEDA_ITEM.ITEM_CREATOR
     *
     * @param itemCreator the value for JEDA_ITEM.ITEM_CREATOR
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public void setItemCreator(String itemCreator) {
        this.itemCreator = itemCreator == null ? null : itemCreator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JEDA_ITEM.ITEM_CREATED
     *
     * @return the value of JEDA_ITEM.ITEM_CREATED
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public Date getItemCreated() {
        return itemCreated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JEDA_ITEM.ITEM_CREATED
     *
     * @param itemCreated the value for JEDA_ITEM.ITEM_CREATED
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public void setItemCreated(Date itemCreated) {
        this.itemCreated = itemCreated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JEDA_ITEM.ITEM_MODIFIER
     *
     * @return the value of JEDA_ITEM.ITEM_MODIFIER
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public String getItemModifier() {
        return itemModifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JEDA_ITEM.ITEM_MODIFIER
     *
     * @param itemModifier the value for JEDA_ITEM.ITEM_MODIFIER
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public void setItemModifier(String itemModifier) {
        this.itemModifier = itemModifier == null ? null : itemModifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JEDA_ITEM.ITEM_MODIFIED
     *
     * @return the value of JEDA_ITEM.ITEM_MODIFIED
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public Date getItemModified() {
        return itemModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JEDA_ITEM.ITEM_MODIFIED
     *
     * @param itemModified the value for JEDA_ITEM.ITEM_MODIFIED
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public void setItemModified(Date itemModified) {
        this.itemModified = itemModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JEDA_ITEM.ITEM_VERSION
     *
     * @return the value of JEDA_ITEM.ITEM_VERSION
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public BigDecimal getItemVersion() {
        return itemVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JEDA_ITEM.ITEM_VERSION
     *
     * @param itemVersion the value for JEDA_ITEM.ITEM_VERSION
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public void setItemVersion(BigDecimal itemVersion) {
        this.itemVersion = itemVersion;
    }
}