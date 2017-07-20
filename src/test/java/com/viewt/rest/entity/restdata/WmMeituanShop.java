package com.viewt.rest.entity.restdata;

import javax.persistence.*;

@Table(name = "wm_meituan_shop")
public class WmMeituanShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer sales;

    @Column(name = "support_coupon")
    private Integer supportCoupon;

    @Column(name = "avg_delivery_time")
    private Integer avgDeliveryTime;

    @Column(name = "wm_poi_view_id")
    private String wmPoiViewId;

    private String distance;

    private String longitude;

    @Column(name = "mt_poi_id")
    private String mtPoiId;

    @Column(name = "month_sale_num")
    private Integer monthSaleNum;

    @Column(name = "shipping_time_x")
    private String shippingTimeX;

    @Column(name = "min_price")
    private Double minPrice;

    private String name;

    @Column(name = "shipping_fee")
    private Double shippingFee;

    @Column(name = "phone_list")
    private String phoneList;

    private String address;

    @Column(name = "brand_type")
    private Integer brandType;

    private String latitude;

    @Column(name = "wm_poi_score")
    private Integer wmPoiScore;

    @Column(name = "comment_num")
    private Integer commentNum;

    @Column(name = "comment_number")
    private Integer commentNumber;

    @Column(name = "invoice_support")
    private Integer invoiceSupport;

    @Column(name = "support_pay")
    private Integer supportPay;

    @Column(name = "shipping_time")
    private String shippingTime;

    /**
     * wm_meituan_poi.id
     */
    @Column(name = "mt_search_poi_id")
    private Long mtSearchPoiId;

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
     * @return sales
     */
    public Integer getSales() {
        return sales;
    }

    /**
     * @param sales
     */
    public void setSales(Integer sales) {
        this.sales = sales;
    }

    /**
     * @return support_coupon
     */
    public Integer getSupportCoupon() {
        return supportCoupon;
    }

    /**
     * @param supportCoupon
     */
    public void setSupportCoupon(Integer supportCoupon) {
        this.supportCoupon = supportCoupon;
    }

    /**
     * @return avg_delivery_time
     */
    public Integer getAvgDeliveryTime() {
        return avgDeliveryTime;
    }

    /**
     * @param avgDeliveryTime
     */
    public void setAvgDeliveryTime(Integer avgDeliveryTime) {
        this.avgDeliveryTime = avgDeliveryTime;
    }

    /**
     * @return wm_poi_view_id
     */
    public String getWmPoiViewId() {
        return wmPoiViewId;
    }

    /**
     * @param wmPoiViewId
     */
    public void setWmPoiViewId(String wmPoiViewId) {
        this.wmPoiViewId = wmPoiViewId == null ? null : wmPoiViewId.trim();
    }

    /**
     * @return distance
     */
    public String getDistance() {
        return distance;
    }

    /**
     * @param distance
     */
    public void setDistance(String distance) {
        this.distance = distance == null ? null : distance.trim();
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
     * @return mt_poi_id
     */
    public String getMtPoiId() {
        return mtPoiId;
    }

    /**
     * @param mtPoiId
     */
    public void setMtPoiId(String mtPoiId) {
        this.mtPoiId = mtPoiId == null ? null : mtPoiId.trim();
    }

    /**
     * @return month_sale_num
     */
    public Integer getMonthSaleNum() {
        return monthSaleNum;
    }

    /**
     * @param monthSaleNum
     */
    public void setMonthSaleNum(Integer monthSaleNum) {
        this.monthSaleNum = monthSaleNum;
    }

    /**
     * @return shipping_time_x
     */
    public String getShippingTimeX() {
        return shippingTimeX;
    }

    /**
     * @param shippingTimeX
     */
    public void setShippingTimeX(String shippingTimeX) {
        this.shippingTimeX = shippingTimeX == null ? null : shippingTimeX.trim();
    }

    /**
     * @return min_price
     */
    public Double getMinPrice() {
        return minPrice;
    }

    /**
     * @param minPrice
     */
    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return shipping_fee
     */
    public Double getShippingFee() {
        return shippingFee;
    }

    /**
     * @param shippingFee
     */
    public void setShippingFee(Double shippingFee) {
        this.shippingFee = shippingFee;
    }

    /**
     * @return phone_list
     */
    public String getPhoneList() {
        return phoneList;
    }

    /**
     * @param phoneList
     */
    public void setPhoneList(String phoneList) {
        this.phoneList = phoneList == null ? null : phoneList.trim();
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
     * @return brand_type
     */
    public Integer getBrandType() {
        return brandType;
    }

    /**
     * @param brandType
     */
    public void setBrandType(Integer brandType) {
        this.brandType = brandType;
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
     * @return wm_poi_score
     */
    public Integer getWmPoiScore() {
        return wmPoiScore;
    }

    /**
     * @param wmPoiScore
     */
    public void setWmPoiScore(Integer wmPoiScore) {
        this.wmPoiScore = wmPoiScore;
    }

    /**
     * @return comment_num
     */
    public Integer getCommentNum() {
        return commentNum;
    }

    /**
     * @param commentNum
     */
    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    /**
     * @return comment_number
     */
    public Integer getCommentNumber() {
        return commentNumber;
    }

    /**
     * @param commentNumber
     */
    public void setCommentNumber(Integer commentNumber) {
        this.commentNumber = commentNumber;
    }

    /**
     * @return invoice_support
     */
    public Integer getInvoiceSupport() {
        return invoiceSupport;
    }

    /**
     * @param invoiceSupport
     */
    public void setInvoiceSupport(Integer invoiceSupport) {
        this.invoiceSupport = invoiceSupport;
    }

    /**
     * @return support_pay
     */
    public Integer getSupportPay() {
        return supportPay;
    }

    /**
     * @param supportPay
     */
    public void setSupportPay(Integer supportPay) {
        this.supportPay = supportPay;
    }

    /**
     * @return shipping_time
     */
    public String getShippingTime() {
        return shippingTime;
    }

    /**
     * @param shippingTime
     */
    public void setShippingTime(String shippingTime) {
        this.shippingTime = shippingTime == null ? null : shippingTime.trim();
    }

    /**
     * 获取wm_meituan_poi.id
     *
     * @return mt_search_poi_id - wm_meituan_poi.id
     */
    public Long getMtSearchPoiId() {
        return mtSearchPoiId;
    }

    /**
     * 设置wm_meituan_poi.id
     *
     * @param mtSearchPoiId wm_meituan_poi.id
     */
    public void setMtSearchPoiId(Long mtSearchPoiId) {
        this.mtSearchPoiId = mtSearchPoiId;
    }
}