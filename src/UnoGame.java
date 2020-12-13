import org.jetbrains.annotations.NotNull;

import java.awt.Graphics;
import java.util.ArrayList;

public class UnoGame {
    private int currPlayer = 0; //Index of the player whose turn it is
    private boolean isClockwise = true;
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

    public UnoPlayer getPlayer(int index) {
        if(this.players.size() == 0) return null;
        while(index < 0) index = this.players.size() + index;
        return this.players.get(index % this.players.size());
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
        if (this.deck.size() > 0) {
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
        if (this.drawPile.size() == 0) this.fillDrawPile();
        return this.drawPile.deal();
    }

    private void incrementTurn() {
        this.currPlayer+=(isClockwise ? 1 : -1);
    }

    public void playGame() {
        while (!this.gameOver()) {
            UnoPlayer currentPlayer = this.getPlayer(this.currPlayer);
            UnoPlayer nextPlayer = this.getPlayer(this.currPlayer + (isClockwise ? 1 : -1));
            // Gets the last card of discard pile (top card)
            UnoCard lastCard = this.discardPile.get(this.discardPile.size() - 1);
            //Add a card to player's hand while no card matches
            while (currentPlayer.getNextCard(lastCard) == null) {
                currentPlayer.addCard(this.dealDrawPile());
            }
            UnoCard nextCard = currentPlayer.getNextCard(lastCard);
            int rank = nextCard.getRank();
            //Reverse Card -> Switch directions
            if (rank == 10) {
                this.isClockwise = !this.isClockwise;
            }
            //Skip Card -> increment turn
            if (rank == 11) {
                this.incrementTurn();
            }
            //Draw 2 Card -> draw 2 for next player and increment turn
            if (rank == 12) {
                for(int i = 0; i < 2; i++) nextPlayer.addCard(this.dealDrawPile());
                this.incrementTurn();
            }
            //Draw 2 Card -> draw 4 for next player and increment turn
            if (rank == 13) {
                for(int i = 0; i < 2; i++) nextPlayer.addCard(this.dealDrawPile());
                this.incrementTurn();
            }
            this.discardPile.add(nextCard);
            this.incrementTurn();
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
