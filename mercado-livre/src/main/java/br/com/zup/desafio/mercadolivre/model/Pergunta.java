package br.com.zup.desafio.mercadolivre.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Pergunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String titulo;

	@NotNull
	private Instant instantePergunta;

	@NotNull
	@Valid
	@ManyToOne
	private Usuario usuario;

	@NotNull
	@Valid
	@ManyToOne
	private Produto produto;

	public Pergunta() {
		super();
	}

	public Pergunta(@NotBlank String titulo, Instant instantePergunta, @NotNull @Valid Usuario usuario,
			@NotNull @Valid Produto produto) {
		super();
		this.titulo = titulo;
		this.usuario = usuario;
		this.produto = produto;
		this.instantePergunta = instantePergunta;
	}

	public Usuario getUsuarioPergunta() {
		return usuario;
	}

	public Usuario getDonoProduto() {
		return produto.getDono();
	}

}
