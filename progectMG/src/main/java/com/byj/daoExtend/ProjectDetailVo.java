package com.byj.daoExtend;

import com.byj.dao.TProjectDetail;
import com.byj.dao.TProjectTflow;

import java.util.List;

/**
 * Created by Administrator on 2018/5/23 0023.
 */
public class ProjectDetailVo extends TProjectDetail{
    private List<TProjectTflow> tflows;

    public List<TProjectTflow> getTflows() {
        return tflows;
    }

    public void setTflows(List<TProjectTflow> tflows) {
        this.tflows = tflows;
    }
}
