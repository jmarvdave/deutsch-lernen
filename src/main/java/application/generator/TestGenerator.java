package application.generator;

import application.model.NounRow;
import application.model.NounSheet;
import application.model.Test;
import application.model.VerbRow;
import application.model.VerbSheet;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class TestGenerator {
    private static final int NUMBER_OF_VERBS = 14;
    private static final int NUMBER_OF_NOUNS = 4;

    private final VerbSheet verbValues;
    private final NounSheet nounValues;

    public TestGenerator(VerbSheet verbSheet, NounSheet nounSheet) {
        this.verbValues = verbSheet;
        this.nounValues = nounSheet;
    }

    public Test generate() {
        List<VerbRow> allVerbs = verbValues.getValues();

        List<NounRow> allNouns = nounValues.getValues();

        List<VerbRow> randomVerbs = generateRandomIntegers(new HashSet<>(), allVerbs.size(), NUMBER_OF_VERBS)
                .stream()
                .map(allVerbs::get)
                .collect(Collectors.toList());

        List<NounRow> randomNouns = generateRandomIntegers(new HashSet<>(), allNouns.size(), NUMBER_OF_NOUNS)
                .stream()
                .map(allNouns::get)
                .collect(Collectors.toList());

        return new Test(randomVerbs, randomNouns);
    }

    private Set<Integer> generateRandomIntegers(Set<Integer> numbers, int numbersOfVerbs, int desiredSize) {
        if (numbers.size() == desiredSize || numbers.size() == numbersOfVerbs) {
            return numbers;
        } else {
            Random rand = new Random();
            int n = rand.nextInt(numbersOfVerbs);
            numbers.add(n);
            return generateRandomIntegers(numbers, numbersOfVerbs, desiredSize);
        }
    }
}
