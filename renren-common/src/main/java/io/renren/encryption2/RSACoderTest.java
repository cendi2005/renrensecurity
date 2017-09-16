package io.renren.encryption2;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by admin on 2017/7/22.
 */
public class RSACoderTest {
    private String publicKey;
    private String privateKey;

    @Before
    public void setUp() throws Exception {
        Map<String, Object> keyMap = RSACoder.initKey();

        publicKey = RSACoder.getPublicKey(keyMap);
        FileUtils.writeByteArrayToFile(new File("/Users/admin/Desktop/SourceCode/encryption/target/classes/main/webapp/WEB-INF/licence/publicKey.licence"),publicKey.getBytes());
        privateKey = RSACoder.getPrivateKey(keyMap);
        FileUtils.writeByteArrayToFile(new File("/Users/admin/Desktop/SourceCode/encryption/target/classes/main/webapp/WEB-INF/licence/privateKey.licence"),privateKey.getBytes());

        System.err.println("公钥: \n" + publicKey);
        System.err.println("私钥： \n" + privateKey);
    }

    @Test
    public void test() throws Exception {
        System.err.println("公钥加密——私钥解密");
        String inputStr = "abc";
        byte[] data = inputStr.getBytes();

        byte[] encodedData = RSACoder.encryptByPublicKey(data, publicKey);

        byte[] decodedData = RSACoder.decryptByPrivateKey(encodedData,
                privateKey);

        String outputStr = new String(decodedData);
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
        assertEquals(inputStr, outputStr);

    }

    @Test
    public void testSign() throws Exception {
        System.err.println("私钥加密——公钥解密");
        String inputStr = "sign";
        byte[] data = inputStr.getBytes();

        byte[] encodedData = RSACoder.encryptByPrivateKey(data, privateKey);

        byte[] decodedData = RSACoder
                .decryptByPublicKey(encodedData, publicKey);

        String outputStr = new String(decodedData);
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
        assertEquals(inputStr, outputStr);

        System.err.println("私钥签名——公钥验证签名");
        /**
         * privateKey
         * publicKey
         * */

        // 产生签名
        String sign = RSACoder.sign(encodedData, privateKey);
        System.err.println("签名:\n" + sign);

        // 验证签名
        boolean status = RSACoder.verify(encodedData, publicKey, sign);
        System.err.println("状态:\n" + status);
        assertTrue(status);

    }

}