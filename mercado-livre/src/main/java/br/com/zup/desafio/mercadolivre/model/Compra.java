package br.com.zup.desafio.mercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.desafio.mercadolivre.model.enums.FormaPagamento;

@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Positive
	@NotNull
	private Integer quantidade;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private FormaPagamento formaPagamento;

	@NotNull
	@Valid
	@ManyToOne
	private Usuario usuario;
	
	@NotNull
	@Valid
	@ManyToOne
	private Produto produto;

	public Compra(@Positive @NotNull Integer quantidade, @NotNull FormaPagamento formaPagamento, @NotNull @Valid Usuario usuario,
			@NotNull @Valid Produto produto) {
		super();
		this.quantidade = quantidade;
		this.formaPagamento = formaPagamento;
		this.usuario = usuario;
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Produto getProduto() {
		return produto;
	}

	public Long getId() {
		return id;
	}
	
	public String redirecionar(UriComponentsBuilder uriBuilder) {
		return this.formaPagamento.urlRetorno(this, uriBuilder);
	}
}
