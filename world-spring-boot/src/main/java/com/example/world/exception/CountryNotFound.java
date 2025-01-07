package com.example.world.exception;

@SuppressWarnings("serial")
public class CountryNotFound extends BaseException {

	public CountryNotFound(String message, String i18nId, String debugId) {
		super(message, i18nId, debugId);
	}

}
