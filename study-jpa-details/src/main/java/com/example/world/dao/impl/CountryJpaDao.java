package com.example.world.dao.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.world.dao.CountryDao;
import com.example.world.entity.Country;

@Repository
public class CountryJpaDao implements CountryDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public Country add(Country country) {
		entityManager.persist(country);
		return country;
	}

	@Override
	@Transactional
	public Country update(Country country) {
		Country found = entityManager.find(Country.class, country.getCode());
		if (found != null) {
			found.setPopulation(country.getPopulation());
			found.setSurfaceArea(country.getSurfaceArea());
		}
		// entityManager.merge(country);
		return country;
	}

	@Override
	@Transactional
	public Country remove(String code) {
		Country found = entityManager.find(Country.class, code);
		if (found != null) {
			entityManager.remove(found);
		}
		return found;
	}

	@Override
	@Transactional
	@Cacheable(value = "country")
	public Country find(String code) {
		System.err.println("find(" + code + ") is running...");
		return entityManager.find(Country.class, code);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Collection<Country> findAll() {
		return entityManager.createNamedQuery("AllFromCountry", Country.class).getResultList();
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Collection<Country> findByContinent(String continent) {
		return entityManager.createNamedQuery("ByContinentFromCountry", Country.class)
				.setParameter("continent", continent).getResultList();
	}

	@Override
	@Transactional()
	public void test(String code) {
		remove(code);
	}

	@Override
	public Country find(String key, String graphName) {
		EntityGraph<?> eg = entityManager.getEntityGraph(graphName);
		Map<String, Object> props = new HashMap<>();
		props.put("javax.persistence.fetchgraph", eg);
		return entityManager.find(Country.class, key, props);
	}

}
