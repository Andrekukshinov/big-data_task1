package cam.epam.bigData.threeGrams.app.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonFileReaderImplTest {

    private static final String SRC_TXT = "src/test/resources/jsonSrc.txt";
    private static final String FILTER_CONDITION = "\"type\":\"PushEvent\"";

    private ObjectMapper mapper = new ObjectMapper();

    private JsonFileReaderImpl reader = new JsonFileReaderImpl(mapper);
    @Test
    void readWithFilteringReturnFilteredDataWithFormattedMessage() {
        Map<String, List<String>> expected = Map.of("Talha A", List.of("added css support and menu", "react hooks toggling", "refactored menu hooks"));

        Map<String, List<String>> stringListMap = reader.readWithFiltering(SRC_TXT, FILTER_CONDITION);

        assertEquals(expected, stringListMap);
    }
}