package br.com.zup.desafio.mercadolivre.form;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import br.com.zup.desafio.mercadolivre.interfaces.Mailer;
import br.com.zup.desafio.mercadolivre.model.Pergunta;

@Service
public class Email {
	
	private final Mailer mailer;

	public Email(Mailer mailer) {
		super();
		this.mailer = mailer;
	}
	
	public void novaPergunta(@NotNull Pergunta pergunta) {		
		mailer.send("Blá blá blá", "Nova pergunta ", pergunta.getUsuarioPergunta().getLogin(), "novapergunta@nossoemail.com", pergunta.getDonoProduto().getLogin());
	}

}
