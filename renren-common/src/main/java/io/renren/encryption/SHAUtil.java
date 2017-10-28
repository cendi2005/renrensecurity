package io.renren.encryption;

/**
 * Created by admin on 2017/7/20.
 */

import qiniu.happydns.util.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 采用SHA加密
 */
public class SHAUtil {
    /***
     * SHA加密 生成40位SHA码
     * @return 返回40位SHA码
     */
//    public static String shaEncode(String inStr) throws Exception {
//        MessageDigest sha = null;
//        try {
//            sha = MessageDigest.getInstance("SHA");
//        } catch (Exception e) {
//            System.out.println(e.toString());
//            e.printStackTrace();
//            return "";
//        }
//
//        byte[] byteArray = inStr.getBytes("UTF-8");
//        byte[] md5Bytes = sha.digest(byteArray);
//        StringBuffer hexValue = new StringBuffer();
//        for (int i = 0; i < md5Bytes.length; i++) {
//            int val = ((int) md5Bytes[i]) & 0xff;
//            if (val < 16) {
//                hexValue.append("0");
//            }
//            hexValue.append(Integer.toHexString(val));
//        }
//        return hexValue.toString();
//    }

    /**
     * 测试主函数
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        String str = new String("sha");
        System.out.println("原始：" + str);
        System.out.println("SHA后：" + getSHAString(str));
    }


    public static String getSHAString(String str){
        MessageDigest messageDigest;
        String encdeStr = "";
        try{

            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = messageDigest.digest(str.getBytes("UTF-8"));
            encdeStr = new String(bytes,"UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return encdeStr;
    }





}

