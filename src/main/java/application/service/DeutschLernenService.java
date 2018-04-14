package application.service;

import application.model.NounSheet;
import application.model.Test;
import application.model.VerbSheet;
import application.writer.LatexWriter;
import application.transformer.ResponseToSheetTransformer;
import application.generator.TestGenerator;

import javax.annotation.PostConstruct;

public class DeutschLernenService {

    private final ResponseToSheetTransformer transformer;
    private final LatexWriter writer;
    private static final String VERB_SHEET_ID = "1MpUyZq-UDfvHAt2jZN45cEQnyeSk6Nr56_slyQUMcDI";
    private static final String NOUN_SHEET_ID = "1-JL2h2SrndIbNkOGJmueRxcZwqaSl90meGc6fkeJ-Go";

    public DeutschLernenService(ResponseToSheetTransformer transformer, LatexWriter writer) {
        this.transformer = transformer;
        this.writer = writer;
    }

    @PostConstruct
    public void run() {
        VerbSheet verbValues = transformer.transformToVerbSheet(VERB_SHEET_ID);
        NounSheet nounValues = transformer.transformToNounSheet(NOUN_SHEET_ID);

        Test test = new TestGenerator(verbValues, nounValues).generate();
        writer.writeTestToFile(test.getRandomVerbs(), test.getRandomNouns());
    }
}
