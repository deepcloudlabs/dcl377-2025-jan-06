package com.example.imdb.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-11-19T14:51:58.879+0200")
@StaticMetamodel(Genre.class)
public class Genre_ {
	public static volatile SingularAttribute<Genre, Integer> id;
	public static volatile SingularAttribute<Genre, String> name;
	public static volatile ListAttribute<Genre, Movie> movies;
}
