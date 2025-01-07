package com.example.imdb.dao;

import java.util.Collection;

import com.example.imdb.entity.Movie;

public interface MovieDao extends GenericDao<Movie, Integer> {
	Collection<Movie> findMoviesByYearRangeAndGenre(boolean isGenreSelected, String genre, boolean isYearRangeSelected,
			Integer fromYear, Integer toYear);

	int countMoviesByYearRange(int from, int to);
}
