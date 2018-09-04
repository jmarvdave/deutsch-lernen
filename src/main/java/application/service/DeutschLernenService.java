package application.service;

import application.algorithm.AlgorithmExecutor;
import application.exam.Exam;
import application.exam.ExamGenerator;
import application.row.ResponseToRowsTransformer;
import application.row.Row;
import application.exam.ExamWriter;
import application.telegram.TelegramNotifier;
import java.util.List;
import javax.annotation.PostConstruct;

public class DeutschLernenService {

  private final ResponseToRowsTransformer transformer;
  private final AlgorithmExecutor algorithmExecutor;
  private final TelegramNotifier notifier;
  private final ExamWriter writer;
  private static final String VERB_SHEET_ID = "1MpUyZq-UDfvHAt2jZN45cEQnyeSk6Nr56_slyQUMcDI";
  private static final String NOUN_SHEET_ID = "1-JL2h2SrndIbNkOGJmueRxcZwqaSl90meGc6fkeJ-Go";
  private static final String ADJECTIVES_SHEET_ID = "1m32NmkZMzUKgRuhTlBoP5I3Q_BOZ8pL5ci9oFIozWzk";

  private static final int NUMBER_OF_VERBS = 14;
  private static final int NUMBER_OF_NOUNS = 5;
  private static final int NUMBER_OF_ADJECTIVES = 1;

  public DeutschLernenService(ResponseToRowsTransformer transformer,
      ExamWriter writer,
      AlgorithmExecutor algorithmExecutor,
      TelegramNotifier notifier) {
    this.transformer = transformer;
    this.writer = writer;
    this.algorithmExecutor = algorithmExecutor;
    this.notifier = notifier;
  }

  @PostConstruct
  public void run() {
    List<Row> verbRows = transformer.transformToVerbSheet(VERB_SHEET_ID);
    List<Row> nounRows = transformer.transformToNounSheet(NOUN_SHEET_ID);
    List<Row> adjectiveRows = transformer.transformToAdjectiveSheet((ADJECTIVES_SHEET_ID));

    List<Row> verbRowsWithAppliedAlgorithm = algorithmExecutor.apply(verbRows);
    List<Row> nounRowsWithAppliedAlgorithm = algorithmExecutor.apply(nounRows);
    List<Row> adjectiveRowsWithAppliedAlgorithm = algorithmExecutor.apply(adjectiveRows);

    Integer numberOfVerbs = setResultLimit(verbRows, NUMBER_OF_VERBS);
    Integer numberOfNouns = setResultLimit(nounRows, NUMBER_OF_NOUNS);
    Integer numberOfAdjectives = setResultLimit(nounRows, NUMBER_OF_ADJECTIVES);

    Exam exam = new ExamGenerator(verbRowsWithAppliedAlgorithm, nounRowsWithAppliedAlgorithm,
        adjectiveRowsWithAppliedAlgorithm)
        .generate(numberOfVerbs, numberOfNouns, numberOfAdjectives);
    List<Row> randomVerbs = exam.getRandomVerbs();
    List<Row> randomNouns = exam.getRandomNouns();
    List<Row> randomAdjectives = exam.getRandomAdjectives();
    writer.writeTestToFile(randomVerbs, randomNouns, randomAdjectives, "testing.tex");
    notifier.sendReport(algorithmExecutor.getAlgorithm(), randomVerbs.size(), randomAdjectives.size(), randomNouns.size());
  }

  private int setResultLimit(List<Row> rows, int desiredLimit) {
    int numberOfNouns = desiredLimit;
    if (rows.size() <= numberOfNouns) {
      numberOfNouns = rows.size();
    }
    return numberOfNouns;
  }

}
