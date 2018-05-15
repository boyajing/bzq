package com.nantian.service.sysmgn;

import bas.jeda.core.AbstrctDBInf;
import com.nantian.dao.*;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 接口调用查询数据字典
 */
@Service
public class TSysCodeService extends AbstrctDBInf {
    @Autowired
    protected SqlSessionTemplate sqlSession;

    private <T extends Object> T getMapper(Class<T> type) {
        return this.sqlSession.getMapper(type);
    }

    @Transactional
    public List<TSysCode> query(TSysCodeExample example){
        return getMapper(TSysCodeMapper.class).selectByExample(example);
    }

}
