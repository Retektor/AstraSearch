package com.mrretektor.astrasearch.service.strategy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mrretektor.astrasearch.dao.PlanetDao;
import com.mrretektor.astrasearch.domain.Planet;


@ExtendWith(MockitoExtension.class)
public class PlanetStrategyTests {
	
	@InjectMocks
	private PlanetStrategy underTest;
	
	@Mock
	private PlanetDao planetDao;
	
	@Test
	void testThatPlanetStrategyReturnsPlanetData() {
		Map<String, Object> testData = Map.of(
				"star_id", 1L,
				"orbital_period_days", 365.25,
				"earth_mean_radius", 1,
				"earth_volume", 1,
				"mean_density", 5.51,
				"surface_gravity", 9.807,
				"surface_temperature_kelvin", 273.15
				);
		
		when(planetDao.create(any(Planet.class), eq(1L), eq(1L))).thenAnswer(inv -> {
	        Planet planet = inv.getArgument(0);
	        planet.setId(1L);
	        return planet;
	    });
		
		
		Map<String, Object> planetResponse = underTest.createObject(1L, testData);
		
		
		assertThat(planetResponse).contains(
				entry("planet_id", 1L),
				entry("star_id", 1L),
				entry("orbital_period_days", 365.25),
				entry("earth_mean_radius", 1),
				entry("earth_volume", 1),
				entry("mean_density", 5.51),
				entry("surface_gravity", 9.807),
				entry("surface_temperature_kelvin", 273.15)
				);
	}
}
