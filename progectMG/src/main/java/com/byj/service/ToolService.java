package com.byj.service;

import bas.jeda.core.AbstrctDBInf;
import com.byj.dao.*;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/5/16 0016.
 */
@Service
public class ToolService extends AbstrctDBInf {
    @Autowired
    protected SqlSessionTemplate sqlSession;
    private <T extends Object> T getMapper(Class<T> type) {
        return this.sqlSession.getMapper(type);
    }


    public List<TTool> selectByExample(TToolExample example) {
        return this.getMapper(TToolMapper.class).selectByExample(example);
    }

    public long countByExample(TToolExample example) {
        return this.getMapper(TToolMapper.class).countByExample(example);
    }

    public int insert(TTool tool) {
        return this.getMapper(TToolMapper.class).insertSelective(tool);
    }

    public TTool selectByPrimaryKey(String toolNo) {
        return this.getMapper(TToolMapper.class).selectByPrimaryKey(toolNo);
    }

    public int update(TTool tool) {
        return this.getMapper(TToolMapper.class).updateByPrimaryKeySelective(tool);
    }
}
