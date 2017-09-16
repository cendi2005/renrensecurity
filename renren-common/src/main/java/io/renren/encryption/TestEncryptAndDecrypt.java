package io.renren.encryption;

/**
 * Created by admin on 2017/7/20.
 */

import javax.crypto.Cipher;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class TestEncryptAndDecrypt {
    public static void main(String[] args) throws Exception {
        String input = "abc";

        Cipher cipher = Cipher.getInstance("RSA");

        RSAPublicKey pubKey = (RSAPublicKey) PublicKeyReader.get(EncryptionConstant.PUBLIC_KEY_LOCATION);

        RSAPrivateKey privKey = null;
        try {
            privKey = (RSAPrivateKey) PrivateKeyReader.get(EncryptionConstant.PRIVATE_KEY_LOCATION);
        } catch (Exception e) {
            System.out.println("证书不存在或者已经损坏");
            return;
        }

        cipher.init(Cipher.ENCRYPT_MODE, pubKey);

        byte[] cipherText = cipher.doFinal(input.getBytes());

        //加密后的东西
        System.out.println("cipher: " + new String(cipherText));

        //开始解密
        cipher.init(Cipher.DECRYPT_MODE, privKey);

        byte[] plainText = cipher.doFinal(cipherText);
        String after = new String(plainText);
        System.out.println("plain : " + new String(plainText));

        if(!input.equals(after)){
            System.out.println("解密失败");
        }

    }

}
