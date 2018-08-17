package application.row;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import application.google.GoogleSheetsRetriever;
import com.google.common.collect.ImmutableList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ResponseToRowsTransformerTest {

    private static final String SHEET = "weoifn34f";

    @Mock
    private GoogleSheetsRetriever googleSheetsRetriever;
    @Mock
    private ResponseToRowsTransformer transformer;

    private final List<Object> firstVerbRow = ImmutableList.of("machen", "es wird gemacht", "oft benutzt Verb");
    private final List<Object> secondVerbRow = ImmutableList.of("anschalten", "es ist angechaltet", "ein Compter kann angeschaltet sein");
    private final List<Object> thirdVerbRowWithNoNotes = ImmutableList.of("reinigen", "lass uns die Maschine reinigen");
    private final List<Object> firstNounRow = ImmutableList.of("Haus", "er wohnt in einem riesigen Haus", "Häuser sind bunt in Deutschland");
    private final List<Object> secondNounRow = ImmutableList.of("Bier", "das Bier schmeckt gut", "ohne Bier kann man nicht leben");
    private final ImmutableList<List<Object>> verbList1 = ImmutableList.of(firstVerbRow, secondVerbRow);
    private final ImmutableList<List<Object>> verbList2 = ImmutableList.of(firstVerbRow, secondVerbRow, thirdVerbRowWithNoNotes);
    private final ImmutableList<List<Object>> nounList = ImmutableList.of(firstNounRow, secondNounRow);

    @Before
    public void setUp() {
        transformer = new ResponseToRowsTransformer(googleSheetsRetriever);
    }

    @Test
    public void transformToVerbSheet() {
        when(googleSheetsRetriever.getValuesFromSheet(SHEET)).thenReturn(verbList1);
        List<Row> verbRows = transformer.transformToVerbSheet(SHEET);

        assertThat(verbRows.size()).isEqualTo(2);
    }

    @Test
    public void transformToNounSheet() {
        when(googleSheetsRetriever.getValuesFromSheet(SHEET)).thenReturn(nounList);
        List<Row> nounRows = transformer.transformToNounSheet(SHEET);

        assertThat(nounRows.size()).isEqualTo(2);
    }

    @Test
    public void handlesExceptionWhenNotesAreBlank() {
        when(googleSheetsRetriever.getValuesFromSheet(SHEET)).thenReturn(verbList2);
        List<Row> verbRows = transformer.transformToVerbSheet(SHEET);

        assertThat(verbRows.size()).isEqualTo(3);
    }

}