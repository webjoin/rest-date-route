package com.viewt.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.viewt.rest.data.test.db.DbBootstrap;
import com.viewt.rest.data.util.FileUtil;
import com.viewt.rest.entity.restdata.AnjukeBean;
import com.viewt.rest.mapper.restdata.AnjukeBeanMapper;
import org.apache.ibatis.session.SqlSession;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elijah on 13/8/2017.
 */
public class AnjunkeBootstrapDataTest extends DbBootstrap {

    public static void main(String[] args) {
        AnjunkeBootstrapDataTest test = new AnjunkeBootstrapDataTest();
        String env = "local";
        env = "local-postgre";
        test.initSqlSessionFactory(env);
        test.save2db();
    }

    private void save2db() {
        String s = "/Users/Elijah/Desktop/self/sites/anjuke/other-anjuke.log";
        List<String> list = FileUtil.readFile(s);

        List<AnjukeBean> beans = new ArrayList<>();
        for (String s1 : list) {
            JSONObject jsonObject = JSON.parseObject(s1);
            AnjukeBean bean = new AnjukeBean();
            fillBean(jsonObject, bean);
            String geog = "SRID=4326;POINT(" + bean.getLng() + " " + bean.getLat() + ")";
            bean.setHouseGeog(geog);
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
            List<AnjukeBean> beans1 = beans.subList(start, end);

            SqlSession sqlSession = getSqlSession();

            MapperHelper mapperHelper = new MapperHelper();
            mapperHelper.registerMapper(Mapper.class);
            mapperHelper.processConfiguration(sqlSession.getConfiguration());

            AnjukeBeanMapper mapper = sqlSession.getMapper(AnjukeBeanMapper.class);//查询全部List<Country> countryList = mapper.select(new Country());//总数Assert.assertEquals(183, countryList.size());//通用Example查询Example example = new Example(Country.class);
            int i1 = mapper.inserts(beans1);
            sqlSession.commit();
            sqlSession.close();
            System.out.println(i1);

        }

    }

    private void fillBean(JSONObject jsonObject, AnjukeBean bean) {


        bean.setReqlisturl(jsonObject.getString("reqListUrl"));

        bean.setReqdetailurl(jsonObject.getString("reqDetailUrl"));

        bean.setHtotal(jsonObject.getInteger("htotal"));

        bean.setId(jsonObject.getInteger("id"));

        bean.setName(jsonObject.getString("name"));

        bean.setAddr(jsonObject.getString("addr"));

        bean.setEnddate(jsonObject.getString("endDate"));

        bean.setLng(jsonObject.getString("lng"));

        bean.setLat(jsonObject.getString("lat"));

        bean.setAvg(jsonObject.getDouble("avg"));

        bean.setTrend(jsonObject.getDouble("trend"));

        bean.setWytype(jsonObject.getString("wyType"));

        bean.setWyfee(jsonObject.getString("wyFee"));

        bean.setWyarea(jsonObject.getString("wyArea"));

        bean.setHouseqty(jsonObject.getString("houseQty"));

        bean.setWybuildtime(jsonObject.getString("wyBuildTime"));

        bean.setParkingnum(jsonObject.getString("parkingNum"));

        bean.setWyparkingnum(jsonObject.getString("wyparkingNum"));

        bean.setVolume(jsonObject.getString("volume"));

        bean.setGreen(jsonObject.getString("green"));

        bean.setDeveloper(jsonObject.getString("developer"));

        bean.setWycompany(jsonObject.getString("wyCompany"));

        bean.setSalenum(jsonObject.getInteger("saleNum"));

        bean.setRentnum(jsonObject.getInteger("rentNum"));

        bean.setPage(jsonObject.getInteger("page"));

        bean.setTotalpage(jsonObject.getInteger("totalPage"));
    }


}
