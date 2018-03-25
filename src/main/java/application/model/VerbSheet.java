package application.model;

import java.util.List;

public class VerbSheet {
    private final List<VerbRow> values;

    public VerbSheet(List<VerbRow> values) {
        this.values = values;
    }

    public List<VerbRow> getValues() {
        return values;
    }
}
