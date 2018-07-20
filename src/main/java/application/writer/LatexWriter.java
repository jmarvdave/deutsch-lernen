package application.writer;

import application.model.Row;

import java.util.List;

public class LatexWriter {

    private final String date;
    private final Writer writer;

    public LatexWriter(String date) {
        this(date, new Writer());
    }

    public LatexWriter(String date, Writer writer) {
        this.date = date;
        this.writer = writer;
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

    public void writeTestToFile(List<Row> verbs, List<Row> nouns, String fileName) {
        Writer.withBufferedWriter((bufferedWriter) -> {
            writer.writeToFile(bufferedWriter, beginning(date));

            verbs.forEach((verb) -> writer.writeToFile(bufferedWriter, String.format(VERB, verb.getPrimaryElement())));

            nouns.forEach((noun) -> {
                if (nouns.indexOf(noun) == nouns.size() - 1) {
                    writer.writeToFile(bufferedWriter, String.format(LAST_NOUN, noun.getPrimaryElement()));
                } else {
                    writer.writeToFile(bufferedWriter, String.format(NOUN, noun.getPrimaryElement()));
                }
            });

            writer.writeToFile(bufferedWriter, ENDING);
        }, fileName);
    }


}
