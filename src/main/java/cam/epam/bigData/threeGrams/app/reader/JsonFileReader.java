package cam.epam.bigData.threeGrams.app.reader;

import java.util.List;
import java.util.Map;

public interface JsonFileReader {
    Map<String, List<String>> readWithFiltering(String path, String filterBy);
}
