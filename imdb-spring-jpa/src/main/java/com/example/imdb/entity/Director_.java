package com.example.imdb.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-11-19T14:51:58.831+0200")
@StaticMetamodel(Director.class)
public class Director_ {
	public static volatile SingularAttribute<Director, Integer> id;
	public static volatile SingularAttribute<Director, String> imdb;
	public static volatile SingularAttribute<Director, String> name;
	public static volatile ListAttribute<Director, Movie> movies;
}
