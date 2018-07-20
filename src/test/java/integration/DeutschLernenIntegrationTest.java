package integration;

import application.configuration.ApplicationConfiguration;
import application.configuration.ApplicationProperties;
import application.configuration.CredentialAuthorizer;
import application.configuration.LatexWriterConfiguration;
import application.configuration.SheetsConfiguration;
import application.configuration.SheetsConnector;
import application.model.Row;
import application.service.DeutschLernenService;
import application.service.ResultsWeigher;
import application.transformer.ResponseToSheetTransformer;
import application.writer.LatexWriter;
import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
public class DeutschLernenIntegrationTest {

    private static final String VERB_SHEET_ID = "1MpUyZq-UDfvHAt2jZN45cEQnyeSk6Nr56_slyQUMcDI";
    private static final String NOUN_SHEET_ID = "1-JL2h2SrndIbNkOGJmueRxcZwqaSl90meGc6fkeJ-Go";

    @MockBean
    private ResponseToSheetTransformer responseToSheetTransformer;

    @MockBean
    private ApplicationProperties applicationProperties;

    @MockBean
    private SheetsConfiguration sheetsConfiguration;

    @MockBean
    private CredentialAuthorizer credentialAuthorizer;

    @MockBean
    private ResultsWeigher resultsWeigher;

    @MockBean
    private SheetsConnector sheetsConnector;

    @Test
    public void integrationTest() throws IOException {
        //prepares the sheet transformer
        List<Row> listOfVerbs = ImmutableList.of(new Row("machen", Row.TYPE.VERB));
        List<Row> listOfNouns = ImmutableList.of(new Row( "Mann", Row.TYPE.NOUN));
        when(responseToSheetTransformer.transformToVerbSheet(VERB_SHEET_ID)).thenReturn(listOfVerbs);
        when(resultsWeigher.apply(listOfVerbs)).thenReturn(listOfVerbs);
        when(responseToSheetTransformer.transformToNounSheet(NOUN_SHEET_ID)).thenReturn(listOfNouns);
        when(resultsWeigher.apply(listOfNouns)).thenReturn(listOfNouns);

        //google sheets configurations
        when(sheetsConfiguration.transformer(credentialAuthorizer, sheetsConnector)).thenReturn(responseToSheetTransformer);

        //prepares the latex writer
        when(applicationProperties.getDate()).thenReturn("1992-03-23");
        LatexWriterConfiguration latexWriterConfiguration = new LatexWriterConfiguration(applicationProperties);
        LatexWriter latexWriter = latexWriterConfiguration.latexWriter();

        ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration(sheetsConfiguration.transformer(credentialAuthorizer, sheetsConnector), latexWriter, resultsWeigher);

        DeutschLernenService application = applicationConfiguration.application();

        application.run();

        List<String> lines = Files.readAllLines(Paths.get("testing.tex"));
        assertThat(lines.contains("\\question machen")).isTrue();
        assertThat(lines.contains("\\question Mann")).isTrue();
    }
}
