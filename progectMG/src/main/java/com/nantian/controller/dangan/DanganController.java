package com.nantian.controller.dangan;

import bas.jeda.controller.JedaController;
import bas.jeda.controller.LoginRequired;
import bas.jeda.dao.JedaUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by nantian on 2017/10/24.
 */
@Controller
@RequestMapping(value = "newDangan")
public class DanganController extends JedaController {

    @LoginRequired
    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public ModelAndView indexFinishPost(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("dangan/index");
        return mv;
    }

}
