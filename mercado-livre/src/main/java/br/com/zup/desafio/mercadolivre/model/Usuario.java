package br.com.zup.desafio.mercadolivre.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;


@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Email
	@NotNull
	private String login;

	@NotBlank
	@Min(6)
	@NotNull
	private String senha;

	@NotNull
	@PastOrPresent
	private Instant instanteCadastro;

	public Usuario(@NotBlank @Email @NotNull String login, @NotBlank @Min(6) @NotNull String senha,
			@NotNull @PastOrPresent Instant instanteCadastro) throws NoSuchAlgorithmException {
		super();
		this.login = login;
        this.senha = senha;
		this.instanteCadastro = instanteCadastro;
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public Instant getInstanteCadastro() {
		return instanteCadastro;
	}

}
