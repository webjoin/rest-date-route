package com.viewt.rest.data.util;

import com.viewt.rest.AnjunkeBootstap;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Elijah on 21/7/2017.
 */
public class CodeUtil {

    public static String sha1(String str) {
        if (null == str || 0 == str.length()) {
            return null;
        }
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String sha256(final String strText) {
        return SHA(strText, "SHA-256");
    }

    /**
     * 传入文本内容，返回 SHA-512 串
     *
     * @param strText
     * @return
     */
    public static String sha512(final String strText) {
        return SHA(strText, "SHA-512");
    }

    /**
     * &#x5b57;&#x7b26;&#x4e32; SHA &#x52a0;&#x5bc6;
     *
     * @return
     */
    private static String SHA(final String strText, final String strType) {
        // 返回值
        String strResult = null;

        // 是否是有效字符串
        if (strText != null && strText.length() > 0) {
            try {
                // SHA 加密开始
                // 创建加密对象 并傳入加密類型
                MessageDigest messageDigest = MessageDigest.getInstance(strType);
                // 传入要加密的字符串
                messageDigest.update(strText.getBytes());
                // 得到 byte 類型结果
                byte byteBuffer[] = messageDigest.digest();

                // 將 byte 轉換爲 string
                StringBuffer strHexString = new StringBuffer();
                // 遍歷 byte buffer
                for (int i = 0; i < byteBuffer.length; i++) {
                    String hex = Integer.toHexString(0xff & byteBuffer[i]);
                    if (hex.length() == 1) {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                // 得到返回結果
                strResult = strHexString.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        return strResult;
    }

    public static String md5(String str) {
        //确定计算方法
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String urlDecode(String content) {
        try {
            return URLDecoder.decode(content, "utf-8");
        } catch (UnsupportedEncodingException e) {
            return content;
        }
    }

    public static String urlEecode(String content) {
        try {
            return URLEncoder.encode(content, "utf-8");
        } catch (UnsupportedEncodingException e) {
            return content;
        }
    }

    /**
     * base 64 encode
     *
     * @return 编码后的base 64 code
     */
    public static String base64Encode(String code) {
        byte[] bytes;
        try {
            bytes = code.getBytes("utf-8");
            return new BASE64Encoder().encode(bytes);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * base 64 decode
     *
     * @param base64Code 待解码的base 64 code
     * @return 解码后的byte[]
     * @throws Exception
     */
    public static byte[] base64Decode(String base64Code) throws Exception {
        return (base64Code == null || base64Code.isEmpty()) ? null : new BASE64Decoder().decodeBuffer(base64Code);
    }

    public static void main(String[] args) {
        String plain = "7ed946add0b498470df373dae8bc95a2";
        int version = 2740;
        String s = sha1(plain);
        System.out.println(s);
        s = sha256(plain);
        System.out.println(s);
        s = sha512(plain);
        System.out.println(s);
        s = md5("123456");
        System.out.println(s);
        s = base64Encode("123456");
        System.out.println(s);

        plain = "3f20912f215f421b6f8dd454b7455e9e";

        String getet = AnjunkeBootstap.getet(plain, version);
        System.out.println(getet);
//        5f092f62a315a01e16f3b5525e7b8ff91274db6a
//        5f092f62a315a01e16f3b5525e7b8ff91274db6a
    }
}
