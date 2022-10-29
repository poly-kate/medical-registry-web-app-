package medregistry.enumer;

public enum TypeDoctor {
    therapist("Терапевт"),
    oculist("Окулист"),
    traumatologist("Травматолог"),
    dermatologist("Дерматолог"),
    allergist("Аллерголог"),
    dentist("Стоматолог"),
    psychiatrist("Психиатр");

    private final String displayValue;

    private TypeDoctor(String displayValue) {
        this.displayValue = displayValue;
    }
    public String getDisplayValue() {
        return displayValue;
    }
}


