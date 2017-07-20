package com.viewt.rest.data.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elijah on 15/7/2017.
 */
public class JSONUtil {
    private static Logger logger = LoggerFactory.getLogger(JSONUtil.class);
    /**
     * 获取json对象
     *
     * @param jsonObject
     * @param key
     * @return
     */
    public static  JSONObject getJSONObject(JSONObject jsonObject, String key) {
        if (jsonObject == null) {
            return new JSONObject();
        }
        try {
            return jsonObject.getJSONObject(key);
        } catch (Exception e) {
            logger.error("{} , the key's value is not JSONObject ， pls check! ",key);
            return new JSONObject();
        }
    }

    public static  JSONObject parseObject(String content) {
        if (StringUtils.isEmpty(content)) {
            return new JSONObject();
        }
        try {
            return JSON.parseObject(content);
        } catch (Exception e){
            logger.error("{} , the content is not JSONObject ， pls check! ",content);
            return new JSONObject();
        }
    }

    public static  <T> List<T> parseArray(String content, Class<T> clazz) {
        if (StringUtils.isEmpty(content)) {
            return new ArrayList<>();
        }
        try {
            return JSON.parseArray(content, clazz);
        } catch (Exception e) {
            logger.error("{} , the content can not case to the class {} ， pls check! ",content,clazz.getSimpleName());
            return new ArrayList<>();
        }
    }

    public static  JSONArray getJSONArray(JSONObject origionJON, String key) {
        if (null == origionJON) {
            return new JSONArray();
        }
        try {
            return origionJON.getJSONArray(key);
        } catch (Exception e) {
            logger.error("{} , the key's value is not JSONArray ， pls check! ",key);
            return new JSONArray();
        }
    }

    public static JSONArray parseArray(String content) {
        try {
            return JSON.parseArray(content);
        } catch (Exception e) {
            logger.error("{} , the content is not JSONArray ， pls check! ",content);
            return new JSONArray();
        }
    }
}
