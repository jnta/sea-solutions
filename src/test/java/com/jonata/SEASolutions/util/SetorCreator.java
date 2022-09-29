package com.jonata.SEASolutions.util;

import com.jonata.SEASolutions.model.Setor;
import com.jonata.SEASolutions.payload.form.SetorForm;

public class SetorCreator {

    public static Setor criarSetorAtualizado() {
        Setor setor = new Setor();
        setor.setNome("Test 2");
        return setor;
    }

    public static Setor criarSetorValido() {
        Setor setor = new Setor();
        setor.setId(1l);
        setor.setNome("Test");
        return setor;
    }

    public static SetorForm criarSetorASerSalvo() {
        SetorForm setorForm = new SetorForm();
        setorForm.setNome("Test");
        return setorForm;
    }
}
