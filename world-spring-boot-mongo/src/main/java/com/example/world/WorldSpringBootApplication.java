package com.example.world;

import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;

import com.example.world.repository.CountryRepository;

@SpringBootApplication
public class WorldSpringBootApplication implements ApplicationRunner{
	private final CountryRepository countryRepository;
	private final MongoTemplate mongoTemplate;
	
	public WorldSpringBootApplication(CountryRepository countryRepository, MongoTemplate mongoTemplate) {
		this.countryRepository = countryRepository;
		this.mongoTemplate = mongoTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(WorldSpringBootApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		countryRepository.findAll(PageRequest.of(0, 100))
		                 .forEach(System.out::println);
		getPopulationByContinent().forEach(System.out::println);	
	}


    public List<ContinentPopulation> getPopulationByContinent() {
        // Group by 'continent' and sum the 'population'
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("continent").sum("population").as("totalPopulation").count().as("numberOfCountries"),
                Aggregation.project("totalPopulation").and("continent").previousOperation()
        );

        // Perform the aggregation query
        AggregationResults<ContinentPopulation> results = mongoTemplate.aggregate(
                aggregation, "countries1", ContinentPopulation.class);

        return results.getMappedResults();
    }
}

record ContinentPopulation(String continent, long totalPopulation,int numberOfCountries) {}
