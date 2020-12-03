import java.awt.Graphics;

public class UnoDeck extends UnoPile {

	private String[] colors;

	public UnoDeck() {
		super();
		this.colors = new String[]{"red", "yellow", "green", "blue"};
		for(String color: colors) {
			createSuite(color);
		}
	}

	private void createSuite(String color) {
		for(int i = 0; i < 10; i++) {
			createCard(color, i);
		}
	}

	private void createCard(String color, int rank) {
		for(int x = 0; x < 2; x++) {
			this.add(new UnoCard(color, rank));
		}
	}
	
	// not sure what we want to do when we draw the deck.  
	// maybe draw a facedown card.  Do we need to pass in the location?
	public void draw(Graphics g) {
		
	}
	

	
}
