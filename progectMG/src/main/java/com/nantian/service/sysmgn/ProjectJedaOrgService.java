package com.nantian.service.sysmgn;

import bas.jeda.core.AbstrctDBInf;
import com.nantian.dao.*;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectJedaOrgService extends AbstrctDBInf {
    @Autowired
    protected SqlSessionTemplate sqlSession;

    private <T extends Object> T getMapper(Class<T> type) {
        return this.sqlSession.getMapper(type);
    }
    public JedaOrg selectByPrimaryKey(String id){
        return getMapper(JedaOrgMapper.class).selectByPrimaryKey(id);
    }

    public List<JedaOrg> query(JedaOrgExample jedaOrgExample){
        return getMapper(JedaOrgMapper.class).selectByExample(jedaOrgExample);
    }

    public int count(JedaOrgExample jedaOrgExample){
        return getMapper(JedaOrgMapper.class).countByExample(jedaOrgExample);
    }

    public int update(JedaOrg jedaOrg){
        return getMapper(JedaOrgMapper.class).updateByPrimaryKey(jedaOrg);
    }

    public int save(JedaOrg jedaOrg){
        return getMapper(JedaOrgMapper.class).insertSelective(jedaOrg);
    }

    public int delete(String  id){
        return getMapper(JedaOrgMapper.class).deleteByPrimaryKey(id);
    }



}