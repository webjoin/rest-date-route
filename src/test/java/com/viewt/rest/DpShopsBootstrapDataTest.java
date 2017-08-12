package com.viewt.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.viewt.rest.data.DpBootstrapIdsFile;
import com.viewt.rest.data.DpShopBootstap2Db;
import com.viewt.rest.data.test.db.DbBootstrap;
import com.viewt.rest.data.util.HttpUtil;
import com.viewt.rest.entity.restdata.DpShopListBean;
import com.viewt.rest.entity.restdata.DpShopsBean;
import com.viewt.rest.mapper.restdata.DpShopListBeanMapper;
import com.viewt.rest.mapper.restdata.DpShopsBeanMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by Elijah on 1/8/2017.
 * 写入dp-shop数据入库
 * 通过 dp-list来的数据 采集明细后入库
 * -Darea=dp-shops-db
 */
public class DpShopsBootstrapDataTest extends DbBootstrap {

    public static void main(String[] args) {
        DpShopsBootstrapDataTest data = new DpShopsBootstrapDataTest();
        String env = "local";
        env = "local-postgre";
        data.initSqlSessionFactory(env);
        data.save2db();
//        data.save2db1();
    }

    private void save2db1() {


        SqlSession sqlSession = getSqlSession();

        MapperHelper mapperHelper = new MapperHelper();
        mapperHelper.registerMapper(Mapper.class);
        mapperHelper.processConfiguration(sqlSession.getConfiguration());

        DpShopsBeanMapper mapper = sqlSession.getMapper(DpShopsBeanMapper.class);//查询全部List<Country> countryList = mapper.select(new Country());//总数Assert.assertEquals(183, countryList.size());//通用Example查询Example example = new Example(Country.class);

        int i = mapper.selectCount(null);
        List<DpShopsBean> list = new ArrayList<>();
        DpShopsBean bean = new DpShopsBean();
        bean.setShopgeog("SRID=4326;POINT(118.07080463253 24.452629127023)");
//        bean.setShopgeog("118.07080463253 24.452629127023");
        bean.setId(2L);
        list.add(bean);
        mapper.inserts(list);
        sqlSession.commit();
        sqlSession.close();
        System.out.println(i);
    }

    private void save2db() {
        String[] args = {"2"};
        DpShopBootstap2Db file = new DpShopBootstap2Db();
        file.start(null);
        List<JSONObject> items = file.items;
        int size = items.size();
        int num = 80;
        int page = (int) Math.ceil((double) size / num);
        System.out.println("page:" + page);
        for (int i = 0; i < num; i++) {
            int start = i * page;
            int end = start + page;
            if (start > size) break;
            if (end > size) end = size;
            List<JSONObject> subKeys = items.subList(start, end);
            List<DpShopsBean> list = new ArrayList<>(subKeys.size());
            for (JSONObject subKey : subKeys) {
                if (!subKey.containsKey("id")) {
                    continue;
                }
                Set<Map.Entry<String, Object>> entries = subKey.entrySet();
                Map<String, Object> kv = new HashMap<>();
                for (Map.Entry<String, Object> entry : entries) {
                    String key = entry.getKey().toLowerCase();
                    Object value = entry.getValue();
                    kv.put(key, value);
                }
                Set<Map.Entry<String, Object>> entries1 = kv.entrySet();
                for (Map.Entry<String, Object> entry : entries1) {
                    subKey.put(entry.getKey(), entry.getValue());
                }
                DpShopsBean dpShopsBean = JSON.toJavaObject(subKey, DpShopsBean.class);
                list.add(dpShopsBean);
//                break; // TODO 测试
            }

            SqlSession sqlSession = getSqlSession();

            MapperHelper mapperHelper = new MapperHelper();
            mapperHelper.registerMapper(Mapper.class);
            mapperHelper.processConfiguration(sqlSession.getConfiguration());

            DpShopsBeanMapper mapper = sqlSession.getMapper(DpShopsBeanMapper.class);//查询全部List<Country> countryList = mapper.select(new Country());//总数Assert.assertEquals(183, countryList.size());//通用Example查询Example example = new Example(Country.class);
            int size1 = list.size();
            int page1 = 100;
            int num1 = (int) Math.ceil((double) size1 / page1);
            for (int i1 = 0; i1 < num1; i1++) {
                int start1 = i1 * page1;
                int end1 = start1 + page1;
                if (start1 > size1) break;
                if (end1 > size1) end1 = size1;
                List<DpShopsBean> dpShopsBeans = list.subList(start1, end1);
                String[] lngLats = getLngLats(dpShopsBeans);
                String[] baiduLngLat = getBaiduLngLat(lngLats);
                int k = 0;
                for (DpShopsBean dpShopsBean : dpShopsBeans) {
                    dpShopsBean.setShopgeog(baiduLngLat[k]);
                    k++;
                }
                //TODO to do thing ...
            }
            int i1 = mapper.inserts(list);
            sqlSession.commit();
            sqlSession.close();
            System.out.println(i1);
        }
    }

    private String[] getLngLats(List<DpShopsBean> dpShopsBeans) {
        String[] coords = new String[dpShopsBeans.size()];
        int i = 0;
        for (DpShopsBean dpShopsBean : dpShopsBeans) {
            String shopglng = dpShopsBean.getShopglng();
            String shopglat = dpShopsBean.getShopglat();
            if (null == shopglng || shopglng.isEmpty()) {
                shopglng = "0";
            }
            if (null == shopglat || shopglat.isEmpty()) {
                shopglat = "0";
            }
            String s = shopglng + "," + shopglat;
            coords[i] = s;
            i++;
        }
        return coords;
    }

    String[] getBaiduLngLat(String[] coords) {
        if (null == coords || coords.length == 0) {
            return null;
        }
        String[] rs = new String[coords.length];
        String join = StringUtils.join(coords, ";");

        String url = "http://api.map.baidu.com/geoconv/v1/?ak=uT9oulDM5zG3HksGyOSRAyW3zyo52Hjm&from=3&to=5&coords=" + join;
        String s = HttpUtil.get(url, 10 * 1000);

        JSONArray result = JSON.parseObject(s).getJSONArray("result");
        Iterator<Object> iterator = result.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            JSONObject next = (JSONObject) iterator.next();
            String lng = next.getString("x");
            String lat = next.getString("y");
            if ("118.07080463253".equals(lng) || "118.07080463253".equals(lat)) {
                System.out.println("-----");
            }
            rs[i] = "SRID=4326;POINT(" + lng + " " + lat + ")";
            i++;
        }
        return rs;
    }

    private void json2Bean(JSONObject json, DpShopsBean bean) {
    }


}
