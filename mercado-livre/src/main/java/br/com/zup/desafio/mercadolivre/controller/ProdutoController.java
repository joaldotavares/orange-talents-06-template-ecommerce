package br.com.zup.desafio.mercadolivre.controller;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.desafio.mercadolivre.config.validation.ResourceException;
import br.com.zup.desafio.mercadolivre.dto.ImagemDTO;
import br.com.zup.desafio.mercadolivre.dto.ProdutoDTO;
import br.com.zup.desafio.mercadolivre.form.UploadImagemFake;
import br.com.zup.desafio.mercadolivre.model.Produto;
import br.com.zup.desafio.mercadolivre.model.Usuario;
import br.com.zup.desafio.mercadolivre.repository.CategoriaRepository;
import br.com.zup.desafio.mercadolivre.repository.ProdutoRepository;
import br.com.zup.desafio.mercadolivre.repository.UsuarioRepository;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@PersistenceContext
	private EntityManager em;

	private final UploadImagemFake uploadImagemFake;

	private final ProdutoRepository produtoRepository;

	private final CategoriaRepository categorriaRepository;

	private final UsuarioRepository usuarioRepository;

	public ProdutoController(ProdutoRepository produtoRepository, CategoriaRepository categorriaRepository,
			UsuarioRepository usuarioRepository, UploadImagemFake uploadImagemFake) {
		this.produtoRepository = produtoRepository;
		this.categorriaRepository = categorriaRepository;
		this.usuarioRepository = usuarioRepository;
		this.uploadImagemFake = uploadImagemFake;
	}

	@PostMapping
	public ResponseEntity<?> inserir(@RequestBody @Valid ProdutoDTO produtoDto) {
		Produto produto = produtoDto.toModel(categorriaRepository, usuarioRepository);
		produtoRepository.save(produto);

		return ResponseEntity.ok().build();
	}

	@PostMapping(value = "/{id}/imagens")
	public ResponseEntity<?> inserirImagem(@PathVariable Long id, @Valid ImagemDTO imagemDto) {

		Set<String> urls = uploadImagemFake.enviarImagem(imagemDto.getImagens());
		
		Usuario usuario = usuarioRepository.findByLogin("jota@email.com").get();
		
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new ResourceException("Id de produto não encontrado"));
		
		if(!produto.checarUsuario(usuario)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		
		produto.vincularImagem(urls);
		produtoRepository.save(produto);
		
		return ResponseEntity.ok().build();
	}

}
