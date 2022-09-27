package com.jonata.SEASolutions.service;

import com.jonata.SEASolutions.dto.SetorDto;
import com.jonata.SEASolutions.model.Setor;

import java.util.List;
import java.util.Set;

public interface SetorService {
    SetorDto cadastrar(Setor setor);
    List<SetorDto> listar();
    SetorDto detalharPorId(Long id);
    SetorDto detalharPorNome(String nome);
    Boolean remover(Long id);
}
