package com.viewt.rest.data.util;

import org.apache.logging.log4j.LogManager;

/**
 * Created by Elijah on 15/7/2017.
 */
public class Log4jUtil {

    public static void reconfigure(String areaIndex) {
        System.setProperty("area", areaIndex);
        ((org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false)).reconfigure();
    }


}

