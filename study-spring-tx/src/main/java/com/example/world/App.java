package com.example.world;

import java.util.concurrent.TimeUnit;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.world.config.AppConfig;
import com.example.world.service.WorldService;

public class App {
	public static void main(String[] args) throws InterruptedException {
		try (ConfigurableApplicationContext container = new AnnotationConfigApplicationContext(AppConfig.class);) {
			WorldService worldSrv = container.getBean(WorldService.class);
			worldSrv.incrementPopulation();
			TimeUnit.SECONDS.sleep(10);
		}
	}
}
