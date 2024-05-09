package com.jornada.socialnetwork.service;

import com.jornada.socialnetwork.dto.request.DadosPessoaisNovoUsuarioRequestDTO;
import com.jornada.socialnetwork.dto.request.DadosPrincipaisNovoUsuarioRequestDTO;
import com.jornada.socialnetwork.dto.response.UsuarioResponseDTO;
import com.jornada.socialnetwork.entity.PermissaoEntity;
import com.jornada.socialnetwork.entity.UsuarioEntity;
import com.jornada.socialnetwork.exceptions.BusinessException;
import com.jornada.socialnetwork.mapper.HabilidadeMapper;
import com.jornada.socialnetwork.mapper.UsuarioMapper;
import com.jornada.socialnetwork.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final HabilidadeMapper habilidadeMapper;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioAutenticacaoService usuarioAutenticacaoService;

    public UsuarioResponseDTO criarUsuarioDadosPrincipais(DadosPrincipaisNovoUsuarioRequestDTO novoUsuario) throws BusinessException {
        validarDadosPrincipais(novoUsuario);

        UsuarioEntity novo = usuarioMapper.toEntity(novoUsuario);
        novo.setSenha(passwordEncoder.encode(novoUsuario.getSenha()));
        HashSet<PermissaoEntity> permissoes = new HashSet<>();
        PermissaoEntity permissaoEntity = new PermissaoEntity();
        permissaoEntity.setIdPermissao(2L); // usuario comum
        permissoes.add(permissaoEntity);
        novo.setPermissoes(permissoes);
        novo = usuarioRepository.save(novo);

        return usuarioMapper.toDto(novo);
    }

    public UsuarioResponseDTO criarUsuarioDadosPessoais(DadosPessoaisNovoUsuarioRequestDTO dadosPessoais) throws BusinessException {
        UsuarioEntity usuarioEntity = usuarioAutenticacaoService.retornarUsuarioLogadoEntity();
        usuarioEntity.setBio(dadosPessoais.getBio());

        usuarioEntity.getHabilidades().clear();
        usuarioEntity.getHabilidades().addAll(habilidadeMapper.toEntityList(dadosPessoais.getHabilidades(), usuarioEntity));

        usuarioEntity = usuarioRepository.save(usuarioEntity);
        return usuarioMapper.toDtoCompleto(usuarioEntity);
    }

    private void validarDadosPrincipais(DadosPrincipaisNovoUsuarioRequestDTO novoUsuario) throws BusinessException {
        // se existe um usuário com o mesmo email
        if(usuarioRepository.existsByUsuario(novoUsuario.getUsuario())) {
            throw new BusinessException("Já existe um usuário cadastrado com esse user '" +novoUsuario.getUsuario()+"'");
        }

        // se o nome de usuário já existe
        if(usuarioRepository.existsByEmail(novoUsuario.getEmail())) {
            throw new BusinessException("Já existe um usuário cadastrado com esse e-mail '" +novoUsuario.getEmail()+"'");
        }

        // validação da senha
        String senha = novoUsuario.getSenha();
        if(!senha.equals(novoUsuario.getConfirmarSenha())) {
            throw new BusinessException("Senhas não conferem.");
        }

        if (!senha.matches(".*[!@#$%^&*()].*")) { // Pelo menos um caractere especial
            throw new BusinessException("A senha não atende aos critérios de segurança (Pelo menos um caractere especial).");
        }

        if (senha.length() < 6) {
            throw new BusinessException("A senha não atende aos critérios de segurança (No mínimo 6 dígitos).");
        }
    }
}
