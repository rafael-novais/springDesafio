package com.rafael.desafioSpring.domain.dto.response;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import com.rafael.desafioSpring.domain.entities.CategoriaEvento;
import com.rafael.desafioSpring.domain.entities.StatusEvento;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(MockitoJUnitRunner.class)
public class EventoResponseTest {

    @InjectMocks
    private EventoResponse evento;

    @Autowired
    public EventoResponseTest() {
        //evento.setNome("rafael");
    }

    @Test
    public void should_getSetNome() {
        evento.setNome("rafael");
        String nome = this.evento.getNome();
        //then 
        assertEquals(nome, "rafael");
    }

    @Test
    public void should_getSetDatas() {
        Date data = new Date();
        evento.setDataHoraInicio(data);
        evento.setDataHoraFim(data);
        Date dataInicio = this.evento.getDataHoraInicio();
        Date dataFim= this.evento.getDataHoraFim();
        //then 
        assertEquals(dataInicio, data);
        assertEquals(dataFim, data);
    }

    @Test
    public void should_getSetLocal() {
        evento.setLocal("xablau");

        String local = evento.getLocal();

        //then 
        assertEquals(local, "xablau");
    }

    @Test
    public void should_getSetDescricao() {
        evento.setDescricao("xabalu");

        String desc = evento.getDescricao();

        //then 
        assertEquals(desc, "xabalu");
    }

    @Test
    public void should_getSetVagas() {

        evento.setLimiteVagas(2);

        int vagas = evento.getLimiteVagas();

        //then 
        assertEquals(vagas, 2);
    }

    @Test
    public void should_getSetStatus() {

        StatusEvento se = new StatusEvento();

        evento.setIdEventoStatus(se);

        StatusEvento teste = evento.getIdEventoStatus();

        //then 
        assertEquals(teste, se);
    }

    @Test
    public void should_getSetCategoria() {

        CategoriaEvento ce = new CategoriaEvento();

        evento.setIdCategoriaEvento(ce);

        CategoriaEvento teste = evento.getIdCategoriaEvento();

        //then 
        assertEquals(teste, ce);
    }

}