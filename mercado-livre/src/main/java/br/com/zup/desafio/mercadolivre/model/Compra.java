package br.com.zup.desafio.mercadolivre.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.desafio.mercadolivre.interfaces.RetornoPagamento;
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

	@OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
	private Set<Transacao> transacoes = new HashSet<Transacao>();
	
	public Compra() {
		super();
	}

	public Compra(@Positive @NotNull Integer quantidade, @NotNull FormaPagamento formaPagamento,
			@NotNull @Valid Usuario usuario, @NotNull @Valid Produto produto) {
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

	public void adicionarPagamento(@Valid RetornoPagamento fechamentoDto) {
		Transacao novaTransacao = fechamentoDto.toTransacao(this);
		Assert.isTrue(!this.transacoes.contains(novaTransacao), "Essa compra já foi finalizada");
		Set<Transacao> concluidasComSucesso = this.transacoes.stream().filter(Transacao::concluidoComSucesso)
				.collect(Collectors.toSet());

		Assert.isTrue(concluidasComSucesso.isEmpty(), "Essa compra já foi concluída com sucesso");
		
		this.transacoes.add(novaTransacao);
	}

	@Override
	public String toString() {
		return "Compra [id=" + id + ", quantidade=" + quantidade + ", formaPagamento=" + formaPagamento + ", usuario="
				+ usuario + ", produto=" + produto + ", transacoes=" + transacoes + "]";
	}
	
	
}
