package com.netsec.sm.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * ECC格式的结果
 */
@Data
@Component
public class CipherText {
    private String x;
    private String y;
    private String hash;
    private String cipher;

    private String ans1HexString;
}
