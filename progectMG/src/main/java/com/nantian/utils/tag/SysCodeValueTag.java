package com.nantian.utils.tag;

import com.nantian.dao.TSysCode;
import com.nantian.dao.TSysCodeExample;
import com.nantian.dao.TSysCodeExample.Criteria;
import com.nantian.dao.TSysCodeMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据字典列表，根据代码值回显名称
 * Created by NT on 2017/3/31.
 */
public class SysCodeValueTag extends BodyTagSupport {

    private static ApplicationContext ctx           = null;
    private String                    ctype;
    private String                    index;
    private StringBuffer              sb   ;
    private PageContext pageContext;
    @Override
    public void setPageContext(PageContext pageContext) {
        this.pageContext=pageContext;
    }



    /**
     * @return Returns the ctype.
     */
    public String getCtype() {
        return ctype;
    }

    /**
     * @param ctype The ctype to set.
     */
    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public int doStartTag() throws JspException {
        if ((this.ctype == null) || (this.ctype.trim().length() == 0)) {
            return SKIP_BODY;
        }
        try {
            ServletContext context = pageContext.getSession().getServletContext();
            Map<String,Object> syscode =(Map<String,Object>) context.getAttribute("sys_code_list");
           // Map<String,Object> syscode = (Map<String,Object>)session.getAttribute("sys_code_list");
            if(syscode == null){
                syscode = new HashMap<String, Object>();
                context.setAttribute("sys_code_list",syscode);
            }
            List<TSysCode> tSysCodes= (List<TSysCode>) syscode.get(ctype);
                if(tSysCodes ==null){
                    if (ctx == null)
                        ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext.getServletContext());
                    SqlSessionTemplate sqlSession = (SqlSessionTemplate) ctx.getBean("sqlSession");

                    TSysCodeMapper codeMapper = sqlSession.getMapper(TSysCodeMapper.class);

                    TSysCodeExample example= new TSysCodeExample();
                    Criteria criteria = example.createCriteria().andCodeTypeEqualTo(ctype);
                    tSysCodes = codeMapper.selectByExample(example);
                    syscode.put(ctype,tSysCodes);
                }
            sb = new StringBuffer();

            for (TSysCode tSysCode: tSysCodes) {
                        if(tSysCode.getCodeValue().equals(index) ){
                            sb.append(tSysCode.getCodeName());
                        }
                    }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }

    public int doEndTag() throws JspException {
        try {
            if(sb!= null){

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
        ctype = null;
        index = null;

    }

}
