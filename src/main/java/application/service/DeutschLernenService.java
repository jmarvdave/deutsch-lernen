package application.service;

import application.model.NounSheet;
import application.model.Test;
import application.model.VerbSheet;
import application.utility.LatexWriter;
import application.utility.TestGenerator;
import application.utility.ResponseToNounSheetTransformer;
import application.utility.ResponseToVerbSheetTransformer;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

public class DeutschLernenService {

    private final Sheets sheetsService;
    private final LatexWriter writer;
    private static final String VERB_SHEET_ID = "1MpUyZq-UDfvHAt2jZN45cEQnyeSk6Nr56_slyQUMcDI";
    private static final String NOUN_SHEET_ID = "1-JL2h2SrndIbNkOGJmueRxcZwqaSl90meGc6fkeJ-Go";

    public DeutschLernenService(Sheets service, LatexWriter writer) {
        this.sheetsService = service;
        this.writer = writer;
    }

    @PostConstruct
    public void run() throws IOException {
        VerbSheet verbValues = new VerbSheet(new ResponseToVerbSheetTransformer().transform(getAllRows(VERB_SHEET_ID)));
        NounSheet nounValues = new NounSheet(new ResponseToNounSheetTransformer().transform(getAllRows(NOUN_SHEET_ID)));

        Test test = new TestGenerator(verbValues, nounValues).generate();
        writer.writeTestToFile(test.getRandomVerbs(), test.getRandomNouns());
    }

    private List<List<Object>> getAllRows(String sheetId) throws IOException {
        // https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
        String range = "A2:E";
        ValueRange response = sheetsService.spreadsheets().values()
                .get(sheetId, range)
                .execute();

        return response.getValues();
    }
}
