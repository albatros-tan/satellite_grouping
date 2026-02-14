package com.SatelliteGrouping;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ConstellationRepository {
    protected ILog log;
    private final Map<String, SatelliteConstellation> constellations = new HashMap<>();

    public ConstellationRepository() {
        this.log = new PrintMessage();
    }

    public void addConstellation(SatelliteConstellation constellation) {
        this.constellations.put(constellation.getConstellationName(), constellation);
        this.log.info(constellation.getConstellationName(), "Группировка создана!");
    }

    public SatelliteConstellation getConstellation(String name) {
        SatelliteConstellation constellation = this.constellations.get(name);
        if (constellation == null) {
            throw new RuntimeException("Группировка " + name + " не найдена!");
        }
        return constellation;
    }

    public Map<String, SatelliteConstellation> getAllConstellations() {
        return new HashMap<>(this.constellations);
    }

    public boolean containsConstellation(String name) {
        return this.constellations.containsKey(name);
    }

    public void removeConstellation(String name) {
        this.constellations.remove(name);
        this.log.info(name, "Группировка удалена!");
    }

}
