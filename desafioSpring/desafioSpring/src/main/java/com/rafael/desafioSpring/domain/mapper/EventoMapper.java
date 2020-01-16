package com.rafael.desafioSpring.domain.mapper;

import com.rafael.desafioSpring.domain.dto.request.EventoCreateRequest;
import com.rafael.desafioSpring.domain.dto.response.EventoResponse;
import com.rafael.desafioSpring.domain.entities.CategoriaEvento;
import com.rafael.desafioSpring.domain.entities.Evento;
import com.rafael.desafioSpring.domain.entities.StatusEvento;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventoMapper {

    private final ModelMapper mapper;

    @Autowired
    public EventoMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public EventoResponse toDto(Evento input) {
        return mapper.map(input, EventoResponse.class);
    }

    public Evento fromDto(EventoCreateRequest input) {
        Evento model = mapper.map(input, Evento.class);
        CategoriaEvento categoriaEvento = new CategoriaEvento();
        categoriaEvento.setIdCategoriaEvento(input.getIdCategoriaEvento());

        model.setIdCategoriaEvento(categoriaEvento);

        model.setIdEventoStatus(StatusEvento.builder().IdEventoStatus(input.getIdEventoStatus()).build());

        return model;
    }

}