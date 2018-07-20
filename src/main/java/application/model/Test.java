package application.model;

import java.util.List;

public class Test {
    private final List<Row> randomVerbs;
    private final List<Row> randomNouns;

    public List<Row> getRandomVerbs() {
        return randomVerbs;
    }

    public List<Row> getRandomNouns() {
        return randomNouns;
    }

    private Test(List<Row> randomVerbs, List<Row> randomNouns) {
        this.randomVerbs = randomVerbs;
        this.randomNouns = randomNouns;
    }

    public static Test from(List<Row> randomVerbs, List<Row> randomNouns) {
        return new Test(randomVerbs, randomNouns);
    }
}
