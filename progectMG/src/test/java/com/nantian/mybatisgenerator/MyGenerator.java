package com.nantian.mybatisgenerator;

import org.junit.Test;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * Mybatis 生成代码所用的类,在 src/main/resources/generatorConfig.xml 中配置需要生成Mybatis代码的表和生成代码所在的包
 * Created by lixinquan
 */
public class MyGenerator {

	@Test
	public void generatorCode() throws Exception {

	        List<String> warnings = new ArrayList<String>();
	        final boolean overwrite = true;
	        File configFile = new File("src/main/resources/generatorConfig.xml");
			//System.out.println("config fiel is in : " + configFile.getAbsoluteFile());
	        ConfigurationParser cp = new ConfigurationParser(warnings);
	        Configuration configuration = cp.parseConfiguration(configFile);
	        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
	        MyBatisGenerator mybatisGenerator = new MyBatisGenerator(configuration, callback, warnings);
	        mybatisGenerator.generate(null);
	}

}
