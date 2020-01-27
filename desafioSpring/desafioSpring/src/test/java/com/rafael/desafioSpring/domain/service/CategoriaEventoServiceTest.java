package com.rafael.desafioSpring.domain.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.rafael.desafioSpring.domain.entities.CategoriaEvento;
import com.rafael.desafioSpring.repository.CategoriaEventoRepository;
import com.rafael.desafioSpring.service.CategoriaEventoService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(MockitoJUnitRunner.class)
public class CategoriaEventoServiceTest {

    @Mock
    CategoriaEventoRepository repository;

    @InjectMocks
    private CategoriaEventoService service;

    @Autowired
    public CategoriaEvento categoriaEvento;

    public CategoriaEventoServiceTest() {
        this.categoriaEvento = new CategoriaEvento();
    }

    @Test
    public void should_createEvento() {

        //given
        when(repository.save(categoriaEvento)).thenReturn(categoriaEvento);
      
        //when
        CategoriaEvento categoriaEventoCriado = service.createEvento(categoriaEvento);

        //then 
        assertEquals(categoriaEventoCriado, this.categoriaEvento);

    }

    @Test
    public void should_list() {

        List<CategoriaEvento> list = new ArrayList<>();
        list.add(this.categoriaEvento);

        //given
        when(repository.findAll()).thenReturn(list);
      
        //when
        List<CategoriaEvento> categoriaEventoCriado = service.listCategoria();

        //then 
        assertEquals(categoriaEventoCriado, list);

    }

    @Test
    public void should_findById() {

        //given
        when(repository.findById(anyInt())).thenReturn(Optional.of(this.categoriaEvento));
      
        //when
        CategoriaEvento categoriaEventoCriado = service.findById(2);

        //then 
        assertEquals(categoriaEventoCriado, this.categoriaEvento);

    }

}