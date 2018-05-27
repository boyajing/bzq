package com.byj.service;

import bas.jeda.core.AbstrctDBInf;
import bas.jeda.dao.JedaUser;
import com.byj.dao.*;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/5/16 0016.
 */
@Service
public class ProjectService extends AbstrctDBInf {
    @Autowired
    protected SqlSessionTemplate sqlSession;
    private <T extends Object> T getMapper(Class<T> type) {
        return this.sqlSession.getMapper(type);
    }


    public List<TProjectBase> selectByExample(TProjectBaseExample example) {
        return this.getMapper(TProjectBaseMapper.class).selectByExample(example);
    }

    public long countByExample(TProjectBaseExample example) {
        return this.getMapper(TProjectBaseMapper.class).countByExample(example);
    }

    public int insert(TProjectBase project) {
        return this.getMapper(TProjectBaseMapper.class).insertSelective(project);
    }

    public int update(TProjectBase project) {
        return this.getMapper(TProjectBaseMapper.class).updateByPrimaryKeySelective(project);
    }

    public TProjectBase selectByPrimaryKey(String projectNo) {
        return this.getMapper(TProjectBaseMapper.class).selectByPrimaryKey(projectNo);
    }

    public TProjectBase countHisByCustomer(String customerNo) {
        return this.getMapper(TProjectBaseMapper.class).countHisByCustomer(customerNo);
    }
    public long countByExample(TProjectDetailExample example) {
        return this.getMapper(TProjectDetailMapper.class).countByExample(example);
    }

    public TProjectDetail sumByWorkpiece(String workpieceNo) {
        return this.getMapper(TProjectDetailMapper.class).sumByWorkpiece(workpieceNo);
    }

    public List<TProjectDetail> selectByExample(TProjectDetailExample example) {
        return this.getMapper(TProjectDetailMapper.class).selectByExample(example);
    }

    public int insert(TProjectDetail detail) {
        return this.getMapper(TProjectDetailMapper.class).insertSelective(detail);
    }

    public int update(TProjectDetail detail) {
        return this.getMapper(TProjectDetailMapper.class).updateByPrimaryKeySelective(detail);
    }

    public TProjectBase createProject(TProjectBase project) {
        project.setEndQuantity(new BigDecimal(0));
        project.setPickupQuantity(new BigDecimal(0));
        project.setProceedsStatus("1");
        project.setActAmt(new BigDecimal(0));
        project.setApplyDate(new Date());
        project.setIsValid("0");
        project.setStatus("1");
        return project;
    }

    public TProjectDetail createDetail(TProjectBase project, TProjectDetail detail) {
        detail.setProjectNo(project.getProjectNo());
        detail.setCustomerNo(project.getCustomerNo());
        detail.setBeginDate(project.getBeginDate());
        detail.setEndDate(project.getEndDate());
        detail.setIsValid("0");
        detail.setStatus("1");
        detail.setApplyDate(new Date());
        detail.setEndQuantity(new BigDecimal(0));
        detail.setPickupQuantity(new BigDecimal(0));
        detail.setActAmt(new BigDecimal(0));
        detail.setProceedsStatus("1");
        return detail;
    }

    public int insert(TProjectTflow tflow) {
        return this.getMapper(TProjectTflowMapper.class).insertSelective(tflow);
    }

    public TProjectDetail selectByPrimaryKey(BigDecimal id) {
        return this.getMapper(TProjectDetailMapper.class).selectByPrimaryKey(id);
    }

    @Transactional
    public void insertTflowRecordEnd(TProjectDetail detail, JedaUser currentUser) {
        TProjectDetail projectDetail=this.selectByPrimaryKey(detail.getId());
        TProjectTflow tflow=new TProjectTflow();
        tflow.setProjectNo(projectDetail.getProjectNo());
        tflow.setActQuantity(detail.getEndQuantity());
        if(null==projectDetail.getEndQuantity()){
            projectDetail.setEndQuantity(new BigDecimal(0));
        }
        tflow.setRemainQuantity(projectDetail.getQuantity().subtract(projectDetail.getEndQuantity()).subtract(detail.getEndQuantity()));
        tflow.setActDate(new Date());
        tflow.setIsValid("0");
        tflow.setApplyOpr(currentUser.getUserId());
        tflow.setApplyDate(new Date());
        tflow.setType("2");
        tflow.setDetailId(detail.getId());
        tflow.setWorkpiece(projectDetail.getWorkpieceNo());
        this.getMapper(TProjectTflowMapper.class).insertSelective(tflow);
    }

    public TProjectDetail sumByProject(String projectNo) {
        return this.getMapper(TProjectDetailMapper.class).sumByProject(projectNo);
    }

    public void insertTflowPickup(TProjectDetail detail, JedaUser currentUser) {
        TProjectDetail projectDetail=this.selectByPrimaryKey(detail.getId());
        TProjectTflow tflow=new TProjectTflow();
        tflow.setProjectNo(projectDetail.getProjectNo());
        tflow.setActPickup(detail.getPickupQuantity());
        if(null==projectDetail.getPickupQuantity()){
            projectDetail.setPickupQuantity(new BigDecimal(0));
        }
        tflow.setRemainPickup(projectDetail.getQuantity().subtract(projectDetail.getPickupQuantity()).subtract(detail.getPickupQuantity()));
        tflow.setActDate(new Date());
        tflow.setIsValid("0");
        tflow.setApplyOpr(currentUser.getUserId());
        tflow.setApplyDate(new Date());
        tflow.setType("3");
        tflow.setDetailId(detail.getId());
        tflow.setWorkpiece(projectDetail.getWorkpieceNo());
        this.getMapper(TProjectTflowMapper.class).insertSelective(tflow);
    }

    public List<TProjectTflow> selectByExample(TProjectTflowExample tflowExample) {
        return this.getMapper(TProjectTflowMapper.class).selectByExample(tflowExample);
    }
}