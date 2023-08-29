package com.netsec.sm.controller;


import com.netsec.sm.config.R;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


@Slf4j
@Tag(name = "常见的编解码")
@RestController
@RequestMapping("/encode")
public class EncoderController {

    @PostMapping("/base64")
    public R<?> base64Encode(@RequestParam String text) {
        Base64.Encoder encoder = Base64.getEncoder();
        String b64enc = encoder.encodeToString(text.getBytes());
        return R.success(b64enc);
    }

    @PostMapping("/hmac")
    public R<?> hmacEncode(@RequestParam String text,String key,@RequestParam @Parameter(description = "Hmac算法") String algo) {
        try {
            // 生成密钥
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), algo);
//            KeyGenerator keyGenerator = KeyGenerator.getInstance(algo);
//            SecretKey secretKey = keyGenerator.generateKey();
            // 创建HMAC-SHA256的Mac实例
            Mac mac = Mac.getInstance(algo);
            // 初始化Mac实例，并设置密钥
            mac.init(secretKey);
            // 计算认证码
            byte[] hmac = mac.doFinal(text.getBytes());
            return R.success(Hex.toHexString(hmac));
        } catch (Exception e) {
            return R.fail(e.toString());
        }
    }
}




