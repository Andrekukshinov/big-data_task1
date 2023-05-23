package cam.epam.bigData.threeGrams.app.service;

import cam.epam.bigData.threeGrams.app.comporator.ThreeGramAmountEntryComparator;
import cam.epam.bigData.threeGrams.app.converter.MessageToThreeGramConverter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class ThreeGramServiceImpl implements ThreeGramService {

    private final MessageToThreeGramConverter converter;

    public ThreeGramServiceImpl(MessageToThreeGramConverter converter) {
        this.converter = converter;
    }

    @Override
    public Map<String, List<String>> toAuthorThreeGrams(Map<String, List<String>> authorMessages) {
        Set<Map.Entry<String, List<String>>> entries = authorMessages.entrySet();
        Map<String, List<String>> result = new HashMap<>();
        entries.forEach(entry -> {
            List<String> messages = entry.getValue();
            List<String> threeGrams = messages.stream()
                    .flatMap(message -> converter.to3Grams(message).stream())
                    .toList();
            result.put(entry.getKey(), threeGrams);
        });
        return result;
    }

    @Override
    public Map<String, Map<String, Integer>> toAuthorThreeGramAmount(Map<String, List<String>> authorThreeGrams) {
        Map<String, Integer> threeGramAmount = new HashMap<>();
        Map<String, Map<String, Integer>> res = new HashMap<>();
        Set<Map.Entry<String, List<String>>> entries = authorThreeGrams.entrySet();
        for (Map.Entry<String, List<String>> entry : entries) {
            List<String> threeGrams = entry.getValue();
            for (String threeGram : threeGrams) {
                if (threeGramAmount.containsKey(threeGram)) {
                    threeGramAmount.put(threeGram, threeGramAmount.get(threeGram) + 1);
                } else {
                    threeGramAmount.put(threeGram, 1);
                }
            }
            res.put(entry.getKey(), threeGramAmount);
            threeGramAmount = new HashMap<>();
        }
        return res;
    }

    @Override
    public Map<String, Map<String, Integer>> getTopAuthorThreeGrams(Map<String, Map<String, Integer>> authorThreeGramAmount, int topThreeGrams) {
        Map<String, Map<String, Integer>> res = new HashMap<>();
        Comparator<Map.Entry<String, Integer>> comparator = new ThreeGramAmountEntryComparator();

        Set<Map.Entry<String, Map<String, Integer>>> entries = authorThreeGramAmount.entrySet();
        entries.forEach(entry -> populateResultWithTopEntries(topThreeGrams, res, comparator, entry));
        return res;
    }

    private void populateResultWithTopEntries(int topThreeGrams, Map<String, Map<String, Integer>> res, Comparator<Map.Entry<String, Integer>> comparator, Map.Entry<String, Map<String, Integer>> entry) {
        Queue<Map.Entry<String, Integer>> queue = new PriorityQueue<>(topThreeGrams, comparator);
        Map<String, Integer> threeGramAmountMap = entry.getValue();
        Set<Map.Entry<String, Integer>> threeGramEntries = threeGramAmountMap.entrySet();
        for (Map.Entry<String, Integer> threeGramAmount : threeGramEntries) {
            queue.offer(threeGramAmount);
            if (queue.size() > topThreeGrams) {
                queue.poll();
            }
        }
        Map<String, Integer> threeGramAmount = new HashMap<>();
        queue.forEach(threeGramEntry -> threeGramAmount.put(threeGramEntry.getKey(), threeGramEntry.getValue()));
        res.put(entry.getKey(), threeGramAmount);
    }
}
