package inf1.oop.display;

public abstract class Display {
	public abstract void draw(char identifier, int x, int y); // possibly swap this for some sort of Drawable object
	public abstract void pushToScreen();
}
