import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;


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
		if(this.pile.size() > 0) {
			return this.pile.remove(0);
		}
		return null;
	}

	public void add(@NotNull UnoCard unoCard) {
		Color color = unoCard.getColor();
		int rank = unoCard.getRank();

		for (int i = 0; i < this.pile.size(); i++) {
			UnoCard current = this.pile.get(i);

			if(color.equals(current.getColor())) {
				if(rank < current.getRank()) {
					this.pile.add(i, unoCard);
					return;
				} if(i != this.pile.size() -1 && !this.pile.get(i + 1).getColor().equals(color)) {
					this.pile.add(i + 1, unoCard);
					return;
				}
			}
		}

		this.pile.add(unoCard);
	}

	public void shuffle() {
		for(int i = 0; i < this.pile.size(); i++) {
			UnoCard temp = this.pile.get(i);
			this.pile.remove(i);
			int random = (int) Math.floor((this.pile.size() - 1) * Math.random());
			this.pile.add(random, temp);
		}
	}

	public UnoCard get(int index) {
		return this.pile.get(index);
	}

	public String toString() {
		return this.pile.toString();
	}
}
