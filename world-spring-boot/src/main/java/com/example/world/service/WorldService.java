package com.example.world.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.world.entity.Country;
import com.example.world.exception.CountryAlreadyExists;
import com.example.world.exception.CountryNotFound;
import com.example.world.repository.CountryRepository;

@Service
public class WorldService {
	@Autowired
	private CountryRepository countryRepository;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Country addCountry(Country country) {
		String code = country.getCode();
		if (countryRepository.existsById(code))
			throw new CountryAlreadyExists(String.format("Country with code %s already exists!", code),
					"countryAlreadyExists", "9eaf5e19-ce15-42d9-9206-95ac5255aa82");
		return countryRepository.save(country);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Country updateCountry(Country country) {
		String code = country.getCode();
		Optional<Country> found = countryRepository.findById(code);
		if (!found.isPresent())
			throw new CountryNotFound(String.format("Country with code %s does not exist!", code),
					"countryDoesNotExist", "6f72f51d-1633-4f5b-a97a-cac331b8ee33");
		Country managed = found.get();
		managed.setPopulation(country.getPopulation());
		managed.setSurfaceArea(country.getPopulation());
		return managed;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Country removeCountry(String code) {
		Optional<Country> found = countryRepository.findById(code);
		if (!found.isPresent())
			throw new CountryNotFound(String.format("Country with code %s does not exist!", code),
					"countryDoesNotExist", "640a2598-c545-44e4-9ded-8c8a4d45ea17");
		Country managed = found.get();
		countryRepository.delete(managed);
		return managed;
	}
}
