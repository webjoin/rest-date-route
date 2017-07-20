package com.viewt.rest.entity.restdata;

import javax.persistence.*;

@Table(name = "wm_meituan_food_spu_tag")
public class WmMeituanFoodSpuTag {
    @Id
    private Integer tag;

    /**
     * 活动标签
     */
    @Column(name = "activity_tag")
    private String activityTag;

    /**
     * 分类名称
     */
    private String name;

    /**
     * @return tag
     */
    public Integer getTag() {
        return tag;
    }

    /**
     * @param tag
     */
    public void setTag(Integer tag) {
        this.tag = tag;
    }

    /**
     * 获取活动标签
     *
     * @return activity_tag - 活动标签
     */
    public String getActivityTag() {
        return activityTag;
    }

    /**
     * 设置活动标签
     *
     * @param activityTag 活动标签
     */
    public void setActivityTag(String activityTag) {
        this.activityTag = activityTag == null ? null : activityTag.trim();
    }

    /**
     * 获取分类名称
     *
     * @return name - 分类名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置分类名称
     *
     * @param name 分类名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}