package stacs.starcade.frontend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stacs.starcade.frontend.model.Card;
import stacs.starcade.frontend.model.FrontendModel;
import stacs.starcade.frontend.model.ICard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static stacs.starcade.frontend.model.IFrontendModel.*;

public class ModelTests {
    FrontendModel model;
    Random random = new Random();

    @BeforeEach
    void setup() {
        model = new FrontendModel();
//        model = mock(FrontendModel.class);
    }

    @Test
    void mustSetPlayerId() {
        int anyPlayerId = random.nextInt(100);
        model.setPlayerId(anyPlayerId);
        assertThat(model.getPlayerId(), is(equalTo(anyPlayerId)));
    }

    @Test
    void mustSetCards() {
        int mockPlayerId = random.nextInt(100);
        model.setPlayerId(mockPlayerId);
        List<ICard> cards = new ArrayList<>();

        int anyNumberOfCards = random.nextInt(100);
        for (int i = 0; i < anyNumberOfCards; i++) {
            cards.add(mock(ICard.class));
        }

        model.setUpCard(cards);
        assertThat(anyNumberOfCards, is(equalTo(model.getCards().size())));
    }

    @Test
    void mustChangeGameStatus() {
        model.setGameStatus(GameStatus.RUNNING);
        assertThat(GameStatus.RUNNING, is(equalTo(model.getStatus())));

        model.setGameStatus(GameStatus.PAUSED);
        assertThat(GameStatus.PAUSED, is(equalTo(model.getStatus())));
    }

    @Test
    void mustThrowIllegalArgumentExceptionWhenClientSelectInvalidCard() {
        Card invalidCard = new Card();
        assertFalse(model.getChosenCards().contains(invalidCard));

        assertThrows(IllegalArgumentException.class, () -> {
            model.selectCard(invalidCard);
        });
    }

    @Test
    void mustSelectCard() {
        List<ICard> cardsForSetUp = new ArrayList<>();
        FrontendModel model = mock(FrontendModel.class);

        int anyNumberOfCards = random.nextInt(100);
        for (int i = 0; i < anyNumberOfCards; i++) {
            cardsForSetUp.add(new Card());
        }

        model.setUpCard(cardsForSetUp);

        model.selectCard(cardsForSetUp.get(random.nextInt(cardsForSetUp.size())));
    }
}
