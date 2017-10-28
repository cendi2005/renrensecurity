package io.renren.encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;

public class Anything {
    private static final String ALGO = "AES";
    //generate 128bit key
    private static final String keyStr = "Z8LSq0wWwB5v+6YJzurcP463H3F12iZh74fDj4S74oUH4EONkiKb2FmiWUbtFh97GG/c/lbDE47mvw6j94yXxKHOpoqu6zpLKMKPcOoSppcVWb2q34qENBJkudXUh4MWcreondLmLL2UyydtFKuU9Sa5VgY/CzGaVGJABK2ZR94=";

    private static Key generateKey() throws Exception {
        byte[] keyValue = keyStr.getBytes("UTF-8");
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        keyValue = sha.digest(keyValue);
        System.out.println(keyValue.length);
        keyValue = Arrays.copyOf(keyValue, 128); // use only first 128 bit
        System.out.println(keyValue.length);
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
    }

    public static String encrypt(String Data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = DatatypeConverter.printBase64Binary(encVal);
        return encryptedValue;
    }

    public static String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = DatatypeConverter.parseBase64Binary(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

    public static void main(String[] args) throws Exception{
        String data = "hello";
        String en = Anything.encrypt(data);
        System.out.println("en:"+en);
        System.out.println("der:"+Anything.decrypt(en));
    }
}
