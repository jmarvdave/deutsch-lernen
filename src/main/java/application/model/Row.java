package application.model;

public class Row {
    private final String primaryElement;
    private final TYPE typeOfRecord;

    public Row(String primaryElement, TYPE typeOfRecord) {
        this.primaryElement = primaryElement;
        this.typeOfRecord = typeOfRecord;
    }

    public String getPrimaryElement() {
        return primaryElement;
    }

    public TYPE getTypeOfRecord() {
        return typeOfRecord;
    }

    public enum TYPE {
        VERB,
        NOUN
    }
}
