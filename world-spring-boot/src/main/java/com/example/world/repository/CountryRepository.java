package com.example.world.repository;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import com.example.world.entity.Country;

public interface CountryRepository extends JpaRepository<Country, String> {
	@Lock(LockModeType.PESSIMISTIC_READ)
	List<Country> findByContinent(String continent);

	@Query(nativeQuery = true, value = "select distinct continent from country")
	List<String> getAllContinents();
	
	@Query("select c from Country c where c.continent=:continent")
	@EntityGraph(value="graph.Country.languages",type = EntityGraphType.LOAD)
	List<Country> retrieveCountriesWithLanguagesByContinent(String continent);

	@Query("select c from Country c where c.continent=:continent")
	@EntityGraph(value="graph.Country.cities",type = EntityGraphType.LOAD)
	List<Country> retrieveCountriesWithCitiesByContinent(String continent);
	
	@Query("select c from Country c where c.continent=:continent")
	@EntityGraph(value="graph.Country.citylangs",type = EntityGraphType.LOAD)
	List<Country> retrieveCountriesWithCitiesAndLangsByContinent(String continent);
	
}
