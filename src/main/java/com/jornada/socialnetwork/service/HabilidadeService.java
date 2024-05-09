package com.jornada.socialnetwork.service;

import com.jornada.socialnetwork.dto.response.ContatoResponseDTO;
import com.jornada.socialnetwork.dto.response.HabilidadeResponseDTO;
import com.jornada.socialnetwork.mapper.ContatoMapper;
import com.jornada.socialnetwork.mapper.HabilidadeMapper;
import com.jornada.socialnetwork.repository.ContatoRepository;
import com.jornada.socialnetwork.repository.HabilidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabilidadeService {
    private final HabilidadeRepository habilidadeRepository;
    private final HabilidadeMapper habilidadeMapper;

    public List<HabilidadeResponseDTO> findAllAtivos() {
        return this.habilidadeRepository.findAllByAtivoTrue().stream()
                .map(habilidadeMapper::toDto)
                .toList();
    }
}
