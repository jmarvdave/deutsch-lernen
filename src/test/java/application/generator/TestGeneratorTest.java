package application.generator;

import application.generator.TestGenerator;
import application.model.NounRow;
import application.model.NounSheet;
import application.model.VerbRow;
import application.model.VerbSheet;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestGeneratorTest {

    @Mock
    private VerbSheet verbSheet;
    @Mock
    private NounSheet nounSheet;

    private TestGenerator testGenerator;

    @Before
    public void setUp() {
        VerbRow verbRow1 = new VerbRow("machen","ich mache es", "machen ist sehr oft benützt");
        VerbRow verbRow2 = new VerbRow("lachen","wir lachen zu viel", "lächeln ist nicht das gleiche Ding");
        NounRow nounRow = new NounRow("der","Laptop", "wird bearbeitet");

        when(verbSheet.getValues()).thenReturn(ImmutableList.of(verbRow1, verbRow2));
        when(nounSheet.getValues()).thenReturn(ImmutableList.of(nounRow));
        testGenerator = new TestGenerator(verbSheet, nounSheet);
    }

    @Test
    public void testGenerate() {
        application.model.Test generatedTest = testGenerator.generate();

        assertThat(generatedTest.getRandomVerbs().size()).isEqualTo(2);
        assertThat(generatedTest.getRandomNouns().size()).isEqualTo(1);
    }
}