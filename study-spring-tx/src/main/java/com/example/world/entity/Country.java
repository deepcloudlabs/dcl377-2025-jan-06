package com.example.world.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity()
@Table(name = "country")
@Access(AccessType.FIELD)
@NamedQueries({ @NamedQuery(name = "Country.findAll", query = "select c from Country c" // JPQL
		),
		@NamedQuery(name = "Country.findByContinent", query = "select c from Country c "
				+ "where c.continent=:continent"),
		@NamedQuery(name = "DistinctContinents", query = "select distinct c.continent from Country c") })
public class Country {
	@Id
	@Column(name = "code")
	private String code;
	@Column(name = "name")
	private String name;
	@Column(name = "continent")
	private String continent;
	@Column(name = "surfacearea")
	private double surfaceArea; // double ? Double
	@Column(name = "population")
	private int population; // int ? Integer

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

	@Override
	public String toString() {
		return "Country [code=" + code + ", name=" + name + ", continent=" + continent + ", surfaceArea=" + surfaceArea
				+ ", population=" + population + "]";
	}

}
