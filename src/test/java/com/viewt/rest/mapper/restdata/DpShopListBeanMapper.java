package com.viewt.rest.mapper.restdata;

import com.viewt.rest.entity.restdata.DpShopListBean;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import java.util.List;

public interface DpShopListBeanMapper extends Mapper<DpShopListBean> {

    int inserts(List<DpShopListBean> list);


}