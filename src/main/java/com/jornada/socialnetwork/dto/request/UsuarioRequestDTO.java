package com.jornada.socialnetwork.dto.request;

import lombok.Data;

@Data
public class UsuarioRequestDTO {
    private String email;
    private String nome;
    private String senha;
    private String confirmarSenha;
//    private CargoEnum cargo;
}
