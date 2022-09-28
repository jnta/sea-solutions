package com.jonata.SEASolutions.payload.dto;

import com.jonata.SEASolutions.model.Cargo;
import com.jonata.SEASolutions.model.Setor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SetorDto {
    private Long id;
    private String nome;
    private List<CargoDto> cargos = new ArrayList<>();

    public SetorDto() {}

    public SetorDto(Setor setor) {
        this.id = setor.getId();
        this.nome = setor.getNome();
        if (!setor.getCargos().isEmpty() || setor.getCargos() != null) {
            this.cargos = setor.getCargos().stream().map(CargoDto::new).collect(Collectors.toList());
        }
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

    public List<CargoDto> getCargos() {
        return cargos;
    }

    public void setCargos(List<CargoDto> cargos) {
        this.cargos = cargos;
    }
}
