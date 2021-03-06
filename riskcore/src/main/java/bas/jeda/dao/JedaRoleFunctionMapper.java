package bas.jeda.dao;

import bas.jeda.dao.JedaRoleFunctionExample;
import bas.jeda.dao.JedaRoleFunctionKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JedaRoleFunctionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JEDA_ROLE_FUNCTION
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    int countByExample(JedaRoleFunctionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JEDA_ROLE_FUNCTION
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    int deleteByExample(JedaRoleFunctionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JEDA_ROLE_FUNCTION
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    int deleteByPrimaryKey(JedaRoleFunctionKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JEDA_ROLE_FUNCTION
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    int insert(JedaRoleFunctionKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JEDA_ROLE_FUNCTION
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    int insertSelective(JedaRoleFunctionKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JEDA_ROLE_FUNCTION
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    List<JedaRoleFunctionKey> selectByExample(JedaRoleFunctionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JEDA_ROLE_FUNCTION
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    int updateByExampleSelective(@Param("record") JedaRoleFunctionKey record, @Param("example") JedaRoleFunctionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JEDA_ROLE_FUNCTION
     *
     * @mbggenerated Mon Mar 02 21:49:14 CST 2015
     */
    int updateByExample(@Param("record") JedaRoleFunctionKey record, @Param("example") JedaRoleFunctionExample example);
}