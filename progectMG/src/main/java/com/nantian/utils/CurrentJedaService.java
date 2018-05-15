package com.nantian.utils;


import bas.jeda.core.AbstrctDBInf;
import bas.jeda.dao.JedaRole;
import bas.jeda.dao.JedaRoleExample;
import bas.jeda.dao.JedaRoleMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrentJedaService extends AbstrctDBInf {
    @Autowired
    protected SqlSessionTemplate sqlSession;
    private <T extends Object> T getMapper(Class<T> type) {
        return this.sqlSession.getMapper(type);
    }
    //通过userid查询role
    public List<JedaRole> query(JedaRoleExample example){
        return getMapper(JedaRoleMapper.class).selectByExample(example);
    }

}
