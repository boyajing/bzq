package com.byj.service;

import bas.jeda.core.AbstrctDBInf;
import com.byj.dao.TCustomer;
import com.byj.dao.TCustomerExample;
import com.byj.dao.TCustomerMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/5/15 0015.
 */
@Service
public class CustomerService extends AbstrctDBInf {
    @Autowired
    protected SqlSessionTemplate sqlSession;
    private <T extends Object> T getMapper(Class<T> type) {
        return this.sqlSession.getMapper(type);
    }


    public List<TCustomer> selectByExample(TCustomerExample example) {
        return this.getMapper(TCustomerMapper.class).selectByExample(example);
    }

    public long countByExample(TCustomerExample example) {
        return this.getMapper(TCustomerMapper.class).countByExample(example);
    }

    public int insert(TCustomer customer) {
        return this.getMapper(TCustomerMapper.class).insertSelective(customer);
    }

    public int update(TCustomer customer) {
        return this.getMapper(TCustomerMapper.class).updateByPrimaryKeySelective(customer);
    }

    public TCustomer selectByPrimaryKey(String customerNo) {
        return this.getMapper(TCustomerMapper.class).selectByPrimaryKey(customerNo);
    }

    public int delete(String customerNo) {
        return this.getMapper(TCustomerMapper.class).deleteByPrimaryKey(customerNo);
    }
}
