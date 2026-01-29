package com.mrretektor.astrasearch.service.strategy;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mrretektor.astrasearch.dao.StarDao;
import com.mrretektor.astrasearch.domain.BodyType;
import com.mrretektor.astrasearch.domain.Star;
import com.mrretektor.astrasearch.util.Util;

@Component
public class StarStrategy implements CelestialObjectStrategy {
	
	private final StarDao starDao;
    
    public StarStrategy(StarDao starDao) {
        this.starDao = starDao;
    }
	
	@Override
	public BodyType getBodyType() {
		return BodyType.STAR;
	}
	
	@Override
	public Map<String, Object> createObject(
	        Long celestialBodyId, 
	        Map<String, Object> requestData
	    ) {
		
		Star star = Star.builder()
				.constellation((String) requestData.get("constellation"))
				.apparentMagnitude(Util.convertToBigDecimal(requestData.get("apparent_magnitude")))
				.absoluteMagnitude(Util.convertToBigDecimal(requestData.get("absolute_magnitude")))
				.massSolar(Util.convertToBigDecimal(requestData.get("mass_solar")))
				.radiusSolar(Util.convertToBigDecimal(requestData.get("radius_solar")))
				.luminositySolar(Util.convertToBigDecimal(requestData.get("luminosity_solar")))
				.temperature(Util.convertToFloat(requestData.get("temperature")))
				.spectralClass((String) requestData.get("spectral_class"))
				.build();
		
		Long starId = starDao.create(star, celestialBodyId).getId();
		
		Map<String, Object> result = new HashMap<>(requestData);
        result.put("star_id", starId);
        
        return result;
	}
}
