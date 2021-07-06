package br.com.zup.desafio.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.desafio.mercadolivre.config.validation.ResourceException;
import br.com.zup.desafio.mercadolivre.dto.OpiniaoDTO;
import br.com.zup.desafio.mercadolivre.model.Opiniao;
import br.com.zup.desafio.mercadolivre.model.Produto;
import br.com.zup.desafio.mercadolivre.model.Usuario;
import br.com.zup.desafio.mercadolivre.repository.OpiniaoRepository;
import br.com.zup.desafio.mercadolivre.repository.ProdutoRepository;
import br.com.zup.desafio.mercadolivre.repository.UsuarioRepository;

@RestController
public class OpiniaoController {
	
	private final ProdutoRepository produtoRepository;
	
	private final UsuarioRepository usuarioRepository;
	
	private final OpiniaoRepository opiniaoRepository;
	
	public OpiniaoController(ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository,
			OpiniaoRepository opiniaoRepository) {
		super();
		this.produtoRepository = produtoRepository;
		this.usuarioRepository = usuarioRepository;
		this.opiniaoRepository = opiniaoRepository;
	}

	@PostMapping(value = "/produto/{id}/opiniao")
	public ResponseEntity<?> inserir(@PathVariable Long id, @RequestBody @Valid OpiniaoDTO opiniaoDto) {
		Produto produto = produtoRepository.findById(id).orElseThrow(() -> new ResourceException("Id de produto não encontrado"));
		
		Usuario usuario = usuarioRepository.findByLogin("jota@email.com").get();
		
		Opiniao opiniao = opiniaoDto.toModel(produto, usuario);
		
		opiniaoRepository.save(opiniao);
		
		return ResponseEntity.ok().build();
	}
}
