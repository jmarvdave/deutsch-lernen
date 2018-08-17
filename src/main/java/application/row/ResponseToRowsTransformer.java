package application.row;

import application.row.Row.TYPE;
import application.google.GoogleSheetsRetriever;
import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ResponseToRowsTransformer {
    private final GoogleSheetsRetriever googleSheetsRetriever;

    public ResponseToRowsTransformer(GoogleSheetsRetriever googleSheetsRetriever) {
        this.googleSheetsRetriever = googleSheetsRetriever;
    }

    public List<Row> transformToVerbSheet(String sheetId) {
        List<List<Object>> values = googleSheetsRetriever.getValuesFromSheet(sheetId);
        return values
                .stream()
                .map(convertToRowModel())
                .map(row -> new Row(row.get(0), TYPE.VERB))
                .collect(Collectors.toList());
    }

    public List<Row> transformToNounSheet(String sheetId) {
        List<List<Object>> values = googleSheetsRetriever.getValuesFromSheet(sheetId);
        return values
                .stream()
                .map(convertToRowModel())
                .map(row -> new Row(row.get(1), TYPE.NOUN))
                .collect(Collectors.toList());
    }

    public List<Row> transformToAdjectiveSheet(String sheetId) {
        List<List<Object>> values = googleSheetsRetriever.getValuesFromSheet(sheetId);
        return values
            .stream()
            .map(convertToRowModel())
            .map(row -> new Row(row.get(0), TYPE.ADJECTIVE))
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
