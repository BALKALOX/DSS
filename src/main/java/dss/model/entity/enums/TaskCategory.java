package dss.model.entity.enums;

public enum TaskCategory {
    ENERGETIC("Енергетика"),
    ECONOMY("Економіка"),
    ECOLOGY("Екологія");

    private final String ukrainianLabel;

    TaskCategory(String ukrainianLabel) {
        this.ukrainianLabel = ukrainianLabel;
    }

    public String getLabel() {
        return ukrainianLabel;
    }
}
