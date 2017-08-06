package com.viewt.rest.entity.restdata;

import java.util.Date;
import javax.persistence.*;

@Table(name = "dp_shop_list")
public class DpShopListBean {
    /**
     * 92753651
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hotelBookable")
    private Byte hotelbookable;

    private Byte bookable;

    @Column(name = "hasTakeaway")
    private Byte hastakeaway;

    @Column(name = "hasPromo")
    private Byte haspromo;

    /**
     * 梅江公园
     */
    @Column(name = "regionName")
    private String regionname;

    @Column(name = "hasMoPay")
    private Byte hasmopay;

    @Column(name = "cityId")
    private Integer cityid;

    /**
     * 梅江公园 粉面馆
     */
    @Column(name = "matchText")
    private String matchtext;

    /**
     * 粉面馆
     */
    @Column(name = "categoryName")
    private String categoryname;

    @Column(name = "newShop")
    private Byte newshop;

    @Column(name = "memberCardId")
    private Integer membercardid;

    @Column(name = "altName")
    private String altname;

    @Column(name = "priceText")
    private String pricetext;

    @Column(name = "authorityLabelType")
    private Integer authoritylabeltype;

    /**
     * 厦门第一家孙厝分店
     */
    @Column(name = "branchName")
    private String branchname;

    @Column(name = "orderDish")
    private Byte orderdish;

    @Column(name = "shopPower")
    private Integer shoppower;

    @Column(name = "hasDeals")
    private Byte hasdeals;

    /**
     * 清真西北拉面
     */
    private String name;

    @Column(name = "scoreText")
    private String scoretext;

    @Column(name = "shopType")
    private Integer shoptype;

    private Byte queueable;

    @Column(name = "categoryId")
    private Integer categoryid;

    private Integer status;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取92753651
     *
     * @return id - 92753651
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置92753651
     *
     * @param id 92753651
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return hotelBookable
     */
    public Byte getHotelbookable() {
        return hotelbookable;
    }

    /**
     * @param hotelbookable
     */
    public void setHotelbookable(Byte hotelbookable) {
        this.hotelbookable = hotelbookable;
    }

    /**
     * @return bookable
     */
    public Byte getBookable() {
        return bookable;
    }

    /**
     * @param bookable
     */
    public void setBookable(Byte bookable) {
        this.bookable = bookable;
    }

    /**
     * @return hasTakeaway
     */
    public Byte getHastakeaway() {
        return hastakeaway;
    }

    /**
     * @param hastakeaway
     */
    public void setHastakeaway(Byte hastakeaway) {
        this.hastakeaway = hastakeaway;
    }

    /**
     * @return hasPromo
     */
    public Byte getHaspromo() {
        return haspromo;
    }

    /**
     * @param haspromo
     */
    public void setHaspromo(Byte haspromo) {
        this.haspromo = haspromo;
    }

    /**
     * 获取梅江公园
     *
     * @return regionName - 梅江公园
     */
    public String getRegionname() {
        return regionname;
    }

    /**
     * 设置梅江公园
     *
     * @param regionname 梅江公园
     */
    public void setRegionname(String regionname) {
        this.regionname = regionname == null ? null : regionname.trim();
    }

    /**
     * @return hasMoPay
     */
    public Byte getHasmopay() {
        return hasmopay;
    }

    /**
     * @param hasmopay
     */
    public void setHasmopay(Byte hasmopay) {
        this.hasmopay = hasmopay;
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
     * 获取梅江公园 粉面馆
     *
     * @return matchText - 梅江公园 粉面馆
     */
    public String getMatchtext() {
        return matchtext;
    }

    /**
     * 设置梅江公园 粉面馆
     *
     * @param matchtext 梅江公园 粉面馆
     */
    public void setMatchtext(String matchtext) {
        this.matchtext = matchtext == null ? null : matchtext.trim();
    }

    /**
     * 获取粉面馆
     *
     * @return categoryName - 粉面馆
     */
    public String getCategoryname() {
        return categoryname;
    }

    /**
     * 设置粉面馆
     *
     * @param categoryname 粉面馆
     */
    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname == null ? null : categoryname.trim();
    }

    /**
     * @return newShop
     */
    public Byte getNewshop() {
        return newshop;
    }

    /**
     * @param newshop
     */
    public void setNewshop(Byte newshop) {
        this.newshop = newshop;
    }

    /**
     * @return memberCardId
     */
    public Integer getMembercardid() {
        return membercardid;
    }

    /**
     * @param membercardid
     */
    public void setMembercardid(Integer membercardid) {
        this.membercardid = membercardid;
    }

    /**
     * @return altName
     */
    public String getAltname() {
        return altname;
    }

    /**
     * @param altname
     */
    public void setAltname(String altname) {
        this.altname = altname == null ? null : altname.trim();
    }

    /**
     * @return priceText
     */
    public String getPricetext() {
        return pricetext;
    }

    /**
     * @param pricetext
     */
    public void setPricetext(String pricetext) {
        this.pricetext = pricetext == null ? null : pricetext.trim();
    }

    /**
     * @return authorityLabelType
     */
    public Integer getAuthoritylabeltype() {
        return authoritylabeltype;
    }

    /**
     * @param authoritylabeltype
     */
    public void setAuthoritylabeltype(Integer authoritylabeltype) {
        this.authoritylabeltype = authoritylabeltype;
    }

    /**
     * 获取厦门第一家孙厝分店
     *
     * @return branchName - 厦门第一家孙厝分店
     */
    public String getBranchname() {
        return branchname;
    }

    /**
     * 设置厦门第一家孙厝分店
     *
     * @param branchname 厦门第一家孙厝分店
     */
    public void setBranchname(String branchname) {
        this.branchname = branchname == null ? null : branchname.trim();
    }

    /**
     * @return orderDish
     */
    public Byte getOrderdish() {
        return orderdish;
    }

    /**
     * @param orderdish
     */
    public void setOrderdish(Byte orderdish) {
        this.orderdish = orderdish;
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
     * @return hasDeals
     */
    public Byte getHasdeals() {
        return hasdeals;
    }

    /**
     * @param hasdeals
     */
    public void setHasdeals(Byte hasdeals) {
        this.hasdeals = hasdeals;
    }

    /**
     * 获取清真西北拉面
     *
     * @return name - 清真西北拉面
     */
    public String getName() {
        return name;
    }

    /**
     * 设置清真西北拉面
     *
     * @param name 清真西北拉面
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return scoreText
     */
    public String getScoretext() {
        return scoretext;
    }

    /**
     * @param scoretext
     */
    public void setScoretext(String scoretext) {
        this.scoretext = scoretext == null ? null : scoretext.trim();
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
     * @return queueable
     */
    public Byte getQueueable() {
        return queueable;
    }

    /**
     * @param queueable
     */
    public void setQueueable(Byte queueable) {
        this.queueable = queueable;
    }

    /**
     * @return categoryId
     */
    public Integer getCategoryid() {
        return categoryid;
    }

    /**
     * @param categoryid
     */
    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}