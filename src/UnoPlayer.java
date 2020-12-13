import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.HashMap;

public class UnoPlayer {
	private String name;
	private UnoPile hand = new UnoPile();
	private int x, y;

	public UnoPlayer(String name) {
		this.name = name;
	}

	public void draw(Graphics g) {

	}

	public void addCard(UnoCard card) {
		this.hand.addInOrder(card);
	}

	/**
	 * @param card The UnoCard to match
	 * @return The next UnoCard to play
	 */
	public UnoCard getNextCard(@NotNull UnoCard card) {
		UnoCard.CardColors color = card.getColor();
		int rank = card.getRank();

		int draw4Index = -1;
		int wildIndex = -1;

		for(int i = 0; i < hand.size(); i++) {
			UnoCard current = hand.get(i);
			UnoCard.CardColors currentColor = current.getColor();
			int currentRank = current.getRank();

			//Wild Card
			if(currentColor.equals(UnoCard.CardColors.BLACK)) {
				if(currentRank == 13) {
					wildIndex = i;
				}
				if(currentRank == 14) {
					draw4Index = i;
				}
				continue;
			}

			//Previous card is Wild Card -> Play any card
			if(color.equals(UnoCard.CardColors.BLACK)) {
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

	public UnoCard.CardColors mostColors() {
		return this.mostColors(UnoCard.CardColors.BLUE);
	}

	public UnoCard.CardColors mostColors(UnoCard.CardColors defaultColor) {
		//Stores the colors and their amounts
		HashMap<UnoCard.CardColors, Integer> colorAmounts = new HashMap();
		//Put all colors except black into hashmap
		for(UnoCard.CardColors color : UnoCard.CardColors.values()) {
			if(!color.equals(UnoCard.CardColors.BLACK)) colorAmounts.put(color, 0);
		}
		//Put the corresponding amounts for each color in the hashmap
		for(int i = 0; i < this.hand.size(); i++) {
			UnoCard card = this.hand.get(i);
			UnoCard.CardColors cardColor = card.getColor();
			if(cardColor.equals(UnoCard.CardColors.BLACK)) continue;
			colorAmounts.put(cardColor, colorAmounts.get(cardColor) + 1);
		}
		UnoCard.CardColors mostColor = defaultColor;
		int most = Integer.MIN_VALUE;
		//Find the greatest amount
		for(UnoCard.CardColors color: colorAmounts.keySet()) {
			int amount = colorAmounts.get(color);
			if(amount > most) {
				most = amount;
				mostColor = color;
			}
		}
		return mostColor;
	}

	public int cardsLeft() {
		return this.hand.size();
	}

	public String getName() {
		return this.name;
	}
}
