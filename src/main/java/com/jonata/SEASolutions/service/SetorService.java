package com.jonata.SEASolutions.service;

import com.jonata.SEASolutions.payload.dto.SetorDto;
import com.jonata.SEASolutions.model.Setor;
import com.jonata.SEASolutions.payload.form.SetorForm;

import java.util.List;

public interface SetorService {
    SetorDto cadastrar(SetorForm setorForm);
    List<SetorDto> listar();
    SetorDto detalharPorId(Long id);

    SetorDto atualizar(Long id, SetorForm setorForm);
    Boolean remover(Long id);
}
