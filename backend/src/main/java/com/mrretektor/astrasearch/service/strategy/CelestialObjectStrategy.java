package com.mrretektor.astrasearch.service.strategy;

import java.util.Map;

import com.mrretektor.astrasearch.domain.BodyType;

public interface CelestialObjectStrategy {
	
	BodyType getBodyType();
	
	Map<String, Object> createObject(
	        Long celestialBodyId, 
	        Map<String, Object> requestData
	    );
}
