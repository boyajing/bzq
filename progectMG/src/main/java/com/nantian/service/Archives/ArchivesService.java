package com.nantian.service.Archives;

import bas.jeda.core.AbstrctDBInf;
import com.nantian.dao.TArchives;
import com.nantian.dao.TArchivesExample;
import com.nantian.dao.TArchivesMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ArchivesService extends AbstrctDBInf {

    @Autowired
    protected SqlSessionTemplate sqlSession;
    private <T extends Object> T getMapper(Class<T> type) {
        return this.sqlSession.getMapper(type);
    }
    //核保申请查询
    @Transactional(readOnly=true)
    public TArchives selectByPrimaryKey(String Id){
        return getMapper(TArchivesMapper.class).selectByPrimaryKey(Id);
    }
    @Transactional(readOnly=true)
    public List<TArchives> selectByExample(TArchivesExample example){
        return getMapper(TArchivesMapper.class).selectByExample(example);
    }

    @Transactional(readOnly=true)
    public int count(TArchivesExample example){
        return getMapper(TArchivesMapper.class).countByExample(example);
    }

    //查询
    @Transactional
    public TArchives query(String id){
        return getMapper(TArchivesMapper.class).selectByPrimaryKey(id);
    }

}
