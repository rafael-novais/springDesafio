package com.rafael.desafioSpring.controller;

import java.util.List;

import javax.validation.Valid;

import com.rafael.desafioSpring.domain.dto.request.EventoCreateRequest;
import com.rafael.desafioSpring.domain.dto.request.ParticipacaoCreateRequest;
import com.rafael.desafioSpring.domain.dto.response.EventoResponse;
import com.rafael.desafioSpring.domain.dto.response.ParticipacaoResponse;
import com.rafael.desafioSpring.domain.entities.CategoriaEvento;
import com.rafael.desafioSpring.domain.entities.Evento;
import com.rafael.desafioSpring.domain.entities.Participacao;
import com.rafael.desafioSpring.domain.mapper.EventoMapper;
import com.rafael.desafioSpring.domain.mapper.ParticipacaoMapper;
import com.rafael.desafioSpring.service.CategoriaEventoService;
import com.rafael.desafioSpring.service.EventoService;
import com.rafael.desafioSpring.service.ParticipacaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/participacao")
public class ParticipacaoController {

	private final ParticipacaoService participacaoService;
	private final ParticipacaoMapper mapper;

	@Autowired
	public ParticipacaoController(ParticipacaoService participacaoService, ParticipacaoMapper participacaoMapper) {
        this.participacaoService = participacaoService;
		this.mapper = participacaoMapper;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ParticipacaoResponse> getById(@PathVariable final Integer id) {
         return ResponseEntity.ok(mapper.toDto(participacaoService.findById(id))) ;
    }
	
	@GetMapping
	public ResponseEntity<List<ParticipacaoResponse>> list() {
		return ResponseEntity.ok(participacaoService.listParticipacao().stream() //
				.map(x -> mapper.toDto(x)) //
				.collect(Collectors.toList()));
	}

	@PostMapping
	public ResponseEntity<ParticipacaoResponse> post(@Valid @RequestBody ParticipacaoCreateRequest model) {

		Participacao participacao = participacaoService.createParticipacao(mapper.fromDto(model));

		return ResponseEntity.ok(mapper.toDto(participacao));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Integer id) {

		participacaoService.deleteParticipacao(id);

		return ResponseEntity.ok(true);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ParticipacaoResponse> update(@Valid @RequestBody ParticipacaoCreateRequest model, @PathVariable Integer id) {

		return ResponseEntity.ok(mapper.toDto(participacaoService.updateParticipacao(id, mapper.fromDto(model))));

	}

}