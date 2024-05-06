package com.jornada.socialnetwork.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AutenticacaoDTO {
    @Schema(example = "admin")
    private String usuario;

    @Schema(example = "admin")
    private String senha;
}
