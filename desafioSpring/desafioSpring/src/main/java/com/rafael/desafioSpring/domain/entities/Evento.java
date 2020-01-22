package com.rafael.desafioSpring.domain.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdEvento;

    @OneToOne
    @JoinColumn(name = "IdEventoStatus", nullable = false)
    private StatusEvento IdEventoStatus;

    @OneToOne
    @JoinColumn(name = "IdCategoriaEvento", nullable = false)
    private CategoriaEvento IdCategoriaEvento;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(nullable = false)
    private Date DataHoraInicio;

    @Column(nullable = false)
    private Date DataHoraFim;

    @Column(nullable = false)
    private String Local;

    @Column(nullable = false)
    private String Descricao;

    @Column(nullable = false)
    private Integer LimiteVagas;

} 