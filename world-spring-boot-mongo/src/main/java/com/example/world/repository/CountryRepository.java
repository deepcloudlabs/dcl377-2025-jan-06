package com.example.world.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.world.document.Country;

public interface CountryRepository extends MongoRepository<Country, String> {
}
