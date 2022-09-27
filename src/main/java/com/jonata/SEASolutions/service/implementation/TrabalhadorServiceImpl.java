package com.jonata.SEASolutions.service.implementation;

import com.jonata.SEASolutions.dto.TrabalhadorDto;
import com.jonata.SEASolutions.exception.ResourceNotFoundException;
import com.jonata.SEASolutions.model.Trabalhador;
import com.jonata.SEASolutions.repository.TrabalhadorRepo;
import com.jonata.SEASolutions.service.TrabalhadorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrabalhadorServiceImpl implements TrabalhadorService {
    private final TrabalhadorRepo trabalhadorRepo;

    public TrabalhadorServiceImpl(TrabalhadorRepo trabalhadorRepo) {
        this.trabalhadorRepo = trabalhadorRepo;
    }

    @Override
    public TrabalhadorDto cadastrar(Trabalhador trabalhador) {
        Trabalhador trabalhadorSalvo = trabalhadorRepo.save(trabalhador);
        return new TrabalhadorDto(trabalhadorSalvo);
    }

    @Override
    public List<TrabalhadorDto> listar() {
        return trabalhadorRepo.findAll().stream().map(TrabalhadorDto::new).collect(Collectors.toList());
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
    public TrabalhadorDto atualizar(TrabalhadorDto trabalhadorDto) {
        return null;
    }

    @Override
    public Boolean remover(Long id) {
        return null;
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
}
