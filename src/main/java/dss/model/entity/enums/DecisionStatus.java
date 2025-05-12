package dss.model.entity.enums;

public enum DecisionStatus {
    PROPOSED("Запропоновано"),
    APPROVED("Схвалено"),
    REJECTED("Відхилено");

    private final String ukrName;

    DecisionStatus(String ukrName) {
        this.ukrName = ukrName;
    }

    public String getUkrName() {
        return ukrName;
    }
}