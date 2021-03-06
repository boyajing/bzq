package com.nantian.dao;

import com.nantian.dao.TSysCode;
import com.nantian.dao.TSysCodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TSysCodeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_CODE
     *
     * @mbg.generated Fri Mar 31 17:51:43 CST 2017
     */
    long countByExample(TSysCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_CODE
     *
     * @mbg.generated Fri Mar 31 17:51:43 CST 2017
     */
    int deleteByExample(TSysCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_CODE
     *
     * @mbg.generated Fri Mar 31 17:51:43 CST 2017
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_CODE
     *
     * @mbg.generated Fri Mar 31 17:51:43 CST 2017
     */
    int insert(TSysCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_CODE
     *
     * @mbg.generated Fri Mar 31 17:51:43 CST 2017
     */
    int insertSelective(TSysCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_CODE
     *
     * @mbg.generated Fri Mar 31 17:51:43 CST 2017
     */
    List<TSysCode> selectByExampleWithRowbounds(TSysCodeExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_CODE
     *
     * @mbg.generated Fri Mar 31 17:51:43 CST 2017
     */
    List<TSysCode> selectByExample(TSysCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_CODE
     *
     * @mbg.generated Fri Mar 31 17:51:43 CST 2017
     */
    TSysCode selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_CODE
     *
     * @mbg.generated Fri Mar 31 17:51:43 CST 2017
     */
    int updateByExampleSelective(@Param("record") TSysCode record, @Param("example") TSysCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_CODE
     *
     * @mbg.generated Fri Mar 31 17:51:43 CST 2017
     */
    int updateByExample(@Param("record") TSysCode record, @Param("example") TSysCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_CODE
     *
     * @mbg.generated Fri Mar 31 17:51:43 CST 2017
     */
    int updateByPrimaryKeySelective(TSysCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_SYS_CODE
     *
     * @mbg.generated Fri Mar 31 17:51:43 CST 2017
     */
    int updateByPrimaryKey(TSysCode record);
}