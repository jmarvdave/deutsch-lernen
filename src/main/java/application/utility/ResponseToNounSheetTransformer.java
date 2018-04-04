package application.utility;

import application.model.NounRow;

import java.util.List;
import java.util.stream.Collectors;

public class ResponseToNounSheetTransformer implements SheetTransformer {
    @Override
    public List<NounRow> transform(List<List<Object>> values) {
        return values.stream().map((row) -> {
            StringBuilder notes = new StringBuilder("");
            try {
                notes.append(row.get(2));
            } catch (Exception ignored) {
            }
            return new NounRow(row.get(0).toString(), row.get(1).toString(), notes.toString());
        }).collect(Collectors.toList());
    }
}
