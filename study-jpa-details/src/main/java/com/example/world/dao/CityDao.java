package com.example.world.dao;

import java.util.Collection;

import com.example.world.entity.City;

public interface CityDao extends GenericDao<City, Integer> {
	Collection<City> findCitiesByCountry(String code);
}
