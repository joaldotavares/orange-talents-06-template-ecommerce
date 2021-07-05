package br.com.zup.desafio.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.desafio.mercadolivre.dto.ProdutoDTO;
import br.com.zup.desafio.mercadolivre.model.Produto;
import br.com.zup.desafio.mercadolivre.model.Usuario;
import br.com.zup.desafio.mercadolivre.repository.CategoriaRepository;
import br.com.zup.desafio.mercadolivre.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	private final ProdutoRepository produtoRepository;
	
	private final CategoriaRepository categorriaRepository;
	
	public ProdutoController(ProdutoRepository produtoRepository, CategoriaRepository categorriaRepository) {
		super();
		this.produtoRepository = produtoRepository;
		this.categorriaRepository = categorriaRepository;
	}

	@PostMapping
	public ResponseEntity<Usuario> inserir(@RequestBody @Valid ProdutoDTO produtoDto) {
		Produto produto = produtoDto.toModel(categorriaRepository);
		produtoRepository.save(produto);
		
		return ResponseEntity.ok().build();
	}
	
}
