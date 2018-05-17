package com.byj.service;

import bas.jeda.core.AbstrctDBInf;
import com.byj.dao.TExpenseDetail;
import com.byj.dao.TExpenseDetailMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/5/17 0017.
 */
@Service
public class ExpenseService extends AbstrctDBInf {
    //select sum(t.quantity),sum(t.total_price) from t_expense_detail t where t.expense_type='2' and t.is_valid='0' and t.tool_no='D01000000001';
    @Autowired
    protected SqlSessionTemplate sqlSession;
    private <T extends Object> T getMapper(Class<T> type) {
        return this.sqlSession.getMapper(type);
    }


    public TExpenseDetail sumByTool(String toolNo) {
        return this.getMapper(TExpenseDetailMapper.class).sumByTool(toolNo);
    }
}
