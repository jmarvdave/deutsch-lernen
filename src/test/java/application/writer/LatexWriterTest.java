package application.writer;

import application.model.NounRow;
import application.model.VerbRow;
import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class LatexWriterTest {

    @Test
    public void writeTestToFile() throws IOException {
        LatexWriter latexWriter = new LatexWriter("23-03-1992");
        String fileName = "testwrite.tex";

        List<VerbRow> listOfVerbs = ImmutableList.of(new VerbRow("machen", "Ich mache es", "wie schaffen"));
        List<NounRow> listOfNouns = ImmutableList.of(new NounRow("der", "Mann", "der Mann ist albern"));
        latexWriter.writeTestToFile(listOfVerbs, listOfNouns, fileName);

        List<String> lines = Files.readAllLines(Paths.get(fileName));
        assertThat(lines.contains("\\question machen")).isTrue();
        assertThat(lines.contains("\\question Mann")).isTrue();
    }
}