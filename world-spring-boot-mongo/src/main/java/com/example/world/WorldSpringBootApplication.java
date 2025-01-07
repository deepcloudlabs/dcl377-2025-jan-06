package com.example.world;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.world.repository.CountryRepository;
import com.example.world.repository.WorldRepository;

@SpringBootApplication
public class WorldSpringBootApplication implements ApplicationRunner{
	private final CountryRepository countryRepository;
	private final WorldRepository worldRepository;
	
	public WorldSpringBootApplication(CountryRepository countryRepository, WorldRepository worldRepository) {
		this.countryRepository = countryRepository;
		this.worldRepository = worldRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(WorldSpringBootApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
//		countryRepository.findAll(PageRequest.of(0, 100))
//		                 .forEach(System.out::println);
		worldRepository.getPopulationByContinent().forEach(System.out::println);	
		countryRepository.getir("Asia","Southeast Asia")
		                 .forEach(System.out::println);
	}



}

