package com.example.world;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.world.config.AppConfig;
import com.example.world.dao.CountryLanguageDao;
import com.example.world.entity.CountryLanguage;
import com.example.world.entity.CountryLanguagePK;

@SuppressWarnings("unused")
public class CountryLanguageDaoApp {

	public static void main(String[] args) {
		ConfigurableApplicationContext container = new AnnotationConfigApplicationContext(AppConfig.class);
		CountryLanguageDao countryLanguageDao = container.getBean(CountryLanguageDao.class);
		Collection<CountryLanguage> countryLanguages = countryLanguageDao.findAll();

		listOfficialLanguagesForEach(countryLanguages);
		System.err.println("======================");
		// listOfficialLanguages(isOfficial, countryLanguages);
		Function<CountryLanguage, String> groupByCountryCode = cl -> cl.getCountryLanguagePK().getCode();
		Map<String, List<CountryLanguage>> languages = countryLanguages.stream().parallel()
				.collect(Collectors.groupingBy(groupByCountryCode));
		for (Entry<String, List<CountryLanguage>> entry : languages.entrySet()) {
			System.err.println(entry.getKey());
			for (CountryLanguage countryLanguage : entry.getValue()) {
				System.err.println(
						countryLanguage.getCountryLanguagePK().getLanguage() + "  " + countryLanguage.getPercentage());
			}
			System.err.println();
		}
		container.close();
	}

	private static void listOfficialLanguagesForEach(Collection<CountryLanguage> countryLanguages) {
		for (CountryLanguage cl : countryLanguages)
			if (cl.isOfficial()) {
				CountryLanguagePK countryLanguagePK = cl.getCountryLanguagePK();
				String code = countryLanguagePK.getCode();
				String language = countryLanguagePK.getLanguage();
				System.err.println(code + " : " + language);
			}
	}

	private static void listOfficialLanguages(Predicate<CountryLanguage> isOfficial,
			Collection<CountryLanguage> countryLanguages) {
		countryLanguages.stream()
				.filter(isOfficial).map(CountryLanguage::getCountryLanguagePK)
				.forEach(System.err::println);
	}

}
