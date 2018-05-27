package com.byj.daoExtend;

import bas.jeda.dao.JedaUserExample;

import java.util.List;

/**
 * Created by Administrator on 2018/5/24 0024.
 */
public interface UserVoMapper {
    UserVo selectByPrimaryKey(String userId);
    List<UserVo> selectByExample(JedaUserExample example);
}
