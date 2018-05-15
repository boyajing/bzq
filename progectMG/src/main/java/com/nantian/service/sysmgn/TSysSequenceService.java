package com.nantian.service.sysmgn;

import bas.jeda.core.AbstrctDBInf;
import com.nantian.daoExtend.IDSequenceMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 获取业务主键的相关类
 */
@Service("tSysSequenceService")
public class TSysSequenceService extends AbstrctDBInf {
    @Autowired
    public SqlSessionTemplate sqlSession;

    /**
     * 投资项目
     */
    public static final String TZ_PROJECT = "T";
    /**
     * 客户
     */
    public static final String CUSTOMER = "KH";

    /**
     * 交易流水号
     */
    public static final String TRANSACTION = "J";

    /**
     * 单据编号
     */
    public static final String RECEIPTS = "D";

    /**
     * 递增主键
     */
    public static final String WYID = "WYID";


    //@Transactional(readOnly = true)
    public  String  getPrimaryKey(String type,String orgId){
        int i = 0;
        IDSequenceMapper mapper = this.sqlSession.getMapper(IDSequenceMapper.class);
        if(TZ_PROJECT.equals(type)){//项目
            i = mapper.querySEQPROJECT();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//            sdf.format(new Date());
            return  type + orgId +sdf.format(new Date())  +getKey(i + "" ,4);
        }else if(CUSTOMER.equals(type) ){//客户
            i = mapper.querySEQCUSTOMER();
            return  type + orgId +getKey(i + "" ,8);
        } else if(TRANSACTION.equals(type)){//交易流水号
            i = mapper.querySEQTRANSACTION();
            return  type + getKey(i + "" ,11);
        }else if(RECEIPTS.equals(type)){//单据编号
            i = mapper.querySEQRECEIPTS();
            return  type + getKey(i + "" ,11);
        }else if(WYID.equals(type)){//递增主键
            i = mapper.querySEQWYID();
            return  getKey(i + "" ,10);
        }
        if(i == 0){
            return null;
        }
        return  type + orgId+getKey(i + "" ,10);
    }

    /**
     *
     * @param value 数据长度不够前面补零的方法
     * @return 返回补零后lenth 长度的字符串
     */
    private String getKey(String value,int lenth){
        if (value.length() == lenth){
            return value;
        }
        return getKey("0"+value, lenth);
    }


}
