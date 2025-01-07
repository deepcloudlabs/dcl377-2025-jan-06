package com.example.world.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.world.dao.CityDao;
import com.example.world.entity.City;
import com.example.world.entity.Country;

@Service
public class BusinessService {
	@Autowired
	private CityDao cityDao;

	@Transactional
	public double computePopulationRate(int id) {
		City city = cityDao.find(id);
		Country country = city.getCountry();
		double cityPopulation = city.getPopulation();
		int countryPopulation = country.getPopulation();
		System.out.println("City population: " + cityPopulation);
		System.out.println("Country population: " + countryPopulation);
		return cityPopulation / countryPopulation;
	}
}
