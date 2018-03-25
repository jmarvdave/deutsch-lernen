package application.utility;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class LatexWriter {

    private static final String BEGINNING = "\\documentclass{exam}\n" +
            "\\usepackage[utf8]{inputenc}\n" +
            "\\pagenumbering{gobble}\n" +
            "\n" +
            "\\begin{document}\n" +
            "\n" +
            "\\title{Prüfung}\n" +
            "\\date{24-03-2018}\n" +
            "\\author{John-Marvin Davis}\n" +
            "\\maketitle\n" +
            "\\begin{questions}\n\n";

    private static final String VERB = "\\question Schreib einen Satz mit dem Verb \\textit{%s}\n" +
            "\\vspace{\\stretch{1}}\n\n";

    private static final String ENDING = "\\end{questions}\n" +
            "\\end{document}";

    public void writeVerbs(List<String> verbs) {
        Path path = Paths.get("testing.tex");
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path);) {
            write(() -> bufferedWriter.write(BEGINNING));

            verbs.forEach((verb) -> {
                write(() -> bufferedWriter.write(String.format(VERB, verb)));
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
