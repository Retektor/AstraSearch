package com.mrretektor.astrasearch.dao;

import java.util.List;
import java.util.Optional;

import com.mrretektor.astrasearch.domain.CelestialBody;

public interface CelestialBodyDao {
	 public CelestialBody create(Long userId, CelestialBody body);
	 public Optional<CelestialBody> findByName(String name);
	 public List<CelestialBody> getAll();
}
