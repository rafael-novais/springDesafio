package com.rafael.desafioSpring.controller;

import java.util.List;

import javax.validation.Valid;

import com.rafael.desafioSpring.domain.dto.request.EventoCreateRequest;
import com.rafael.desafioSpring.domain.dto.request.StatusChangeRequest;
import com.rafael.desafioSpring.domain.dto.response.EventoResponse;
import com.rafael.desafioSpring.domain.dto.response.StatusEventoResponse;
import com.rafael.desafioSpring.domain.dto.response.VagasDisponiveisResponse;
import com.rafael.desafioSpring.domain.entities.Evento;
import com.rafael.desafioSpring.domain.entities.StatusEvento;
import com.rafael.desafioSpring.domain.mapper.EventoMapper;
import com.rafael.desafioSpring.service.CategoriaEventoService;
import com.rafael.desafioSpring.service.EventoService;
import com.rafael.desafioSpring.service.StatusEventoService;

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
@RequestMapping("/eventos")
public class EventoController {

	private final EventoService eventoService;
	private final EventoMapper mapper;
	private final CategoriaEventoService categoriaService;
	

	@Autowired
	public EventoController(EventoService eventoService, EventoMapper eventoMapper, CategoriaEventoService categoriaService) {
        this.eventoService = eventoService;
		this.mapper = eventoMapper;
		this.categoriaService = categoriaService;
		
    }

	@GetMapping(value = "/vagasDisponiveis/{idEvento}")
	public ResponseEntity<VagasDisponiveisResponse> buscaPorVagasDisponiveis(@PathVariable Integer idEvento) {

		Integer vagasDisponiveis = eventoService.buscaPorVagasDisponiveis(idEvento);
		Evento evento = eventoService.findById(idEvento);
		VagasDisponiveisResponse vdr = new VagasDisponiveisResponse();

		vdr.setNomeEvento(evento.getNome());
		vdr.setLimiteVagas(evento.getLimiteVagas());
		vdr.setIdEvento(idEvento);
		vdr.setVagasDisponiveis(vagasDisponiveis);

		return ResponseEntity.ok(vdr);
	}

	@GetMapping(value = "/categoria/{idCategoria}")
	public ResponseEntity<List<EventoResponse>> listPorCategoria(@PathVariable Integer idCategoria) {
		return ResponseEntity.ok(eventoService.buscaPorCategoria(idCategoria).stream() //
				.map(x -> mapper.toDto(x)) //
				.collect(Collectors.toList()));
	}

	@GetMapping(value = "/status/{idEvento}")
	public ResponseEntity<StatusEventoResponse> statusDoEvento(@PathVariable Integer idEvento) {
		return ResponseEntity.ok(mapper.toDtoStatus(eventoService.findById(idEvento))); 
	}

	@PutMapping(value = "mudarStatus/{idEvento}")
	public ResponseEntity<EventoResponse> atualizarStatusEvento(@Valid @RequestBody StatusChangeRequest model, @PathVariable Integer idEvento) {

		Evento evento = eventoService.updateStatusEvento(model, idEvento);

		return ResponseEntity.ok(mapper.toDto(evento));

	}

	@GetMapping(value = "/data/{data}")
	public ResponseEntity<List<EventoResponse>> listPorData(@PathVariable String data) {
		return ResponseEntity.ok(eventoService.buscaPorData(data).stream() //
				.map(x -> mapper.toDto(x)) //
				.collect(Collectors.toList()));
	}

    @GetMapping(value = "/{id}")
    public ResponseEntity<EventoResponse> getById(@PathVariable final Integer id) {
         return ResponseEntity.ok(mapper.toDto(eventoService.findById(id))) ;
    }
	
	@GetMapping
	public ResponseEntity<List<EventoResponse>> list() {
		return ResponseEntity.ok(eventoService.listEvento().stream() //
				.map(x -> mapper.toDto(x)) //
				.collect(Collectors.toList()));
	}

	@PostMapping
	public ResponseEntity<EventoResponse> post(@Valid @RequestBody EventoCreateRequest model) {

		Evento evento = eventoService.createEvento(mapper.fromDto(model));

		return ResponseEntity.ok(mapper.toDto(evento));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Integer id) {

		eventoService.deleteEvento(id);

		return ResponseEntity.ok(true);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<EventoResponse> update(@Valid @RequestBody EventoCreateRequest model, @PathVariable Integer id) {

		return ResponseEntity.ok(mapper.toDto(eventoService.updateEvento(id, mapper.fromDto(model))));

	}

}