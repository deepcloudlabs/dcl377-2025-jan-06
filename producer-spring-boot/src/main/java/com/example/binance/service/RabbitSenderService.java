package com.example.binance.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.binance.dto.TradeMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RabbitSenderService {
	private final RabbitTemplate rabbitTemplate;
	private final String exchangeName;
	private final ObjectMapper objectMapper;
	
	public RabbitSenderService(RabbitTemplate rabbitTemplate, 
			@Value("${exchangeName}") String exchangeName, ObjectMapper objectMapper) {
		this.rabbitTemplate = rabbitTemplate;
		this.exchangeName = exchangeName;
		this.objectMapper = objectMapper;
	}

	//@EventListener
	public void handleMessage(TradeMessage tradeMessage) throws JsonProcessingException {
		var jsonMessage = objectMapper.writeValueAsString(tradeMessage);
		rabbitTemplate.convertAndSend(exchangeName, "", jsonMessage);
	}
}
