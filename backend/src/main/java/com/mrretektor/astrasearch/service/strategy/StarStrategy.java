package com.mrretektor.astrasearch.service.strategy;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mrretektor.astrasearch.dao.StarDao;
import com.mrretektor.astrasearch.domain.BodyType;
import com.mrretektor.astrasearch.domain.Star;

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
				.bodyId(celestialBodyId)
				.constellation((String) requestData.get("constellation"))
				.apparentMagnitude(convertToBigDecimal(requestData.get("apparent_magnitude")))
				.absoluteMagnitude(convertToBigDecimal(requestData.get("absolute_magnitude")))
				.massSolar(convertToBigDecimal(requestData.get("mass_solar")))
				.radiusSolar(convertToBigDecimal(requestData.get("radius_solar")))
				.luminositySolar(convertToBigDecimal(requestData.get("luminosity_solar")))
				.temperature(convertToFloat(requestData.get("temperature")))
				.spectralClass((String) requestData.get("spectral_class"))
				.build();
		
		Long starId = starDao.create(star).getId();
		
		Map<String, Object> result = new HashMap<>(requestData);
        result.put("star_id", starId);
        
        return result;
	}
	
	private BigDecimal convertToBigDecimal(Object value) {
        if (value == null) {
            return null;
        }
        
        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        } else if (value instanceof Integer) {
            return BigDecimal.valueOf((Integer) value);
        } else if (value instanceof Long) {
            return BigDecimal.valueOf((Long) value);
        } else if (value instanceof Double) {
            return BigDecimal.valueOf((Double) value);
        } else if (value instanceof Float) {
            return BigDecimal.valueOf((Float) value);
        } else if (value instanceof String) {
        return new BigDecimal((String) value);
        }
        return null;
	}
	
	private float convertToFloat(Object value) {
		if (value == null) {
	        return 0.0f;
	    }
	    
	    if (value instanceof Float) {
	        return (Float) value;
	    } else if (value instanceof Integer) {
	        return ((Integer) value).floatValue();
	    } else if (value instanceof Long) {
	        return ((Long) value).floatValue();
	    } else if (value instanceof Double) {
	        return ((Double) value).floatValue();
	    } else if (value instanceof BigDecimal) {
	        return ((BigDecimal) value).floatValue();
	    } else if (value instanceof String) {
	        return Float.parseFloat((String) value);
	    }
        return 0.0f;
	}
}
