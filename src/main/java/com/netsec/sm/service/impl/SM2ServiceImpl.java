package com.netsec.sm.service.impl;

import com.netsec.sm.domain.CipherText;
import com.netsec.sm.domain.Sm2Result;
import com.netsec.sm.service.SM2Service;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.stereotype.Service;

import java.security.*;
import java.util.Arrays;

@Service
@Slf4j
public class SM2ServiceImpl implements SM2Service {





    @Override
    public Sm2Result sm2Encrypt(String publicKey, String data) {
        // 获取一条SM2曲线参数
        X9ECParameters sm2ECParameters = GMNamedCurves.getByName("sm2p256v1");
        // 构造ECC算法参数，曲线方程、椭圆曲线G点、大整数N
        ECDomainParameters domainParameters = new ECDomainParameters(sm2ECParameters.getCurve(), sm2ECParameters.getG(), sm2ECParameters.getN());
        //提取公钥点
        ECPoint pukPoint = sm2ECParameters.getCurve().decodePoint(Hex.decode(publicKey));
        // 公钥前面的02或者03表示是压缩公钥，04表示未压缩公钥, 04的时候，可以去掉前面的04
        ECPublicKeyParameters publicKeyParameters = new ECPublicKeyParameters(pukPoint, domainParameters);

        SM2Engine sm2Engine = new SM2Engine(SM2Engine.Mode.C1C3C2);
        // 设置sm2为加密模式
        sm2Engine.init(true, new ParametersWithRandom(publicKeyParameters, new SecureRandom()));

        byte[] arrayOfBytes;
        try {
            byte[] in = data.getBytes();
            arrayOfBytes = sm2Engine.processBlock(in, 0, in.length);
        } catch (Exception e) {
            System.out.println("SM2加密时出现异常:"+e.getMessage());
            throw new RuntimeException(e);
        }
        Sm2Result sm2Result = new Sm2Result();
        sm2Result.setResult(Hex.toHexString(arrayOfBytes));
        return sm2Result;
    }

    @Override
    public String sm2Decrypt() {
        return null;
    }

    @Override
    public String changeAsn1ToC1C3C2() {
        return null;
    }

    @SneakyThrows
    @Override
    public CipherText changeC1C3C2ToAsn1(byte[] c1c3c2){
        CipherText cipherText = new CipherText();
        final int C1_LEN = 65;
        final int C3_LEN = 32;
        byte[] c1 = Arrays.copyOfRange(c1c3c2, 0, C1_LEN);
        byte[] c3 = Arrays.copyOfRange(c1c3c2, C1_LEN, C1_LEN + C3_LEN);
        byte[] c2 = Arrays.copyOfRange(c1c3c2, C1_LEN + C3_LEN, c1c3c2.length);
        byte[] c1X = Arrays.copyOfRange(c1, 1, 33);
        byte[] c1Y = Arrays.copyOfRange(c1, 33, 65);

        log.debug(Arrays.toString(c1X));
        log.debug(Hex.toHexString(c1X));
        log.debug(Arrays.toString(c1Y));
        log.debug(Hex.toHexString(c1Y));
        cipherText.setX(Hex.toHexString(c1X));
        cipherText.setY(Hex.toHexString(c1Y));
        cipherText.setHash(Hex.toHexString(c3));
        cipherText.setCipher(Hex.toHexString(c2));

        /*
        BigInteger r = new BigInteger(1, c1X);
        BigInteger s = new BigInteger(1, c1Y);

        ASN1Integer x = new ASN1Integer(r);
        ASN1Integer y = new ASN1Integer(s);
        DEROctetString derDig = new DEROctetString(c3);
        DEROctetString derEnc = new DEROctetString(c2);
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(x);
        v.add(y);
        v.add(derDig);
        v.add(derEnc);
        DERSequence seq = new DERSequence(v);
        String ans1HexString = Hex.toHexString(seq.getEncoded(ASN1Encoding.DER));
        System.out.println("asn1："+ans1HexString);

         */
//        return ans1HexString;
        return cipherText;
    }
}
