package com.example.imdb.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-11-19T14:51:58.881+0200")
@StaticMetamodel(Movie.class)
public class Movie_ {
	public static volatile SingularAttribute<Movie, Integer> id;
	public static volatile SingularAttribute<Movie, String> title;
	public static volatile SingularAttribute<Movie, String> imdb;
	public static volatile SingularAttribute<Movie, Integer> year;
	public static volatile ListAttribute<Movie, Director> directors;
	public static volatile ListAttribute<Movie, Genre> genres;
}
