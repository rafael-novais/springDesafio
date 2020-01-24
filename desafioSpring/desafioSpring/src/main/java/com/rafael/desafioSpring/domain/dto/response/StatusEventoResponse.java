package com.rafael.desafioSpring.domain.dto.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusEventoResponse {

    private Integer idEvento;
    private String nomeEvento;
    private Date data;
    private String status;

}