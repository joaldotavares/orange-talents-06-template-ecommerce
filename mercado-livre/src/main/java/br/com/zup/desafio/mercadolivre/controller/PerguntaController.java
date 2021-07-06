package br.com.zup.desafio.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.desafio.mercadolivre.dto.PerguntaDTO;
import br.com.zup.desafio.mercadolivre.form.Email;
import br.com.zup.desafio.mercadolivre.model.Pergunta;
import br.com.zup.desafio.mercadolivre.repository.PerguntaRepository;
import br.com.zup.desafio.mercadolivre.repository.ProdutoRepository;
import br.com.zup.desafio.mercadolivre.repository.UsuarioRepository;

@RestController
public class PerguntaController {

	private final ProdutoRepository produtoRepository;

	private final UsuarioRepository usuarioRepository;

	private final PerguntaRepository perguntaRepository;
	
	private Email email;

	public PerguntaController(ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository,
			PerguntaRepository perguntaRepository, Email email) {
		super();
		this.produtoRepository = produtoRepository;
		this.usuarioRepository = usuarioRepository;
		this.perguntaRepository = perguntaRepository;
		this.email = email;
	}

	@PostMapping(value = "/produto/{id}/pergunta")
	public ResponseEntity<?> inserir(@PathVariable Long id, @RequestBody @Valid PerguntaDTO perguntaDto) {

		Pergunta pergunta = perguntaDto.toModel(produtoRepository, usuarioRepository);

		perguntaRepository.save(pergunta);
		
		email.novaPergunta(pergunta);
		return ResponseEntity.ok().build();
	}
}
