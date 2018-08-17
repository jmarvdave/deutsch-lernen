package application.algorithm;

import application.utility.SheetsRetriever;
import com.google.common.collect.ImmutableList;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResponseToAlgorithmTransformer {

  private static final String ALGORITHM_SHEET_ID = "1oalhNUo1jD2vhNFi1cygYMnXlQXRyRe3Tm1tU03dZ9w";
  private final String sheetId;

  private final SheetsRetriever sheetsRetriever;

  public ResponseToAlgorithmTransformer(SheetsRetriever sheetsRetriever) {
    this(ALGORITHM_SHEET_ID, sheetsRetriever);
  }

  public ResponseToAlgorithmTransformer(String sheetId, SheetsRetriever sheetsRetriever) {
    this.sheetId = sheetId;
    this.sheetsRetriever = sheetsRetriever;
  }

  Map<DayOfWeek, Algorithm> transformIntoAlgorithm() {
    List<List<Object>> values = sheetsRetriever.getValuesFromSheet(sheetId);
    return values
        .stream()
        .map(this::convertToAlgorithmList)
        .collect(Collectors.toMap(i -> DayOfWeek.valueOf(i.get(0).toUpperCase()),
            i -> Algorithm.valueOf(i.get(1).toUpperCase())));
  }

  private List<String> convertToAlgorithmList(List<Object> row) {
    return ImmutableList.of(row.get(0).toString(), row.get(1).toString());
  }
}
