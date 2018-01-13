package com.viewt.rest.data.bean;

import com.alibaba.fastjson.JSON;

import java.time.LocalDate;

/**
 * @author tangyu
 * @since 2018-01-09 10:26
 */

public class MassageShopBean {
    /**
     * yyyy-mm-dd
     */
    private LocalDate date;
    private String shopId;
    private String shopName;
    /**
     * //人均
     */
    private String priceText;
    /**
     * 商圈
     */
    private String regionName;
    /**
     * 预订
     */
    private String bookable;
    /**
     * 团购
     */
    private String dealable;


    private String url;

    public MassageShopBean() {
    }

    public MassageShopBean(String url,LocalDate localDate) {
        this.url = url;
        this.date = localDate;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getPriceText() {
        return priceText;
    }

    public void setPriceText(String priceText) {
        this.priceText = priceText;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getBookable() {
        return bookable;
    }

    public void setBookable(String bookable) {
        this.bookable = bookable;
    }

    public String getDealable() {
        return dealable;
    }

    public void setDealable(String dealable) {
        this.dealable = dealable;
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
