package com.example.binance.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

	@KafkaListener(topics = {"${topicName}"}, groupId = "binance-consumer")
	public void handleMessage(String message) {
		System.out.println("Kafka: %s".formatted(message));
	}
}
