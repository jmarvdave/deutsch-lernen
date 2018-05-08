package application.service;

import application.transformer.ResponseToSheetTransformer;
import application.writer.LatexWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class DeutschLernenServiceTest {

    @Mock
    private ResponseToSheetTransformer transformer;

    @Mock
    private LatexWriter writer;

    @Test
    public void run() {
        DeutschLernenService service = new DeutschLernenService(transformer, writer);

        service.run();

        Mockito.verify(transformer, times(1)).transformToVerbSheet("1MpUyZq-UDfvHAt2jZN45cEQnyeSk6Nr56_slyQUMcDI");
        Mockito.verify(transformer, times(1)).transformToNounSheet("1-JL2h2SrndIbNkOGJmueRxcZwqaSl90meGc6fkeJ-Go");
    }
}