package com.mrretektor.astrasearch.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mrretektor.astrasearch.dao.PlanetDao;
import com.mrretektor.astrasearch.domain.Planet;

@Component
public class PlanetDaoImpl implements PlanetDao {
	private final JdbcTemplate jdbcTemplate;
	
	public PlanetDaoImpl(final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public Planet create(Planet planet, Long celestialBodyId, Long starId) {
		String sql = "INSERT INTO planets (body_id, star_id, orbital_period_days,"
				+ " earth_mean_radius, earth_volume, mean_density, surface_gravity, surface_temperature_kelvin)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
				+ " RETURNING id, body_id, star_id, orbital_period_days, earth_mean_radius, earth_volume,"
				+ " mean_density, surface_gravity, surface_temperature_kelvin";
		
		try {
			return jdbcTemplate.queryForObject(sql,
					new PlanetRowMapper(),
					celestialBodyId,
					starId,
					planet.getOrbitalPeriodDays(),
					planet.getEarthMeanRadius(),
					planet.getEarthVolume(),
					planet.getMeanDensity(),
					planet.getSurfaceGravity(),
					planet.getSurfaceTemperatureKelvin());
		} catch (DataAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static class PlanetRowMapper implements RowMapper<Planet> {
		
		@Override
		public Planet mapRow(ResultSet rs, int rowNum) throws SQLException {
			return Planet.builder()
					.id(rs.getLong("id"))
					.orbitalPeriodDays(rs.getFloat("orbital_period_days"))
					.earthMeanRadius(rs.getFloat("earth_mean_radius"))
					.earthVolume(rs.getFloat("earth_volume"))
					.meanDensity(rs.getFloat("mean_density"))
					.surfaceGravity(rs.getFloat("surface_gravity"))
					.surfaceTemperatureKelvin(rs.getFloat("surface_temperature_kelvin"))
					.build();
		}
	}
}
