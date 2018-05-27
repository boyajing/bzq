package com.byj.daoExtend;

import bas.jeda.dao.JedaUser;
import com.byj.dao.TSalary;

import java.util.List;

/**
 * Created by Administrator on 2018/5/24 0024.
 */
public class UserVo extends JedaUser{
    private List<TSalary> salaryList;

    public List<TSalary> getSalaryList() {
        return salaryList;
    }

    public void setSalaryList(List<TSalary> salaryList) {
        this.salaryList = salaryList;
    }
}
