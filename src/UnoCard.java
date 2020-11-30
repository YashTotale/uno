import java.awt.Graphics;

public class UnoCard {
	private String color;
	private int rank;

	public UnoCard(String color, int rank) {
		this.color = color;
		this.rank = rank;
	}

	/**  This method is called when this UnoCard is being asked
	 * to draw itself.  Where the card draws itself still needs to be 
	 * decided.  Should the card keep track of its location or should 
	 * it be told where to draw itself???  If it needs to be told where
	 * to draw itself, then you need to add parameters to this method.
	 * @param g
	 */
	public void draw(Graphics g) {
		
	}

	public String getColor() {
		return this.color;
	}

	public int getRank() {
		return this.rank;
	}
	

}
