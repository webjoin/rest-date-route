package com.viewt.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.viewt.rest.data.util.Cons;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


/**
 * 美团
 * @author tangyu
 * @since 2017-11-01 02:19
 */
public class MeituanQueueDataBootstrap  extends BaseBootstrap {
    public static void main(String[] args) {
        MeituanQueueDataBootstrap bootstrap = new MeituanQueueDataBootstrap();
        bootstrap.start(args);
    }

    @Override
    protected void crawl(String[] args) {
        String logFile = Cons.USER_DIR+"/logs/other/other-meituan-queue.log";
        Path path = Paths.get(logFile);
        try {
            List<String> strings = Files.readAllLines(path);
            strings.forEach(x->{
                if (x.startsWith("{")) {
                    JSONObject json = JSON.parseObject(x);
                    String cityName = json.getString("cityName");
                    String cityId = json.getString("cityId");
                    String page = json.getString("page");
                    JSONArray jsonArray = json.getJSONObject("data").getJSONObject("poiList").getJSONArray("poiInfos");
                    jsonArray.forEach(info->{
                        JSONObject infoJson = (JSONObject) info;
                        String areaName = infoJson.getString("areaName");
                        String name = infoJson.getString("name");
                        String cateName = infoJson.getString("cateName");
                        logger.info("{},{},{},{},{},{}",cityName,name,areaName,cateName,cityId,page);
                    });
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
