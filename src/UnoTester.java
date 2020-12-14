import javax.swing.*;
import java.awt.*;

public class UnoTester {
    private enum TestingMode {CARDS, PILE, DECK, PLAYER, GAME, ALL}

    private final TestingMode tm = TestingMode.ALL;
    private int numRepaints = 0;

    public static void main(String[] args) {
        UnoTester ut = new UnoTester();
        ut.init();
        ut.testStuff();
    }

    /**
     * This code in the init method
     */
    private void init() {
        JPanel panel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);// basically clears the canvas
                UnoTester.this.numRepaints++;
                //System.out.println("painting!! " + numRepaints);
                UnoTester.this.drawStuff(g);
            }
        };
        panel.setBackground(new Color(34, 222, 34));
        panel.setPreferredSize(new Dimension(800, 600));
        JFrame frame = new JFrame("Testing Uno Classes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    protected void testStuff() {
        switch (this.tm) {
            case CARDS:
                this.testUnoCard();
                break;
            case PILE:
                this.testUnoPile();
                break;
            case DECK:
                this.testUnoDeck(true);
                break;
            case PLAYER:
                this.testUnoPlayer();
                break;
            case GAME:
                this.testUnoGame();
                break;
            case ALL:
                this.testUnoCard();
                this.testUnoPile();
                this.testUnoDeck(false);
                this.testUnoPlayer();
                this.testUnoGame();
                break;
        }
    }

    protected void drawStuff(Graphics g) {

    }

    private void testUnoGame() {
        UnoGame ug = new UnoGame();
    }

    private void testUnoPlayer() {
        try {
            System.out.println("\n-----\nTesting UnoPlayer\n");
            String name = "Player 1";
            UnoPlayer player = new UnoPlayer(name);

            //constructor + getName
            if (!player.getName().equals(name)) throw new Exception("getName does not work");
            System.out.println("getName & constructor work");

            //addCard + cardsLeft
            UnoCard g4 = new UnoCard(UnoCard.CardColors.GREEN, 4),
                    b6 = new UnoCard(UnoCard.CardColors.BLUE, 6),
                    b7 = new UnoCard(UnoCard.CardColors.BLUE, 7),
                    wild = new UnoCard(UnoCard.CardColors.BLACK, 13),
                    draw4 = new UnoCard(UnoCard.CardColors.BLACK, 14);
            player.addCard(g4);
            player.addCard(b6);
            player.addCard(b7);
            player.addCard(draw4);
            player.addCard(wild);
            if (player.cardsLeft() != 5) {
                System.out.println("cardsLeft = " + player.cardsLeft());
                throw new Exception("addCard or cardsLeft does not work");
            }
            System.out.println("addCard & cardsLeft work");

            //mostColors
            if (!player.mostColors().equals(UnoCard.CardColors.BLUE)) {
                System.out.println("mostColors = " + player.mostColors());
                throw new Exception("mostColors does not work");
            }
            System.out.println("mostColors works");

            //getNextCard
            UnoCard g0 = new UnoCard(UnoCard.CardColors.GREEN, 0),
                    r6 = new UnoCard(UnoCard.CardColors.RED, 6);
            UnoCard first = player.getNextCard(g0),
                    second = player.getNextCard(r6),
                    third = player.getNextCard(g0),
                    fourth = player.getNextCard(g0),
                    fifth = player.getNextCard(r6);

            if (!first.equals(g4)) throw new Exception("getNextCard w/ color does not work");
            if (!second.equals(b6)) throw new Exception("getNextCard w/ rank does not work");
            if (!third.equals(wild)) throw new Exception("getNextCard w/ wild card does not work");
            if (!fourth.equals(draw4)) throw new Exception("getNextCard w/ draw4 card does not work");
            if (fifth != null) throw new Exception("getNextCard w/ no match does not work");
            System.out.println("getNextCard works");

            System.out.println("\nUnoPlayer: " + player + "\n-----");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void testUnoPile() {
        try {
            System.out.println("\n-----\nTesting UnoPile\n");
            UnoPile pile = new UnoPile();
            int expectedSize = 0;

            //size & constructor
            if (pile.size() != expectedSize) throw new Exception("size does not work");
            System.out.println("size & constructor work");

            //addInOrder & get
            UnoCard first = new UnoCard(UnoCard.CardColors.GREEN, 2),
                    second = new UnoCard(UnoCard.CardColors.GREEN, 4),
                    third = new UnoCard(UnoCard.CardColors.RED, 1);
            pile.addInOrder(second);
            expectedSize++;
            pile.addInOrder(first);
            expectedSize++;
            pile.addInOrder(third);
            expectedSize++;

            if (!pile.get(0).equals(first)) throw new Exception("addInOrder or get does not work");
            if (!pile.get(1).equals(second)) throw new Exception("addInOrder or get does not work");
            if (!pile.get(2).equals(third)) throw new Exception("addInOrder or get does not work");
            System.out.println("addInOrder and get work");

            //deal
            UnoCard dealt = pile.deal();
            expectedSize--;
            if (pile.size() != expectedSize) throw new Exception("deal does not work");
            if (!dealt.equals(first)) throw new Exception("deal does not work");
            System.out.println("deal works");

            //shuffle
            pile.shuffle();
            if (pile.size() != expectedSize) throw new Exception("shuffle does not work");
            System.out.println("shuffle works");

            System.out.println("\nUnoPile: " + pile + "\n-----");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void testUnoDeck(boolean printDeck) {
        try {
            System.out.println("\n-----\nTesting UnoDeck\n");

            UnoDeck deck = new UnoDeck();

            //constructor
            if (deck.size() != 108) throw new Exception("UnoDeck constructor does not work");
            System.out.println("constructor works");

            if (printDeck) System.out.println("\nUnoDeck:\n" + deck + "\n");
            System.out.println("-----");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void testUnoCard() {
        try {
            System.out.println("\n-----\nTesting UnoCard\n");

            UnoCard g1 = new UnoCard(UnoCard.CardColors.GREEN, 1);

            //getColor
            UnoCard.CardColors color = g1.getColor();

            if (!color.equals(UnoCard.CardColors.GREEN)) throw new Exception("getColor does not work");
            System.out.println("getColor works");

            //getRank
            int rank = g1.getRank();

            if (rank != 1) throw new Exception("getRank does not work");
            System.out.println("getRank works");

            System.out.println("\nUnoCard: " + g1 + "\n-----");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
