package com.SatelliteGrouping;

public abstract class Satellite {
    private final double criticalBatteryLevel = 0.2;
    protected ILog log;
    protected String name;
    protected SatelliteState state = new SatelliteState();
    protected EnergySystem energy;

    public Satellite(String name, double batteryLevel, ILog log) {
        this.name = name;
        this.log = log;
        this.energy = new EnergySystem(batteryLevel);
        this.log.printMsg(
                "Создан путник: "
                        + this.name
                        + " (заряд: " + this.energy.getPercentBatteryLevel() + "%)");
    }

    public boolean activate() {
        if (this.energy.getBatteryLevel() <= this.criticalBatteryLevel) {
            this.log.error(
                    this.name,
                    "Не удалось активировать",
                    "Уровень заряда "
                            + this.energy.getPercentBatteryLevel()
                            + "% ниже критического");
            return false;
        }
        String stateMessage = this.state.activate();
        this.log.info(this.name, stateMessage);
        return true;
    }

    public void deactivate() {
        String stateMessage = this.state.deactivate();
        this.log.info(this.name, stateMessage);
    }

    public void consumeBattery(double amountEnergy) {
        if (amountEnergy > this.energy.getBatteryLevel()) {
            this.log.error(this.name,
                    "Операция не может быть выполнена",
                    "Низкий уровень заряда");
        } else {
            this.energy.consume(amountEnergy);
        }
        if (this.energy.getBatteryLevel() <= this.criticalBatteryLevel) {
            deactivate();
        }
    }

    public String getName() {
        return this.name;
    }

    public void getState() {
        this.log.info(this.name, "Активен: " + this.state.getState());
    }

    protected abstract void performMission();

}
