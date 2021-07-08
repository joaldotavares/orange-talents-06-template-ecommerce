package br.com.zup.desafio.mercadolivre.model.enums;

public enum StatusRetornoPag {
	
	SUCESSO, ERRO;

	public StatusTransacao normalizar() {
		if(this.equals(SUCESSO)) {
			return StatusTransacao.sucesso;
		}
		return StatusTransacao.erro;
	}
}
