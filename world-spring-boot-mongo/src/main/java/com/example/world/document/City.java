package com.example.world.document;

import java.util.Objects;

import org.springframework.data.mongodb.core.mapping.Field;

public class City {
	private String district;
	@Field("_id")
	private Integer id;
	private String name;
	private Integer population;

	public City() {
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPopulation() {
		return population;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "City [district=" + district + ", id=" + id + ", name=" + name + ", population=" + population + "]";
	}

}
