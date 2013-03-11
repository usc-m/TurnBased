/* =========================
 * CLASS DETAILS
 * ---------------
 * 
 * CONSTRUCTOR: 
 * Entity object_name = new Entity(int x, int y, String name, int damage, int movespeed, int totalhealth)
 * 
 * creates the entity object_name with given x-y positions, name, damage, movespeed and totalhealth.
 * eg. for a wall we could have: Entity(10,5,"wall",0,0,999);
 * 
 *  
 * METHODS:
 * getObject() returns the object in order to access an object from separate methods and classes.
 * getX() returns x
 * setX(x) sets x
 * getY() returns y
 * setY(y) sets y
 * getName() returns name
 * setName(name) sets name
 * getDamage() returns damage
 * attack() get attack, returns damage with a randomised equation and depending on weapon held.
 * setDamage(dmg) returns damage
 * getHealth() returns health
 * setHealth(health) sets health to new health, limits health to max-health if it exceeds the total.
 * heal(heal) increases current health by heal amount. Automagically limits heal to the total if required.
 * hit(hit) decreases current health by hit amount. Death script when health equals or falls below 0.
 * died() automatically triggered when ownhealth <= 0. What to do in this method? see suggestions in method.
 * =========================
 */

package inf1.oop.turnbased.entity;

public class Entity {
	private int x, y, totalhealth, ownhealth, damage, movespeed, weapon; //HIGH PRIORITY
	//int money, stamina, attackspeed, level, experience, weapondamage, armor;//LOWER PRIORITY
	private String name;
	private Entity temp = new Entity(x,y,name,damage,movespeed, totalhealth);
	
	//constructor; similar to "instance_create(x,y,name);"
	public Entity(int x, int y, String name, int damage, int movespeed, int totalhealth)
	{
		//Initializes variables
		this.x = x;
		this.y = y;
		this.name = name;
		this.damage = damage;
		this.movespeed = movespeed;
		this.totalhealth = totalhealth;
		this.ownhealth = totalhealth;
		this.weapon = 0;
	}
	
	//get entity object
	public Entity getObject()
	{
		return temp;
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
	
	//get object name
	public String getName()
	{
		return this.name;
	}
	
	//set object name
	public void setName(String name)
	{
		this.name = name;
	}
	
	//get damage
	public int getDamage()
	{
		return damage;
	}
	
	//set damage
	public void setDamage(int damage)
	{
		this.damage = damage;
	}
	
	//get attack, outputs damage with a randomized equation and depending on weapon held.
	public int attack()
	{
		return (int) Math.round(damage*(1+0.6*weapon) + (0.3*damage+0.5*(1+weapon))*Math.random());
	}
	
	//get entity health
	public int getHealth()
	{
		if (ownhealth > totalhealth) //make sure it cannot return a health above the maximum health
		{
			ownhealth = totalhealth;
			return totalhealth;
		}
		else
		{
			return ownhealth;
		}
	}
	
	//set entity health
	public void setHealth(int newhealth)
	{
		if (newhealth > totalhealth) //make sure it cannot return a health above the set maximum
		{
			newhealth = totalhealth;
		}
		else if (newhealth <= 0)
		{
			//DIED SCRIPT???
			died();
		}
		else
		{
			ownhealth = newhealth;
		}
	}
	
	//adds health to entity
	public void heal(int heal)
	{
		if (heal + ownhealth > totalhealth)
		{
			ownhealth = totalhealth;
		}
		else
		{
			ownhealth += heal;
		}
	}
	
	//decreases health from entity
	public void hit(int hit)
	{
		if (ownhealth - hit <= 0)
		{
			//DIED SCRIPT???
			died();
		}
		else
		{
			ownhealth -= hit;
		}
	}
	
	public void died()
	{
		//delete object?
		//drop items/money?
		//add experience to player?
		
	}
}
