package com.jonata.SEASolutions.service.implementation;

import com.jonata.SEASolutions.payload.dto.SetorDto;
import com.jonata.SEASolutions.exception.ResourceNotFoundException;
import com.jonata.SEASolutions.model.Setor;
import com.jonata.SEASolutions.payload.form.SetorForm;
import com.jonata.SEASolutions.repository.SetorRepo;
import com.jonata.SEASolutions.service.SetorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Boolean.TRUE;

@Service
@Transactional
public class SetorServiceImpl implements SetorService {
    private final SetorRepo setorRepo;

    public SetorServiceImpl(SetorRepo setorRepo) {
        this.setorRepo = setorRepo;
    }

    @Override
    public SetorDto cadastrar(SetorForm setorForm) {
        Setor setor = new Setor();
        setor.setNome(setorForm.getNome());

        Setor setorSalvo = setorRepo.save(setor);
        return new SetorDto(setorSalvo);
    }

    @Override
    public List<SetorDto> listar() {
        return setorRepo.findAll().stream().map(SetorDto::new).collect(Collectors.toList());
    }

    @Override
    public SetorDto detalharPorId(Long id) {
        Setor setor = this.buscarSetorPorId(id);
        return new SetorDto(setor);
    }

    @Override
    public SetorDto atualizar(Long id, SetorForm setorForm) {
        Setor setor = buscarSetorPorId(id);
        setor.setNome(setorForm.getNome());

        Setor setorAtualizado = setorRepo.save(setor);
        return new SetorDto(setorAtualizado);
    }

    @Override
    public Boolean remover(Long id) {
        Setor setor = this.buscarSetorPorId(id);
        setorRepo.delete(setor);
        return TRUE;
    }

    private Setor buscarSetorPorId(Long id) {
        return setorRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Setor: " + id + " não encontrado!"));
    }

    private Setor buscarSetorPorNome(String nome) {
        return setorRepo.findByNome(nome).orElseThrow(() -> new ResourceNotFoundException("Setor " + nome +" não encontrado!"));
    }
}
