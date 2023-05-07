package cam.epam.bigData.threeGrams.app.converter;

import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MessageToThreeGramConverterImplTest {

    private static final String INIT_STRING = "added css support and menu";
    private static final List<String> EXPECTED = List.of("added css support", "css support and", "support and menu");

    private final MessageToThreeGramConverter converter = new MessageToThreeGramConverterImpl();

    @Test
    void to3Grams() {
        List<String> actual = converter.to3Grams(INIT_STRING);

        assertEquals(EXPECTED, actual);

    }
}