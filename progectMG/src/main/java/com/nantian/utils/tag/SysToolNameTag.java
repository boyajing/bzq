package com.nantian.utils.tag;

import com.byj.dao.TTool;
import com.byj.dao.TToolMapper;
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
 * Created by Administrator on 2018/5/26 0026.
 */
    public class SysToolNameTag extends BodyTagSupport {
    private static ApplicationContext ctx = null;
    private String toolno;
    private StringBuffer sb;
    private PageContext pageContext;

    @Override
    public void setPageContext(PageContext pageContext) {
        this.pageContext = pageContext;
    }

    public String getToolno() {
        return toolno;
    }

    public void setToolno(String toolno) {
        this.toolno = toolno;
    }

    public int doStartTag() throws JspException {
        if ((this.toolno == null) || (this.toolno.trim().length() == 0)) {
            sb = new StringBuffer();
            return SKIP_BODY;
        }
        try {

            if (ctx == null)
                ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext.getServletContext());
            SqlSessionTemplate sqlSession = (SqlSessionTemplate) ctx.getBean("sqlSession");

            TToolMapper toolMapper = sqlSession.getMapper(TToolMapper.class);
            String[] split = this.toolno.split(",");
            sb = new StringBuffer();
            for(int i = 0 ;i< split.length ;i++){
                TTool tool = toolMapper.selectByPrimaryKey(split[i]);
                if(tool != null){
                    if(i < split.length-1){
                        sb.append(tool.getToolName() +",");
                    }else{
                        sb.append(tool.getToolName());
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
        toolno = null;

    }

    }
