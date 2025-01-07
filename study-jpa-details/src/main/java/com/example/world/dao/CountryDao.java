package com.example.world.dao;

import java.util.Collection;

import com.example.world.entity.Country;

public interface CountryDao extends GenericDao<Country, String> {
	Collection<Country> findByContinent(String continent);

	void test(String code);
}
