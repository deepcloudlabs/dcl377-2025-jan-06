package com.example.world;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.world.config.AppConfig;
import com.example.world.dao.CountryDao;

public class RemoveCountryOrphanRemoval {

	public static void main(String[] args) {
		ConfigurableApplicationContext container = new AnnotationConfigApplicationContext(AppConfig.class);
		CountryDao countryDao = container.getBean(CountryDao.class);
		countryDao.remove("YEM");
		container.close();
	}

}
