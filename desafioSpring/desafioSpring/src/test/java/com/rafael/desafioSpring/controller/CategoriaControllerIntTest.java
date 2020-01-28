package com.rafael.desafioSpring.controller;

import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafael.desafioSpring.domain.dto.request.AvaliacaoCreateRequest;
import com.rafael.desafioSpring.domain.dto.response.StatusEventoResponse;
import com.rafael.desafioSpring.domain.entities.CategoriaEvento;
import com.rafael.desafioSpring.domain.entities.Evento;
import com.rafael.desafioSpring.domain.entities.Participacao;
import com.rafael.desafioSpring.domain.entities.StatusEvento;
import com.rafael.desafioSpring.repository.CategoriaEventoRepository;
import com.rafael.desafioSpring.repository.EventoRepository;
import com.rafael.desafioSpring.repository.ParticipacaoRepository;
import com.rafael.desafioSpring.repository.StatusEventoRepository;
import com.rafael.desafioSpring.service.CategoriaEventoService;
import com.rafael.desafioSpring.service.EventoService;
import com.rafael.desafioSpring.service.ParticipacaoService;
import com.rafael.desafioSpring.utils.IntegrationTestConfig;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * HelloWorldControllerIntTest
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(locations = IntegrationTestConfig.appProperties)
@ActiveProfiles("test")
public class CategoriaControllerIntTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EventoRepository repository;

    @Autowired
    private ParticipacaoRepository participacaoRepository;

    @Autowired
    private StatusEventoRepository statusRepository;

    @Autowired
    private CategoriaEventoRepository categoriaRepository;

    @Test   //testa caminho /eventos;
    public void should_returnList() throws Exception {

        StatusEvento status = new StatusEvento();
        status.setIdEventoStatus(1);
        status.setNomeStatus("status");

        CategoriaEvento ce = new CategoriaEvento();
        ce.setIdCategoriaEvento(1);
        ce.setNomeCategoria("NomeCategoria");
        
        Date data = new Date(new Date().getTime() + 86400100);

        Evento evento = new Evento();
        evento.setIdEventoStatus(status);
        evento.setNome("nome");
        evento.setDataHoraInicio(data);
        evento.setDataHoraFim(data);
        evento.setDescricao("Descricao");
        evento.setIdCategoriaEvento(ce);
        evento.setLimiteVagas(10);
        evento.setLocal("Local");
        
        evento.setIdEvento(1);

        categoriaRepository.saveAndFlush(ce);
        statusRepository.saveAndFlush(status);
        repository.saveAndFlush(evento);

        Participacao participacao = new Participacao();
        participacao.setComentario("Comentario");
        participacao.setIdParticipacao(1);
        participacao.setLoginParticipante("loginParticipante");
        participacao.setIdEvento(evento);
        participacaoRepository.save(participacao);

        mockMvc.perform(MockMvcRequestBuilders.get("/categoria")) // Executa
        .andDo(MockMvcResultHandlers.print()) //pega resultado
        .andExpect(MockMvcResultMatchers.status().isOk()) //faz a validação.
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
        //.andExpect(MockMvcResultMatchers.jsonPath("$[0].NomeCategoria").value("NomeCategoria"));
        //.andExpect(MockMvcResultMatchers.jsonPath("$[1]").value("World"));
    }
}    