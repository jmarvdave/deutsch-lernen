package application.service;

import application.generator.TestGenerator;
import application.model.NounRow;
import application.model.Test;
import application.model.VerbRow;
import application.transformer.ResponseToSheetTransformer;
import application.writer.LatexWriter;

import javax.annotation.PostConstruct;
import java.util.List;

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
        List<VerbRow> verbRows = transformer.transformToVerbSheet(VERB_SHEET_ID);
        List<NounRow> nounRows = transformer.transformToNounSheet(NOUN_SHEET_ID);

        Test test = new TestGenerator(verbRows, nounRows).generate();
        writer.writeTestToFile(test.getRandomVerbs(), test.getRandomNouns());
    }
}
