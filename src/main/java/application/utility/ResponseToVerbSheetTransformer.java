package application.utility;

import application.model.VerbRow;

import java.util.List;
import java.util.stream.Collectors;

public class ResponseToVerbSheetTransformer {
    public List<VerbRow> transform(List<List<Object>> values) {
        return values.stream().map((row) -> {
            StringBuilder o = new StringBuilder("");
            try {
                o.append(row.get(2));
            } catch (Exception ignored) {
            }
            return new VerbRow(row.get(0).toString(), row.get(1).toString(), o.toString());
        }).collect(Collectors.toList());
    }
}
