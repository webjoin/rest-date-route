package com.viewt.massage.day;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.viewt.rest.data.bean.MassageActivityBean;
import com.viewt.rest.data.bean.MassageShopBean;
import com.viewt.rest.data.bean.RespBean;
import com.viewt.rest.data.service.UrlService;
import com.viewt.rest.data.service.impl.UrlServiceImpl;
import com.viewt.rest.data.util.Cons;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tangyu
 * @since 2018-01-09 09:58
 * -Darea=massage-shop-data
 */
public class MassageShopDataApplication {
    static Logger logger = LoggerFactory.getLogger(MassageActivityDataApplication.class);

    public static void main(String[] args) {
        String massageShopFilePath = Cons.USER_DIR + "/logs/other/day-massage-shop.log";

        try {
            logger.info("{},{},{},{},{},{}", "点评门店id","店名","人均","商圈","是否支持预订","是否支持团购");
            List<String> strings = Files.readAllLines(Paths.get(massageShopFilePath));
            strings.forEach(x -> {
                MassageShopBean bean = JSON.parseObject(x, MassageShopBean.class);
                logger.info(
                        "{},{},{},{},{},{},{}",
                        bean.getShopId(),
                        bean.getShopName(),
                        bean.getPriceText(),
                        bean.getRegionName(),
                        bean.getBookable(),
                        bean.getDealable(),
                        bean.getDate()
                );
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
