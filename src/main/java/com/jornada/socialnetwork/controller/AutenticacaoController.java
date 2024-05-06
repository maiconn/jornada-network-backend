package com.jornada.socialnetwork.controller;


import com.jornada.socialnetwork.dto.AutenticacaoDTO;
import com.jornada.socialnetwork.dto.response.UsuarioResponseDTO;
import com.jornada.socialnetwork.exceptions.BusinessException;
import com.jornada.socialnetwork.service.UsuarioAutenticacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autenticacao")
@RequiredArgsConstructor
public class AutenticacaoController {
    public final UsuarioAutenticacaoService usuarioAutenticacaoService;
    @PostMapping
    public UsuarioResponseDTO requerirToken(@RequestBody AutenticacaoDTO autenticacaoDTO) throws BusinessException {
        return usuarioAutenticacaoService.autenticar(autenticacaoDTO);
    }

    @GetMapping("/usuario-logado")
    public UsuarioResponseDTO retornarUsuarioLogado() throws BusinessException {
        return usuarioAutenticacaoService.retornarUsuarioLogado();
    }
}
