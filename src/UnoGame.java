import org.jetbrains.annotations.NotNull;

import java.awt.Graphics;
import java.util.ArrayList;

public class UnoGame {
    private int currPlayer; //Index of the player whose turn it is
    private int direction = 1;
    private ArrayList<UnoPlayer> players = new ArrayList();
    private UnoPile drawPile = new UnoPile();
    private UnoPile discardPile = new UnoPile();
    private UnoDeck deck = new UnoDeck();

    public UnoGame() {
    }

    public UnoGame(UnoPlayer[] players) {
        for (UnoPlayer player : players) {
            this.addPlayer(player);
        }
    }

    public void addPlayer(@NotNull UnoPlayer player) {
        this.players.add(player);
    }

    public void removePlayer(int index) {
        this.players.remove(index);
    }

    public void setUpGame(int numCards) {
        this.dealCards(numCards);
        this.fillDrawPile();
        this.discardPile.add(this.drawPile.deal());
    }

    private void dealCards(int numCards) {
        this.deck.shuffle();
        for (int i = 0; i < numCards; i++) {
            for (UnoPlayer player : this.players) {
                player.addCard(this.deck.deal());
            }
        }
    }

    private void fillDrawPile() {
        //Remove all cards from deck and add to the draw pile (only happens at start)
        if(this.deck.size() > 0) {
            while (this.deck.size() > 0) {
                this.drawPile.add(this.deck.deal());
            }
        }
        //Remove all cards except top card from discard pile and add to the draw pile
        else if (this.discardPile.size() > 1) {
            while (this.discardPile.size() > 1) {
                this.drawPile.add(this.discardPile.deal());
            }
        }
    }

    private UnoCard dealDrawPile() {
        if(this.drawPile.size() == 0) this.fillDrawPile();
        return this.drawPile.deal();
    }

    public void playGame() {
        while (!this.gameOver()) {
            UnoPlayer currentPlayer = this.players.get(this.currPlayer);
            // Gets the last card of discard pile (top card)
            UnoCard lastCard = this.discardPile.get(this.discardPile.size() - 1);
            //Add a card while no card matches
            while (currentPlayer.getNextCard(lastCard) == null) {
                currentPlayer.addCard(this.dealDrawPile());
            }
        }
    }

    public boolean gameOver() {
        for (UnoPlayer player : this.players) {
            if (player.cardsLeft() == 0) return true;
        }
        return false;
    }

    public void draw(Graphics g) {

    }
}
