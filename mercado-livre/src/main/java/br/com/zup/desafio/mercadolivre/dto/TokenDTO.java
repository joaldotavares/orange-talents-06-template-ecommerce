package br.com.zup.desafio.mercadolivre.dto;

public class TokenDTO {

	private String token;
	private String tipo;

	public TokenDTO(String token, String tipo) {
		super();
		this.token = token;
		this.tipo = tipo;
	}

	public String getToken() {
		return token;
	}

	public String getTipo() {
		return tipo;
	}

}
