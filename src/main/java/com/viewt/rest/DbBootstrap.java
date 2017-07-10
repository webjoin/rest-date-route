package com.viewt.rest;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;

/**
 * Created by Elijah on 3/3/2017.
 */
public abstract class DbBootstrap {

    String resource = "mybatis.xml";
    String excelFile  = "dp_booking_data.xlsx";
    SqlSessionFactory sqlSessionFactory = null;

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
