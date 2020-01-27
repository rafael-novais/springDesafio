package com.rafael.desafioSpring.service;

import java.util.Optional;

import javax.validation.constraints.Pattern.Flag;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rafael.desafioSpring.domain.dto.request.FlagCreateRequest;
import com.rafael.desafioSpring.domain.entities.Evento;
import com.rafael.desafioSpring.domain.entities.Participacao;
import com.rafael.desafioSpring.exception.DataNotFoundException;
import com.rafael.desafioSpring.exception.DuplicidadeInscricaoException;
import com.rafael.desafioSpring.exception.EventoIndisponivelException;
import com.rafael.desafioSpring.exception.SemVagasException;
import com.rafael.desafioSpring.exception.StatusInvalidoException;
import com.rafael.desafioSpring.repository.*;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ParticipacaoService {

    private final ParticipacaoRepository participacaoRepository;
    private final EventoService eventoService;


    @Autowired
    public ParticipacaoService(final ParticipacaoRepository participacaoRepository, final EventoService eventoService) {
        this.participacaoRepository = participacaoRepository;
        this.eventoService = eventoService;
    }

    public Participacao createParticipacao(final Participacao model) {
        model.setFlagPresente(false);
        return participacaoRepository.save(model);
    }

    public Participacao salvar(final FlagCreateRequest model, final Integer id) {

        final Participacao participacao = findById(id);

        participacao.setFlagPresente(model.getFlag());

        return participacaoRepository.save(participacao);
    }

    public List<Participacao> listParticipacao() {
        return participacaoRepository.findAll();
    }

    public Participacao findById(final Integer id) {
        final Optional<Participacao> participacao = participacaoRepository.findById(id);

        return participacao.orElseThrow(() -> new DataNotFoundException("Participação não encontrada!"));
    }

    public void deleteParticipacao(final Integer id) {
        participacaoRepository.delete(findById(id));
        ;
        listParticipacao();
    }

    public Participacao updateParticipacao(final Integer id, final Participacao model) {
        final Optional<Participacao> participacao = participacaoRepository.findById(id);
        participacao.orElseThrow(() -> new DataNotFoundException("Client Not found"));
        final Participacao p = participacao.get();

        p.setComentario(model.getComentario());
        p.setFlagPresente(model.getFlagPresente());
        p.setIdEvento(model.getIdEvento());
        p.setNota(model.getNota());
        p.setLoginParticipante(model.getLoginParticipante());

        return participacaoRepository.save(p);
    }

    public Participacao avaliar(final Integer idParticipacao, final Participacao model) {

        final Participacao participacao = participacaoRepository.findParticipacaoByIdParticipacao(idParticipacao);

        participacao.setComentario(model.getComentario());
        participacao.setNota(model.getNota());

        return participacaoRepository.save(participacao);
    }

    public List<Participacao> buscaPorLogin(final String login) {

        return participacaoRepository.listByLogin(login);
    }

    ///////////////// NÃO INSCREVER CASO EVENTO ESTEJA CANCELADO OU CONCLUIDO
    ///////////////// NÃO INSCREVER CASO NÃO HAJA VAGAS
    ///////////////// NÃO INSCREVER MESMO LOGIN 2 VEZES
    public Participacao inscrever(final Participacao model) {

        if (!validarStatusEvento(model.getIdEvento().getIdEvento()))
            throw new EventoIndisponivelException("EVENTO NÃO ESTÁ ABERTO A NOVAS INSCRIÇÕES!");
        if (eventoService.buscaPorVagasDisponiveis(model.getIdEvento().getIdEvento()) <= 0)
            throw new SemVagasException("SEM VAGAS DISPONIVEIS!");
        if (validarDuplicidadeDeInscricao(model.getLoginParticipante(), model.getIdEvento().getIdEvento()))
            throw new DuplicidadeInscricaoException("VOCÊ NÃO PODE SE INSCREVER MAIS DE UMA VEZ NO MESMO EVENTO!");

        return createParticipacao(model);

    }

    public Boolean validarStatusEvento(final Integer idEvento) {

        final Evento evento = eventoService.findById(idEvento);

        final Integer status = evento.getIdEventoStatus().getIdEventoStatus();

        switch (status) {
        case 1:
            return true;
        case 2:
            return false;
        case 3:
            return false;
        case 4:
            return false;

        default:
            throw new StatusInvalidoException("STATUS INSERIDO NÃO É UM STATUS VÁLIDO!");
        }

    }

    public List<Participacao> buscarInscritosNoEvento(final Integer idEvento) {

        return participacaoRepository.listParticipacaoPorEvento(idEvento);

    }

    public Boolean validarDuplicidadeDeInscricao(final String login, final Integer idEvento) {

        final List<Participacao> lista = buscarInscritosNoEvento(idEvento);

        for(int i = 0; i < lista.size(); i++){
            if(lista.get(i).getLoginParticipante().toLowerCase().equals(login.toLowerCase())) return true;
        }

        return false;
    }
    
}