package com.mrretektor.astrasearch.util;

import java.math.BigDecimal;
import java.sql.Timestamp;

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
	
	public static Image createTestImage() {
		return Image.builder()
				.id(1L)
				.url("https://example.com")
				.caption("testCaption")
				.build();
	}
}
