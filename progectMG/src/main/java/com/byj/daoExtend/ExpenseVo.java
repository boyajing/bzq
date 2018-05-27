package com.byj.daoExtend;

import com.byj.dao.TExpenseDetail;

import java.util.List;

/**
 * Created by Administrator on 2018/5/27 0027.
 */
public class ExpenseVo {
    private List<TExpenseDetail> details;

    public List<TExpenseDetail> getDetails() {
        return details;
    }

    public void setDetails(List<TExpenseDetail> details) {
        this.details = details;
    }
}
