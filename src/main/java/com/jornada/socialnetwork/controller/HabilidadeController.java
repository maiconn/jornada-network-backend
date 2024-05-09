package com.jornada.socialnetwork.controller;

import com.jornada.socialnetwork.dto.response.HabilidadeResponseDTO;
import com.jornada.socialnetwork.service.HabilidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/habilidade")
@RequiredArgsConstructor
public class HabilidadeController {
    private final HabilidadeService habilidadeService;

    @GetMapping
    public List<HabilidadeResponseDTO> findAllAtivos()  {
        return habilidadeService.findAllAtivos();
    }

}
