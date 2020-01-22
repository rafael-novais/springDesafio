package com.rafael.desafioSpring.service;

import java.util.Optional;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rafael.desafioSpring.domain.entities.Participacao;
import com.rafael.desafioSpring.exception.DataNotFoundException;
import com.rafael.desafioSpring.repository.*;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ParticipacaoService {

    private final ParticipacaoRepository participacaoRepository;

    @Autowired
    public ParticipacaoService(ParticipacaoRepository participacaoRepository) {
        this.participacaoRepository = participacaoRepository;
    }

    public Participacao createParticipacao(Participacao model) {
        model.setFlagPresente(false);
        return participacaoRepository.save(model);
    }

    public List<Participacao> listParticipacao() {
        return participacaoRepository.findAll();
    }

    public Participacao findById(Integer id) {
        Optional<Participacao> participacao = participacaoRepository.findById(id);
        
        return participacao.orElseThrow(() -> new DataNotFoundException("Evento não encontrado!"));
    }

    public void deleteParticipacao(Integer id) {
        participacaoRepository.delete(findById(id));;
        listParticipacao();
    }

    public Participacao updateParticipacao(Integer id, Participacao model) {
        Optional<Participacao> participacao = participacaoRepository.findById(id);
        participacao.orElseThrow(() -> new DataNotFoundException("Client Not found"));
        Participacao p = participacao.get();
        
        p.setComentario(model.getComentario());
        p.setFlagPresente(model.getFlagPresente());
        p.setIdEvento(model.getIdEvento());
        p.setNota(model.getNota());
        p.setLoginParticipante(model.getLoginParticipante());
        
        return participacaoRepository.save(p);
    }

	public Participacao avaliar(Integer idParticipacao, Participacao model) {

        Participacao participacao = participacaoRepository.findParticipacaoByIdParticipacao(idParticipacao);

        participacao.setComentario(model.getComentario());
        participacao.setNota(model.getNota());

		return participacaoRepository.save(participacao);
    }
    
    public List<Participacao> buscaPorLogin(String login) {
        
        return participacaoRepository.listByLogin(login);
    }
    
}