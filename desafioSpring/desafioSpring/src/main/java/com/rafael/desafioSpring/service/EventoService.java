package com.rafael.desafioSpring.service;

import java.util.Optional;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rafael.desafioSpring.domain.entities.Evento;
import com.rafael.desafioSpring.exception.DataErradaException;
import com.rafael.desafioSpring.exception.DataNotFoundException;
import com.rafael.desafioSpring.repository.*;
import com.rafael.desafioSpring.validator.DataEventoValidator;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    @Autowired
    public EventoService(final EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public Evento createEvento(final Evento model) {

        if(DataEventoValidator.validarDataHora(model.getDataHoraInicio(), model.getDataHoraFim())){
            return eventoRepository.save(model);
        }

        throw new DataErradaException("Datas do evento foram inseridas de forma errada!");        
              
    }

    public List<Evento> listEvento() {
        return eventoRepository.findAll();
    }

    public List<Evento> buscaPorCategoria(final Integer idCategoria) {
        return eventoRepository.findEventoByCategoria(idCategoria);
    }

    public Evento findById(final Integer id) {
        final Optional<Evento> evento = eventoRepository.findById(id);
        
        return evento.orElseThrow(() -> new DataNotFoundException("Evento não encontrado!"));
    }

    public void deleteEvento(final Integer id) {
        eventoRepository.delete(findById(id));;
        listEvento();
    }

    public Evento updateEvento(final Integer id, final Evento model) {
        final Optional<Evento> evento = eventoRepository.findById(id);
        evento.orElseThrow(() -> new DataNotFoundException("Client Not found"));
        final Evento e = evento.get();
        
        e.setNome(model.getNome());
        e.setLocal(model.getLocal());
        e.setLimiteVagas(model.getLimiteVagas());
        e.setDescricao(model.getDescricao());
        
        //c.setPhone(model.getPhone());
        
        return eventoRepository.save(e);
    }

	public List<Evento> buscaPorData(final String data) {

        DataEventoValidator.validarFormatoData(data);

		return eventoRepository.findEventoByDate(data);
	}

	public Integer buscaPorVagasDisponiveis(final Integer idEvento) {
    
        final Optional op = eventoRepository.findById(idEvento);
        final Evento evento = (Evento) op.get();
        final Integer inscricoes = eventoRepository.findVagasDoEvento(idEvento);


        return evento.getLimiteVagas() - inscricoes;
        
	}
    
}