package com.SatelliteGrouping;

import java.util.ArrayList;

public class SatelliteConstellation {
    private ILog log;
    private String constellationName;
    private ArrayList<Satellite> satellites;

    public SatelliteConstellation(String constellationName, ILog log) {
        this.constellationName = constellationName;
        this.satellites = new ArrayList<>();
        this.log = log;
        this.log.printMsg(
                "=========Создана спутниковая группировка " +
                        this.constellationName + "=========");
    }

    private void activateAllSatellites() {
        this.log.printMsg("=========Активация спутников=========");
        for (Satellite satellite : this.satellites) {
            satellite.activate();
        }
    }

    public void addSatellite(Satellite satellite) {
        this.satellites.add(satellite);
        this.log.info(
                this.constellationName,
                "в группировку добавлен спутник " + satellite.name);
    }

    public ArrayList<Satellite> getSatellites() {
        return this.satellites;
    }

    public void executeAllMission() {
        activateAllSatellites();
        this.log.printMsg(
                "=========Выполнение миссии группировки " + this.constellationName + "=========");
        for (Satellite satellite : this.satellites) {
            satellite.performMission();
        }
    }
}
