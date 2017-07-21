package com.viewt.rest.data.bean;

import java.util.regex.Matcher;

/**
 * Created by Elijah on 21/7/2017.
 */
public class AnjukeDataBean {

    private String reqListUrl;
    private String reqDetailUrl;

    private Integer htotal; //总的小区量
    private Integer id;
    private String name;
    private String addr;
    private String endDate;//竣工时间
    private Double lng;
    private Double lat;
    private Double avg;  // 均价
    private Double trend; //趋势
    private String wyType;// 物业类型
    private String wyFee;// 物业类型
    private String wyArea;// 物业类型
    private String houseQty;// 物业类型
    private String wyBuildTime;//
    private String parkingNum ;  //停车位
    private String wyparkingNum;//

    private String volume ; //容积率
    private String green;   // 绿化
    private String developer;  //开发商
    private String wyCompany;  // 物业公司

    private Integer saleNum ;//二手房房源数
    private Integer rentNum ;//租房源数


    private int page;  //
    private int totalPage;  //


    public String getParkingNum() {
        return parkingNum;
    }

    public void setParkingNum(String parkingNum) {
        this.parkingNum = parkingNum;
    }

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public Integer getRentNum() {
        return rentNum;
    }

    public void setRentNum(Integer rentNum) {
        this.rentNum = rentNum;
    }

    public String getReqListUrl() {
        return reqListUrl;
    }

    public void setReqListUrl(String reqListUrl) {
        this.reqListUrl = reqListUrl;
    }

    public String getReqDetailUrl() {
        return reqDetailUrl;
    }

    public void setReqDetailUrl(String reqDetailUrl) {
        this.reqDetailUrl = reqDetailUrl;
    }

    public Integer getHtotal() {
        return htotal;
    }

    public void setHtotal(Integer htotal) {
        this.htotal = htotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }

    public Double getTrend() {
        return trend;
    }

    public void setTrend(Double trend) {
        this.trend = trend;
    }

    public String getWyType() {
        return wyType;
    }

    public void setWyType(String wyType) {
        this.wyType = wyType;
    }

    public String getWyFee() {
        return wyFee;
    }

    public void setWyFee(String wyFee) {
        this.wyFee = wyFee;
    }

    public String getWyArea() {
        return wyArea;
    }

    public void setWyArea(String wyArea) {
        this.wyArea = wyArea;
    }

    public String getHouseQty() {
        return houseQty;
    }

    public void setHouseQty(String houseQty) {
        this.houseQty = houseQty;
    }

    public String getWyBuildTime() {
        return wyBuildTime;
    }

    public void setWyBuildTime(String wyBuildTime) {
        this.wyBuildTime = wyBuildTime;
    }

    public String getWyparkingNum() {
        return wyparkingNum;
    }

    public void setWyparkingNum(String wyparkingNum) {
        this.wyparkingNum = wyparkingNum;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getGreen() {
        return green;
    }

    public void setGreen(String green) {
        this.green = green;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getWyCompany() {
        return wyCompany;
    }

    public void setWyCompany(String wyCompany) {
        this.wyCompany = wyCompany;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
