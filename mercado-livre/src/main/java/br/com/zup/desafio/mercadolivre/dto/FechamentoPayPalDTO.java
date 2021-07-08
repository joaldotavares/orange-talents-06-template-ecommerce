package br.com.zup.desafio.mercadolivre.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import br.com.zup.desafio.mercadolivre.interfaces.RetornoPagamento;
import br.com.zup.desafio.mercadolivre.model.Compra;
import br.com.zup.desafio.mercadolivre.model.Transacao;
import br.com.zup.desafio.mercadolivre.model.enums.StatusTransacao;

public class FechamentoPayPalDTO implements RetornoPagamento {

	@Min(0)
	@Max(1)
	private int status;

	@NotBlank
	private String compraId;

	public FechamentoPayPalDTO(@Min(0) @Max(1) int status, @NotBlank String compraId) {
		super();
		this.status = status;
		this.compraId = compraId;
	}
	
	public int getStatus() {
		return status;
	}

	public String getCompraId() {
		return compraId;
	}

	public Transacao toTransacao(Compra compra) {
		return new Transacao(this.status == 0 ? StatusTransacao.erro : StatusTransacao.sucesso, compraId, compra);
	}
}
