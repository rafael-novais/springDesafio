package com.rafael.desafioSpring.controller;

import java.util.List;

import com.rafael.desafioSpring.domain.dto.response.StatusResponse;
import com.rafael.desafioSpring.domain.mapper.StatusMapper;
import com.rafael.desafioSpring.service.StatusEventoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/status")
public class StatusController {

	private final StatusEventoService statusEventoService;
	private final StatusMapper mapper;

	@Autowired
	public StatusController(StatusEventoService statusEventoService, StatusMapper statusMapper) {
        this.statusEventoService = statusEventoService;
		this.mapper = statusMapper;
    }
	
	@GetMapping
	public ResponseEntity<List<StatusResponse>> list() {
		return ResponseEntity.ok(statusEventoService.listStatus().stream() //
				.map(x -> mapper.toDto(x)) //
				.collect(Collectors.toList()));
	}

}