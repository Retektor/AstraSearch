package com.mrretektor.astrasearch.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.mrretektor.astrasearch.dao.StarDao;
import com.mrretektor.astrasearch.domain.Star;

@Component
public class StarDaoImpl implements StarDao {
	private final JdbcTemplate jdbcTemplate;
		
	public StarDaoImpl(final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public Star create(Star star, Long celestialBodyId) {
		String sql = "INSERT INTO stars (body_id, constellation, apparent_magnitude, absolute_magnitude,"
	            + " mass_solar, radius_solar, luminosity_solar, temperature, spectral_class)"
	            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
	            + " RETURNING id, body_id, constellation, apparent_magnitude, absolute_magnitude,"
	            + " mass_solar, radius_solar, luminosity_solar, temperature, spectral_class";
		
		try {
			return jdbcTemplate.queryForObject(sql,
					new StarRowMapper(),
					celestialBodyId,
					star.getConstellation(),
					star.getApparentMagnitude(),
					star.getAbsoluteMagnitude(),
					star.getMassSolar(),
					star.getRadiusSolar(),
					star.getLuminositySolar(),
					star.getTemperature(),
					star.getSpectralClass()
					);
		} catch (DataAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public static class StarRowMapper implements RowMapper<Star> {
		
		@Override
		public Star mapRow(ResultSet rs, int rowNum) throws SQLException {
			return Star.builder()
					.id(rs.getLong("id"))
					.constellation(rs.getString("constellation"))
					.apparentMagnitude(rs.getBigDecimal("apparent_magnitude"))
					.absoluteMagnitude(rs.getBigDecimal("absolute_magnitude"))
					.massSolar(rs.getBigDecimal("mass_solar"))
					.radiusSolar(rs.getBigDecimal("radius_solar"))
					.luminositySolar(rs.getBigDecimal("luminosity_solar"))
					.temperature(rs.getFloat("temperature"))
					.spectralClass(rs.getString("spectral_class"))
					.build();
		}
	}
}
