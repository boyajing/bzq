package com.nantian.service.tradInstitutionPool;

import bas.jeda.core.AbstrctDBInf;
import com.nantian.dao.TCustmorCredit;
import com.nantian.dao.TCustmorCreditMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by nantian on 2017/10/26.
 */
@Service
public class TCustmorCreditService extends AbstrctDBInf {
    @Autowired
    protected SqlSessionTemplate sqlSession;
    private <T extends Object> T getMapper(Class<T> type) {
        return this.sqlSession.getMapper(type);
    }

    public int insertSelective(TCustmorCredit tCustmorCredit){return getMapper(TCustmorCreditMapper.class).insertSelective(tCustmorCredit);}

    public int insert(TCustmorCredit tCustmorCredit){return getMapper(TCustmorCreditMapper.class).insert(tCustmorCredit);}

    public void insertCreditList(List<TCustmorCredit> tCustmorCreditList){
        for (TCustmorCredit credit:tCustmorCreditList) {
            this.insertSelective(credit);
        }
    }
}
