package com.rafael.desafioSpring.controller;

import java.util.List;

import javax.validation.Valid;

import com.rafael.desafioSpring.domain.dto.request.AvaliacaoCreateRequest;
import com.rafael.desafioSpring.domain.dto.request.FlagCreateRequest;
import com.rafael.desafioSpring.domain.dto.request.ParticipacaoCreateRequest;
import com.rafael.desafioSpring.domain.dto.response.InscritosResponse;
import com.rafael.desafioSpring.domain.dto.response.ParticipacaoResponse;
import com.rafael.desafioSpring.domain.entities.Evento;
import com.rafael.desafioSpring.domain.entities.Participacao;
import com.rafael.desafioSpring.domain.mapper.ParticipacaoMapper;
import com.rafael.desafioSpring.exception.SemPresencaException;
import com.rafael.desafioSpring.exception.SemVagasException;
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
	private final EventoService eventoService;
	private final ParticipacaoMapper mapper;

	@Autowired
	public ParticipacaoController(ParticipacaoService participacaoService, ParticipacaoMapper participacaoMapper, EventoService eventoService) {
        this.participacaoService = participacaoService;
		this.mapper = participacaoMapper;
		this.eventoService = eventoService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ParticipacaoResponse> getById(@PathVariable final Integer id) {
         return ResponseEntity.ok(mapper.toDto(participacaoService.findById(id))) ;
	}
	
	@GetMapping(value = "/buscaPorLogin/{login}")
	public ResponseEntity<List<ParticipacaoResponse>> buscaPorLogin(@PathVariable String login) {
		return ResponseEntity.ok(participacaoService.buscaPorLogin(login).stream() //
				.map(x -> mapper.toDto(x)) //
				.collect(Collectors.toList()));
	}

	@GetMapping(value = "/inscritosPorEvento/{idEvento}")
	public ResponseEntity<List<ParticipacaoResponse>> buscaInscritosNoEvento(@PathVariable Integer idEvento) {

		return ResponseEntity.ok(participacaoService.buscarInscritosNoEvento(idEvento).stream() //
				.map(x -> mapper.toDto(x)) //
				.collect(Collectors.toList()));

	}

	@GetMapping
	public ResponseEntity<List<ParticipacaoResponse>> list() {
		return ResponseEntity.ok(participacaoService.listParticipacao().stream() //
				.map(x -> mapper.toDto(x)) //
				.collect(Collectors.toList()));
	}

	@PostMapping(value = "/inscricao")
	public ResponseEntity<ParticipacaoResponse> inscricao(@Valid @RequestBody ParticipacaoCreateRequest model) {

		return ResponseEntity.ok(mapper.toDto(participacaoService.inscrever(mapper.fromDto(model))));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Integer id) {

		participacaoService.deleteParticipacao(id);

		return ResponseEntity.ok(true);
	}

	@PutMapping(value = "avaliacao/{idParticipacao}")
	public ResponseEntity<ParticipacaoResponse> avaliacao(@Valid @RequestBody AvaliacaoCreateRequest model, @PathVariable Integer idParticipacao) {

		if(!validarPresenca(idParticipacao)) throw new SemPresencaException("VOCÊ NÃO PODE AVALIAR UM EVENTO QUE NÃO ESTEVE PRESENTE!");

		return ResponseEntity.ok(mapper.toDto(participacaoService.avaliar(idParticipacao, mapper.fromDtoAvaliacao(model))));

	}

	@PutMapping(value = "presenca/{idParticipacao}")
	public ResponseEntity<Boolean> trocarFlag(@Valid @RequestBody FlagCreateRequest model, @PathVariable Integer idParticipacao) {

		participacaoService.salvar(model, idParticipacao);

		return ResponseEntity.ok(true);

	}

	public Boolean validarPresenca(Integer id){

		ParticipacaoResponse participacao = getById(id).getBody();

		if(participacao.getFlagPresente()) return true;

		return false;

	}

}