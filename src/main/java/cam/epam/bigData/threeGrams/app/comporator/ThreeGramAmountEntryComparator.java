package cam.epam.bigData.threeGrams.app.comporator;

import java.util.Comparator;
import java.util.Map;

public class ThreeGramAmountEntryComparator implements Comparator<Map.Entry<String, Integer>> {
    @Override
    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        Integer first = o1.getValue();
        Integer second = o2.getValue();
        return first.compareTo(second);
    }
}
