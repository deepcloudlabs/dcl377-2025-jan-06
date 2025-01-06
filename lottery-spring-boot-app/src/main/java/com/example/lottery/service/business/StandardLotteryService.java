package com.example.lottery.service.business;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.example.lottery.service.LotteryService;
import com.example.random.service.RandomService;

@Service
public class StandardLotteryService implements LotteryService {
	private final RandomService randomService;

	public StandardLotteryService(
			//@Qualify(QualityLevel.FAST)  
			RandomService randomService) {
		this.randomService = randomService;
	}

	@Override
	public List<Integer> draw(int max, int size) {
		return IntStream.generate(() -> randomService.generate(1, max))
				        .distinct()
				        .limit(size)
				        .boxed()
				        .toList();
	}

}
