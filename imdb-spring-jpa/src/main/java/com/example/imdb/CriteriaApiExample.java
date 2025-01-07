package com.example.imdb;

import java.util.Collection;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.imdb.config.AppConfig;
import com.example.imdb.dao.MovieDao;
import com.example.imdb.entity.Movie;

public class CriteriaApiExample {

	public static void main(String[] args) {
		ConfigurableApplicationContext container = new AnnotationConfigApplicationContext(AppConfig.class);
		MovieDao movieDao = container.getBean(MovieDao.class);
		Collection<Movie> movies = movieDao.findMoviesByYearRangeAndGenre(true, "Drama", false, null, null);
		movies.forEach(System.out::println);
		container.close();
	}

}
