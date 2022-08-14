package com.netsec.sm.domain;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.Data;

@Data
public class Sm2Encrypt {

    @Schema(example = "04C4F7D581BEFEF25C8BBB6DAD52A6AB8234FA7DB7A988592BC592DAF2BE630647E3746788CBDC59042D85260DD48B6A7347D82C5314E8AC261588A33151DFCA17")
    private String publicKey;

    @Schema(example = "E7CB09606A53320B347F61F3F142DCB118F723A9BC27879F2805BE778F24AEE5")
    private String privateKey;

    @Schema(example = "hello12345")
    private String data;
}
