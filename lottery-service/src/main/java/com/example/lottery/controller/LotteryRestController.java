package com.example.lottery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lottery.service.LotteryService;

@RestController
@RequestMapping("/numbers")
public class LotteryRestController {
	private final LotteryService lotteryService;
	private final int lotteryMax;
	private final int lotterySize;
	
	public LotteryRestController(LotteryService lotteryService, 
			@Value("${lotterySize}") int lotterySize, 
			@Value("${lotteryMax}")int lotteryMax) {
		this.lotteryService = lotteryService;
		this.lotteryMax = lotteryMax;
		this.lotterySize = lotterySize;
	}

	@GetMapping(params = { "column" })
	public List<List<Integer>> getNumbers(@RequestParam int column) {
		return lotteryService.draw(lotteryMax, lotterySize, column);
	}
}