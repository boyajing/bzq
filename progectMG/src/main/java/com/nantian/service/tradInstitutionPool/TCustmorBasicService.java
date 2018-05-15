package com.nantian.service.tradInstitutionPool;

import bas.jeda.core.AbstrctDBInf;
import com.nantian.dao.TCustmorBasic;
import com.nantian.dao.TCustmorBasicExample;
import com.nantian.dao.TCustmorBasicMapper;
import com.nantian.daoExtend.TCustmorBasicVo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by nantian on 2017/10/25.
 */
@Service
public class TCustmorBasicService extends AbstrctDBInf {

    @Autowired
    protected SqlSessionTemplate sqlSession;
    private <T extends Object> T getMapper(Class<T> type) {
        return this.sqlSession.getMapper(type);
    }

    public int insertSelective(TCustmorBasic tCustmorBasic){return getMapper(TCustmorBasicMapper.class).insertSelective(tCustmorBasic);}

    public List<TCustmorBasic> selectByExample(TCustmorBasicExample example){return getMapper(TCustmorBasicMapper.class).selectByExample(example);}

    public int countByExample(TCustmorBasicExample example){return getMapper(TCustmorBasicMapper.class).countByExample(example);}

    public TCustmorBasic selectByPrimaryKey(String custmorNo){return getMapper(TCustmorBasicMapper.class).selectByPrimaryKey(custmorNo);}

    public int deleteByPrimaryKey(String custmorNo){return getMapper(TCustmorBasicMapper.class).deleteByPrimaryKey(custmorNo);}

    public TCustmorBasic createTCustmorBasic(TCustmorBasicVo tCustmorBasicVo) {
        TCustmorBasic tCustmorBasic=new TCustmorBasic();
        tCustmorBasic.setCustmorNo(tCustmorBasicVo.getCustmorNo());
        tCustmorBasic.setCustmorName(tCustmorBasicVo.getCustmorName());
        tCustmorBasic.setCustmorType(tCustmorBasicVo.getCustmorType());
        tCustmorBasic.setOrgCode(tCustmorBasicVo.getOrgCode());
        tCustmorBasic.setBusiLienceNo(tCustmorBasicVo.getBusiLienceNo());
        tCustmorBasic.setOwnType(tCustmorBasicVo.getOwnType());
        tCustmorBasic.setOrgType(tCustmorBasicVo.getOrgType());
        tCustmorBasic.setIndNo(tCustmorBasicVo.getIndNo());
        tCustmorBasic.setEnteSize(tCustmorBasicVo.getEnteSize());
        tCustmorBasic.setRegAmt(tCustmorBasicVo.getRegAmt());
        tCustmorBasic.setPostCode(tCustmorBasicVo.getPostCode());
        tCustmorBasic.setAddr(tCustmorBasicVo.getAddr());
        tCustmorBasic.setConnName(tCustmorBasicVo.getConnName());
        tCustmorBasic.setConnPhone(tCustmorBasicVo.getConnPhone());
        tCustmorBasic.setRespName(tCustmorBasicVo.getRespName());
        tCustmorBasic.setAccNo(tCustmorBasicVo.getAccNo());
        tCustmorBasic.setBankName(tCustmorBasicVo.getBankName());
        tCustmorBasic.setBankType(tCustmorBasicVo.getBankType());
        tCustmorBasic.setNationalLience(tCustmorBasicVo.getNationalLience());
        tCustmorBasic.setPropertyTaxLience(tCustmorBasicVo.getPropertyTaxLience());
        tCustmorBasic.setCredit(tCustmorBasicVo.getCredit());
        tCustmorBasic.setRembNo(tCustmorBasicVo.getRembNo());
        tCustmorBasic.setOperator(tCustmorBasicVo.getOperator());
        tCustmorBasic.setOprDate(tCustmorBasicVo.getOprDate());
        tCustmorBasic.setStatus(tCustmorBasicVo.getStatus());
        tCustmorBasic.setIsValid(tCustmorBasicVo.getIsValid());
        tCustmorBasic.setMark(tCustmorBasicVo.getMark());
        return tCustmorBasic;
    }
}
