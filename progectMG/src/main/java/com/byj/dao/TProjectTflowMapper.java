package com.byj.dao;

import com.byj.dao.TProjectTflow;
import com.byj.dao.TProjectTflowExample;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TProjectTflowMapper {
    long countByExample(TProjectTflowExample example);

    int deleteByExample(TProjectTflowExample example);

    int deleteByPrimaryKey(BigDecimal id);

    int insert(TProjectTflow record);

    int insertSelective(TProjectTflow record);

    List<TProjectTflow> selectByExampleWithRowbounds(TProjectTflowExample example, RowBounds rowBounds);

    List<TProjectTflow> selectByExample(TProjectTflowExample example);

    TProjectTflow selectByPrimaryKey(BigDecimal id);

    int updateByExampleSelective(@Param("record") TProjectTflow record, @Param("example") TProjectTflowExample example);

    int updateByExample(@Param("record") TProjectTflow record, @Param("example") TProjectTflowExample example);

    int updateByPrimaryKeySelective(TProjectTflow record);

    int updateByPrimaryKey(TProjectTflow record);
}