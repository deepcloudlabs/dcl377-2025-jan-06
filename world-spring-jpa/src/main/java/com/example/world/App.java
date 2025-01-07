package com.example.world;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.world.config.AppConfig;
import com.example.world.dao.CountryDao;
import com.example.world.entity.CountryLanguage;

public class App {
	public static void main(String[] args) {
		try (ConfigurableApplicationContext container = new AnnotationConfigApplicationContext(AppConfig.class);) {
			CountryDao countryDao = container.getBean(CountryDao.class);
			countryDao.findOne("TUR").ifPresent(System.out::println);
			System.out.println("------------------------------------------");
			countryDao.findAll(1, 10).forEach(System.out::println);
			System.out.println("------------------------------------------");
			countryDao.getByContinent("Asia").forEach(System.out::println);
			System.err.println("------------------------------------------");
			countryDao.getContinents().forEach(System.out::println);
			System.err.println("------------------------------------------");
			countryDao.remove("AAA").ifPresent(System.out::println);
			System.err.println("------------------------------------------");
			countryDao.retrieveCountriesWithLanguagesByContinent("Asia")
			           .stream()
			           .forEach( country -> {
			        	   country.getLanguages().stream().map(CountryLanguage::getCountryLanguagePK).forEach(System.out::println);
			        	   //country.getCities().forEach(System.out::println);
			           });
			System.err.println("------------------------------------------");
			countryDao.retrieveCountriesWithCitiesAndLangsByContinent("Antarctica")
			          .forEach(System.out::println);
			System.out.println("------------------------------------------");
			countryDao.getByContinentCapitals("Asia").forEach(System.out::println);

		}
	}
}
