package io.renren.encryption;

/**
 * Created by admin on 2017/7/20.
 */

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2016/10/15.
 */
public class RSAsignature1 {
    private static final String PUBLIC_KEY = "publickey";
    private static final String PRIVATE_KEY = "privatekey";

    /*
     *RSA生成密钥对
     */
    public static Map<String,Object> GenerateKeyPair(String keyType, int keySize) throws Exception {
        KeyPairGenerator pairGenerator = KeyPairGenerator.getInstance(keyType);
        pairGenerator.initialize(keySize);
        //生成秘钥对
        KeyPair keyPair = pairGenerator.generateKeyPair();
        //获得公钥和私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put(PUBLIC_KEY,publicKey);
        map.put(PRIVATE_KEY,privateKey);
        return  map;
    }
    /*
    *从Map中获得密钥对
    */
    public static RSAPublicKey GetPublicKey(Map<String,Object> map){
        RSAPublicKey publicKey = (RSAPublicKey) map.get(PUBLIC_KEY);
        return publicKey;
    }
    public static RSAPrivateKey GetPrivateKey(Map<String,Object> map){
        RSAPrivateKey privateKey = (RSAPrivateKey) map.get(PRIVATE_KEY);
        return privateKey;
    }
    /*
     *私钥对原始数据进行签名
     */
    public static byte[] signature(RSAPrivateKey privateKey ,byte[] data,String keyType) throws Exception {
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(privateKey);
        signature.update(data);
        return signature.sign();
    }
    /*
     * 公钥、原始数据、原始签名数据进行验证
     */
    public static boolean verify(RSAPublicKey publicKey,byte[] data,byte[] sign) throws Exception {
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(publicKey);
        signature.update(data);
        return signature.verify(sign);
    }

    public static void main(String[] args) throws Exception {
        Map<String,Object> map = GenerateKeyPair("RSA",1024);
        RSAPublicKey publicKey = RSAsignature1.GetPublicKey(map);
        RSAPrivateKey privateKey = RSAsignature1.GetPrivateKey(map);
        byte[] sign = signature(privateKey,"abc".getBytes(),"RSA");
        boolean verify = verify(publicKey,"abc".getBytes(),sign);

        System.out.println(fromBytesToHexString(publicKey.getEncoded()));
        System.out.println(fromBytesToHexString(privateKey.getEncoded()));
        System.out.println(fromBytesToHexString(sign));
        System.out.println(verify);
    }
    public static String fromBytesToHexString(byte[] data){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            String s = Integer.toHexString(0xFF & data[i]);
            if (s.length() == 1){
                str.append(0).append(s);
            }else {
                str.append(s);
            }
        }
        return str.toString();
    }
}