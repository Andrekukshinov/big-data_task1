package cam.epam.bigData.threeGrams.app.writer;

import java.util.Map;

public interface CsvFileWriter {
    void writeCsv(Map<String, Map<String, Integer>> topAuthorThreeGrams, String path);
}
