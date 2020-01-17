package com.rafael.desafioSpring.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusResponse {

    private Integer IdEventoStatus;
    private String NomeStatus;

}