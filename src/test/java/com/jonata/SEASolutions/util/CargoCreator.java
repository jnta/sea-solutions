package com.jonata.SEASolutions.util;

import com.jonata.SEASolutions.model.Cargo;
import com.jonata.SEASolutions.model.Setor;
import com.jonata.SEASolutions.payload.form.CargoForm;
import com.jonata.SEASolutions.payload.form.SetorForm;

import java.math.BigDecimal;
import java.util.List;

public class CargoCreator {

    public static Cargo criarCargoAtualizado() {
        Cargo cargo = new Cargo();
        cargo.setNome("Test 2");
        cargo.setSetor(new Setor(1l, "Setor Test", List.of()));
        return cargo;
    }

    public static Cargo criarCargoValido() {
        Cargo cargo = new Cargo();
        cargo.setId(1l);
        cargo.setNome("Test");
        cargo.setSetor(new Setor(1l, "Setor Test", List.of()));
        return cargo;
    }

    public static CargoForm criarCargoASerSalvo() {
        CargoForm cargoForm = new CargoForm();
        cargoForm.setNome("Test");
        cargoForm.setSetorId(1l);
        return cargoForm;
    }
}
