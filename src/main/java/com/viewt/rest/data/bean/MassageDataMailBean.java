package com.viewt.rest.data.bean;

import com.viewt.rest.data.util.Massage;

import java.util.Collection;

/**
 * @author tangyu
 * @since 2018-01-15 22:48
 */
public class MassageDataMailBean {

//    public MassageDataMailBean(Collection<MassageDataBean> massageActivityDataBean, Collection<MassageDataBean> massageShopDataBean) {
//        this.massageActivityDataBeans = massageActivityDataBean;
//        this.massageShopDataBeans = massageShopDataBean;
//    }

    @Massage("商户")
    private Collection<MassageShopBean> massageShopBeans;

    @Massage("活动")
    private Collection<MassageActivityBean> massageActivityBeans;

    @Massage("团购数据")
    private Collection<MassageDataBean> massageActivityDataBeans;

    @Massage("门店预订数据")
    private Collection<MassageDataBean> massageShopDataBeans;

    public Collection<MassageShopBean> getMassageShopBeans() {
        return massageShopBeans;
    }

    public void setMassageShopBeans(Collection<MassageShopBean> massageShopBeans) {
        this.massageShopBeans = massageShopBeans;
    }

    public Collection<MassageActivityBean> getMassageActivityBeans() {
        return massageActivityBeans;
    }

    public void setMassageActivityBeans(Collection<MassageActivityBean> massageActivityBeans) {
        this.massageActivityBeans = massageActivityBeans;
    }

    public Collection<MassageDataBean> getMassageActivityDataBeans() {
        return massageActivityDataBeans;
    }

    public void setMassageActivityDataBeans(Collection<MassageDataBean> massageActivityDataBeans) {
        this.massageActivityDataBeans = massageActivityDataBeans;
    }

    public Collection<MassageDataBean> getMassageShopDataBeans() {
        return massageShopDataBeans;
    }

    public void setMassageShopDataBeans(Collection<MassageDataBean> massageShopDataBeans) {
        this.massageShopDataBeans = massageShopDataBeans;
    }
}
