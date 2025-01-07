package com.example.world.entity;

import com.example.world.entity.converter.BooleanCharacterConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "countrylanguage")
public class CountryLanguage {
	@EmbeddedId
	private CountryLanguagePK countryLanguagePK;
	private double percentage;
	@Column(name = "isofficial")
	@Convert(converter = BooleanCharacterConverter.class)
	private boolean official;

	@ManyToOne
	@JoinColumn(name = "countrycode", insertable = false, updatable = false, nullable = false)
	@JsonIgnore
	private Country country;

	public CountryLanguage() {
	}

	public CountryLanguagePK getCountryLanguagePK() {
		return countryLanguagePK;
	}

	public void setCountryLanguagePK(CountryLanguagePK countryLanguagePK) {
		this.countryLanguagePK = countryLanguagePK;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public boolean isOfficial() {
		return official;
	}

	public void setOfficial(boolean official) {
		this.official = official;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "CountryLanguage [countryLanguagePK=" + countryLanguagePK + ", percentage=" + percentage + ", official="
				+ official + ", country=" + country.getCode() + "]";
	}

}
