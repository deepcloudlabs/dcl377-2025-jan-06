package com.example.world.dao.template;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.world.dao.CityDao;
import com.example.world.dao.CountryDao;
import com.example.world.entity.City;
import com.example.world.entity.Country;
import com.example.world.exception.EntityNotFoundException;

@Repository
public class JdbcTemplateCountryDao implements CountryDao {
	private static final String SELECT_COUNTRY_BY_CODE = "SELECT * FROM COUNTRY WHERE CODE=?";
	private static final String SELECT_COUNTRIES_BY_CONTINENT = "SELECT * FROM COUNTRY WHERE CONTINENT=?";
	private static final String SELECT_ALL_COUNTRIES = "SELECT * FROM COUNTRY";
	private static final String INSERT_COUNTRY = "INSERT INTO COUNTRY(CODE,NAME,CONTINENT,POPULATION,SURFACEAREA) "
			+ "VALUES(?,?,?,?,?)";
	private static final String UPDATE_COUNTRY = "UPDATE COUNTRY SET POPULATION=?, SURFACEAREA=? " + "WHERE CODE=?";
	private static final String DELETE_COUNTRY = "DELETE FROM COUNTRY WHERE CODE=?";
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private CityDao cityDao;

	@Override
	public Optional<Country> findOne(String code) {
		Country country = jdbcTemplate.queryForObject(SELECT_COUNTRY_BY_CODE, new Object[] { code },
				new BeanPropertyRowMapper<Country>(Country.class));
		Collection<City> cities = cityDao.findByCountryCode(code);
		cities.forEach(city -> city.setCountry(country));
		country.setCities(cities);
		return Optional.ofNullable(country);
	}

	@Override
	public Collection<Country> findAll(int pageNo, int pageSize) {
		List<Country> countries = jdbcTemplate.query(SELECT_ALL_COUNTRIES, new Object[] {},
				new BeanPropertyRowMapper<Country>(Country.class));
		countries.forEach(country -> country.setCities(cityDao.findByCountryCode(country.getCode())));
		return countries;
	}

	public Country add(Country country) {
		int rowsAffected = jdbcTemplate.update(INSERT_COUNTRY, new Object[] { country.getCode(), country.getName(),
				country.getContinent(), country.getPopulation(), country.getSurfaceArea() });
		System.err.println(String.format("%d rows affected.", rowsAffected));
		return country;
	}
    @Transactional(isolation=Isolation.READ_COMMITTED,propagation = Propagation.MANDATORY)
	public Country update(Country country) {
		String code = country.getCode();
		Optional<Country> existing = findOne(code);
		if (!existing.isPresent())
			throw new EntityNotFoundException("Country does not exist");
		jdbcTemplate.update(UPDATE_COUNTRY,
				new Object[] { country.getPopulation(), country.getSurfaceArea(), country.getCode() });
		return country;
	}

	public Optional<Country> remove(String code) {
		Optional<Country> country = findOne(code);
		if (country.isPresent()) {
			jdbcTemplate.update(DELETE_COUNTRY, new Object[] { code });
		}
		return country;
	}

	public Collection<Country> getByContinent(String continent) {
		return jdbcTemplate.query(SELECT_COUNTRIES_BY_CONTINENT, new Object[] { continent },
				new BeanPropertyRowMapper<Country>(Country.class));
	}

	@Override
	public Set<String> getContinents() {
		return new HashSet<>(jdbcTemplate.queryForList("SELECT DISTINCT CONTINENT FROM COUNTRY", String.class));
	}

}
