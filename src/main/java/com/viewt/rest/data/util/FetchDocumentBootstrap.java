package com.viewt.rest.data.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 提取json数据
 */
public class FetchDocumentBootstrap {

    public static void main(String[] args) {
        String sourcePath = args[0];
        String destPath = args[1];

        File sourceFile = new File(sourcePath);
        File destFile = new File(destPath);
        if (!sourceFile.exists()) {
            throw new IllegalArgumentException(sourcePath + " 源文件没有找到");
        }
        if (destFile.exists()) {
            throw new IllegalArgumentException(sourcePath + " 目标文件已经存在");
        }
        List<String> list = new ArrayList<>();
        if (sourceFile.isFile())
            list = readFileLines(sourcePath);
        else if (sourceFile.isDirectory()) {
            File[] files = sourceFile.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    list.addAll(readFileLines(file.getAbsolutePath()));
                }
            }
        }
        dealList(list, sourceFile.getName(), destPath);
        if (osw != null) {
            try {
                osw.flush();
                osw.close();
                osw = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("done !");
    }

    static String objSymbolS = "{";
    static String objSymbolE = "}";

    static String arrSymbolS = "[{";
    static String arrSymbolE = "}]";

    static void dealList(List<String> list, String sourceFileName, String destPath) {
        int line = 0;
        for (String s : list) {
            line++;
            if (s == null || s.isEmpty()) continue;
            int ii = 0;
            int i = s.indexOf(objSymbolS);
            boolean isObj = false;
            boolean isArr = false;
            if (i > -1) {
                ii = s.lastIndexOf(objSymbolE);
                if (ii > -1 && ii > i) {
                    isObj = true;
                }
            }
            if (isObj) {
                s = s.substring(i, ii + 1);
                try {
                    JSONObject jsonObject = JSON.parseObject(s);
                    jsonObject.put("sourceFileName", sourceFileName);
                    writeLine(jsonObject.toString(), destPath);
                } catch (Exception e) {
                    System.out.println("第 " + line + "不是一个JSONObject对象");
                }
            } else {
                i = s.indexOf(arrSymbolS);
                if (i > -1) {
                    ii = s.lastIndexOf(arrSymbolE);
                    if (ii > -1 && ii > i) {
                        isArr = true;
                    }
                }
                if (isArr) {
                    s = s.substring(i, ii + 1);
                    try {
                        JSONArray jsonArray = JSON.parseArray(s);
                        Iterator<Object> iterator = jsonArray.iterator();
                        while (iterator.hasNext()) {
                            JSONObject jsonObject = (JSONObject) iterator.next();
                            jsonObject.put("sourceFileName", sourceFileName);
                            writeLine(jsonObject.toString(), destPath);
                        }
                    } catch (Exception e) {
                        System.out.println("第 " + line + "不是一个JSONArray对象");
                    }
                }
                if (!isArr && !isObj) {
                    System.out.println("第 " + line + "不是一个 JSONArray 或者 JSONObject 对象");
                }
            }
        }
    }

    static OutputStreamWriter osw = null;

    private static void writeLine(String lineContent, String destPath) {


        try {
            if (osw == null)
                osw = new OutputStreamWriter(new FileOutputStream(destPath, true));

            osw.write(lineContent + "\r\n");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static List<String> readFileLines(String path) {
        List<String> list = new ArrayList<>();
        InputStreamReader fr = null;
        BufferedReader br = null;
        try {
            fr = new InputStreamReader(new FileInputStream(path), "utf-8");
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                list.add(line);
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