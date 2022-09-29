package com.jonata.SEASolutions.controller;

import com.jonata.SEASolutions.payload.dto.SetorDto;
import com.jonata.SEASolutions.payload.form.SetorForm;
import com.jonata.SEASolutions.service.SetorService;
import com.jonata.SEASolutions.service.implementation.SetorServiceImpl;
import com.jonata.SEASolutions.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/setores")
public class SetorController {
    private final SetorServiceImpl setorService;

    public SetorController(SetorServiceImpl setorService) {
        this.setorService = setorService;
    }



    @PostMapping
    public ResponseEntity<SetorDto> cadastrar(@RequestBody @Valid SetorForm setorForm) {
        SetorDto setorDto = setorService.cadastrar(setorForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(setorDto);
    }

    @GetMapping
    public ResponseEntity<List<SetorDto>> listar() {
        List<SetorDto> setorDtoList = setorService.listar();
        return ResponseEntity.ok(setorDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SetorDto> detalharPorId(@PathVariable(value = "id") Long id) {
        SetorDto setorDto = setorService.detalharPorId(id);
        return ResponseEntity.ok(setorDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SetorDto> atualizar(@PathVariable(value = "id") Long id, @RequestBody @Valid SetorForm setorForm) {
        SetorDto setorDto = setorService.atualizar(id, setorForm);
        return ResponseEntity.ok(setorDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> remover(@PathVariable(value = "id") Long id) {
        Boolean removido = setorService.remover(id);
        return ResponseEntity.ok(new ApiResponse("Setor: " + id + " removido!", removido));
    }
}
