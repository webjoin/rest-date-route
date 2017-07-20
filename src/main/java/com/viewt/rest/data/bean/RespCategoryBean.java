package com.viewt.rest.data.bean;

import com.alibaba.fastjson.JSONArray;

/**
 * Created by Elijah on 15/7/2017.
 */
public class RespCategoryBean {

    private Integer count; //1450,
    private Integer id; //-100,
    private Integer[] ids; //Array[9],
    private Integer level; //1,
    private String name; //美食,
    private JSONArray sub_categories;


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JSONArray getSub_categories() {
        return sub_categories;
    }

    public void setSub_categories(JSONArray sub_categories) {
        this.sub_categories = sub_categories;
    }
}
