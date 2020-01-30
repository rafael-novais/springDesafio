package com.rafael.desafioSpring.domain.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.rafael.desafioSpring.domain.entities.CategoriaEvento;
import com.rafael.desafioSpring.domain.entities.Evento;
import com.rafael.desafioSpring.domain.entities.StatusEvento;
import com.rafael.desafioSpring.repository.EventoRepository;
import com.rafael.desafioSpring.service.EventoService;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(MockitoJUnitRunner.class)
public class EventoServiceTest {

    @Mock
    EventoRepository repository;

    @InjectMocks
    private EventoService service;

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Autowired
    public Evento evento;

    public EventoServiceTest() {
        this.evento = new Evento();
        this.evento.setDataHoraInicio(new Date(new Date().getTime() + 86501101));
        this.evento.setDataHoraFim(new Date(new Date().getTime() + 86501102));
        this.evento.setLimiteVagas(5);
        this.evento.setIdEvento(1);
        this.evento.setIdEventoStatus(new StatusEvento());
        this.evento.setIdCategoriaEvento(new CategoriaEvento());
        this.evento.setLocal("Local");
        this.evento.setNome("nome");
        this.evento.setDescricao("Descricao");

    }

    @Test
    public void should_createEvento() {

        //given
        when(repository.save(evento)).thenReturn(evento);
      
        //when
        Evento eventoCriado = service.createEvento(evento);

        //then 
        verify(repository, times(1)).save(evento);
        assertEquals(eventoCriado, evento);

    }

    @Test
    public void should_listEvento(){

        List<Evento> list = new ArrayList<>();
        list.add(evento);

        // given
        when(repository.findAll()).thenReturn(list);

        // when
        List<Evento> model = service.listEvento();

        // then
        assertEquals(list, model);

    }

    @Test
    public void should_buscaPorCategoria(){

        List<Evento> list = new ArrayList<>();
        list.add(evento);

        // given
        when(repository.findEventoByCategoria(anyInt())).thenReturn(list);

        // when
        List<Evento> model = service.buscaPorCategoria(anyInt());

        // then
        assertEquals(list, model);

    }

    @Test
    public void should_findById(){

        // given
        when(repository.findById(anyInt())).thenReturn(Optional.of(evento));

        // when
        Evento model = service.findById(anyInt());

        // then
        assertEquals(evento, model);

    }

    @Test
    public void should_updateEvento(){

        when(repository.findById(anyInt())).thenReturn(Optional.of(evento)); 
        when(repository.save(evento)).thenReturn(evento);    
         
        assertEquals(evento, service.updateEvento(2, evento));

    }

    @Test
    public void should_validarDataHora(){

        Boolean testeComInicioEFimIguais = service.validarDataHora(new Date(), new Date());
        Boolean testeDataFimMenorQueInicio = service.validarDataHora(new Date(new Date().getTime() + 1), new Date());
        Boolean testeDiasDiferentes = service.validarDataHora(new Date(new Date().getTime() + 172800002), new Date(new Date().getTime() + 259200003));
        Boolean testeComMesesDiferentes = service.validarDataHora(new Date(new Date().getTime() + 172800002), new Date(new Date().getTime() + 2678400031L));
        Boolean testeComAnosDiferentes = service.validarDataHora(new Date(new Date().getTime() + 172800002), new Date(new Date().getTime() + 32140800372L));
        Boolean testeDiasCorretos = service.validarDataHora(new Date(new Date().getTime() + 172800002), new Date(new Date().getTime() + 172800003));
        Boolean teste = service.validarDataHora(new Date(new Date().getTime() + 86400001), new Date(new Date().getTime() + 86400010));

        assertEquals(testeComInicioEFimIguais, false);
        assertEquals(testeDataFimMenorQueInicio, false);
        assertEquals(testeDiasDiferentes, false);
        assertEquals(testeComMesesDiferentes, false);
        assertEquals(testeComAnosDiferentes, false);
        assertEquals(testeDiasCorretos, true);
        assertEquals(teste, true);

    }

    @Test
    public void should_buscarPorVagasDisponiveis(){

        when(repository.findById(anyInt())).thenReturn(Optional.of(evento)); 
        when(repository.findVagasDoEvento(anyInt())).thenReturn(2);
         
        assertEquals(service.buscaPorVagasDisponiveis(2), Integer.valueOf(3));

    }

    @Test
    public void should_validarDataCancelamento(){

        Boolean testeFalse = service.validarDataCancelamento(new Date());
        Boolean testeTrue = service.validarDataCancelamento(new Date(new Date().getTime() + 86501101));
         
        assertEquals(testeFalse, false);
        assertEquals(testeTrue, true);

    }

    @Test
    public void should_validarUsuariosInscritos(){

        when(repository.findById(anyInt())).thenReturn(Optional.of(evento)); 
        when(repository.findVagasDoEvento(anyInt())).thenReturn(2); 

        Boolean testeFalse = service.validarUsuariosInscritos(evento);

        evento.setLimiteVagas(2);

        Boolean testeTrue = service.validarUsuariosInscritos(evento);
         
        assertEquals(testeFalse, false);
        assertEquals(testeTrue, true);

    }


}