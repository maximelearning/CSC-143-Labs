import java.awt.Color;
import java.awt.Graphics;

/**
 * <p>
 * Class Box
 * </p>
 * <p>
 * A box is a rectangle that may contain another box
 * </p>
 *
 * @author CSC 143
 */

public class Box {

	// Dimensions and location
	private int height, width, centerX, centerY;
	// Color
	private Color color;

	// Box inside of this box
	private Box innerBox;

	/**
	 * Creates a box of a given width, height, location and color.
	 *
	 * @param centerX
	 *            the x coordinate of the center of the box.
	 * @param centerY
	 *            the y coordinate of the center of the box.
	 * @param width
	 *            the width of the box.
	 * @param height
	 *            the height of the box.
	 * @param color
	 *            the color of the box.
	 * @throws IllegalArgumentException
	 *             if width or height are not positive, or if color is null.
	 */
	public Box(int centerX, int centerY, int height, int width, Color color) {
		if (width <= 0 || height <= 0 || color == null) {
			throw new IllegalArgumentException("width = " + width
					+ ", height = " + height + ", color = " + color); // "width or height are not positive, or color is null
		}
		this.centerX = centerX;
		this.centerY = centerY;
		this.width = width;
		this.height = height;
		this.color = color;
	}

	/**
	 * Adds an inner box to this box. Returns true if the operation was
	 * successful and false otherwise. The inner box might not be added if its
	 * dimensions are less than or equal to 1. The inner box has the same center
	 * and is 10% smaller than its direct enclosing box. The inner box has the
	 * same color as its enclosing box.
	 *
	 * @return true if the inner box could be added and false otherwise.
	 */
	public boolean addInnerBox() {
		if (innerBox == null) { // base case: there is no inner box
			// add an inner box if not too small
			int innerWidth = width * 9 / 10; // NOT 9 / 10 * width which is always 0 // Another option: (int) (0.9 * height)
			int innerHeight = height * 9 / 10;
			if (innerWidth > 1 && innerHeight > 1) {
				innerBox = new Box(centerX, centerY, innerHeight, innerWidth, color);
				return true;
			} else {
				return false;
			}
		} else {
			return innerBox.addInnerBox();
		}
	}

	/**
	 * Removes inner box from this box. Returns true if the operation was
	 * successful and false otherwise.
	 *
	 * @return true if the inner box could be removed and false otherwise.
	 */
	public boolean removeInnerBox() {
		if (innerBox == null) {
			return false;
		} else if (innerBox.innerBox == null) {
			innerBox = null;
			return true;
		} else {
			return innerBox.removeInnerBox();
		}
	}

	/**
	 * Gets the number of inner boxes inside this box
	 */
	public int getNumberOfInnerBoxes() {
		// base case -> no innerBox
		if (innerBox == null) {
			return 0;
		} else {
			return 1 + innerBox.getNumberOfInnerBoxes();
		}
	}

	/**
	 * Draws this box (and its inner boxes if any)
	 *
	 * @param g
	 *            the graphics context to use
	 */
	public void draw(Graphics g) {
		// draw this box
		g.setColor(color);
		g.drawRect(centerX - width / 2, centerY - height / 2, width, height);
		// is there an inner box? if so, draw it
		if (innerBox != null) {
			innerBox.draw(g);
		}
	}

	/**
	 * Returns a string representation of this box, namely the location of its
	 * center, its width and height, its color and its number of inner boxes.
	 *
	 * @return a string representation of this box.
	 */
	public String toString() {
		// %d -> to display an integer (d stands for decimal)
		// %s -> to display a string
		return String.format("center = (%d,%d), width = %d, height = %d, color = %s, inner boxes = %d",
				             centerX, centerY, width, height, color, getNumberOfInnerBoxes());
	}
}









