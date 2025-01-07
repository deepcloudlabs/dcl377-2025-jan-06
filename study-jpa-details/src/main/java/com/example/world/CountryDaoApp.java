package com.example.world;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.world.config.AppConfig;
import com.example.world.dao.CountryDao;
import com.example.world.entity.Country;

public class CountryDaoApp {

	public static void main(String[] args) {
		ConfigurableApplicationContext container = new AnnotationConfigApplicationContext(AppConfig.class);
		CountryDao countryDao = container.getBean(CountryDao.class);
		// Country turkey= countryDao.find("TUR");
		Country turkey = countryDao.find("TUR", "graph.Country.citylangs");
		turkey.getCities().forEach(System.err::println);
		container.close();
	}

}
