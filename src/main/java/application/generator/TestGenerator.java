package application.generator;

import application.model.NounRow;
import application.model.Test;
import application.model.VerbRow;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class TestGenerator {
    private static final int NUMBER_OF_VERBS = 14;
    private static final int NUMBER_OF_NOUNS = 4;
    private final List<VerbRow> verbRows;
    private final List<NounRow> nounRows;

    public TestGenerator(List<VerbRow> verbRows, List<NounRow> nounRows) {
        this.verbRows = verbRows;
        this.nounRows = nounRows;
    }

    public Test generate() {
        List<VerbRow> randomVerbs = generateRandomIntegers(new HashSet<>(), verbRows.size(), NUMBER_OF_VERBS)
                .stream()
                .map(verbRows::get)
                .collect(Collectors.toList());

        List<NounRow> randomNouns = generateRandomIntegers(new HashSet<>(), nounRows.size(), NUMBER_OF_NOUNS)
                .stream()
                .map(nounRows::get)
                .collect(Collectors.toList());

        return new Test(randomVerbs, randomNouns);
    }

    private Set<Integer> generateRandomIntegers(Set<Integer> numbers, int totalNumberOfElements, int desiredSize) {
        if (numbers.size() == desiredSize || numbers.size() == totalNumberOfElements) {
            return numbers;
        } else {
            Random rand = new Random();
            int n = rand.nextInt(totalNumberOfElements);
            numbers.add(n);
            return generateRandomIntegers(numbers, totalNumberOfElements, desiredSize);
        }
    }
}
