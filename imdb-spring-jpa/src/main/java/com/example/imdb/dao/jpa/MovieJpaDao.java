package com.example.imdb.dao.jpa;

import static java.util.Objects.nonNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.imdb.dao.MovieDao;
import com.example.imdb.entity.Genre;
import com.example.imdb.entity.Genre_;
import com.example.imdb.entity.Movie;
import com.example.imdb.entity.Movie_;

@Repository
public class MovieJpaDao implements MovieDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public Movie add(Movie country) {
		country.setId(null);
		entityManager.persist(country);
		return country;
	}

	@Override
	@Transactional
	public Movie update(Movie movie) {
		int key = movie.getId();
		Movie found = entityManager.find(Movie.class, key);
		if (Objects.isNull(found))
			throw new IllegalArgumentException("Movie does not exist!");
		found.setGenres(movie.getGenres());
		found.setYear(movie.getYear());
		return found;
	}

	@Override
	public Movie remove(Integer key) {
		Movie movie = entityManager.find(Movie.class, key);
		if (nonNull(movie)) {
			entityManager.remove(movie);
			return movie;
		}
		throw new IllegalArgumentException("Movie does not exist!");
	}

	@Override
	public Movie find(Integer key) {
		return entityManager.find(Movie.class, key);
	}

	@Override
	public Movie find(Integer key, String graphName) {
		EntityGraph<?> eg = entityManager.getEntityGraph(graphName);
		Map<String, Object> props = new HashMap<>();
		props.put("javax.persistence.fetchgraph", eg);
		return entityManager.find(Movie.class, key, props);
	}

	@Override
	public Collection<Movie> findAll() {
		Object count = entityManager.createNativeQuery("select count(*) from movies").getSingleResult();
		int numberOfRecords = Integer.parseInt(count.toString());
		System.err.println("Number of movies : " + numberOfRecords);
		// Integer numberOfRecords=
		// Integer.parseInt(em.createNamedQuery("countMovie").getSingleResult().toString());
		System.err.println("Result: " + numberOfRecords);
		List<Movie> movies = entityManager.createNamedQuery("fromMovie.all", Movie.class).setFirstResult(0)
				.setMaxResults(20).getResultList();
		return movies;
	}

	@Override
	public Collection<Movie> findMoviesByYearRangeAndGenre(boolean isGenreSelected, String genre,
			boolean isYearRangeSelected, Integer fromYear, Integer toYear) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Movie> query = criteriaBuilder.createQuery(Movie.class);
		Root<Movie> root = query.from(Movie.class);
		if (!isYearRangeSelected) {
			fromYear = Integer.MIN_VALUE;
			toYear = Integer.MAX_VALUE;
		}
		Predicate condition = criteriaBuilder.and(
				criteriaBuilder.greaterThanOrEqualTo(root.get(Movie_.year).as(Integer.class), fromYear),
				criteriaBuilder.lessThanOrEqualTo(root.get(Movie_.year).as(Integer.class), toYear));
		if (isGenreSelected) {
			Join<Movie, Genre> join = root.join(Movie_.genres);
			condition = criteriaBuilder.and(condition, criteriaBuilder.equal(join.get(Genre_.name), genre));
		}
		query.select(root).where(condition);
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public int countMoviesByYearRange(int from, int to) {
		StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("Movie.getYearRange")
				.setParameter("fromyear", from).setParameter("toyear", to);
		query.execute();
		return (Integer) query.getOutputParameterValue("moviecount");
	}

}
