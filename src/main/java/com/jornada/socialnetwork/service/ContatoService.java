package com.jornada.socialnetwork.service;

import com.jornada.socialnetwork.dto.response.ContatoResponseDTO;
import com.jornada.socialnetwork.mapper.ContatoMapper;
import com.jornada.socialnetwork.repository.ContatoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContatoService {
    private final ContatoRepository contatoRepository;
    private final ContatoMapper contatoMapper;

    public List<ContatoResponseDTO> findAllAtivos() {
        return this.contatoRepository.findAllByAtivoTrue().stream()
                .map(contatoMapper::toDto)
                .toList();
    }
}
