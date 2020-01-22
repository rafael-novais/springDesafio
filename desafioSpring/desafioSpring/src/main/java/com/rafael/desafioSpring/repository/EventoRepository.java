package com.rafael.desafioSpring.repository;

import java.util.List;

import com.rafael.desafioSpring.domain.entities.Evento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

        @Query(value = "SELECT * FROM Evento WHERE Evento.IdCategoriaEvento = :idCategoria", 
        nativeQuery = true)
        List<Evento> findEventoByCategoria(Integer idCategoria);

        @Query(value = "SELECT * FROM Evento WHERE Evento.DataHoraInicio = :data", 
        nativeQuery = true)
        List<Evento> findEventoByDate(String data);

        @Query(value = "SELECT COUNT(IdParticipacao) FROM Participacao INNER JOIN Evento ON Participacao.IdEvento = Evento.IdEvento WHERE Evento.IdEvento = :idEvento", 
        nativeQuery = true)
        Integer findVagasDoEvento(Integer idEvento);

}