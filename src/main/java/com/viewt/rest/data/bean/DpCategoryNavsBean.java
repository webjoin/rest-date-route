package com.viewt.rest.data.bean;

import java.util.List;

/**
 * Created by Elijah on 24/7/2017.
 */
public class DpCategoryNavsBean {
    
    private int id; //10,
    private String name; //美食,
    private int parentId; //0,
    private List<DpCategoryNavsBean> children;

    public DpCategoryNavsBean() {
    }

    public DpCategoryNavsBean(int id, String name, int parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public List<DpCategoryNavsBean> getChildren() {
        return children;
    }

    public void setChildren(List<DpCategoryNavsBean> children) {
        this.children = children;
    }
}
