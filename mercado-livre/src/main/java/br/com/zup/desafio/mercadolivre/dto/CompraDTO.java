package br.com.zup.desafio.mercadolivre.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.desafio.mercadolivre.model.enums.FormaPagamento;

public class CompraDTO {

	@Positive
	private Integer quantidade;

	@NotNull
	private Long produtoId;

	@NotNull
	private FormaPagamento formaPagamento;

	public CompraDTO(@Positive Integer quantidade, @NotNull Long produtoId, @NotNull FormaPagamento formaPagamento) {
		this.quantidade = quantidade;
		this.produtoId = produtoId;
		this.formaPagamento = formaPagamento;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}

	public Long getProdutoId() {
		return produtoId;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

}
