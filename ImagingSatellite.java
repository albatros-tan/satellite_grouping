public class ImagingSatellite extends Satellite {
    private double resolution;
    private int photosTaken = 0;

    public ImagingSatellite(String name, double batteryLevel, ILog log, double resolution) {
        super(name, batteryLevel, log);
        this.resolution = resolution;
    }

    private void takePhoto() {
        if (this.isActive) {
            this.photosTaken++;
            this.log.info(
                    this.name,
                    "Снимок #" + getPhotosTaken() + " сделан!");
        }
    }

    public double getResolution() {
        return this.resolution;
    }

    public double getPhotosTaken() {
        return this.photosTaken;
    }

    @Override
    public void performMission() {
        if (this.isActive) {
            this.log.info(
                    this.name,
                    "Съемка территории с разрешением " + getResolution() + " м/пиксель");
            takePhoto();
            consumeBattery(0.08);
        } else {
            this.log.error(this.name, "Не может выполнить съемку", "Не активен");
        }
    }

    @Override
    public String toString() {
        return "ImagingSatellite{resolution=" + getResolution() +
                ", photosTaken=" + getPhotosTaken() + ", name='" +
                this.name + "', isActive=" + this.isActive +
                ", batteryLevel=" + this.batteryLevel + "}";
    }
}
