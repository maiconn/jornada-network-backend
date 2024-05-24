package com.jornada.socialnetwork.service;

import com.jornada.socialnetwork.dto.request.*;
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
import java.util.Optional;
import java.util.Set;

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
        validarDados(novoUsuario, null);

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

        atualizarHabilidades(dadosPessoais.getHabilidades(), usuarioEntity);

        return saveUserAndReturnCompleteDTO(usuarioEntity);
    }

    private void atualizarHabilidades(Set<HabilidadeUsuarioResponseDTO> habilidades, UsuarioEntity usuarioEntity) {
        usuarioEntity.getHabilidades().clear();

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
    }

    public UsuarioResponseDTO criarLocalizacao(LocalizacaoNovoUsuarioRequestDTO localizacao) throws BusinessException {
        UsuarioEntity usuarioEntity = usuarioAutenticacaoService.retornarUsuarioLogadoEntity();
        usuarioEntity.setIdCidade(localizacao.getIdCidade());
        usuarioEntity.setIdEstado(localizacao.getIdEstado());
        return saveUserAndReturnCompleteDTO(usuarioEntity);
    }

    public UsuarioResponseDTO criarContatos(ContatosNovoUsuarioRequestDTO contatos) throws BusinessException {
        UsuarioEntity usuarioEntity = usuarioAutenticacaoService.retornarUsuarioLogadoEntity();

        atualizarContatos(contatos.getContatos(), usuarioEntity);

        return saveUserAndReturnCompleteDTO(usuarioEntity);
    }

    private void atualizarContatos(Set<UsuarioContatoDTO> contatos, UsuarioEntity usuarioEntity) {
        usuarioEntity.getContatos().clear();
        usuarioEntity.getContatos().addAll(contatoMapper.toUsuarioContatoEntity(contatos, usuarioEntity));
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

    private void validarDados(DadosPrincipaisNovoUsuarioRequestDTO novoUsuario, Long idUsuario) throws BusinessException {
        validarNomeUsuarioEEmail(novoUsuario.getUsuario(),novoUsuario.getEmail(), idUsuario);

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

    private void validarNomeUsuarioEEmail(String nomeUsuario, String email, Long idUsuario) throws BusinessException {
        String mensagemUsuario = "Já existe um usuário cadastrado com esse user '" + nomeUsuario + "'";
        String mensagemEmail = "Já existe um usuário cadastrado com esse e-mail '" + email + "'";
        if(idUsuario == null) {
            // se existe um usuário com o mesmo nome de usuário
            if (usuarioRepository.existsByUsuario(nomeUsuario)) {
                throw new BusinessException(mensagemUsuario);
            }
            if (usuarioRepository.existsByEmail(email)) {
                throw new BusinessException(mensagemEmail);
            }
        } else {
            if (usuarioRepository.existsByUsuarioAndIdUsuarioNot(nomeUsuario, idUsuario)) {
                throw new BusinessException(mensagemUsuario);
            }
            if (usuarioRepository.existsByEmailAndIdUsuarioNot(email, idUsuario)) {
                throw new BusinessException(mensagemEmail);
            }
        }
    }

    public UsuarioResponseDTO atualizarUsuario(AtualizacaoUsuarioDTO atualizacaoUsuarioDTO) throws BusinessException {
        UsuarioEntity usuarioEntity = usuarioAutenticacaoService.retornarUsuarioLogadoEntity();
        validarDados(usuarioMapper.transformarAtualizacaoParaNovoUsuario(atualizacaoUsuarioDTO), usuarioEntity.getIdUsuario());

        usuarioEntity.setEmail(atualizacaoUsuarioDTO.getEmail());
        usuarioEntity.setNome(atualizacaoUsuarioDTO.getNome());
        usuarioEntity.setSenha(passwordEncoder.encode(atualizacaoUsuarioDTO.getSenha()));
        usuarioEntity.setUsuario(atualizacaoUsuarioDTO.getUsuario());
        usuarioEntity.setBio(atualizacaoUsuarioDTO.getBio());
        usuarioEntity.setIdCidade(atualizacaoUsuarioDTO.getIdCidade());
        usuarioEntity.setIdEstado(atualizacaoUsuarioDTO.getIdEstado());
        atualizarHabilidades(atualizacaoUsuarioDTO.getHabilidades(), usuarioEntity);
        atualizarContatos(atualizacaoUsuarioDTO.getContatos(), usuarioEntity);

        return saveUserAndReturnCompleteDTO(usuarioEntity);
    }

    public UsuarioResponseDTO recuperarUsuarioPorNomeUsuario(String nomeUsuario) throws BusinessException {
        UsuarioEntity usuarioEntity = usuarioRepository.findByUsuario(nomeUsuario)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado!"));
        return usuarioMapper.toDtoCompleto(usuarioEntity);
    }
}
