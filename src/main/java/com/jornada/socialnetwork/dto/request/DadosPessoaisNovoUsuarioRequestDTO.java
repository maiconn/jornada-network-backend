package com.jornada.socialnetwork.dto.request;

import com.jornada.socialnetwork.dto.response.HabilidadeUsuarioResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class DadosPessoaisNovoUsuarioRequestDTO {
    private String bio;
    private List<HabilidadeUsuarioResponseDTO> habilidades;
}
