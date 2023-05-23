package cam.epam.bigData.threeGrams.app.writer;

import java.util.Map;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CsvFileWriterImplTest {

    private static final String OUT_FILE = "src/test/resources/output.txt";



    @Test
    void writeCsv() {
        Map<String, Map<String, Integer>> init = Map.of(
                "Talha A", Map.of("support and menu", 10000,  "added css support", 2000, "react hooks toggling", 400, "refactored menu hooks", 300, "refactored menu dooks", 17),
                "Talha B", Map.of("support and menu", 1000, "css support and", 111, "added css support", 11, "react hooks toggling", 2)
        );

        CsvFileWriterImpl csvFileWriter = new CsvFileWriterImpl();
        csvFileWriter.writeCsv(init, OUT_FILE);
    }
}