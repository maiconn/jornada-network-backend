package com.jornada.socialnetwork.dto.request;

import lombok.Data;

@Data
public class NovoUsuarioRequestDTO {
    private String nome;
    private String email;
    private String bio;
    private Integer idCidade;
    private Integer idEstado;
    private String senha;
    private String confirmarSenha;
    private String usuario;
}
