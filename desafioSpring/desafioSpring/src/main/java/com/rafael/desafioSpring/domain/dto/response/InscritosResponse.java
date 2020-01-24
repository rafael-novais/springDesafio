package com.rafael.desafioSpring.domain.dto.response;

import com.rafael.desafioSpring.domain.entities.Evento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscritosResponse {

    private Integer idEvento;
    private Integer idParticipacao;
    private String LoginParticipante;


}