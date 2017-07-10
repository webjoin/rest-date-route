package com.viewt.rest.data.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Elijah on 7/6/2017.
 */
public class RespBean {
    private String content;
    private Map<String,String> cookies = new HashMap<>();

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public void setCookies(Map<String, String> cookies) {
        this.cookies = cookies;
    }
}
