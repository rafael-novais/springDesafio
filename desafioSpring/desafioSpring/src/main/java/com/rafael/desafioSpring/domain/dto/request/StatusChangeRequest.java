package com.rafael.desafioSpring.domain.dto.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusChangeRequest {

    @NotNull(message = "IdEventoStatus não pode ser null")
    private Integer IdEventoStatus;

}