package com.SatelliteGrouping;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class SpaceOperationCenterService {
    protected ILog log = new PrintMessage();
    private final ConstellationRepository repository;

    public SpaceOperationCenterService(ConstellationRepository repository) {
        this.repository = repository;
    }

    public void createAndSaveConstellation(String name) {
        SatelliteConstellation constellation = new SatelliteConstellation(name, this.log);
        this.repository.addConstellation(constellation);
    }

    public void addSatelliteToConstellation(String constellationName, Satellite satellite) {
        SatelliteConstellation constellation = this.repository.getConstellation(constellationName);
        constellation.addSatellite(satellite);
        this.log.printMsg(
                "В группировку " + constellationName + " добавлен спутник " + satellite.getName());
    }

    public void executeCanstellationMission(String constellationName) {
        SatelliteConstellation constellation = this.repository.getConstellation(constellationName);
        this.log.info(constellationName, "---ЗАПУЩЕНО ВЫПОЛНЕНИЕ МИССИИ----->");
        constellation.executeAllMission();
    }

    public void activateAllSatellites(String constellationName) {
        SatelliteConstellation constellation = this.repository.getConstellation(constellationName);
        this.log.info(constellationName, "Активация спутников группировки");

        for (Satellite satellite : constellation.getSatellites()) {
            satellite.activate();
        }
    }

    public void showConstellationStatus(String constellationName) {
        SatelliteConstellation constellation = this.repository.getConstellation(constellationName);
        ArrayList<Satellite> satellites = constellation.getSatellites();

        this.log.printMsg("==============" + "Статус группировки " + constellationName + "==============");
        this.log.printMsg("Количество спутников: " + satellites.size());

        for (Satellite satellite : satellites) {
            satellite.getState();
        }
    }

}
