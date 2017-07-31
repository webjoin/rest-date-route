package com.viewt.rest.data.bean;

/**
 * Created by Elijah on 22/7/2017.
 */
public class DpDataBean {

    private String dcNames; //上海餐厅/静安寺/小吃快餐/快餐简餐/和食居(1788国际中心店)
    private String fullName; //和食居(1788国际中心店)
    private Integer branchQty ; // 分店数
    private Long id ; // 1
    private Integer cityId ; // 1
    private Integer shopCityId ; // 1
    private String address;//
    private String cityName;// "上海",
    private String cityEnName;// "shanghai",
    private String shopGlat;// "31.222263",
    private String shopGlng;//"121.4437",
    private String cityGlat;//"31.230708",
    private String cityGlng;//"121.472916",
    private Integer power;//5,
    private Integer shopType;//5,
    private String categoryURLName;//5,

    private Integer shopPower;//35,
    private Integer mainRegionId;//812,
    private String mainCategoryName;//"快餐简餐",
    private Integer mainCategoryId;//210




    private Integer reviewCount;//484条评论

    private Double avgPrice;// 人均：36元

    private Double smell ; //<span class="item">口味：7.9</span>
    private Double env ; //<span class="item">环境：7.9</span>
    private Double service ; //<span class="item">服务：7.9</span>

    private String tel ; // <span class="item" itemprop="tel">18701719195</span>
    private String shopHour ; //<span class="info-name">营业时间：</span>
//    private St
    private String hui; // 9.5,2500;100-5,646;   每满100减5元已买646

    private String tuan; //80,101,27;35,45,26; // 实付,应付,售卖量;
    //推荐菜
    private String allDishes; // 泡菜肥牛稻粉,56,35;牛肉诱惑稻粉,39,29   name,count,price


    private  Integer reviewCountStar1;
    private  Integer reviewCountStar2;
    private  Integer reviewCountStar3;
    private  Integer reviewCountStar4;
    private  Integer reviewCountStar5;
    private  Long lastReviewTime; // 最近更新时间

    //大家认为
    private String summarys; //回头客,18;干净卫生;38;上菜快,9;


    public Integer getBranchQty() {
        return branchQty;
    }

    public void setBranchQty(Integer branchQty) {
        this.branchQty = branchQty;
    }

    public Integer getShopType() {
        return shopType;
    }

    public void setShopType(Integer shopType) {
        this.shopType = shopType;
    }

    public String getCategoryURLName() {
        return categoryURLName;
    }

    public void setCategoryURLName(String categoryURLName) {
        this.categoryURLName = categoryURLName;
    }

    public Long getLastReviewTime() {
        return lastReviewTime;
    }

    public void setLastReviewTime(Long lastReviewTime) {
        this.lastReviewTime = lastReviewTime;
    }

    public Integer getShopCityId() {
        return shopCityId;
    }

    public void setShopCityId(Integer shopCityId) {
        this.shopCityId = shopCityId;
    }

    public String getDcNames() {
        return dcNames;
    }

    public void setDcNames(String dcNames) {
        this.dcNames = dcNames;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityEnName() {
        return cityEnName;
    }

    public void setCityEnName(String cityEnName) {
        this.cityEnName = cityEnName;
    }

    public String getShopGlat() {
        return shopGlat;
    }

    public void setShopGlat(String shopGlat) {
        this.shopGlat = shopGlat;
    }

    public String getShopGlng() {
        return shopGlng;
    }

    public void setShopGlng(String shopGlng) {
        this.shopGlng = shopGlng;
    }

    public String getCityGlat() {
        return cityGlat;
    }

    public void setCityGlat(String cityGlat) {
        this.cityGlat = cityGlat;
    }

    public String getCityGlng() {
        return cityGlng;
    }

    public void setCityGlng(String cityGlng) {
        this.cityGlng = cityGlng;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getShopPower() {
        return shopPower;
    }

    public void setShopPower(Integer shopPower) {
        this.shopPower = shopPower;
    }

    public Integer getMainRegionId() {
        return mainRegionId;
    }

    public void setMainRegionId(Integer mainRegionId) {
        this.mainRegionId = mainRegionId;
    }

    public String getMainCategoryName() {
        return mainCategoryName;
    }

    public void setMainCategoryName(String mainCategoryName) {
        this.mainCategoryName = mainCategoryName;
    }

    public Integer getMainCategoryId() {
        return mainCategoryId;
    }

    public void setMainCategoryId(Integer mainCategoryId) {
        this.mainCategoryId = mainCategoryId;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public Double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public Double getSmell() {
        return smell;
    }

    public void setSmell(Double smell) {
        this.smell = smell;
    }

    public Double getEnv() {
        return env;
    }

    public void setEnv(Double env) {
        this.env = env;
    }

    public Double getService() {
        return service;
    }

    public void setService(Double service) {
        this.service = service;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getShopHour() {
        return shopHour;
    }

    public void setShopHour(String shopHour) {
        this.shopHour = shopHour;
    }

    public String getHui() {
        return hui;
    }

    public void setHui(String hui) {
        this.hui = hui;
    }

    public String getTuan() {
        return tuan;
    }

    public void setTuan(String tuan) {
        this.tuan = tuan;
    }

    public String getAllDishes() {
        return allDishes;
    }

    public void setAllDishes(String allDishes) {
        this.allDishes = allDishes;
    }

    public Integer getReviewCountStar1() {
        return reviewCountStar1;
    }

    public void setReviewCountStar1(Integer reviewCountStar1) {
        this.reviewCountStar1 = reviewCountStar1;
    }

    public Integer getReviewCountStar2() {
        return reviewCountStar2;
    }

    public void setReviewCountStar2(Integer reviewCountStar2) {
        this.reviewCountStar2 = reviewCountStar2;
    }

    public Integer getReviewCountStar3() {
        return reviewCountStar3;
    }

    public void setReviewCountStar3(Integer reviewCountStar3) {
        this.reviewCountStar3 = reviewCountStar3;
    }

    public Integer getReviewCountStar4() {
        return reviewCountStar4;
    }

    public void setReviewCountStar4(Integer reviewCountStar4) {
        this.reviewCountStar4 = reviewCountStar4;
    }

    public Integer getReviewCountStar5() {
        return reviewCountStar5;
    }

    public void setReviewCountStar5(Integer reviewCountStar5) {
        this.reviewCountStar5 = reviewCountStar5;
    }

    public String getSummarys() {
        return summarys;
    }

    public void setSummarys(String summarys) {
        this.summarys = summarys;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
