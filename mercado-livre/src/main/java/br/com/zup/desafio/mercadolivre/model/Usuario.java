package br.com.zup.desafio.mercadolivre.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


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
	@Size(min = 6)
	@NotNull
	private String senha;

	@NotNull
	@PastOrPresent
	private Instant instanteCadastro;

	public Usuario(@NotBlank @Email @NotNull String login, @NotBlank @Size(min = 6) @NotNull String senha,
			@NotNull @PastOrPresent Instant instanteCadastro) {
		super();
		this.login = login;
        this.senha = new BCryptPasswordEncoder().encode(senha);
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

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", login=" + login + ", senha=" + senha + ", instanteCadastro=" + instanteCadastro
				+ "]";
	}
	
	

}
