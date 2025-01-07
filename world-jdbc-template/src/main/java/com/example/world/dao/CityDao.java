package com.example.world.dao;

import java.util.Collection;

import com.example.world.entity.City;

public interface CityDao extends GenericDao<City, Long> {
	Collection<City> findByCountryCode(String countryCode);
}
