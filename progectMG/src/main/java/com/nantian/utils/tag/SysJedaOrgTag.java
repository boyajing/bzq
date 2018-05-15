package com.nantian.utils.tag;

import bas.jeda.dao.JedaOrg;
import bas.jeda.dao.JedaOrgMapper;
import bas.jeda.dao.JedaUser;
import bas.jeda.dao.JedaUserMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.util.HashMap;
import java.util.Map;

/**
 * 根据机构代码获取机构名称
 * Created by NT on 2017/3/31.
 */
public class SysJedaOrgTag extends BodyTagSupport {

    private static ApplicationContext ctx = null;
    private String orgid;
    private StringBuffer sb;
    private PageContext pageContext;

    @Override
    public void setPageContext(PageContext pageContext) {
        this.pageContext = pageContext;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public int doStartTag() throws JspException {
        if ((this.orgid == null) || (this.orgid.trim().length() == 0)) {
            return SKIP_BODY;
        }
        try {

            if (ctx == null)
                ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext.getServletContext());
            SqlSessionTemplate sqlSession = (SqlSessionTemplate) ctx.getBean("sqlSession");
            //从当前用户的session 中取用机构信息 避免同一登录用户使用同一标签时候，相同用户名多次查询数据库
            Map<String,JedaOrg > org_tag = (Map<String, JedaOrg>) pageContext.getSession().getAttribute("jeda_org_tag_session");
            if(org_tag ==null){
                org_tag = new HashMap<String, JedaOrg>();
                pageContext.getSession().setAttribute("jeda_org_tag_session",org_tag);
            }
            JedaOrgMapper jedaOrgMapper = sqlSession.getMapper(JedaOrgMapper.class);

            JedaOrg jedaOrg = org_tag.get(orgid) ;
            if(jedaOrg ==null){
                jedaOrg = jedaOrgMapper.selectByPrimaryKey(orgid);
                org_tag.put(orgid,jedaOrg);
            }

            sb = new StringBuffer();
            if(jedaOrg !=null){
                sb.append(jedaOrg.getOrgName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }

    public int doEndTag() throws JspException {
        try {
            if(sb != null) {
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
        orgid = null;

    }

}
