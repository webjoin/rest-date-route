package com.viewt.rest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.viewt.rest.data.bean.RespBean;
import com.viewt.rest.data.bean.RespCategoryBean;
import com.viewt.rest.data.bean.RespElemeSelectBean;
import com.viewt.rest.data.service.UrlService;
import com.viewt.rest.data.service.impl.UrlServiceImpl;
import com.viewt.rest.data.util.JSONUtil;
import com.viewt.rest.data.util.Log4jUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by Elijah on 13/7/2017.
 */
public class ElemeBootstrapBat {
    public static void main(String[] args) {
        int start = 0;
        int end = 0;


//        @echo off
//        cd ../
//        title='/startup-1.bat  6 '
//        C:\jdk1.8.0_121\bin\java -server -Djava.compiler=NONE -Darea=0  -Djava.ext.dirs=./lib com.viewt.rest.ElemeBootstrap 6 6
//        @pause
        ElemeBootstrapBat bat = new ElemeBootstrapBat();
        int length = WaimaiBootstrap.shopArea.length;
        List<Integer> ii = new ArrayList<>();
        List<String> args1 = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            if (i % 5 == 0 && i <= 20) {
                System.out.println(i - 1);
                ii.add(i - 1);
            } else if (i % 8 == 0 && i > 20 && i <= 50) {
                System.out.println(i - 1);
                ii.add(i - 1);
            } else {
                if (i % (length - 1) == 0) {
                    System.out.println(i);
                    ii.add(i - 1);
                }
            }
        }
        System.out.println(ii);
        for (int i = 0; i < ii.size(); i++) {
            if (i > 0) {
                args1.add((ii.get(i - 1) + 1) + " " + ii.get(i));
            }
        }

        for (int i = 0; i < args1.size(); i++) {
            bat.generateBat(new String[]{args1.get(i)}, i);
        }

    }

    private void generateBat(String[] args1, int i) {
        String path1 = "/Users/Elijah/Desktop/self/rest-date-route";
        String[] shopAreas = WaimaiBootstrap.shopArea;
        int length = WaimaiBootstrap.shopArea.length;
        int[][] areaGroup = {{0, 6}, {5, 9}, {10, 14}, {15, 19}, {20,}};
        try {
            newFile("ele", args1, i, path1, this.getClass());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void newFile(String prefix, String[] args1, int i, String path1, Class clazz) throws IOException {
        String fileName = "/" + prefix + "-startup-" + i + ".bat";
        Path path = Paths.get(path1 + "/bin" + fileName);
        BufferedWriter br1 = Files.newBufferedWriter(path, Charset.defaultCharset());
        br1.write("@echo off\r\n");
        int i2 = 0;
//                    shopAreas
        if (0 == i2)
            br1.write("cd ../\r\n");

        br1.write("\r\n");
        for (int i1 = 0; i1 < args1.length; i1++) {
            String title = "startup-" + i + "  " + i1 + "/" + args1.length + " " + args1[i1];
            br1.write("title '" + title + "'");
            br1.write("\r\n");
            br1.write("echo '" + title + " %time% start ' >> start-" + i + ".log\r\n");
//            br1.write("C:\\jdk1.8.0_121\\bin\\java -server -Djava.compiler=NONE -Darea=0  -Djava.ext.dirs=./lib com.viewt.rest.ElemeBootstrap " + args1[i1]);
            br1.write("C:\\jdk1.8.0_121\\bin\\java -server -Djava.compiler=NONE -Darea=" + i + "  -Djava.ext.dirs=./lib " + clazz.getName() + " " + args1[i1]);
            br1.write("\r\n");
            br1.write("echo '" + title + " %time% end ' >> start-" + i + ".log\r\n");
            br1.write("\r\n");

        }
        ++i2;
        br1.write("@pause");
        br1.flush();
        br1.close();
    }
}
