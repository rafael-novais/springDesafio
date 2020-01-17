package com.rafael.desafioSpring.controller;

import java.util.List;

import com.rafael.desafioSpring.domain.dto.response.CategoriaResponse;
import com.rafael.desafioSpring.domain.mapper.CategoriaMapper;
import com.rafael.desafioSpring.service.CategoriaEventoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	private final CategoriaEventoService categoriaEventoService;
	private final CategoriaMapper mapper;

	@Autowired
	public CategoriaController(CategoriaEventoService categoriaEventoService, CategoriaMapper categoriaMapper) {
        this.categoriaEventoService = categoriaEventoService;
		this.mapper = categoriaMapper;
    }
	
	@GetMapping
	public ResponseEntity<List<CategoriaResponse>> list() {
		return ResponseEntity.ok(categoriaEventoService.listCategoria().stream() //
				.map(x -> mapper.toDto(x)) //
				.collect(Collectors.toList()));
	}

}