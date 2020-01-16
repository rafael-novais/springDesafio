package com.rafael.desafioSpring.repository;

import com.rafael.desafioSpring.domain.entities.CategoriaEvento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaEventoRepository extends JpaRepository<CategoriaEvento, Integer> {

}