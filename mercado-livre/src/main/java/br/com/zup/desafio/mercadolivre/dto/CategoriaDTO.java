package br.com.zup.desafio.mercadolivre.dto;

import javax.validation.constraints.NotBlank;

import br.com.zup.desafio.mercadolivre.config.validation.UnicoValor;
import br.com.zup.desafio.mercadolivre.model.Categoria;
import br.com.zup.desafio.mercadolivre.repository.CategoriaRepository;

public class CategoriaDTO {

	@NotBlank
	@UnicoValor(domainClass = Categoria.class, fieldName = "nome")
	private String nome;

	private Long categoriaId;

	public CategoriaDTO(@NotBlank String nome, Long categoriaId) {
		this.nome = nome;
		this.categoriaId = categoriaId;
	}

	public Categoria converter(CategoriaRepository categoriaRepository) {
		Categoria categoria = null;

		if (categoriaId != null) {
			categoria = categoriaId == null ? null : categoriaRepository.findById(categoriaId).orElseThrow();
			
		}
		return new Categoria(nome, categoria);
	}

	public String getNome() {
		return nome;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}

}
