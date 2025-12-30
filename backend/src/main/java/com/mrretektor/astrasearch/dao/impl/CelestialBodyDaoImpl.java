package com.mrretektor.astrasearch.dao.impl;

import java.sql.PreparedStatement;
import java.sql.Types;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mrretektor.astrasearch.dao.CelestialBodyDao;
import com.mrretektor.astrasearch.domain.CelestialBody;

@Component
public class CelestialBodyDaoImpl implements CelestialBodyDao{
	private final JdbcTemplate jdbcTemplate;
	
	public CelestialBodyDaoImpl(final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public void create(CelestialBody body) {
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(
			"INSERT INTO celestial_bodies (name, description, body_type, discovery_time,"
				+ " image_id, right_ascension, declination)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, body.getName());
			ps.setString(2, body.getDescription());
			if (body.getBodyType() != null) {
	            ps.setObject(3, body.getBodyType().name(), Types.OTHER);
	        } else {
	            ps.setNull(3, Types.OTHER);
	        }
	        
	        if (body.getDiscoveryTime() != null) {
	            ps.setTimestamp(4, body.getDiscoveryTime());
	        } else {
	            ps.setNull(4, Types.TIMESTAMP);
	        }
	        
	        if (body.getImageId() != null) {
	            ps.setLong(5, body.getImageId());
	        } else {
	            ps.setNull(5, Types.BIGINT);
	        }
	        
	        if (body.getRightAscension() != null) {
	            ps.setBigDecimal(6, body.getRightAscension());
	        } else {
	            ps.setNull(6, Types.NUMERIC);
	        }
	        
	        if (body.getDeclination() != null) {
	            ps.setBigDecimal(7, body.getDeclination());
	        } else {
	            ps.setNull(7, Types.NUMERIC);
	        }
			
			return ps;
		});
	}
}
