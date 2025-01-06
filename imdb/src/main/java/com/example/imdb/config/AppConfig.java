package com.example.imdb.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.context.annotation.RequestScope;

import com.example.imdb.service.MovieService;
import com.example.imdb.service.SequenceService;
import com.example.imdb.service.business.InMemoryMovieService;

@Configuration
public class AppConfig {

	@Bean(initMethod = "populate", name="inMemoryMovieService")
	@RequestScope
	@Profile({"test","dev"})
	MovieService createMovieService(SequenceService sequenceSrv,ApplicationContext context) {
		System.err.println("createMovieService() is called");
		context.getBeansOfType(Object.class)
		       .entrySet()
		       .stream()
		       .filter(entry -> ! entry.getKey().equals("inMemoryMovieService"))
		       .forEach(System.out::println);
		return new InMemoryMovieService(sequenceSrv);
	}
}
