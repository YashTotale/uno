import java.awt.Graphics;

public class UnoPlayer {
	private String name;
	private UnoPile hand;
	private int x, y;
	private boolean isTurn;


	public UnoPlayer(String name) {

	}

	public void draw(Graphics g) {

	}

	public void addCard(UnoCard deal) {
		this.hand.add(deal);
	}

	public UnoCard getNextCard(UnoCard needToMatch) {

		return null;
	}
	public int cardsLeft() {
		return this.hand.size();
	}
}
