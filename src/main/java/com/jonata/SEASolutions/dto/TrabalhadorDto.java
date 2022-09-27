package com.jonata.SEASolutions.dto;

import com.jonata.SEASolutions.model.Trabalhador;
import org.hibernate.validator.constraints.br.CPF;

public class TrabalhadorDto {

    private Long id;
    private String nome;
    @CPF(message = "Formato inválido!")
    private String cpf;

    public TrabalhadorDto(){}

    public TrabalhadorDto(Trabalhador trabalhador) {
        this.id = trabalhador.getId();
        this.nome = trabalhador.getNome();
        this.cpf = trabalhador.getCpf();
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
}
