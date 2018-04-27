package application.transformer;

import application.model.NounRow;
import application.model.VerbRow;
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

    public List<VerbRow> transformToVerbSheet(String sheetId) {
        List<List<Object>> values = sheetsRetriever.getValuesFromSheet(sheetId);
        List<VerbRow> rows = values
                .stream()
                .map(convertToRowModel())
                .map(row -> new VerbRow(row.get(0), row.get(1), row.get(2)))
                .collect(Collectors.toList());
        return rows;
    }

    public List<NounRow> transformToNounSheet(String sheetId) {
        List<List<Object>> values = sheetsRetriever.getValuesFromSheet(sheetId);
        List<NounRow> rows = values
                .stream()
                .map(convertToRowModel())
                .map(row -> new NounRow(row.get(0), row.get(1), row.get(2)))
                .collect(Collectors.toList());
        return rows;
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
