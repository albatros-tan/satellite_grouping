public class EnergySystem {
    protected double batteryLevel;

    public EnergySystem(double batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public double getBatteryLevel() {
        return this.batteryLevel;
    }

    public double getPercentBatteryLevel() {
        return this.batteryLevel * 100;
    }

    public void consume(double amountEnergy) {
        if (amountEnergy >= this.batteryLevel) {
            this.batteryLevel = 0;
        } else {
            this.batteryLevel -= amountEnergy;
        }
    }
}
