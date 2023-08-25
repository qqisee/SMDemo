package com.netsec.sm.controller;


import com.netsec.sm.config.R;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}




