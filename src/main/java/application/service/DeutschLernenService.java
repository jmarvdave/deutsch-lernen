package application.service;

import application.generator.TestGenerator;
import application.model.Row;
import application.model.Test;
import application.transformer.ResponseToSheetTransformer;
import application.writer.LatexWriter;

import javax.annotation.PostConstruct;
import java.util.List;

public class DeutschLernenService {

    private final ResponseToSheetTransformer transformer;
    private final LatexWriter writer;
    private final ResultsWeigher weighter;
    private static final String VERB_SHEET_ID = "1MpUyZq-UDfvHAt2jZN45cEQnyeSk6Nr56_slyQUMcDI";
    private static final String NOUN_SHEET_ID = "1-JL2h2SrndIbNkOGJmueRxcZwqaSl90meGc6fkeJ-Go";

    private static final int NUMBER_OF_VERBS = 14;
    private static final int NUMBER_OF_NOUNS = 6;

    public DeutschLernenService(ResponseToSheetTransformer transformer, LatexWriter writer, ResultsWeigher weighter) {
        this.transformer = transformer;
        this.writer = writer;
        this.weighter = weighter;
    }

    @PostConstruct
    public void run() {
        List<Row> verbRows = transformer.transformToVerbSheet(VERB_SHEET_ID);
        List<Row> nounRows = transformer.transformToNounSheet(NOUN_SHEET_ID);

        List<Row> verbRowsWeighted = weighter.apply(verbRows);
        List<Row> nounRowsWeighted = weighter.apply(nounRows);

        Integer numberOfVerbs = setResultLimit(verbRows, NUMBER_OF_VERBS);
        Integer numberOfNouns = setResultLimit(nounRows, NUMBER_OF_NOUNS);

        Test test = new TestGenerator(verbRowsWeighted, nounRowsWeighted).generate(numberOfVerbs, numberOfNouns);
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
