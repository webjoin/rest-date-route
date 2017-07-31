package com.viewt.rest.data.util;

import java.util.concurrent.TimeUnit;

/**
 * Created by Elijah on 19/7/2017.
 */
public class TimeUtil {

    public static void sleep(int second) {
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep(long milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
