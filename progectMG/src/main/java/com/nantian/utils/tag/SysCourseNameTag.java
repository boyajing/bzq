package com.nantian.utils.tag;

import com.nantian.dao.TCourse;
import com.nantian.dao.TCourseMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * 根据用户id 返回用户名
 * Created by NT on 2017/3/31.
 */
public class SysCourseNameTag extends BodyTagSupport {

    private static ApplicationContext ctx = null;
    private String courseid;
    private StringBuffer sb;
    private PageContext pageContext;

    @Override
    public void setPageContext(PageContext pageContext) {
        this.pageContext = pageContext;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public int doStartTag() throws JspException {
        if ((this.courseid == null) || (this.courseid.trim().length() == 0)) {
            sb = new StringBuffer();
            return SKIP_BODY;
        }
        try {

            if (ctx == null)
                ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext.getServletContext());
            SqlSessionTemplate sqlSession = (SqlSessionTemplate) ctx.getBean("sqlSession");

            TCourseMapper tCourseMapper = sqlSession.getMapper(TCourseMapper.class);
            String[] split = this.courseid.split(",");
            sb = new StringBuffer();
            for(int i = 0 ;i< split.length ;i++){
                TCourse tCourse = tCourseMapper.selectByPrimaryKey(split[i]);
                if(tCourse != null){
                    if(i < split.length-1){
                        sb.append(tCourse.getCourseName() +",");
                    }else{
                        sb.append(tCourse.getCourseName());
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }

    public int doEndTag() throws JspException {
        try {
            if(sb != null){
                JspWriter out = pageContext.getOut();
                out.print(sb.toString());
            }
        } catch (Exception e) {
            throw new JspException(e);
        }
        return EVAL_PAGE;
    }

    /**
     * Release any acquired resources.
     */
    public void release() {

        super.release();
        courseid = null;

    }

}
