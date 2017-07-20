package com.viewt.rest.data.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elijah on 17/7/2017.
 */
public class FileUtil {

    public static  List<String> readFile(String path){
        return readFile(path, null);
    }
    public static  List<String> readFile(String path, String prefix) {
        List<String> list = new ArrayList<>();
        InputStreamReader fr = null;
        BufferedReader br = null;
        try {
            fr = new InputStreamReader(new FileInputStream(path), "utf-8");
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (prefix == null) {
                    list.add(line);
                } else {
                    int ix = line.indexOf(prefix);
                    if (ix > -1) {
                        list.add(line.substring(ix));
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}
