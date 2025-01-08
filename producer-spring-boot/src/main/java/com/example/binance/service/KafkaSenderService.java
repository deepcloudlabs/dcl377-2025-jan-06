package com.example.binance.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.binance.dto.TradeMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaSenderService {
	private final KafkaTemplate<String, String> kafkaTemplate;
	private final String topicName;
	private final ObjectMapper objectMapper;
	
	public KafkaSenderService(KafkaTemplate<String, String> kafkaTemplate, 
			@Value("${topicName}") String topicName, ObjectMapper objectMapper) {
		this.kafkaTemplate = kafkaTemplate;
		this.topicName = topicName;
		this.objectMapper = objectMapper;
	}

	@EventListener
	public void handleMessage(TradeMessage tradeMessage) throws JsonProcessingException {
		var jsonMessage = objectMapper.writeValueAsString(tradeMessage);
		kafkaTemplate.send(topicName, tradeMessage.symbol(), jsonMessage);
	}
}
