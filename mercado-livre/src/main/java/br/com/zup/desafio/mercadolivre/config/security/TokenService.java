package br.com.zup.desafio.mercadolivre.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.zup.desafio.mercadolivre.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${mercado.jwt.expiration}")
	private String expiration;

	@Value("${mercado.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authentication) {
		Usuario logado = (Usuario) authentication.getPrincipal();
		Date hoje = new Date();
		Date dataToken = new Date(hoje.getTime() + Long.parseLong(expiration));

		return Jwts.builder().setIssuer("API desafio mercado livre ZUP").setSubject(logado.getId().toString())
				.setIssuedAt(hoje).setExpiration(dataToken).signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public boolean isValid(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}
