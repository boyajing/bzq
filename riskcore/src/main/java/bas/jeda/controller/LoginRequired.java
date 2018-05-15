/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 用户标明需要登录的服务方法
 *
 */
@Target({ElementType.METHOD})
public @interface LoginRequired {
    
}
