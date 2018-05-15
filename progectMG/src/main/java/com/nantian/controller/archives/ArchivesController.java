package com.nantian.controller.archives;

import bas.jeda.controller.JedaController;
import bas.jeda.controller.LoginRequired;
import bas.jeda.dao.JedaUser;
import com.nantian.dao.*;
import com.nantian.service.Archives.ArchivesService;
//import com.nantian.service.ProjectMemberService;
//import com.nantian.service.ProjectService;
//import com.nantian.service.ProjectVPStatusService;
//import com.nantian.service.feedback.FeedBackService;
//import com.nantian.service.programme.ProgrammeService;
import com.nantian.utils.Constants;
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
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "archives")
public class ArchivesController extends JedaController {
    @Autowired
    ArchivesService archivesService;

//    @Autowired
//    private ProjectService projectService;
//
//    @Autowired
//    private ProjectVPStatusService vrojectVPStatusService;
//
//    @Autowired
//    private ProgrammeService programmeService;
//
//    @Autowired
//    private ProjectMemberService projectMemberService;
//    @Autowired
//    FeedBackService feedBackService;
    @Value("#{configProperties['PAGESIZE']}")
    private int pageSize;



    /**
     * 项目审批完成列表查询
     * @param request
     * @param mv
     * @param response
     * @return
     * @throws Exception
     */
//    @LoginRequired
//    @RequestMapping(value = "/index", method = RequestMethod.GET)
//    public ModelAndView indexFinish(HttpServletRequest request, ModelAndView mv,HttpServletResponse response,JedaUser currentUser,
//                                    @RequestParam(value = "pi", required = true, defaultValue = "-1") int pageIndex,VProjectProjectstatus vobj,
//                                    @RequestParam(value = "o", required = true, defaultValue = "cteate_time desc") String order){
//        try {
//            //查询
//            businessFinish(vobj, request, pageIndex, order, pageSize, mv,currentUser);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        mv.setViewName("archives/index");
//        return mv;
//    }
    /**
     * 项目审批完成列表条件查询
     * @param request
     * @param mv
     * @param response
     * @return
     * @throws Exception
     */

//    @LoginRequired
//    @RequestMapping(value = "/index", method = RequestMethod.POST)
//    public ModelAndView indexFinishPost(HttpServletRequest request, ModelAndView mv,HttpServletResponse response,JedaUser currentUser,
//                                        @RequestParam(value = "pi", required = true, defaultValue = "-1") int pageIndex,VProjectProjectstatus vobj,
//                                        @RequestParam(value = "o", required = true, defaultValue = "cteate_time desc") String order){
//        try {
//            businessFinish(vobj, request, pageIndex, order, pageSize, mv,currentUser);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        mv.setViewName("archives/index");
//        return mv;
//    }


    //项目审批完成列表查询
    //@Transactional
//    public ModelAndView businessFinish(VProjectProjectstatus vobj,HttpServletRequest request,int pageIndex,String order,int pageSize,ModelAndView mv,JedaUser currentUser)throws Exception{
//        VProjectProjectstatusExample example = Constants.getSessionObject(VProjectProjectstatusExample.class, request.getSession(true), "VProjectProjectstatusExample", true);
//        if (-1 == pageIndex) {
//            example.clear();
//            pageIndex = 0;
//            example.setLimitStart(pageIndex);
//        } else if (0 == pageIndex) {
//            pageIndex = 0;
//            example.setLimitStart(pageIndex);
//        } else {
//            example.setLimitStart((pageIndex - 1) * pageSize);
//        }
//        if (!"".equals(order))
//            example.setOrderByClause(order);
//        example.setLimitEnd(pageSize);
//
//
//        TProjectMemberExample example9 =new TProjectMemberExample();
//        example9.createCriteria().andUserIdEqualTo(currentUser.getUserId());
//        List<TProjectMember> list9 = projectMemberService.queryList9(example9);//项目组成员
//        List list1 = new ArrayList();
//        for(int i=0;i<list9.size();i++){
//            String procode = list9.get(i).getProjectCode();
//            list1.add(procode);
//
//        }
//
//        TProjectExample projectexample =new TProjectExample();
//        projectexample.createCriteria().andCreaterEqualTo(currentUser.getUserId());
//        List<TProject> listproj = projectService.query(projectexample);//项目组成员
//        for(int i=0;i<listproj.size();i++){
//            String procode = listproj.get(i).getProjectCode();
//            list1.add(procode);
//
//        }
//
//        VProjectProjectstatusExample.Criteria criteria = example.createCriteria();
//        List list2 = new ArrayList();
//        list2.add("1");
//        list2.add("2");
//        criteria.andProjectStatusIn(list2);
//
//
//        criteria.andProjectCodeIn(list1);
//
//        if (vobj.getProjectCode() != null&& !"".equals(vobj.getProjectCode() ))
//            criteria.andProjectCodeLike("%" + vobj.getProjectCode() + "%");
//        if (vobj.getProjectName() != null&& !"".equals(vobj.getProjectName()))
//            criteria.andProjectNameLike("%" + vobj.getProjectName() + "%");
//        criteria.andProjectStatusEqualTo("1") ;
//        List<VProjectProjectstatus> list = vrojectVPStatusService.query(example);
//        //分页
//        int totalCount = vrojectVPStatusService.count(example);
//        int pageCount = totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);
//        mv.addObject("pagecount", pageCount);
//        mv.addObject("pagesize", pageSize);
//        mv.addObject("pageindex", pageIndex == 0 ? 1 : pageIndex);
//        mv.addObject("list",list);
//        return mv;
//    }


    /**
     * 文件管理---下载附件
     * @param request
     * @param response
     * @param id  附件表主键
     */
    @LoginRequired
    @RequestMapping(value = "/fileload", method = RequestMethod.GET)
    public void fileload( HttpServletRequest request,HttpServletResponse response,
                          @RequestParam(value = "id", required = true, defaultValue = "") String id) {
        try{
            fileload(id,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //文件管理---下载附件
    @Transactional
    public void fileload(String id,HttpServletResponse response)throws Exception{
        FileInputStream fis=null;
        OutputStream os=null;
        byte []buffer=new byte[1024];
        int len=0;
        response.setContentType("application/octet-stream;charset=UTF-8");
        TArchives tArchives = archivesService.query(id);
        String filename=tArchives.getFileName();//文件标题
        String filterFilename = new String(filename.getBytes("GB2312"), "ISO_8859_1");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + filterFilename +"\"");
        String filePath=tArchives.getFilePath();//保存地址
        String fileAllPath=filePath+"/"+filename;
        fis=new FileInputStream(fileAllPath);
        os=response.getOutputStream();
        while((len=fis.read(buffer))>0){
            os.write(buffer, 0, len);
        }
        fis.close();
        os.close();
    }


}
