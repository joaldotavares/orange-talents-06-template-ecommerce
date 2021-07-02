package br.com.zup.desafio.mercadolivre.dto;

import java.security.NoSuchAlgorithmException;
import java.time.Instant;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import br.com.zup.desafio.mercadolivre.config.validation.UnicoEmail;
import br.com.zup.desafio.mercadolivre.model.Usuario;

public class UsuarioDTO {

	@NotBlank
	@Email
	@UnicoEmail(domainClass = Usuario.class, fieldName = "login")
	private String login;

	@NotBlank
	@Size(min = 6)
	private String senha;

	@NotNull
	@PastOrPresent
	private Instant instanteCadastro;

	public UsuarioDTO(@NotBlank @Email String login, @NotBlank @Min(6) String senha) {
		super();
		this.login = login;
		this.senha = senha;
		this.instanteCadastro = Instant.now();
	}
	
	public Usuario toModel() throws NoSuchAlgorithmException {
		return new Usuario(login, senha, instanteCadastro);
	}

}
