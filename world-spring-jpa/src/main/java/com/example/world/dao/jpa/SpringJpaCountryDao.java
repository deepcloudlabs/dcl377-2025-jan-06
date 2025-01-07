package com.example.world.dao.jpa;

import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.world.dao.CountryDao;
import com.example.world.entity.Country;
import com.example.world.entity.CountryWithCapital;

@Repository
public class SpringJpaCountryDao implements CountryDao {
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	@Override
	public Optional<Country> findOne(String code) {
		Country country = entityManager.find(Country.class, code);
		return ofNullable(country);
	}

	@Override
	public Collection<Country> findAll(int pageNo, int pageSize) {
		return entityManager.createNamedQuery("Country.findAll", Country.class).setFirstResult((pageNo - 1) * pageSize)
				.setMaxResults(pageSize).getResultList();
	}

	@Override
	@Transactional
	public Country add(Country country) {
		String code = country.getCode();
		Optional<Country> found = findOne(code);
		if (found.isPresent())
			throw new IllegalArgumentException("Country exists!");
		entityManager.persist(country);
		return country;
	}

	@Override
	@Transactional
	public Country update(Country country) {
		String code = country.getCode();
		Optional<Country> found = findOne(code);
		if (found.isPresent()) {
			Country c = found.get();
			c.setPopulation(country.getPopulation());
			c.setSurfaceArea(country.getSurfaceArea());
			entityManager.merge(c);
			return c;
		}
		throw new IllegalArgumentException("Country does not exist!");
	}

	@Override
	@Transactional
	public Optional<Country> remove(String code) {
		Country country = entityManager.find(Country.class, code);
		if (nonNull(country))
			entityManager.remove(country);
		return ofNullable(country);
	}

	@Override
	public Collection<Country> getByContinent(String continent) {
		return entityManager.createNamedQuery("Country.findByContinent", Country.class)
				.setParameter("continent", continent).getResultList();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Set<String> getContinents() {
		// List<String> continents = entityManager.createNativeQuery("select distinct
		// continent from country")
		// .getResultList();
		// return new TreeSet<>(continents);
		List continents = entityManager.createNamedQuery("DistinctContinents").getResultList();
		return new TreeSet<String>(continents);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Collection<CountryWithCapital> getByContinentCapitals(String continent) {
		List<Object[]> resultList = entityManager.createNamedStoredProcedureQuery("ContinentCapitals")
				.setParameter("cont", continent).getResultList();
		return resultList.stream().map(record -> {
			CountryWithCapital countryWithCapital = new CountryWithCapital();
			countryWithCapital.setCode(record[0].toString());
			countryWithCapital.setName(record[1].toString());
			countryWithCapital.setCityId(Long.parseLong(record[2].toString()));
			countryWithCapital.setCapital(record[3].toString());
			return countryWithCapital;
		}).collect(Collectors.toList());
	}

	@Override
	public List<Country> retrieveCountriesWithLanguagesByContinent(String continent) {
		EntityGraph<?> eg = entityManager.getEntityGraph("graph.Country.languages");

		return entityManager.createNamedQuery("Country.findAll", Country.class)
				.setHint("javax.persistence.loadgraph", eg).getResultList();
	}

	@Override
	public List<Country> retrieveCountriesWithCitiesByContinent(String continent) {
		EntityGraph<?> eg = entityManager.getEntityGraph("graph.Country.cities");

		return entityManager.createNamedQuery("Country.findAll", Country.class)
				.setHint("javax.persistence.loadgraph", eg).getResultList();
	}

	@Override
	public List<Country> retrieveCountriesWithCitiesAndLangsByContinent(String continent) {
		EntityGraph<?> eg = entityManager.getEntityGraph("graph.Country.citylangs");

		return entityManager.createNamedQuery("Country.findAll", Country.class)
				.setHint("javax.persistence.loadgraph", eg).getResultList();
	}

}
