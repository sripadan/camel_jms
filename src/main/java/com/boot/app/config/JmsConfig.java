package com.boot.app.config;

import javax.jms.ConnectionFactory;

import org.apache.camel.component.jms.JmsComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.JmsTransactionManager;

@Configuration
public class JmsConfig {

	@Value("${max.concurrent.consumers}")
	private int maxConsumers;

	@Bean
	public JmsTransactionManager createJmstransactionManager(final ConnectionFactory connectionFactoty) {
		JmsTransactionManager jmsTransactionManager = new JmsTransactionManager();
		jmsTransactionManager.setConnectionFactory(connectionFactoty);
		return jmsTransactionManager;
	}

	@Bean
	public JmsComponent createJmsComponent(final ConnectionFactory connectionFactory,
			final JmsTransactionManager jmsTransactionManager) {
		JmsComponent jmsComponent = JmsComponent.jmsComponentTransacted(connectionFactory, jmsTransactionManager);
		jmsComponent.setMaxConcurrentConsumers(maxConsumers);
		return jmsComponent;
	}

}
