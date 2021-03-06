package com.byj.dao;

import com.byj.dao.TTool;
import com.byj.dao.TToolExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TToolMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOOL
     *
     * @mbg.generated Tue May 15 21:38:24 CST 2018
     */
    long countByExample(TToolExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOOL
     *
     * @mbg.generated Tue May 15 21:38:24 CST 2018
     */
    int deleteByExample(TToolExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOOL
     *
     * @mbg.generated Tue May 15 21:38:24 CST 2018
     */
    int deleteByPrimaryKey(String toolNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOOL
     *
     * @mbg.generated Tue May 15 21:38:24 CST 2018
     */
    int insert(TTool record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOOL
     *
     * @mbg.generated Tue May 15 21:38:24 CST 2018
     */
    int insertSelective(TTool record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOOL
     *
     * @mbg.generated Tue May 15 21:38:24 CST 2018
     */
    List<TTool> selectByExampleWithRowbounds(TToolExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOOL
     *
     * @mbg.generated Tue May 15 21:38:24 CST 2018
     */
    List<TTool> selectByExample(TToolExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOOL
     *
     * @mbg.generated Tue May 15 21:38:24 CST 2018
     */
    TTool selectByPrimaryKey(String toolNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOOL
     *
     * @mbg.generated Tue May 15 21:38:24 CST 2018
     */
    int updateByExampleSelective(@Param("record") TTool record, @Param("example") TToolExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOOL
     *
     * @mbg.generated Tue May 15 21:38:24 CST 2018
     */
    int updateByExample(@Param("record") TTool record, @Param("example") TToolExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOOL
     *
     * @mbg.generated Tue May 15 21:38:24 CST 2018
     */
    int updateByPrimaryKeySelective(TTool record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_TOOL
     *
     * @mbg.generated Tue May 15 21:38:24 CST 2018
     */
    int updateByPrimaryKey(TTool record);
}