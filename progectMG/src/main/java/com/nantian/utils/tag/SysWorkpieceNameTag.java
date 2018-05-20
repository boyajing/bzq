package com.nantian.utils.tag;

import com.byj.dao.TWorkpiece;
import com.byj.dao.TWorkpieceMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * Created by Administrator on 2018/5/19 0019.
 */
public class SysWorkpieceNameTag extends BodyTagSupport {
    private static ApplicationContext ctx = null;
    private String workpieceno;
    private StringBuffer sb;
    private PageContext pageContext;

    @Override
    public void setPageContext(PageContext pageContext) {
        this.pageContext = pageContext;
    }

    public String getWorkpieceno() {
        return workpieceno;
    }

    public void setWorkpieceno(String workpieceno) {
        this.workpieceno = workpieceno;
    }

    public int doStartTag() throws JspException {
        if ((this.workpieceno == null) || (this.workpieceno.trim().length() == 0)) {
            sb = new StringBuffer();
            return SKIP_BODY;
        }
        try {

            if (ctx == null)
                ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext.getServletContext());
            SqlSessionTemplate sqlSession = (SqlSessionTemplate) ctx.getBean("sqlSession");

            TWorkpieceMapper workpieceMapper = sqlSession.getMapper(TWorkpieceMapper.class);
            String[] split = this.workpieceno.split(",");
            sb = new StringBuffer();
            for(int i = 0 ;i< split.length ;i++){
                TWorkpiece workpiece = workpieceMapper.selectByPrimaryKey(split[i]);
                if(workpiece != null){
                    if(i < split.length-1){
                        sb.append(workpiece.getWorkpieceName() +",");
                    }else{
                        sb.append(workpiece.getWorkpieceName());
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
        workpieceno = null;

    }
}
