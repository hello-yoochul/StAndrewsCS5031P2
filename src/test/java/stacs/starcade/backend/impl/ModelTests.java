package stacs.starcade.backend.impl;

import java.util.List;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stacs.starcade.shared.Card;
import stacs.starcade.shared.Checks;
import stacs.starcade.shared.ICard;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ModelTests {

    private Model model;
    @BeforeEach
    public void setup() {
        model = new Model();
    }

    @Test
    void testCardsSetup() {
        assertEquals(81,model.getAllCards().size());
    }

    @Test
    void checkTwelveCards() {
        List<ICard> cards = model.getTwelveCards();
        assertEquals(12, cards.size());
        int numSets = 0;
        for (int i = 0; i < cards.size() - 2; i++) {
            for (int j = i; j < cards.size() - 1; j++){
                if (j == i){
                    continue;
                }

                for (int k = j; k < cards.size(); k++){
                    if (k == j){
                        continue;
                    }

                    if (Checks.isSet(new ICard[]{
                      cards.get(i),
                      cards.get(j),
                      cards.get(k)
                    })) {
                        numSets ++;
                    }
                }
            }
        }
        assertEquals(5, numSets);

        for (int i = 0; i < cards.size() - 1; i++) {
            for (int j = i; j < cards.size(); j++) {
                if (j == i){
                    continue;
                }
                assertNotEquals(cards.get(i), cards.get(j));
            }
        }
    }

    @Test
    void checkCardsPersist() {
        List<ICard> cards = model.getTwelveCards();
        List<ICard> cards2 = model.getTwelveCards();
        assertEquals(cards.size(), cards2.size());
    }

}
