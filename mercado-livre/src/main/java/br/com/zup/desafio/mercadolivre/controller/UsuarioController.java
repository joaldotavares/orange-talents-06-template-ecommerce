package br.com.zup.desafio.mercadolivre.controller;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.desafio.mercadolivre.dto.UsuarioDTO;
import br.com.zup.desafio.mercadolivre.model.Usuario;
import br.com.zup.desafio.mercadolivre.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private final UsuarioRepository usuarioRepository;

	public UsuarioController(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}

	@PostMapping
	public ResponseEntity<Usuario> inserir(@RequestBody @Valid UsuarioDTO usuarioDto) throws NoSuchAlgorithmException {
		Usuario usuario = usuarioDto.toModel();
		usuarioRepository.save(usuario);

		return ResponseEntity.ok().build();
	}

}
