package com.rafael.desafioSpring.domain.mapper;

import com.rafael.desafioSpring.domain.dto.request.AvaliacaoCreateRequest;
import com.rafael.desafioSpring.domain.dto.request.ParticipacaoCreateRequest;
import com.rafael.desafioSpring.domain.dto.response.ParticipacaoResponse;
import com.rafael.desafioSpring.domain.entities.Evento;
import com.rafael.desafioSpring.domain.entities.Participacao;

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

    public Participacao fromDtoAvaliacao(AvaliacaoCreateRequest input) {
        
        Participacao model = mapper.map(input, Participacao.class);
        
        return model;
    }

}