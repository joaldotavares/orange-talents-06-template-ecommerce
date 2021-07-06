package br.com.zup.desafio.mercadolivre.model;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import br.com.zup.desafio.mercadolivre.dto.CaracteristicaProdutoDTO;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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
	@Valid
	@ManyToOne
	private Categoria categoria;
	private Instant instanteCadastro;
	
	@NotNull
	@Valid
	@Size(min = 3)
	@OneToMany(mappedBy = "produto")
	private final Set<CaracteristicaProduto> caracteristica = new HashSet<>();

	@NotNull
	@Valid
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<Imagem> imagens = new HashSet<Imagem>();
	
	@NotNull
	@Valid
	@ManyToOne
	private Usuario usuario;
	
	public Produto() {
		
	}

	public Produto(@NotBlank String nome, @NotNull @Positive Double preco, @NotNull @Min(0) Integer quantidade,
			@NotEmpty @Size(max = 1000) String descricao, @NotNull Categoria categoria,
			Instant instanteCadastro, @Size(min = 3) Set<CaracteristicaProdutoDTO> caracteristica, Usuario usuario) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
		this.instanteCadastro = instanteCadastro;
		caracteristica.forEach(dto -> this.caracteristica.add(dto.toModel(this)));
		this.usuario = usuario;
	}
	
	public void vincularImagem(Set<String> urls) {
		Set<Imagem> imagens = urls.stream().map(url -> new Imagem(url, this)).collect(Collectors.toSet());
		this.imagens.addAll(imagens);
	}
	
	public boolean checarUsuario(Usuario possivelUsuario) {
        return this.usuario.equals(possivelUsuario);
    }
	
	public String getNome() {
		return nome;
	}

	public Double getPreco() {
		return preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public Instant getInstanteCadastro() {
		return instanteCadastro;
	}

	public Set<CaracteristicaProduto> getCaracteristica() {
		return caracteristica;
	}

	public Set<Imagem> getImagens() {
		return imagens;
	}

	public Usuario getDono() {
		return this.usuario;
	}
	
}
