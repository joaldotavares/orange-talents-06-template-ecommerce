package br.com.zup.desafio.mercadolivre.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.zup.desafio.mercadolivre.model.enums.StatusTransacao;

@Entity
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private StatusTransacao status;

	@NotNull
	private String transacaoId;

	private Instant instanteTransacao;

	@NotNull
	@Valid
	@ManyToOne
	private Compra compra;

	public Transacao() {

	}

	public Transacao(@NotNull StatusTransacao status, @NotNull String transacaoId, @NotNull @Valid Compra compra) {
		super();
		this.status = status;
		this.transacaoId = transacaoId;
		this.instanteTransacao = Instant.now();
		this.compra = compra;
	}
	
	public boolean concluidoComSucesso() {
		return this.status.equals(StatusTransacao.sucesso);
	}

	public StatusTransacao getStatus() {
		return status;
	}

	public String getTransacaoId() {
		return transacaoId;
	}

	public Instant getInstanteTransacao() {
		return instanteTransacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transacao other = (Transacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transacao [id=" + id + ", status=" + status + ", transacaoId=" + transacaoId + ", instanteTransacao="
				+ instanteTransacao + "]";
	}

	
}
