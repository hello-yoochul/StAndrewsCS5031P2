package stacs.starcade.frontend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stacs.starcade.frontend.model.FrontendModel;
import stacs.starcade.frontend.view.sub.card.CardImageButton;
import stacs.starcade.shared.Card;
import stacs.starcade.shared.ICard;
import stacs.starcade.shared.ICard.LineStyle;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static stacs.starcade.shared.ICard.*;
import static stacs.starcade.shared.ICard.Number;

public class CardImageButtonTests {
    private CardImageButton cardImageButton;
    private FrontendModel model;

    @BeforeEach
    void setup() {
        cardImageButton = new CardImageButton(model);
    }

    @Test
    void mustStoreImagePath() {
        ICard card = new Card();
        card.setLineStyle(LineStyle.DOTTED);
        card.setColour(Colour.RED);
        card.setShape(Shape.TRIANGLE);
        card.setNumber(Number.ONE);
        cardImageButton.setCard(card);

        assertThat(cardImageButton.getImagePathStr(), is(equalTo("/RED-ONE-TRIANGLE-DOTTED.png")));
    }

    @Test
    void mustGetCard() {
        ICard card = new Card();
        card.setLineStyle(LineStyle.DOTTED);
        card.setColour(Colour.RED);
        card.setShape(Shape.TRIANGLE);
        card.setNumber(Number.ONE);
        cardImageButton.setCard(card);

        assertThat(cardImageButton.getCard(), is(equalTo(card)));
    }


}
