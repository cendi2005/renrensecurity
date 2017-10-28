package io.renren.encryption;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
public class AES256Encryption {
    /**
     * http://www.jb51.net/article/106173.htm
     * */
    public static final String KEY_ALGORITHM = "AES";
    public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS7Padding";

    public static byte[] initkey() throws Exception {
        //实例化密钥生成器
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM, "BC");
        kg.init(256);
        SecretKey secretKey = kg.generateKey();
        return secretKey.getEncoded();
    }

    public static byte[] initRootKey() throws Exception {
        return new byte[]{0x08, 0x08, 0x04, 0x0b, 0x02, 0x0f, 0x0b, 0x0c,
                0x01, 0x03, 0x09, 0x07, 0x0c, 0x03, 0x07, 0x0a, 0x04, 0x0f,
                0x06, 0x0f, 0x0e, 0x09, 0x05, 0x01, 0x0a, 0x0a, 0x01, 0x09,
                0x06, 0x07, 0x09, 0x0d};
    }

    public static Key toKey(byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
        return secretKey;
    }

    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        Key k = toKey(key);
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
        cipher.init(Cipher.ENCRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        Key k = toKey(key);
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
        cipher.init(Cipher.DECRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    public static void main(String[] args) throws Exception {

        String oriKey = "test";

        String str = "芸sweet";
        //打印原文
        System.out.println("原文：" + str);
        //密钥
        byte[] key;
        //生成随机密钥
        key = AES256Encryption.initkey();
        System.out.println("生成的key："+new String(key));
        //打印密钥
        //加密
        byte[] data = AES256Encryption.encrypt(str.getBytes(), key);
        //打印密文
        System.out.print("加密后：" + new String(data));
//解密密文
        data=AES256Encryption.decrypt(data,key);
        System.out.println("解密后:"+new String(data));
        //打印原文
    }

        }