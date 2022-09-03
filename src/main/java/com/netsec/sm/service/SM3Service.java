package com.netsec.sm.service;

import com.netsec.sm.domain.SM3Result;

public interface SM3Service {
    public SM3Result SM3Hash(String plainText);
}
