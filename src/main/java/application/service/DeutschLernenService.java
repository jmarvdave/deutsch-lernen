package application.service;

import application.utility.LatexWriter;
import application.utility.ResponseToVerbSheetTransformer;
import application.model.VerbRow;
import application.model.VerbSheet;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class DeutschLernenService {
    private static final int NUMBER_OF_VERBS = 10;
    private final Sheets sheetsService;
    private final LatexWriter writer;
    private static final String VERB_SHEET_ID = "1MpUyZq-UDfvHAt2jZN45cEQnyeSk6Nr56_slyQUMcDI";

    public DeutschLernenService(Sheets service, LatexWriter writer) {
        this.sheetsService = service;
        this.writer = writer;
    }

    public void run() throws IOException {
        VerbSheet values = getAllRows();

        generateRandomTest(values);
    }

    private void generateRandomTest(VerbSheet sheet) {
        List<String> allVerbs = sheet.getValues()
                .stream()
                .map(VerbRow::getVerb)
                .collect(Collectors.toList());

        writer.writeVerbs(generateRandomIntegers(new HashSet<>(), allVerbs.size(), NUMBER_OF_VERBS)
                .stream()
                .map(allVerbs::get)
                .collect(Collectors.toList()));
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

    private VerbSheet getAllRows() throws IOException {
        // Prints the names and majors of students in a sample spreadsheet:
        // https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
        String range = "A2:E";
        ValueRange response = sheetsService.spreadsheets().values()
                .get(VERB_SHEET_ID, range)
                .execute();

        return new VerbSheet(new ResponseToVerbSheetTransformer().transform(response.getValues()));
    }
}
