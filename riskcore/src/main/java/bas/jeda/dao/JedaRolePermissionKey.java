package bas.jeda.dao;

public class JedaRolePermissionKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JEDA_ROLE_PERMISSION.ROLE_ID
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    private String roleId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JEDA_ROLE_PERMISSION.PERMISSION_ID
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    private String permissionId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JEDA_ROLE_PERMISSION.ROLE_ID
     *
     * @return the value of JEDA_ROLE_PERMISSION.ROLE_ID
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JEDA_ROLE_PERMISSION.ROLE_ID
     *
     * @param roleId the value for JEDA_ROLE_PERMISSION.ROLE_ID
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JEDA_ROLE_PERMISSION.PERMISSION_ID
     *
     * @return the value of JEDA_ROLE_PERMISSION.PERMISSION_ID
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public String getPermissionId() {
        return permissionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JEDA_ROLE_PERMISSION.PERMISSION_ID
     *
     * @param permissionId the value for JEDA_ROLE_PERMISSION.PERMISSION_ID
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId == null ? null : permissionId.trim();
    }
}