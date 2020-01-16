package com.rafael.desafioSpring.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rafael.desafioSpring.domain.entities.CategoriaEvento;
import com.rafael.desafioSpring.repository.*;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CategoriaEventoService {

    private final CategoriaEventoRepository categoriaEventoRepository;

    @Autowired
    public CategoriaEventoService(CategoriaEventoRepository categoriaEventoRepository) {
        this.categoriaEventoRepository = categoriaEventoRepository;
    }

    public CategoriaEvento createEvento(CategoriaEvento model) {
        return categoriaEventoRepository.save(model);
    }

    public CategoriaEvento findById(Integer id) {
        Optional<CategoriaEvento> evento = categoriaEventoRepository.findById(id);
        return evento.get();
    }

    // public void deleteEvento(Integer id) {
    //     eventoRepository.delete(findById(id));;
    //     listEvento();
    // }
}