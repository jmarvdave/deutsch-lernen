package application.exam;

import application.row.Row;
import java.util.List;

public class Exam {

    private final List<Row> randomAjectives;
    private final List<Row> randomVerbs;
    private final List<Row> randomNouns;

    public List<Row> getRandomVerbs() {
        return randomVerbs;
    }

    public List<Row> getRandomNouns() {
        return randomNouns;
    }

    public List<Row> getRandomAdjectives() {
        return randomAjectives;
    }

    private Exam(List<Row> randomVerbs, List<Row> randomNouns, List<Row> randomAjectives) {
        this.randomVerbs = randomVerbs;
        this.randomNouns = randomNouns;
        this.randomAjectives = randomAjectives;
    }

    public static Exam from(List<Row> randomVerbs, List<Row> randomNouns, List<Row> randomAjectives) {
        return new Exam(randomVerbs, randomNouns, randomAjectives);
    }
}
