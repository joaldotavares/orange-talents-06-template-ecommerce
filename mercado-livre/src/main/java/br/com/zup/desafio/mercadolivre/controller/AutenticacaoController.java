package br.com.zup.desafio.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.desafio.mercadolivre.config.security.TokenService;
import br.com.zup.desafio.mercadolivre.dto.TokenDTO;
import br.com.zup.desafio.mercadolivre.form.LoginForm;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

	private final AuthenticationManager authenticationManager;

	private final TokenService tokenService;

	public AutenticacaoController(AuthenticationManager authenticationManager, TokenService tokenService) {
		super();
		this.authenticationManager = authenticationManager;
		this.tokenService = tokenService;
	}

	@PostMapping
	public ResponseEntity<TokenDTO> autenticar(@RequestBody @Valid LoginForm form) {
		UsernamePasswordAuthenticationToken dados = form.converter();
		try {
			Authentication authentication = authenticationManager.authenticate(dados);
			String token = tokenService.gerarToken(authentication);
			return ResponseEntity.ok(new TokenDTO(token, "Bearer"));

		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
