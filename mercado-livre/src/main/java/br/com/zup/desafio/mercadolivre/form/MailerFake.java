package br.com.zup.desafio.mercadolivre.form;

import org.springframework.stereotype.Component;

import br.com.zup.desafio.mercadolivre.interfaces.Mailer;

@Component
public class MailerFake implements Mailer{

	@Override
	public void send(String body, String subject, String nameFrom, String from, String to) {
		System.out.println(body);
		System.out.println(subject);
		System.out.println(nameFrom);
		System.out.println(from);
		System.out.println(to);
	}

	
}
