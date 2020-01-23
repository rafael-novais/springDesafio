package com.rafael.desafioSpring.service;

import java.util.Optional;
import java.util.Calendar;
import java.util.Date;
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

    public Evento createEvento(Evento model) {

        if(validarDataHora(model.getDataHoraInicio(), model.getDataHoraFim())){
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
    
        final Evento evento = findById(idEvento);
        final Integer inscricoes = eventoRepository.findVagasDoEvento(idEvento);


        return evento.getLimiteVagas() - inscricoes;
        
    }
    
    public Boolean validarDataHora(Date horaInicio, Date horaFim){

        Long timeInicio = horaInicio.getTime();
        Long timeFim = horaFim.getTime();

        if(timeFim - timeInicio <= 0) return false;
        if(timeInicio < new Date().getTime() + 86400000) return false;

        Calendar inicio = Calendar.getInstance();
        Calendar fim = Calendar.getInstance();

        inicio.setTime(horaInicio);
        fim.setTime(horaFim);

        if(inicio.get(Calendar.DAY_OF_MONTH) != fim.get(Calendar.DAY_OF_MONTH)) return false;
        if(inicio.get(Calendar.MONTH) != fim.get(Calendar.MONTH)) return false;
        if(inicio.get(Calendar.YEAR) != fim.get(Calendar.YEAR)) return false;

        return true;

    }  

}