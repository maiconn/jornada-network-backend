package com.jornada.socialnetwork.service;

import com.jornada.socialnetwork.dto.request.NovoUsuarioRequestDTO;
import com.jornada.socialnetwork.dto.response.UsuarioResponseDTO;
import com.jornada.socialnetwork.entity.PermissaoEntity;
import com.jornada.socialnetwork.entity.UsuarioEntity;
import com.jornada.socialnetwork.exceptions.BusinessException;
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
    private final UsuarioMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioResponseDTO criarUsuario(NovoUsuarioRequestDTO novoUsuario) throws BusinessException {
        validar(novoUsuario);

        UsuarioEntity novo = mapper.toEntity(novoUsuario);
        novo.setSenha(passwordEncoder.encode(novoUsuario.getSenha()));
        HashSet<PermissaoEntity> permissoes = new HashSet<>();
        PermissaoEntity permissaoEntity = new PermissaoEntity();
        permissaoEntity.setIdPermissao(2L);
        permissoes.add(permissaoEntity);
        novo.setPermissoes(permissoes);
        novo = usuarioRepository.save(novo);

        return mapper.toDto(novo);
    }

    private void validar(NovoUsuarioRequestDTO novoUsuario) throws BusinessException {
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
