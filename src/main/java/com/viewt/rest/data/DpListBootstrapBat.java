package com.viewt.rest.data;

import com.viewt.rest.BaseBootstrap;
import com.viewt.rest.ElemeBootstrapBat;
import com.viewt.rest.data.util.Cons;
import com.viewt.rest.data.util.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Elijah on 18/7/2017.
 * -Darea=shop-list-bat
 */
public class DpListBootstrapBat extends BaseBootstrap {

//    static String categoryId = "102\t; 103\t; 104\t; 106\t; 107\t; 110\t; 111\t; 112,-3613; 112,1831; 112,1832; 112,1833; 112,1834; 112,1835; 112,1836; 112,1837; 112,1838; 112,1839; 112,1840; 112,8085; 112,8373; 112,8374; 112,8375; 112,15652; 112,27090; 112,28549; 112,28887; 112,104; 112,105; 112,1847; 112,1846; 112,1830; 112,15651; 112,1842; 112,8087; 112,15650; 112,27464; 112,1843; 112,65208; 112,70185; 112,27090; 112,70208; 112,15649; 112,27054; 112,1845; 112,1844; 112,70523; 112,70175; 112,106; 112,107; 112,108; 112,109; 114\t; 115\t; 116\t; 117,-3613; 117,104; 117,105; 117,106; 117,107; 117,108; 117,109; 118,-3613; 118,104; 118,105; 118,106; 118,107; 118,108; 118,109; 132\t; 219\t; 224\t; 247\t; 249\t; 250\t; 251\t; 508\t; 733\t; 1338\t; 1783\t; 1817\t;";

    public static void main(String[] args) {
        DpListBootstrapBat bat = new DpListBootstrapBat();
        bat.start(args);
    }


    /**
     * 解析生成好的日志文件
     */
    private List<String> parseCategoryQtyLog() {
        String s = Cons.USER_DIR + "/logs/other/other-dp_category_qty.log";
        List<String> list = FileUtil.readFile(s);
        List<String> rs = new ArrayList<>(list.size());
        for (String s1 : list) {
            String[] split = s1.split(",");
            int length = split.length;

            String cityId = split[0];
            String categoryId = split[length - 3];
            String regionId = split[length - 5];
            rs.add(cityId + " " + categoryId + " " + regionId);
        }
        return rs;
    }

    @Override
    public void crawl(String[] args) {
        if (null == args || 0 == args.length) {
            List<String> rs = parseCategoryQtyLog();
            args = rs.toArray(new String[rs.size()]);
        }
        generate(args);
    }

    private void generate(String[] args) {
        String path1 = Cons.USER_DIR;
        int size = args.length;
        int num = 16;
        int page = (int) Math.ceil((double) size / num);
        for (int i = 0; i < num; i++) {
            int start = i * page;
            int end = start + page;
            if (start > size) {
                break;
            }
            if (end > size) {
                end = size;
            }
            String[] strings = Arrays.copyOfRange(args, start, end);
            //TODO to do thing ...
            try {
                ElemeBootstrapBat.newFile("dp-shop-list", strings, i, path1, DpListBootstrap.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
