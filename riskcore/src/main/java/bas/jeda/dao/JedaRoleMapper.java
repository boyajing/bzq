package bas.jeda.dao;

import bas.jeda.dao.JedaRole;
import bas.jeda.dao.JedaRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JedaRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JEDA_ROLE
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    int countByExample(JedaRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JEDA_ROLE
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    int deleteByExample(JedaRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JEDA_ROLE
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    int deleteByPrimaryKey(String roleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JEDA_ROLE
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    int insert(JedaRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JEDA_ROLE
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    int insertSelective(JedaRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JEDA_ROLE
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    List<JedaRole> selectByExample(JedaRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JEDA_ROLE
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    JedaRole selectByPrimaryKey(String roleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JEDA_ROLE
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    int updateByExampleSelective(@Param("record") JedaRole record, @Param("example") JedaRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JEDA_ROLE
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    int updateByExample(@Param("record") JedaRole record, @Param("example") JedaRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JEDA_ROLE
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    int updateByPrimaryKeySelective(JedaRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JEDA_ROLE
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    int updateByPrimaryKey(JedaRole record);
}