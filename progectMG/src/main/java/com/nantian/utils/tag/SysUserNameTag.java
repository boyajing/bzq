package com.nantian.utils.tag;

import bas.jeda.dao.JedaUser;
import bas.jeda.dao.JedaUserMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.util.HashMap;
import java.util.Map;

/**
 * 根据用户id 返回用户名
 * Created by NT on 2017/3/31.
 */
public class SysUserNameTag extends BodyTagSupport {

    private static ApplicationContext ctx = null;
    private String userid;
    private StringBuffer sb;
    private PageContext pageContext;

    @Override
    public void setPageContext(PageContext pageContext) {
        this.pageContext = pageContext;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int doStartTag() throws JspException {
        if ((this.userid == null) || (this.userid.trim().length() == 0)) {
            sb = new StringBuffer();
            return SKIP_BODY;
        }
        try {

            if (ctx == null)
                ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext.getServletContext());
            SqlSessionTemplate sqlSession = (SqlSessionTemplate) ctx.getBean("sqlSession");
            //从当前用户的session 中取用户名信息 避免同一登录用户使用同一标签时候，相同用户名多次查询数据库
            Map<String,JedaUser > user_tag = (Map<String, JedaUser>) pageContext.getSession().getAttribute("user_tag_session");
            if(user_tag ==null){
                user_tag = new HashMap<String, JedaUser>();
                pageContext.getSession().setAttribute("user_tag_session",user_tag);
            }
            JedaUserMapper jedaUserMapper = sqlSession.getMapper(JedaUserMapper.class);
            String[] split = this.userid.split(",");
            sb = new StringBuffer();
            for(int i = 0 ;i< split.length ;i++){

                JedaUser jedaUser= user_tag.get(split[i]);
                if(jedaUser ==null){
                    jedaUser = jedaUserMapper.selectByPrimaryKey(split[i]);
                    user_tag.put(split[i],jedaUser);
                }

                if(jedaUser != null){
                    if(i < split.length-1){
                        sb.append(jedaUser.getUserName() +",");
                    }else{
                        sb.append(jedaUser.getUserName());
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
        userid = null;

    }

}
