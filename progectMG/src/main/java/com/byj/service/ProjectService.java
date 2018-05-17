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
public class ProjectService extends AbstrctDBInf {
    @Autowired
    protected SqlSessionTemplate sqlSession;
    private <T extends Object> T getMapper(Class<T> type) {
        return this.sqlSession.getMapper(type);
    }


    public List<TProjectBase> selectByExample(TProjectBaseExample example) {
        return this.getMapper(TProjectBaseMapper.class).selectByExample(example);
    }

    public long countByExample(TProjectBaseExample example) {
        return this.getMapper(TProjectBaseMapper.class).countByExample(example);
    }

    public int insert(TProjectBase project) {
        return this.getMapper(TProjectBaseMapper.class).insertSelective(project);
    }

    public int update(TProjectBase project) {
        return this.getMapper(TProjectBaseMapper.class).updateByPrimaryKeySelective(project);
    }

    public TProjectBase selectByPrimaryKey(String projectNo) {
        return this.getMapper(TProjectBaseMapper.class).selectByPrimaryKey(projectNo);
    }

    public TProjectBase countHisByCustomer(String customerNo) {
        return this.getMapper(TProjectBaseMapper.class).countHisByCustomer(customerNo);
    }
    public long countByExample(TProjectDetailExample example) {
        return this.getMapper(TProjectDetailMapper.class).countByExample(example);
    }

    public TProjectDetail sumByWorkpiece(String workpieceNo) {
        return this.getMapper(TProjectDetailMapper.class).sumByWorkpiece(workpieceNo);
    }
}