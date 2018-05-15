package com.nantian.controller.tradInstitutionPool;

import bas.jeda.controller.JedaController;
import bas.jeda.controller.LoginRequired;
import bas.jeda.dao.JedaUser;
import com.nantian.dao.TCustmorBasic;
import com.nantian.dao.TCustmorBasicExample;
import com.nantian.dao.TCustmorCredit;
import com.nantian.daoExtend.TCustmorBasicVo;
import com.nantian.service.sysmgn.TSysSequenceService;
import com.nantian.service.tradInstitutionPool.TCustmorBasicService;
import com.nantian.service.tradInstitutionPool.TCustmorCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.table.TableCellEditor;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * Created by nantian on 2017/10/24.
 */
@Controller
@RequestMapping(value = "tradInstitutionPool")
public class TradInstitutionPoolController extends JedaController {

    @Autowired
    private TCustmorBasicService tCustmorBasicService;
    @Autowired
    private TSysSequenceService tSysSequenceService;
    @Autowired
    private TCustmorCreditService tCustmorCreditService;

    @Value("#{configProperties['PAGESIZE']}")
    private int pageSize;

    /**
     * 交易机构首页（多条件查询）
     * @param tCustmorBasic
     * @param pageIndex
     * @param order
     * @param mv
     * @param request
     * @param response
     * @param currentUser
     * @return
     * @throws Exception
     */
    @LoginRequired
    @RequestMapping(value = "/index", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView indexFinish(TCustmorBasic tCustmorBasic,@RequestParam(value = "pi", required = true, defaultValue = "1") int pageIndex,
                                    @RequestParam(value = "o", required = true, defaultValue = "custmor_No desc") String order,
                                    ModelAndView mv, HttpServletRequest request,HttpServletResponse response,
                                    JedaUser currentUser)throws Exception{
        try {
            businessOperationQuery(tCustmorBasic,request,pageIndex,order,pageSize,mv,currentUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("resourcePool/tradInstitutionPool/index");
        return mv;
    }

    /**
     * 新增交易机构页面
     * @param currentUser
     * @param mv
     * @return
     */
    @LoginRequired
    @RequestMapping(value = "/createCustmor", method = RequestMethod.GET)
    public ModelAndView createCustmor(JedaUser currentUser,ModelAndView mv){
        String orgId=currentUser.getOrgId().substring(0,2);
        String id = tSysSequenceService.getPrimaryKey(TSysSequenceService.TZ_PROJECT,orgId);

        TCustmorBasic tCustmorBasic=new TCustmorBasic();
        tCustmorBasic.setCustmorNo(id);
        mv.addObject("tCustmorBasic",tCustmorBasic);
        mv.setViewName("resourcePool/tradInstitutionPool/createCustmor");
        return mv;
    }

    /**
     * 修改机构信息
     * @param currentUser
     * @param mv
     * @param custmorNo
     * @return
     */
    @LoginRequired
    @RequestMapping(value = "/updateCustmor", method = RequestMethod.GET)
    public ModelAndView updateCustmor(JedaUser currentUser,ModelAndView mv,String custmorNo){

        TCustmorBasic tCustmorBasic=tCustmorBasicService.selectByPrimaryKey(custmorNo);
        mv.addObject("tCustmorBasic",tCustmorBasic);
        mv.setViewName("resourcePool/tradInstitutionPool/createCustmor");
        return mv;
    }

    /**
     * 删除机构
     * @param currentUser
     * @param mv
     * @param custmorNo
     * @param response
     */
    @LoginRequired
    @RequestMapping(value = "/deleteCustmor", method = RequestMethod.GET)
    public void deleteCustmor(JedaUser currentUser,ModelAndView mv,String custmorNo,HttpServletResponse response){

        int result=tCustmorBasicService.deleteByPrimaryKey(custmorNo);
        returnResult(response,result);
    }

    /**
     * 返回ajax结果
     * @param response
     * @param result
     */
    private void returnResult(HttpServletResponse response,int result){
        try {
            response.getWriter().print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存交易机构
     * @param currentUser
     * @param mv
     * @param tCustmorBasicVo
     * @param response
     */
    @LoginRequired
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(JedaUser currentUser, ModelAndView mv, TCustmorBasicVo tCustmorBasicVo, HttpServletResponse response){

        List<TCustmorCredit> tCustmorCreditList= createTCustmorCredit(tCustmorBasicVo);
        tCustmorCreditService.insertCreditList(tCustmorCreditList);
        TCustmorBasic tCustmorBasic=tCustmorBasicService.createTCustmorBasic(tCustmorBasicVo);

        int result= tCustmorBasicService.insertSelective(tCustmorBasic);
        returnResult(response,0);
    }



    /**
     * 新建授信信息
     * @param tCustmorBasicVo
     * @return
     */
    private List<TCustmorCredit> createTCustmorCredit(TCustmorBasicVo tCustmorBasicVo) {
        List<TCustmorCredit> tCustmorCreditList=null;
        if(null!=tCustmorBasicVo){
            tCustmorCreditList=tCustmorBasicVo.gettCustmorCreditList();
        }
        if(!tCustmorCreditList.isEmpty()){
            tCustmorCreditList=takeOutNullList(tCustmorCreditList);
            for (int i = 0; i <tCustmorCreditList.size() ; i++) {
                tCustmorCreditList.get(i).settCustmorNo(tCustmorBasicVo.getCustmorNo());
            }
        }
        return tCustmorCreditList;
    }

    /**
     * 去掉集合中的空元素
     * @param tCustmorCreditList
     * @return
     */
    private List<TCustmorCredit> takeOutNullList(List<TCustmorCredit> tCustmorCreditList){
        Iterator<TCustmorCredit> iterator = tCustmorCreditList.iterator();
        while(iterator.hasNext()){
            TCustmorCredit credit=iterator.next();
            if(credit.getActAmt()==null&&credit.getBgDate()==null&&credit.getEndDate()==null&&credit.getPerAmt()==null){
                iterator.remove();
            }
        }
        return tCustmorCreditList;
    }

    /**
     * 交易机构多条件查询
     * @param tCustmorBasic
     * @param request
     * @param pageIndex
     * @param order
     * @param pageSize
     * @param mv
     * @param currentUser
     * @return
     * @throws Exception
     */
    @Transactional
    public ModelAndView businessOperationQuery(TCustmorBasic tCustmorBasic,HttpServletRequest request, int pageIndex, String order, int pageSize, ModelAndView mv,JedaUser currentUser)throws Exception{
        TCustmorBasicExample example = new TCustmorBasicExample();
        if (-1 == pageIndex) {
            example.clear();
            pageIndex = 0;
            example.setLimitStart(pageIndex);
        } else if (0 == pageIndex) {
            pageIndex = 0;
            example.setLimitStart(pageIndex);
        } else {
            example.setLimitStart((pageIndex - 1) * pageSize);
        }
        if (!"".equals(order))
            example.setOrderByClause(order);
        example.setLimitEnd(pageSize);
        TCustmorBasicExample.Criteria criteria = example.createCriteria();

        if (tCustmorBasic.getCustmorNo() != null&& !"".equals(tCustmorBasic.getCustmorNo() ))
            criteria.andCustmorNoLike("%" + tCustmorBasic.getCustmorNo() + "%");
        if (tCustmorBasic.getCustmorName() != null&& !"".equals(tCustmorBasic.getCustmorName() ))
            criteria.andCustmorNameLike("%" + tCustmorBasic.getCustmorName() + "%");
        if (tCustmorBasic.getCustmorType() != null&& !"".equals(tCustmorBasic.getCustmorType() ))
            criteria.andCustmorTypeEqualTo(tCustmorBasic.getCustmorType());

        List<TCustmorBasic> list = tCustmorBasicService.selectByExample(example);

        //分页
        int totalCount = tCustmorBasicService.countByExample(example);
        int pageCount = totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);
        mv.addObject("pagecount", pageCount);
        mv.addObject("pagesize", pageSize);
        mv.addObject("pageindex", pageIndex == 0 ? 1 : pageIndex);
        mv.addObject("list",list);
        return mv;
    }
}
