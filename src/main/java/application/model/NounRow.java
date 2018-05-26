package application.model;

public class NounRow {
    private final String gender;
    private final String noun;
    private final String example;

    public NounRow(String gender, String noun, String example) {
        this.gender = gender;
        this.noun = noun;
        this.example = example;
    }

    public String getNoun() {
        return noun;
    }

    @Override
    public String toString() {
        return "NounRow{" +
                "gender='" + gender + '\'' +
                ", noun='" + noun + '\'' +
                ", example='" + example + '\'' +
                '}';
    }
}
