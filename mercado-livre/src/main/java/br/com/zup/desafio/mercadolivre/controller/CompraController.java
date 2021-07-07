package br.com.zup.desafio.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.desafio.mercadolivre.config.validation.ResourceException;
import br.com.zup.desafio.mercadolivre.dto.CompraDTO;
import br.com.zup.desafio.mercadolivre.form.Email;
import br.com.zup.desafio.mercadolivre.model.Compra;
import br.com.zup.desafio.mercadolivre.model.Produto;
import br.com.zup.desafio.mercadolivre.model.Usuario;
import br.com.zup.desafio.mercadolivre.model.enums.FormaPagamento;
import br.com.zup.desafio.mercadolivre.repository.CompraRepository;
import br.com.zup.desafio.mercadolivre.repository.ProdutoRepository;
import br.com.zup.desafio.mercadolivre.repository.UsuarioRepository;

@RestController
@RequestMapping("/compra")
public class CompraController {

	private final ProdutoRepository produtoRepository;

	private final UsuarioRepository usuarioRepository;

	private final CompraRepository compraRepository;

	private final Email email;

	public CompraController(ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository,
			CompraRepository compraRepository, Email email) {
		super();
		this.produtoRepository = produtoRepository;
		this.usuarioRepository = usuarioRepository;
		this.compraRepository = compraRepository;
		this.email = email;
	}

	@PostMapping
	public ResponseEntity<?> inserir(@AuthenticationPrincipal Usuario usuario, @RequestBody @Valid CompraDTO compraDto,
			UriComponentsBuilder uriBuilder) {
		Long produtoId = compraDto.getProdutoId();
		Produto produto = produtoRepository.findById(produtoId).orElseThrow();
		Integer quantidade = compraDto.getQuantidade();
		boolean abatido = produto.abateEstoque(quantidade);
		usuario = usuarioRepository.findByLogin("jota@email.com").orElseThrow();

		if (abatido) {
			FormaPagamento formaPagamento = compraDto.getFormaPagamento();
			Compra compra = new Compra(quantidade, formaPagamento, usuario, produto);
			compraRepository.save(compra);
			email.novaCompra(compra);

			return ResponseEntity.status(HttpStatus.FOUND).body(compra.redirecionar(uriBuilder));
		} else {
			throw new ResourceException("Não foi possivel concluir a compra");
		}
	}
}
