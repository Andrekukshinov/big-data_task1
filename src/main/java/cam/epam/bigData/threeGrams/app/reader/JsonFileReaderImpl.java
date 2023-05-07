package cam.epam.bigData.threeGrams.app.reader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonFileReaderImpl implements JsonFileReader {

    private static final String COMMITS = "commits";
    private static final String NAME = "name";
    private static final String MESSAGE = "message";
    private static final String PAYLOAD = "payload";
    private static final String AUTHOR = "author";
    private final ObjectMapper mapper;

    public JsonFileReaderImpl(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public Map<String, List<String>> readWithFiltering(String path, String filterBy) {//path, "type":"PushEvent"
        Map<String, List<String>> res = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(filterBy)) {
                    populateMapWithAutherAndCommitMessages(res, line);
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read from file " + path, e);
        }
        return res;
    }

    private void populateMapWithAutherAndCommitMessages(Map<String, List<String>> res, String line) throws JsonProcessingException {
        JsonNode root = mapper.readTree(line);
        JsonNode payload = root.get(PAYLOAD);
        JsonNode commits = payload.get(COMMITS);
        commits.forEach(commit -> {
            JsonNode authorNode = commit.get(AUTHOR);
            String author = authorNode.get(NAME).asText();
            String message = commit.get(MESSAGE).asText();
            String lowerCaseMessage = message.toLowerCase();
            String noNewLines = lowerCaseMessage.replaceAll("\n+", " ");
            String expectedMessage = noNewLines.replaceAll("\\p{Punct}", "");
            if (res.containsKey(author)) {
                res.get(author).add(expectedMessage);
            } else {
                List<String> msgList = new ArrayList<>(){{add(expectedMessage);}};
                res.put(author, msgList);
            }
        });
    }
}
