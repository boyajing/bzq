package com.nantian.utils.tag;

import com.nantian.dao.TCustomerBasic;
import com.nantian.dao.TCustomerBasicMapper;
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
public class SysCustomerNameTag extends BodyTagSupport {

    private static ApplicationContext ctx = null;
    private String cuscomerid;
    private StringBuffer sb;
    private PageContext pageContext;

    @Override
    public void setPageContext(PageContext pageContext) {
        this.pageContext = pageContext;
    }

    public String getCuscomerid() {
        return cuscomerid;
    }

    public void setCuscomerid(String userid) {
        this.cuscomerid = userid;
    }

    public int doStartTag() throws JspException {
        if ((this.cuscomerid == null) || (this.cuscomerid.trim().length() == 0)) {
            sb = new StringBuffer();
            return SKIP_BODY;
        }
        try {

            if (ctx == null)
                ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext.getServletContext());
            SqlSessionTemplate sqlSession = (SqlSessionTemplate) ctx.getBean("sqlSession");

            TCustomerBasicMapper tCustomerBasicMapper = sqlSession.getMapper(TCustomerBasicMapper.class);
            String[] split = this.cuscomerid.split(",");
            sb = new StringBuffer();
            for(int i = 0 ;i< split.length ;i++){
                TCustomerBasic tCustomerBasic = tCustomerBasicMapper.selectByPrimaryKey(split[i]);
                if(tCustomerBasic != null){
                    if(i < split.length-1){
                        sb.append(tCustomerBasic.getCustomerName() +",");
                    }else{
                        sb.append(tCustomerBasic.getCustomerName());
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
        cuscomerid = null;

    }

}
