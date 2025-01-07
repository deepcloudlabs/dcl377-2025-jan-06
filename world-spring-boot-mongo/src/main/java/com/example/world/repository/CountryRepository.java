package com.example.world.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.world.document.Country;

public interface CountryRepository extends MongoRepository<Country, String> {
    List<Country> findAllByContinentAndRegion(String continent,String region);
    @Query("{'continent': ?0, 'region': ?1}")
    List<Country> getir(String continent,String region);
    
}
