package br.com.zup.desafio.mercadolivre.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.desafio.mercadolivre.dto.DetalheProdutoDTO;
import br.com.zup.desafio.mercadolivre.model.Produto;
import br.com.zup.desafio.mercadolivre.repository.ProdutoRepository;

@RestController
public class DetalheProdutoController {
	
	private final ProdutoRepository produtoRepository;
		
	public DetalheProdutoController(ProdutoRepository produtoRepository) {
		super();
		this.produtoRepository = produtoRepository;
	}

	@GetMapping(value = "/produto/{id}")
	public DetalheProdutoDTO detalhar(@PathVariable Long id) {
		Produto produto = produtoRepository.findById(id).orElseThrow();
		return new DetalheProdutoDTO(produto);
	}
}
