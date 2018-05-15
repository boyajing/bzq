package com.nantian.service.sysmgn;

import bas.jeda.core.AbstrctDBInf;
import com.nantian.dao.*;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JedaOrgService extends AbstrctDBInf {
    @Autowired
    protected SqlSessionTemplate sqlSession;
    private <T extends Object> T getMapper(Class<T> type) {
        return this.sqlSession.getMapper(type);
    }

    public JedaOrg selectByPrimaryKey(String orgId){return getMapper(JedaOrgMapper.class).selectByPrimaryKey(orgId);}
    public int save(JedaOrg jedaOrg){return getMapper(JedaOrgMapper.class).insertSelective(jedaOrg);}
    public int save2(JedaOrg jedaOrg){return getMapper(JedaOrgMapper.class).insert(jedaOrg);}
    public int update(JedaOrg jedaOrg){return getMapper(JedaOrgMapper.class).updateByPrimaryKey(jedaOrg);}

    public List<JedaOrg> query(JedaOrgExample jedaOrgExample){
        return getMapper(JedaOrgMapper.class).selectByExample(jedaOrgExample);
    }
    public int count(JedaOrgExample jedaOrgExample){
        return getMapper(JedaOrgMapper.class).countByExample(jedaOrgExample);
    }
}