package enums;

public enum BottomPageButtons {

    TERMS("terms"),
    PRIVACY("privacy");

    private final String description;

    BottomPageButtons(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
