package application.google;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.util.List;

public class GoogleSheetsRetriever {

    private final Sheets sheetsService;

    public GoogleSheetsRetriever(Sheets sheetsService) {
        this.sheetsService = sheetsService;
    }

    public List<List<Object>> getValuesFromSheet(String sheetId) {
        List<List<Object>> values;
        try {
            values = getResponseFromService(sheetId);
        } catch (IOException e) {
            throw new RuntimeException("Critical failure retrieving data from Google Sheets:" + e);
        }
        return values;
    }

    private List<List<Object>> getResponseFromService(String sheetId) throws IOException {
        String range = "A2:E";
        ValueRange response = sheetsService
                .spreadsheets()
                .values()
                .get(sheetId, range)
                .execute();

        return response.getValues();
    }
}
