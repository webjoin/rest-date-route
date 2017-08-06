package com.viewt.rest.entity;
import org.junit.Test;

/**
 * Created by Elijah on 29/6/2017.
 */
public class GenerateBeans extends BaseDBTest {


    @Test
    public void generateBean() {
        String ds = "rest-data";

        System.setProperty("ds", ds.replace("-",""));              //设置数据源


        System.setProperty("table_name","rest_busi_circle");  //依据数据的表
        System.setProperty("bean_name","RestBusiCircleBean"); //生成的类名


        System.setProperty("table_name","rest_busi_circle_shop_rel");  //依据数据的表
        System.setProperty("bean_name","RestBusiCircleShopRelBean"); //生成的类名
//
//
        System.setProperty("table_name","rest_category");  //依据数据的表
        System.setProperty("bean_name","RestCategoryBean"); //生成的类名
//
//
        System.setProperty("table_name","rest_city");  //依据数据的表
        System.setProperty("bean_name","RestCityBean"); //生成的类名
//
//
        System.setProperty("table_name","rest_shop");  //依据数据的表
        System.setProperty("bean_name","RestShopBean"); //生成的类名
//
//
        System.setProperty("table_name","wm_meituan_discount");  //依据数据的表
        System.setProperty("bean_name","WmMeituanDiscount"); //生成的类名
//
        System.setProperty("table_name","wm_meituan_food_spu");  //依据数据的表
        System.setProperty("bean_name","WmMeituanFoodSpu"); //生成的类名
//
//
        System.setProperty("table_name","wm_meituan_food_spu_tag");  //依据数据的表
        System.setProperty("bean_name","WmMeituanFoodSpuTag"); //生成的类名
//
        System.setProperty("table_name","wm_meituan_poi");  //依据数据的表
        System.setProperty("bean_name","WmMeituanPoi"); //生成的类名
//
        System.setProperty("table_name","wm_meituan_shop");  //依据数据的表
        System.setProperty("bean_name","WmMeituanShop"); //生成的类名


        System.setProperty("table_name","wm_meituan_sku");  //依据数据的表
        System.setProperty("bean_name","WmMeituanSku"); //生成的类名

        System.setProperty("table_name","dp_category");  //依据数据的表
        System.setProperty("bean_name","DpCategoryBean"); //生成的类名


        System.setProperty("table_name","dp_region");  //依据数据的表
        System.setProperty("bean_name","DpRegionBean"); //生成的类名

        System.setProperty("table_name","dp_shop_list");  //依据数据的表
        System.setProperty("bean_name","DpShopListBean"); //生成的类名



        System.setProperty("url", "jdbc:mysql://127.0.0.1:3306/"+ds+"?useUnicode=true&amp;characterEncoding=UTF-8&amp;zeroDateTimeBehavior=convertToNull");
        System.setProperty("user", "root");
        System.setProperty("passcode", "123456");
    }


}
