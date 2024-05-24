package com.jornada.socialnetwork.controller;

import com.jornada.socialnetwork.dto.AutenticacaoDTO;
import com.jornada.socialnetwork.dto.request.*;
import com.jornada.socialnetwork.dto.response.UsuarioResponseDTO;
import com.jornada.socialnetwork.exceptions.BusinessException;
import com.jornada.socialnetwork.service.UsuarioAutenticacaoService;
import com.jornada.socialnetwork.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping(value = "/upload-foto", consumes = {"multipart/form-data"})
    public UsuarioResponseDTO atualizarFoto(@ModelAttribute MultipartFile profilePhoto) throws BusinessException {
        return usuarioService.uploadPhoto(profilePhoto);
    }

    @PostMapping("/dados-pessoais")
    public UsuarioResponseDTO criarUsuarioDadosPessoais(@RequestBody DadosPessoaisNovoUsuarioRequestDTO dadosPessoais) throws BusinessException {
        return usuarioService.criarUsuarioDadosPessoais(dadosPessoais);
    }

    @PostMapping("/contatos")
    public UsuarioResponseDTO criarContatos(@RequestBody ContatosNovoUsuarioRequestDTO contatos) throws BusinessException {
        return usuarioService.criarContatos(contatos);
    }

    @PostMapping("/localizacao")
    public UsuarioResponseDTO criarLocalizacao(@RequestBody LocalizacaoNovoUsuarioRequestDTO localizacao) throws BusinessException {
        return usuarioService.criarLocalizacao(localizacao);
    }

    @PutMapping
    public UsuarioResponseDTO atualizarUsuario(@RequestBody AtualizacaoUsuarioDTO atualizacaoUsuarioDTO) throws BusinessException  {
        return usuarioService.atualizarUsuario(atualizacaoUsuarioDTO);
    }

    @GetMapping("/perfil/{usuario}")
    public UsuarioResponseDTO recuperarUsuarioPorNomeUsuario(@PathVariable("usuario") String nomeUsuario) throws BusinessException {
        return usuarioService.recuperarUsuarioPorNomeUsuario(nomeUsuario);
    }
}
