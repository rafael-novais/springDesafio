package com.rafael.desafioSpring.repository;

import com.rafael.desafioSpring.domain.entities.Participacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipacaoRepository extends JpaRepository<Participacao, Integer> {

}