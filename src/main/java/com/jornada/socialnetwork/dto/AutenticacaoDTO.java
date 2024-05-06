package com.jornada.socialnetwork.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutenticacaoDTO {
    @Schema(example = "admin")
    private String usuario;

    @Schema(example = "admin")
    private String senha;
}
