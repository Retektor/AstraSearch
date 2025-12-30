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
	public void create(Long userId, CelestialBody body) {
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(
			"INSERT INTO celestial_bodies (user_id, name, description, body_type, discovery_time,"
				+ " image_id, right_ascension, declination)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setLong(1, userId);
			ps.setString(2, body.getName());
			ps.setString(3, body.getDescription());
			if (body.getBodyType() != null) {
	            ps.setObject(4, body.getBodyType().name(), Types.OTHER);
	        } else {
	            ps.setNull(4, Types.OTHER);
	        }
	        
	        if (body.getDiscoveryTime() != null) {
	            ps.setTimestamp(5, body.getDiscoveryTime());
	        } else {
	            ps.setNull(5, Types.TIMESTAMP);
	        }
	        
	        if (body.getImageId() != null) {
	            ps.setLong(6, body.getImageId());
	        } else {
	            ps.setNull(6, Types.BIGINT);
	        }
	        
	        if (body.getRightAscension() != null) {
	            ps.setBigDecimal(7, body.getRightAscension());
	        } else {
	            ps.setNull(7, Types.NUMERIC);
	        }
	        
	        if (body.getDeclination() != null) {
	            ps.setBigDecimal(8, body.getDeclination());
	        } else {
	            ps.setNull(8, Types.NUMERIC);
	        }
			
			return ps;
		});
	}
}
