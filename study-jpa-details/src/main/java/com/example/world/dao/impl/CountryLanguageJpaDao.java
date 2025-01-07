package com.example.world.dao.impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.example.world.dao.CountryLanguageDao;
import com.example.world.entity.CountryLanguage;
import com.example.world.entity.CountryLanguagePK;

@Repository
public class CountryLanguageJpaDao implements CountryLanguageDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public CountryLanguage add(CountryLanguage countryLanguage) {
		entityManager.persist(countryLanguage);
		return countryLanguage;
	}

	@Override
	public CountryLanguage update(CountryLanguage countryLanguage) {
		return null;
	}

	@Override
	public CountryLanguage remove(CountryLanguagePK key) {
		CountryLanguage countryLanguage = entityManager.find(CountryLanguage.class, key);
		if (countryLanguage != null)
			entityManager.remove(countryLanguage);
		return countryLanguage;
	}

	@Override
	public CountryLanguage find(CountryLanguagePK key) {
		return entityManager.find(CountryLanguage.class, key);
	}

	@Override
	public Collection<CountryLanguage> findAll() {
		List<CountryLanguage> languages = entityManager
				.createQuery("select cl from CountryLanguage cl", CountryLanguage.class).getResultList();
		return languages;
	}

	@Override
	public CountryLanguage find(CountryLanguagePK key, String graphName) {
		return null;
	}

}
