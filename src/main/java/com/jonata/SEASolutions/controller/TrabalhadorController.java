package com.jonata.SEASolutions.controller;

import com.jonata.SEASolutions.payload.dto.TrabalhadorDto;
import com.jonata.SEASolutions.payload.form.TrabalhadorForm;
import com.jonata.SEASolutions.service.TrabalhadorService;
import com.jonata.SEASolutions.util.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/trabalhadores")
public class TrabalhadorController {
    private final TrabalhadorService trabalhadorService;

    public TrabalhadorController(TrabalhadorService trabalhadorService) {
        this.trabalhadorService = trabalhadorService;
    }

    @PostMapping
    public ResponseEntity<TrabalhadorDto> cadastrar(@RequestBody @Valid TrabalhadorForm trabalhadorForm) {
        TrabalhadorDto trabalhadorDto = trabalhadorService.cadastrar(trabalhadorForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(trabalhadorDto);
    }

    @GetMapping
    public ResponseEntity<Page<TrabalhadorDto>> listar(@PageableDefault Pageable pageable) {
        Page<TrabalhadorDto> trabalhadorDtoPage = trabalhadorService.listar(pageable);
        return ResponseEntity.ok(trabalhadorDtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrabalhadorDto> detalharPorId(@PathVariable(value = "id") Long id) {
        TrabalhadorDto trabalhadorDto = trabalhadorService.detalharPorId(id);
        return ResponseEntity.ok(trabalhadorDto);
    }

    @GetMapping("/{nome}")
    public ResponseEntity<List<TrabalhadorDto>> detalharPorNome(@PathVariable(value = "nome") String nome) {
        List<TrabalhadorDto> trabalhadorDtoList = trabalhadorService.detalharPorNome(nome);
        return ResponseEntity.ok(trabalhadorDtoList);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<TrabalhadorDto> detalharPorCpf(@PathVariable(value = "cpf") String cpf) {
        TrabalhadorDto trabalhadorDto = trabalhadorService.detalharPorCpf(cpf);
        return ResponseEntity.ok(trabalhadorDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrabalhadorDto> atualizar(@PathVariable(value = "id") Long id, @RequestBody TrabalhadorForm trabalhadorForm) {
        TrabalhadorDto trabalhadorDto = trabalhadorService.atualizar(id, trabalhadorForm);
        return ResponseEntity.ok(trabalhadorDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> remover(@PathVariable(value = "id") Long id) {
        Boolean removido = trabalhadorService.remover(id);
        return ResponseEntity.ok(new ApiResponse("Trabalhador: " + id + " removido", removido));
    }

}
