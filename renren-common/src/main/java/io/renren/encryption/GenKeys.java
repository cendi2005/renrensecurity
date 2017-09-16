package io.renren.encryption;

/**
 * Created by admin on 2017/7/20.
 */
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.util.Date;
import java.util.UUID;

public class GenKeys {
    public static void main(String[] args) throws Exception {

//        String key = "!@#$%^&*()_+"+ UUID.randomUUID()+new Date().toString()+ComputerIdentifierUtil.generateLicenseKey();
//
//        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//        SecureRandom secureRandom = new SecureRandom(key.toString().getBytes());
//        keyPairGenerator.initialize(1024, secureRandom);
//        KeyPair keyPair = keyPairGenerator.genKeyPair();
//        String publicKeyFilename = "/Users/admin/Desktop/SourceCode/encryption/src/main/webapp/WEB-INF/licence/publicKeyFile.licence";
//        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
//        FileOutputStream fos = new FileOutputStream(publicKeyFilename);
//        fos.write(publicKeyBytes);
//        fos.close();
//        String privateKeyFilename = "/Users/admin/Desktop/SourceCode/encryption/src/main/webapp/WEB-INF/licence/privateKeyFile.licence";
//        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
//        fos = new FileOutputStream(privateKeyFilename);
//        fos.write(privateKeyBytes);
//        fos.close();
    }
}