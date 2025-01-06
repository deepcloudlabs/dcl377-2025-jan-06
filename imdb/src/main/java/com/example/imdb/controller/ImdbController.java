package com.example.imdb.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.imdb.entity.Movie;
import com.example.imdb.service.MovieService;

@RestController
@RequestScope
@RequestMapping("/movies")
@Validated
@CrossOrigin
public class ImdbController {
	private final MovieService movieService;
	
	public ImdbController(MovieService movieService) {
		this.movieService = movieService;
		System.err.println("ImdbController is created");
	}

	@GetMapping(produces = {"application/xml", "application/json"},value="/{id}")
	public Movie getMovieByIb(@PathVariable int id) {
		return movieService.findMovieById(id);
	}

	@GetMapping(produces = {"application/xml", "application/json"}, params= {"pageno", "pagesize"})
	public List<Movie> getMovies(@RequestParam int pageno,@RequestParam int pagesize) {
		return movieService.findAllMovies()
				           .stream()
				           .skip(pageno*pagesize)
				           .limit(pagesize)
				           .toList();
	}
}
