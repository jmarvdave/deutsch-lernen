package integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import application.algorithm.AlgorithmExecutor;
import application.configuration.ApplicationConfiguration;
import application.configuration.ApplicationProperties;
import application.exam.ExamWriterConfiguration;
import application.row.ResponseToRowsTransformer;
import application.row.Row;
import application.service.DeutschLernenService;
import application.exam.ExamWriter;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
public class DeutschLernenIntegrationTest {

  private static final String VERB_SHEET_ID = "1MpUyZq-UDfvHAt2jZN45cEQnyeSk6Nr56_slyQUMcDI";
  private static final String NOUN_SHEET_ID = "1-JL2h2SrndIbNkOGJmueRxcZwqaSl90meGc6fkeJ-Go";

  @MockBean
  private ResponseToRowsTransformer responseToRowsTransformer;
  @MockBean
  private ApplicationProperties applicationProperties;
  @MockBean
  private AlgorithmExecutor algorithmExecutor;

  @Test
  public void integrationTest() throws IOException {
    //prepares the sheet transformer
    List<Row> listOfVerbs = ImmutableList.of(new Row("machen", Row.TYPE.VERB));
    List<Row> listOfNouns = ImmutableList.of(new Row("Mann", Row.TYPE.NOUN));
    when(responseToRowsTransformer.transformToVerbSheet(VERB_SHEET_ID)).thenReturn(listOfVerbs);
    when(algorithmExecutor.apply(listOfVerbs)).thenReturn(listOfVerbs);
    when(responseToRowsTransformer.transformToNounSheet(NOUN_SHEET_ID)).thenReturn(listOfNouns);
    when(algorithmExecutor.apply(listOfNouns)).thenReturn(listOfNouns);

    //prepares the latex writer
    when(applicationProperties.getDate()).thenReturn("1992-03-23");
    ExamWriterConfiguration examWriterConfiguration = new ExamWriterConfiguration(
        applicationProperties);
    ExamWriter examWriter = examWriterConfiguration.latexWriter();

    ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration(
        responseToRowsTransformer, examWriter, algorithmExecutor);

    DeutschLernenService application = applicationConfiguration.application();

    application.run();

    List<String> lines = Files.readAllLines(Paths.get("testing.tex"));
    assertThat(lines.contains("\\question machen")).isTrue();
    assertThat(lines.contains("\\question Mann")).isTrue();
  }
}
