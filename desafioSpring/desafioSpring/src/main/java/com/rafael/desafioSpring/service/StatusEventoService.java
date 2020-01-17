package com.rafael.desafioSpring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rafael.desafioSpring.domain.entities.StatusEvento;
import com.rafael.desafioSpring.repository.*;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class StatusEventoService {

    private final StatusEventoRepository statusEventoRepository;

    @Autowired
    public StatusEventoService(StatusEventoRepository statusEventoRepository) {
        this.statusEventoRepository = statusEventoRepository;
    }

    public StatusEvento createEvento(StatusEvento model) {
        return statusEventoRepository.save(model);
    }

    public StatusEvento findById(Integer id) {
        Optional<StatusEvento> evento = statusEventoRepository.findById(id);
        return evento.get();
    }

    public List<StatusEvento> listStatus() {
        return statusEventoRepository.findAll();
    }

    // public void deleteEvento(Integer id) {
    //     eventoRepository.delete(findById(id));;
    //     listEvento();
    // }
}