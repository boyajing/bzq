package com.byj.service;

import bas.jeda.core.AbstrctDBInf;
import bas.jeda.dao.JedaUser;
import bas.jeda.dao.JedaUserExample;
import bas.jeda.dao.JedaUserMapper;
import com.byj.dao.TSalary;
import com.byj.dao.TSalaryExample;
import com.byj.dao.TSalaryMapper;
import com.byj.daoExtend.UserVo;
import com.byj.daoExtend.UserVoMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/5/24 0024.
 */
@Service
public class SalaryService extends AbstrctDBInf  {
    @Autowired
    protected SqlSessionTemplate sqlSession;
    private <T extends Object> T getMapper(Class<T> type) {
        return this.sqlSession.getMapper(type);
    }

    public List<TSalary> selectByExample(TSalaryExample example) {
        return this.getMapper(TSalaryMapper.class).selectByExample(example);
    }

    public List<UserVo> selectByExample(JedaUserExample userExample) {
        return this.getMapper(UserVoMapper.class).selectByExample(userExample);
    }

    public int updateByPrimaryKey(TSalary salary) {
        return this.getMapper(TSalaryMapper.class).updateByPrimaryKeySelective(salary);
    }

    public int insert(TSalary salary) {
        return this.getMapper(TSalaryMapper.class).insertSelective(salary);
    }

    public TSalary sumSalaryByMonth(String userId, String date) {
        return this.getMapper(TSalaryMapper.class).sumSalaryByMonth(userId,date);
    }
    public int updateByExample(TSalary salary,TSalaryExample example) {
        return this.getMapper(TSalaryMapper.class).updateByExampleSelective(salary,example);
    }
    public void updateByExampleMonth(TSalary salary,TSalaryExample example,String date) {
        this.getMapper(TSalaryMapper.class).updateByExampleMonth(salary,example,date);
    }
}
