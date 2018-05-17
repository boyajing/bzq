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
public class WorkpiecService extends AbstrctDBInf {
    @Autowired
    protected SqlSessionTemplate sqlSession;
    private <T extends Object> T getMapper(Class<T> type) {
        return this.sqlSession.getMapper(type);
    }


    public List<TWorkpiece> selectByExample(TWorkpieceExample example) {
        return this.getMapper(TWorkpieceMapper.class).selectByExample(example);
    }

    public long countByExample(TWorkpieceExample example) {
        return this.getMapper(TWorkpieceMapper.class).countByExample(example);
    }

    public int insert(TWorkpiece workpiece) {
        return this.getMapper(TWorkpieceMapper.class).insertSelective(workpiece);
    }

    public TWorkpiece selectByPrimaryKey(String workpieceNo) {
        return this.getMapper(TWorkpieceMapper.class).selectByPrimaryKey(workpieceNo);
    }

    public int update(TWorkpiece workpiece) {
        return this.getMapper(TWorkpieceMapper.class).updateByPrimaryKeySelective(workpiece);
    }
}
