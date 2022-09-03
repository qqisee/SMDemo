package com.netsec.sm.service.impl;

import com.netsec.sm.domain.Sm2Result;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.junit.jupiter.api.Test;

import java.security.*;
import java.security.spec.ECGenParameterSpec;

class SM2ServiceImplTest {

    @Test
    void sm2Encrypt() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException {
        String M="encryption standard";
        final ECGenParameterSpec sm2Spec = new ECGenParameterSpec("sm2p256v1");
        // 获取一个椭圆曲线类型的密钥对生成器
        final KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", new BouncyCastleProvider());
        // 使用SM2参数初始化生成器
        kpg.initialize(sm2Spec);
        // 获取密钥对
        KeyPair keyPair = kpg.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        System.out.println(publicKey);
        BCECPublicKey p=(BCECPublicKey)publicKey;
        System.out.println("\n公钥："+ Hex.toHexString(p.getQ().getEncoded(false)));
        String newPublicKey = "04C4F7D581BEFEF25C8BBB6DAD52A6AB8234FA7DB7A988592BC592DAF2BE630647E3746788CBDC59042D85260DD48B6A7347D82C5314E8AC261588A33151DFCA17";
        System.out.println("my公钥"+newPublicKey);
        PrivateKey privateKey = keyPair.getPrivate();
        BCECPrivateKey s=(BCECPrivateKey)privateKey;
        System.out.println("\n私钥："+Hex.toHexString(s.getD().toByteArray()));

        SM2ServiceImpl sm2Service = new SM2ServiceImpl();
        Sm2Result sm2Encrypt = sm2Service.sm2Encrypt(newPublicKey, M);
        System.out.println("加密结果"+sm2Encrypt);
//        byte[] decode = Hex.decode(sm2Encrypt.);
        sm2Service.changeC1C3C2ToAsn1(sm2Encrypt.getResult().getBytes());

    }
}