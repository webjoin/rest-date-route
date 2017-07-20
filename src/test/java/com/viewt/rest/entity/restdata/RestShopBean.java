package com.viewt.rest.entity.restdata;

import java.util.Date;
import javax.persistence.*;

@Table(name = "rest_shop")
public class RestShopBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 店名称
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 店地址
     */
    private String address;

    /**
     * 店状态
     */
    private Byte status;

    /**
     * 归属菜系ID
     */
    @Column(name = "category_id")
    private Long categoryId;

    /**
     * 归属商圈ID
     */
    @Column(name = "bc_id")
    private Long bcId;

    /**
     * 品牌ID
     */
    @Column(name = "brand_id")
    private Long brandId;

    /**
     * 新建日期
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 更新日期
     */
    @Column(name = "update_date")
    private Date updateDate;

    private String longitude;

    private String latitude;

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
     * 获取店名称
     *
     * @return NAME - 店名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置店名称
     *
     * @param name 店名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取店地址
     *
     * @return address - 店地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置店地址
     *
     * @param address 店地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取店状态
     *
     * @return status - 店状态
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置店状态
     *
     * @param status 店状态
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取归属菜系ID
     *
     * @return category_id - 归属菜系ID
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * 设置归属菜系ID
     *
     * @param categoryId 归属菜系ID
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取归属商圈ID
     *
     * @return bc_id - 归属商圈ID
     */
    public Long getBcId() {
        return bcId;
    }

    /**
     * 设置归属商圈ID
     *
     * @param bcId 归属商圈ID
     */
    public void setBcId(Long bcId) {
        this.bcId = bcId;
    }

    /**
     * 获取品牌ID
     *
     * @return brand_id - 品牌ID
     */
    public Long getBrandId() {
        return brandId;
    }

    /**
     * 设置品牌ID
     *
     * @param brandId 品牌ID
     */
    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    /**
     * 获取新建日期
     *
     * @return create_date - 新建日期
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置新建日期
     *
     * @param createDate 新建日期
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取更新日期
     *
     * @return update_date - 更新日期
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置更新日期
     *
     * @param updateDate 更新日期
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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
}