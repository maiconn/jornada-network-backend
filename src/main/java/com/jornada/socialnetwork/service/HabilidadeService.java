package com.jornada.socialnetwork.service;

import com.jornada.socialnetwork.dto.response.HabilidadeResponseDTO;
import com.jornada.socialnetwork.dto.response.HabilidadeUsuarioResponseDTO;
import com.jornada.socialnetwork.entity.HabilidadeEntity;
import com.jornada.socialnetwork.mapper.HabilidadeMapper;
import com.jornada.socialnetwork.repository.HabilidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<HabilidadeEntity> findHabilidadeByDescricao(String descricao) {
        return habilidadeRepository.findByDescricao(descricao);
    }

    public HabilidadeEntity criarNova(String descricao) {
        HabilidadeEntity entity = new HabilidadeEntity();
        entity.setDescricao(descricao);
        return habilidadeRepository.save(entity);
    }

}
