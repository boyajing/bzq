/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.core;

import bas.jeda.dao.SSystemparam;
import bas.jeda.dao.SSystemparamMapper;
import org.mybatis.spring.SqlSessionTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 需要访问数据库的类的基类
 * @author 
 */
public abstract class AbstrctDBInf implements DBInf {

    protected SqlSessionTemplate sqlSession;

    /**
     * @param sqlSession the sqlSession to set
     */
    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    /**
     * @return the sqlSession
     */
    public SqlSessionTemplate getSqlSession() {
        return sqlSession;
    }
    /**
     * 
     * @return 返回系统时间的方法 数据库日期加当前系统时间
     */
    public Date getSysDatetime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        SSystemparamMapper sysmapper = getSqlSession().getMapper(SSystemparamMapper.class);
        SSystemparam ss = sysmapper.selectByPrimaryKey("sysDate");
        String ddate = sdf.format(new Date()).substring(8);
        String parm = ss.getParamvalue().replace("-", "") + ddate;
        Date sysdatetime =null;
        try {
             sysdatetime = sdf.parse(parm);
        } catch (ParseException ex) {
            Logger.getLogger(AbstrctDBInf.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sysdatetime;
    }


}
