package com.nantian.service.sysmgn;

import bas.jeda.core.AbstrctDBInf;
import com.nantian.dao.*;
import com.nantian.daoExtend.Sequence;
import com.nantian.daoExtend.SequenceMapper;

import java.util.List;

public class CommonService extends AbstrctDBInf {

    private <T extends Object> T getMapper(Class<T> type) {
        return this.sqlSession.getMapper(type);
    }

    public List<D9007sys_code> dropDownList(String code) {
        D9007sys_codeExample example = new D9007sys_codeExample();
        example.setOrderByClause("f9007id");
        D9007sys_codeExample.Criteria criteria = example.createCriteria();
        criteria.andF9007dmEqualTo(code);
        List<D9007sys_code> list = getMapper(D9007sys_codeMapper.class).selectByExample(example);
        example.clear();
        D9007sys_code sCode = new D9007sys_code();
        sCode.setF9007id("");
        sCode.setF9007dmmc("请选择");
        sCode.setF9007dm(code);
        list.add(0, sCode);
        return list;
    }

    public List<D9007sys_code> dropDownList(String code, String tag, String type) {
        D9007sys_codeExample example = new D9007sys_codeExample();
        example.setOrderByClause("f9007id");
        D9007sys_codeExample.Criteria criteria = example.createCriteria();
        criteria.andF9007dmEqualTo(code);
        if ("0".equals(type)) {
            criteria.andF9007idLike("%" + tag);
        }
        if ("1".equals(type)) {
            criteria.andF9007idLike(tag + "%");
            criteria.andF9007idLike("%00");
            criteria.andF9007idNotLike("%0000");
        }
        if ("2".equals(type)) {
            criteria.andF9007idLike(tag + "%");
            criteria.andF9007idNotLike("%00");
        }
        List<D9007sys_code> list = getMapper(D9007sys_codeMapper.class).selectByExample(example);
        example.clear();
        D9007sys_code sCode = new D9007sys_code();
        sCode.setF9007id("");
        sCode.setF9007dmmc("请选择");
        sCode.setF9007dm(code);
        list.add(0, sCode);
        return list;
    }

    //根据dm与id查询中文
    public String  querymmc(String dm,String id) {
        if(dm==null||id==null)
            return null;
        D9007sys_codeExample example = new D9007sys_codeExample();
        D9007sys_codeExample.Criteria criteria = example.createCriteria();
        criteria.andF9007dmEqualTo(dm);
        criteria.andF9007idEqualTo(id);
        List<D9007sys_code> list = getMapper(D9007sys_codeMapper.class).selectByExample(example);
        if(list.size()==0){
            return null;
        }else {
            return list.get(0).getF9007dmmc();
        }
    }
    //根据客户编号与当前用户编号查询权限 1:显示 2:隐藏
//    public String authority(String zcbh,String userid){
//        String obj = "2";
//        D9009qxExample example = new D9009qxExample();
//        D9009qxExample.Criteria criteria = example.createCriteria();
//        criteria.andF9009yhbhEqualTo(userid);
//        criteria.andF9009zcbhEqualTo(zcbh);
//        criteria.andF9009sfyxEqualTo("1");
//        List<D9009qx> list = getMapper(D9009qxMapper.class).selectByExample(example);
//        if(list.size()!=0)
//            obj = "1";
//        return obj;
//    }
}
