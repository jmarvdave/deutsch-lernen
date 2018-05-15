package application.writer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;

class Writer {
    static void withBufferedWriter(Consumer<BufferedWriter> consumer) {
        Path path = Paths.get("testing.tex");

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path);) {
            consumer.accept(bufferedWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void writeToFile(BufferedWriter bufferedWriter, String content) {
        try {
            bufferedWriter.write(content);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
