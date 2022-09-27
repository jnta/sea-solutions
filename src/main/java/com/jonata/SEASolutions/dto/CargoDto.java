package com.jonata.SEASolutions.dto;

import com.jonata.SEASolutions.model.Cargo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CargoDto {
    private Long id;
    private String nome;
    private BigDecimal salario;
    private String setor;
    private List<TrabalhadorDto> trabalhadores = new ArrayList<>();

    public CargoDto() {}

    public CargoDto(Cargo cargo) {
        this.id = cargo.getId();
        this.nome = cargo.getNome();
        this.salario = cargo.getSalario();
        this.setor = cargo.getSetor().getNome();
        this.trabalhadores = cargo.getTrabalhadores().stream().map(TrabalhadorDto::new).collect(Collectors.toList());
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

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public List<TrabalhadorDto> getTrabalhadores() {
        return trabalhadores;
    }

    public void setTrabalhadores(List<TrabalhadorDto> trabalhadores) {
        this.trabalhadores = trabalhadores;
    }
}
