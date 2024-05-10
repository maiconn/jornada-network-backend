package com.jornada.socialnetwork.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContatosNovoUsuarioRequestDTO {
    private Set<UsuarioContatoDTO> contatos;
}
