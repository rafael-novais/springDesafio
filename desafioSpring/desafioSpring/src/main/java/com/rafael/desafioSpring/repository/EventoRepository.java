package com.rafael.desafioSpring.repository;

import com.rafael.desafioSpring.domain.entities.Evento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

}