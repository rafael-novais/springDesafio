package com.rafael.desafioSpring.domain.dto.request;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaCreateRequest {

    
    private Integer IdCategoriaEvento;
    //@NotEmpty(message = "NomeCategoria não pode ser null")
    private String NomeCategoria;

}