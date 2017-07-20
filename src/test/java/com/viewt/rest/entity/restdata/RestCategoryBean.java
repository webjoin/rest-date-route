package com.viewt.rest.entity.restdata;

import javax.persistence.*;

@Table(name = "rest_category")
public class RestCategoryBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 分类名称(菜系)名称
     */
    @Column(name = "NAME")
    private String name;

    @Column(name = "parent_id")
    private Long parentId;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取分类名称(菜系)名称
     *
     * @return NAME - 分类名称(菜系)名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置分类名称(菜系)名称
     *
     * @param name 分类名称(菜系)名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return parent_id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}