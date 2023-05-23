package cam.epam.bigData.threeGrams.app.service;

import java.util.List;
import java.util.Map;

public interface ThreeGramService {
    Map<String, List<String>> toAuthorThreeGrams(Map<String, List<String>> authorMessages);
    Map<String, Map<String, Integer>> toAuthorThreeGramAmount(Map<String, List<String>> authorThreeGrams);
    Map<String, Map<String, Integer>> getTopAuthorThreeGrams(Map<String, Map<String, Integer>> authorThreeGramAmount, int topThreeGrams);
}
