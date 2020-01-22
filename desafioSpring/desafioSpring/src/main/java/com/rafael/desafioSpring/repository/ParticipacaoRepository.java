package com.rafael.desafioSpring.repository;

import java.util.List;

import com.rafael.desafioSpring.domain.entities.Participacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipacaoRepository extends JpaRepository<Participacao, Integer> {

    @Query(value = "SELECT * FROM Participacao WHERE Participacao.IdParticipacao = :idParticipacao", 
    nativeQuery = true)
    Participacao findParticipacaoByIdParticipacao(Integer idParticipacao);

    @Query(value = "SELECT * FROM Participacao WHERE Participacao.LoginParticipante = :login", 
    nativeQuery = true)
	List<Participacao> listByLogin(String login);

}