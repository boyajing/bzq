/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.core;

import bas.jeda.dao.SLog;
import bas.jeda.dao.SLogExample;
import bas.jeda.dao.SLogMapper;
import java.util.List;
import java.util.UUID;

/**
 *日志
 * @author zhixiangfei
 */

public class SLogService extends AbstrctDBInf{
   
   
     

    public SLogMapper getsLogMapper() {
        return   this.getMapper(SLogMapper.class);
    }

     
     public <T extends Object> T getMapper(Class<T> type) {
        return this.sqlSession.getMapper(type);
    } 
    
    public List<SLog> getList(SLogExample se){
        SLogMapper sLogMapper = this.getMapper(SLogMapper.class);
        return sLogMapper.selectByExample(se);
    }
    
    public void addSLog(SLog sLog){
        SLogMapper sLogMapper = this.getMapper(SLogMapper.class);
        sLog.setLogid(UUID.randomUUID().toString());
        sLogMapper.insert(sLog);
    }

    public List<SLog> querylogbyexample(SLogExample example){return getMapper(SLogMapper.class).selectByExample(example); }
}
