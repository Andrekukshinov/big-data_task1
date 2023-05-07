package cam.epam.bigData.threeGrams.app.converter;

import java.util.List;

public interface MessageToThreeGramConverter {
    List<String> to3Grams(String message);
}
