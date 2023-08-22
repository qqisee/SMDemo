package com.netsec.sm.service.impl;

import com.netsec.sm.service.SM4Service;
import lombok.var;
import org.bouncycastle.crypto.engines.SM4Engine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Arrays;

@Service
public class SM4ServiceImpl implements SM4Service {


    static {
        Security.addProvider(new BouncyCastleProvider());
    }
    public static final int BLOCK_SIZE = 16;
    private static SecretKeySpec ks = new SecretKeySpec("sm4EncryptionKey".getBytes(StandardCharsets.UTF_8), "SM4");  // key has 16 bytes
    private static IvParameterSpec iv = new IvParameterSpec("abcdefghabcdefgh".getBytes(StandardCharsets.UTF_8)); // iv has 16 bytes
    private static IvParameterSpec shortIv = new IvParameterSpec("abcdefgh".getBytes(StandardCharsets.UTF_8)); // CTR support >= 8bytes iv
    private static byte[] aad = "abcdefgh".getBytes(StandardCharsets.UTF_8);
    private static String plainText = "helloworldhellow";  // 16bytes for NoPadding
    private static String shortPlainText = "helloworld"; // 5 bytes for padding
    @Override
    public byte[] sm4Encrypt(String plainText, String algo,byte[] expectRes) throws Exception {
        Cipher encryptCipher = Cipher.getInstance(algo);
        if (algo.contains("ECB")) {
            encryptCipher.init(Cipher.ENCRYPT_MODE, ks);
        } else if (algo.contains("GCM")) {
            GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(96, iv.getIV());
            encryptCipher.init(Cipher.ENCRYPT_MODE, ks, gcmParameterSpec);
            encryptCipher.updateAAD(aad);
        } else {
            encryptCipher.init(Cipher.ENCRYPT_MODE, ks, iv);
        }
        byte[] cipherText = encryptCipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

//        Cipher decryptCipher = Cipher.getInstance(algo);
//        decryptCipher.init(Cipher.DECRYPT_MODE, ks, encryptCipher.getParameters());
//        if (algo.contains("GCM")) {
//            decryptCipher.updateAAD(aad);
//        }
//        String decryptPlainText = new String(decryptCipher.doFinal(cipherText));
//        if (!plainText.equals(decryptPlainText)) {
//            throw new RuntimeException("sm4 decryption failed, algo = " + algo);
//        }
        return cipherText;
    }



    @Override
    public byte[] sm4Decrypt(byte[] cipherText, String algo,byte[] expectRes) throws Exception {
        Cipher decryptCipher = Cipher.getInstance(algo);
        if (algo.contains("ECB")) {
            decryptCipher.init(Cipher.DECRYPT_MODE, ks);
        } else if (algo.contains("GCM")) {
            GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(96, iv.getIV());
            decryptCipher.init(Cipher.DECRYPT_MODE, ks, gcmParameterSpec);
            decryptCipher.updateAAD(aad);
        } else {
            decryptCipher.init(Cipher.DECRYPT_MODE, ks, iv);
        }
        byte[] decryptPlainText = decryptCipher.doFinal(cipherText);
        return decryptPlainText;
    }

    @Override
    public byte[] sm4Encrypt(byte[] in, String key) {
        return new byte[0];
    }
}
