package cam.epam.bigData.threeGrams.app.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageToThreeGramConverterImpl implements MessageToThreeGramConverter {
    @Override
    public List<String> to3Grams(String message) {
        List<String> words = Arrays.stream(message.split(" ")).toList();
        List<String> res = new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            StringBuilder stringBuilder = new StringBuilder();
            if (i + 2 < words.size()){
                append3GramToBuilder(stringBuilder, words, i);
            } else {
                break;
            }
            res.add(stringBuilder.toString());
        }
        return res;
    }

    private void append3GramToBuilder(StringBuilder stringBuilder, List<String> words, int wordsAmount) {
        for (int tmpRunner = wordsAmount; tmpRunner < wordsAmount + 3; tmpRunner++) {
            stringBuilder.append(words.get(tmpRunner))
                    .append(" ");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
    }

}
