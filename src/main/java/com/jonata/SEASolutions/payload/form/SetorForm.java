package com.jonata.SEASolutions.payload.form;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public class SetorForm {
    @NotBlank
    private String nome;

    public SetorForm(){}

    public SetorForm(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
