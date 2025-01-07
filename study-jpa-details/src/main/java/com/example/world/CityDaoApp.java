package com.example.world;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.world.config.AppConfig;
import com.example.world.dao.CityDao;
import com.example.world.dao.CountryDao;
import com.example.world.entity.City;
import com.example.world.entity.Country;
import com.example.world.service.BusinessService;

public class CityDaoApp {

	public static void main(String[] args) {
		ConfigurableApplicationContext container = new AnnotationConfigApplicationContext(AppConfig.class);
		CityDao cityDao = container.getBean(CityDao.class);
		CountryDao countryDao = container.getBean(CountryDao.class);
		City city = cityDao.find(1);
		BusinessService bs = container.getBean(BusinessService.class);
		System.err.println(bs.computePopulationRate(1000));
		System.err.println(city.getClass());
		System.err.println(city.getCountry().getClass());
		System.err.println(city);
		Country turkey = countryDao.find("TUR","graph.Country.cities");
		System.err.println(turkey.getCapital().getName());
		turkey.getCities().forEach(System.err::println);
		container.close();
	}

}
