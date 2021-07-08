package br.com.zup.desafio.mercadolivre.interfaces;

import br.com.zup.desafio.mercadolivre.model.Compra;
import br.com.zup.desafio.mercadolivre.model.Transacao;

public interface RetornoPagamento {

	Transacao toTransacao(Compra compra);
}
