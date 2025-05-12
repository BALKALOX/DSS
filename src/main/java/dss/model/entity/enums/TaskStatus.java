package dss.model.entity.enums;

public enum TaskStatus {
    NEW("Нова"),
    SOLVED("Вирішена"),
    EMERGENCY("Термінова");

    private final String ukrainianLabel;

    TaskStatus(String ukrainianLabel) {
        this.ukrainianLabel = ukrainianLabel;
    }

    public String getLabel() {
        return ukrainianLabel;
    }
}
