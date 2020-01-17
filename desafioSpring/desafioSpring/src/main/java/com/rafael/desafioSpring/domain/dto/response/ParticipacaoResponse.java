package com.rafael.desafioSpring.domain.dto.response;

import com.rafael.desafioSpring.domain.entities.Evento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipacaoResponse {

    private Integer IdParticipacao;
    private Evento IdEvento;
    private String LoginParticipante;
    private Boolean FlagPresente;
    private Integer Nota;
    private String Comentario;
}