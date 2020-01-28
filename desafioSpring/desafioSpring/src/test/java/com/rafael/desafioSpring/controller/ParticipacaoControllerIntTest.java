package com.rafael.desafioSpring.controller;

import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafael.desafioSpring.domain.dto.request.AvaliacaoCreateRequest;
import com.rafael.desafioSpring.domain.dto.request.FlagCreateRequest;
import com.rafael.desafioSpring.domain.dto.request.ParticipacaoCreateRequest;
import com.rafael.desafioSpring.domain.dto.response.StatusEventoResponse;
import com.rafael.desafioSpring.domain.entities.CategoriaEvento;
import com.rafael.desafioSpring.domain.entities.Evento;
import com.rafael.desafioSpring.domain.entities.Participacao;
import com.rafael.desafioSpring.domain.entities.StatusEvento;
import com.rafael.desafioSpring.repository.CategoriaEventoRepository;
import com.rafael.desafioSpring.repository.EventoRepository;
import com.rafael.desafioSpring.repository.ParticipacaoRepository;
import com.rafael.desafioSpring.repository.StatusEventoRepository;
import com.rafael.desafioSpring.service.EventoService;
import com.rafael.desafioSpring.service.ParticipacaoService;
import com.rafael.desafioSpring.utils.IntegrationTestConfig;

import org.hamcrest.Matchers;
import org.junit.Before;
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
public class ParticipacaoControllerIntTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private ParticipacaoService participacaoService;

    @Autowired
    private EventoRepository repository;

    @Autowired
    private ParticipacaoRepository participacaoRepository;

    @Autowired
    private StatusEventoRepository statusRepository;

    @Autowired
    private CategoriaEventoRepository categoriaRepository;

    StatusEvento status;
    CategoriaEvento ce;
    Date data; 
    Evento evento;
    Participacao participacao;
    FlagCreateRequest fcr;
    AvaliacaoCreateRequest acr;

    @Before public void initialize() {
       status = new StatusEvento();
       status.setIdEventoStatus(1);
       status.setNomeStatus("status");

       ce = new CategoriaEvento();
       ce.setIdCategoriaEvento(1);
       ce.setNomeCategoria("NomeCategoria");

       data = new Date(new Date().getTime() + 86400100);

       evento = new Evento();
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

       participacao = new Participacao();
       participacao.setComentario("Comentario");
       participacao.setIdParticipacao(1);
       participacao.setLoginParticipante("loginParticipante");
       participacao.setIdEvento(evento);
       participacao.setFlagPresente(true);
       participacaoRepository.save(participacao);

       fcr = new FlagCreateRequest();
       fcr.setFlag(true);

       acr = new AvaliacaoCreateRequest();
       acr.setComentario("Comentario");
       acr.setNota(7);
    }

    @Test   //testa caminho /eventos;
    public void should_returnList() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/participacao")) // Executa
        .andDo(MockMvcResultHandlers.print()) //pega resultado
        .andExpect(MockMvcResultMatchers.status().isOk()) //faz a validação.
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].loginParticipante").value("loginParticipante"));
        //.andExpect(MockMvcResultMatchers.jsonPath("$[1]").value("World"));
    }

    @Test   //testa caminho /status/{id};
    public void should_avaliacao() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/participacao/avaliacao/" + participacao.getIdParticipacao()) //
                .contentType(MediaType.APPLICATION_JSON) //
                .content(mapper.writeValueAsString(acr))) // Executa
                .andDo(MockMvcResultHandlers.print()) // pega resultado
                .andExpect(MockMvcResultMatchers.status().isOk()); 

    }

    @Test   //testa caminho /status/{id};
    public void should_buscaPorLogin() throws Exception {
    
        mockMvc.perform(MockMvcRequestBuilders.get("/participacao/buscaPorLogin/loginParticipante")) // Executa
        .andDo(MockMvcResultHandlers.print()) //pega resultado
        .andExpect(MockMvcResultMatchers.status().isOk()) //faz a validação.
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.anything()))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].loginParticipante").value("loginParticipante"));
        //.andExpect(MockMvcResultMatchers.jsonPath("$[1]").value("World"));

    }

    @Test   //testa caminho /status/{id};
    public void should_buscaInscritosNoEvento() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/participacao/inscritosPorEvento/1")) // Executa
        .andDo(MockMvcResultHandlers.print()) //pega resultado
        .andExpect(MockMvcResultMatchers.status().isOk()) //faz a validação.
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].loginParticipante").value("loginParticipante"));
        //.andExpect(MockMvcResultMatchers.jsonPath("$[1]").value("World"));

    }

    @Test   //testa caminho /status/{id};
    public void should_inscricao() throws Exception {

        ParticipacaoCreateRequest pcr = new ParticipacaoCreateRequest();
        pcr.setIdEvento(1);
        pcr.setLoginParticipante("diego");

        mockMvc.perform(MockMvcRequestBuilders.post("/participacao/inscricao") //
                .contentType(MediaType.APPLICATION_JSON) //
                .content(mapper.writeValueAsString(pcr))) // Executa
                .andDo(MockMvcResultHandlers.print()) // pega resultado
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.loginParticipante").value("diego"));

    }
    
    @Test   //testa caminho /status/{id};
    public void should_trocarFlag() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/participacao/presenca/1") //
                .contentType(MediaType.APPLICATION_JSON) //
                .content(mapper.writeValueAsString(fcr))) // Executa
                .andDo(MockMvcResultHandlers.print()) // pega resultado
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("true"));

    }

    @Test   //testa caminho /status/{id};
    public void should_getById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/participacao/1")) // Executa
        .andDo(MockMvcResultHandlers.print()) //pega resultado
        .andExpect(MockMvcResultMatchers.status().isOk()) //faz a validação.
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.anything()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.loginParticipante").value("loginParticipante"));
        //.andExpect(MockMvcResultMatchers.jsonPath("$[1]").value("World"));

    }

    // @Test   //testa caminho /status/{id};
    // public void should_delete() throws Exception {

    //     mockMvc.perform(MockMvcRequestBuilders.delete("/participacao/1")) // Executa
    //     .andDo(MockMvcResultHandlers.print()) //pega resultado
    //     .andExpect(MockMvcResultMatchers.status().isOk()) //faz a validação.
    //     .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
    //     .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.anything()))
    //     .andExpect(MockMvcResultMatchers.jsonPath("$").value("true"));
    //     //.andExpect(MockMvcResultMatchers.jsonPath("$[1]").value("World"));

    //     initialize();

    // }

}    