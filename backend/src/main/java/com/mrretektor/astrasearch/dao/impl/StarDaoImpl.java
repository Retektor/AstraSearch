package com.mrretektor.astrasearch.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
	public Star create(Star star) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(connection -> {
	        PreparedStatement ps = connection.prepareStatement(
	            "INSERT INTO stars (constellation, apparent_magnitude, absolute_magnitude,"
	            + " mass_solar, radius_solar, luminosity_solar, temperature, spectral_class)"
	            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
	            new String[]{"id"}
	        );
	        
	        ps.setString(1, star.getConstellation());
	        ps.setBigDecimal(2, star.getApparentMagnitude());
	        ps.setBigDecimal(3, star.getAbsoluteMagnitude());
	        ps.setBigDecimal(4, star.getMassSolar());
	        ps.setBigDecimal(5, star.getRadiusSolar());
	        ps.setBigDecimal(6, star.getLuminositySolar());
	        ps.setFloat(7, star.getTemperature());
	        ps.setString(8, star.getSpectralClass());
	        
	        return ps;
	    }, keyHolder);
	    
	    Long starId = keyHolder.getKey().longValue();
		
		Star createdStar = jdbcTemplate.query("SELECT * FROM stars WHERE id = ?",
				new StarRowMapper(),
				starId).getFirst();
		
		return createdStar;
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
