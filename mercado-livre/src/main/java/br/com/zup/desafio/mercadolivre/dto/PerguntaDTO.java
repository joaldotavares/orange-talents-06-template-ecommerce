package br.com.zup.desafio.mercadolivre.dto;

import java.time.Instant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.desafio.mercadolivre.config.validation.ResourceException;
import br.com.zup.desafio.mercadolivre.model.Pergunta;
import br.com.zup.desafio.mercadolivre.model.Produto;
import br.com.zup.desafio.mercadolivre.model.Usuario;
import br.com.zup.desafio.mercadolivre.repository.ProdutoRepository;
import br.com.zup.desafio.mercadolivre.repository.UsuarioRepository;

public class PerguntaDTO {

	@NotBlank
	private String titulo;

	@NotNull
	private Instant instantePergunta;

	@NotNull
	private Long usuarioId;

	@NotNull
	private Long produtoId;

	public PerguntaDTO(@NotBlank String titulo, @NotNull Instant instantePergunta, @NotNull Long usuarioId,
			@NotNull Long produtoId) {
		super();
		this.titulo = titulo;
		this.instantePergunta = Instant.now();
		this.usuarioId = usuarioId;
		this.produtoId = produtoId;
	}

	public Pergunta toModel(ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository) {
		Produto produto = produtoRepository.findById(produtoId)
				.orElseThrow(() -> new ResourceException("Id de produto não encontrado"));

		Usuario usuario = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new ResourceException("Id de usuario não encontrado"));
		return new Pergunta(titulo, instantePergunta, usuario, produto);
	}

}
