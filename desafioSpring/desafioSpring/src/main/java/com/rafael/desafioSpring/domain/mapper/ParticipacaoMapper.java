package com.rafael.desafioSpring.domain.mapper;

import com.rafael.desafioSpring.domain.dto.request.EventoCreateRequest;
import com.rafael.desafioSpring.domain.dto.request.ParticipacaoCreateRequest;
import com.rafael.desafioSpring.domain.dto.response.EventoResponse;
import com.rafael.desafioSpring.domain.dto.response.ParticipacaoResponse;
import com.rafael.desafioSpring.domain.entities.CategoriaEvento;
import com.rafael.desafioSpring.domain.entities.Evento;
import com.rafael.desafioSpring.domain.entities.Participacao;
import com.rafael.desafioSpring.domain.entities.StatusEvento;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParticipacaoMapper {

    private final ModelMapper mapper;

    @Autowired
    public ParticipacaoMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public ParticipacaoResponse toDto(Participacao input) {
        return mapper.map(input, ParticipacaoResponse.class);
    }

    public Participacao fromDto(ParticipacaoCreateRequest input) {
        
        Participacao model = mapper.map(input, Participacao.class);
        
        Evento evento = new Evento();
        evento.setIdEvento(input.getIdEvento());
        
        model.setIdEvento(evento);
        //model.setIdEventoStatus(StatusEvento.builder().IdEventoStatus(input.getIdEventoStatus()).build());

        return model;
    }

}