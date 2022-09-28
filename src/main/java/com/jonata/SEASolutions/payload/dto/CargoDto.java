package com.jonata.SEASolutions.payload.dto;

import com.jonata.SEASolutions.model.Cargo;

import java.math.BigDecimal;

public class CargoDto {
    private Long id;
    private String nome;
    private BigDecimal salario;
    private String setor;

    public CargoDto() {}

    public CargoDto(Cargo cargo) {
        this.id = cargo.getId();
        this.nome = cargo.getNome();
        this.salario = cargo.getSalario();
        this.setor = cargo.getSetor().getNome();
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

}
