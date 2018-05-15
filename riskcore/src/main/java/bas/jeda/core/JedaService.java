/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.core;

import bas.jeda.dao.JedaMenu;
import bas.jeda.dao.JedaRoleUserExample;
import bas.jeda.dao.JedaRoleUserKey;
import bas.jeda.dao.JedaRoleUserMapper;
import bas.jeda.dao.JedaUser;
import bas.jeda.dao.JedaSequences;
import bas.jeda.dao.JedaSequencesMapper;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author menghui
 */
public class JedaService extends AbstrctDBInf {

    public class MenuItem  {
        private JedaMenu menuitem;
        private List<MenuItem> subItem;
        
        public MenuItem(){
            
        }
        public MenuItem(JedaMenu ju){
            this.menuitem=ju;
        }
        /**
         * @return the menuitem
         */
        public JedaMenu getMenuitem() {
            return menuitem;
        }

        /**
         * @param menuitem the menuitem to set
         */
        public void setMenuitem(JedaMenu menuitem) {
            this.menuitem = menuitem;
        }

        /**
         * @return the subItem
         */
        public List<MenuItem> getSubItem() {
            return subItem;
        }

        /**
         * @param subItem the subItem to set
         */
        public void setSubItem(List<MenuItem> subItem) {
            this.subItem = subItem;
        }
        
    }

    public <T extends Object> T getMapper(Class<T> type) {
        return this.getSqlSession().getMapper(type);
    }

    public List<MenuItem> getMenuByUser(final String userid, final String parent) {
        //bas.bondanalysesys.core.jeda.sqlmap.queryMenuByUserList<JedaMenu> 
        //在JedaOtherMapper.xml中
        List<JedaMenu> menus=this.getSqlSession().selectList("bas.jeda.dao.sqlmap.queryMenuByUser", new HashMap<String, Object>() {
            {
                put("userid", userid);
                put("parent", parent);
            }
        });
        if(menus.isEmpty()){
            return null;//new ArrayList<MenuItem>();
        }
        List<MenuItem> items=new ArrayList<MenuItem>(menus.size());
        for(int i=0;i<menus.size();i++){
            MenuItem m=new MenuItem(menus.get(i));
            m.setSubItem(this.getMenuByUser(userid,m.getMenuitem().getMenuId()));
            items.add(m);
        }
        return items;
    }
    /**
     * 返回用户菜单中的前两级
     * @param userid
     * @return 
     */
    public List<MenuItem> gettop2LeveMenuByUser(final String userid){
        List<MenuItem> top=this.getMenuByUser(userid, null);
        
        return top;
    }
     
    public void updateroleuser(String [] roles,String [] users){
        JedaRoleUserMapper jmm = this.getMapper(JedaRoleUserMapper.class);
        
        JedaRoleUserExample exp=new JedaRoleUserExample();
         for(int i=0;i<users.length;i++){
            exp.clear();
            exp.createCriteria().andUserIdEqualTo(users[i]);
            jmm.deleteByExample(exp);
            if(roles.length> 0 && !"".equals(roles[0])){
                for(int j=0;j<roles.length;j++){
                    JedaRoleUserKey jru=new JedaRoleUserKey();
                    jru.setRoleId(roles[j]);
                    jru.setUserId(users[i]);
                    jmm.insert(jru);
                }
            }

        }
    }
    public List<JedaUser> getRoleUser(final String role){
        return this.sqlSession.selectList("bas.jeda.dao.sqlmap.queryUsersByRole",new HashMap<String, Object>() {
            {
                put("roleid", role);
            }});
    }
    public String newId(String idTag) {
        JedaSequencesMapper jsm = getMapper(JedaSequencesMapper.class);
        JedaSequences js = jsm.selectByPrimaryKey(idTag);
        js.setNextVal(js.getNextVal().add(BigDecimal.ONE));
        jsm.updateByPrimaryKeySelective(js);
        return js.getNextVal().toString();
    }

    @Transactional(readOnly = true)
    public List<Map<String, String>> getAllMenubyPId(String menuid){
        List<Map<String, String>> list = this.getMenubyPId(menuid);
        List<Map<String, String>> relist = new ArrayList<Map<String, String>>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, String> map =  list.get(i);
            Map<String, String> remap =  new HashMap<String, String>();

            remap.put("id","_"+map.get("MENU_ID"));
            remap.put("name",map.get("MENU_NAME"));
            remap.put("pId",map.get("PARENT_MENU_ID"));
            remap.put("url",map.get("MENU_URL"));
            //{ id:3, pId:0, name:"zTree in Iteye", url:"http://ztreeapi.iteye.com/", target:"_blank"},
//            { id:4, pId:0, name:"Nothing...", url:"", target:"_blank", click:"alert('我是不会跳转的...');"}

            remap.put("target","_blank");
            remap.put("click","return menu_click(this);");

            relist.add(remap);
        }

        return relist;
    }
    @Transactional(readOnly = true)
    public List<Map<String, String>> getMenubyPId(String menuid){
        List<Map<String,String>> list = new ArrayList<Map<String, String>>();
        List<Map<String, String>> returnlist = new ArrayList<Map<String, String>>();
        Map<String ,String> map = new HashMap();
        map.put("pId","_"+menuid);
        list = this.sqlSession.selectList("bas.jeda.dao.sqlmap.getMenubyPId",map);
        if(list == null){
            return returnlist;
        }else {
            returnlist.addAll(list);
            for (int i = 0; i < list.size(); i++) {
                Map<String, String> map1 =  list.get(i);
                if(!"0".equals(map.get("COUNT")) ){
                    List<Map<String, String>> menu_id = this.getMenubyPId(map1.get("MENU_ID"));
                    returnlist.addAll(menu_id);
                }
            }
        }

        return returnlist;
    }


}
