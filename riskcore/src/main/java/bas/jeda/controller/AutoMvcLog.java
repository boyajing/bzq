/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
/**
 * 自动日志记录
 * 使用切面自动记录Controller的操作，在Controller的方法上添加
 * AutoMvcLog注解
 * 激活自动日志记录，自动日志依赖AutoLogAspect类中的切面
 * public Object AutoLog(ProceedingJoinPoint jp) throws Exception 
 * 实现。
 * 
 * 记录的内容包括：
 *  当前用户信息，前提是Coltroller必须派生自JedaController类
 *  当前Request的地址、参数、方法
 *  当前方法传入的参数，只记录简单参数（数字、字符、字符串）
 *  当前方法返回的运行结果
 *      识别Controller返回值为String、Model、Map、ModelMap、View、ModelAndView这几种。
 *  日志记录格式为:
 [15/03/16 17:42:04:341] #2192518d0a241802f4f405f9790dcaba:user:admin	bas.bondanalysesys.jeda.UserController	orgTree	
 [15/03/16 17:42:04:342] #472fc3c4ea04941d82f43560c9d245b8:user:admin	bas.bondanalysesys.jeda.UserController	orgTree	
 [15/03/16 17:42:04:353] #472fc3c4ea04941d82f43560c9d245b8:Return view:jeda/user/index	Return Map:{}
 [15/03/16 17:42:04:354] #2192518d0a241802f4f405f9790dcaba:Return view:jeda/user/index	Return Map:{}
 * 
 * 上面#2192518d0a241802f4f405f9790dcaba:中#：之间的编码为请求ID用于标识一个请求，先出现的是Request后出现的是response
 * user:为操作的用户ID
 * bas.bondanalysesys.jeda.UserController	orgTree分别是controller的名字和方法
 * Return view:jeda/user/index	Return Map:{}
 * 分别是返回的视图名称和返回的数据，如果识别到数据格式则自动转换为json字符串。
 * 
 * 自动日志记录在jedalogger日志文件中。
 */

@Target({ElementType.METHOD})
public @interface AutoMvcLog {
    
}
