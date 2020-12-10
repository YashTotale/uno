import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class UnoPlayer {
	private String name;
	private UnoPile hand;
	private int x, y;
	private boolean isTurn;


	public UnoPlayer(String name) {

	}

	public void draw(Graphics g) {

	}

	public void addCard(UnoCard card) {
		this.hand.add(card);
	}

	/**
	 * @param card The UnoCard to match
	 * @return The next UnoCard to play
	 */
	public UnoCard getNextCard(UnoCard card) {
		Color color = card.getColor();
		int rank = card.getRank();

		int draw4Index = -1;
		int wildIndex = -1;

		for(int i = 0; i < hand.size(); i++) {
			UnoCard current = hand.get(i);
			Color currentColor = current.getColor();
			int currentRank = current.getRank();

			//Wild Card
			if(currentColor.equals(Color.BLACK)) {
				if(currentRank == 13) {
					wildIndex = i;
				}
				if(currentRank == 14) {
					draw4Index = i;
				}
				continue;
			}

			//Previous card is Wild Card -> Play any card
			if(color.equals(Color.BLACK)) {
				return hand.deal(i);
			}

			//Play matching color
			if(currentColor.equals(color)) {
				return hand.deal(i);
			}

			//Play matching rank
			if(currentRank == rank) {
				return hand.deal(i);
			}
		}

		//Play Wild or Draw4 as last resort
		return wildIndex > -1
				? hand.deal(wildIndex)
				: draw4Index > -1
				? hand.deal(draw4Index)
				: null;
	}

	public int cardsLeft() {
		return this.hand.size();
	}
}
