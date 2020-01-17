package com.rafael.desafioSpring.domain.mapper;

import com.rafael.desafioSpring.domain.dto.request.CategoriaCreateRequest;
import com.rafael.desafioSpring.domain.dto.response.CategoriaResponse;
import com.rafael.desafioSpring.domain.entities.CategoriaEvento;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    private final ModelMapper mapper;

    @Autowired
    public CategoriaMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public CategoriaResponse toDto(CategoriaEvento input) {
        return mapper.map(input, CategoriaResponse.class);
    }

    public CategoriaEvento fromDto(CategoriaCreateRequest input) {
        
        CategoriaEvento model = mapper.map(input, CategoriaEvento.class);
        // CategoriaEvento categoriaEvento = new CategoriaEvento();
        // categoriaEvento.setIdCategoriaEvento(input.getIdCategoriaEvento());

        // model.setIdCategoriaEvento(categoriaEvento);

        // StatusEvento statusEvento = new StatusEvento();
        // statusEvento.setIdEventoStatus(input.getIdEventoStatus());
        // model.setIdEventoStatus(statusEvento);

        //model.setIdEventoStatus(StatusEvento.builder().IdEventoStatus(input.getIdEventoStatus()).build());

        return model;
    }

}