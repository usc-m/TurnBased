package inf1.oop.turnbased.entity;

public class Entity {
	int x, y, ownhealth, damage; //HIGH PRIORITY
	//int money, stamina, attackspeed, level, experience, weapondamage, armor;//LOWER PRIORITY
	String name;
	
	//constructor; similar to "instance_create(x,y,name);"
	public Entity(int x, int y, String name)
	{
		//Initializes variables
		this.x = x;
		this.y = y;
		this.name = name;
		this.damage = 2;
	}
	
	//method that returns object's x-position
	public int getX()
	{
		return x;
	}
	
	//method that returns object's y-position
	public int getY()
	{
		return y;
	}
	
	//method that sets object's x-position
	public void setX(int x)
	{
		this.x = x;
	}
	
	//method that sets object's y-position
	public void setY(int y)
	{
		this.y = y;
	}
}
