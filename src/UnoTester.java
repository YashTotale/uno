import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class UnoTester {
    private enum TestingMode {CARDS, PILE, DECK, PLAYER, GAME}

    private TestingMode tm = TestingMode.DECK;
    private int numRepaints = 0;

    public static void main(String[] args) {
        System.out.println("Testing classes");
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
                numRepaints++;
                System.out.println("painting!! " + numRepaints);
                drawStuff(g);
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
        switch (tm) {
            case CARDS:
                testUnoCard();
                break;
            case PILE:
                testUnoPile();
                break;
            case DECK:
                testUnoDeck();
                break;
            case PLAYER:
                testUnoPlayer();
                break;
            case GAME:
                testUnoGame();
                break;
        }
    }

    protected void drawStuff(Graphics g) {

    }

    private void testUnoGame() {
        UnoGame ug = new UnoGame();

    }

    private void testUnoPlayer() {
        UnoPlayer up1 = new UnoPlayer("Player 1"),
                up2 = new UnoPlayer("Player 2");
        UnoDeck ud = new UnoDeck();
        for (int x = 0; x < 7; x++) {
            up1.addCard(ud.deal());
            up2.addCard(ud.deal());
        }
        System.out.println(up1);
        System.out.println(up2);
        UnoCard top = ud.deal();
        UnoCard uc1 = up1.getNextCard(top),
                uc2 = up2.getNextCard(top);
        System.out.println(up1);
        System.out.println(uc1);

    }

    private void testUnoPile() {
        try {
            System.out.println("\n-----\nTesting UnoPile\n");
            UnoPile pile = new UnoPile();

            //size & constructor
            if (pile.size() != 0) {
                throw new Exception("size does not work");
            }
            System.out.println("size & constructor work");

            //add & get
            UnoCard second = new UnoCard(Color.GREEN, 4);
            pile.add(second);
            UnoCard first = new UnoCard(Color.GREEN, 2);
            pile.add(first);
            UnoCard third = new UnoCard(Color.RED, 1);
            pile.add(third);

            if (!pile.get(0).equals(first)) {
                throw new Exception("add or get does not work");
            }
            if (!pile.get(1).equals(second)) {
                throw new Exception("add or get does not work");
            }
            if (!pile.get(2).equals(third)) {
                throw new Exception("add or get does not work");
            }
            System.out.println("add and get work");

            //deal
            UnoCard dealt = pile.deal();
            if(pile.size() != 2) {
                throw new Exception("deal does not work");
            }
            if(!dealt.equals(first)) {
                throw new Exception("deal does not work");
            }
            System.out.println("deal works");

            //shuffle
            pile.shuffle();
            if(pile.size() != 2) {
                throw new Exception("shuffle does not work");
            }
            System.out.println("shuffle works");

            System.out.println("\nUnoPile:\n" + pile + "\n\n-----");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void testUnoDeck() {
        try {
            System.out.println("\n-----\nTesting UnoCard\n");

            UnoDeck deck = new UnoDeck();

            //constructor
            if(deck.size() != 108) {
                throw new Exception("UnoDeck constructor does not work");
            }
            System.out.println("constructor works");

            System.out.println("\nUnoDeck:\n" + deck + "\n\n-----");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void testUnoCard() {
        try {
            System.out.println("\n-----\nTesting UnoCard\n");

            ArrayList<UnoCard> listOfCards = new ArrayList();

            UnoCard g1 = new UnoCard(Color.GREEN, 1), b9 = new UnoCard(Color.BLUE, 9), y5 = new UnoCard(Color.YELLOW, 5), r2 = new UnoCard(Color.RED, 2), gReverse = new UnoCard(Color.GREEN, 10),
                    ySkip = new UnoCard(Color.YELLOW, 11), rDraw2 = new UnoCard(Color.RED, 12), wild = new UnoCard(Color.BLACK, 13), wildDraw4 = new UnoCard(Color.BLACK, 14);


            UnoCard[] ucArr = new UnoCard[]{g1, b9, y5, r2, gReverse,
                    ySkip, rDraw2, wild, wildDraw4};

            listOfCards.addAll(Arrays.asList(ucArr));

            //getColor
            Color color = listOfCards.get(0).getColor();

            if (!color.equals(Color.GREEN)) {
                throw new Exception("getColor does not work");
            }
            System.out.println("getColor works");

            //getRank
            int rank = listOfCards.get(0).getRank();

            if (rank != 1) {
                throw new Exception("getRank does not work");
            }
            System.out.println("getRank works");


            System.out.println("\nUnoCard list:\n" + listOfCards + "\n\n-----");

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
