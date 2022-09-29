package com.jonata.SEASolutions.controller;

import com.jonata.SEASolutions.payload.dto.CargoDto;
import com.jonata.SEASolutions.payload.form.CargoForm;
import com.jonata.SEASolutions.service.CargoService;
import com.jonata.SEASolutions.service.implementation.CargoServiceImpl;
import com.jonata.SEASolutions.util.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cargos")
public class CargoController {
    private final CargoServiceImpl cargoService;

    public CargoController(CargoServiceImpl cargoService) {
        this.cargoService = cargoService;
    }

    @PostMapping
    public ResponseEntity<CargoDto> cadastrar(@RequestBody @Valid CargoForm cargoForm) {
        CargoDto cargoDto = cargoService.cadastrar(cargoForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(cargoDto);
    }

    @GetMapping
    public ResponseEntity<Page<CargoDto>> listar(@PageableDefault Pageable pageable) {
        Page<CargoDto> cargos = cargoService.listar(pageable);
        return ResponseEntity.ok(cargos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CargoDto> detalhar(@PathVariable(value = "id") Long id) {
        CargoDto cargoDto = cargoService.detalharPorId(id);
        return ResponseEntity.ok(cargoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CargoDto> atualizar(@PathVariable(value = "id") Long id,@RequestBody @Valid CargoForm cargoForm) {
        CargoDto cargoAtualizado = cargoService.atualizar(id, cargoForm);
        return ResponseEntity.ok(cargoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletar(@PathVariable(value = "id") Long id) {
        Boolean removido = cargoService.remover(id);
        return ResponseEntity.ok(new ApiResponse("Cargo removido!", removido));
    }

}
