public class CommunicationSatellite extends Satellite {
    private double bandWidth;

    public CommunicationSatellite(String name, double batteryLevel, ILog log, double bandWidth) {
        super(name, batteryLevel, log);
        this.bandWidth = bandWidth;
    }

    private void sendData() {

    }

    public double getBandwidth() {
        return this.bandWidth;
    }

    @Override
    public void performMission() {
        if (this.isActive) {
            this.log.info(
                    this.name,
                    "Передача данны со скоростью " + getBandwidth() + " МБ/с");
            sendData();
            consumeBattery(0.05);
        } else {
            this.log.error(this.name, "Не может передать данные", "Не активен");
        }
    }

    @Override
    public String toString() {
        return "CommunicationSatellite{bandwidth=" + getBandwidth() +
                ", name='" + this.name + "', isActive=" + this.isActive +
                ", batteryLevel=" + this.batteryLevel + "}";
    }
}
