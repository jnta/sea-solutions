package com.jonata.SEASolutions.service.implementation;

import com.jonata.SEASolutions.payload.dto.CargoDto;
import com.jonata.SEASolutions.exception.ResourceNotFoundException;
import com.jonata.SEASolutions.model.Cargo;
import com.jonata.SEASolutions.model.Setor;
import com.jonata.SEASolutions.payload.form.CargoForm;
import com.jonata.SEASolutions.repository.CargoRepo;
import com.jonata.SEASolutions.repository.SetorRepo;
import com.jonata.SEASolutions.service.CargoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.Boolean.TRUE;

@Service
@Transactional
public class CargoServiceImpl implements CargoService {
    private final CargoRepo cargoRepo;
    private final SetorRepo setorRepo;

    public CargoServiceImpl(CargoRepo cargoRepo, SetorRepo setorRepo) {
        this.cargoRepo = cargoRepo;
        this.setorRepo = setorRepo;
    }

    @Override
    public CargoDto cadastrar(CargoForm cargoForm) {
        Setor setor = this.buscarSetor(cargoForm.getSetorId());
        Cargo cargo = new Cargo();
        cargo.setNome(cargoForm.getNome());
        cargo.setSetor(setor);
        cargo.setSalario(cargoForm.getSalario());

        Cargo cargoSalvo = cargoRepo.save(cargo);
        return new CargoDto(cargoSalvo);
    }

    @Override
    public Page<CargoDto> listar(Pageable pageable) {
        return cargoRepo.findAll(pageable).map(CargoDto::new);
    }

    @Override
    public CargoDto detalharPorId(Long id) {
        Cargo cargo = this.buscarCargo(id);
        return new CargoDto(cargo);
    }

    @Override
    public CargoDto atualizar(Long id, CargoForm cargoForm) {
        Cargo cargo = this.buscarCargo(id);
        Setor setor = this.buscarSetor(cargoForm.getSetorId());

        cargo.setNome(cargoForm.getNome());
        cargo.setSalario(cargoForm.getSalario());
        cargo.setSetor(setor);
        return new CargoDto(cargo);
    }

    @Override
    public Boolean remover(Long id) {
        Cargo cargo = this.buscarCargo(id);
        cargoRepo.deleteById(id);
        return TRUE;
    }


    private Cargo buscarCargo(Long id) {
        return cargoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cargo: " + id + " não encontrado!"));
    }

    private Setor buscarSetor(Long id) {
        return setorRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Setor: " + id + " não encontrado!"));
    }
}
