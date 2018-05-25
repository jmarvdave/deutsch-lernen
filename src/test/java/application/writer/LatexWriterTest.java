package application.writer;

import application.model.NounRow;
import application.model.VerbRow;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;

public class LatexWriterTest {

    @Test
    public void writeTestToFile() {
        LatexWriter latexWriter = new LatexWriter("23-03-1992");

        List<VerbRow> listOfVerbs = ImmutableList.of(new VerbRow("machen", "Ich mache es", "wie schaffen"));
        List<NounRow> listOfNouns = ImmutableList.of(new NounRow("der", "Mann", "der Mann ist albern"));
        latexWriter.writeTestToFile(listOfVerbs, listOfNouns, "testwrite.tex");


    }
}