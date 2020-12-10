import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class UnoTester {
    // This is like a new data type that can be one of 4 choices
    private enum TestingMode {CARDS, PILE, DECK, PLAYER, GAME}

    ;

    // setting the Testing mode to Cards.  Change this when
    // you are ready to test the Deck class or Player class or Game
    private TestingMode tm = TestingMode.PILE;
    private UnoDeck ud = new UnoDeck();
    private ArrayList<UnoCard> listOfCards = new ArrayList<>();
    private int numRepaints = 0;

    public static void main(String[] args) {
        System.out.println("Testing classes");
        UnoTester ut = new UnoTester();
        ut.testUnoCards();
        ut.init();
    }

    /**
     * This code in the init method
     */
    private void init() {
        JPanel panel = new JPanel() {
            /** this is a super-important method when working with
             * javax swing graphics.  All drawing is invoked from here.
             */
            public void paintComponent(Graphics g) {
                super.paintComponent(g);// basically clears the canvas
                numRepaints++;
                System.out.println("painting!! " + numRepaints);
                drawStuff(g);
            }
        };
        panel.setBackground(new Color(100, 220, 100));
        panel.setPreferredSize(new Dimension(800, 600));
        JFrame frame = new JFrame("Testing Uno Classes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    protected void drawStuff(Graphics g) {
        switch (tm) {
            case CARDS:
                testUnoCards();
                for (UnoCard uc : this.listOfCards)
                    uc.draw(g);
                break;
            case PILE:
                testUnoPile();
                // other code
                break;
            case DECK:
                testUnoDeck();
                // other code
                break;
            case PLAYER:
                testUnoPlayer();
                // other code
                break;
            case GAME:
                testUnoGame();
                // other code
                break;
        }


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

            //size
            if (pile.size() != 0) {
                throw new Exception("size does not work");
            }
            System.out.println("size works");

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

            //shuffle
            pile.shuffle();
            if(pile.size() != 3) {
                throw new Exception("shuffle does not work");
            }
            System.out.println("shuffle works");

            System.out.println("\nUnoPile:\n" + pile + "\n\n-----");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void testUnoDeck() {
        UnoDeck ud = new UnoDeck();


        for (int i = 0; i < 5; i++) {
            System.out.println(ud.deal());
        }
        System.out.println(ud);
        while (ud.size() > 0) {
            if (ud.size() % 3 == 0) {
                listOfCards.add(ud.deal());
            } else {
                ud.deal();
            }
        }

    }

    private void testUnoCards() {
        try {
            System.out.println("\n-----\nTesting UnoCard\n");
            UnoCard g1 = new UnoCard(Color.GREEN, 1), b9 = new UnoCard(Color.BLUE, 9), y5 = new UnoCard(Color.YELLOW, 5), r2 = new UnoCard(Color.RED, 2), gReverse = new UnoCard(Color.GREEN, 10),
                    ySkip = new UnoCard(Color.YELLOW, 11), rDraw2 = new UnoCard(Color.RED, 12), wild = new UnoCard(Color.BLACK, 13), wildDraw4 = new UnoCard(Color.BLACK, 14);


            UnoCard[] ucArr = new UnoCard[]{g1, b9, y5, r2, gReverse,
                    ySkip, rDraw2, wild, wildDraw4};

            this.listOfCards.addAll(Arrays.asList(ucArr));


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


            System.out.println("\nUnoCard list:\n" + this.listOfCards + "\n\n-----");

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
