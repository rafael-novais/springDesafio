package com.rafael.desafioSpring.domain.dto.request;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipacaoCreateRequest {

    private Integer IdEvento;
    private String LoginParticipante;
    private Boolean FlagPresente;
    private Integer Nota;
    private String Comentario;
    
}