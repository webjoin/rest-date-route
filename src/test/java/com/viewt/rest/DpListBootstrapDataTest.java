package com.viewt.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.viewt.rest.data.DpBootstrapIdsFile;
import com.viewt.rest.data.test.db.DbBootstrap;
import com.viewt.rest.data.util.FileUtil;
import com.viewt.rest.entity.restdata.DpShopListBean;
import com.viewt.rest.mapper.restdata.DpShopListBeanMapper;
import org.apache.ibatis.session.SqlSession;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Elijah on 1/8/2017.
 * 写入dp-list数据入库
 */
public class DpListBootstrapDataTest extends DbBootstrap {

    public static void main(String[] args) {
        DpListBootstrapDataTest data = new DpListBootstrapDataTest();
        String env = "local";
        env = "pachong-aliyun-prod";
        data.initSqlSessionFactory(env);
        data.save2db();
//        data.save2db1();
    }

    private void save2db1() {


        SqlSession sqlSession = getSqlSession();

        MapperHelper mapperHelper = new MapperHelper();
        mapperHelper.registerMapper(Mapper.class);
        mapperHelper.processConfiguration(sqlSession.getConfiguration());

        DpShopListBeanMapper mapper = sqlSession.getMapper(DpShopListBeanMapper.class);//查询全部List<Country> countryList = mapper.select(new Country());//总数Assert.assertEquals(183, countryList.size());//通用Example查询Example example = new Example(Country.class);

        int i = mapper.selectCount(null);
        System.out.println(i);
    }

    List<JSONObject> readJsonFromFile() {
        String s = "/Users/Elijah/Desktop/self/rest-date-route/logs/other/other-dp-shop-list-items.log";
        return FileUtil.readFile2Json(s);
    }

    private void save2db() {
        String insertCategory = "insertCategory";
        String[] args = {"2"};
        List<JSONObject> items = readJsonFromFile();
        int size = items.size();

        int page = 10000;
        int num = (int) Math.ceil((double) size / page);
        for (int i = 0; i < num; i++) {
            int start = i * page;
            int end = start + page;
            if (start > size) break;
            if (end > size) end = size;
            System.out.println(i + " -- " + start + " -- " + end);
            List<JSONObject> subKeys = items.subList(start, end);
            List<DpShopListBean> list = new ArrayList<>(subKeys.size());
            for (JSONObject subKey : subKeys) {
                DpShopListBean dpShopListBean = new DpShopListBean();
                json2Bean(subKey, dpShopListBean);
                list.add(dpShopListBean);
            }

            SqlSession sqlSession = getSqlSession();

            MapperHelper mapperHelper = new MapperHelper();
            mapperHelper.registerMapper(Mapper.class);
            mapperHelper.processConfiguration(sqlSession.getConfiguration());

            DpShopListBeanMapper mapper = sqlSession.getMapper(DpShopListBeanMapper.class);//查询全部List<Country> countryList = mapper.select(new Country());//总数Assert.assertEquals(183, countryList.size());//通用Example查询Example example = new Example(Country.class);
            int i1 = mapper.inserts(list);
            sqlSession.commit();
            sqlSession.close();
            System.out.println(i + "--->" + i1);
        }
    }

    private void json2Bean(JSONObject json, DpShopListBean bean) {
        byte one = 1;
        byte zero = 0;
        bean.setId(json.getLongValue("id"));
        bean.setHotelbookable(json.getBoolean("hotelBookable") ? one : zero);
        bean.setBookable(json.getBoolean("bookable") ? one : zero);
        bean.setHastakeaway(json.getBoolean("hasTakeaway") ? one : zero);
        bean.setHaspromo(json.getBoolean("hasPromo") ? one : zero);
        bean.setRegionname(json.getString("regionName"));
        bean.setHasmopay(json.getBoolean("hasMoPay") ? one : zero);
        bean.setCityid(json.getInteger("cityId"));
        bean.setMatchtext(json.getString("matchText"));
        bean.setCategoryname(json.getString("categoryName"));
        bean.setNewshop(json.getBoolean("newShop") ? one : zero);
        bean.setMembercardid(json.getInteger("memberCardId"));
        bean.setAltname(json.getString("altName"));
        bean.setPricetext(json.getString("priceText"));
        bean.setAuthoritylabeltype(json.getInteger("authorityLabelType"));
        bean.setBranchname(json.getString("branchName"));
        bean.setOrderdish(json.getBoolean("orderDish") ? one : zero);
        bean.setShoppower(json.getInteger("shopPower"));
        bean.setHasdeals(json.getBoolean("hasDeals") ? one : zero);
        bean.setName(json.getString("name"));
        bean.setScoretext(json.getString("scoreText"));
        bean.setShoptype(json.getInteger("shopType"));
        bean.setQueueable(json.getBoolean("queueable") ? one : zero);
        bean.setCategoryid(json.getInteger("categoryId"));
        bean.setStatus(json.getInteger("status"));
        bean.setCreateTime(new Date());
    }


}
