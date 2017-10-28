package io.renren.encryption;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class CryptUtil {
    /**
     * 暗号化
     * @param src 暗号化対象の文字列
     * @param key 暗号化に使用する文字列（128bit）
     * @param iv 暗号化に使用する初期ベクトル（Base64エンコードされた値）
     */
    public static String encrypt(String src, String key, String iv) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            Key skey = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec param = new IvParameterSpec(decodeBase64(iv));
            cipher.init(Cipher.ENCRYPT_MODE, skey, param);
            return encodeBase64(cipher.doFinal(src.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 復号化
     * @param src 復号化対象の文字列（Base64デコードされた状態）
     * @param key 暗号化の際に使用した文字列（128bit）
     * @param iv 暗号化で使用した初期ベクトル（Base64エンコードされた値）
     */
    public static String decrypt(byte[] src, String key, String iv) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            Key skey = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec param = new IvParameterSpec(decodeBase64(iv));
            cipher.init(Cipher.DECRYPT_MODE, skey, param);
            return new String(cipher.doFinal(src));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] decodeBase64(String value) throws Exception {
        return new BASE64Decoder().decodeBuffer(value);
    }

    private static String encodeBase64(byte[] value) throws Exception {
        return new BASE64Encoder().encodeBuffer(value);
    }

    public static void main(String[] args) {
        String str = "Z8LSq0wWwB5v+6YJzurcP463H3F12iZh74fDj4S74oUH4EONkiKb2FmiWUbtFh97GG/c/lbDE47mvw6j94yXxKHOpoqu6zpLKMKPcOoSppcVWb2q34qENBJkudXUh4MWcreondLmLL2UyydtFKuU9Sa5VgY/CzGaVGJABK2ZR94=";
        System.out.println(CryptUtil.encrypt("hello",str,"1111111111111111"));
    }
}
