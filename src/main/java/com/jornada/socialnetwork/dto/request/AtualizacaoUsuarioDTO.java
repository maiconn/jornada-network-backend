package com.jornada.socialnetwork.dto.request;

import com.jornada.socialnetwork.dto.response.HabilidadeUsuarioResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtualizacaoUsuarioDTO {
    private String email;
    private String nome;
    private String senha;
    private String confirmarSenha;
    private String usuario;
    private String bio;
    private Set<HabilidadeUsuarioResponseDTO> habilidades;
    private Set<UsuarioContatoDTO> contatos;
    private Integer idCidade;
    private Integer idEstado;

}
