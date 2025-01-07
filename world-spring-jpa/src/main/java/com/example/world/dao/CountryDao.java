package com.example.world.dao;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.example.world.entity.Country;
import com.example.world.entity.CountryWithCapital;

public interface CountryDao extends GenericDao<Country, String> {
	Collection<Country> getByContinent(String continent);

	@Transactional
	Collection<CountryWithCapital> getByContinentCapitals(String continent);

	Set<String> getContinents();

	List<Country> retrieveCountriesWithLanguagesByContinent(String continent);

	List<Country> retrieveCountriesWithCitiesByContinent(String continent);

	List<Country> retrieveCountriesWithCitiesAndLangsByContinent(String continent);

}
