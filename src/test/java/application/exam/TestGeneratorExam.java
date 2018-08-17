package application.exam;

import static org.assertj.core.api.Assertions.assertThat;

import application.exam.Exam;
import application.exam.ExamGenerator;
import application.row.Row;
import application.row.Row.TYPE;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestGeneratorExam {

    private ExamGenerator examGenerator;

    @Before
    public void setUp() {
        Row verbRow1 = new Row("machen", TYPE.VERB);
        Row verbRow2 = new Row("lachen", TYPE.VERB);
        Row adjectiveRow1 = new Row("lachen", TYPE.ADJECTIVE);
        Row adjectiveRow2 = new Row("lachen", TYPE.ADJECTIVE);
        Row nounRow1 = new Row("Laptop", TYPE.NOUN);
        Row nounRow2 = new Row("Gabel", TYPE.NOUN);
        Row nounRow3 = new Row("Messer", TYPE.NOUN);

        ImmutableList<Row> verbRows = ImmutableList.of(verbRow1, verbRow2, verbRow2);
        ImmutableList<Row> nounRows = ImmutableList.of(nounRow1, nounRow2, nounRow3, nounRow3, nounRow3);
        ImmutableList<Row> adjectiveRows = ImmutableList.of(adjectiveRow1, adjectiveRow2);
        examGenerator = new ExamGenerator(verbRows, nounRows, adjectiveRows);
    }

    @Test
    public void testGenerate() {
        Exam generatedTest = examGenerator.generate(2, 3, 2);

        assertThat(generatedTest.getRandomVerbs().size()).isEqualTo(2);
        assertThat(generatedTest.getRandomNouns().size()).isEqualTo(3);
    }
}