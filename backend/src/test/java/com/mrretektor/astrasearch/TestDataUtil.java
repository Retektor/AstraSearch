package com.mrretektor.astrasearch;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.Types;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mrretektor.astrasearch.domain.BodyType;
import com.mrretektor.astrasearch.domain.CelestialBody;
import com.mrretektor.astrasearch.domain.Image;
import com.mrretektor.astrasearch.domain.User;

public final class TestDataUtil {
	
	public static User createTestUser() {
		return User.builder()
				.username("user")
				.password("password")
				.firstName("John")
				.lastName("Doe")
				.email("JohnDoe@example.com")
				.build();
	}
	
	public static void saveTestUser(User user, JdbcTemplate jdbcTemplate) {
		jdbcTemplate.update("INSERT INTO users (username, password, first_name, last_name, email)"
				+ " VALUES (?, ?, ?, ?, ?)",
				user.getUsername(),
				user.getPassword(),
				user.getFirstName(),
				user.getLastName(),
				user.getEmail()
				);
	}
	
	public static CelestialBody createTestCelestialBody(Long userId, Timestamp discoveryTime) {
		return CelestialBody.builder()
				.id(1L)
				.userId(userId)
				.name("testName")
				.description("testDescription")
				.bodyType(BodyType.STAR)
				.discoveryTime(discoveryTime)
				.imageId(1L)
				.rightAscension(new BigDecimal("1.000000"))
				.declination(new BigDecimal("-1.000000"))
				.build();
	}
	
	public static void saveTestCelestialBody(CelestialBody body, JdbcTemplate jdbcTemplate) {
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(
			"INSERT INTO celestial_bodies (user_id, name, description, body_type, discovery_time,"
				+ " image_id, right_ascension, declination)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setLong(1, body.getUserId());
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
	            ps.setLong(5, body.getImageId());
	        } else {
	            ps.setNull(5, Types.BIGINT);
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
	
	public static Image createTestImage() {
		return Image.builder()
				.id(1L)
				.url("https://example.com")
				.caption("testCaption")
				.build();
	}
	
	public static void saveTestImage(Image image, JdbcTemplate jdbcTemplate) {
		jdbcTemplate.update("INSERT INTO images (url, caption) VALUES (?, ?)",
				image.getUrl(),
				image.getCaption()
				);
	}
}
