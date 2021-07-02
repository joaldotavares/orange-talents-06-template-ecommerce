package br.com.zup.desafio.mercadolivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.desafio.mercadolivre.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
