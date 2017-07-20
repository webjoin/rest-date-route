package com.viewt.rest.entity.restdata;

import javax.persistence.*;

@Table(name = "wm_meituan_poi")
public class WmMeituanPoi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * poi地点名称
     */
    @Column(name = "w_addr")
    private String wAddr;

    /**
     * 地址
     */
    private String address;

    /**
     * poi所有的商户量
     */
    @Column(name = "poi_total_num")
    private Integer poiTotalNum;

    /**
     * 省名称
     */
    private String pname;

    private String longitude;

    private String latitude;

    /**
     * 城市名称
     */
    private String cityname;

    /**
     * 行政名称
     */
    private String adname;

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
     * 获取poi地点名称
     *
     * @return w_addr - poi地点名称
     */
    public String getwAddr() {
        return wAddr;
    }

    /**
     * 设置poi地点名称
     *
     * @param wAddr poi地点名称
     */
    public void setwAddr(String wAddr) {
        this.wAddr = wAddr == null ? null : wAddr.trim();
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取poi所有的商户量
     *
     * @return poi_total_num - poi所有的商户量
     */
    public Integer getPoiTotalNum() {
        return poiTotalNum;
    }

    /**
     * 设置poi所有的商户量
     *
     * @param poiTotalNum poi所有的商户量
     */
    public void setPoiTotalNum(Integer poiTotalNum) {
        this.poiTotalNum = poiTotalNum;
    }

    /**
     * 获取省名称
     *
     * @return pname - 省名称
     */
    public String getPname() {
        return pname;
    }

    /**
     * 设置省名称
     *
     * @param pname 省名称
     */
    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    /**
     * @return longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * @param longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    /**
     * @return latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @param latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    /**
     * 获取城市名称
     *
     * @return cityname - 城市名称
     */
    public String getCityname() {
        return cityname;
    }

    /**
     * 设置城市名称
     *
     * @param cityname 城市名称
     */
    public void setCityname(String cityname) {
        this.cityname = cityname == null ? null : cityname.trim();
    }

    /**
     * 获取行政名称
     *
     * @return adname - 行政名称
     */
    public String getAdname() {
        return adname;
    }

    /**
     * 设置行政名称
     *
     * @param adname 行政名称
     */
    public void setAdname(String adname) {
        this.adname = adname == null ? null : adname.trim();
    }
}