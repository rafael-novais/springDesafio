package com.rafael.desafioSpring.domain.dto.request;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import com.rafael.desafioSpring.domain.entities.CategoriaEvento;
import com.rafael.desafioSpring.domain.entities.StatusEvento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventoCreateRequest {

    //@NotEmpty(message = "Data não pode ser null")
    private Integer idCategoriaEvento;

    private Integer idEventoStatus;

    //@NotEmpty(message = "Data não pode ser null")
    private String nome;

    private LocalDateTime dataHoraInicio;

    private LocalDateTime dataHoraFim;

    //@NotEmpty(message = "Data não pode ser null")
    private String local;

    //@NotEmpty(message = "Data não pode ser null")
    private String descricao;

    //@NotEmpty(message = "Data não pode ser null")
    private Integer limiteVagas;
}