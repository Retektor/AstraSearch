package com.mrretektor.astrasearch.service.strategy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mrretektor.astrasearch.dao.StarDao;
import com.mrretektor.astrasearch.domain.Star;


@ExtendWith(MockitoExtension.class)
public class StarStrategyTests {
	
	@InjectMocks
	private StarStrategy underTest;
	
	@Mock
	private StarDao starDao;
	
	@Test
	void testThatStarStrategyReturnsStarData() {
		Map<String, Object> testData = Map.of(
				"constellation", "testConstellation",
				"apparent_magnitude", BigDecimal.valueOf(1L),
				"absolute_magnitude", BigDecimal.valueOf(-1L),
				"mass_solar", BigDecimal.valueOf(1L),
				"radius_solar", BigDecimal.valueOf(1L),
				"luminosity_solar", BigDecimal.valueOf(1L),
				"temperature", 20000
				);
		
		when(starDao.create(any(Star.class), eq(1L))).thenAnswer(inv -> {
	        Star star = inv.getArgument(0);
	        star.setId(1L);
	        return star;
	    });
		
		
		Map<String, Object> starResponse = underTest.createObject(1L, testData);
		
		
		assertThat(starResponse).contains(
				entry("star_id", 1L),
				entry("constellation", "testConstellation"),
				entry("apparent_magnitude", BigDecimal.valueOf(1L)),
				entry("absolute_magnitude", BigDecimal.valueOf(-1L)),
				entry("mass_solar", BigDecimal.valueOf(1L)),
				entry("radius_solar", BigDecimal.valueOf(1L)),
				entry("luminosity_solar", BigDecimal.valueOf(1L)),
				entry("temperature", 20000)
				);
	}
	
	
	@Test
	void testThatStarStrategyCallsStarDao() {
		Map<String, Object> testData = Map.of(
				"constellation", "testConstellation",
				"apparent_magnitude", BigDecimal.valueOf(1L),
				"absolute_magnitude", BigDecimal.valueOf(-1L),
				"mass_solar", BigDecimal.valueOf(1L),
				"radius_solar", BigDecimal.valueOf(1L),
				"luminosity_solar", BigDecimal.valueOf(1L),
				"temperature", 20000
				);
		
		when(starDao.create(any(Star.class), eq(1L))).thenAnswer(inv -> {
	        Star star = inv.getArgument(0);
	        star.setId(1L);
	        return star;
	    });
		
		
		Map<String, Object> starResponse = underTest.createObject(1L, testData);
		
		
		verify(starDao).create(any(Star.class), eq(1L));
	}
	
}
