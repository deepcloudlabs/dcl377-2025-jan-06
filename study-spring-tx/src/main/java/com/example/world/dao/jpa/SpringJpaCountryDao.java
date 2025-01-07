package com.example.world.dao.jpa;

import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.world.dao.CountryDao;
import com.example.world.entity.Country;

@Repository
public class SpringJpaCountryDao implements CountryDao {
	@PersistenceContext
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
	@Transactional(propagation=Propagation.SUPPORTS)
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

}
