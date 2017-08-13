package com.viewt.rest.entity;

import org.junit.Test;

/**
 * Created by Elijah on 29/6/2017.
 */
public class GeneratePostgreBeans extends BaseDBTest {


    @Test
    public void generateBean() {
        String ds = "rest-data";

        System.setProperty("ds", ds.replace("-", ""));              //设置数据源


        System.setProperty("table_name", "anjuke");  //依据数据的表
        System.setProperty("bean_name", "AnjukeBean"); //生成的类名

        System.setProperty("table_name", "fivejob");  //依据数据的表
        System.setProperty("bean_name", "FivejobBean"); //生成的类名


        System.setProperty("url", "jdbc:postgresql://127.0.0.1:5432/rest-data");
        System.setProperty("user", "postgres");
        System.setProperty("passcode", "123456");
        System.setProperty("driverClass", "org.postgresql.Driver");
    }


}
