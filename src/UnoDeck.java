import java.awt.*;

public class UnoDeck extends UnoPile {

    private UnoCard.CardColors[] colors;

    public UnoDeck() {
        super();
        this.colors = new UnoCard.CardColors[]{UnoCard.CardColors.RED, UnoCard.CardColors.YELLOW, UnoCard.CardColors.GREEN, UnoCard.CardColors.BLUE};
        for (UnoCard.CardColors color : colors) {
            createSuite(color);
        }
        for (int i = 0; i < 2; i++) {
            //Add 2 Wild Cards
            this.add(new UnoCard(UnoCard.CardColors.BLACK, 13));
            //Add 2 Draw 4 Cards
            this.add(new UnoCard(UnoCard.CardColors.BLACK, 14));
        }
    }

    /**
     * @param color The color of the suite of cards
     *              Create a suite (2 cards of each rank) of cards for a specific color
     */
    private void createSuite(UnoCard.CardColors color) {
        for (int i = 0; i < 13; i++) {
            createCard(color, i);
        }
    }

    /**
     * @param color The color of each card
     * @param rank  The rank of each card (0-9, 10: Reverse, 11: Skip, 12: Draw 2)
     *              Create 2 cards for the specific rank and color
     */
    private void createCard(UnoCard.CardColors color, int rank) {
        for (int x = 0; x < 2; x++) {
            this.add(new UnoCard(color, rank));
        }
    }

    // not sure what we want to do when we draw the deck.
    // maybe draw a facedown card.  Do we need to pass in the location?
    public void draw(Graphics g) {

    }
}
