package com.example.world.repository.template;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Repository;

import com.example.world.dto.ContinentPopulation;
import com.example.world.repository.WorldRepository;

@Repository
public class MongoTemplateWorldRepository implements WorldRepository {

	private final MongoTemplate mongoTemplate;

	public MongoTemplateWorldRepository(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
    public List<ContinentPopulation> getPopulationByContinent() {
        // Group by 'continent' and sum the 'population'
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("continent").sum("population").as("totalPopulation"),
                Aggregation.project("totalPopulation").and("continent").previousOperation()
        );

        // Perform the aggregation query
        AggregationResults<ContinentPopulation> results = mongoTemplate.aggregate(
                aggregation, "countries1", ContinentPopulation.class);

        return results.getMappedResults();
    }

}
