package com.mrretektor.astrasearch.service.strategy;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mrretektor.astrasearch.dao.PlanetDao;
import com.mrretektor.astrasearch.domain.BodyType;
import com.mrretektor.astrasearch.domain.Planet;
import com.mrretektor.astrasearch.util.Util;

@Component
public class PlanetStrategy implements CelestialObjectStrategy {
	
	private final PlanetDao planetDao;
    
    public PlanetStrategy(PlanetDao planetDao) {
        this.planetDao = planetDao;
    }
	
	@Override
	public BodyType getBodyType() {
		return BodyType.PLANET;
	}
	
	@Override
	public Map<String, Object> createObject(
	        Long celestialBodyId,
	        Map<String, Object> requestData
	    ) {
		
		Long starId = Util.convertToLong(requestData.get("star_id"));
		
		Planet planet = Planet.builder()
				.orbitalPeriodDays(Util.convertToFloat(requestData.get("orbital_period_days")))
				.earthMeanRadius(Util.convertToFloat(requestData.get("earth_mean_radius")))
				.earthVolume(Util.convertToFloat(requestData.get("earth_volume")))
				.meanDensity(Util.convertToFloat(requestData.get("mean_density")))
				.surfaceGravity(Util.convertToFloat(requestData.get("surface_gravity")))
				.surfaceTemperatureKelvin(Util.convertToFloat(requestData.get("surface_temperature_kelvin")))
				.build();
		
		Long planetId = planetDao.create(planet, celestialBodyId, starId).getId();
		
		Map<String, Object> result = new HashMap<>(requestData);
        result.put("planet_id", planetId);
        result.put("star_id", starId);
        
        return result;
	}
}
