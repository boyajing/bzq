package com.nantian.dao;

import com.nantian.dao.TArchives;
import com.nantian.dao.TArchivesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TArchivesMapper {
    int countByExample(TArchivesExample example);

    int deleteByExample(TArchivesExample example);

    int deleteByPrimaryKey(String id);

    int insert(TArchives record);

    int insertSelective(TArchives record);

    List<TArchives> selectByExample(TArchivesExample example);

    TArchives selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TArchives record, @Param("example") TArchivesExample example);

    int updateByExample(@Param("record") TArchives record, @Param("example") TArchivesExample example);

    int updateByPrimaryKeySelective(TArchives record);

    int updateByPrimaryKey(TArchives record);
}