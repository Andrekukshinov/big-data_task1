package cam.epam.bigData.threeGrams.app.service;

import cam.epam.bigData.threeGrams.app.converter.MessageToThreeGramConverter;
import cam.epam.bigData.threeGrams.app.converter.MessageToThreeGramConverterImpl;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThreeGramServiceImplTest {
    private final MessageToThreeGramConverter converter = new MessageToThreeGramConverterImpl();

    private final ThreeGramService service = new ThreeGramServiceImpl(converter);

    @Test
    void toAuthorThreeGrams() {
        Map<String, List<String>> init = Map.of("Talha A", List.of("added css support and menu", "react hooks toggling", "refactored menu hooks"));
        Map<String, List<String>> expected = Map.of("Talha A", List.of("added css support", "css support and", "support and menu", "react hooks toggling", "refactored menu hooks"));

        Map<String, List<String>> actual = service.toAuthorThreeGrams(init);

        assertEquals(expected, actual);
    }

    @Test
    void toAuthorThreeGramAmount() {
        Map<String, List<String>> init = Map.of(
                "Talha A", List.of("added css support", "css support and", "support and menu", "react hooks toggling", "refactored menu hooks", "support and menu"),
                "Talha B", List.of("added css support", "css support and", "support and menu", "react hooks toggling", "refactored menu hooks")
        );
        Map<String, Map<String, Integer>> expected = Map.of(
                "Talha A", Map.of("support and menu", 2, "css support and", 1, "added css support", 1, "react hooks toggling", 1, "refactored menu hooks", 1),
                "Talha B", Map.of("support and menu", 1, "css support and", 1, "added css support", 1, "react hooks toggling", 1, "refactored menu hooks", 1)
        );

        Map<String, Map<String, Integer>> actual = service.toAuthorThreeGramAmount(init);

        assertEquals(expected, actual);

    }

    @Test
    void getTopAuthorThreeGrams() {

        Map<String, Map<String, Integer>> init = Map.of(
                "Talha A", Map.of("support and menu", 19, "css support and", 1, "added css support", 20, "react hooks toggling", 30, "refactored menu hooks", 40, "refactored menu dooks", 17, "refactor menu books", 14, "refactor menu dooks", 10),
                "Talha B", Map.of("support and menu", 1000, "css support and", 11234, "added css support", 1, "react hooks toggling", 2)
        );
        Map<String, Map<String, Integer>> expected = Map.of(
                "Talha A", Map.of("support and menu", 19,  "added css support", 20, "react hooks toggling", 30, "refactored menu hooks", 40, "refactored menu dooks", 17),
                "Talha B", Map.of("support and menu", 1000, "css support and", 11234, "added css support", 1, "react hooks toggling", 2)
        );

        Map<String, Map<String, Integer>> actual = service.getTopAuthorThreeGrams(init, 5);

        assertEquals(expected, actual);
    }

}