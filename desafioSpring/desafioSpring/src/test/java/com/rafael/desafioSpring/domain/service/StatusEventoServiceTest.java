package com.rafael.desafioSpring.domain.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.rafael.desafioSpring.domain.entities.CategoriaEvento;
import com.rafael.desafioSpring.domain.entities.StatusEvento;
import com.rafael.desafioSpring.repository.CategoriaEventoRepository;
import com.rafael.desafioSpring.repository.StatusEventoRepository;
import com.rafael.desafioSpring.service.CategoriaEventoService;
import com.rafael.desafioSpring.service.StatusEventoService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(MockitoJUnitRunner.class)
public class StatusEventoServiceTest {

    @Mock
    StatusEventoRepository repository;

    @InjectMocks
    private StatusEventoService service;

    @Autowired
    public StatusEvento statusEvento;

    public StatusEventoServiceTest() {
        this.statusEvento = new StatusEvento();
    }

    @Test
    public void should_createStatusEvento() {

        //given
        when(repository.save(statusEvento)).thenReturn(statusEvento);
      
        //when
        StatusEvento statusEventoCriado = service.createEvento(statusEvento);

        //then 
        assertEquals(statusEventoCriado, this.statusEvento);

    }

    @Test
    public void should_list() {

        List<StatusEvento> list = new ArrayList<>();
        list.add(this.statusEvento);

        //given
        when(repository.findAll()).thenReturn(list);
      
        //when
        List<StatusEvento> statusEventoCriado = service.listStatus();

        //then 
        assertEquals(statusEventoCriado, list);

    }

    @Test
    public void should_findById() {

        //given
        when(repository.findById(anyInt())).thenReturn(Optional.of(this.statusEvento));
      
        //when
        StatusEvento categoriaEventoCriado = service.findById(2);

        //then 
        assertEquals(categoriaEventoCriado, this.statusEvento);

    }

}