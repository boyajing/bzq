package bas.jeda.dao;

import bas.jeda.dao.SSystemparam;
import bas.jeda.dao.SSystemparamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SSystemparamMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table S_SYSTEMPARAM
     *
     * @mbggenerated Mon May 11 14:29:15 CST 2015
     */
    int countByExample(SSystemparamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table S_SYSTEMPARAM
     *
     * @mbggenerated Mon May 11 14:29:15 CST 2015
     */
    int deleteByExample(SSystemparamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table S_SYSTEMPARAM
     *
     * @mbggenerated Mon May 11 14:29:15 CST 2015
     */
    int deleteByPrimaryKey(String paramname);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table S_SYSTEMPARAM
     *
     * @mbggenerated Mon May 11 14:29:15 CST 2015
     */
    int insert(SSystemparam record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table S_SYSTEMPARAM
     *
     * @mbggenerated Mon May 11 14:29:15 CST 2015
     */
    int insertSelective(SSystemparam record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table S_SYSTEMPARAM
     *
     * @mbggenerated Mon May 11 14:29:15 CST 2015
     */
    List<SSystemparam> selectByExample(SSystemparamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table S_SYSTEMPARAM
     *
     * @mbggenerated Mon May 11 14:29:15 CST 2015
     */
    SSystemparam selectByPrimaryKey(String paramname);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table S_SYSTEMPARAM
     *
     * @mbggenerated Mon May 11 14:29:15 CST 2015
     */
    int updateByExampleSelective(@Param("record") SSystemparam record, @Param("example") SSystemparamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table S_SYSTEMPARAM
     *
     * @mbggenerated Mon May 11 14:29:15 CST 2015
     */
    int updateByExample(@Param("record") SSystemparam record, @Param("example") SSystemparamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table S_SYSTEMPARAM
     *
     * @mbggenerated Mon May 11 14:29:15 CST 2015
     */
    int updateByPrimaryKeySelective(SSystemparam record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table S_SYSTEMPARAM
     *
     * @mbggenerated Mon May 11 14:29:15 CST 2015
     */
    int updateByPrimaryKey(SSystemparam record);
}