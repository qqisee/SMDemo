package com.netsec.sm.service.impl;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.SM4;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SM4ServiceImplTest {

    @Autowired
    private SM4ServiceImpl sm4Service;

    @Test
    void sm4Encrypt() {
        byte[] bytes = "1234567890123456".getBytes();
        byte[] sm4Encrypt = sm4Service.sm4Encrypt(bytes);
        System.out.println(ByteUtils.toHexString(sm4Encrypt));
    }

    @Test
    void hutoolSM4() {
        SM4 sm4 = new SM4(Mode.ECB, Padding.PKCS5Padding);
        byte[] nihaos = sm4.encrypt("nihao");
        System.out.println(ByteUtils.toHexString(nihaos));
    }
}