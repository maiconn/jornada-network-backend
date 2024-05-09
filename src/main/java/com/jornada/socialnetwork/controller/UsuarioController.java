package com.jornada.socialnetwork.controller;

import com.jornada.socialnetwork.dto.AutenticacaoDTO;
import com.jornada.socialnetwork.dto.request.DadosPessoaisNovoUsuarioRequestDTO;
import com.jornada.socialnetwork.dto.request.DadosPrincipaisNovoUsuarioRequestDTO;
import com.jornada.socialnetwork.dto.response.UsuarioResponseDTO;
import com.jornada.socialnetwork.exceptions.BusinessException;
import com.jornada.socialnetwork.service.UsuarioAutenticacaoService;
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
    private final UsuarioAutenticacaoService autenticacaoService;

    @PostMapping("/create")
    public UsuarioResponseDTO criarUsuarioDadosPrincipais(@RequestBody DadosPrincipaisNovoUsuarioRequestDTO novoUsuario) throws BusinessException {
        usuarioService.criarUsuarioDadosPrincipais(novoUsuario);
        UsuarioResponseDTO autenticar = autenticacaoService.autenticar(new AutenticacaoDTO(novoUsuario.getUsuario(), novoUsuario.getSenha()));
        return autenticar;
    }

    @PostMapping("/dados-pessoais")
    public UsuarioResponseDTO criarUsuarioDadosPessoais(@RequestBody DadosPessoaisNovoUsuarioRequestDTO dadosPessoais) throws BusinessException {
        return usuarioService.criarUsuarioDadosPessoais(dadosPessoais);
    }
}
