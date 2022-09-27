package com.jonata.SEASolutions.repository;

import com.jonata.SEASolutions.model.Trabalhador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrabalhadorRepo extends JpaRepository<Trabalhador, Long> {

    Optional<Trabalhador> findByCpf(String cpf);

    @Query("SELECT t FROM Trabalhador T where t.nome like :keyword")
    List<Trabalhador> findByNome(@Param("keyword") String nome);
}
