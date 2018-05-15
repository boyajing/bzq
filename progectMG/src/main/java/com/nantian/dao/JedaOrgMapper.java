package com.nantian.dao;

import com.nantian.dao.JedaOrg;
import com.nantian.dao.JedaOrgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JedaOrgMapper {
    int countByExample(JedaOrgExample example);

    int deleteByExample(JedaOrgExample example);

    int deleteByPrimaryKey(String orgId);

    int insert(JedaOrg record);

    int insertSelective(JedaOrg record);

    List<JedaOrg> selectByExample(JedaOrgExample example);

    JedaOrg selectByPrimaryKey(String orgId);

    int updateByExampleSelective(@Param("record") JedaOrg record, @Param("example") JedaOrgExample example);

    int updateByExample(@Param("record") JedaOrg record, @Param("example") JedaOrgExample example);

    int updateByPrimaryKeySelective(JedaOrg record);

    int updateByPrimaryKey(JedaOrg record);
}