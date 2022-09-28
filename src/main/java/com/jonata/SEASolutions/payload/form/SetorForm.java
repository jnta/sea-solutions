package com.jonata.SEASolutions.payload.form;

import javax.validation.constraints.NotBlank;

public class SetorForm {
    @NotBlank
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
