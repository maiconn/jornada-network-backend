package com.jornada.socialnetwork.repository;

import com.jornada.socialnetwork.entity.ContatoEntity;
import com.jornada.socialnetwork.entity.HabilidadeEntity;
import com.jornada.socialnetwork.entity.UsuarioHabilidadeEntity;
import com.jornada.socialnetwork.entity.pk.UsuarioHabilidadePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabilidadeRepository extends JpaRepository<HabilidadeEntity, Long> {
    List<HabilidadeEntity> findAllByAtivoTrue();
}
