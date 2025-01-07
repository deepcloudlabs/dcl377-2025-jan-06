package com.example.world.entity;

public class CountryWithCapital {
	private String code;
	private String name;
	private Long cityId;
	private String capital;

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

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	@Override
	public String toString() {
		return "CountryWithCapital [code=" + code + ", name=" + name + ", capital=" + capital + "]";
	}

}
