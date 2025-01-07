package com.example.world.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class CountryLanguagePK implements Serializable {
	@Column(nullable = false)
	private String language;
	@Column(name = "countrycode", nullable = false)
	private String code;

	public CountryLanguagePK() {
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "CountryLanguagePK [language=" + language + ", code=" + code + "]";
	}

}
