package application.service;

import application.algorithm.AlgorithmExecutor;
import application.generator.TestGenerator;
import application.model.Row;
import application.model.Test;
import application.transformer.ResponseToSheetTransformer;
import application.writer.LatexWriter;
import java.util.List;
import javax.annotation.PostConstruct;

public class DeutschLernenService {

  private final ResponseToSheetTransformer transformer;
  private final AlgorithmExecutor algorithmExecutor;
  private final LatexWriter writer;
  private static final String VERB_SHEET_ID = "1MpUyZq-UDfvHAt2jZN45cEQnyeSk6Nr56_slyQUMcDI";
  private static final String NOUN_SHEET_ID = "1-JL2h2SrndIbNkOGJmueRxcZwqaSl90meGc6fkeJ-Go";

  private static final int NUMBER_OF_VERBS = 14;
  private static final int NUMBER_OF_NOUNS = 6;

  public DeutschLernenService(ResponseToSheetTransformer transformer,
      LatexWriter writer,
      AlgorithmExecutor algorithmExecutor) {
    this.transformer = transformer;
    this.writer = writer;
    this.algorithmExecutor = algorithmExecutor;
  }

  @PostConstruct
  public void run() {
    List<Row> verbRows = transformer.transformToVerbSheet(VERB_SHEET_ID);
    List<Row> nounRows = transformer.transformToNounSheet(NOUN_SHEET_ID);

    List<Row> verbRowsWithAppliedAlgorithm = algorithmExecutor.apply(verbRows);
    List<Row> nounRowsWithAppliedAlgorithm = algorithmExecutor.apply(nounRows);

    Integer numberOfVerbs = setResultLimit(verbRows, NUMBER_OF_VERBS);
    Integer numberOfNouns = setResultLimit(nounRows, NUMBER_OF_NOUNS);

    Test test = new TestGenerator(verbRowsWithAppliedAlgorithm, nounRowsWithAppliedAlgorithm)
        .generate(numberOfVerbs, numberOfNouns);
    writer.writeTestToFile(test.getRandomVerbs(), test.getRandomNouns(), "testing.tex");
  }

  private int setResultLimit(List<Row> rows, int desiredLimit) {
    Integer numberOfNouns = desiredLimit;
    if (rows.size() <= numberOfNouns) {
      numberOfNouns = rows.size();
    }
    return numberOfNouns;
  }

}
