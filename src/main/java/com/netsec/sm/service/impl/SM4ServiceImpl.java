package com.netsec.sm.service.impl;

import com.netsec.sm.service.SM4Service;
import lombok.var;
import org.bouncycastle.crypto.engines.SM4Engine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.springframework.stereotype.Service;

@Service
public class SM4ServiceImpl implements SM4Service {
    public static final int BLOCK_SIZE = 16;
    @Override
    public byte[] sm4Encrypt(byte[] in,String key) {
        var keyBytes = key.getBytes();
        SM4Engine sm4Engine = new SM4Engine();
        sm4Engine.init(false, new KeyParameter(keyBytes));
        int inLen = in.length;
        byte[] out = new byte[inLen];

        int times = inLen / BLOCK_SIZE;

        for (int i = 0; i < times; i++) {
            sm4Engine.processBlock(in, i * BLOCK_SIZE, out, i * BLOCK_SIZE);
        }

        return out;
    }
}
