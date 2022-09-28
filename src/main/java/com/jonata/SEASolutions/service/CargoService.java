package com.jonata.SEASolutions.service;

import com.jonata.SEASolutions.payload.dto.CargoDto;
import com.jonata.SEASolutions.payload.form.CargoForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CargoService {
    CargoDto cadastrar(CargoForm CargoForm);
    Page<CargoDto> listar(Pageable pageable);
    CargoDto detalharPorId(Long id);
    CargoDto atualizar(Long id, CargoForm cargoForm);
    Boolean remover(Long id);
}
