package application.transformer;

import application.model.NounRow;
import application.model.VerbRow;
import application.utility.SheetsRetriever;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ResponseToSheetTransformerTest {

    private static final String SHEET = "weoifn34f";

    @Mock
    private SheetsRetriever sheetsRetriever;
    @Mock
    private ResponseToSheetTransformer transformer;

    private final List<Object> firstVerbRow = ImmutableList.of("machen", "es wird gemacht", "oft benutzt Verb");
    private final List<Object> secondVerbRow = ImmutableList.of("anschalten", "es ist angechaltet", "ein Compter kann angeschaltet sein");
    private final List<Object> firstNounRow = ImmutableList.of("Haus", "er wohnt in einem riesigen Haus", "Häuser sind bunt in Deutschland");
    private final List<Object> secondNounRow = ImmutableList.of("Bier", "das Bier schmeckt gut", "ohne Bier kann man nicht leben");
    private final ImmutableList<List<Object>> verbList = ImmutableList.of(firstVerbRow, secondVerbRow);
    private final ImmutableList<List<Object>> nounList = ImmutableList.of(firstNounRow, secondNounRow);

    @Before
    public void setUp() {
        transformer = new ResponseToSheetTransformer(sheetsRetriever);
    }

    @Test
    public void transformToVerbSheet() {
        when(sheetsRetriever.getValuesFromSheet(SHEET)).thenReturn(verbList);
        List<VerbRow> verbRows = transformer.transformToVerbSheet(SHEET);

        assertThat(verbRows.size()).isEqualTo(2);
    }

    @Test
    public void transformToNounSheet() {
        when(sheetsRetriever.getValuesFromSheet(SHEET)).thenReturn(nounList);
        List<NounRow> nounRows = transformer.transformToNounSheet(SHEET);

        assertThat(nounRows.size()).isEqualTo(2);
    }
}