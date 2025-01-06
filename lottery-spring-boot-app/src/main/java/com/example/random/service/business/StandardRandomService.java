package com.example.random.service.business;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.example.random.service.RandomService;

@Service
//@Qualify(QualityLevel.FAST)
@ConditionalOnProperty(name="randomService",havingValue = "FAST")
public class StandardRandomService implements RandomService {

	@Override
	public int generate(int min, int max) {
		System.err.println("StandardRandomService::generate");
		return ThreadLocalRandom.current().nextInt(min, max);
	}

}
