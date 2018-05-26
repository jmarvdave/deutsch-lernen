package integration;

import application.service.DeutschLernenService;
import application.transformer.ResponseToSheetTransformer;
import application.writer.LatexWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
public class DeutschLernenIntegrationTest {

    @MockBean
    private ResponseToSheetTransformer responseToSheetTransformer;

    @Test
    public void integrationTest() {
        LatexWriter latexWriter = new LatexWriter("23-03-1992");
        DeutschLernenService deutschLernenService = new DeutschLernenService(responseToSheetTransformer, latexWriter);

        deutschLernenService.run();
    }
}
