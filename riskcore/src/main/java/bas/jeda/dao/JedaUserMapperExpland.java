package bas.jeda.dao;



public interface JedaUserMapperExpland {

    //根据userid查询 用户名字、机构
    JedaUserExpland selectUserByUserId(String userid);
}