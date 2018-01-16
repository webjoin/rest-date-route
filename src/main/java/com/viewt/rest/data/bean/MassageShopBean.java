package com.viewt.rest.data.bean;

import com.alibaba.fastjson.JSON;
import com.viewt.rest.data.util.Massage;

import java.time.LocalDate;

/**
 * @author tangyu
 * @since 2018-01-09 10:26
 */

public class MassageShopBean {
    /**
     * yyyy-mm-dd
     */
    @Massage("日期")
    private LocalDate date;

    @Massage("点ID")
    private String shopId;

    @Massage("点名称")
    private String shopName;

    /**
     * //人均
     */
    @Massage("人均")
    private String priceText;
    /**
     * 商圈
     */
    @Massage("商圈")
    private String regionName;
    /**
     * 预订
     */
    @Massage("预订")
    private String bookable;

    /**
     * 团购
     */
    @Massage("团购")

    private String dealable;

    @Massage("采集地址")
    private String url;



    public MassageShopBean() {
    }

    public MassageShopBean(LocalDate date, String shopId, String shopName, String priceText, String regionName, String bookable, String dealable, String url) {
        this.date = date;
        this.shopId = shopId;
        this.shopName = shopName;
        this.priceText = priceText;
        this.regionName = regionName;
        this.bookable = bookable;
        this.dealable = dealable;
        this.url = url;
    }

    public MassageShopBean(String url, LocalDate localDate) {
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
