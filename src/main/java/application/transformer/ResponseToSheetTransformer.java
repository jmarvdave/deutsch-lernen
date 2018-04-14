package application.transformer;

import application.model.NounRow;
import application.model.NounSheet;
import application.model.VerbRow;
import application.model.VerbSheet;
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

    public VerbSheet transformToVerbSheet(String sheetId) {
        List<List<Object>> values = sheetsRetriever.getValuesFromSheet(sheetId);
        List<VerbRow> rows = values
                .stream()
                .map(convertToRowModel())
                .map(row -> new VerbRow(row.get(0), row.get(1), row.get(2)))
                .collect(Collectors.toList());
        return new VerbSheet(rows);
    }

    public NounSheet transformToNounSheet(String sheetId) {
        List<List<Object>> values = sheetsRetriever.getValuesFromSheet(sheetId);
        List<NounRow> rows = values
                .stream()
                .map(convertToRowModel())
                .map(row -> new NounRow(row.get(0), row.get(1), row.get(2)))
                .collect(Collectors.toList());
        return new NounSheet(rows);
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
