package com.byj.dao;

import com.byj.dao.TExpenseDetail;
import com.byj.dao.TExpenseDetailExample;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TExpenseDetailMapper {
    long countByExample(TExpenseDetailExample example);

    int deleteByExample(TExpenseDetailExample example);

    int deleteByPrimaryKey(BigDecimal id);

    int insert(TExpenseDetail record);

    int insertSelective(TExpenseDetail record);

    List<TExpenseDetail> selectByExampleWithRowbounds(TExpenseDetailExample example, RowBounds rowBounds);

    List<TExpenseDetail> selectByExample(TExpenseDetailExample example);

    TExpenseDetail selectByPrimaryKey(BigDecimal id);

    int updateByExampleSelective(@Param("record") TExpenseDetail record, @Param("example") TExpenseDetailExample example);

    int updateByExample(@Param("record") TExpenseDetail record, @Param("example") TExpenseDetailExample example);

    int updateByPrimaryKeySelective(TExpenseDetail record);

    int updateByPrimaryKey(TExpenseDetail record);

    TExpenseDetail sumByTool(String toolNo);
}