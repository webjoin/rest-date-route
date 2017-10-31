package com.viewt.rest.data.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author tangyu
 * @since 2017-10-31 19:16
 */
public class MeituanFoodShopListReq {

    private Integer offset ; // :0,
    private Integer limit ; // :15,
    private Integer cateId ; // :1,
    private Integer lineId ; // :0,
    private Integer stationId ; // :0,
    private Integer areaId ; // :0,
    private String sort ; // :"default",

    @JSONField(name="deal_attr_23")
    private String dealAttr23 ; // :"",

    @JSONField(name="deal_attr_24")
    private String dealAttr24 ; // :"",

    @JSONField(name="deal_attr_25")
    private String dealAttr25 ; // :"",

    @JSONField(name="poi_attr_20043")
    private String poiAttr20043 ; // :"",

    @JSONField(name="poi_attr_20033")
    private Integer poiAttr20033 ; // :20065}


    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String  sort) {
        this.sort = sort;
    }

    public String getDealAttr23() {
        return dealAttr23;
    }

    public void setDealAttr23(String dealAttr23) {
        this.dealAttr23 = dealAttr23;
    }

    public String getDealAttr24() {
        return dealAttr24;
    }

    public void setDealAttr24(String dealAttr24) {
        this.dealAttr24 = dealAttr24;
    }

    public String getDealAttr25() {
        return dealAttr25;
    }

    public void setDealAttr25(String dealAttr25) {
        this.dealAttr25 = dealAttr25;
    }

    public String getPoiAttr20043() {
        return poiAttr20043;
    }

    public void setPoiAttr20043(String poiAttr20043) {
        this.poiAttr20043 = poiAttr20043;
    }

    public Integer getPoiAttr20033() {
        return poiAttr20033;
    }

    public void setPoiAttr20033(Integer poiAttr20033) {
        this.poiAttr20033 = poiAttr20033;
    }
}
