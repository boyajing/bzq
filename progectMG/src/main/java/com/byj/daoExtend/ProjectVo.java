package com.byj.daoExtend;

import com.byj.dao.TProjectBase;
import com.byj.dao.TProjectDetail;

import java.util.List;

/**
 * Created by Administrator on 2018/5/19 0019.
 */
public class ProjectVo {
    private TProjectBase project;
    private List<TProjectDetail> details;

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
