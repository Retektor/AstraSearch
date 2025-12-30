package com.mrretektor.astrasearch.dao.impl;

import java.sql.Timestamp;
import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.mrretektor.astrasearch.TestDataUtil;
import com.mrretektor.astrasearch.domain.CelestialBody;
import com.mrretektor.astrasearch.domain.Image;

@Testcontainers
@SpringBootTest
@ActiveProfiles("test")
public class CelestialBodyDaoIntegrationTests {
	
	@Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine")
        .withDatabaseName("testdb")
        .withUsername("test")
        .withPassword("test");
    
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.datasource.driver-class-name", () -> "org.postgresql.Driver");
    }
    
    
    private CelestialBodyDaoImpl underTest;
    private final JdbcTemplate jdbcTemplate;
    
    
    @Autowired
    public CelestialBodyDaoIntegrationTests(CelestialBodyDaoImpl underTest, JdbcTemplate jdbcTemplate) {
    	this.underTest = underTest;
    	this.jdbcTemplate = jdbcTemplate;
    }
    
    @Test
    @Transactional
    public void testThatCelestialBodyIsCreatedSuccessfully () {
    	Timestamp time = Timestamp.from(Instant.now());
    	Image image = TestDataUtil.createTestImage();
    	
    	jdbcTemplate.update(
    	        "INSERT INTO images (url, caption) VALUES (?, ?)",
    	        image.getUrl(),
    	        image.getCaption()
    	    );
    	
    	CelestialBody celestialBody = TestDataUtil.createTestCelestialBody(time);
    	
    	underTest.create(celestialBody);
    }

}
