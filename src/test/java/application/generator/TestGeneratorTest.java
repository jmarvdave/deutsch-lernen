package application.generator;

import application.model.Row;
import application.model.Row.TYPE;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class TestGeneratorTest {

    private TestGenerator testGenerator;

    @Before
    public void setUp() {
        Row verbRow1 = new Row("machen", TYPE.VERB);
        Row verbRow2 = new Row("lachen", TYPE.VERB);
        Row nounRow1 = new Row("Laptop", TYPE.NOUN);
        Row nounRow2 = new Row("Gabel", TYPE.NOUN);
        Row nounRow3 = new Row("Messer", TYPE.NOUN);

        ImmutableList<Row> verbRows = ImmutableList.of(verbRow1, verbRow2, verbRow2);
        ImmutableList<Row> nounRows = ImmutableList.of(nounRow1, nounRow2, nounRow3, nounRow3, nounRow3);
        testGenerator = new TestGenerator(verbRows, nounRows);
    }

    @Test
    public void testGenerate() {
        application.model.Test generatedTest = testGenerator.generate(2, 3);

        assertThat(generatedTest.getRandomVerbs().size()).isEqualTo(2);
        assertThat(generatedTest.getRandomNouns().size()).isEqualTo(3);
    }
}