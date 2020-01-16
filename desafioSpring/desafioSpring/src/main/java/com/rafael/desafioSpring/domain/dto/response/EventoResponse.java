package com.rafael.desafioSpring.domain.dto.response;

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
public class EventoResponse {

    private String nome;
    private CategoriaEvento categoria;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private String local;
    private String descricao;
    private Integer limiteVagas;
}