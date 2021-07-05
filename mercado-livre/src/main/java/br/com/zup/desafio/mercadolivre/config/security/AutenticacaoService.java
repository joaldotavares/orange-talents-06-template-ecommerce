package br.com.zup.desafio.mercadolivre.config.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.zup.desafio.mercadolivre.model.Usuario;
import br.com.zup.desafio.mercadolivre.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

	private final UsuarioRepository usuarioRepository;

	public AutenticacaoService(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findByLogin(username);
		if (usuario.isPresent()) {
			return usuario.get();
		}
		throw new UsernameNotFoundException("Dados inválidos!");
	}

}
