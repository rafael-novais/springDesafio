package com.rafael.desafioSpring.service;

import java.util.Optional;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rafael.desafioSpring.domain.entities.Evento;
import com.rafael.desafioSpring.repository.*;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    @Autowired
    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public Evento createEvento(Evento model) {
        return eventoRepository.save(model);
    }

    public List<Evento> listEvento() {
        return eventoRepository.findAll();
    }

    public Evento findById(Integer id) {
        Optional<Evento> evento = eventoRepository.findById(id);
        return evento.get();
    }

    public void deleteEvento(Integer id) {
        eventoRepository.delete(findById(id));;
        listEvento();
    }

    public Evento updateEvento(Integer id, Evento model) {
        Optional<Evento> client = eventoRepository.findById(id);
        //client.orElseThrow(() -> new DataNotFoundException("Client Not found"));
        Evento c = client.get();
        
        //c.setName(model.getName());
        //c.setPhone(model.getPhone());
        
        return eventoRepository.save(c);
    }
    
}