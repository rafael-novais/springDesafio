package com.rafael.desafioSpring.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VagasDisponiveisResponse {

    private Integer idEvento;
    private String nomeEvento;
    private Integer limiteVagas;
    private Integer vagasDisponiveis;

}