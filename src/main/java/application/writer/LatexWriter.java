package application.writer;

import application.model.NounRow;
import application.model.VerbRow;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LatexWriter {

    static Format formatter = new SimpleDateFormat("dd-MM-yyyy");

    private static final String BEGINNING = "\\documentclass{exam}\n" +
            "\\usepackage[utf8]{inputenc}\n" +
            "\\pagenumbering{gobble}\n" +
            "\\rhead{" + formatter.format(new Date(2018,5,11)) + "}\n" +
            "\\lhead{\\textsc{Prüfung:} Schreibe Sätze mit den folgenden Wörtern }\n" +
            "\\begin{document}\n" +
            "\\begin{questions}\n\n";

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
            write(() -> bufferedWriter.write(BEGINNING));

            verbs.forEach((verb) -> {
                write(() -> bufferedWriter.write(String.format(VERB, verb.getVerb())));
            });

            nouns.forEach((noun) -> {
                write(() -> {
                    if (nouns.indexOf(noun) != nouns.size() - 1) {
                        bufferedWriter.write(String.format(NOUN, noun.getNoun()));
                    } else {
                        bufferedWriter.write(String.format(LAST_NOUN, noun.getNoun()));
                    }
                });
            });

            write(() -> bufferedWriter.write(ENDING));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void write(BufferedWriterConsumer action) {
        try {
            action.writeOutput();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
