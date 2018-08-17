package application.algorithm;

import application.model.Row;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AlgorithmExecutor {

  private final AlgorithmSettings algorithmSettings;

  public AlgorithmExecutor(AlgorithmSettings algorithmSettings) {
    this.algorithmSettings = algorithmSettings;
  }

  public List<Row> apply(List<Row> rows) {
    Algorithm algorithm = algorithmSettings.getAlgorithm();
    if (algorithm == Algorithm.NEWEST) {
      return newest(rows);
    } else if (algorithm == Algorithm.OLDEST) {
      return oldest(rows);
    } else {
      return rows;
    }
  }

  private List<Row> oldest(List<Row> incomingRows) {
    List<Row> rows = Lists.reverse(incomingRows);
    return lastEntriesAppearMostOften(rows);
  }

  private List<Row> newest(List<Row> rows) {
    return lastEntriesAppearMostOften(rows);
  }

  private List<Row> lastEntriesAppearMostOften(List<Row> rows) {
    return IntStream
        .rangeClosed(1, rows.size())
        .mapToObj(number -> Collections.nCopies(number, rows.get(number - 1)))
        .flatMap(Collection::stream)
        .collect(Collectors.toList());
  }
}
