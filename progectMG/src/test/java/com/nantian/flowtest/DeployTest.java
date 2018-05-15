package com.nantian.flowtest;

import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by NT on 2017/4/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:activiti-deploy.cfg.xml"})
//@ContextConfiguration(locations ={"classpath:app.xml","classpath:mvc.xml"})
public class DeployTest {

    @Autowired
    ProcessEngineFactoryBean processEngine ;

//    /**
//     * 立项流程发布
//     * @throws Exception
//     */
//    @Test
//    public void deploy1() throws Exception {
//        DeploymentBuilder db = processEngine.getObject().getRepositoryService().createDeployment();
//        db.addClasspathResource("flow/project1.bpmn");
//        db.name("project").deploy();
//        System.out.print("=========================");
//    }

}

