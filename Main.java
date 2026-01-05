public class Main {
    public static void main(String[] args) {
        PrintMessage log = new PrintMessage();
        log.printMsg("ЗАПУСК СИСТЕМЫ УПРАВЛЕНИЯ СПУТНИКОВОЙ ГРУППИРОВКИ");
        log.printMsg("=".repeat(50));
        log.printMsg("Создание спутников");
        log.printMsg("-".repeat(20));
        CommunicationSatellite[] csList = {
                new CommunicationSatellite("Связь-1", 0.9, log, 500),
                new CommunicationSatellite("Связь-2", 0.75, log, 1000)
        };
        ImagingSatellite[] isList = {
                new ImagingSatellite("ДЗЗ-1", 0.85, log, 2.0),
                new ImagingSatellite("ДЗЗ-2", 0.15, log, 1.5),
                new ImagingSatellite("ДЗЗ-3", 0.9, log, 2.5),
                new ImagingSatellite("ДЗЗ-4", 0.25, log, 1.0)
        };

        log.printMsg("-".repeat(20));
        SatelliteConstellation grouping = new SatelliteConstellation("RU Base", log);
        log.printMsg("Формирование группировки:");
        for (CommunicationSatellite item : csList) {
            grouping.addSatellite(item);
        }
        for (ImagingSatellite item : isList) {
            grouping.addSatellite(item);
        }
        System.out.println(grouping.getSatellites());
        grouping.executeAllMission();
        System.out.println(grouping.getSatellites());
    }
}