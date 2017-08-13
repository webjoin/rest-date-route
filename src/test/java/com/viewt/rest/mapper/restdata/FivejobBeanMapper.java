package com.viewt.rest.mapper.restdata;

import com.viewt.rest.entity.restdata.FivejobBean;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface FivejobBeanMapper extends Mapper<FivejobBean> {

    int inserts(List<FivejobBean> list);

}