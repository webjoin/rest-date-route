package com.viewt.rest.data.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.zip.GZIPInputStream;

/**
 * Created by Elijah on 17/7/2017.
 */
public class FileUtil {

    public static List<String> readFile(String path) {
        return readFile(path, null);
    }

    public static List<String> readFile(String path, String prefix) {
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

    public static List<JSONObject> readFile2Json(String path) {
        List<JSONObject> list = new ArrayList<>();
        InputStreamReader fr = null;
        BufferedReader br = null;
        try {
            fr = new InputStreamReader(new FileInputStream(path), "utf-8");
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                list.add(JSON.parseObject(line));
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

    public static final int BUFFER = 1024;
    public static final String GZ_POSTFIX = ".gz";

    /**
     * @param gzipFile gz文件
     * @param destDis  解压地方
     */
    public static void unGzip(File gzipFile, String destDis) throws IOException {
        String empty = "";
        String name = gzipFile.getName();
        mkdirs(destDis);
        String gzipPath = Optional.of(destDis).orElse(gzipFile.getParent()) + File.separator + name.replace(GZ_POSTFIX, empty);
        try (GZIPInputStream gis = new GZIPInputStream(new FileInputStream(gzipFile))) {
            try (FileOutputStream fos = new FileOutputStream(gzipPath)) {
                int count;
                byte data[] = new byte[BUFFER];
                while ((count = gis.read(data, 0, BUFFER)) != -1) {
                    fos.write(data, 0, count);
                }
                fos.flush();
            }
        }
    }

    private static void mkdirs(String destDis) throws IOException {
        if (Objects.nonNull(destDis)) {
            Path path = Paths.get(destDis);
            if (Files.notExists(path)) {
                Files.createDirectories(path);
            }
        }

    }

    public static void unGzip(File gzipFile) throws IOException {
        unGzip(gzipFile, null);
    }

}
