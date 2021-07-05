package br.com.zup.desafio.mercadolivre.dto;

import javax.validation.constraints.NotBlank;

import br.com.zup.desafio.mercadolivre.model.CaracteristicaProduto;
import br.com.zup.desafio.mercadolivre.model.Produto;

public class CaracteristicaProdutoDTO {

	@NotBlank
	private String nome;

	@NotBlank
	private String descricao;

	public CaracteristicaProdutoDTO(@NotBlank String nome, @NotBlank String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}

	public CaracteristicaProduto toModel(Produto produto) {
		return new CaracteristicaProduto(nome, descricao, produto);
	}
}
