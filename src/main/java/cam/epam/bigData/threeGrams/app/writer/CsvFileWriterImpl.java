package cam.epam.bigData.threeGrams.app.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

public class CsvFileWriterImpl implements CsvFileWriter {
    @Override
    public void writeCsv(Map<String, Map<String, Integer>> topAuthorThreeGrams, String path) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
            StringBuilder builder = new StringBuilder();
            topAuthorThreeGrams.forEach((author, threeGramAmount) -> {
                builder.append("'").append(author).append("' ");
                threeGramAmount.forEach((threeGram, amount) -> builder.append("'").append(threeGram).append("' "));
                builder.deleteCharAt(builder.length() - 1);
                builder.append("\n");
            });
            bufferedWriter.write(builder.toString());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to write to file " + path, e);

        }
    }
}
