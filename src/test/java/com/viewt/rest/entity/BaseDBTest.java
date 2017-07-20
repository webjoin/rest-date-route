package com.viewt.rest.entity;

import org.junit.After;
import org.junit.Before;
import org.mybatis.generator.api.ShellRunner;

import java.io.File;

/**
 * Created by Elijah on 17/7/2017.
 */
public class BaseDBTest {

    @After
    public void before() {
        String path = Thread.currentThread().getContextClassLoader().getResource(".").getPath();
        System.out.println(path);
        File file = new File(path + "generatorConfig.xml");
        String[] args1 = {"-configfile", file.getAbsolutePath(), "-overwrite"};
        ShellRunner.main(args1);
    }

    @Before
    public void after(){
        System.out.println("before start...!");
    }
}
