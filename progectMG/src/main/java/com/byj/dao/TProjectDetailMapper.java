package com.byj.dao;

import com.byj.dao.TProjectDetail;
import com.byj.dao.TProjectDetailExample;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TProjectDetailMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_PROJECT_DETAIL
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    long countByExample(TProjectDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_PROJECT_DETAIL
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    int deleteByExample(TProjectDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_PROJECT_DETAIL
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    int deleteByPrimaryKey(BigDecimal id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_PROJECT_DETAIL
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    int insert(TProjectDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_PROJECT_DETAIL
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    int insertSelective(TProjectDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_PROJECT_DETAIL
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    List<TProjectDetail> selectByExampleWithRowbounds(TProjectDetailExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_PROJECT_DETAIL
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    List<TProjectDetail> selectByExample(TProjectDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_PROJECT_DETAIL
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    TProjectDetail selectByPrimaryKey(BigDecimal id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_PROJECT_DETAIL
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    int updateByExampleSelective(@Param("record") TProjectDetail record, @Param("example") TProjectDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_PROJECT_DETAIL
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    int updateByExample(@Param("record") TProjectDetail record, @Param("example") TProjectDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_PROJECT_DETAIL
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    int updateByPrimaryKeySelective(TProjectDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_PROJECT_DETAIL
     *
     * @mbg.generated Sat May 19 15:33:17 CST 2018
     */
    int updateByPrimaryKey(TProjectDetail record);

    TProjectDetail sumByWorkpiece(String workpieceNo);
}