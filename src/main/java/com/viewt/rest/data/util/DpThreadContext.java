package com.viewt.rest.data.util;


/**
 * Created by Elijah on 17/6/2017.
 */
public class DpThreadContext {

    private static final ThreadLocal<String > threadCookie = new ThreadLocal<String>(){
        @Override
        public String initialValue() {
            return "cy=1; cye=shanghai";
        }
    };

    public static String getThreadCookie() {
        return threadCookie.get();
    }

    public static void setThreadCookie(String cookie) {
        threadCookie.set(cookie);
    }
}
