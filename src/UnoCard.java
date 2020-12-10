import java.awt.*;

public class UnoCard {
    private final Color color;
    /**
     * 0-9, 10: Reverse, 11: Skip, 12: Draw 2.
     * For Black cards, 13: Wild and 14: Draw 4
     */
    private final int rank;

    public UnoCard(Color color, int rank) {
        this.color = color;
        this.rank = rank;
    }

    /**
     * This method is called when this UnoCard is being asked
     * to draw itself.  Where the card draws itself still needs to be
     * decided.  Should the card keep track of its location or should
     * it be told where to draw itself???  If it needs to be told where
     * to draw itself, then you need to add parameters to this method.
     *
     * @param g
     */
    public void draw(Graphics g) {

    }

    public Color getColor() {
        return this.color;
    }

    public int getRank() {
        return this.rank;
    }

    public String toString() {
        return "<UnoCard> (Color: " +
                (
                        this.color.equals(Color.RED)
                                ? "Red"
                                : this.color.equals(Color.YELLOW)
                                ? "Yellow"
                                : this.color.equals(Color.BLUE)
                                ? "Blue"
                                : this.color.equals(Color.GREEN)
                                ? "Green"
                                : this.color.equals(Color.BLACK)
                                ? "Black"
                                : "Unknown"
                ) + ", Rank: " + this.rank + ")";
    }


}
