package com.mrretektor.astrasearch.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(connection ->{
			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO planets (body_id, star_id, orbital_period_days,"
					+ " earth_mean_radius, earth_volume, mean_density, surface_gravity, surface_temperature_kelvin)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
					new String[]{"id"}
				);
			
			ps.setLong(1, celestialBodyId);
			ps.setLong(2, starId);
			ps.setFloat(3, planet.getOrbitalPeriodDays());
			ps.setFloat(4, planet.getEarthMeanRadius());
			ps.setFloat(5, planet.getEarthVolume());
			ps.setFloat(6, planet.getMeanDensity());
			ps.setFloat(7, planet.getSurfaceGravity());
			ps.setFloat(8, planet.getSurfaceTemperatureKelvin());
			
			return ps;
		}, keyHolder);
		
		Long planetId = keyHolder.getKey().longValue();
		
		Planet createdPlanet = jdbcTemplate.query("SELECT * FROM planets WHERE id = ?",
				new PlanetRowMapper(),
				planetId).getFirst();
		
		return createdPlanet;
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
