package com.netsec.sm.controller;

import com.netsec.sm.config.R;
import com.netsec.sm.domain.CipherText;
import com.netsec.sm.domain.Sm2Encrypt;
import com.netsec.sm.domain.Sm2Result;
import com.netsec.sm.service.impl.SM2ServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "SM2加密")
@RestController
public class SM2Controller {

    @Autowired
    private SM2ServiceImpl sm2Service;


    @Operation(summary = "SM2加密，asn1为true同时返回asn1格式的加密结果")
    @PostMapping("/sm2")
    public R<?> sm2(@RequestBody
                    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "请求体")
                    Sm2Encrypt sm2Encrypt,
                    @RequestParam(value = "asn1", required = false, defaultValue = "false")
                    @Parameter(description = "是否参数")
                    String asn1) {
        System.out.println("asn1:" + asn1);
        log.debug(sm2Encrypt.toString());
        Sm2Result encrypt = sm2Service.sm2Encrypt(sm2Encrypt.getPublicKey(), sm2Encrypt.getData());
        if ("true".equalsIgnoreCase(asn1)) {
            CipherText toAsn1 = sm2Service.changeC1C3C2ToAsn1(Hex.decode(encrypt.getResult()));
            encrypt.setCipherText(toAsn1);
        }
        return R.success(encrypt);
    }

    @PostMapping("/c1c3c2toasn1")
    public R<?> c1c3c2toasn1(@RequestBody Sm2Result sm2Result) {
        CipherText toAsn1 = sm2Service.changeC1C3C2ToAsn1(Hex.decode(sm2Result.getResult()));
        return R.success(toAsn1);
    }
}
