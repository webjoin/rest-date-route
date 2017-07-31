package com.viewt.rest;

import com.viewt.rest.data.service.UrlService;
import com.viewt.rest.data.service.impl.UrlServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Elijah on 22/7/2017.
 */
public abstract class BaseBootstrap {


    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected UrlService urlService = new UrlServiceImpl();
    protected Map<String, String> reqHeader = new HashMap<>();
    protected abstract void crawl(String[] args);
    protected boolean initDatasouce = false;
    protected String env = "local";

    protected void start(String[] args){
        String area = System.getProperty("area");
        if (StringUtils.isEmpty(area)){
            System.out.println("exit do nothing , can not find property 'area'");
            System.exit(1);
        }
        if (initDatasouce){
            initSqlSessionFactory(env);
        }
        try{
            this.crawl(args);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }

    }

    String resource = "mybatis.xml";
    SqlSessionFactory sqlSessionFactory = null;

    protected void initSqlSessionFactory(String env) {
        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            if ( StringUtils.isNoneEmpty( env )) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build( inputStream , env );
            }else {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(  inputStream );
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    protected int insert(String statement, Object parameter) {
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            int i =  sqlSession.insert( statement,  parameter);
            sqlSession.commit();
            return i;
        } catch (Exception e) {
            if ( null != sqlSession ) {
                sqlSession.rollback();
            }
            e.printStackTrace();
            return 0;
        }
    }
}
