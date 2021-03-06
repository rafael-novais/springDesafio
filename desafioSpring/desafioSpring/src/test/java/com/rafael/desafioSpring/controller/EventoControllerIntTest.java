package com.rafael.desafioSpring.controller;

import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafael.desafioSpring.domain.dto.request.EventoCreateRequest;
import com.rafael.desafioSpring.domain.dto.request.StatusChangeRequest;
import com.rafael.desafioSpring.domain.entities.CategoriaEvento;
import com.rafael.desafioSpring.domain.entities.Evento;
import com.rafael.desafioSpring.domain.entities.StatusEvento;
import com.rafael.desafioSpring.repository.CategoriaEventoRepository;
import com.rafael.desafioSpring.repository.EventoRepository;
import com.rafael.desafioSpring.repository.StatusEventoRepository;
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
public class EventoControllerIntTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private EventoRepository repository;

    @Autowired
    private StatusEventoRepository statusRepository;

    @Autowired
    private CategoriaEventoRepository categoriaRepository;


    StatusEvento status;
    CategoriaEvento ce; 
    Date data; 
    Evento evento;
    EventoCreateRequest ecr;
    StatusChangeRequest scr;

    @Before public void initialize() {
        status = new StatusEvento();
        status.setIdEventoStatus(1);
        status.setNomeStatus("status");

        ce = new CategoriaEvento();
        ce.setIdCategoriaEvento(1);
        ce.setNomeCategoria("NomeCategoria");
        
        data = new Date(new Date().getTime() + 87500100);

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

        ecr = new EventoCreateRequest();
        ecr.setNome("nome");
        ecr.setLocal("local");
        ecr.setIdCategoriaEvento(1);
        ecr.setDataHoraInicio(data);
        ecr.setDataHoraFim(new Date(data.getTime() + 3000));
        ecr.setLimiteVagas(5);
        ecr.setDescricao("descricao");

        scr = new StatusChangeRequest();
        scr.setIdEventoStatus(1);
    }

    @Test   //testa caminho /eventos;
    public void should_returnList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/eventos")) // Executa
        .andDo(MockMvcResultHandlers.print()) //pega resultado
        .andExpect(MockMvcResultMatchers.status().isOk()) //faz a validação.
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
        //.andExpect(MockMvcResultMatchers.jsonPath("$[0]").value("Hello"))
        //.andExpect(MockMvcResultMatchers.jsonPath("$[1]").value("World"));
    }

    @Test   //testa caminho /status/{id};
    public void should_statusDoEvento() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/eventos/status/" + evento.getIdEvento())) // Executa
        .andDo(MockMvcResultHandlers.print()) //pega resultado
        .andExpect(MockMvcResultMatchers.status().isOk()) //faz a validação.
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.anything()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.nomeEvento").value("nome"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.idEvento").value(1));

    }

    @Test
    public void should_buscarPorVagasDisponiveis() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/eventos/vagasDisponiveis/" + evento.getIdEvento())) // Executa
        .andDo(MockMvcResultHandlers.print()) //pega resultado
        .andExpect(MockMvcResultMatchers.status().isOk()) //faz a validação.
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.anything()));
        //.andExpect(MockMvcResultMatchers.jsonPath("$[0]").value("Hello"))
        //.andExpect(MockMvcResultMatchers.jsonPath("$[1]").value("World"));
    }

    @Test   //testa caminho /status/{id};
    public void should_post() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/eventos") //
                .contentType(MediaType.APPLICATION_JSON) //
                .content(mapper.writeValueAsString(ecr))) // Executa
                .andDo(MockMvcResultHandlers.print()) // pega resultado
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("nome"));

    }

    @Test
    public void should_listPorCategoria() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/eventos/categoria/1")) // Executa
        .andDo(MockMvcResultHandlers.print()) //pega resultado
        .andExpect(MockMvcResultMatchers.status().isOk()) //faz a validação.
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("nome"));
        //.andExpect(MockMvcResultMatchers.jsonPath("$[1]").value("World"));
    }

    // @Test
    // public void should_update() throws Exception {

    //     mockMvc.perform(MockMvcRequestBuilders.post("/eventos/1") //
    //             .contentType(MediaType.APPLICATION_JSON) //
    //             .content(mapper.writeValueAsString(ecr))) // Executa
    //             .andDo(MockMvcResultHandlers.print()) // pega resultado
    //             .andExpect(MockMvcResultMatchers.status().isOk())
    //             .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("nome"));
    // }

    @Test
    public void should_getById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/eventos/1")) // Executa
        .andDo(MockMvcResultHandlers.print()) //pega resultado
        .andExpect(MockMvcResultMatchers.status().isOk()) //faz a validação.
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.anything()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("nome"));
        //.andExpect(MockMvcResultMatchers.jsonPath("$[1]").value("World"));
    }

    @Test   //testa caminho /status/{id};
    public void should_atualizarStatusEvento() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/eventos/mudarStatus/1") //
                .contentType(MediaType.APPLICATION_JSON) //
                .content(mapper.writeValueAsString(scr))) // Executa
                .andDo(MockMvcResultHandlers.print()) // pega resultado
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("nome"));

    }

} 