package com.example.world.dao.impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.world.dao.CityDao;
import com.example.world.entity.City;

@Repository
public class CityJpaDao implements CityDao {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public City add(City country) {
		entityManager.persist(country);
		return country;
	}

	@Override
	public City update(City country) {
		return entityManager.merge(country);
	}

	@Override
	@Transactional
	public City remove(Integer key) {
		City city;
		city = entityManager.find(City.class, key);
		if (city != null) {
			entityManager.remove(city);
		}
		return city;
	}

	@Override
	@Transactional
	public City find(Integer key) {
		City city = entityManager.find(City.class, key);
		return city;
	}

	@Override
	public Collection<City> findAll() {
		List<City> cities = entityManager.createNamedQuery("fromCity.all", City.class).getResultList();
		return cities;
	}

	@Override
	public Collection<City> findCitiesByCountry(String code) {
		List<City> cities = entityManager.createNamedQuery("fromCity.byCountry", City.class).setParameter("code", code)
				.getResultList();
		return cities;
	}

	@Override
	public City find(Integer key, String graphName) {
		// TODO Auto-generated method stub
		return null;
	}

}
