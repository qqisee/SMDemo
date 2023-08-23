package com.netsec.sm.domain;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Sm4Data {

    @Schema(example = "SM4/CBC/NOPADDING")
    private String algo;

    @Schema(example = "helloworldhellow")
    private String data;

    @Schema(example = "1111222233334444")
    private String iv;

    @Schema(example = "1111222233334444")
    private String key;
}
