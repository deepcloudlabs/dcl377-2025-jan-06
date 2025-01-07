package com.example.world;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.world.config.AppConfig;
import com.example.world.dao.CountryDao;
import com.example.world.entity.Country;
import com.example.world.entity.CountryLanguage;
import com.example.world.entity.CountryLanguagePK;

public class CountryLanguageDaoApp3 {

	public static void main(String[] args) {
		ConfigurableApplicationContext container = new AnnotationConfigApplicationContext(AppConfig.class);
		CountryDao countryDao = container.getBean(CountryDao.class);
		
		// this causes lazy initialization problem
		Country turkey1 = countryDao.find("TUR");
		try {
			turkey1.getLanguages().stream()
				.map(CountryLanguage::getCountryLanguagePK)
				.map(CountryLanguagePK::getLanguage)
				.forEach(System.err::println);			
		}catch(Exception e) {System.err.println(e.getMessage());}
		
		// solution: use entity graph
		Country turkey2 = countryDao.find("TUR","graph.Country.languages"); 
		turkey2.getLanguages().stream()
		      .map(CountryLanguage::getCountryLanguagePK)
		      .map(CountryLanguagePK::getLanguage)
			  .forEach(System.err::println);
		container.close();
	}

}
