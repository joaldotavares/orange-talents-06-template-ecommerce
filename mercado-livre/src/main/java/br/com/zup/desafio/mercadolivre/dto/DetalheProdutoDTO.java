package br.com.zup.desafio.mercadolivre.dto;

import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;

import br.com.zup.desafio.mercadolivre.model.DetalheProdutoCaracteristica;
import br.com.zup.desafio.mercadolivre.model.Produto;

public class DetalheProdutoDTO {

	private String nome;
	private Double preco;
	private String descricao;
	private Set<DetalheProdutoCaracteristica> caracteristicas;
	private Set<String> urlImagens;
	private Set<String> perguntas;
	private Set<Map<String, String>> opinioes;
	private Double media;

	public DetalheProdutoDTO(Produto produto) {
		this.nome = produto.getNome();
		this.preco = produto.getPreco();
		this.descricao = produto.getDescricao();
		this.caracteristicas = produto
				.mapCaracteristica(caracteristica -> new DetalheProdutoCaracteristica(caracteristica));
		this.urlImagens = produto.mapImagem(imagem -> imagem.getUrl());
		this.perguntas = produto.mapPergunta(pergunta -> pergunta.getTitulo());
		this.opinioes = produto.mapOpiniao(opiniao -> {
			return Map.of("titulo", opiniao.getTitulo(), "descricao", opiniao.getDescricao());
		});

		Set<Integer> notas = produto.mapOpiniao(opiniao -> opiniao.getNota());
		
		OptionalDouble mediaInicial = notas.stream().mapToInt(nota -> nota).average();
		
		this.media = mediaInicial.orElseGet(() -> 0.0);

	}

	public String getNome() {
		return nome;
	}

	public Double getPreco() {
		return preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public Set<DetalheProdutoCaracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public Set<String> getUrlImagens() {
		return urlImagens;
	}

	public Set<String> getPerguntas() {
		return perguntas;
	}

	public Set<Map<String, String>> getOpinioes() {
		return opinioes;
	}

	public Double getMedia() {
		return media;
	}

}
