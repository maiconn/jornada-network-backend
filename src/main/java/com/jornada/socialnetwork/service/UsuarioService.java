package com.jornada.socialnetwork.service;

import com.jornada.socialnetwork.dto.request.ContatosNovoUsuarioRequestDTO;
import com.jornada.socialnetwork.dto.request.DadosPessoaisNovoUsuarioRequestDTO;
import com.jornada.socialnetwork.dto.request.DadosPrincipaisNovoUsuarioRequestDTO;
import com.jornada.socialnetwork.dto.request.LocalizacaoNovoUsuarioRequestDTO;
import com.jornada.socialnetwork.dto.response.HabilidadeUsuarioResponseDTO;
import com.jornada.socialnetwork.dto.response.UsuarioResponseDTO;
import com.jornada.socialnetwork.entity.HabilidadeEntity;
import com.jornada.socialnetwork.entity.PermissaoEntity;
import com.jornada.socialnetwork.entity.UsuarioEntity;
import com.jornada.socialnetwork.exceptions.BusinessException;
import com.jornada.socialnetwork.mapper.ContatoMapper;
import com.jornada.socialnetwork.mapper.HabilidadeMapper;
import com.jornada.socialnetwork.mapper.UsuarioMapper;
import com.jornada.socialnetwork.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final HabilidadeMapper habilidadeMapper;
    private final HabilidadeService habilidadeService;
    private final ContatoMapper contatoMapper;
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

    public UsuarioResponseDTO uploadPhoto(MultipartFile profilePhoto) throws BusinessException {
        UsuarioEntity usuarioEntity = usuarioAutenticacaoService.retornarUsuarioLogadoEntity();
        usuarioEntity.setFotoPerfil(getBytes(profilePhoto));
        return saveUserAndReturnCompleteDTO(usuarioEntity);
    }

    public UsuarioResponseDTO criarUsuarioDadosPessoais(DadosPessoaisNovoUsuarioRequestDTO dadosPessoais) throws BusinessException {
        UsuarioEntity usuarioEntity = usuarioAutenticacaoService.retornarUsuarioLogadoEntity();
        usuarioEntity.setBio(dadosPessoais.getBio());

        usuarioEntity.getHabilidades().clear();

        List<HabilidadeUsuarioResponseDTO> habilidades = dadosPessoais.getHabilidades();
        habilidades.forEach(habilidadeUsuarioResponseDTO -> {
            String descricao = habilidadeUsuarioResponseDTO.getDescricao().toUpperCase();
            Optional<HabilidadeEntity> habilidadeBuscada = habilidadeService.findHabilidadeByDescricao(descricao);
            if (habilidadeBuscada.isPresent()) {
                HabilidadeEntity habilidadeEntity = habilidadeBuscada.get();
                habilidadeUsuarioResponseDTO.setIdHabilidade(habilidadeEntity.getIdHabilidade());
                habilidadeUsuarioResponseDTO.setDescricao(habilidadeEntity.getDescricao());
            } else {
                HabilidadeEntity nova = habilidadeService.criarNova(descricao);
                habilidadeUsuarioResponseDTO.setIdHabilidade(nova.getIdHabilidade());
                habilidadeUsuarioResponseDTO.setDescricao(nova.getDescricao());
            }
        });

        usuarioEntity.getHabilidades().addAll(habilidadeMapper.toEntityList(habilidades, usuarioEntity));

        return saveUserAndReturnCompleteDTO(usuarioEntity);
    }

    public UsuarioResponseDTO criarLocalizacao(LocalizacaoNovoUsuarioRequestDTO localizacao) throws BusinessException {
        UsuarioEntity usuarioEntity = usuarioAutenticacaoService.retornarUsuarioLogadoEntity();
        usuarioEntity.setIdCidade(localizacao.getIdCidade());
        usuarioEntity.setIdEstado(localizacao.getIdEstado());
        return saveUserAndReturnCompleteDTO(usuarioEntity);
    }

    public UsuarioResponseDTO criarContatos(ContatosNovoUsuarioRequestDTO contatos) throws BusinessException {
        UsuarioEntity usuarioEntity = usuarioAutenticacaoService.retornarUsuarioLogadoEntity();

        usuarioEntity.getContatos().clear();
        usuarioEntity.getContatos().addAll(contatoMapper.toUsuarioContatoEntity(contatos.getContatos(), usuarioEntity));

        return saveUserAndReturnCompleteDTO(usuarioEntity);
    }

    private byte[] getBytes(MultipartFile profilePhoto) {
        if (profilePhoto != null) {
            try {
                return profilePhoto.getBytes();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        return null;
    }

    private UsuarioResponseDTO saveUserAndReturnCompleteDTO(UsuarioEntity usuarioEntity) {
        usuarioEntity = usuarioRepository.save(usuarioEntity);
        return usuarioMapper.toDtoCompleto(usuarioEntity);
    }


    private void validarDadosPrincipais(DadosPrincipaisNovoUsuarioRequestDTO novoUsuario) throws BusinessException {
        // se existe um usuário com o mesmo email
        if (usuarioRepository.existsByUsuario(novoUsuario.getUsuario())) {
            throw new BusinessException("Já existe um usuário cadastrado com esse user '" + novoUsuario.getUsuario() + "'");
        }

        // se o nome de usuário já existe
        if (usuarioRepository.existsByEmail(novoUsuario.getEmail())) {
            throw new BusinessException("Já existe um usuário cadastrado com esse e-mail '" + novoUsuario.getEmail() + "'");
        }

        // validação da senha
        String senha = novoUsuario.getSenha();
        if (!senha.equals(novoUsuario.getConfirmarSenha())) {
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
