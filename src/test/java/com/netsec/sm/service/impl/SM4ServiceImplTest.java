package com.netsec.sm.service.impl;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.SM4;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SM4ServiceImplTest {

    private static String plainText = "helloworldhellow";  // 16bytes for NoPadding
    @Autowired
    private SM4ServiceImpl sm4Service;

    @Test
    void sm4Encrypt() throws Exception {
        byte[] bytes = "1234567890123456".getBytes();
        byte[] sm4Encrypt = sm4Service.sm4Encrypt(bytes,"8888888888888888");
        System.out.println(ByteUtils.toHexString(sm4Encrypt));
        byte[] sm4Encrypt1 = sm4Service.sm4Encrypt(plainText, "SM4/CBC/NOPADDING", new byte[]{86, 69, 47, -115, -63, 54, 35, 24, -2, 114, 113, 102, 82, 20, 69, 59});
        System.out.println(Arrays.toString(sm4Encrypt1));

        byte[] sm4Decrypt = sm4Service.sm4Decrypt(new byte[]{86, 69, 47, -115, -63, 54, 35, 24, -2, 114, 113, 102, 82, 20, 69, 59}, "SM4/CBC/NOPADDING", plainText.getBytes());
        System.out.println(new String(sm4Decrypt));

    }

    @Test
    void hutoolSM4() {
        SM4 sm4 = new SM4(Mode.ECB, Padding.PKCS5Padding);
        byte[] nihaos = sm4.encrypt("nihao");
        System.out.println(ByteUtils.toHexString(nihaos));
    }
}
