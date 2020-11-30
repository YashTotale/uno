import org.jetbrains.annotations.NotNull;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

public class UnoPile {
	private ArrayList<UnoCard> pile;

	public UnoPile() {
		this.pile = new ArrayList();
	}
	
	public void draw(Graphics g) {

	}

	public int size() {
		return this.pile.size();
	}

	public UnoCard deal() {
		return this.pile.remove(this.pile.size() - 1);
	}

	public void add(@NotNull UnoCard unoCard) {
		String color = unoCard.getColor();
		int rank = unoCard.getRank();

		for (int i = 0; i < this.pile.size(); i++) {
			UnoCard current = this.pile.get(i);

			if(color.equals(current.getColor())) {
				if(rank < current.getRank()) {
					this.pile.add(i, unoCard);
					return;
				} if(i != this.pile.size() -1 && !this.pile.get(i + 1).equals(color)) {
					this.pile.add(i + 1, unoCard);
					return;
				}
			}
		}

		this.pile.add(unoCard);
	}

	public void shuffle() {
		Collections.shuffle(pile);
	}
}
