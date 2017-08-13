package com.viewt.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.viewt.rest.data.test.db.DbBootstrap;
import com.viewt.rest.data.util.FileUtil;
import com.viewt.rest.entity.restdata.AnjukeBean;
import com.viewt.rest.entity.restdata.FivejobBean;
import com.viewt.rest.mapper.restdata.AnjukeBeanMapper;
import com.viewt.rest.mapper.restdata.FivejobBeanMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elijah on 13/8/2017.
 */
public class FiveJobBoostrapDataTest extends DbBootstrap {


    public static void main(String[] args) {


        FiveJobBoostrapDataTest test = new FiveJobBoostrapDataTest();
        String env;
        env = "local-postgre";
        test.initSqlSessionFactory(env);
        test.save2db();

    }

    private void save2db() {

        String s = "/Users/Elijah/Desktop/self/sites/51job/51job.txt";
        List<String> list = FileUtil.readFile(s);

        List<FivejobBean> beans = new ArrayList<>();
        for (String s1 : list) {
            JSONObject jsonObject = JSON.parseObject(s1);
            FivejobBean bean = new FivejobBean();
            fillBean(jsonObject, bean);
            if (StringUtils.isEmpty(bean.getLng())) {
                bean.setLng("0");
            }
            if (StringUtils.isEmpty(bean.getLat())) {
                bean.setLat("0");
            }
            String geog = "SRID=4326;POINT(" + bean.getLng() + " " + bean.getLat() + ")";
            bean.setCompenyGeog(geog);
            beans.add(bean);
        }

        int size = beans.size();
        int page = 1000;
        int num = (int) Math.ceil((double) size / page);
        for (int i = 0; i < num; i++) {
            int start = i * page;
            int end = start + page;
            if (start > size) break;
            if (end > size) end = size;
            List<FivejobBean> beans1 = beans.subList(start, end);

            SqlSession sqlSession = getSqlSession();

            MapperHelper mapperHelper = new MapperHelper();
            mapperHelper.registerMapper(Mapper.class);
            mapperHelper.processConfiguration(sqlSession.getConfiguration());

            FivejobBeanMapper mapper = sqlSession.getMapper(FivejobBeanMapper.class);//查询全部List<Country> countryList = mapper.select(new Country());//总数Assert.assertEquals(183, countryList.size());//通用Example查询Example example = new Example(Country.class);
            int i1 = mapper.inserts(beans1);
            sqlSession.commit();
            sqlSession.close();
            System.out.println(i1);

        }
    }

    private void fillBean(JSONObject jsonObject, FivejobBean bean) {
        bean.setAddress(jsonObject.getString("address"));
        bean.setCompanyname(jsonObject.getString("companyName"));
        bean.setCompanytype(jsonObject.getString("companyType"));
        bean.setFuli(jsonObject.getString("fuLi"));
        bean.setGzjy(jsonObject.getString("gzjy"));
        bean.setIndustry(jsonObject.getString("industry"));
        bean.setJobname(jsonObject.getString("jobName"));
        bean.setLat(jsonObject.getString("lat"));
        bean.setLng(jsonObject.getString("lng"));
        bean.setMoney(jsonObject.getString("money"));
        bean.setPeoplecount(jsonObject.getString("peopleCount"));
        bean.setPublishdate(jsonObject.getString("publishDate"));
        bean.setXueli(jsonObject.getString("xueLi"));
    }
}
