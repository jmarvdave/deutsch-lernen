package application.exam;

import java.io.BufferedWriter;
import java.io.IOException;

class Writer {
    void writeToFile(BufferedWriter bufferedWriter, String content) {
        try {
            bufferedWriter.write(content);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
