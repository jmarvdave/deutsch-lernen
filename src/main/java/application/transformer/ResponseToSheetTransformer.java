package application.transformer;

import application.model.Row;
import application.model.Row.TYPE;
import application.utility.SheetsRetriever;
import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ResponseToSheetTransformer {
    private final SheetsRetriever sheetsRetriever;

    public ResponseToSheetTransformer(SheetsRetriever sheetsRetriever) {
        this.sheetsRetriever = sheetsRetriever;
    }

    public List<Row> transformToVerbSheet(String sheetId) {
        List<List<Object>> values = sheetsRetriever.getValuesFromSheet(sheetId);
        return values
                .stream()
                .map(convertToRowModel())
                .map(row -> new Row(row.get(0), TYPE.VERB))
                .collect(Collectors.toList());
    }

    public List<Row> transformToNounSheet(String sheetId) {
        List<List<Object>> values = sheetsRetriever.getValuesFromSheet(sheetId);
        return values
                .stream()
                .map(convertToRowModel())
                .map(row -> new Row(row.get(1), TYPE.NOUN))
                .collect(Collectors.toList());
    }

    private Function<List<Object>, List<String>> convertToRowModel() {
        return (row) -> {
            StringBuilder o = new StringBuilder("");
            try {
                o.append(row.get(2));
            } catch (Exception ignored) {
            }
            return ImmutableList.of(row.get(0).toString(), row.get(1).toString(), o.toString());
        };
    }

}
