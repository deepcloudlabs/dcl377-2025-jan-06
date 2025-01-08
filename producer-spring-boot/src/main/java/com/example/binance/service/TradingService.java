package com.example.binance.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;

import com.example.binance.dto.TradeMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;

@Service
public class TradingService implements WebSocketHandler {
	private final WebSocketClient webSocketClient;
	private final ObjectMapper objectMapper;
	private final ApplicationEventPublisher eventPublisher;
	
	public TradingService(WebSocketClient webSocketClient, ObjectMapper objectMapper, ApplicationEventPublisher eventPublisher) {
		this.webSocketClient = webSocketClient;
		this.objectMapper = objectMapper;
		this.eventPublisher = eventPublisher;
	}

	@PostConstruct
	public void handshake() {
		webSocketClient.execute(this, "wss://stream.binance.com:9443/ws/btcusdt@trade");
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.err.println("Connected to the binance ws server...");
		
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		var jsonMessage = message.getPayload().toString();
		var tradeMessage= objectMapper.readValue(jsonMessage, TradeMessage.class);
		eventPublisher.publishEvent(tradeMessage);
		System.err.println(jsonMessage);
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}

}
