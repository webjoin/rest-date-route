package com.viewt.rest.entity.restdata;

import javax.persistence.*;

@Table(name = "wm_meituan_food_spu")
public class WmMeituanFoodSpu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 菜名 原味手抓饼
     */
    private String name;

    /**
     * 点赞数
     */
    @Column(name = "praise_num")
    private Integer praiseNum;

    /**
     * 月销量
     */
    @Column(name = "month_saled")
    private Integer monthSaled;

    /**
     * 交易销量
     */
    @Column(name = "tread_num")
    private Integer treadNum;

    /**
     * spu_tag
     */
    private Long tag;

    /**
     * 启送金额
     */
    @Column(name = "min_price")
    private Double minPrice;

    /**
     * 新点赞数
     */
    @Column(name = "praise_num_new")
    private Integer praiseNumNew;

    /**
     * 活动标签
     */
    @Column(name = "activity_tag")
    private Integer activityTag;

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
     * 获取菜名 原味手抓饼
     *
     * @return name - 菜名 原味手抓饼
     */
    public String getName() {
        return name;
    }

    /**
     * 设置菜名 原味手抓饼
     *
     * @param name 菜名 原味手抓饼
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取点赞数
     *
     * @return praise_num - 点赞数
     */
    public Integer getPraiseNum() {
        return praiseNum;
    }

    /**
     * 设置点赞数
     *
     * @param praiseNum 点赞数
     */
    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    /**
     * 获取月销量
     *
     * @return month_saled - 月销量
     */
    public Integer getMonthSaled() {
        return monthSaled;
    }

    /**
     * 设置月销量
     *
     * @param monthSaled 月销量
     */
    public void setMonthSaled(Integer monthSaled) {
        this.monthSaled = monthSaled;
    }

    /**
     * 获取交易销量
     *
     * @return tread_num - 交易销量
     */
    public Integer getTreadNum() {
        return treadNum;
    }

    /**
     * 设置交易销量
     *
     * @param treadNum 交易销量
     */
    public void setTreadNum(Integer treadNum) {
        this.treadNum = treadNum;
    }

    /**
     * 获取spu_tag
     *
     * @return tag - spu_tag
     */
    public Long getTag() {
        return tag;
    }

    /**
     * 设置spu_tag
     *
     * @param tag spu_tag
     */
    public void setTag(Long tag) {
        this.tag = tag;
    }

    /**
     * 获取启送金额
     *
     * @return min_price - 启送金额
     */
    public Double getMinPrice() {
        return minPrice;
    }

    /**
     * 设置启送金额
     *
     * @param minPrice 启送金额
     */
    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    /**
     * 获取新点赞数
     *
     * @return praise_num_new - 新点赞数
     */
    public Integer getPraiseNumNew() {
        return praiseNumNew;
    }

    /**
     * 设置新点赞数
     *
     * @param praiseNumNew 新点赞数
     */
    public void setPraiseNumNew(Integer praiseNumNew) {
        this.praiseNumNew = praiseNumNew;
    }

    /**
     * 获取活动标签
     *
     * @return activity_tag - 活动标签
     */
    public Integer getActivityTag() {
        return activityTag;
    }

    /**
     * 设置活动标签
     *
     * @param activityTag 活动标签
     */
    public void setActivityTag(Integer activityTag) {
        this.activityTag = activityTag;
    }
}