package com.jonata.SEASolutions.payload.form;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TrabalhadorForm {
    @NotBlank(message = "O nome não deve ser nulo")
    private String nome;
    @NotBlank(message = "O cpf não deve ser nulo")
    @Length(min = 11, max = 11)
    private String cpf;
    @NotNull(message = "O cargo não deve ser nulo")
    private Long cargoId;

    public TrabalhadorForm() {}

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

    public Long getCargoId() {
        return cargoId;
    }

    public void setCargoId(Long cargoId) {
        this.cargoId = cargoId;
    }
}
