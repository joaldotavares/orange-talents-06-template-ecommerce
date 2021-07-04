package br.com.zup.desafio.mercadolivre.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@NotBlank
	@Column(unique = true)
	private String nome;

	@ManyToOne
	private Categoria categoria;
	
	public Categoria() {
		
	}
	
	public Categoria(@NotBlank String nome) {
		super();
		this.nome = nome;
	}

	public Categoria(@NotBlank String nome, Categoria categoria) {
		super();
		this.nome = nome;
		this.categoria = categoria;
	}

}
