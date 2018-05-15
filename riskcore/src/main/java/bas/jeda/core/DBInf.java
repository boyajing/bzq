/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.core;

import org.mybatis.spring.SqlSessionTemplate;

/**
 *
 * @author menghui
 */
public interface DBInf {

    /**
     * @param sqlSession the sqlSession to set
     */
    void setSqlSession(SqlSessionTemplate sqlSession);
    
}
