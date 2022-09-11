package com.netsec.sm.service.impl;

import com.netsec.sm.domain.SM3Result;
import com.netsec.sm.service.SM3Service;
import lombok.var;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SM3ServiceImpl implements SM3Service {
    @Override
    public SM3Result SM3Hash(String plainText) {
        var srcData = plainText.getBytes();
        SM3Digest digest = new SM3Digest();
        //update the message digest with a single byte.
        digest.update(srcData, 0, srcData.length);
        byte[] hash = new byte[digest.getDigestSize()];
        //close the digest, producing the final digest value.
        digest.doFinal(hash, 0);
//        System.out.println(Arrays.toString(hash));
//        System.out.println(hash.length);
        return new SM3Result(ByteUtils.toHexString(hash));
    }
}
