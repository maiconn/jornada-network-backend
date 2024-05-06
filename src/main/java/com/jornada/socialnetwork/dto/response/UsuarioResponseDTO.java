package com.jornada.socialnetwork.dto.response;

import com.jornada.socialnetwork.entity.PermissaoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UsuarioResponseDTO {
    private Long idUsuario;
    private String email;
    private String nome;
    private Set<PermissaoEntity> permissoes;
    private String token;
    private String fotoPerfil;
}
