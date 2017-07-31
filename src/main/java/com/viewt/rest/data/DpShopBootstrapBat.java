package com.viewt.rest.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.viewt.rest.BaseBootstrap;
import com.viewt.rest.ElemeBootstrapBat;
import com.viewt.rest.data.bean.DpDataBean;
import com.viewt.rest.data.bean.RespBean;
import com.viewt.rest.data.util.CodeUtil;
import com.viewt.rest.data.util.Cons;
import com.viewt.rest.data.util.JSONUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Elijah on 18/7/2017.
 * 点评明细数据
 */
public class DpShopBootstrapBat {

    static Logger logger = LoggerFactory.getLogger(DpShopBootstrapBat.class);


    public static void main(String[] args) throws IOException {


        String path1 = Cons.USER_DIR;
        int start = 1;
        int end = 1;
        int step = 15500;
        for (int i = 1; i <= 3; i++) {
            start = (i - 1) * step + 1;
            end = step * i;
            List<String> subKeys = new ArrayList<>(Arrays.asList(start + " " + end));

            ElemeBootstrapBat.newFile("dp-shop", subKeys.toArray(new String[subKeys.size()]), i, path1, DpShopBootstrap.class);
        }
    }

}
