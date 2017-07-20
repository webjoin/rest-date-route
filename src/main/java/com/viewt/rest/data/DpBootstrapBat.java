package com.viewt.rest.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.viewt.rest.ElemeBootstrapBat;
import com.viewt.rest.WaimaiBootstrap;
import com.viewt.rest.data.bean.RespBean;
import com.viewt.rest.data.service.UrlService;
import com.viewt.rest.data.service.impl.UrlServiceImpl;
import com.viewt.rest.data.util.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Elijah on 18/7/2017.
 */
public class DpBootstrapBat {

    static String categoryId = "102\t; 103\t; 104\t; 106\t; 107\t; 110\t; 111\t; 112,-3613; 112,1831; 112,1832; 112,1833; 112,1834; 112,1835; 112,1836; 112,1837; 112,1838; 112,1839; 112,1840; 112,8085; 112,8373; 112,8374; 112,8375; 112,15652; 112,27090; 112,28549; 112,28887; 112,104; 112,105; 112,1847; 112,1846; 112,1830; 112,15651; 112,1842; 112,8087; 112,15650; 112,27464; 112,1843; 112,65208; 112,70185; 112,27090; 112,70208; 112,15649; 112,27054; 112,1845; 112,1844; 112,70523; 112,70175; 112,106; 112,107; 112,108; 112,109; 114\t; 115\t; 116\t; 117,-3613; 117,104; 117,105; 117,106; 117,107; 117,108; 117,109; 118,-3613; 118,104; 118,105; 118,106; 118,107; 118,108; 118,109; 132\t; 219\t; 224\t; 247\t; 249\t; 250\t; 251\t; 508\t; 733\t; 1338\t; 1783\t; 1817\t;";
    static Logger logger = LoggerFactory.getLogger(DpBootstrapBat.class);

    public static void main(String[] args) throws IOException {
        DpBootstrapBat bootstrap = new DpBootstrapBat();
        bootstrap.crawl1();


        String path1 = System.getProperty("user.dir");
        String[] split = categoryId.split(";");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            String args1 = StringUtils.join(split[i].trim().split(","), " ");
            list.add(args1);
        }

        int size = list.size();
        int num = 5;
        int page = (int) Math.ceil((double) size / num);
        for (int i = 0; i < num; i++) {
            int start = i * page;
            int end = start + page;
            if (start > size) break;
            if (end > size) end = size;
            List<String> subKeys = list.subList(start, end);
            //TODO to do thing ...
            ElemeBootstrapBat.newFile("dp", subKeys.toArray(new String[subKeys.size()]), i, path1,DpBootstrap.class);
        }

    }

    private void crawl1() {

    }
}
