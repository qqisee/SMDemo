package com.netsec.sm.controller;


import com.netsec.sm.config.R;
import com.netsec.sm.domain.Sm4Data;
import com.netsec.sm.service.SM4Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/sm4")
@Slf4j
@Tag(name = "SM4加解密")
public class SM4Controller {

    @Autowired
    private SM4Service sm4Service;

    @PostMapping("/encrypt")
    @Operation(summary = "sm4加密")
    public R<?> sm4Encrypt(@RequestBody Sm4Data sm4Data) throws Exception {
        byte[] bytes = sm4Service.sm4Encrypt(sm4Data.getData(), sm4Data.getAlgo(), sm4Data.getKey(), sm4Data.getIv());
        return R.success(Hex.toHexString(bytes));
    }


    @PostMapping("/decrypt")
    @Operation(summary = "sm4解密")
    public R<?> sm4Decrypt(@RequestBody Sm4Data sm4Data) throws Exception{
        byte[] bytes = sm4Service.sm4Decrypt(Hex.decode(sm4Data.getData()), sm4Data.getAlgo(), sm4Data.getKey(), sm4Data.getIv());
        return R.success(Hex.toHexString(bytes));
    }
}
