package br.com.zup.desafio.mercadolivre.health;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class MeuHealthCheck implements HealthIndicator {

	@Override
	public Health health() {
		Map<String, Object> detalhes = new HashMap<>();
		detalhes.put("versão", "1.2.3");
		detalhes.put("descrição", "Meu primeiro Health Check");
		detalhes.put("endereço", "127.0.0.1");

		return Health.status(Status.UP).withDetails(detalhes).build();
	}

}
