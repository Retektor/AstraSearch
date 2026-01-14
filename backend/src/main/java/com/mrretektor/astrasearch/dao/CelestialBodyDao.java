package com.mrretektor.astrasearch.dao;

import java.util.Optional;

import com.mrretektor.astrasearch.domain.CelestialBody;

public interface CelestialBodyDao {
	 public CelestialBody create(Long userId, CelestialBody body);
	 public Optional<CelestialBody> findByName(String name);
}
