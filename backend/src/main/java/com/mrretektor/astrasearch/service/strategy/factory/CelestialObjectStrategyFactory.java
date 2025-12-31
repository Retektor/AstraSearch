package com.mrretektor.astrasearch.service.strategy.factory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.mrretektor.astrasearch.domain.BodyType;
import com.mrretektor.astrasearch.service.strategy.CelestialObjectStrategy;

@Component
public class CelestialObjectStrategyFactory {
    
    private final Map<BodyType, CelestialObjectStrategy> strategies;
    
    public CelestialObjectStrategyFactory(List<CelestialObjectStrategy> strategyList) {
        strategies = strategyList.stream()
            .collect(Collectors.toMap(
                CelestialObjectStrategy::getBodyType,
                strategy -> strategy
            ));
        
        System.out.println("Factory created. Strategies: " + strategies.keySet());
    }
    
    public CelestialObjectStrategy getStrategy(BodyType bodyType) {
        CelestialObjectStrategy strategy = strategies.get(bodyType);
        if (strategy == null) {
            throw new IllegalArgumentException(
                "No strategy found for body type: " + bodyType
            );
        }
        
        return strategy;
    }
}
