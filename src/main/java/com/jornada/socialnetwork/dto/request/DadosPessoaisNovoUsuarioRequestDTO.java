package com.jornada.socialnetwork.dto.request;

import com.jornada.socialnetwork.dto.response.HabilidadeUsuarioResponseDTO;
import lombok.Data;

import java.util.Set;

@Data
public class DadosPessoaisNovoUsuarioRequestDTO {
    private String bio;
    private Set<HabilidadeUsuarioResponseDTO> habilidades;
}
