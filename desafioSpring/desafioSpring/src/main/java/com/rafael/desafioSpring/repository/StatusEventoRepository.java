package com.rafael.desafioSpring.repository;

import com.rafael.desafioSpring.domain.entities.StatusEvento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusEventoRepository extends JpaRepository<StatusEvento, Integer> {

}