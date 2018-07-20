package application.service;

import application.model.Row;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultsWeigherTest {

    private static final Row verbRow1 = new Row("machen", Row.TYPE.VERB);
    private static final Row verbRow2 = new Row("versehen", Row.TYPE.VERB);
    private static final Row verbRow3 = new Row("erlauben", Row.TYPE.VERB);

    private static final Row nounRow1 = new Row("Haus", Row.TYPE.NOUN);
    private static final Row nounRow2 = new Row("Hund", Row.TYPE.NOUN);
    private static final Row nounRow3 = new Row("Gabel", Row.TYPE.NOUN);

    private static final List<Row> verbRows = ImmutableList.of(verbRow1, verbRow2, verbRow3);
    private static final List<Row> nounRows = ImmutableList.of(nounRow1, nounRow2, nounRow3);

    private ResultsWeigher resultsWeigher;

    @Before
    public void setUp() {
        resultsWeigher = new ResultsWeigher();
    }

    @Test
    public void shouldMakeVerbListWhereLastVerbAppearsTheMost() {
        List<Row> verbRows = resultsWeigher.apply(ResultsWeigherTest.verbRows);

        assertThat(verbRows.size()).isEqualTo(6);
        assertThat(verbRows
                .stream()
                .filter((verbRow -> "machen".equals(verbRow.getPrimaryElement())))
                .count())
                .isEqualTo(1L);
        assertThat(verbRows
                .stream()
                .filter((verbRow -> "versehen".equals(verbRow.getPrimaryElement())))
                .count())
                .isEqualTo(2L);
        assertThat(verbRows
                .stream()
                .filter((verbRow -> "erlauben".equals(verbRow.getPrimaryElement())))
                .count())
                .isEqualTo(3L);
    }

    @Test
    public void shouldMakeNounListWhereLastVerbAppearsTheMost() {
        List<Row> nounRows = resultsWeigher.apply(ResultsWeigherTest.nounRows);

        assertThat(nounRows.size()).isEqualTo(6);
        assertThat(nounRows
                .stream()
                .filter((nounRow -> "Haus".equals(nounRow.getPrimaryElement())))
                .count())
                .isEqualTo(1L);
        assertThat(nounRows
                .stream()
                .filter((nounRow -> "Hund".equals(nounRow.getPrimaryElement())))
                .count())
                .isEqualTo(2L);
        assertThat(nounRows
                .stream()
                .filter((nounRow -> "Gabel".equals(nounRow.getPrimaryElement())))
                .count())
                .isEqualTo(3L);
    }
}