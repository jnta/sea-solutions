package com.jonata.SEASolutions.service.implementation;

import com.jonata.SEASolutions.dto.CargoDto;
import com.jonata.SEASolutions.exception.ResourceNotFoundException;
import com.jonata.SEASolutions.model.Cargo;
import com.jonata.SEASolutions.model.Setor;
import com.jonata.SEASolutions.repository.CargoRepo;
import com.jonata.SEASolutions.repository.SetorRepo;
import com.jonata.SEASolutions.service.CargoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Boolean.TRUE;

@Service
public class CargoServiceImpl implements CargoService {
    private final CargoRepo cargoRepo;
    private final SetorRepo setorRepo;

    public CargoServiceImpl(CargoRepo cargoRepo, SetorRepo setorRepo) {
        this.cargoRepo = cargoRepo;
        this.setorRepo = setorRepo;
    }

    @Override
    public CargoDto cadastrar(Cargo cargo) {
        cargo.setId(null);
        Cargo cargoSalvo = cargoRepo.save(cargo);

        return new CargoDto(cargoSalvo);
    }

    @Override
    public List<CargoDto> listar() {
        return cargoRepo.findAll().stream().map(CargoDto::new).collect(Collectors.toList());
    }

    @Override
    public CargoDto detalharPorId(Long id) {
        Cargo cargo = this.buscarCargo(id);
        return new CargoDto(cargo);
    }

    @Override
    public CargoDto atualizar(CargoDto cargoDto) {
        Cargo cargo = this.buscarCargo(cargoDto.getId());
        Setor setor = this.buscarSetor(cargoDto.getSetor());

        cargo.setNome(cargoDto.getNome());
        cargo.setSalario(cargoDto.getSalario());
        cargo.setSetor(setor);
        return new CargoDto(cargo);
    }

    @Override
    public Boolean remover(Long id) {
        Cargo cargo = this.buscarCargo(id);
        cargoRepo.delete(cargo);
        return TRUE;
    }


    private Cargo buscarCargo(Long id) {
        return cargoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cargo não encontrado!"));
    }

    private Setor buscarSetor(String nome) {
        return setorRepo.findByNome(nome).orElseThrow(() -> new ResourceNotFoundException("Setor não encontrado!"));
    }
}
