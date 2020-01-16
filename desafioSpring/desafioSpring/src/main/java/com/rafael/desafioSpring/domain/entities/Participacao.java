package com.rafael.desafioSpring.domain.entities;

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
public class Participacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdParticipacao;

    @OneToOne
    @JoinColumn(name = "IdEvento", nullable = false)
    private Evento IdEvento;

    @Column(nullable = false)
    private String LoginParticipante;

    @Column(nullable = false)
    private Boolean FlagPresente;

    @Column(nullable = true)
    private Integer Nota;

    @Column(nullable = true)
    private String Comentario;
} 