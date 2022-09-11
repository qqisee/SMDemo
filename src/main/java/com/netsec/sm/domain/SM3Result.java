package com.netsec.sm.domain;

import lombok.Data;

@Data
public class SM3Result {
    String hexString;

    public SM3Result(String hexString) {
        this.hexString = hexString;
    }
}
