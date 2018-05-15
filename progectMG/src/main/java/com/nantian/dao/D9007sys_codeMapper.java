package com.nantian.dao;

import com.nantian.dao.D9007sys_code;
import com.nantian.dao.D9007sys_codeExample;
import com.nantian.dao.D9007sys_codeKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface D9007sys_codeMapper {
    int countByExample(D9007sys_codeExample example);

    int deleteByExample(D9007sys_codeExample example);

    int deleteByPrimaryKey(D9007sys_codeKey key);

    int insert(D9007sys_code record);

    int insertSelective(D9007sys_code record);

    List<D9007sys_code> selectByExample(D9007sys_codeExample example);

    D9007sys_code selectByPrimaryKey(D9007sys_codeKey key);

    int updateByExampleSelective(@Param("record") D9007sys_code record, @Param("example") D9007sys_codeExample example);

    int updateByExample(@Param("record") D9007sys_code record, @Param("example") D9007sys_codeExample example);

    int updateByPrimaryKeySelective(D9007sys_code record);

    int updateByPrimaryKey(D9007sys_code record);
}