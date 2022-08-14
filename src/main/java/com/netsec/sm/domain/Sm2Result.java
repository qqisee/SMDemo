package com.netsec.sm.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Sm2Result{
    /*
    c1c3c2格式结果
     */
    private String result;


    private CipherText cipherText;

}
