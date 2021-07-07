package br.com.zup.desafio.mercadolivre.model.enums;

import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.desafio.mercadolivre.model.Compra;

public enum FormaPagamento {
	PAGSEGURO {

		@Override
		public String urlRetorno(Compra compra, UriComponentsBuilder uriBuilder) {
			String urlPagSeguro = uriBuilder.path("/retorno-pag-seguro/{id}").buildAndExpand(compra.getId()).toString();
			return "pagseguro.com?returnId=" + compra.getId() + "&redirectUrl=" + urlPagSeguro;
		}

	},
	PAYPAL {

		@Override
		public String urlRetorno(Compra compra, UriComponentsBuilder uriBuilder) {
			String urlPayPal = uriBuilder.path("/retorno-pay-pal/{id}").buildAndExpand(compra.getId()).toString();
			return "paypal.com?buyerId=" + compra.getId() + "&redirectUrl=" + urlPayPal;
		}

	};

	public abstract String urlRetorno(Compra compra, UriComponentsBuilder uriBuilder);
}
