import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class UnoTester {
	// This is like a new data type that can be one of 4 choices
	private enum TestingMode{CARDS, PILE, DECK, PLAYER, GAME};
	
	// setting the Testing mode to Cards.  Change this when 
	// you are ready to test the Deck class or Player class or Game
	private TestingMode tm= TestingMode.CARDS;
	private UnoDeck ud = new UnoDeck();
	private ArrayList<UnoCard> listOfCards = new ArrayList<>();
	private int numRepaints = 0;
	
	public static void main(String[] args) {
		System.out.println("Testing classes");
		UnoTester ut = new UnoTester();
		ut.init();
		ut.testUnoCards();
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
				System.out.println("painting!! "+numRepaints);
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
		switch(tm) {
		case CARDS:
			testUnoCards();
			for(UnoCard uc: this.listOfCards)
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
		UnoPlayer up1 = new UnoPlayer(),
				  up2 = new UnoPlayer();
		UnoDeck ud = new UnoDeck();
		for(int x = 0; x<7; x++) {
			up1.addCard(ud.deal());
			up2.addCard(ud.deal());
		}
		System.out.println(up1);
		System.out.println(up2);
		UnoCard uc1 = up1.getNextCard(),
				uc2 = up2.getNextCard();
		System.out.println(up1);
		System.out.println(uc1);
		
	}
	private void testUnoPile() {
		UnoPile up = new UnoPile();
		for(int x = 0; x < 5; x++) {
			up.add(this.listOfCards.get(x));
			System.out.println(up);
		}
		up.shuffle();
		for(int x = 0; x < 2; x++) {
			
		}
	}
	private void testUnoDeck() {
		UnoDeck ud = new UnoDeck();
		ud.shuffle();
		for(int i = 0; i<5; i++) {
			System.out.println(ud.deal());
		}
		System.out.println(ud);
		while(ud.size() > 0) {
			if(ud.size()%3==0) {
				listOfCards.add(ud.deal());
			}
			else {
				ud.deal();
			}
		}
		System.out.println(listOfCards);
		
	}
	private void testUnoCards() {
		UnoCard g1=null, b10=null, y5=null, r2=null, gReverse=null, 
				ySkip=null, rDraw2=null, wild=null, wildDraw4=null;
		// TODO:  Initialize the cards.  Those cards will be added to 
		// the array below and then subsequently added to the 
		// ArrayList instance variable.
		
		
		UnoCard[] ucArr = new UnoCard[] {g1,b10,y5, r2, gReverse,
				                   ySkip, rDraw2, wild, wildDraw4};
		// this calls the list's addAll method, but I needed to 
		// treat the array like a List.  Arrays.asList allows this
		this.listOfCards.addAll(Arrays.asList(ucArr));
		
		System.out.println(listOfCards);
		System.out.println(listOfCards.remove(0).getColor());
		System.out.println(listOfCards.remove(0).getRank());
	}

}
