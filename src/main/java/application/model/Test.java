package application.model;

import java.util.List;

public class Test {
    private final List<VerbRow> randomVerbs;
    private final List<NounRow> randomNouns;

    public List<VerbRow> getRandomVerbs() {
        return randomVerbs;
    }

    public List<NounRow> getRandomNouns() {
        return randomNouns;
    }

    public Test(List<VerbRow> randomVerbs, List<NounRow> randomNouns) {
        this.randomVerbs = randomVerbs;
        this.randomNouns = randomNouns;
    }
}
