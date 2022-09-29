package com.jonata.SEASolutions.controller;

import com.jonata.SEASolutions.model.Cargo;
import com.jonata.SEASolutions.model.Setor;
import com.jonata.SEASolutions.payload.dto.CargoDto;
import com.jonata.SEASolutions.payload.dto.SetorDto;
import com.jonata.SEASolutions.payload.form.CargoForm;
import com.jonata.SEASolutions.payload.form.SetorForm;
import com.jonata.SEASolutions.service.implementation.CargoServiceImpl;
import com.jonata.SEASolutions.service.implementation.SetorServiceImpl;
import com.jonata.SEASolutions.util.CargoCreator;
import com.jonata.SEASolutions.util.SetorCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Boolean.TRUE;


@ExtendWith(SpringExtension.class)
class CargoControllerTest {
    @InjectMocks
    private CargoController cargoController;

    @Mock
    private CargoServiceImpl cargoServiceMock;

    @BeforeEach
    void setUp(){
        Cargo cargoValido = CargoCreator.criarCargoValido();
        Cargo cargoAtualizado = CargoCreator.criarCargoAtualizado();
        Page<CargoDto> cargoDtoPage = new PageImpl<>(List.of(cargoValido).stream().map(CargoDto::new).collect(Collectors.toList()));

        BDDMockito.when(cargoServiceMock.listar(ArgumentMatchers.any(Pageable.class))).thenReturn(cargoDtoPage);
        BDDMockito.when(cargoServiceMock.detalharPorId(ArgumentMatchers.any())).thenReturn(new CargoDto(cargoValido));
        BDDMockito.when(cargoServiceMock.cadastrar(ArgumentMatchers.any(CargoForm.class))).thenReturn(new CargoDto(cargoValido));
        BDDMockito.when(cargoServiceMock.atualizar(ArgumentMatchers.anyLong(),ArgumentMatchers.any()))
                .thenReturn(new CargoDto(cargoAtualizado));
        BDDMockito.when(cargoServiceMock.remover(ArgumentMatchers.anyLong())).thenReturn(TRUE);
    }

    @Test
    @DisplayName("Deveria retornar uma Pagina de CargoDto")
    public void deveriaRetornarPaginaDeCargoDto_quandoListar() {
        String nomeEsperado = CargoCreator.criarCargoValido().getNome();
        Page<CargoDto> cargoDtoPage = cargoController.listar(Pageable.ofSize(1)).getBody();

        Assertions.assertThat(cargoDtoPage).hasSize(1);
        Assertions.assertThat(cargoDtoPage.toList().get(0).getNome()).isEqualTo(nomeEsperado);
    }

    @Test
    @DisplayName("Deveria retornar CargoDto detalhado ao buscar por Id")
    public void deveriaRetornarCargoDetalhadoAoBuscarPorId() {
        Cargo cargo = CargoCreator.criarCargoValido();
        CargoDto cargoDto = cargoController.detalhar(cargo.getId()).getBody();

        Assertions.assertThat(cargoDto).isNotNull();
        Assertions.assertThat(cargoDto.getNome()).isEqualTo(new CargoDto(cargo).getNome());
    }

    @Test
    @DisplayName("Deveria retornar status created ao criar cargo")
    public void deveriaRetornarCriado_quandoCriarCargo() throws Exception {
        CargoForm cargoForm = CargoCreator.criarCargoASerSalvo();
        ResponseEntity<CargoDto> response = cargoController.cadastrar(cargoForm);
        CargoDto cargoDto = cargoController.cadastrar(cargoForm).getBody();

        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.CREATED.value());
        Assertions.assertThat(cargoDto.getNome()).isEqualTo(cargoForm.getNome());
    }

    @Test
    @DisplayName("Deveria retornar CargoDto atualizado ao atualizar setor")
    public void deveriaRetornarSetorDtoAtualizado_quandoAtualizarSetor() {
        CargoDto cargoDto = new CargoDto(CargoCreator.criarCargoAtualizado());
        CargoForm cargoForm = CargoCreator.criarCargoASerSalvo();
        cargoForm.setNome("Test 2");
        CargoDto cargoDtoAtualizado = cargoController.atualizar(1l, cargoForm).getBody();

        Assertions.assertThat(cargoDtoAtualizado).isNotNull();
        Assertions.assertThat(cargoDto.getNome()).isEqualTo(cargoDtoAtualizado.getNome());
        Assertions.assertThat(cargoDto.getId()).isEqualTo(cargoDtoAtualizado.getId());
    }

    @Test
    @DisplayName("Deveria retornar True ao deletar cargo por Id")
    public void deveriaRetornarTrue_quandoDeletarCargoPorId() {
        Boolean success = cargoController.remover(1l).getBody().getSuccess();
        Assertions.assertThat(success).isEqualTo(TRUE);
    }
}