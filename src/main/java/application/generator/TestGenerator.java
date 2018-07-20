package application.generator;

import application.model.Row;
import application.model.Test;

import java.security.SecureRandom;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class TestGenerator {

    private final List<Row> verbRows;
    private final List<Row> nounRows;

    public TestGenerator(List<Row> verbRows, List<Row> nounRows) {
        this.verbRows = verbRows;
        this.nounRows = nounRows;
    }

    public Test generate(int numberOfVerbs, int numberOfNouns) {
        List<Row> randomVerbs = generateRandomIntegersForVerbs(new HashMap<>(), verbRows.size(), numberOfVerbs)
                .stream()
                .map(verbRows::get)
                .sorted(Comparator.comparing(Row::getPrimaryElement))
                .collect(Collectors.toList());

        List<Row> randomNouns = generateRandomIntegersForNouns(new HashMap<>(), nounRows.size(), numberOfNouns)
                .stream()
                .map(nounRows::get)
                .sorted(Comparator.comparing(Row::getPrimaryElement))
                .collect(Collectors.toList());

        return Test.from(randomVerbs, randomNouns);
    }

    private Set<Integer> generateRandomIntegersForVerbs(Map<String, Integer> wordToIndex, int totalNumberOfElements, int desiredSize) {
        if (wordToIndex.size() == desiredSize || wordToIndex.size() == totalNumberOfElements) {
            return new HashSet<>(wordToIndex.values());
        } else {
            Random rand = new SecureRandom();
            int n = rand.nextInt(totalNumberOfElements);
            String currentVerb = verbRows.get(n).getPrimaryElement();
            if (!wordToIndex.containsKey(currentVerb)) {
                wordToIndex.put(currentVerb, n);
            }
            return generateRandomIntegersForVerbs(wordToIndex, totalNumberOfElements, desiredSize);
        }
    }

    private Set<Integer> generateRandomIntegersForNouns(Map<String, Integer> wordToIndex, int totalNumberOfElements, int desiredSize) {
        if (wordToIndex.size() == desiredSize || wordToIndex.size() == totalNumberOfElements) {
            return new HashSet<>(wordToIndex.values());
        } else {
            Random rand = new SecureRandom();
            int n = rand.nextInt(totalNumberOfElements);
            String currentNoun = nounRows.get(n).getPrimaryElement();
            if (!wordToIndex.containsKey(currentNoun)) {
                wordToIndex.put(currentNoun, n);
            }
            return generateRandomIntegersForNouns(wordToIndex, totalNumberOfElements, desiredSize);
        }
    }
}
