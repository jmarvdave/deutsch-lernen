package application.writer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class WriterTest {

    @Mock
    private BufferedWriter bufferedWriter;

    private Writer writer;

    @Before
    public void setUp() {
        writer = new Writer();
    }

    @Test
    public void writeToDummyFile() {
        Writer.withBufferedWriter(bufferedWriter -> writer.writeToFile(bufferedWriter, "testing"), "testfile.tex");

        try {
            String fileName = "testfile.tex";
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            assertThat(lines.get(0)).isEqualTo("testing");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Test
    public void catchesIOExceptionWhenUnableToWrite() throws IOException {
        Mockito.doThrow(new IOException("testing exception")).when(bufferedWriter).write("blah");
        writer.writeToFile(bufferedWriter, "blah");
    }
}