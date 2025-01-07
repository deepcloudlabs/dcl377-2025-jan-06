package com.example.world.entity;

import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/* 
DELIMITER //
CREATE PROCEDURE continent_countries_capital
(IN cont CHAR(20))
BEGIN
  SELECT co.code, co.Name as "Name", ci.id, ci.Name as "Capital" FROM Country as co, City ci
  WHERE co.Continent = cont AND co.capital=ci.id;
END //
DELIMITER ;
*/
@Entity()
@Table(name = "country")
@Access(AccessType.FIELD)
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
		name="ContinentCapitals",
		procedureName="continent_countries_capital",
		parameters= {
			@StoredProcedureParameter(
				mode = ParameterMode.IN, 
				type = String.class, name = "cont"
			)	
		}
	)
})
@SqlResultSetMapping(
 name="CountryCapitalMapping",
 entities = {
	@EntityResult(
		entityClass= Country.class,
		fields= {
			@FieldResult(name="code", column="code"),	
			@FieldResult(name="name", column="name")	
		}
	), 
	@EntityResult(
		entityClass= City.class,
		fields= {
			@FieldResult(name="id", column="id"),	
			@FieldResult(name="capitalname", column="capitalname")	
		}
	) 
 }
)
@NamedQueries({ 
	@NamedQuery(
		name = "Country.findAll", 
		query = "select c from Country c"
	),
	@NamedQuery(
		name = "Country.findByContinent", 
		query = "select c from Country c where c.continent=:continent"
	),
	@NamedQuery(
		name = "DistinctContinents", 
		query = "select distinct c.continent from Country c"
	) 
})
@DynamicUpdate
@DynamicInsert
@NamedEntityGraphs({
	@NamedEntityGraph(
		name = "graph.Country.cities", 
		attributeNodes = @NamedAttributeNode(value = "cities",subgraph = "cities"), 
		subgraphs = @NamedSubgraph(name = "cities",attributeNodes = @NamedAttributeNode("country"))
	),
	@NamedEntityGraph(
		name = "graph.Country.citylangs", 
		attributeNodes = {
			@NamedAttributeNode(value = "cities", subgraph = "cities"),
			@NamedAttributeNode(value = "languages", subgraph = "languages") 
		}, 
		subgraphs = {
			@NamedSubgraph(name = "cities", attributeNodes = @NamedAttributeNode("country")),
			@NamedSubgraph(name = "languages", attributeNodes = @NamedAttributeNode("country")) 
		}
	),
	@NamedEntityGraph(
		name = "graph.Country.languages", 
		attributeNodes = @NamedAttributeNode(value = "languages", subgraph = "languages"), 
		subgraphs = @NamedSubgraph(name = "languages", attributeNodes = @NamedAttributeNode("country"))) 
})
public class Country {
	@Id
	@Column(name = "code")
	private String code;
	@Column(name = "name")
	private String name;
	@Column(name = "continent")
	private String continent;
	@Column(name = "surfacearea")
	private double surfaceArea; 
	@Column(name = "population")
	private int population;  
	@OneToOne
	@JoinColumn(name = "capital")
	private City capital;
	@OneToMany(mappedBy = "country")
	private Set<City> cities;
	@OneToMany(mappedBy = "country")
	private Set<CountryLanguage> languages;

	public Country() {
	}

	public Country(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public double getSurfaceArea() {
		return surfaceArea;
	}

	public void setSurfaceArea(double surfaceArea) {
		this.surfaceArea = surfaceArea;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public City getCapital() {
		return capital;
	}

	public void setCapital(City capital) {
		this.capital = capital;
	}

	public Set<City> getCities() {
		return cities;
	}

	public void setCities(Set<City> cities) {
		this.cities = cities;
	}

	public Set<CountryLanguage> getLanguages() {
		return languages;
	}

	public void setLanguages(Set<CountryLanguage> languages) {
		this.languages = languages;
	}

	@Override
	public String toString() {
		return "Country [code=" + code + ", name=" + name + ", continent=" + continent + ", surfaceArea=" + surfaceArea
				+ ", population=" + population + "]";
	}

}