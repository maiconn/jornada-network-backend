package com.jornada.socialnetwork.controller;

import com.jornada.socialnetwork.dto.response.ContatoResponseDTO;
import com.jornada.socialnetwork.service.ContatoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contato")
@RequiredArgsConstructor
public class ContatoController {
    private final ContatoService contatoService;

    @GetMapping
    public List<ContatoResponseDTO> findAllAtivos()  {
        return contatoService.findAllAtivos();
    }

}
