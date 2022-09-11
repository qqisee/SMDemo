package com.netsec.sm.controller;

import com.netsec.sm.config.R;
import com.netsec.sm.domain.SM3Result;
import com.netsec.sm.service.impl.SM3ServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "SM3HASH")
@RestController
public class SM3Controller {

    @Autowired
    private SM3ServiceImpl sm3Service;

    @Operation(summary = "SM3杂凑运算")
    @PostMapping("/sm3")
    public R<?> sm3(@RequestBody String plaintext) {
        log.debug(plaintext);
        SM3Result sm3Result = sm3Service.SM3Hash(plaintext);
        return R.success(sm3Result);

    }
}
