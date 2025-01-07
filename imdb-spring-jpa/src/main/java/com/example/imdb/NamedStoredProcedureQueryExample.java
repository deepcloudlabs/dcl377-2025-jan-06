package com.example.imdb;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.imdb.config.AppConfig;
import com.example.imdb.dao.MovieDao;

public class NamedStoredProcedureQueryExample {

	public static void main(String[] args) {
		ConfigurableApplicationContext container = new AnnotationConfigApplicationContext(AppConfig.class);
		MovieDao movieDao = container.getBean(MovieDao.class);
		String format = "There are %d moves in years between %d and %d";
		final int fromYear = 1970;
		final int toYear = 1979;
		int movieCount = movieDao.countMoviesByYearRange(fromYear, toYear);
		String countMessage = String.format(format, movieCount,fromYear,toYear);
		System.out.println(countMessage);
		container.close();
	}

}
