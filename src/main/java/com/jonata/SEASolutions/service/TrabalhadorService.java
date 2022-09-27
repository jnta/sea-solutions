package com.jonata.SEASolutions.service;

import com.jonata.SEASolutions.dto.TrabalhadorDto;
import com.jonata.SEASolutions.model.Trabalhador;

import java.util.List;

public interface TrabalhadorService {

    TrabalhadorDto cadastrar(Trabalhador trabalhador);
    List<TrabalhadorDto> listar();
    TrabalhadorDto detalharPorId(Long id);
    List<TrabalhadorDto> detalharPorNome(String nome);
    TrabalhadorDto detalharPorCpf(String cpf);
    TrabalhadorDto atualizar(TrabalhadorDto trabalhadorDto);
    Boolean remover(Long id);
}
