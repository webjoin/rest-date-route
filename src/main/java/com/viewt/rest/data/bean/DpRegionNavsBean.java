package com.viewt.rest.data.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elijah on 24/7/2017.
 */
public class DpRegionNavsBean {
    private String name;//美食;
    private int id;//10;
    private int parentId;//0}

    public DpRegionNavsBean() {
    }

    public DpRegionNavsBean(String name, int id, int parentId) {
        this.name = name;
        this.id = id;
        this.parentId = parentId;
    }

    private List<DpRegionNavsBean> children ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public List<DpRegionNavsBean> getChildren() {
        return children;
    }

    public void setChildren(List<DpRegionNavsBean> children) {
        this.children = children;
    }
}
