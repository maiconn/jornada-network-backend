package com.jornada.socialnetwork.dto.response;

import com.jornada.socialnetwork.entity.PermissaoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
public class UsuarioResponseDTO {
    private Long idUsuario;
    private String email;
    private String nome;
//    private Set<PermissaoEntity> permissoes;
    private Set<HabilidadeUsuarioResponseDTO> habilidades;
    private String token;
    private String fotoPerfil;

    private String bio;
}
