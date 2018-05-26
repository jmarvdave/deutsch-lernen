package application.model;

public class VerbRow {
    private final String verb;
    private final String sentence;
    private final String notes;

    public VerbRow(String verb, String sentence, String notes) {
        this.verb = verb;
        this.sentence = sentence;
        this.notes = notes;
    }

    public String getVerb() {
        return verb;
    }

    public String getSentence() {
        return sentence;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return "VerbRow{" +
                "verb='" + verb + '\'' +
                ", sentence='" + sentence + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
