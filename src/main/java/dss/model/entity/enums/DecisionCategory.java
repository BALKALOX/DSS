package dss.model.entity.enums;

public enum DecisionCategory {
    ECOLOGY("Екологія"),
    ENERGY("Енергетика"),
    ECONOMY("Економіка");

    private final String ukrName;

    DecisionCategory(String ukrName) {
        this.ukrName = ukrName;
    }

    public String getUkrName() {

        return ukrName;
    }
}