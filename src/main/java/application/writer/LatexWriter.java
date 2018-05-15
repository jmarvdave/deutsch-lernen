package application.writer;

import application.model.NounRow;
import application.model.VerbRow;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class LatexWriter {

    private final String date;

    public LatexWriter(String date) {
        this.date = date;
    }

    private String beginning(String date) {
        return "\\documentclass{exam}\n" +
                "\\usepackage[utf8]{inputenc}\n" +
                "\\usepackage[bottom=0.0in,top=0.5in]{geometry}\n" +
                "\\pagenumbering{gobble}\n" +
                "\\rhead{" + date + "}\n" +
                "\\lhead{\\textsc{Prüfung:} Schreibe Sätze mit den folgenden Wörtern }\n" +
                "\\begin{document}\n" +
                "\\begin{questions}\n\n";
    }

    private static final String VERB = "\\question %s\n" +
            "\\vspace{1.5\\baselineskip}\n\n";
    private static final String REGULAR_SPACE = "\\vspace{1.5\\baselineskip}\n\n";
    private static final String NOUN = "\\question %s\n" + REGULAR_SPACE;
    private static final String LAST_NOUN = "\\question %s\n";

    private static final String ENDING = "\\end{questions}\n" +
            "\\end{document}";

    public void writeTestToFile(List<VerbRow> verbs, List<NounRow> nouns) {
        Path path = Paths.get("testing.tex");

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path);) {
            writeToFile(bufferedWriter, beginning(date));

            verbs.forEach((verb) -> writeToFile(bufferedWriter, String.format(VERB, verb.getVerb())));

            nouns.forEach((noun) -> {
                if (nouns.indexOf(noun) == nouns.size() - 1) {
                    writeToFile(bufferedWriter, String.format(LAST_NOUN, noun.getNoun()));
                } else {
                    writeToFile(bufferedWriter, String.format(NOUN, noun.getNoun()));
                }
            });

            bufferedWriter.write(ENDING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(BufferedWriter bufferedWriter, String content) {
        try {
            bufferedWriter.write(content);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
