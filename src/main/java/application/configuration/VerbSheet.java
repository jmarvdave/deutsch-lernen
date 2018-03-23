package application.configuration;

import java.util.List;

public class VerbSheet {
    private final List<VerbRow> values;

    VerbSheet(List<VerbRow> values) {
        this.values = values;
    }

    public List<VerbRow> getValues() {
        return values;
    }
}
