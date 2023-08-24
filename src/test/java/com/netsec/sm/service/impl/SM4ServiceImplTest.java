package com.netsec.sm.service.impl;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.SM4;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;


@SpringBootTest
class SM4ServiceImplTest {

    private static String plainText = "helloworldhellow";  // 16bytes for NoPadding
    @Autowired
    private SM4ServiceImpl sm4Service;

    @Test
    void sm4Encrypt() throws Exception {
        byte[] bytes = "1234567890123456".getBytes();
//        byte[] sm4Encrypt = sm4Service.sm4Encrypt(bytes,"8888888888888888");
//        System.out.println(ByteUtils.toHexString(sm4Encrypt));
        byte[] sm4Encrypt1 = sm4Service.sm4Encrypt(plainText, "SM4/CBC/NOPADDING","8888888888888888","8888888888888888");
        System.out.println(Arrays.toString(sm4Encrypt1));
        byte[] sm4Decrypt = sm4Service.sm4Decrypt(new byte[]{-120, 68, 121, 63, 118, -98, -35, 60, 11, 27, -10, 81, -94, 96, 70, 56}, "SM4/CBC/NOPADDING","8888888888888888","8888888888888888");
        System.out.println(new String(sm4Decrypt));
    }

    @Test
    void hutoolSM4() {
        SM4 sm4 = new SM4(Mode.ECB, Padding.PKCS5Padding);
        byte[] nihaos = sm4.encrypt("nihao");
        System.out.println(ByteUtils.toHexString(nihaos));
    }

    @Test
    void sm4Test() throws Exception{
        try {
            byte[] bytes = sm4Service.sm4Encrypt("helloworldhellow", "SM4/CBC/NOPADDING", "8888888888888888", "8888888888888888");
            System.out.println(Arrays.toString(bytes));
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        byte[] bytes = sm4Service.sm4Decrypt(new byte[]{-120, 68, 121, 63, 118, -98, -35, 60, 11, 27, -10, 81, -94, 96, 70, 56}, "SM4/CBC/NOPADDING", "8888888888888888", "8888888888888888");
        System.out.println(new String(bytes));
    }
}
