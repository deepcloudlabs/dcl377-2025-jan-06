package com.example.binance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TradeMessage(
		@JsonProperty("s")
		String symbol,
		@JsonProperty("p")
		String price,
		@JsonProperty("q")
		String quantity,
		@JsonProperty("T")
		long timestamp
		) {

}
