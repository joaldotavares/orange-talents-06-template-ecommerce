package br.com.zup.desafio.mercadolivre.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.desafio.mercadolivre.interfaces.RetornoPagamento;
import br.com.zup.desafio.mercadolivre.model.Compra;
import br.com.zup.desafio.mercadolivre.model.Transacao;
import br.com.zup.desafio.mercadolivre.model.enums.StatusRetornoPag;

public class FechamentoPagSeguroDTO implements RetornoPagamento{
	
	@NotBlank
	private String compraId;
	
	@NotNull
	private StatusRetornoPag status;

	public FechamentoPagSeguroDTO(@NotNull String compraId, @NotNull StatusRetornoPag status) {
		super();
		this.compraId = compraId;
		this.status = status;
	}

	public String getCompraId() {
		return compraId;
	}

	public StatusRetornoPag getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "FechamentoDTO [compraId=" + compraId + ", status=" + status + "]";
	}

	public Transacao toTransacao(Compra compra) {
		return new Transacao(status.normalizar(), compraId, compra);
	}
	
}
