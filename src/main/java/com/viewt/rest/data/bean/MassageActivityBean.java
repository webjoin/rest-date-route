package com.viewt.rest.data.bean;

import com.alibaba.fastjson.JSON;
import com.viewt.rest.data.util.Massage;

import java.time.LocalDate;

/**
 * @author tangyu
 * @since 2018-01-09 10:26
 */

public class MassageActivityBean {
    /**
     * yyyy-mm-dd
     */
    @Massage("日期")
    private LocalDate date;

    @Massage("品牌")
    private String brandName;

    @Massage("活动ID")
    private String activityId;

    @Massage("采集地址")
    private String url;

    public MassageActivityBean(LocalDate date, String brandName, String activityId, String url) {
        this.date = date;
        this.brandName = brandName;
        this.activityId = activityId;
        this.url = url;
    }

    public MassageActivityBean(String url, LocalDate localDate) {
        this.url = url;
        this.date = localDate;
    }

    public MassageActivityBean() {
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
