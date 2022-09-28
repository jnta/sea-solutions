package com.jonata.SEASolutions.service;

import com.jonata.SEASolutions.payload.dto.TrabalhadorDto;
import com.jonata.SEASolutions.model.Trabalhador;
import com.jonata.SEASolutions.payload.form.TrabalhadorForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TrabalhadorService {

    TrabalhadorDto cadastrar(TrabalhadorForm trabalhadorForm);
    Page<TrabalhadorDto> listar(Pageable pageable);
    TrabalhadorDto detalharPorId(Long id);
    List<TrabalhadorDto> detalharPorNome(String nome);
    TrabalhadorDto detalharPorCpf(String cpf);
    TrabalhadorDto atualizar(Long id, TrabalhadorForm trabalhadorForm);
    Boolean remover(Long id);
}
