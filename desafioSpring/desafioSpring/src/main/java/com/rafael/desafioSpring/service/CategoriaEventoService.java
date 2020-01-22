package com.rafael.desafioSpring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rafael.desafioSpring.domain.entities.CategoriaEvento;
import com.rafael.desafioSpring.repository.*;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CategoriaEventoService {

    private final CategoriaEventoRepository categoriaEventoRepository;

    @Autowired
    public CategoriaEventoService(final CategoriaEventoRepository categoriaEventoRepository) {
        this.categoriaEventoRepository = categoriaEventoRepository;
    }

    public List<CategoriaEvento> listCategoria() {
        return categoriaEventoRepository.findAll();
    }

    public CategoriaEvento createEvento(final CategoriaEvento model) {
        return categoriaEventoRepository.save(model);
    }

    public CategoriaEvento findById(final Integer id) {
        final Optional<CategoriaEvento> evento = categoriaEventoRepository.findById(id);
        return evento.get();
    }

}