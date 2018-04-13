package application.service;

import application.model.NounRow;
import application.model.NounSheet;
import application.model.VerbRow;
import application.model.VerbSheet;
import application.utility.LatexWriter;
import application.utility.ResponseToNounSheetTransformer;
import application.utility.ResponseToVerbSheetTransformer;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class DeutschLernenService {
    private static final int NUMBER_OF_VERBS = 10;
    private static final int NUMBER_OF_NOUNS = 4;
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

        generateRandomTest(verbValues, nounValues);
    }

    private void generateRandomTest(VerbSheet verbSheet, NounSheet nounSheet) {
        List<VerbRow> allVerbs = verbSheet.getValues();

        List<NounRow> allNouns = nounSheet.getValues();

        List<VerbRow> randomVerbs = generateRandomIntegers(new HashSet<>(), allVerbs.size(), NUMBER_OF_VERBS)
                .stream()
                .map(allVerbs::get)
                .collect(Collectors.toList());

        List<NounRow> randomNouns = generateRandomIntegers(new HashSet<>(), allNouns.size(), NUMBER_OF_NOUNS)
                .stream()
                .map(allNouns::get)
                .collect(Collectors.toList());

        writer.writeTestToFile(randomVerbs, randomNouns);
    }

    private Set<Integer> generateRandomIntegers(Set<Integer> numbers, int numbersOfVerbs, int desiredSize) {
        if (numbers.size() == desiredSize) {
            return numbers;
        } else {
            Random rand = new Random();
            int n = rand.nextInt(numbersOfVerbs);
            numbers.add(n);
            return generateRandomIntegers(numbers, numbersOfVerbs, desiredSize);
        }
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
