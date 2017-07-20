package com.viewt.rest.entity.restdata;

import javax.persistence.*;

@Table(name = "wm_meituan_discount")
public class WmMeituanDiscount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer sequence;

    private Integer type;

    @Column(name = "use_icon_from_server")
    private Integer useIconFromServer;

    private String info;

    @Column(name = "display_code")
    private Integer displayCode;

    @Column(name = "shop_id")
    private Long shopId;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return sequence
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     * @param sequence
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    /**
     * @return type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return use_icon_from_server
     */
    public Integer getUseIconFromServer() {
        return useIconFromServer;
    }

    /**
     * @param useIconFromServer
     */
    public void setUseIconFromServer(Integer useIconFromServer) {
        this.useIconFromServer = useIconFromServer;
    }

    /**
     * @return info
     */
    public String getInfo() {
        return info;
    }

    /**
     * @param info
     */
    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    /**
     * @return display_code
     */
    public Integer getDisplayCode() {
        return displayCode;
    }

    /**
     * @param displayCode
     */
    public void setDisplayCode(Integer displayCode) {
        this.displayCode = displayCode;
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