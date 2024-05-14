package com.jornada.socialnetwork.repository;

import com.jornada.socialnetwork.entity.ContatoEntity;
import com.jornada.socialnetwork.entity.HabilidadeEntity;
import com.jornada.socialnetwork.entity.UsuarioHabilidadeEntity;
import com.jornada.socialnetwork.entity.pk.UsuarioHabilidadePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface HabilidadeRepository extends JpaRepository<HabilidadeEntity, Long> {
    List<HabilidadeEntity> findAllByAtivoTrue();
    Optional<HabilidadeEntity> findByDescricao(String descricao);
}
