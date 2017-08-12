package com.viewt.rest.data.test.db;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Elijah on 3/3/2017.
 */
public abstract class DbBootstrap {

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
    protected SqlSession getSqlSession(){
        SqlSession sqlSession;
        try{
            sqlSession = sqlSessionFactory.openSession();
        }catch (Exception e){
            return null;
        }
        return sqlSession;

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

    protected <T> List<T> select(String statement, Object parameter) {
        SqlSession sqlSession;
        try {
            sqlSession = sqlSessionFactory.openSession();
            return sqlSession.selectList(statement, parameter);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    protected <T> T selectOne(String statement, Object parameter) {
        List<T> select = select(statement, parameter);
        if (select != null){
            if (!select.isEmpty()){
                return select.get(0);
            }
        }
        return null;
    }

    protected int update(String statement, Object parameter) {
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            int i =  sqlSession.update( statement,  parameter);
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
