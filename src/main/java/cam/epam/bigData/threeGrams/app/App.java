package cam.epam.bigData.threeGrams.app;

import cam.epam.bigData.threeGrams.app.converter.MessageToThreeGramConverter;
import cam.epam.bigData.threeGrams.app.converter.MessageToThreeGramConverterImpl;
import cam.epam.bigData.threeGrams.app.reader.JsonFileReader;
import cam.epam.bigData.threeGrams.app.reader.JsonFileReaderImpl;
import cam.epam.bigData.threeGrams.app.service.ThreeGramService;
import cam.epam.bigData.threeGrams.app.service.ThreeGramServiceImpl;
import cam.epam.bigData.threeGrams.app.writer.CsvFileWriter;
import cam.epam.bigData.threeGrams.app.writer.CsvFileWriterImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 */
public class App {

    private static final String SRC = "C:/Users/Andrei_Kukshynau/Downloads/10K.github.jsonl";
    private static final String OUT_FILE = "src/main/resources/output.txt";

    private static final String FILTER_CONDITION = "\"type\":\"PushEvent\"";


    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        JsonFileReader reader = new JsonFileReaderImpl(mapper);
        MessageToThreeGramConverter converter = new MessageToThreeGramConverterImpl();
        ThreeGramService service = new ThreeGramServiceImpl(converter);
        CsvFileWriter writer = new CsvFileWriterImpl();

        Map<String, List<String>> authorMessages = reader.readWithFiltering(SRC, FILTER_CONDITION);
        Map<String, List<String>> authorThreeGrams = service.toAuthorThreeGrams(authorMessages);
        Map<String, Map<String, Integer>> authorThreeGramAmount = service.toAuthorThreeGramAmount(authorThreeGrams);
        Map<String, Map<String, Integer>> topAuthorThreeGrams = service.getTopAuthorThreeGrams(authorThreeGramAmount, 5);
        writer.writeCsv(topAuthorThreeGrams, OUT_FILE);
    }
}

