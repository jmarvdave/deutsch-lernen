package application.telegram;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class TelegramNotifier {
  public void sendReport(String algorithm, int numberOfverbs, int numberOfAjectives,
      int numberOfNouns) {
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append(
        "Deine Prüfung wurde mit den Einstellungen von " + LocalDate.now().getDayOfWeek()
            .getDisplayName(
                TextStyle.FULL, Locale.GERMANY) + " erstellt");
    stringBuilder.append("Algorithmus: " + algorithm);
    stringBuilder.append("Verben: " + numberOfverbs);
    stringBuilder.append("Adjekitve: " + numberOfAjectives);
    stringBuilder.append("Nomen: " + numberOfNouns);
  }
}
