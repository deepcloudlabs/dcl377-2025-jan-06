package com.example.world.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.example.world.entity.converter.BooleanCharacterConverter;

@Entity
public class CountryLanguage {
	@EmbeddedId
	private CountryLanguagePK countryLanguagePK;
	@Column(name = "isOfficial")
	@Convert(converter = BooleanCharacterConverter.class)
	private boolean official;
	private double percentage;

	@OneToOne()
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "countrycode", insertable = false, updatable = false, nullable = false)
	private Country country;

	public CountryLanguage() {
	}

	public CountryLanguagePK getCountryLanguagePK() {
		return countryLanguagePK;
	}

	public void setCountryLanguagePK(CountryLanguagePK countryLanguagePK) {
		this.countryLanguagePK = countryLanguagePK;
	}

	public boolean isOfficial() {
		return official;
	}

	public void setOfficial(boolean official) {
		this.official = official;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((countryLanguagePK == null) ? 0 : countryLanguagePK.hashCode());
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
		CountryLanguage other = (CountryLanguage) obj;
		if (countryLanguagePK == null) {
			if (other.countryLanguagePK != null)
				return false;
		} else if (!countryLanguagePK.equals(other.countryLanguagePK))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CountryLanguage [countryLanguagePK=" + countryLanguagePK + ", official=" + official + ", percentage="
				+ percentage + "]";
	}

}
