package com.netsec.sm.service;



public interface SM4Service {
    byte[] sm4Encrypt(byte[] in,String key);

    byte[] sm4Encrypt(String plainText, String algo,String key,String ivString) throws Exception;


    byte[] sm4Decrypt(byte[] cipherText, String algo,String key,String ivString) throws Exception;
}
