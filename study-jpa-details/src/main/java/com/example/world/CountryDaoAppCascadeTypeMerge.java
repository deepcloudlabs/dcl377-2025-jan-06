package com.example.world;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.world.config.AppConfig;
import com.example.world.dao.CountryDao;
import com.example.world.entity.Country;

public class CountryDaoAppCascadeTypeMerge {

	public static void main(String[] args) {
		ConfigurableApplicationContext container = new AnnotationConfigApplicationContext(AppConfig.class);
		CountryDao countryDao = container.getBean(CountryDao.class);
		Country turkey = countryDao.find("TUR");
		turkey.setPopulation(2);
		turkey.getCapital().setPopulation(2);
		countryDao.update(turkey);
		container.close();
	}

}
