package com.viewt.rest.data.storage;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Elijah on 8/7/2017.
 */
public interface Storage {

    void save(JSONObject jsonObject);
    void save(String json);


}
