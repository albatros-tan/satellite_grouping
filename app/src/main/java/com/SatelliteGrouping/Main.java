package com.SatelliteGrouping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        PrintMessage log = new PrintMessage();
        log.printMsg("ЗАПУСК СИСТЕМЫ УПРАВЛЕНИЯ СПУТНИКОВОЙ ГРУППИРОВКИ");
        log.printMsg("=".repeat(50));
        log.printMsg("Создание спутниковых группировок");

        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

        ConstellationRepository constellationRepository = context.getBean(ConstellationRepository.class);
        SpaceOperationCenterService operationCenter = context.getBean(SpaceOperationCenterService.class);

        operationCenter.createAndSaveConstellation("Орбита-1");
        operationCenter.createAndSaveConstellation("Орбита-2");
        operationCenter.addSatelliteToConstellation(
                "Орбита-1",
                new CommunicationSatellite("Связь-1", 0.9, log, 500));
        operationCenter.addSatelliteToConstellation(
                "Орбита-2",
                new CommunicationSatellite("Связь-2", 0.75, log, 1000));
        operationCenter.addSatelliteToConstellation(
                "Орбита-1",
                new ImagingSatellite("ДЗЗ-1", 0.85, log, 2.0));
        operationCenter.addSatelliteToConstellation(
                "Орбита-2",
                new ImagingSatellite("ДЗЗ-2", 0.15, log, 1.5));
        operationCenter.addSatelliteToConstellation(
                "Орбита-1",
                new ImagingSatellite("ДЗЗ-3", 0.9, log, 2.5));
        operationCenter.addSatelliteToConstellation(
                "Орбита-1",
                new ImagingSatellite("ДЗЗ-4", 0.25, log, 1.0));
        log.printMsg("-".repeat(20));
        operationCenter.showConstellationStatus("Орбита-1");
        operationCenter.showConstellationStatus("Орбита-2");

        operationCenter.activateAllSatellites("Орбита-1");
        operationCenter.activateAllSatellites("Орбита-2");

        operationCenter.executeCanstellationMission("Орбита-1");
        operationCenter.showConstellationStatus("Орбита-1");
        operationCenter.executeCanstellationMission("Орбита-2");
        operationCenter.showConstellationStatus("Орбита-2");

        System.out.println(constellationRepository.getAllConstellations().toString());
    }
}