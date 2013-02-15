package inf1.oop.turnbased.display;

import inf1.oop.turnbased.util.Point2D;

public abstract class VisualDisplay {
	public abstract void draw(char identifier, Point2D position); // possibly swap this for some sort of Drawable object
	public abstract void push();
	public abstract int getHeight();
	public abstract int getWidth();
}
