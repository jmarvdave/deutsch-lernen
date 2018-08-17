package application.algorithm;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

class AlgorithmSettings {

  private final DayOfWeek dayOfWeek;
  private final ResponseToAlgorithmTransformer algorithmTransformer;

  AlgorithmSettings(ResponseToAlgorithmTransformer algorithmTransformer) {
    this(LocalDate.now().getDayOfWeek(), algorithmTransformer);
  }

  private AlgorithmSettings(DayOfWeek dayOfWeek,
      ResponseToAlgorithmTransformer algorithmTransformer) {
    this.dayOfWeek = dayOfWeek;
    this.algorithmTransformer = algorithmTransformer;
  }

  Algorithm getAlgorithm() {
    Map<DayOfWeek, Algorithm> dayOfWeekAlgorithmMap = algorithmTransformer.transformIntoAlgorithm();
    return dayOfWeekAlgorithmMap.get(dayOfWeek);
  }
}
