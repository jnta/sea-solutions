package com.jonata.SEASolutions.payload.dto;

import com.jonata.SEASolutions.model.Trabalhador;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;

public class TrabalhadorDto {

    private Long id;
    private String nome;
    @CPF(message = "Formato inválido!")
    private String cpf;

    private String cargo;

    private String setor;

    private BigDecimal salario;

    public TrabalhadorDto(){}

    public TrabalhadorDto(Trabalhador trabalhador) {
        this.id = trabalhador.getId();
        this.nome = trabalhador.getNome();
        this.cpf = trabalhador.getCpf();
        this.cargo = trabalhador.getCargo().getNome();
        this.setor = trabalhador.getCargo().getSetor().getNome();
        this.salario = trabalhador.getCargo().getSalario();
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }
}
