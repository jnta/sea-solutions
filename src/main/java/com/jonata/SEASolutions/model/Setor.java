package com.jonata.SEASolutions.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name= "TB_SETOR")
public class Setor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    private String nome;

    @OneToMany(mappedBy = "setor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Cargo> cargos = new ArrayList<>();


    public Setor() {}

    public Setor(Long id, String nome, List<Cargo> cargos) {
        this.id = id;
        this.nome = nome;
        this.cargos = cargos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Setor setor = (Setor) o;
        return id.equals(setor.id) && nome.equals(setor.nome) && Objects.equals(cargos, setor.cargos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cargos);
    }

    @Override
    public String toString() {
        return "Setor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cargos=" + cargos +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Cargo> getCargos() {
        return cargos;
    }

    public void setCargos(List<Cargo> cargos) {
        this.cargos = cargos;
    }
}
