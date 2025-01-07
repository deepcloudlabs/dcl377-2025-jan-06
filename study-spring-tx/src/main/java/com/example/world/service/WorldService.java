package com.example.world.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.world.dao.CountryDao;

@Service
public class WorldService {
	@Autowired
	private CountryDao countryDao;

	@Transactional(propagation = Propagation.NEVER)
	public void incrementPopulation() {
		countryDao.findAll(1, 1000).forEach(country -> {
			country.setPopulation(country.getPopulation() + 1);
			countryDao.update(country);
		});
	}
}
