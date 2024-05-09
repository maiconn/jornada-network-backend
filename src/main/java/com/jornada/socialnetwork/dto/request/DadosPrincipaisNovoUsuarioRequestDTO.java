package com.jornada.socialnetwork.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DadosPrincipaisNovoUsuarioRequestDTO {
    @Schema(example = "Maicon Gerardi")
    private String nome;
    @Schema(example = "maicon.gerardi@gmail.com")
    private String email;
    @Schema(example = "Maicon#")
    private String senha;
    @Schema(example = "Maicon#")
    private String confirmarSenha;
    @Schema(example = "maicongerardi")
    private String usuario;
}
