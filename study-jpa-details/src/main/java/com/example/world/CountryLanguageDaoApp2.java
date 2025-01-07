package com.example.world;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.world.config.AppConfig;
import com.example.world.dao.CountryLanguageDao;
import com.example.world.entity.CountryLanguage;
import com.example.world.entity.CountryLanguagePK;

public class CountryLanguageDaoApp2 {

	public static void main(String[] args) {
		ConfigurableApplicationContext container = new AnnotationConfigApplicationContext(AppConfig.class);
		CountryLanguageDao cld = container.getBean(CountryLanguageDao.class);
		CountryLanguagePK key = new CountryLanguagePK("Turkish", "TUR");
		CountryLanguage trTR = cld.find(key);

		System.err.println(trTR.getCountry());
		container.close();
	}

}
