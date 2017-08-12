package com.viewt.rest.mapper.restdata;

import com.viewt.rest.entity.restdata.DpShopsBean;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DpShopsBeanMapper extends Mapper<DpShopsBean> {

    int inserts(List<DpShopsBean> list);
}