package br.com.zup.desafio.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.desafio.mercadolivre.dto.CategoriaDTO;
import br.com.zup.desafio.mercadolivre.model.Categoria;
import br.com.zup.desafio.mercadolivre.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	private final CategoriaRepository categoriaRepository;

	public CategoriaController(CategoriaRepository categoriaRepository) {
		super();
		this.categoriaRepository = categoriaRepository;
	}
	
	@PostMapping
	public ResponseEntity<Categoria> inserir(@RequestBody @Valid CategoriaDTO categoriaDto){
		Categoria categoria = categoriaDto.converter(categoriaRepository);
		categoriaRepository.save(categoria);
		return ResponseEntity.ok().build();
	}
}
