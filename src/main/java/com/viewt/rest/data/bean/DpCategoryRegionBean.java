package com.viewt.rest.data.bean;

/**
 * Created by Elijah on 24/7/2017.
 */
public class DpCategoryRegionBean {


    public DpCategoryRegionBean(DpCategoryNavsBean dpCategoryNavsBean, DpRegionNavsBean dpRegionNavsBean) {
        this.dpCategoryNavsBean = dpCategoryNavsBean;
        this.dpRegionNavsBean = dpRegionNavsBean;
    }

    private DpCategoryNavsBean dpCategoryNavsBean;
    private DpRegionNavsBean dpRegionNavsBean;

    public DpCategoryNavsBean getDpCategoryNavsBean() {
        return dpCategoryNavsBean;
    }

    public void setDpCategoryNavsBean(DpCategoryNavsBean dpCategoryNavsBean) {
        this.dpCategoryNavsBean = dpCategoryNavsBean;
    }

    public DpRegionNavsBean getDpRegionNavsBean() {
        return dpRegionNavsBean;
    }

    public void setDpRegionNavsBean(DpRegionNavsBean dpRegionNavsBean) {
        this.dpRegionNavsBean = dpRegionNavsBean;
    }
}
