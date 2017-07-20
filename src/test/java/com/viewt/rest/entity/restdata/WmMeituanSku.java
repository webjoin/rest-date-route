package com.viewt.rest.entity.restdata;

import javax.persistence.*;

@Table(name = "wm_meituan_sku")
public class WmMeituanSku {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "origin_price")
    private Double originPrice;

    @Column(name = "box_num")
    private Double boxNum;

    private String picture;

    private Double price;

    private Integer stock;

    @Column(name = "box_price")
    private Double boxPrice;

    @Column(name = "spu_id")
    private Long spuId;

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
     * @return origin_price
     */
    public Double getOriginPrice() {
        return originPrice;
    }

    /**
     * @param originPrice
     */
    public void setOriginPrice(Double originPrice) {
        this.originPrice = originPrice;
    }

    /**
     * @return box_num
     */
    public Double getBoxNum() {
        return boxNum;
    }

    /**
     * @param boxNum
     */
    public void setBoxNum(Double boxNum) {
        this.boxNum = boxNum;
    }

    /**
     * @return picture
     */
    public String getPicture() {
        return picture;
    }

    /**
     * @param picture
     */
    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    /**
     * @return price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return stock
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * @param stock
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * @return box_price
     */
    public Double getBoxPrice() {
        return boxPrice;
    }

    /**
     * @param boxPrice
     */
    public void setBoxPrice(Double boxPrice) {
        this.boxPrice = boxPrice;
    }

    /**
     * @return spu_id
     */
    public Long getSpuId() {
        return spuId;
    }

    /**
     * @param spuId
     */
    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }
}