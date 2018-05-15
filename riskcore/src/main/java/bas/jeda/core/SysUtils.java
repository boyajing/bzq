package bas.jeda.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import bas.jeda.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class SysUtils {


    /**
     * 返回系统日期
     *
     * @return
     */
    public static Date getSystemDate(JedaService JedaService) {
        SSystemparamMapper mapper = JedaService.getMapper(SSystemparamMapper.class);
        SSystemparam sysaccout = mapper.selectByPrimaryKey(Contexts.sysDate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

        Date sysDate = new Date();
        try {
            sysDate = sdf2.parse(sysaccout.getParamvalue());
            //System.out.println("sysDate="+sdf2.format(sysDate));
            return sysDate;
        } catch (ParseException e1) {
            try {
                sysDate = sdf2.parse(sysaccout.getParamvalue());
                return sysDate;
//				System.out.println("sysDate = "+sdf2.format(sysDate));
            } catch (Exception e) {
                e1.printStackTrace();
                return sysDate;
            }
        }

    }

    /**
     * 返回角色集合日期
     *
     * @return
     */
    public static List<JedaRoleUserKey> getRoles(JedaService jService, String userId) {
        JedaRoleUserExample example = new JedaRoleUserExample();
        example.createCriteria().andUserIdEqualTo(userId);
        JedaRoleUserMapper mapper = jService.getMapper(JedaRoleUserMapper.class);
        List<JedaRoleUserKey> list = null;
        try {
            list = mapper.selectByExample(example);
        } catch (Exception e) {
            e.printStackTrace();
        }
        example.clear();
        return list;
    }
}
