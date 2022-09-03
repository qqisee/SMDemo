package com.netsec.sm.domain;

public class SM3Result {
    String hexString;

    public SM3Result(String hexString) {
        this.hexString = hexString;
    }

    @Override
    public String toString() {
        return "SM3Result{" +
                "hexString='" + hexString + '\'' +
                '}';
    }
}
