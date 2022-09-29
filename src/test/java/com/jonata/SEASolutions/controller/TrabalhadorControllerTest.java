package com.jonata.SEASolutions.controller;

import com.jonata.SEASolutions.model.Cargo;
import com.jonata.SEASolutions.model.Trabalhador;
import com.jonata.SEASolutions.payload.dto.CargoDto;
import com.jonata.SEASolutions.payload.dto.TrabalhadorDto;
import com.jonata.SEASolutions.payload.form.CargoForm;
import com.jonata.SEASolutions.payload.form.TrabalhadorForm;
import com.jonata.SEASolutions.service.implementation.CargoServiceImpl;
import com.jonata.SEASolutions.service.implementation.TrabalhadorServiceImpl;
import com.jonata.SEASolutions.util.CargoCreator;
import com.jonata.SEASolutions.util.TrabalhadorCreator;
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
class TrabalhadorControllerTest {
    @InjectMocks
    private TrabalhadorController trabalhadorController;

    @Mock
    private TrabalhadorServiceImpl trabalhadorServiceMock;

    @BeforeEach
    void setUp(){
        Trabalhador trabalhadorValido = TrabalhadorCreator.criarTrabalhadorValido();
        Trabalhador trabalhadorAtualizado = TrabalhadorCreator.criarTrabalhadorAtualizado();
        Page<TrabalhadorDto> trabalhadorDtoPage = new PageImpl<>(List.of(trabalhadorValido).stream().map(TrabalhadorDto::new).collect(Collectors.toList()));

        BDDMockito.when(trabalhadorServiceMock.listar(ArgumentMatchers.any(Pageable.class))).thenReturn(trabalhadorDtoPage);

        BDDMockito.when(trabalhadorServiceMock.detalharPorId(ArgumentMatchers.any())).thenReturn(new TrabalhadorDto(trabalhadorValido));

        BDDMockito.when(trabalhadorServiceMock.cadastrar(ArgumentMatchers.any(TrabalhadorForm.class)))
                .thenReturn(new TrabalhadorDto(trabalhadorValido));

        BDDMockito.when(trabalhadorServiceMock.atualizar(ArgumentMatchers.anyLong(),ArgumentMatchers.any()))
                .thenReturn(new TrabalhadorDto(trabalhadorValido));

        BDDMockito.when(trabalhadorServiceMock.remover(ArgumentMatchers.anyLong())).thenReturn(TRUE);
    }

    @Test
    @DisplayName("Deveria retornar uma Pagina de TrabalhadorDto")
    public void deveriaRetornarPaginaDeTrabalhadorDto_quandoListar() {
        String nomeEsperado = TrabalhadorCreator.criarTrabalhadorValido().getNome();
        Page<TrabalhadorDto> trabalhadorDtoPage = trabalhadorController.listar(Pageable.ofSize(1)).getBody();

        Assertions.assertThat(trabalhadorDtoPage).hasSize(1);
        Assertions.assertThat(trabalhadorDtoPage.toList().get(0).getNome()).isEqualTo(nomeEsperado);
    }

    @Test
    @DisplayName("Deveria retornar TrabalhadorDto detalhado ao buscar por Id")
    public void deveriaRetornarTrabalhadorDetalhadoAoBuscarPorId() {
        Trabalhador trabalhador = TrabalhadorCreator.criarTrabalhadorValido();
        TrabalhadorDto trabalhadorDto = trabalhadorController.detalharPorId(trabalhador.getId()).getBody();

        Assertions.assertThat(trabalhadorDto).isNotNull();
        Assertions.assertThat(trabalhadorDto.getNome()).isEqualTo(new TrabalhadorDto(trabalhador).getNome());
    }

    @Test
    @DisplayName("Deveria retornar status created ao criar trabalhador")
    public void deveriaRetornarCriado_quandoCriarTrabalhador() throws Exception {
        TrabalhadorForm trabalhadorForm = TrabalhadorCreator.criarTrabalhadorASerSalvo();
        ResponseEntity<TrabalhadorDto> response = trabalhadorController.cadastrar(trabalhadorForm);
        TrabalhadorDto trabalhadorDto = trabalhadorController.cadastrar(trabalhadorForm).getBody();

        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.CREATED.value());
        Assertions.assertThat(trabalhadorDto.getNome()).isEqualTo(trabalhadorForm.getNome());
    }

    @Test
    @DisplayName("Deveria retornar TrabalhadorDto atualizado ao atualizar trabalhador")
    public void deveriaRetornarTrabalhadorAtualizado_quandoAtualizarTrabalhador() {
        TrabalhadorDto trabalhadorDto = new TrabalhadorDto(TrabalhadorCreator.criarTrabalhadorAtualizado());
        TrabalhadorForm trabalhadorForm = TrabalhadorCreator.criarTrabalhadorASerSalvo();
        trabalhadorForm.setNome("Test 2");
        TrabalhadorDto trabalhadorDtoAtualizado = trabalhadorController.atualizar(1l, trabalhadorForm).getBody();

        Assertions.assertThat(trabalhadorDtoAtualizado).isNotNull();
        Assertions.assertThat(trabalhadorDto.getNome()).isEqualTo(trabalhadorDtoAtualizado.getNome());
        Assertions.assertThat(trabalhadorDto.getId()).isEqualTo(trabalhadorDtoAtualizado.getId());
    }

    @Test
    @DisplayName("Deveria retornar True ao deletar trabalhador por Id")
    public void deveriaRetornarTrue_quandoDeletarTrabalhadorPorId() {
        Boolean success = trabalhadorController.remover(1l).getBody().getSuccess();
        Assertions.assertThat(success).isEqualTo(TRUE);
    }
}