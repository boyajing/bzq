package com.nantian.dao;

import com.nantian.dao.TCustomerBasic;
import com.nantian.dao.TCustomerBasicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TCustomerBasicMapper {
    int countByExample(TCustomerBasicExample example);

    int deleteByExample(TCustomerBasicExample example);

    int deleteByPrimaryKey(String customerId);

    int insert(TCustomerBasic record);

    int insertSelective(TCustomerBasic record);

    List<TCustomerBasic> selectByExample(TCustomerBasicExample example);

    TCustomerBasic selectByPrimaryKey(String customerId);

    int updateByExampleSelective(@Param("record") TCustomerBasic record, @Param("example") TCustomerBasicExample example);

    int updateByExample(@Param("record") TCustomerBasic record, @Param("example") TCustomerBasicExample example);

    int updateByPrimaryKeySelective(TCustomerBasic record);

    int updateByPrimaryKey(TCustomerBasic record);
}