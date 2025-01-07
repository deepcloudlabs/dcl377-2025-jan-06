package com.example.world.repository;

import java.util.List;

import com.example.world.dto.ContinentPopulation;

public interface WorldRepository {
	List<ContinentPopulation> getPopulationByContinent();
}
