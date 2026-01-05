public abstract class Satellite {
    private final double criticalBatteryLevel = 0.2;
    protected ILog log;
    protected String name;
    protected boolean isActive;
    protected double batteryLevel;

    public Satellite(String name, double batteryLevel, ILog log) {
        this.name = name;
        this.log = log;
        this.batteryLevel = batteryLevel;
        this.log.printMsg(
                "Создан путник: " + this.name + " (заряд: " + this.batteryLevel * 100 + "%)");
    }

    public boolean activate() {
        if (this.batteryLevel <= this.criticalBatteryLevel) {
            this.log.error(
                    this.name,
                    "Не удалось активировать",
                    "Уровень заряда " + this.batteryLevel * 100 + "% ниже критического");
            return false;
        }
        this.isActive = true;
        this.log.info(this.name, "Успешно активирован!");
        return true;
    }

    public void deactivate() {
        if (this.isActive == true) {
            this.isActive = false;
            this.log.info(this.name, "Отключен!");
        } else {
            this.log.warning(this.name, "Уже отключен!");
        }
    }

    public void consumeBattery(double amountEnergy) {
        if (amountEnergy > this.batteryLevel) {
            this.log.error(this.name,
                    "Операция не может быть выполнена",
                    "Низкий уровень заряда");
        } else {
            this.batteryLevel -= amountEnergy;
        }
        if (this.batteryLevel <= this.criticalBatteryLevel) {
            deactivate();
        }
    }

    protected abstract void performMission();

}
