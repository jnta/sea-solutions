package com.jonata.SEASolutions.util;

import com.jonata.SEASolutions.model.Cargo;
import com.jonata.SEASolutions.model.Setor;
import com.jonata.SEASolutions.model.Trabalhador;
import com.jonata.SEASolutions.payload.form.CargoForm;
import com.jonata.SEASolutions.payload.form.TrabalhadorForm;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;

public class TrabalhadorCreator {

    private static final Cargo cargo = CargoCreator.criarCargoValido();;

    public static Trabalhador criarTrabalhadorAtualizado() {
        Trabalhador trabalhador = new Trabalhador();
        trabalhador.setId(1l);
        trabalhador.setNome("Test");
        trabalhador.setCpf("00000000000");
        trabalhador.setCargo(cargo);
        return trabalhador;
    }

    public static Trabalhador criarTrabalhadorValido() {
        Trabalhador trabalhador = new Trabalhador();
        trabalhador.setId(1l);
        trabalhador.setNome("Test");
        trabalhador.setCpf("00000000000");
        trabalhador.setCargo(cargo);
        return trabalhador;
    }

    public static TrabalhadorForm criarTrabalhadorASerSalvo() {
        TrabalhadorForm trabalhadorForm = new TrabalhadorForm();
        trabalhadorForm.setNome("Test");
        trabalhadorForm.setCpf("00000000000");
        trabalhadorForm.setCargoId(1l);
        return trabalhadorForm;
    }
}
