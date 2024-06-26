package com.jornada.socialnetwork.dto.response;

import com.jornada.socialnetwork.dto.request.UsuarioContatoDTO;
import com.jornada.socialnetwork.entity.PermissaoEntity;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
public class UsuarioResponseDTO {
    private Long idUsuario;
    private String email;
    private String nome;
    private Set<HabilidadeUsuarioResponseDTO> habilidades;
    private Set<UsuarioContatoDTO> contatos;
    private String token;
    private String fotoPerfil;
    private String bio;
    private Integer idCidade;
    private Integer idEstado;
    private String usuario;
    private Long qtdSeguindo;
    private Long qtdSeguidores;
    private Long qtdPostagens;
}
