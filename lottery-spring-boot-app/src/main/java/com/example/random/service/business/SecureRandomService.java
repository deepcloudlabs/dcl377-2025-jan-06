package com.example.random.service.business;

import java.security.SecureRandom;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.example.random.service.RandomService;

@Service
//@Qualify(QualityLevel.SECURE)
@ConditionalOnProperty(name="randomService",havingValue = "SECURE")
public class SecureRandomService implements RandomService {

	private SecureRandom random = new SecureRandom();

	@Override
	public int generate(int min, int max) {
		return random.nextInt(min, max) ;
	}

}
