package application.utility;

import java.io.IOException;

@FunctionalInterface
public interface BufferedWriterConsumer {
    void writeOutput() throws IOException;
}
