package com.jornada.socialnetwork.repository;

import com.jornada.socialnetwork.entity.ContatoEntity;
import com.jornada.socialnetwork.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContatoRepository extends JpaRepository<ContatoEntity, Long> {
    List<ContatoEntity> findAllByAtivoTrue();
}
