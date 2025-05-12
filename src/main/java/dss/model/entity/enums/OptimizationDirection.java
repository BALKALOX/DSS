package dss.model.entity.enums;

public enum OptimizationDirection {
    MINIMIZE("Мінімізація"),
    MAXIMIZE("Максимізація");

    private final String label;

    OptimizationDirection(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}