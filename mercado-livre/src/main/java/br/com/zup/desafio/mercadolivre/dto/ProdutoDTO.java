package br.com.zup.desafio.mercadolivre.dto;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import br.com.zup.desafio.mercadolivre.config.validation.ResourceException;
import br.com.zup.desafio.mercadolivre.model.Categoria;
import br.com.zup.desafio.mercadolivre.model.Produto;
import br.com.zup.desafio.mercadolivre.model.Usuario;
import br.com.zup.desafio.mercadolivre.repository.CategoriaRepository;
import br.com.zup.desafio.mercadolivre.repository.UsuarioRepository;

public class ProdutoDTO {

	@NotBlank
	private String nome;

	@NotNull
	@Positive
	private Double preco;

	@NotNull
	@Min(0)
	private Integer quantidade;

	@NotEmpty
	@Size(max = 1000)
	private String descricao;

	@NotNull
	private Long categoriaId;
	private Instant instanteCadastro;

	@Size(min = 3)
	private Set<CaracteristicaProdutoDTO> caracteristicas = new HashSet<>();

	@NotNull
	private Long usuarioId;

	public ProdutoDTO(@NotBlank String nome, @NotNull @Positive Double preco, @NotNull @Min(0) Integer quantidade,
			@NotEmpty @Size(max = 1000) String descricao, @NotNull Long categoriaId, Instant instanteCadastro,
			@Size(min = 3) Set<CaracteristicaProdutoDTO> caracteristicas, @NotNull Long usuarioId) {
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoriaId = categoriaId;
		this.instanteCadastro = Instant.now();
		this.caracteristicas.addAll(caracteristicas);
		this.usuarioId = usuarioId;
	}

	public Produto toModel(CategoriaRepository categoriaRepository, UsuarioRepository usuarioRepository) {
		
		Categoria categoria = categoriaRepository.findById(categoriaId)
				.orElseThrow(() -> new ResourceException("Id de categoria não encontrado"));
		
		Usuario usuario = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new ResourceException("Id de usuario não encontrado"));
		return new Produto(nome, preco, quantidade, descricao, categoria, instanteCadastro, caracteristicas, usuario);
	}
}
