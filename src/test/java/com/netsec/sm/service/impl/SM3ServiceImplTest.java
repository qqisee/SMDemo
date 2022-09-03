package com.netsec.sm.service.impl;

import com.netsec.sm.domain.SM3Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SM3ServiceImplTest {

    @Autowired
    private SM3ServiceImpl sm3Service;

    @Test
    void SM3Hash() {
        SM3Result hash = sm3Service.SM3Hash("123456");
        System.out.println(hash);
    }
}