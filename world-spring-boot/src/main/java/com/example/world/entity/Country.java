package com.example.world.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.example.world.validation.CountryCode;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedEntityGraphs;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@Entity()
@Table(name = "country")
@Access(AccessType.FIELD)
@NamedEntityGraphs({
		@NamedEntityGraph(name = "graph.Country.cities", attributeNodes = @NamedAttributeNode(value = "cities", subgraph = "cities"), subgraphs = @NamedSubgraph(name = "cities", attributeNodes = @NamedAttributeNode("country"))),
		@NamedEntityGraph(name = "graph.Country.citylangs", attributeNodes = {
				@NamedAttributeNode(value = "cities", subgraph = "cities"),
				@NamedAttributeNode(value = "languages", subgraph = "languages") }, subgraphs = {
						@NamedSubgraph(name = "cities", attributeNodes = @NamedAttributeNode("country")),
						@NamedSubgraph(name = "languages", attributeNodes = @NamedAttributeNode("country")) }),
		@NamedEntityGraph(name = "graph.Country.languages", attributeNodes = @NamedAttributeNode(value = "languages", subgraph = "languages"), subgraphs = @NamedSubgraph(name = "languages", attributeNodes = @NamedAttributeNode("country"))) })
@NamedQueries({ @NamedQuery(name = "Country.findAll", query = "select c from Country c"),
		@NamedQuery(name = "Country.findByContinent", query = "select c from Country c where c.continent=:continent"),
		@NamedQuery(name = "DistinctContinents", query = "select distinct c.continent from Country c") })
@DynamicUpdate
@DynamicInsert
public class Country {
	@Id
	@Column(name = "code")
	@CountryCode
	private String code;
	@Column(name = "name")
	@Size(min=3,message="Country name must have at least 3 chars.")
	@JsonProperty("ad")
	private String name;
	@Column(name = "continent")
	private String continent;
	@Column(name = "surfacearea")
	private double surfaceArea; // double ? Double
	@Column(name = "population")
	@Min(0)
	private int population; // int ? Integer
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "capital")
	private City capital;
	@OneToMany(mappedBy = "country", cascade = { CascadeType.REMOVE })
	private List<City> cities;
	@OneToMany(mappedBy = "country", cascade = { CascadeType.REMOVE })
	private Set<CountryLanguage> languages;
	private Date created;
	private Date updated;

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

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public Set<CountryLanguage> getLanguages() {
		return languages;
	}

	public void setLanguages(Set<CountryLanguage> languages) {
		this.languages = languages;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@PrePersist
	public void countryCreated() {
		this.created = new Date();
		this.updated = new Date();
	}

	@PreUpdate
	public void countryUpdated() {
		this.updated = new Date();
	}

	@Override
	public String toString() {
		return "Country [code=" + code + ", name=" + name + ", continent=" + continent + ", surfaceArea=" + surfaceArea
				+ ", population=" + population + "]";
	}

}
