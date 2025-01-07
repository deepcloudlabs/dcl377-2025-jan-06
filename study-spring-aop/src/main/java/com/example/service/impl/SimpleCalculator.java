package com.example.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("request")
public class SimpleCalculator {
	// @Autowired private Calculator self;

	//@Profiler(TimeUnit.NANOSECONDS)
	public int add(int x, int y) {
		return x + y;
	}

	//@Profiler(TimeUnit.NANOSECONDS)
	public int sub(int x, int y) {
		return x - y;
	}

	public int mul(int x, int y) {
		int sum = 0;
		for (int i = 0; i < x; ++i, sum = this.add(sum, y))
			;
		return sum;
	}

	public int div(int x, int y) {
		return x / y;
	}

}
