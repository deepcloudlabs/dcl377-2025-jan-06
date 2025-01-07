package com.example.world.document;

import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "countries1")
public class Country {
	@Id
	private String code;
	private int capital;
	private List<City> cities;
	private String continent;
	private Double gnp;
	private String governmentForm;
	private String headOfState;
	@Field("indepYear")
	private Integer independenceYear;
	private Double lifeExpectancy;
	private String localName;
	@Indexed
	private String name;
	private Integer population;
	private String region;
	private Double surfaceArea;

	public Country() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getCapital() {
		return capital;
	}

	public void setCapital(int capital) {
		this.capital = capital;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public Double getGnp() {
		return gnp;
	}

	public void setGnp(Double gnp) {
		this.gnp = gnp;
	}

	public String getGovernmentForm() {
		return governmentForm;
	}

	public void setGovernmentForm(String governmentForm) {
		this.governmentForm = governmentForm;
	}

	public String getHeadOfState() {
		return headOfState;
	}

	public void setHeadOfState(String headOfState) {
		this.headOfState = headOfState;
	}

	public Integer getIndepYear() {
		return independenceYear;
	}

	public void setIndepYear(Integer indepYear) {
		this.independenceYear = indepYear;
	}

	public Double getLifeExpectancy() {
		return lifeExpectancy;
	}

	public void setLifeExpectancy(Double lifeExpectancy) {
		this.lifeExpectancy = lifeExpectancy;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
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

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Double getSurfaceArea() {
		return surfaceArea;
	}

	public void setSurfaceArea(Double surfaceArea) {
		this.surfaceArea = surfaceArea;
	}

	@Override
	public int hashCode() {
		return Objects.hash(code);
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
		return Objects.equals(code, other.code);
	}

	@Override
	public String toString() {
		return "Country [code=" + code + ", capital=" + capital + ", cities=" + cities + ", continent=" + continent
				+ ", gnp=" + gnp + ", governmentForm=" + governmentForm + ", headOfState=" + headOfState
				+ ", indepYear=" + independenceYear + ", lifeExpectancy=" + lifeExpectancy + ", localName=" + localName
				+ ", name=" + name + ", population=" + population + ", region=" + region + ", surfaceArea="
				+ surfaceArea + "]";
	}

}
