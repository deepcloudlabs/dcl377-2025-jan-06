package com.example.world.exception;

@SuppressWarnings("serial")
public class CountryAlreadyExists extends BaseException {

	public CountryAlreadyExists(String message, String i18nId, String debugId) {
		super(message, i18nId, debugId);
	}

}
