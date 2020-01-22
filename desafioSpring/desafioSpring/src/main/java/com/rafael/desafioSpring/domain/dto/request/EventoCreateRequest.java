package com.rafael.desafioSpring.domain.dto.request;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventoCreateRequest {

    @NotNull(message = "idCategoriaEvento não pode ser null")
    private Integer idCategoriaEvento;

    @Size(max = 255)
    @NotBlank(message = "Sem entradas em branco por favor")
    @NotEmpty(message = "Nome não pode ser null")
    private String nome;

    @NotNull(message = "dataHoraInicio não pode ser null")
    @Future
    private Date dataHoraInicio;

    @NotNull(message = "dataHoraFim não pode ser null")
    @Future
    private Date dataHoraFim;

    @Size(max = 255)
    @NotBlank(message = "Sem entradas em branco por favor")
    @NotEmpty(message = "Local não pode ser null")
    private String local;

    @Size(max = 255)
    @NotBlank(message = "Sem entradas em branco por favor")
    @NotEmpty(message = "Descrição não pode ser null")
    private String descricao;

    @NotNull(message = "Limite vagas não pode ser null")
    private Integer limiteVagas;
}