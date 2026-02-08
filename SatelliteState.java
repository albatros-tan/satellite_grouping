public class SatelliteState {
    private boolean isActive = false;

    public boolean getState() {
        return this.isActive;
    }

    public String activate() {
        if (this.isActive == false) {
            this.isActive = true;
            return "Успешно активирован!";
        }
        return "Уже активен!";
    }

    public String deactivate() {
        if (this.isActive == true) {
            this.isActive = false;
            return "Отключен!";
        }
        return "Уже отключен!";
    }
}
