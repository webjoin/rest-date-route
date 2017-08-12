package com.viewt.rest.entity.restdata;

import javax.persistence.*;

@Table(name = "dp_shops")
public class DpShopsBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sourceFileName")
    private String sourcefilename;

    private String summarys;

    @Column(name = "avgPrice")
    private Double avgprice;

    @Column(name = "shopCityId")
    private Integer shopcityid;

    @Column(name = "lastReviewTime")
    private Long lastreviewtime;

    @Column(name = "cityId")
    private Integer cityid;

    @Column(name = "shopGlat")
    private String shopglat;

    private Double smell;

    @Column(name = "mainRegionId")
    private Integer mainregionid;

    @Column(name = "reviewCountStar2")
    private Integer reviewcountstar2;

    @Column(name = "reviewCountStar1")
    private Integer reviewcountstar1;

    @Column(name = "shopGlng")
    private String shopglng;

    @Column(name = "reviewCountStar4")
    private Integer reviewcountstar4;

    @Column(name = "reviewCountStar3")
    private Integer reviewcountstar3;

    @Column(name = "cityName")
    private String cityname;

    @Column(name = "reviewCount")
    private Integer reviewcount;

    @Column(name = "reviewCountStar5")
    private Integer reviewcountstar5;

    @Column(name = "cityGlng")
    private String cityglng;

    private String tuan;

    private String tel;

    @Column(name = "cityEnName")
    private String cityenname;

    private Integer power;

    @Column(name = "allDishes")
    private String alldishes;

    @Column(name = "cityGlat")
    private String cityglat;

    @Column(name = "mainCategoryId")
    private Integer maincategoryid;

    private String address;

    @Column(name = "fullName")
    private String fullname;

    @Column(name = "shopPower")
    private Integer shoppower;

    private Double env;

    @Column(name = "mainCategoryName")
    private String maincategoryname;

    @Column(name = "categoryURLName")
    private String categoryurlname;

    private Double service;

    @Column(name = "branchQty")
    private Integer branchqty;

    @Column(name = "shopType")
    private Integer shoptype;


    private String shopgeog;


    public String getShopgeog() {
        return shopgeog;
    }

    public void setShopgeog(String shopgeog) {
        this.shopgeog = shopgeog;
    }

    private String hui;


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
     * @return sourceFileName
     */
    public String getSourcefilename() {
        return sourcefilename;
    }

    /**
     * @param sourcefilename
     */
    public void setSourcefilename(String sourcefilename) {
        this.sourcefilename = sourcefilename == null ? null : sourcefilename.trim();
    }

    /**
     * @return summarys
     */
    public String getSummarys() {
        return summarys;
    }

    /**
     * @param summarys
     */
    public void setSummarys(String summarys) {
        this.summarys = summarys == null ? null : summarys.trim();
    }

    /**
     * @return avgPrice
     */
    public Double getAvgprice() {
        return avgprice;
    }

    /**
     * @param avgprice
     */
    public void setAvgprice(Double avgprice) {
        this.avgprice = avgprice;
    }

    /**
     * @return shopCityId
     */
    public Integer getShopcityid() {
        return shopcityid;
    }

    /**
     * @param shopcityid
     */
    public void setShopcityid(Integer shopcityid) {
        this.shopcityid = shopcityid;
    }

    /**
     * @return lastReviewTime
     */
    public Long getLastreviewtime() {
        return lastreviewtime;
    }

    /**
     * @param lastreviewtime
     */
    public void setLastreviewtime(Long lastreviewtime) {
        this.lastreviewtime = lastreviewtime;
    }

    /**
     * @return cityId
     */
    public Integer getCityid() {
        return cityid;
    }

    /**
     * @param cityid
     */
    public void setCityid(Integer cityid) {
        this.cityid = cityid;
    }

    /**
     * @return shopGlat
     */
    public String getShopglat() {
        return shopglat;
    }

    /**
     * @param shopglat
     */
    public void setShopglat(String shopglat) {
        this.shopglat = shopglat == null ? null : shopglat.trim();
    }

    /**
     * @return smell
     */
    public Double getSmell() {
        return smell;
    }

    /**
     * @param smell
     */
    public void setSmell(Double smell) {
        this.smell = smell;
    }

    /**
     * @return mainRegionId
     */
    public Integer getMainregionid() {
        return mainregionid;
    }

    /**
     * @param mainregionid
     */
    public void setMainregionid(Integer mainregionid) {
        this.mainregionid = mainregionid;
    }

    /**
     * @return reviewCountStar2
     */
    public Integer getReviewcountstar2() {
        return reviewcountstar2;
    }

    /**
     * @param reviewcountstar2
     */
    public void setReviewcountstar2(Integer reviewcountstar2) {
        this.reviewcountstar2 = reviewcountstar2;
    }

    /**
     * @return reviewCountStar1
     */
    public Integer getReviewcountstar1() {
        return reviewcountstar1;
    }

    /**
     * @param reviewcountstar1
     */
    public void setReviewcountstar1(Integer reviewcountstar1) {
        this.reviewcountstar1 = reviewcountstar1;
    }

    /**
     * @return shopGlng
     */
    public String getShopglng() {
        return shopglng;
    }

    /**
     * @param shopglng
     */
    public void setShopglng(String shopglng) {
        this.shopglng = shopglng == null ? null : shopglng.trim();
    }

    /**
     * @return reviewCountStar4
     */
    public Integer getReviewcountstar4() {
        return reviewcountstar4;
    }

    /**
     * @param reviewcountstar4
     */
    public void setReviewcountstar4(Integer reviewcountstar4) {
        this.reviewcountstar4 = reviewcountstar4;
    }

    /**
     * @return reviewCountStar3
     */
    public Integer getReviewcountstar3() {
        return reviewcountstar3;
    }

    /**
     * @param reviewcountstar3
     */
    public void setReviewcountstar3(Integer reviewcountstar3) {
        this.reviewcountstar3 = reviewcountstar3;
    }

    /**
     * @return cityName
     */
    public String getCityname() {
        return cityname;
    }

    /**
     * @param cityname
     */
    public void setCityname(String cityname) {
        this.cityname = cityname == null ? null : cityname.trim();
    }

    /**
     * @return reviewCount
     */
    public Integer getReviewcount() {
        return reviewcount;
    }

    /**
     * @param reviewcount
     */
    public void setReviewcount(Integer reviewcount) {
        this.reviewcount = reviewcount;
    }

    /**
     * @return reviewCountStar5
     */
    public Integer getReviewcountstar5() {
        return reviewcountstar5;
    }

    /**
     * @param reviewcountstar5
     */
    public void setReviewcountstar5(Integer reviewcountstar5) {
        this.reviewcountstar5 = reviewcountstar5;
    }

    /**
     * @return cityGlng
     */
    public String getCityglng() {
        return cityglng;
    }

    /**
     * @param cityglng
     */
    public void setCityglng(String cityglng) {
        this.cityglng = cityglng == null ? null : cityglng.trim();
    }

    /**
     * @return tuan
     */
    public String getTuan() {
        return tuan;
    }

    /**
     * @param tuan
     */
    public void setTuan(String tuan) {
        this.tuan = tuan == null ? null : tuan.trim();
    }

    /**
     * @return tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel
     */
    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    /**
     * @return cityEnName
     */
    public String getCityenname() {
        return cityenname;
    }

    /**
     * @param cityenname
     */
    public void setCityenname(String cityenname) {
        this.cityenname = cityenname == null ? null : cityenname.trim();
    }

    /**
     * @return power
     */
    public Integer getPower() {
        return power;
    }

    /**
     * @param power
     */
    public void setPower(Integer power) {
        this.power = power;
    }

    /**
     * @return allDishes
     */
    public String getAlldishes() {
        return alldishes;
    }

    /**
     * @param alldishes
     */
    public void setAlldishes(String alldishes) {
        this.alldishes = alldishes == null ? null : alldishes.trim();
    }

    /**
     * @return cityGlat
     */
    public String getCityglat() {
        return cityglat;
    }

    /**
     * @param cityglat
     */
    public void setCityglat(String cityglat) {
        this.cityglat = cityglat == null ? null : cityglat.trim();
    }

    /**
     * @return mainCategoryId
     */
    public Integer getMaincategoryid() {
        return maincategoryid;
    }

    /**
     * @param maincategoryid
     */
    public void setMaincategoryid(Integer maincategoryid) {
        this.maincategoryid = maincategoryid;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * @return fullName
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * @param fullname
     */
    public void setFullname(String fullname) {
        this.fullname = fullname == null ? null : fullname.trim();
    }

    /**
     * @return shopPower
     */
    public Integer getShoppower() {
        return shoppower;
    }

    /**
     * @param shoppower
     */
    public void setShoppower(Integer shoppower) {
        this.shoppower = shoppower;
    }

    /**
     * @return env
     */
    public Double getEnv() {
        return env;
    }

    /**
     * @param env
     */
    public void setEnv(Double env) {
        this.env = env;
    }

    /**
     * @return mainCategoryName
     */
    public String getMaincategoryname() {
        return maincategoryname;
    }

    /**
     * @param maincategoryname
     */
    public void setMaincategoryname(String maincategoryname) {
        this.maincategoryname = maincategoryname == null ? null : maincategoryname.trim();
    }

    /**
     * @return categoryURLName
     */
    public String getCategoryurlname() {
        return categoryurlname;
    }

    /**
     * @param categoryurlname
     */
    public void setCategoryurlname(String categoryurlname) {
        this.categoryurlname = categoryurlname == null ? null : categoryurlname.trim();
    }

    /**
     * @return service
     */
    public Double getService() {
        return service;
    }

    /**
     * @param service
     */
    public void setService(Double service) {
        this.service = service;
    }

    /**
     * @return branchQty
     */
    public Integer getBranchqty() {
        return branchqty;
    }

    /**
     * @param branchqty
     */
    public void setBranchqty(Integer branchqty) {
        this.branchqty = branchqty;
    }

    /**
     * @return shopType
     */
    public Integer getShoptype() {
        return shoptype;
    }

    /**
     * @param shoptype
     */
    public void setShoptype(Integer shoptype) {
        this.shoptype = shoptype;
    }

    /**
     * @return hui
     */
    public String getHui() {
        return hui;
    }

    /**
     * @param hui
     */
    public void setHui(String hui) {
        this.hui = hui == null ? null : hui.trim();
    }
}