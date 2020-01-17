package com.rafael.desafioSpring.domain.mapper;

import com.rafael.desafioSpring.domain.dto.request.CategoriaCreateRequest;
import com.rafael.desafioSpring.domain.dto.request.StatusCreateRequest;
import com.rafael.desafioSpring.domain.dto.response.CategoriaResponse;
import com.rafael.desafioSpring.domain.dto.response.StatusResponse;
import com.rafael.desafioSpring.domain.entities.CategoriaEvento;
import com.rafael.desafioSpring.domain.entities.StatusEvento;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatusMapper {

    private final ModelMapper mapper;

    @Autowired
    public StatusMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public StatusResponse toDto(StatusEvento input) {
        return mapper.map(input, StatusResponse.class);
    }

    public StatusEvento fromDto(StatusCreateRequest input) {
        
        StatusEvento model = mapper.map(input, StatusEvento.class);
        
        return model;
    }

}