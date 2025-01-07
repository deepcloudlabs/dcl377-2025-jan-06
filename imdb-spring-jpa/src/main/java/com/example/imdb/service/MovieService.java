package com.example.imdb.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.imdb.dao.MovieDao;
import com.example.imdb.entity.Director;
import com.example.imdb.entity.Genre;
import com.example.imdb.entity.Movie;

@Service
public class MovieService {
	@Autowired
	private MovieDao movieDao;

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public void printMovieInfo(int id) {
		Movie movie = movieDao.find(id);
		if (Objects.isNull(movie))
			throw new IllegalArgumentException("Cannot find the movie!");
		System.err.println(movie);
		for (Genre genre : movie.getGenres())
			System.err.println(genre.getName());
		for (Director director : movie.getDirectors())
			System.err.println(director.getName());
	}
}
