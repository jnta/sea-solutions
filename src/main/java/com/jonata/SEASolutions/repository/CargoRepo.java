package com.jonata.SEASolutions.repository;

import com.jonata.SEASolutions.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CargoRepo extends JpaRepository<Cargo, Long> {

    @Modifying
    @Query("delete from Cargo c where c.id = ?1")
    void deleteById(Long cargoId);
    Optional<Cargo> findByNome(String nome);

}
