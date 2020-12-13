import org.jetbrains.annotations.NotNull;

import java.awt.Graphics;
import java.util.ArrayList;

public class UnoGame {
	private int currPlayer; //Index of the player whose turn it is
	private int direction = 1;
	private ArrayList<UnoPlayer> players = new ArrayList();
	private UnoPile drawPile = new UnoPile();
	private UnoPile discardPile = new UnoPile();
	private UnoDeck deck =  new UnoDeck();

	public UnoGame() {}

	public UnoGame(UnoPlayer[] players) {
		for(UnoPlayer player: players) {
			this.addPlayer(player);
		}
	}

	public void addPlayer(@NotNull UnoPlayer player) {
		this.players.add(player);
	}

	public void dealCards(int numCards) {
		for(int i = 0; i < numCards; i++) {
			for(UnoPlayer player: this.players) {
				player.addCard(deck.deal());
			}
		}
	}

	public void playGame() {
		this.playGame(7);
	}

	public void playGame(int numCards) {
		this.deck.shuffle();
		this.dealCards(numCards);
		this.fillDrawPile();
		this.discardPile.add(drawPile.deal());
	}

	private void fillDrawPile() {
		for(int i = 0; i < this.deck.size(); i++) {
			this.drawPile.add(this.deck.deal());
		}
	}

	public void draw(Graphics g) {

	}
}
