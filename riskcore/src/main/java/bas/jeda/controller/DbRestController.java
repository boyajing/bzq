/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas.jeda.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author menghui
 */

@Controller
public class DbRestController extends JedaController {

    @RequestMapping(value = "dbrest/service/{ns}/{id}", method = RequestMethod.GET)
    public ModelAndView query(@PathVariable("ns") String ns, @PathVariable("id") String id, HttpServletRequest request) throws IOException {
        HashMap<String, String> m = new HashMap();
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            m.put(name, request.getParameter(name));
        }
        SqlSessionTemplate session = this.jService.getSqlSession();
        ModelAndView mv=new ModelAndView();
         
        if (m.isEmpty()) {
            mv.addObject("result", session.selectList(ns + "." + id));
        } else {
            mv.addObject("result", session.selectList(ns + "." + id, m));
        }
        return mv;
    }
}
