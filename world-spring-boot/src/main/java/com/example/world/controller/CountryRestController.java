package com.example.world.controller;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.world.entity.Country;
import com.example.world.repository.CountryRepository;
import com.example.world.service.WorldService;

@RestController
@RequestScope
@CrossOrigin
public class CountryRestController {
	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private WorldService worldService;

	// http://localhost:8001/world/api/v1/countries?continent=Asia
	// http://localhost:8001/world/api/v1/countries
	@GetMapping("countries")
	public Collection<Country> getAllCountries(@RequestParam(name = "continent", required = false) String continent) {
		if (Objects.isNull(continent))
			return countryRepository.findAll();
		return countryRepository.findByContinent(continent);
	}

	// http://localhost:8001/world/api/v1/countries/TUR
	@GetMapping("countries/{code}")
	public Country getCountryByCode(@PathVariable("code") String code) {
		Optional<Country> country = countryRepository.findById(code);
		if (country.isPresent())
			return country.get();
		throw new IllegalArgumentException("Cannot find country");
	}

	@GetMapping("continents")
	public Collection<String> getAllContinents() {
		return countryRepository.getAllContinents();
	}

	@PostMapping("countries")
	public Country createCountry(@RequestBody @Validated Country country,BindingResult result) {
		if (result.hasErrors()) {
			throw new IllegalArgumentException("Wrong country!");
		}
		return worldService.addCountry(country);
	}

	@PutMapping("countries")
	public Country updateCountry(@RequestBody Country country) {
		return worldService.updateCountry(country);
	}

	@DeleteMapping("countries/{code}")
	public Country removeCountryByCode(@PathVariable("code") String code) {
		return worldService.removeCountry(code);
	}
}
