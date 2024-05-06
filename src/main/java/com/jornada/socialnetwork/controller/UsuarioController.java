package com.jornada.socialnetwork.controller;

import com.jornada.socialnetwork.dto.AutenticacaoDTO;
import com.jornada.socialnetwork.dto.request.NovoUsuarioRequestDTO;
import com.jornada.socialnetwork.dto.response.UsuarioResponseDTO;
import com.jornada.socialnetwork.exceptions.BusinessException;
import com.jornada.socialnetwork.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;
    @PostMapping("/create")
    public UsuarioResponseDTO criarUsuario(@RequestBody NovoUsuarioRequestDTO novoUsuario) throws BusinessException {
        return usuarioService.criarUsuario(novoUsuario);
    }
}
