package com.viewt.rest.mapper.restdata;

import com.viewt.rest.entity.restdata.AnjukeBean;
import com.viewt.rest.entity.restdata.DpShopListBean;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AnjukeBeanMapper extends Mapper<AnjukeBean> {

    int inserts(List<AnjukeBean> list);


}