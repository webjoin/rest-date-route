package com.viewt.massage.day;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.viewt.rest.data.bean.MassageActivityBean;
import com.viewt.rest.data.bean.RespBean;
import com.viewt.rest.data.service.UrlService;
import com.viewt.rest.data.service.impl.UrlServiceImpl;
import com.viewt.rest.data.util.Cons;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tangyu
 * @since 2018-01-09 09:58
 * -Darea=massage-activety-data
 */
public class MassageActivityDataApplication {
    static Logger logger = LoggerFactory.getLogger(MassageActivityDataApplication.class);

    public static void main(String[] args) {

        String massageActivetyFilePath = Cons.USER_DIR + "/logs/other/day-massage-activety.log";

        try {
            Map<String, MassageActivityBean> map = new HashMap<>();
            List<String> strings = Files.readAllLines(Paths.get(massageActivetyFilePath));
            strings.forEach(x -> {
                MassageActivityBean bean = JSON.parseObject(x, MassageActivityBean.class);
                String brandName = bean.getBrandName();
                if (Objects.nonNull(brandName)) {
                    map.put(brandName, bean);
                }
            });
            Collection<MassageActivityBean> values = map.values();
            logger.info("{},{},{},{}","活动ID","品牌名称","采集地址", "日期");
            values.forEach(x -> logger.info("{},{},{},{}"
                    , x.getActivityId()
                    , x.getBrandName()
                    , x.getUrl()
                    , x.getDate()
                    )
            );

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
