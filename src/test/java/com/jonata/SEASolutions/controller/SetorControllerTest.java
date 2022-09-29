package com.jonata.SEASolutions.controller;

import com.jonata.SEASolutions.model.Setor;
import com.jonata.SEASolutions.payload.dto.SetorDto;
import com.jonata.SEASolutions.payload.form.SetorForm;
import com.jonata.SEASolutions.service.implementation.SetorServiceImpl;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Boolean.TRUE;


@ExtendWith(SpringExtension.class)
class SetorControllerTest {
    @InjectMocks
    private SetorController setorController;

    @Mock
    private SetorServiceImpl setorServiceMock;

    @BeforeEach
    void setUp(){
        Setor setorValido = SetorCreator.criarSetorValido();
        Setor setorAtualizado = SetorCreator.criarSetorAtualizado();
        List<SetorDto> setorDtoList = List.of(setorValido).stream().map(SetorDto::new).collect(Collectors.toList());

        BDDMockito.when(setorServiceMock.listar()).thenReturn(setorDtoList);
        BDDMockito.when(setorServiceMock.detalharPorId(ArgumentMatchers.any())).thenReturn(new SetorDto(setorValido));
        BDDMockito.when(setorServiceMock.cadastrar(ArgumentMatchers.any(SetorForm.class))).thenReturn(new SetorDto(setorValido));
        BDDMockito.when(setorServiceMock.atualizar(ArgumentMatchers.anyLong(),ArgumentMatchers.any()))
                .thenReturn(new SetorDto(setorAtualizado));
        BDDMockito.when(setorServiceMock.remover(ArgumentMatchers.anyLong())).thenReturn(TRUE);
    }

    @Test
    @DisplayName("Deveria retornar uma lista de SetorDto")
    public void deveriaRetornarListaDeSetorDto_quandoListar() {
        String nomeEsperado = SetorCreator.criarSetorASerSalvo().getNome();
        List<SetorDto> setorDtoList = setorController.listar().getBody();

        Assertions.assertThat(setorDtoList).hasSize(1);
        Assertions.assertThat(setorDtoList.get(0).getNome()).isEqualTo(nomeEsperado);
    }

    @Test
    @DisplayName("Deveria retornar SetorDto detalhado ao buscar por Id")
    public void deveriaRetornarSetorDetalhadoAoBuscarPorId() {
        Setor setor = SetorCreator.criarSetorValido();
        SetorDto setorDto = setorController.detalharPorId(setor.getId()).getBody();

        Assertions.assertThat(setorDto).isNotNull();
        Assertions.assertThat(setorDto.getNome()).isEqualTo(new SetorDto(setor).getNome());
    }

    @Test
    @DisplayName("Deveria retornar status created ao criar setor")
    public void deveriaRetornarCriado_quandoCriarSetor() throws Exception {
        SetorForm setorASerSalvo = SetorCreator.criarSetorASerSalvo();
        ResponseEntity<SetorDto> response = setorController.cadastrar(setorASerSalvo);
        SetorDto setorDto = setorController.cadastrar(setorASerSalvo).getBody();

        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.CREATED.value());
        Assertions.assertThat(setorDto.getNome()).isEqualTo(setorASerSalvo.getNome());
    }

    @Test
    @DisplayName("Deveria retornar SetorDto atualizado ao atualizar setor")
    public void deveriaRetornarSetorDtoAtualizado_quandoAtualizarSetor() {
        SetorDto setorDto = new SetorDto(SetorCreator.criarSetorAtualizado());
        SetorDto setorDtoAtualizado = setorController.atualizar(1l, new SetorForm("Teste 2")).getBody();

        Assertions.assertThat(setorDtoAtualizado).isNotNull();
        Assertions.assertThat(setorDto.getNome()).isEqualTo(setorDtoAtualizado.getNome());
        Assertions.assertThat(setorDto.getId()).isEqualTo(setorDtoAtualizado.getId());
    }

    @Test
    @DisplayName("Deveria retornar True ao deletar setor por Id")
    public void deveriaRetornarTrue_quandoDeletarSetorPorId() {
        Boolean success = setorController.remover(1l).getBody().getSuccess();
        Assertions.assertThat(success).isEqualTo(TRUE);
    }
}