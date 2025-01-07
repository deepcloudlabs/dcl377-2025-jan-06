package com.example.world.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
@NamedQueries({ @NamedQuery(name = "AllFromCountry", query = "select c from Country c"),
		@NamedQuery(name = "ByContinentFromCountry", query = "select c from Country c where c.continent=:continent") })
@NamedEntityGraphs({
		@NamedEntityGraph(name = "graph.Country.cities", attributeNodes = @NamedAttributeNode(value = "cities", subgraph = "cities"), subgraphs = @NamedSubgraph(name = "cities", attributeNodes = @NamedAttributeNode("country"))),
		@NamedEntityGraph(name = "graph.Country.citylangs", attributeNodes = {
				@NamedAttributeNode(value = "cities", subgraph = "cities"),
				@NamedAttributeNode(value = "languages", subgraph = "languages") }, subgraphs = {
						@NamedSubgraph(name = "cities", attributeNodes = @NamedAttributeNode("country")),
						@NamedSubgraph(name = "languages", attributeNodes = @NamedAttributeNode("country")), }),
		@NamedEntityGraph(name = "graph.Country.languages", attributeNodes = @NamedAttributeNode(value = "languages", subgraph = "languages"), subgraphs = @NamedSubgraph(name = "languages", attributeNodes = @NamedAttributeNode("country"))) })
public class Country implements Serializable {
	@Id
	private String code;
	private String name;
	private int population;
	@Column(name = "surfacearea")
	private double surfaceArea;
	private String continent;

	@JoinColumn(name = "capital", nullable = false, updatable = false, insertable = false)
	@OneToOne(cascade = { CascadeType.MERGE , CascadeType.REMOVE})
	private City capital;

	@OneToMany(mappedBy = "country", cascade = { CascadeType.MERGE, CascadeType.REMOVE })
	private Set<City> cities;

	@OneToMany(mappedBy = "country", cascade = { CascadeType.MERGE, CascadeType.REMOVE })
	private Set<CountryLanguage> languages;

	public Country() {
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

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public double getSurfaceArea() {
		return surfaceArea;
	}

	public void setSurfaceArea(double surfaceArea) {
		this.surfaceArea = surfaceArea;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Country [code=" + code + ", name=" + name + ", population=" + population + ", surfaceArea="
				+ surfaceArea + ", continent=" + continent + "]";
	}

}
