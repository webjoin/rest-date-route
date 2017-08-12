package com.viewt.rest.data;

import com.viewt.rest.ElemeBootstrapBat;
import com.viewt.rest.data.util.Cons;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Elijah on 18/7/2017.
 * 点评明细数据
 */
public class DpShopBootstrapBat {



    public static void main(String[] args) throws IOException {


        String path1 = Cons.USER_DIR;
        int start;
        int end;
        int step = 187_500;
        for (int i = 1; i <= 3; i++) {
            start = (i - 1) * step + 1;
            end = step * i;
            List<String> subKeys = new ArrayList<>(Arrays.asList(start + " " + end + " dp-shop-list-4-id"));

            ElemeBootstrapBat.newFile("dp-shop", subKeys.toArray(new String[subKeys.size()]), i, path1, DpShopBootstrap.class);
        }
    }

}
