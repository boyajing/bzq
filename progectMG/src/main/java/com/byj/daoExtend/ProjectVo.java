package com.byj.daoExtend;

import com.byj.dao.TProjectBase;
import com.byj.dao.TProjectDetail;
import com.byj.dao.TSalary;

import java.util.List;

/**
 * Created by Administrator on 2018/5/19 0019.
 */
public class ProjectVo {
    private TProjectBase project;
    private List<TProjectDetail> details;
    private List<TSalary> salaryList;

    public List<TSalary> getSalaryList() {
        return salaryList;
    }

    public void setSalaryList(List<TSalary> salaryList) {
        this.salaryList = salaryList;
    }

    public TProjectBase getProject() {
        return project;
    }

    public void setProject(TProjectBase project) {
        this.project = project;
    }

    public List<TProjectDetail> getDetails() {
        return details;
    }

    public void setDetails(List<TProjectDetail> details) {
        this.details = details;
    }
}
