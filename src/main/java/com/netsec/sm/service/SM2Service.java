package com.netsec.sm.service;

import com.netsec.sm.domain.CipherText;
import com.netsec.sm.domain.Sm2Result;

public interface SM2Service {
    public Sm2Result sm2Encrypt(String publicKey, String data);

    public String sm2Decrypt();

    public String changeAsn1ToC1C3C2();

    public CipherText changeC1C3C2ToAsn1(byte[] c1c3c2);
}
