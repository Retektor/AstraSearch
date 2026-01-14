package com.mrretektor.astrasearch.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.mrretektor.astrasearch.dao.CelestialBodyDao;
import com.mrretektor.astrasearch.domain.BodyType;
import com.mrretektor.astrasearch.domain.CelestialBody;

@Component
public class CelestialBodyDaoImpl implements CelestialBodyDao {
	private final JdbcTemplate jdbcTemplate;
	
	public CelestialBodyDaoImpl(final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public CelestialBody create(Long userId, CelestialBody body) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(
			"INSERT INTO celestial_bodies (user_id, name, description, body_type, discovery_time,"
				+ " image_id, right_ascension, declination)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)", new String[]{"id"}
			);
			
			ps.setLong(1, userId);
			ps.setString(2, body.getName());
			ps.setString(3, body.getDescription());
			if (body.getBodyType() != null) {
	            ps.setObject(4, body.getBodyType().name(), Types.OTHER);
	        } else {
	            ps.setNull(4, Types.OTHER);
	        }
	        
	        if (body.getDiscoveryTime() != null) {
	            ps.setTimestamp(5, Timestamp.valueOf(body.getDiscoveryTime()));
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
		}, keyHolder);
		
		Long id = keyHolder.getKey().longValue();
		
		CelestialBody createdBody = jdbcTemplate.query("SELECT * FROM celestial_bodies WHERE id = ?", new CelestialBodyRowMapper(), id).getFirst();
		
		return createdBody;
	}
	
	
	@Override
	public Optional<CelestialBody> findByName(String name) {
		try {
			return Optional.ofNullable(
					jdbcTemplate.queryForObject("SELECT * FROM celestial_bodies WHERE name = ?", new CelestialBodyRowMapper(), name)
					);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}
	
	
	public static class CelestialBodyRowMapper implements RowMapper<CelestialBody> {
		
		@Override
		public CelestialBody mapRow(ResultSet rs, int rowNum) throws SQLException {
			String bodyTypeStr = rs.getString("body_type");
			
			BodyType bodyType = null;
			
			if (bodyTypeStr != null) {
				bodyType = BodyType.valueOf(bodyTypeStr);
			}
			
			return CelestialBody.builder()
					.id(rs.getLong("id"))
					.userId(rs.getLong("user_id"))
					.name(rs.getString("name"))
					.description(rs.getString("description"))
					.bodyType(bodyType)
					.discoveryTime(rs.getTimestamp("discovery_time").toLocalDateTime())
					.imageId(rs.getLong("image_id"))
					.rightAscension(rs.getBigDecimal("right_ascension"))
					.declination(rs.getBigDecimal("declination"))
					.build();
		}
	}
}
