package com.viewt.rest.entity.restdata;

import javax.persistence.*;

@Table(name = "rest_busi_circle_shop_rel")
public class RestBusiCircleShopRelBean {
    @Id
    @Column(name = "bc_shop_rel_id")
    private Long bcShopRelId;

    @Column(name = "bc_id")
    private Long bcId;

    @Column(name = "shop_id")
    private Long shopId;

    /**
     * @return bc_shop_rel_id
     */
    public Long getBcShopRelId() {
        return bcShopRelId;
    }

    /**
     * @param bcShopRelId
     */
    public void setBcShopRelId(Long bcShopRelId) {
        this.bcShopRelId = bcShopRelId;
    }

    /**
     * @return bc_id
     */
    public Long getBcId() {
        return bcId;
    }

    /**
     * @param bcId
     */
    public void setBcId(Long bcId) {
        this.bcId = bcId;
    }

    /**
     * @return shop_id
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * @param shopId
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}