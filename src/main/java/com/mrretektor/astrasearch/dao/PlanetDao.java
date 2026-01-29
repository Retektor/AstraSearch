package com.mrretektor.astrasearch.dao;

import com.mrretektor.astrasearch.domain.Planet;

public interface PlanetDao {
	public Planet create(Planet planet, Long celestialBodyId, Long starId);
}
