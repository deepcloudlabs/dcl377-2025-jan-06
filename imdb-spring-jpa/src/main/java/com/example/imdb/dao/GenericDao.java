package com.example.imdb.dao;

import java.util.Collection;

public interface GenericDao<Entity, Key> {
	Entity add(Entity country);

	Entity update(Entity country);

	Entity remove(Key key);

	Entity find(Key key);

	Entity find(Key key, String graphName);

	Collection<Entity> findAll();

}
