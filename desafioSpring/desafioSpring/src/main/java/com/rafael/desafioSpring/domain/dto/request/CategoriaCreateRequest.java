package com.rafael.desafioSpring.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaCreateRequest {

    //@NotEmpty(message = "Data não pode ser null")
    private Integer IdCategoriaEvento;
    private String NomeCategoria;

}