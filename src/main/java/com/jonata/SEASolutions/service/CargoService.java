package com.jonata.SEASolutions.service;

import com.jonata.SEASolutions.dto.CargoDto;
import com.jonata.SEASolutions.model.Cargo;

import java.util.List;

public interface CargoService {
    CargoDto cadastrar(Cargo cargo);
    List<CargoDto> listar();
    CargoDto detalharPorId(Long id);
    CargoDto atualizar(CargoDto cargoDto);
    Boolean remover(Long id);
}
