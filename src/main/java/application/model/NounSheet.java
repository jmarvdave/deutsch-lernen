package application.model;

import java.util.List;

public class NounSheet implements Sheet {
    private final List<NounRow> values;

    public NounSheet(List<NounRow> values) {
        this.values = values;
    }

    public List<NounRow> getValues() {
        return values;
    }
}
