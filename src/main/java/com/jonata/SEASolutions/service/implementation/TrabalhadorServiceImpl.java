package com.jonata.SEASolutions.service.implementation;

import com.jonata.SEASolutions.exception.ResourceNotFoundException;
import com.jonata.SEASolutions.model.Cargo;
import com.jonata.SEASolutions.model.Trabalhador;
import com.jonata.SEASolutions.payload.dto.TrabalhadorDto;
import com.jonata.SEASolutions.payload.form.TrabalhadorForm;
import com.jonata.SEASolutions.repository.CargoRepo;
import com.jonata.SEASolutions.repository.TrabalhadorRepo;
import com.jonata.SEASolutions.service.TrabalhadorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TrabalhadorServiceImpl implements TrabalhadorService {
    private final TrabalhadorRepo trabalhadorRepo;
    private final CargoRepo cargoRepo;

    public TrabalhadorServiceImpl(TrabalhadorRepo trabalhadorRepo, CargoRepo cargoRepo) {
        this.trabalhadorRepo = trabalhadorRepo;
        this.cargoRepo = cargoRepo;
    }

    @Override
    public TrabalhadorDto cadastrar(TrabalhadorForm trabalhadorForm) {
        Cargo cargo = buscarCargo(trabalhadorForm.getCargoId());

        Trabalhador trabalhador = new Trabalhador();
        trabalhador.setNome(trabalhadorForm.getNome());
        trabalhador.setCpf(trabalhadorForm.getCpf());
        trabalhador.setCargo(cargo);

        Trabalhador trabalhadorSalvo = trabalhadorRepo.save(trabalhador);
        return new TrabalhadorDto(trabalhadorSalvo);
    }

    @Override
    public Page<TrabalhadorDto> listar(Pageable pageable) {
        return trabalhadorRepo.findAll(pageable).map(TrabalhadorDto::new);
    }

    @Override
    public TrabalhadorDto detalharPorId(Long id) {
        Trabalhador trabalhador = this.buscarPorId(id);
        return new TrabalhadorDto(trabalhador);
    }

    @Override
    public List<TrabalhadorDto> detalharPorNome(String nome) {
        List<Trabalhador> trabalhadores = this.buscarPorNome(nome);
        return trabalhadores.stream().map(TrabalhadorDto::new).collect(Collectors.toList());
    }

    @Override
    public TrabalhadorDto detalharPorCpf(String cpf) {
        Trabalhador trabalhador = this.buscarPorCpf(cpf);
        return new TrabalhadorDto(trabalhador);
    }

    @Override
    public TrabalhadorDto atualizar(Long id, TrabalhadorForm trabalhadorForm) {
        Trabalhador trabalhador = this.buscarPorId(id);
        Cargo cargo = this.buscarCargo(trabalhadorForm.getCargoId());

        trabalhador.setNome(trabalhadorForm.getNome());
        trabalhador.setCpf(trabalhadorForm.getCpf());
        trabalhador.setCargo(cargo);
        Trabalhador trabalhadorAtualizado = trabalhadorRepo.save(trabalhador);

        return new TrabalhadorDto(trabalhadorAtualizado);
    }

    @Override
    public Boolean remover(Long id) {
        Trabalhador trabalhador = buscarPorId(id);
        trabalhadorRepo.delete(trabalhador);
        return Boolean.TRUE;
    }

    private Trabalhador buscarPorId(Long id) {
        return trabalhadorRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Trabalhador não encontrado!"));
    }

    private List<Trabalhador> buscarPorNome(String nome) {
        return trabalhadorRepo.findByNome(nome);
    }

    private Trabalhador buscarPorCpf(String cpf) {
        return trabalhadorRepo.findByCpf(cpf).orElseThrow(() -> new ResourceNotFoundException("Trabalhador não encontrado!"));
    }

    private Cargo buscarCargo(Long id) {
        return cargoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cargo: " + id + " não encontrado!"));
    }
}
