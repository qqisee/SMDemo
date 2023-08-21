package com.netsec.sm.service;

import com.netsec.sm.domain.CipherText;
import com.netsec.sm.domain.Sm2Result;

public interface SM2Service {
    Sm2Result sm2Encrypt(String publicKey, String data);

    String sm2Decrypt(String privateKey,String cipherData);

    String changeAsn1ToC1C3C2();

    CipherText changeC1C3C2ToAsn1(byte[] c1c3c2);
}
