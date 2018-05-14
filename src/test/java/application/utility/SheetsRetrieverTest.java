package application.utility;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.Sheets.Spreadsheets;
import com.google.api.services.sheets.v4.Sheets.Spreadsheets.Values;
import com.google.api.services.sheets.v4.Sheets.Spreadsheets.Values.Get;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SheetsRetrieverTest {

    @Mock
    private Sheets sheetsService;

    @Mock
    private Spreadsheets spreadsheets;

    @Mock
    private Values values;

    @Mock
    private Get get;

    private SheetsRetriever sheetsRetriever;

    @Before
    public void setUp() {
        sheetsRetriever = new SheetsRetriever(sheetsService);
    }

    @Test
    public void getValuesFromSheet() throws IOException {
        when(values.get("example", "A2:E")).thenReturn(get);
        List<Object> row = Arrays.asList("machen", "ich mache es", "wie schaffem");

        when(get.execute()).thenReturn(new ValueRange().setValues(Collections.singletonList(row)));
        when(spreadsheets.values()).thenReturn(values);
        when(sheetsService.spreadsheets()).thenReturn(spreadsheets);

        List<List<Object>> valuesFromSheet = sheetsRetriever.getValuesFromSheet("example");

        assertThat(valuesFromSheet.isEmpty()).isFalse();
    }

    @Test(expected = RuntimeException.class)
    public void throwsExceptionWhenServiceFails() {
        when(sheetsService.spreadsheets()).thenThrow(IOException.class);

        sheetsRetriever.getValuesFromSheet("example");
    }
}