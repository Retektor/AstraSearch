package com.mrretektor.astrasearch.util;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.mrretektor.astrasearch.domain.BodyType;
import com.mrretektor.astrasearch.domain.CelestialBody;
import com.mrretektor.astrasearch.domain.Image;
import com.mrretektor.astrasearch.domain.Star;
import com.mrretektor.astrasearch.domain.User;
import com.mrretektor.astrasearch.dto.request.CreateCelestialObjectRequest;

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
	
	public static CelestialBody createTestCelestialBody(Long userId, LocalDateTime discoveryTime, Long imageId) {
		return CelestialBody.builder()
				.id(1L)
				.userId(userId)
				.name("testName")
				.description("testDescription")
				.bodyType(BodyType.STAR)
				.discoveryTime(discoveryTime)
				.imageId(imageId)
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
	
	public static Star createTestStar() {
		return Star.builder()
				.constellation("testConstellation")
				.apparentMagnitude(BigDecimal.valueOf(-1))
				.absoluteMagnitude(BigDecimal.valueOf(1))
				.massSolar(BigDecimal.valueOf(1))
				.radiusSolar(BigDecimal.valueOf(1))
				.luminositySolar(BigDecimal.valueOf(1))
				.temperature((float) 273.15)
				.spectralClass("G")
				.build();
	}
	
	public static LocalDateTime createLocalDateTime() {
		return LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC);
	}
	
	public static CreateCelestialObjectRequest createCelestialObjectRequest() {
		LocalDateTime time = TestDataUtil.createLocalDateTime();
		
		
		Star star = TestDataUtil.createTestStar();
		Image image = TestDataUtil.createTestImage();
		
		CelestialBody celestialBody = TestDataUtil.createTestCelestialBody((long) 1, time, image.getId());
		
		return CreateCelestialObjectRequest.builder()
				.name(celestialBody.getName())
				.description(celestialBody.getDescription())
				.bodyType(celestialBody.getBodyType())
				.discoveryTime(celestialBody.getDiscoveryTime())
				.imageUrl(image.getUrl())
				.rightAscension(celestialBody.getRightAscension())
				.declination(celestialBody.getDeclination())
				.typeSpecificData(null)
				.build();
	}
}
