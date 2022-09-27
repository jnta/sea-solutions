package com.jonata.SEASolutions.repository;

import com.jonata.SEASolutions.model.Setor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SetorRepo extends JpaRepository<Setor, Long> {
    Optional<Setor> findByNome(String nome);
}
