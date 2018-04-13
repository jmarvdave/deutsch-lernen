package application.model;

import java.util.List;

public class VerbSheet implements Sheet {
    private final List<VerbRow> values;

    public VerbSheet(List<VerbRow> values) {
        this.values = values;
    }

    @Override
    public List<VerbRow> getValues() {
        return values;
    }
}
