package stacs.starcade.frontend;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stacs.starcade.frontend.model.ClientModel;
import stacs.starcade.frontend.view.sub.card.CardImageButton;
import stacs.starcade.shared.Card;
import stacs.starcade.shared.ICard;

public class CardImageButtonTests {
    private CardImageButton cardImageButton;

    @BeforeEach
    void setup() {
        cardImageButton = new CardImageButton(mock(ClientModel.class));
    }

    @Test
    void mustSetCard() {
        ICard card = mock(Card.class);
        cardImageButton.setCard(card);
        assertThat(cardImageButton.getCard(), is(equalTo(card)));
    }

    @Test
    void mustGetCard() {
        ICard card = mock(Card.class);
        cardImageButton.setCard(card);
        assertThat(cardImageButton.getIcon(), is(equalTo(cardImageButton.getImageIcon())));
    }

    @Test
    void mustSetNullCard() {
        cardImageButton.setCard(null);
        assertNull(cardImageButton.getCard());
    }

}
